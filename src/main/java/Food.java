import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

import org.postgresql.util.PSQLException;

public class Food {
	
	private Connection c = null;
	
	private static int foodId = 0;
	private static String foodName = "";
	private static int catId = 0;
	private static String catName = "";
	private static double energie = 0;
	private static double proteines = 0;
	private static double glucides = 0;
	private static double lipides = 0;

	public Food(Connection c) {
		this.c = c;
	}

	public void addFood() throws SQLException{		
		
	    System.out.println("Saisir l'ingredient à ajouter");
	    foodName = App.sc.nextLine();
    	
	    do {
	    	System.out.println("Son Type");
	    	catName = App.sc.nextLine();
	    	catId = ifCatExist(catName);
	    	//Do until cat exist (may be later, get possibilty to create a new cat, but it will be a new feature)
	    } while (catId <= 0);
    	
    	energie = checkNumberValue("Sa valeur énergétique (en kcal)");
    	proteines = checkNumberValue("Son taux de protéines (g/100g)");
    	glucides = checkNumberValue("Son taux de glucides (g/100g)");
    	lipides = checkNumberValue("Son taux de lipides (g/100g)");
    	
    	insertFood();
	}
	
	private void insertFood() throws SQLException{
		String query = "UPDATE food SET catId = ?, "
									 +" energie = ?, "
								 	 +" proteines = ?, "
								 	 +" glucides = ?, "
									 +" lipides = ? "
								+" WHERE name = ?";
		
		PreparedStatement ps = c.prepareStatement(query);
    	ps.setInt( 1, catId );
    	ps.setDouble( 2, energie );
    	ps.setDouble( 3, proteines );
    	ps.setDouble( 4, glucides );
    	ps.setDouble( 5, lipides );
    	ps.setString( 6, foodName );
    	
		try {
	        if(ps.executeUpdate() > 0)   //if nothing to update            
	        {
	        	System.out.println("Ingredient "+foodName+" mis à jour.");	        	 
	        }
	        else { //If no Row with this food, we try ton insert it
	        	
	        	query = "INSERT INTO food (name,catId,energie,proteines,glucides,lipides) "
	    				+"VALUES (?, ?, ?,?,?,?)";
	        	
	        	ps = c.prepareStatement(query);
	        	ps.setString( 1, foodName );
	        	ps.setInt( 2, catId );
	        	ps.setDouble( 3, energie );
	        	ps.setDouble( 4, proteines );
	        	ps.setDouble( 5, glucides );
	        	ps.setDouble( 6, lipides );	        	
	        	
	    		
	        	try {
		        	ps.executeUpdate();
		        	System.out.println("Ingredient "+foodName+" ajouté à la base.");
	        	}
	        	catch(PSQLException e) { //Générée en cas de blocage à cause d'une contrainte
	        							 //Dans ce cas les valeurs doivent etre >=0
	    			System.out.println("Can NOT insert Food in database, may be because a illegal value on numeric values ! ");
	    	    	System.out.println("Must be greater or equal to zero");
	    		}
	        }
	    }
		catch(SQLException e) {
			System.out.println("-------------------------------------");
			System.out.println("Insert food ("+foodName+") failed\n" +e+ "\nQuery : " +query);
		}
		finally{
			if(ps != null){
				ps.close();
			}
		}

	}
	
	public void deleteFood() throws SQLException {
		System.out.println("Nom de l'aliment à supprimer : ");
		foodName = App.sc.nextLine();
				
		String query = "DELETE FROM food WHERE name = ?";
		PreparedStatement ps = c.prepareStatement(query);
    	ps.setString( 1, foodName );
    	
		try {
	    	int i = ps.executeUpdate();
	    	System.out.println("-------------------------------------");
	    	if (i > 0) {
	    		System.out.println("Aliment "+foodName+" supprimé");
	    	}
	    	else {
	    		System.out.println("Aliment "+foodName+" n'existe pas dans la base");
	    	}	    		
		}
		catch(SQLException e) {
			System.out.println("-------------------------------------");
			System.out.println("Delete "+foodName+" failed ! "+e);
			System.out.println("Query : "+query);
		}
		finally{
			if(ps != null){
				ps.close();
			}
		}
	}
	
	public void printFood() throws SQLException {
		Statement state = c.createStatement();
		String query = "SELECT " + 
							"food.name as foodName, " + 
							"cats.name as catName, " + 
							"food.energie as energie, " + 
							"food.proteines as proteines, " + 
							"food.glucides as glucides, " + 
							"food.lipides as lipides " + 
							"FROM food LEFT JOIN cats ON food.catId = cats.catId ORDER BY food.name;";
		
		try {
			System.out.println("-------------------------------------");
			ResultSet rs = state.executeQuery(query);
			int cpt = 0;
			printLine();
			System.out.println(
								String.format("|%-15s|","Ingrédient") + 
								String.format("%-15s|","Catégorie") +
								String.format("%7s|","Energie") +
								String.format("%9s|","Proteines")+
								String.format("%8s|","Glucides") +
								String.format("%8s|","Lipides")
							);
			System.out.println(
								String.format("|%-15s|","") + 
								String.format("%-15s|","") +
								String.format("%7s|","(kcal)") +
								String.format("%9s|","(g/100g)")+
								String.format("%8s|","(g/100g)") +
								String.format("%8s|","(g/100g)")
							);
			printLine();
			
			while ( rs.next() ) {
				cpt++;

		        System.out.println(
					        		String.format("|%-15s|",rs.getString("foodName")) + 
									String.format("%-15s|",rs.getString("catName")) +
									String.format("%7d|",rs.getInt("energie")) +
									String.format("%9d|",rs.getInt("proteines")) +
									String.format("%8d|",rs.getInt("glucides")) +
									String.format("%8d|",rs.getInt("lipides")) 
								);
		        }
			if (cpt == 0)
			{
				System.out.println("|                    Aucun ingredient à afficher                    |");
				printLine();
			}
			else
			{
				printLine();
			}
		}
		catch(SQLException e) {
			System.out.println("-------------------------------------");
			System.out.println("Select Liste failed ! "+e);
			System.out.println("Query : "+query);
		}
		finally{
			if(state != null){
				state.close();
			}
		}
	}
	
	private void printLine() {
		System.out.println("|===================================================================|");
	}
	
	private int ifCatExist(String catName) throws SQLException {		
		Statement state = c.createStatement();
		String query = "SELECT catId FROM cats WHERE name = '"+catName+"'";
		int catId = 0;
		
		try {
			ResultSet rs = state.executeQuery(query);
			if (rs.next()){
				catId =  rs.getInt("catId");
			}
			else {
				System.out.println("-------------------------------------");
				System.out.println("Ce type d'ingredient ("+catName+") n'existe pas !");
			}
		}
		catch(SQLException e) {
			System.out.println("-------------------------------------");
			System.out.println("Select Cat failed ! "+e);
			System.out.println("Query : "+query);
		}
		finally{
			if(state != null){
				state.close();
			}
		}
		
		return catId;
	}
	
	private double checkNumberValue(String message)
	{
		boolean valueOk = true;
		double inputValue = 0;
		
		do {
    		try 
    		{
    			System.out.println(message);
       			if(App.sc.hasNext())
    	        {
    				inputValue = App.sc.nextDouble();  
    				if (inputValue < 0) {
           				System.out.println("La valeur doit être supérieure ou égale à zero");		
        				valueOk = false;
           			}
    				else {
    					valueOk = true;
    				}
    	        }
       			
			} 
    		catch (InputMismatchException e) 
    		{		
				System.out.println("Valeur non numérique, merci de saisir une autre valeur !");		
				valueOk = false;
			}
    		finally
    		{
    			App.sc.nextLine();
    		}
    	} while (!valueOk);	
		
		return inputValue;
	}
	
	
	
	public int getFoodId() {
		return foodId;
	}
	
	public void setfoodId(int foodId) {
		this.foodId = foodId;
	}
	
	public String getFoodName() {
		return foodName;
	}
	
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
		
}


