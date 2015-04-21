package com.hw1.devlyn.thewateringhole;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by Devlyn on 4/8/2015.
 */
public class MyApplicationClass extends Application {
    public static GoogleApiClient mGoogleApiClient;

    public static GoogleApiClient getClient() {
        return mGoogleApiClient;
    }

    public static void setClient(GoogleApiClient newGoogleApiClient) {
        if(mGoogleApiClient == null) {
            mGoogleApiClient = newGoogleApiClient;
        }
    }

    public static class MySQLAccess {
        private Connection connect = null;
        private Statement statement = null;
        private PreparedStatement preparedStatement = null;
        private ResultSet resultSet = null;
        private String dbCon = "jdbc:mysql://sql4.freesqldatabase.com:3306/sql474837";
        private String usrName = "sql474837";
        private String usrPswd = "zY3*gS1!";



        public void readDataBase() throws Exception {
            try {
                // This will load the MySQL driver, each DB has its own driver
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                // Setup the connection with the DB
                Log.d("Before Connect","Register");
                connect = DriverManager
                        .getConnection(dbCon, usrName, usrPswd);
                Log.d("Connected","Register");

                // PreparedStatements can use variables and are more efficient
                preparedStatement = connect
                        .prepareStatement("insert into sql474837.Users (`userName`, `password`, `email`) values (?,?,?)");
                                /*feedback.comments values (default, ?, ?, ?, ? , ?, ?)");*/
                // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
                // Parameters start with 1
                preparedStatement.setString(1, "Dev");
                preparedStatement.setString(2, "BoB");
                preparedStatement.setString(3, "dev@gmail.com");
                preparedStatement.executeUpdate();
                Log.d("SQLConnect","added to DB");


            } catch (Exception e) {
                e.getStackTrace();
                throw e;
            } finally {
                close();
            }

        }

        // You need to close the resultSet
        private void close() {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connect != null) {
                    connect.close();
                }
            } catch (Exception e) {

            }
        }

    }
}
