import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
	public static Scanner sc = new Scanner(System.in);
	
	public App() {		
	}
	
	public static void main(String[] args) throws SQLException, IOException{
		Connection c = Connexion.getConnexion();
		
		//Test si les tables existent, si non, on les crée.
		CreateTables cT = new CreateTables(c);
		
		//Affichage du menu + recup du choix (boucle)
		getChoice(c);
		
		//On ferme la connection BDD
		Connexion.closeConnection(c);
	}

	public static String printMenu()
	{
		String menuChoice = "";
		
		// Display menu in console
	    System.out.println();	
	    System.out.println("----------------------------------------------");
	    System.out.println("- INGREDIENTS");
	    System.out.println("----------------------------------------------");
	    System.out.println("1) Ajouter un aliment en base de données");
	    System.out.println("2) Supprimer un aliment de la base de données");
	    System.out.println("3) Afficher toute la liste");
	    System.out.println("----------------------------------------------");
	    System.out.println("- CATEGORIES");
	    System.out.println("----------------------------------------------");
	    System.out.println("4) Ajouter une catégorie");
	    System.out.println("5) Supprimer une catégorie");
	    System.out.println("6) Afficher toute les catégories");
	    System.out.println("X) Quitter le programme");
	    System.out.println("----------------------------------------------");

	    // Récupération de l'entrée clavier qui sera utilisée pour définir l'action à faire.
	    menuChoice = sc.nextLine();

	    return menuChoice;
	}
	
	public static void getChoice(Connection c) throws SQLException {
		String menuChoice = "";
		
		Food food = new Food(c);
		Cats cats = new Cats(c);
		
		System.out.println("Bonjour et bienvenue dans l'application de BDD alimentaire");

	    do {
	    	//On récupere le choix de l'utilisateur suite a l'affichage du menu
	    	
			menuChoice = printMenu();
			
	        switch (menuChoice) {
	        case "1":
	          food.addFood();
	          break;

	        case "2":
	          food.deleteFood();
	          break;

	        case "3":
	          food.printFood();
	          break;

	        case "4":
	          cats.addCat();
	          break;

	        case "5":
	          cats.deleteCat();
	          break;

	        case "6":
	          cats.printCats();
	          break;
	          
	        case "X":
	        case "x":
	          System.out.println("Giscard a dit : Au revoir ....");
	          break;

	        default:
	          System.out.println("Commande inconnue !");
	          break;
	        }

	      // while menu choice is not "X" (not case sensitive) we keep printing the menu
	    } while (!menuChoice.equalsIgnoreCase("X"));

	    sc.close();
	  }
}
