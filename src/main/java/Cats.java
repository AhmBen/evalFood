import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.util.PSQLException;

public class Cats {

	private Connection c = null;

	private static int catId = 0;
	private static String catName = "";
	
	public Cats(Connection c) {
		this.c = c;
	}

	public void addCat() throws SQLException{		
		
	    System.out.println("Saisir la catégorie à ajouter");
	    catName = App.sc.nextLine();
    	
		String query = "INSERT INTO cats (name) VALUES (?)";
		
		PreparedStatement ps = c.prepareStatement(query);
		ps = c.prepareStatement(query);
		ps.setString( 1, catName );
       	
		try {
			ps.executeUpdate();
			System.out.println("Catégorie "+catName+" ajoutée à la base.");
		}
		catch(SQLException e) {
			System.out.println("-------------------------------------");
			System.out.println("Insert cats ("+catName+") failed\n" +e+ "\nQuery : " +query);
		}
		finally{
			if(ps != null){
			ps.close();
			}
		}
		
	}

	public void deleteCat() throws SQLException {
		System.out.println("Nom de la catégorie à supprimer : ");
		catName = App.sc.nextLine();
				
		String query = "DELETE FROM cats WHERE name = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString( 1, catName );
		System.out.println("-------------------------------------");
		
		try {
	    	int i = ps.executeUpdate();
	    	
	    	if (i > 0) {
	    		System.out.println("Catégorie "+catName+" supprimé");
	    	}
	    	else {
	    		System.out.println("Catégorie "+catName+" n'existe pas dans la base");
	    	}	    		
		}
		catch(PSQLException e) { //Générée en cas de blocage à cause d'une contrainte
			System.out.println("Can NOT Delete Categorie, may be because "+catName+" exist on Food Table ! ");
	    	System.out.println("Delete before all the Food with this CatName ("+catName+")");
		}
		catch(SQLException e) {
			System.out.println("Delete "+catName+" failed ! "+e);
			System.out.println("Query : "+query);
		}
		finally{
			if(ps != null){
				ps.close();
			}
		}
	}
	
	public void printCats() throws SQLException {
		String query = "SELECT name as catName FROM cats ORDER BY name";
		Statement state = c.createStatement();
		
		try {
			ResultSet rs = state.executeQuery(query);
			System.out.println("Liste des catégories:");
			System.out.println("-------------------------------------");
	    	while ( rs.next() ) {
		        System.out.println(rs.getString("catName"));
		    }    		
		}
		catch(SQLException e) {
			System.out.println("-------------------------------------");
			System.out.println("List All Cats failed ! "+e);
			System.out.println("Query : "+query);
		}
		finally{
			if(state != null){
				state.close();
			}
		}
	}
}
