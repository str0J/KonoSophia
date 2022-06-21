package application;
import java.sql.*;
public class DBConnector {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnector() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia","root","sroot");
			st = con.createStatement();
		}
		catch(Exception ex) {
			System.out.println("Error: "+ex);
		}
		
	}
	public void inserer(String nom, String prenom) throws Exception   {
		
		String sql = "INSERT INTO Users (Nom, Prenom) VALUES (?, ?)";
		 
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1,nom);
		statement.setString(2, prenom);
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("Vous avez été bien inscrit");
		}
	}
	public void modifier(String nom, String prenom,String newnom, String newprenom) throws Exception {
		String sql = "UPDATE users SET Nom="+nom+", Prenom="+prenom+"";
		 
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, newnom);
		statement.setString(2, newprenom);
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
		    System.out.println("Vos informations ont été modifiées.");
	}
	}
	public void supprimer(String nom) throws Exception {
		String sql = "DELETE FROM users WHERE Nom="+nom+"";
		 
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, "bill");
		 
		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
		    System.out.println("C'est supprimé !");
		}
	}

	public void getData() {
		try {
			String query = "select * from users ";
			rs = st.executeQuery(query);
			System.out.println("La liste des étudiants :");
			while(rs.next()) {
				String prenom= rs.getString("Prenom");
				String nom= rs.getString("Nom");
				System.out.println("Prénom :"+prenom);
				System.out.println("Nom :"+nom);
			}
		}
		catch(Exception ex) {
			System.out.println("Error: "+ex);
		}
	}
}
