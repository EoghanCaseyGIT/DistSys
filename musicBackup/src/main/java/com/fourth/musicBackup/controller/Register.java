package com.fourth.musicBackup.controller;

import com.fourth.musicBackup.model.Playlist;
import com.fourth.musicBackup.model.Song;
import com.fourth.musicBackup.model.User;
import com.fourth.musicBackup.repository.SongRepository;
import com.fourth.musicBackup.repository.UserRepository;
import com.fourth.musicBackup.repository.playlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


@Controller
public class Register {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    playlistRepository playlistRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(User user){

//        UserRepository newUser = null;
//        if (
//                newUser.equals(user)
//                )
//        } else {
//
//        }

        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addNewUser(@Valid User user, Model model){
//        if (bindingResult.hasErrors()){
//            return "index";
//        }
        userRepository.save(user);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("password", user.getPassword());
        return "home";
    }





    @RequestMapping(value = "/parseSongs", method = RequestMethod.POST)
    public String addSong() throws IOException, SAXException, ParserConfigurationException {




            String trackId;
            String trackName;
            String trackArtist;
            String albumName;
            String libraryPersID;

        File inputFile = new File("ITunes Music Library2.xml");


        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
//            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("dict").item(1).getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            Node b = nList.item(i);

            if(b.getNodeName().equals("dict")){
                NodeList theList = b.getChildNodes();
                Song song = new Song();
                for(int y = 0; y < theList.getLength(); y++){
                    Node aNode = theList.item(y);
                    if(aNode.getNodeName().equals("key") && aNode.getFirstChild().getNodeValue().equals("Track ID")){
                        String id = aNode.getNextSibling().getFirstChild().getTextContent();
                        song.setTrackId(id);
                    }
                    if(aNode.getNodeName().equals("key") && aNode.getFirstChild().getNodeValue().equals("Name")){
                        String name = aNode.getNextSibling().getFirstChild().getNodeValue();
//                            System.out.println("--------");
                        song.setTrackName(name);
                    }
                    if(aNode.getNodeName().equals("key") && aNode.getFirstChild().getNodeValue().equals("Artist")){
                        String artist = aNode.getNextSibling().getFirstChild().getNodeValue();
//                            System.out.println(artist);
                        song.setTrackArtist(artist);
                    }
                    if(aNode.getNodeName().equals("key") && aNode.getFirstChild().getNodeValue().equals("Album")){
                        String album = aNode.getNextSibling().getFirstChild().getNodeValue();
//                            System.out.println("--------");
                        song.setAlbumName(album);
                    }
                    if(aNode.getNodeName().equals("key") && aNode.getFirstChild().getNodeValue().equals("Persistent ID")){
                        String libID = aNode.getNextSibling().getFirstChild().getNodeValue();
//                            System.out.println("--------");
                        song.setLibraryPersID(libID);
                    }
                }
                songRepository.save(song);
            }

        }


        return "success";
    }

    @RequestMapping(value = "/parsePlaylist", method = RequestMethod.POST)
    public String parsePlaylists(@Valid Playlist playlist, BindingResult bindingResult, Model model) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{

        ArrayList<Playlist> playlists = new ArrayList<>();
        ArrayList<Integer> playlistTracks;
        File fXmlFile = new File("iTunes Music Library2.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        String xmlbeam = "//dict/key[. = 'Playlists']/following-sibling::*[1]/child::*";
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(xmlbeam).evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            playlistTracks = new ArrayList<>();
            playlist = new Playlist();
            Node playlistNode = nodeList.item(i);

            if (playlistNode.getNodeName().equals("dict")) {
                NodeList playlistsNode = playlistNode.getChildNodes();
                for (int a = 0; a < playlistsNode.getLength(); a++) {
                    Node node = playlistsNode.item(a);
                    if (node.getTextContent().equals("Name")) {
                        playlist.setName(node.getNextSibling().getTextContent());
                    } else if (node.getTextContent().equals("Playlist ID")) {
                        playlist.setId(Integer.parseInt(node.getNextSibling().getTextContent()));
                    } else if (node.getTextContent().equals("Playlist Persistent ID")) {
                        playlist.setPlaylistPersistenceId(node.getNextSibling().getTextContent());
                    } else if (node.getTextContent().equals("Playlist Items")) {
                        NodeList items = node.getNextSibling().getNextSibling().getChildNodes();
                        for (int x = 0; x < items.getLength(); x++) {
                            Node xitem = items.item(x);
                            NodeList nodeList2 = xitem.getChildNodes();
                            for (int h = 0; h < nodeList2.getLength(); h++) {
                                Node node2 = nodeList2.item(h);
                                if (node2.getTextContent().equals("Track ID")) {
                                    playlistTracks.add(Integer.parseInt(node2.getNextSibling().getTextContent()));
                                }
                            }
                        }
                        playlistRepository.save(playlist);
                    }
                }
            }
        }

        return "success";

    }

    @RequestMapping(value = "/rest/files/upload", method = RequestMethod.POST)
    public String importParse(@RequestParam("myFile") MultipartFile myFile) {

        //call method to parse xml file here (myFile)
//		  parseXmlfile(myFile);


        return "userLibrary";
    }


    @RequestMapping(value = "/getSongs", method = RequestMethod.GET)
    public String songs(Song Song){

//        if {
//            user.getUsername().equals("");
//
//        }
        return "songs";
    }

    @RequestMapping(value = "/getPlaylists", method = RequestMethod.GET)
    public String playlists(){

//        if {
//            user.getUsername().equals("");
//
//        }
        return "playlists";
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){

        return "home";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(){

        return "findEdit";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){

        return "index";
    }





}


