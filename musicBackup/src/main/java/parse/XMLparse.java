package parse;


import com.fourth.musicBackup.model.User;
import com.fourth.musicBackup.repository.SongRepository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import sun.net.www.MimeTable;

import javax.xml.parsers.*;
import java.io.*;


public class XMLparse {

    SongRepository songRepository;

    public static void main(String [] args){


        SongRepository songRepository;




        try {

            File inputFile = new File("iTunes Music Library2.xml");
//            File inputFile = new File("iTunes Music Library1.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("dict");
            System.out.println("----------------------------");


            for(int i=0;i<nList.getLength();i++){
                Node b =nList.item(i);
                System.out.println("----------------------------");

                if (b.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) b;
//		               System.out.println("Dict : "
//		                       + eElement
//		                       .getElementsByTagName("dict")
//		                       .item(0)
//		                       .getTextContent());

                   // Gets the track ID
//                    System.out.println("Key Track ID: "
//                            + eElement
//                            .getElementsByTagName("key")
//                            .item(0)
//                            .getTextContent());
                    System.out.println("Track ID : "
                            + eElement
                            .getElementsByTagName("integer")
                            .item(0)
                            .getTextContent());

                    //Gets song name
//                    System.out.println("Key name : "
//                            + eElement
//                            .getElementsByTagName("key")
//                            .item(1)
//                            .getTextContent());
                    System.out.println("Song name : "
                            + eElement
                            .getElementsByTagName("string")
                            .item(0)
                            .getTextContent());

                    //Gets artist name
                    System.out.println("Key artist : "
                            + eElement
                            .getElementsByTagName("key")
                            .item(2)
                            .getTextContent());
                    System.out.println("String artist : "
                            + eElement
                            .getElementsByTagName("string")
                            .item(1)
                            .getTextContent());

//                        songRepository.save(song);




                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//            songRepository.save(song);

}


