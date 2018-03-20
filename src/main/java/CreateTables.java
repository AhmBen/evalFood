import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

	private Connection c = null;
    private String[] listeCat = {"fruits","légumes","conserve","boulangerie","épices","boissons","sauce"};
	
	public CreateTables(Connection c) throws SQLException{
		this.c = c;
		testIfTableCatsExist();
		testIfTableFoodExist();
	}

	private void testIfTableCatsExist() throws SQLException
	{
		Statement state = c.createStatement();
		String query = "SELECT 1 FROM cats";
		 
		try {
			state.execute(query);
		}
		catch(SQLException e) {
			System.out.println("-------------------------------------");
			System.out.println("Table Cats not Found");
			createCats();
		}
		finally{
			if(state != null){
				state.close();
			}
		}
	}
	
	private void createCats() throws SQLException{
		
		Statement state = c.createStatement();
		String query = 	"CREATE TABLE cats ( " + 
				"    catId SERIAL NOT NULL, " + 
				"	name varchar NOT NULL, " + 
				"	CONSTRAINT catId_pkey PRIMARY KEY (catId) " + 
				");";
		
		try {
			state.execute(query);
			System.out.println("Table Cats created");
			initCats();
		}
		catch(SQLException e) {
			System.out.println("-------------------------------------");
			System.out.println("Table Cats NOT created");
			System.out.println(query);
		}
		finally{
			if(state != null){
				state.close();
			}
		}
	}
	
	private void initCats() throws SQLException
	{
		System.out.println("Stat Init Table Cats");
		
		String query = "INSERT INTO cats (name) VALUES (?)";
		PreparedStatement ps = c.prepareStatement(query);
		for (int i = 0; i < listeCat.length; i++ )
		{
			ps.setString( 1, listeCat[i] );
			try {
				ps.executeUpdate();
			}
			catch(SQLException e) {
				System.out.println("-------------------------------------");
				System.out.println("Init Table Cats Failed");
				System.out.println(query);
			}
		}
		if(ps != null){
			ps.close();
		}
		System.out.println("End Init Table Cats");
	}
	private void testIfTableFoodExist() throws SQLException
	{
		Statement state = c.createStatement();
		String query = "SELECT 1 FROM food";
		 
		try {
			state.execute(query);
		}
		catch(SQLException e) {
			System.out.println("-------------------------------------");
			System.out.println("Table Food not Found");
			createFood();
		}
		finally{
			if(state != null){
				state.close();
			}
		}
	}
	
	private void createFood() throws SQLException{
		
		Statement state = c.createStatement();
		String query = 	"CREATE TABLE food " + 
						"( " + 
						"   foodId SERIAL NOT NULL, " + 
						"	name varchar NOT NULL, " + 
						"	catId integer NOT NULL, " + 
						"	energie integer NOT NULL, " + 
						"	proteines integer NOT NULL, " + 
						"	glucides integer NOT NULL, " + 
						"	lipides integer NOT NULL, " + 
						"   CONSTRAINT food_pkey PRIMARY KEY (foodId), " + 
						"	CONSTRAINT catId_fkey FOREIGN KEY (catId) REFERENCES cats (catId), " + 
						"	CONSTRAINT energie_positive CHECK (energie >= 0.0), " + 
						"	CONSTRAINT proteines_positive CHECK (proteines >= 0.0), " + 
						"	CONSTRAINT glucides_positive CHECK (glucides >= 0.0), " + 
						"	CONSTRAINT lipides_positive CHECK (lipides >= 0.0) " + 
						"	 " + 
						");";
		
		try {
			state.execute(query);
			System.out.println("Table Food created");
		}
		catch(SQLException e) {
			System.out.println("-------------------------------------");
			System.out.println("Table Food NOT created");
			System.out.println(query);
		}
		finally{
			if(state != null){
				state.close();
			}
		}
	}
}
