package Resources;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EscribirXML {
    public static void escribirAdestradoresXML(List<Object[]> listaTabla) throws IOException, XMLStreamException {
        FileWriter fileWriter = new FileWriter("/home/yoi/IdeaProyects/PokemonHibernate2/src/Resources/adestradores.xml", false);
        fileWriter.close();
        XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newDefaultFactory().createXMLStreamWriter(new FileWriter("/home/yoi/IdeaProyects/PokemonHibernate2/src/Resources/adestradores.xml"));
        xmlStreamWriter.writeStartDocument("1.0");
        xmlStreamWriter.writeStartElement("Adestradores");
        for(Object[] o : listaTabla){
            xmlStreamWriter.writeStartElement("Adestrador");
            xmlStreamWriter.writeAttribute("id", String.valueOf(o[0]));
            xmlStreamWriter.writeStartElement("nome");
            xmlStreamWriter.writeCharacters(o[1].toString());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("Nacemento");
            xmlStreamWriter.writeCharacters(o[2].toString());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndElement();
        }
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.flush();
        xmlStreamWriter.close();
    }

    public static void escribirPokedexXML(List<Object[]> listaTabla) throws IOException, XMLStreamException {
        FileWriter fileWriter = new FileWriter("/home/yoi/IdeaProyects/PokemonHibernate2/src/Resources/pokedex.xml", false);
        fileWriter.close();
        XMLStreamWriter writter = XMLOutputFactory.newDefaultFactory().createXMLStreamWriter(new FileWriter("/home/yoi/IdeaProyects/PokemonHibernate2/src/Resources/pokedex.xml"));
        writter.writeStartDocument("1.0");
        writter.writeStartElement("Pokedex");
        for(Object[] o : listaTabla){
            writter.writeStartElement("Entrada");
            writter.writeAttribute("id",o[0].toString());
            writter.writeStartElement("nome");
            writter.writeCharacters(o[1].toString());
            writter.writeEndElement();
            writter.writeStartElement("peso");
            writter.writeCharacters(o[2].toString());
            writter.writeEndElement();
            writter.writeStartElement("miscelaneo");
            writter.writeCharacters(o[3].toString());
            writter.writeEndElement();
            writter.writeEndElement();
        }
        writter.writeEndElement();
        writter.writeEndDocument();
        writter.flush();
        writter.close();
    }
}

