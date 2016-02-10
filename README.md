# Timeline plugin

## Subject

Our project a plugin for the project Timeline. Our main goal is to give music suggestions to users according to one music (and its artist) 
listend by a user. 
From these data, we are using DBpedia and Youtube to give 18 songs to the user, 18 songs which are related to the initial song. 

##Technologies used

We made the development in Java with a Java server called Spring and Jena API. 

##Project structure

You can find the heart of the program in the folder src. We have two controllers to check information on the web, one for DBpedia and another
for Youtube. They are related to two programs (CallDbpedia and CallYoutube).

In the folder database, we configure our database and create our data manager CleanRDF. This previous class is cleaning the file data.rdf 
(which contains the data from the web), extract data and clean them. In fact, we need to clean them because we have several noises in our data.

Then, when we get our 18 songs, we build an ontology from these data. 


