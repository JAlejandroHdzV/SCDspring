//package com.cummins.scd.util;
//
//import org.xml.sax.Attributes;
//import org.xml.sax.SAXException;
//import org.xml.sax.helpers.DefaultHandler;
//
//public class SamlSessionParserHandler extends DefaultHandler {
//
//    private String uid;
//    private boolean uidFound = false;
//    private boolean uidFlag = false;
//
//    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        if(qName.equalsIgnoreCase("Attribute") && attributes.getValue("Name").equalsIgnoreCase("uid")){
//            uidFound = true;
//        }
//        if(qName.equalsIgnoreCase("AttributeValue") && uidFound){
//            uidFlag = true;
//        }
//    }
//
//    public void endElement(String uri, String localName, String qName) throws SAXException {
//    
//    }
// 
//    public void characters(char[] ch, int start, int length) throws SAXException {
//        String value = new String(ch, start, length).trim();
//
//        if(uidFlag){
//            setUid(value.toUpperCase());
//            uidFound = false;
//            uidFlag = false;
//        }
//    }
//
//    public String getUid() {
//        return uid;
//    }
//
//    public void setUid(String uid) {
//        this.uid = uid;
//    }
//    
//}
