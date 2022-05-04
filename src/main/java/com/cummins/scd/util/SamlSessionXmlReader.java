//package com.cummins.scd.util;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
//
//import org.apache.commons.codec.binary.Base64;
//
//public class SamlSessionXmlReader {
//    private InputStream samlXML;
//    private SamlSessionParserHandler samlHandler;
//    private boolean correctStatus = true;
//
//    public SamlSessionXmlReader(String samlResponse) {
//        if(samlResponse != null){
//            try {
//                Base64 base64 = new Base64();
//                byte[] decodedB = base64.decode(samlResponse);
//                String decodedS = new String(decodedB);
//                this.samlXML = new ByteArrayInputStream(decodedS.getBytes());
//            } catch (Exception e) {
//                this.correctStatus = false;
//                this.samlXML = null;
//            }
//            initSessionAttrs();
//        } else {
//            this.samlHandler = null;
//        }
//    }
//
//    public void initSessionAttrs() {
//        //Create a "parser factory" for creating SAX parsers
//		SAXParserFactory spfac = SAXParserFactory.newInstance();
//
//        //Now use the parser factory to create a SAXParser object
//        SAXParser sp;
//        try {
//            sp = spfac.newSAXParser();
//            this.samlHandler = new SamlSessionParserHandler();
//            sp.parse(this.samlXML, this.samlHandler);
//        } catch (Exception e) {
//            this.samlHandler = null;
//        }
//    }
//
//    public String getUid() {
//
//        if(!this.correctStatus){
//            return "Bad SAML String";
//        } else {
//            return samlHandler == null ? null : samlHandler.getUid();
//        }
//    }
//}
