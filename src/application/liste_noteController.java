package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class liste_noteController {

	   @FXML
	    private TableView<NoteStudent> table;

	    @FXML
	    private TableColumn<NoteStudent, Integer> id;

	    @FXML
	    private TableColumn<NoteStudent, String> nom;

	    @FXML
	    private TableColumn<NoteStudent, String> prenom;

	    @FXML
	    private TableColumn<NoteStudent, Integer> tp;

	    @FXML
	    private TableColumn<NoteStudent, Integer> examen;

	    @FXML
	    private TableColumn<NoteStudent, Integer> generale;
	    
	    @FXML
	    private TableColumn<NoteStudent, String> matiere;
	    @FXML
	    private Button ReturnButton;

	    @FXML
	    void PressReturn(ActionEvent event) throws Exception {
	    	ReturnButton.getScene().getWindow().hide();
	         Stage stage = new Stage();
	         Parent root =FXMLLoader.load(getClass().getResource("/FXML/gestion_etud.fxml"));
	         Scene scene = new Scene(root);
	         stage.setScene(scene);; 
	         stage.setTitle("NeoSophia");
				stage.getIcons().add(new Image("/image/icon.png"));
				stage.resizableProperty().setValue(Boolean.FALSE);
	         stage.show();

	    }

   
    public static ObservableList<NoteStudent> getData() throws SQLException{
    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","sroot");
    ObservableList<NoteStudent> ListeNote= FXCollections.observableArrayList();
    String FiliereTMP1 =  gestion_etudController.FiliereTMP1;
    String sem = (String) gestion_etudController.sem;
    String mod =  (String) gestion_etudController.val;
    String mat=(String) gestion_etudController.mat;
  	java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");

    java.sql.Statement st = con.createStatement();

    
    
    	  PreparedStatement myStmt = myConn.prepareStatement("SELECT * from etudiant, note where etudiant.id_etud=note.Etudiant_id_etud and note.Matière_Nom='"+mat+"'");
    	  
    	  ResultSet myRs = myStmt.executeQuery();
    	  while(myRs.next()) {
     		  NoteStudent tmp = new NoteStudent(myRs.getInt("Etudiant_id_etud"),myRs.getString("nom_etudiant"), myRs.getString("prenom_etudiant"), myRs.getInt("note_tp"), myRs.getInt("note_exam"), myRs.getInt("note_generale"),myRs.getString("Matière_Nom"));
     		      ListeNote.add(tmp);
    	  }
    	  
          
      
	return ListeNote;
    }
    ObservableList<NoteStudent> List;
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @FXML
	public void initialize() throws SQLException {
    	id.setCellValueFactory(new PropertyValueFactory<NoteStudent, Integer>("ID"));
    	nom.setCellValueFactory(new PropertyValueFactory<NoteStudent, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<NoteStudent, String>("prenom"));
		tp.setCellValueFactory(new PropertyValueFactory<NoteStudent, Integer>("TP"));
		examen.setCellValueFactory(new PropertyValueFactory<NoteStudent, Integer>("examen"));
        generale.setCellValueFactory(new PropertyValueFactory<NoteStudent, Integer>("Generale"));
        matiere.setCellValueFactory(new PropertyValueFactory<NoteStudent, String>("matiere"));

	    List= getData();
		table.setItems(List);
	}
}