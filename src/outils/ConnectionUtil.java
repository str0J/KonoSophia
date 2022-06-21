package outils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;


public class ConnectionUtil {
	public static String dividenom (String str) {
		String t[]=new String[2];
		int i=0;
		String[] arrOfStr=str.split(" ",2);
		
		for(String a:arrOfStr) {
			t[i]=a;
			i++;
		}
		return t[0];
	}
	public static String divideprenom (String str) {
		String t[]=new String[2];
		int i=0;
		String[] arrOfStr=str.split(" ",2);
		
		for(String a:arrOfStr) {
			t[i]=a;
			i++;
		}
		return t[1];
	}
	public static int RoleTMP=300;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	static String FiliereTMP;
	public static boolean check(String usernametocheck,String passwordtocheck,String roletocheck) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");
	java.sql.Statement st = con.createStatement();
	String hash=Md5_hash.Run_hash(passwordtocheck); 
	ResultSet sr =st.executeQuery("SELECT * FROM utilisateur WHERE usename= '"+usernametocheck+"' and password='"+hash+"' and role='"+roletocheck+"';");
 
		if(sr.next()) {
			if(roletocheck=="Enseignant") {
				RoleTMP=0;
			}
			if(roletocheck=="Coordinateur") {
				RoleTMP=1;
			}
			return true;
 
	}
		else {
			
			return false;
			
		}
}
	
	public static java.sql.Statement connectDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");
		return con.createStatement();
			
}
	public static void ComboFiliere(ComboBox combo_filiere) throws Throwable {
		Class.forName("com.mysql.cj.jdbc.Driver");
    	java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");
    	java.sql.Statement st = con.createStatement();
    	ResultSet sr =st.executeQuery("SELECT * FROM filière");
  
  	  ObservableList<String> Filiere= FXCollections.observableArrayList();
    	while(sr.next()) {
    		Filiere.add(sr.getString("nom_filière"));
    	}
    	
    	combo_filiere.setValue("Filière");
    	combo_filiere.setItems(Filiere);

	}

}
