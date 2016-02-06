def extract_data():
    print("coucuo")
    fichier = open("data.rdf", "r")
    text = fichier.read()
    fichier.close()
    text = text.replace("<?xml version='1.0'?>\n", "")
    text = text.replace("<rdf:RDF\n", "")
    text = text.replace("</rdf:RDF>", "")
    text = text.replace("xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n", "")
    return text.replace("xmlns:j.0=\"http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/\">\n", "")




def parseFile():
    intro = '<?xml version=\'1.0\'?>\n<rdf:RDF\nxmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"\nxmlns:j.0="http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/">\n'
    fin = '</rdf:RDF>'
    return intro+extract_data()+fin


def main():
    new_file = parseFile()
    fichier = open("data.rdf", "w")
    fichier.write(new_file)


main()