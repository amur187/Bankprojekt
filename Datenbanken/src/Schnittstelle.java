import java.sql.*;


 abstract class Schnittstelle {
     static final String DB_URL = "jdbc:postgresql://db.f4.htw-berlin.de/_s0563207__beleg";
     static final String DB_USER = "_s0563207__beleg_generic";
     static final String DB_PASS = "belegarbeit";
     static final String kategorie1 = "MannschaftID";
     static final String kategorie2 = "Name";
     static final String kategorie3 = "Sitz";
     static final String kategorie4 = "Farben";
     static final String kategorie5 = "Mitglieder";
     static final String kategorie6 = "Heimstadion";

     static Connection verbinden() throws SQLException {
         return DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
     }

     static void datensatzHinzufuegen(String name, String sitz, String farben, int mitglieder, int heimstadion){
         try {
             Connection conn = verbinden();
             Statement stmt = conn.createStatement();
           String sql = "INSERT INTO mannschaft(" + kategorie2 + "," + kategorie3 + ","

                     + kategorie4  + ","+ kategorie5 + ")" + " VALUES('" +
                     name + "','" + sitz  + "','" + farben + "'," + mitglieder  +
                   ")";



             System.out.println(sql);
             ResultSet rs = stmt.executeQuery(sql);


         } catch(SQLException e) {
             e.printStackTrace();
         }

     }
    static void tabelleAusgeben(){
        try {
            Connection conn = verbinden();
            Statement stmt = conn.createStatement();

            String sql;
            sql = "SELECT * FROM Mannschaft";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.printf("%-12s | %-20s | %-12s | %-12s | %s | %s \n", kategorie1, kategorie2 , kategorie3,kategorie4,kategorie5,kategorie6);
            System.out.println("---------------------------------------------------------------------------------------------");

            while(rs.next()){
                int mannschaftID = rs.getInt(kategorie1);
                String name = rs.getString(kategorie2);
                String sitz = rs.getString(kategorie3);
                String farbe = rs.getString(kategorie4);
                int mitglieder = rs.getInt(kategorie5);
                int heimstadion = rs.getInt(kategorie6);

                System.out.printf("%-12d | %-20s | %-12s | %-12s | %10d | %10s  \n", mannschaftID, name, sitz,farbe,mitglieder,heimstadion);
            }
            rs.close();
            stmt.close();
            conn.close();
        }  catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
