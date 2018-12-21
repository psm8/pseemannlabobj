import java.sql.*;
import java.util.ArrayList;

public class DB {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;


    public void createTable(){
        try {
            connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(            "CREATE TABLE IF NOT EXISTS pracownicy (" +
                    "PESEL CHAR(13) NOT NULL, wynagodzenie INT,PRIMARY KEY (PESEL));");


        }catch (SQLException ex){
            // handle any errors

        }finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }

    public void add(String pesel, double wynagrodzenie){
        try {
            connect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM pracownicy");
            boolean existFlag = false;
            while(rs.next()){
                if(rs.getString(1).equals(pesel)){
                    existFlag = true;
                }
            }
            if(!existFlag) {
                stmt.executeUpdate("INSERT INTO pracownicy VALUES(" + pesel +
                        "," + wynagrodzenie + ")");
            }

        }catch (SQLException ex){
            ex.printStackTrace();

        }finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }

    public ArrayList<Pracownik> get(){
        ArrayList<Pracownik> ps = new ArrayList<>();
        try {
            connect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM pracownicy");
            while(rs.next()){
                ps.add(new Pracownik(rs.getString(1),rs.getDouble(2)));
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
        return ps;
    }


    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/seemann",
                    "seemann", "H2kcSgDjy8hZQMF5");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }catch(Exception e){e.printStackTrace();}
    }
}
