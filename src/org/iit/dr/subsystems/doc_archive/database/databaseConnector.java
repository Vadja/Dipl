package org.iit.dr.subsystems.doc_archive.database;

import java.sql.*;

/**
 * Created by Vadzim on 12.05.2014.
 */
public class databaseConnector {
    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:h2:~/doc_storage",
                    "root", "root");
            Statement st = null;
            st = conn.createStatement();
//            st.execute("CREATE TABLE TEST");
//            st.execute("INSERT INTO TEST VALUES(default,'HELLO')");
//            st.execute("INSERT INTO TEST(NAME) VALUES('JOHN')");
//            String name1 = "Jack";
//            String q = "insert into TEST(name) values(?)";
//            PreparedStatement st1 = null;
//
//            st1 = conn.prepareStatement(q);
//            st1.setString(1, name1);
//            st1.execute();

            ResultSet result;
            result = st.executeQuery("SELECT * FROM DOCUMENT");
            while (result.next()) {
                String name = result.getString("NAME");

                System.out.println(result.getString("ID")+" "+name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}