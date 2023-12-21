import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;

public class SqliteDb {
	
    private static final String URL = "jdbc:sqlite:";
    private static String dbUrl = null;
    // TennisPlayer Field Index
    public static final int TPF_TPID         = 0;
    public static final int TPF_LASTNAME     = 1;
    public static final int TPF_FIRSTNAME    = 2;
    public static final int TPF_AGE          = 3;
    public static final int TPF_COUNTRY      = 4;
    public static final int TPF_ORGANIZATION = 5;
    public static final int TPF_RANKING      = 6;
    public static final int TPF_POINTS       = 7;
    public static final int TPF_COUNT        = 8;
    public static String[] TPF_NAME = {
                    "Tpid",
                    "LastName",
                    "FirstName",
                    "Age",
                    "Country",
                    "Organization",
                    "Ranking",
                    "Points"
    };

    public static void setDb(String dbname) {
            dbUrl = URL + dbname;
    }
    
    public static Connection connect() {
    	Connection conn = null;
    	try {
    		Class.forName("org.sqlite.JDBC");
    		conn = DriverManager.getConnection(dbUrl);
    	} 
    	catch (Exception e) {
    		System.out.println("<<< " + e.getMessage());
    		JOptionPane.showMessageDialog(null, "<<< Connection to SQLite DB <" + dbUrl + "> Failed");
    	}
    	return conn;
    }
    
    public static void createTable() {
    	Connection conn = connect();
    	if (conn == null) return;
    	String sql1 = "CREATE TABLE IF NOT EXISTS TennisPlayers (\n" +
    			"Tpid         TEXT PRIMARY KEY,\n" +
    			"LastName     TEXT NOT NULL,\n" +
    			"FirstName    TEXT NOT NULL,\n" +
    			"Age          TEXT NOT NULL,\n" +
    			"Country      TEXT NOT NULL,\n" +
    			"Organization TEXT NOT NULL,\n" +
    			"Ranking      TEXT NOT NULL,\n" +
    			"Points       TEXT NOT NULL\n" +
    			");";
    	try {
    		Statement st = conn.createStatement();
    		st.execute(sql1);
    		st.close();
    		conn.close();
    	} 
    	catch (Exception e) {
    		System.out.println("<<< " + e.getMessage());
    		JOptionPane.showMessageDialog(null, "<<< Table Creation in SQLite DB <" + dbUrl + "> Failed");
    		return;
    		}
    	JOptionPane.showMessageDialog(null, "Created a new table in SQLite DB <" + dbUrl + ">");
    }
    
    public static void initialize(String csvfile) {
    	createTable();
    	ArrayList<String[]> tpList = DbData.readFile(csvfile);
    	for (int i = 0; i < tpList.size(); i++) {
    		String[] tpItem = tpList.get(i);
    		insert(tpItem);
    	}
    	JOptionPane.showMessageDialog(null, "SQLite DB <" + dbUrl + "> Initialized");
    }
    
    public static String[] getList() {
    	ArrayList<String> list = new ArrayList<String>();
    	Connection conn = connect();
    	if (conn == null) return null;
    	String sql = "SELECT Tpid, LastName, FirstName FROM TennisPlayers";
    	try {
    		Statement st = conn.createStatement();
    		ResultSet rs = st.executeQuery(sql);
    		while (rs.next()) {
    			String str = rs.getString("Tpid");
    			str += ": " + rs.getString("LastName");
    			str += ", " + rs.getString("FirstName");
    			list.add(str);
    		}
    		String[] slist = new String[list.size()];
    		int n = 0;
    		for (String s : list) {
    			slist[n] = s;
    			n += 1;
    		}
    		st.close();
    		rs.close();
    		conn.close();
    		JOptionPane.showMessageDialog(null, "SQLite DB <" + dbUrl + "> Data List Obtained");
    		return slist;
    	}
    	catch (Exception e) {
    		System.out.println("<<< " + e.getMessage());
    		JOptionPane.showMessageDialog(null, "<<< SELECT Operation to SQLite DB <" + dbUrl + "> Failed");
    		return null;
    	}
    }

    public static void insert(String[] flist) {
    	Connection conn = connect();
    	if (conn == null) return;
    	try {
    		String sql = "INSERT INTO TennisPlayers(Tpid, LastName, FirstName, Age, Country, Organization, Ranking, Points) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, flist[TPF_TPID]);
    		ps.setString(2, flist[TPF_LASTNAME]);
    		ps.setString(3, flist[TPF_FIRSTNAME]);
    		ps.setString(4, flist[TPF_AGE]);
    		ps.setString(5, flist[TPF_COUNTRY]);
    		ps.setString(6, flist[TPF_ORGANIZATION]);
    		ps.setString(7, flist[TPF_RANKING]);
    		ps.setString(8, flist[TPF_POINTS]);
    		ps.executeUpdate();
    		ps.close();
    		conn.close();
    	}
    	catch (Exception e) {
    		System.out.println("<<< " + e.getMessage());
    		JOptionPane.showMessageDialog(null, "<<< Inserting Data in SQLite DB <" + dbUrl + "> Failed");
    		return;
    	}
    }

}
