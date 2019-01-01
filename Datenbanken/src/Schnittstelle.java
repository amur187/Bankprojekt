import java.sql.*;
import java.util.Scanner;


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
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
    static void datensatzLoeschen(int mannschaftID) {
        try {
            Connection conn = verbinden();
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM mannschaft WHERE mannschaftID =" + mannschaftID;
            stmt.executeUpdate(sql);
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
    static void datensatzHinzufuegen(int mannschaftID, String name, String sitz, String farben, int mitglieder, int heimstadion) {
        try {
            Connection conn = verbinden();
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO mannschaft(" + kategorie1 + "," + kategorie2 + "," + kategorie3 + "," + kategorie4 + "," + kategorie5 + "," + kategorie6 + ")" + " VALUES (" + mannschaftID + ",'" + name + "','" + sitz + "','" + farben + "','" + mitglieder + "','" + heimstadion + "')";

            //  System.out.println(sql);
            stmt.execute(sql);
            conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    static void zeileAusgeben() {
        try {
            Connection conn = verbinden();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql;
            sql = "SELECT * FROM Mannschaft";
            ResultSet rs = stmt.executeQuery(sql);
            rs.first();
            navigation(rs);
            conn.close();
            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    static void navigation(ResultSet rs) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean started = true;
        Menu m = new Menu();
        while(started) {
            m.showOptions();
            String auswahl = sc.next();
            switch(auswahl) {
                case "l":
                    started = false;
                    break;
                case "n":
                    nachsteZeile(rs);
                    break;
                case "p":
                    vorherigeZeile(rs);
                    break;
            }
        }
        m.showMenu();
    }
    static void ausgabeListe(ResultSet rs) {
        try {
            int mannschaftID = rs.getInt(kategorie1);
            String name = rs.getString(kategorie2);
            String sitz = rs.getString(kategorie3);
            String farbe = rs.getString(kategorie4);
            int mitglieder = rs.getInt(kategorie5);
            int heimstadion = rs.getInt(kategorie6);
            System.out.printf("MannschaftID: %d \n", mannschaftID);
            System.out.printf("Name: %s  \n", name);
            System.out.printf("Sitz: %s \n", sitz);
            System.out.printf("Farbe: %s \n", farbe);
            System.out.printf("Mitglieder: %d \n", mitglieder);
            System.out.printf("HeimstadionID: %d \n", heimstadion);
            System.out.println();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    static void nachsteZeile(ResultSet r) throws SQLException {
        if(r.isLast()) {
            r.first();
            ausgabeListe(r);
        } else {
            r.next();
            ausgabeListe(r);
        }
    }
    static void vorherigeZeile(ResultSet r) throws SQLException {
        if(r.isFirst()) {
            r.last();
            ausgabeListe(r);
        } else {
            r.previous();
            ausgabeListe(r);
        }
    }
    static void tabelleAusgeben() {
        try {
            Connection conn = verbinden();
            Statement stmt = conn.createStatement();

            String sql;
            sql = "SELECT * FROM Mannschaft";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.printf("%-15s | %-60s | %-30s | %-20s | %-15s | %-15s \n", kategorie1, kategorie2, kategorie3, kategorie4, kategorie5, kategorie6);
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while(rs.next()) {
                int mannschaftID = rs.getInt(kategorie1);
                String name = rs.getString(kategorie2);
                String sitz = rs.getString(kategorie3);
                String farbe = rs.getString(kategorie4);
                int mitglieder = rs.getInt(kategorie5);
                int heimstadion = rs.getInt(kategorie6);
                System.out.printf("%-15d | %-60s | %-30s | %-20s | %-15d | %-15s  \n", mannschaftID, name, sitz, farbe, mitglieder, heimstadion);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
