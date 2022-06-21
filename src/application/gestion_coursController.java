package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import outils.ConnectionUtil;

public class gestion_coursController {

    @FXML
    private Button UpdateButton;

    @FXML
    private TextField txt_prcTP;

    @FXML
    private TextField txt_prcExam;

    @FXML
    private Button AddEnseignant;

    @FXML
    private TextField UserArea;

    @FXML
    private TextField PassArea;

    @FXML
    private TextField NomArea;

    @FXML
    private TextField PrenomArea;

    @FXML
    private Button UpdateInfo;
    @FXML
    private Button BackButton;
    
    @FXML
    private ComboBox Combo_Mat;
    
   
    	
   	 
    @FXML
    void PressBackButton(ActionEvent event) throws Throwable {
   	 BackButton.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root =FXMLLoader.load(getClass().getResource("/FXML/gestion.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);; 
        stage.setTitle("NeoSophia");
		stage.getIcons().add(new Image("/image/icon.png"));
		stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
   }
    public void initialize() throws  Exception {
 	   ObservableList<String> mat=FXCollections.observableArrayList();
 	   Class.forName("com.mysql.cj.jdbc.Driver");
    	java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");
    	Statement myStmt = con.createStatement();
    	ResultSet myRS = myStmt.executeQuery("SELECT * FROM matière");
    	while (myRS.next()) {
        mat.add(myRS.getString("matière_name"));
        Combo_Mat.setItems(mat);}}


    @FXML
    
    
    void AddEnseignantEvent(ActionEvent event) throws Throwable {
    	if(outils.ConnectionUtil.RoleTMP==1) {
    		String UN=UserArea.getText();
            String pass=PassArea.getText();
            String n=NomArea.getText();
            String pre=PrenomArea.getText();
     
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","sroot");
           Statement myStmt = myConn.createStatement();
          String sql = "INSERT INTO utilisateur  (`usename`, `password`, `nom`,`prenom`,`role`) VALUES ('"+UN+"','"+pass+"', '"+n+"','"+pre+"','enseignant')";
             
            int rowsInserted = myStmt.executeUpdate(sql);
            if (rowsInserted > 0) {
                System.out.println("A user was added successfully!");
            }
           
    	}
    	 else {
         	System.out.println("Vous n'avez pas l'accès");
             Alert d = new Alert(AlertType.ERROR);
             d.setTitle("ACCESS DENIED !!!");
             d.setContentText("Vous n'avez pas l'accès à cette fonctionnalité. Contactez un admin/coordinateur");
             d.show();
         }
    }
    @FXML
    
    void InsertStudentEvent(ActionEvent event) throws Throwable {
    	 BackButton.getScene().getWindow().hide();
         Stage stage = new Stage();
         Parent root =FXMLLoader.load(getClass().getResource("/FXML/InsertStudent.fxml"));
         Scene scene = new Scene(root);
         stage.setScene(scene);;
         stage.setTitle("NeoSophia");
			stage.getIcons().add(new Image("/image/icon.png"));
			stage.resizableProperty().setValue(Boolean.FALSE);
         stage.show();
    }
    @FXML
    void PressUpdate(ActionEvent event) throws SQLException, Throwable {
    	
    String cfTp=txt_prcTP.getText();
    String cfExam=txt_prcExam.getText();
    String mats =  (String) Combo_Mat.getValue();
    Class.forName("com.mysql.cj.jdbc.Driver");
	java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");
	java.sql.Statement st = con.createStatement();
	if(cfTp!=null) {
	st.executeUpdate("UPDATE neosophia.matière SET Pourcentage_tp = '"+Float.parseFloat(cfTp)+"' where matière_name='"+mats+"'");
	}
	if(cfExam!=null) {
		st.executeUpdate("UPDATE neosophia.matière SET Pourcentage_examen = '"+Float.parseFloat(cfExam)+"'where matière_name='"+mats+"'");
	}
}
    }
