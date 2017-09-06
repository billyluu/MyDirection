package com.billylu.mydirection.Utils;

import android.provider.DocumentsContract;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by billylu on 2017/9/6.
 */

public class ParserXML {

    public ParserXML() {

    }

    public String[] parseXML(String XMLContent) {
        String[] latlng = new String[2];
        try {
            InputStream inputStream = new ByteArrayInputStream(XMLContent.getBytes(StandardCharsets.UTF_8));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            Element root = document.getDocumentElement();

            String lat = root.getElementsByTagName("lat").item(0).getTextContent();
            String lng = root.getElementsByTagName("lng").item(0).getTextContent();

            latlng[0] = lat;
            latlng[1] = lng;

        }catch (Exception e){

        }

        return latlng;
    }

}
