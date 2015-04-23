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
        private static Connection connect = null;
        private static Statement statement = null;
        private static PreparedStatement preparedStatement = null;
        private static ResultSet resultSet = null;
        private static final String DBCON = "jdbc:mysql://sql4.freesqldatabase.com:3306/sql474837";
        private static final String USERNAME = "sql474837";
        private static final String USERPSWD = "zY3*gS1!";

        public static void registerUser(String userName, String userPswd, String email) { // PreparedStatements can use variables and are more efficient
            try {
                preparedStatement = connect
                        .prepareStatement("insert into sql474837.Users (userName, password, email) values (?,?,?)");
                //
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, userPswd);
                preparedStatement.setString(3, email);
                preparedStatement.executeUpdate();
                Log.d("SQLConnect", "added to DB");
            } catch (SQLException e) {
                Log.e("MYMYSQLACCESS", e.getMessage());
            }
        }

        public static void readDataBase() throws Exception {
            try {
                // This will load the MySQL driver, each DB has its own driver
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                // Setup the connection with the DB
                Log.d("Before Connect","Register");
                connect = DriverManager
                        .getConnection(DBCON, USERNAME, USERPSWD);
                Log.d("Connected","Register");
            } catch (Exception e) {
                e.getStackTrace();
                throw e;
            }

        }

        // You need to close the resultSet
        private static void close() {
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
