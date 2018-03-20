import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion {

	public Connexion() {
	}

	public static Connection getConnexion() throws IOException {
		Connection conn = null;
		
		final Properties prop = new Properties();
		InputStream input = null;
		
		try{
			
			input = new FileInputStream("src/main/java/config/properties/bdd.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			conn= DriverManager.getConnection(	prop.getProperty("db.url"),
												prop.getProperty("db.username"),
												prop.getProperty("db.password")
											);
			/*
			conn= DriverManager.getConnection(
												"jdbc:postgresql://stampy.db.elephantsql.com:5432"
												,"tmmeradk"
												,"2JxoL4OXx_f1G6lTSPW56kEF-GGKCI9a"
											);
			*/
			//
						
		}
		catch(SQLException e) {
			System.out.println("Connexion KO : " + e);
		}
		
		return conn;
	}
	
	public static void closeConnection(Connection c) throws SQLException {
		if(c != null){
			c.close();
		}
	}
	
}
