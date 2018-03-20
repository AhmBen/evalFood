import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

	public Connexion() {
	}

	public static Connection getConnexion() {
		Connection conn = null;
		
		try{
			conn= DriverManager.getConnection(
												"jdbc:postgresql://stampy.db.elephantsql.com:5432"
												,"tmmeradk"
												,"2JxoL4OXx_f1G6lTSPW56kEF-GGKCI9a"
											);
			
						
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
