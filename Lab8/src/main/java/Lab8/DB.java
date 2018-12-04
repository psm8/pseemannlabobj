package Lab8;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DB{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ObservableList<Row> data;

    public void buildData(){
        data = FXCollections.observableArrayList();
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            rs = stmt.executeQuery("SELECT * FROM books");

            while(rs.next()){
                Row row = new Row();
                row.setIsbn(rs.getString(1));
                row.setTitle(rs.getString(2));
                row.setAuthor(rs.getString(3));
                row.setYear(rs.getString(4));
                data.add(row);
            }
        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }

    public void addData(String isbn, String title, String author, String year){
        try{
            Integer.parseInt(year);
        }catch (Exception ex){
            YearMustBeIntBox.display();
        }
        try {
            connect();
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO books VALUES ("+isbn+","+title+","+author+","+year+")");

        }catch (SQLException ex){
            WasntAbleToAddBox.display(ex.getMessage());

        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }

    public ObservableList<Row> getData(){
        return data;
    }

    private void connect() {
        boolean notConnected = true;
        int counter = 0;
        while (notConnected && counter < 3) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/seemann",
                        "seemann", "H2kcSgDjy8hZQMF5");
            } catch (SQLException ex) {
                counter++;
                continue;
                // handle any errors
            } catch (Exception e) {
                counter++;
                continue;
            }
            notConnected = false;
        }
        if(counter == 3) {
            NoConnectionBox.display();
        }

    }

}