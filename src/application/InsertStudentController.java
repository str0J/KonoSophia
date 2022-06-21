package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class InsertStudentController {

	 @FXML
	    private TextField nom;

	    @FXML
	    private TextField prenom;

	    @FXML
	    private TextField annee;

	    @FXML
	    private TextField filiere;

	    @FXML
	    private Button button;
	    @FXML
	    private Button BackButton;

	    @FXML
	    void add_etu(ActionEvent event) throws SQLException {
    	String UN=nom.getText();
    	String pass=prenom.getText();
    	String n=annee.getText();
    	String pre=filiere.getText();

    	Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","sroot");
 	   Statement myStmt = myConn.createStatement();
 	  ResultSet myRS = myStmt.executeQuery("SELECT id_filière FROM filière WHERE nom_filière='"+pre+"'");
 	  myRS.next();
 		  int X = myRS.getInt("id_filière");
 	  
 	  String sql = "INSERT INTO etudiant  (`nom_etudiant`, `prenom_etudiant`, `année`,`filière_id_filière`) VALUES ('"+UN+"','"+pass+"', '"+n+"','"+X+"')";
	    
		int rowsInserted = myStmt.executeUpdate(sql);
		if (rowsInserted > 0) {
		    System.out.println("A student was inserted successfully!");
		}
	    
    }
	    @FXML
	    void PressBackButton(ActionEvent event) throws Throwable {
	    	 BackButton.getScene().getWindow().hide();
	         Stage stage = new Stage();
	         Parent root =FXMLLoader.load(getClass().getResource("/FXML/gestion_etud.fxml"));
	         Scene scene = new Scene(root);
	         stage.setScene(scene);;
	         stage.setTitle("NeoSophia");
				stage.getIcons().add(new Image("/image/icon.png"));
				stage.resizableProperty().setValue(Boolean.FALSE);
	         stage.show();
	    }
}