package com.hw1.devlyn.thewateringhole;

import android.os.AsyncTask;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Devlyn on 4/21/2015.
 */
public class ConnectDb extends AsyncTask<String, Void, String> {

    private Exception exception;
    private MyApplicationClass.MySQLAccess dao = new MyApplicationClass.MySQLAccess();

    protected String doInBackground(String... urls) {
        try {
            dao.readDataBase();
        } catch (Exception e) {
            this.exception = e;
        }
        return null;
    }
}
