/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package hello.youtubeEngine;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Print a list of videos matching a search term.
 *
 * @author Jeremy Walker
 */
public class YoutubeQuerry {

    /**
     * Define a global variable that identifies the name of a file that
     * contains the developer's API youtube.properties.
     */

    private final long NUMBER_OF_VIDEOS_RETURNED = 1;

    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private YouTube youtube;

    /**
     * Initialize a YouTube object to search for videos on YouTube. Then
     * display the name and thumbnail image of each video in the result set.
     */
    public String youtubeRequest(String artist, String title) {
        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.

            youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Prompt the user to enter a query term.
            String queryTerm = artist + "." + title;

            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");

            // Set your developer youtube.properties from the Google Developers Console for
            // non-authenticated requests. See:
            // https://console.developers.google.com/
            String apiKey = "AIzaSyDbwRQvCvTn8R2GBrwmMiWWoAb5hC_Zv9k";
            search.setKey(apiKey);
            search.setQ(queryTerm);
            // Restrict the search results to only include videos. See:
            // https://developers.google.com/youtube/v3/docs/search/list#type
            search.setType("video");
            search.setOrder("viewCount");
            // To increase efficiency, only retrieve the fields that the
            // application uses.
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();

            YouTube.Videos.List listVideosRequest = youtube.videos().list("statistics").setId("" + searchResultList.get(0).getId().getVideoId()).setKey(apiKey);
            VideoListResponse listResponse = listVideosRequest.execute();
            List<Video> videoList = listResponse.getItems();

            if (searchResultList != null && videoList != null) {
                return "" + videoList.get(0).getStatistics().getViewCount();
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return null;
    }

    public Map<String, Integer> closeSong(List<List<String>> song) {
        Map<String, Integer> interMap = new HashMap<>();
        for(List<String> s : song) {
            int view = Integer.parseInt(youtubeRequest(s.get(0), s.get(1)));
            interMap.put(s.get(1), view);
        }
        return returnResult(interMap, 3);
    }

    public Map<String, Integer> returnResult(Map<String, Integer> interMap, int numberToShow) {
        List<String> cles = new ArrayList<>(interMap.keySet());
        Collections.sort(cles, new IntComparator(interMap));
        Map<String, Integer> result = new HashMap<>();

        for(int i = 0; i<numberToShow && i<=(interMap.keySet().size() - 1); i++) {
            result.put(cles.get((interMap.keySet().size() - 1) - i), interMap.get(cles.get((interMap.keySet().size() - 1) - i)));
        }

        return result;
    }
}

class IntComparator implements Comparator<String> {
    private Map<String, Integer> map;//pour garder une copie du Map que l'on souhaite traiter

    public IntComparator(Map<String, Integer> map){
        this.map = map; //stocker la copie pour qu'elle soit accessible dans compare()
    }

    @Override
    public int compare(String id1, String id2){
        //récupérer les personnes du Map par leur identifiant
        int p1 = map.get(id1);
        int p2 = map.get(id2);
        return p1 - p2;
    }
}