package application;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import outils.ConnectionUtil;

public class gestion_etudController {
	String FiliereTMP;
	public static String FiliereTMP1;
	public static String val;
	public static String sem;
	public static String mat;

	  @FXML
	    private Button btn_selectetud;

	   

	    @FXML
	    private ComboBox combo_etud;


	    @FXML
	    private Button btn_showetud;

	    @FXML
	    private Button btn_selectfil;

	    @FXML
	    private Button btn_showfil;

	    @FXML
	    private ComboBox combo_filiere1;

	    @FXML
	    private ComboBox combo_matiere;

	    @FXML
	    private Button BackButton;
	    @FXML
	    private ComboBox combo_sem;

	    @FXML
	    private ComboBox combo_module;
	    @FXML

	    private TextField TPField;

	    @FXML
	    private TextField ExamField;

	    


	    
    @FXML
    void FiliereEvent1(ActionEvent event) throws Throwable {
    	ObservableList<String> Semestre= FXCollections.observableArrayList();
    	Class.forName("com.mysql.cj.jdbc.Driver");
      	java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");
      	java.sql.Statement st = con.createStatement();
    	   FiliereTMP1 = (String) combo_filiere1.getValue();/*Par etudiant*/
    	   ResultSet rs =st.executeQuery("SELECT id_filière FROM filière WHERE nom_filière='"+FiliereTMP1+"'");
    	  	  rs.next();
  	  System.out.println("test");
  		
      	/*Semestre*/
  	 
  	  int id_filiere = rs.getInt("id_filière");
  	  ResultSet rss =st.executeQuery("SELECT * FROM semestre WHERE filière_id_filière='"+id_filiere+"'");
  	  
  	  while(rss.next()) {
  		  Semestre.add(rss.getString("nom_semestre"));
  		  
  	  }
  	  
    		combo_sem.setValue("Semestres");
    		combo_sem.setItems(Semestre); 
    		/*Etudiant*/
      	  ResultSet rssx =st.executeQuery("SELECT * FROM etudiant WHERE filière_id_filière='"+id_filiere+"'");
      	  ObservableList<String> Etudiant= FXCollections.observableArrayList();
      	  while(rssx.next()) {
      		  Etudiant.add(rssx.getString("nom_etudiant")+" "+rssx.getString("prenom_etudiant"));
      		  
      	  }
      	  
        		combo_etud.setValue("Etudiants");
        		combo_etud.setItems(Etudiant); 
  }
    @FXML
    void SemestreEvent(ActionEvent event) throws Throwable {
    	ObservableList<String> mod= FXCollections.observableArrayList();
    	Class.forName("com.mysql.cj.jdbc.Driver");
      	java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");
      	java.sql.Statement st = con.createStatement();
        ResultSet myRs9 = st.executeQuery("SELECT id_filière from filière WHERE nom_filière = '"+FiliereTMP1+"'");
        myRs9.next();
        int x = myRs9.getInt("id_filière");
         sem = (String) combo_sem.getValue();
        ResultSet myRs8 = st.executeQuery("SELECT semestre_id FROM semestre WHERE nom_semestre ='"+sem+"' AND filière_id_filière = '"+x+"'");
        myRs8.next();
        int y = myRs8.getInt("semestre_id");
        
        ResultSet myRs2 = st.executeQuery("select Module_name from module WHERE semestre_semestre_id = '"+y+"' ");
        while(myRs2.next()) {    
        mod.add(myRs2.getString("Module_name"));
        combo_module.setItems(mod); 
  }}
    @FXML
    void ModuleEvent(ActionEvent event) throws Throwable {
    	ObservableList<String> Matiere= FXCollections.observableArrayList();
    	Class.forName("com.mysql.cj.jdbc.Driver");
      	java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");
      	java.sql.Statement st = con.createStatement();
         val =  (String) combo_module.getValue();
        ResultSet myRs11 = st.executeQuery("SELECT matière_name from matière WHERE Module_Module_name = '"+val+"'");
        System.out.print(ConnectionUtil.RoleTMP);
        
        while(myRs11.next()) {    
        Matiere.add(myRs11.getString("matière_name"));
        
  } 	combo_matiere.setItems(Matiere); 
        }
    @FXML
    void showetudEvent(ActionEvent event) throws Throwable {
    	 outils.Backup.Etudiant = (String) combo_etud.getValue();
    	 btn_showetud.getScene().getWindow().hide();
         Stage stage = new Stage();
         Parent root =FXMLLoader.load(getClass().getResource("/FXML/gerer_etud.fxml"));
         Scene scene = new Scene(root);
         stage.setScene(scene);;  
         stage.show();
    }
    @FXML
    void initialize() throws Throwable, SQLException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");
    	java.sql.Statement st = con.createStatement();
    	ResultSet sr =st.executeQuery("SELECT * FROM filière");
  
  	  ObservableList<String> Filiere= FXCollections.observableArrayList();
    	while(sr.next()) {
    		Filiere.add(sr.getString("nom_filière"));
    	}
    	
    	
    	combo_filiere1.setValue("Filière");
    	combo_filiere1.setItems(Filiere);

    }
    
    @FXML
    void TPEvent(ActionEvent event) throws IOException, Throwable  {
    	Class.forName("com.mysql.cj.jdbc.Driver");
      	java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");
      	java.sql.Statement st = con.createStatement();
      	String Etudiant=(String) combo_etud.getValue();
      String nom=	outils.ConnectionUtil.dividenom(Etudiant);
      	String prenom= outils.ConnectionUtil.divideprenom(Etudiant);
      	String Matiere=combo_matiere.getPromptText();
        ResultSet myRs11 = st.executeQuery(" Update note SET note.note_tp=la_note where Etudiant_id_etud= (Select id_etud from etudiant where nom_etudiant='"+nom+"' and prenom_etudiant='"+prenom+"') and Matière_Nom='"+Matiere+"'");
   	 

    }
    @FXML
    void ExamEvent(ActionEvent event) throws IOException, Throwable  {
    	Class.forName("com.mysql.cj.jdbc.Driver");
      	java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");
      	java.sql.Statement st = con.createStatement();
      String nom=	outils.ConnectionUtil.dividenom(outils.Backup.Etudiant);
      	String prenom=outils.ConnectionUtil.divideprenom(outils.Backup.Etudiant);
        ResultSet myRs11 = st.executeQuery(" Update note SET note.note_exam=la_note where Etudiant_id_etud= (Select id_etud from etudiant where nom_etudiant='"+nom+"' and prenom_etudiant='"+prenom+"') and Matière_Nom=la_matiere");

    }
 void TPEvent1(ActionEvent event) throws IOException, Throwable  {
	 Class.forName("com.mysql.cj.jdbc.Driver");
   	java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");
   	java.sql.Statement st = con.createStatement();
   String nom=	outils.ConnectionUtil.dividenom(outils.Backup.Etudiant);
   	String prenom=outils.ConnectionUtil.divideprenom(outils.Backup.Etudiant);
     ResultSet myRs11 = st.executeQuery(" Update note SET note.note_exam=la_note where Etudiant_id_etud= (Select id_etud from etudiant where nom_etudiant='"+nom+"' and prenom_etudiant='"+prenom+"') and Matière_Nom=la_matiere");

    }
    @FXML
    void ExamEvent1(ActionEvent event) throws IOException, Throwable  {
    	Class.forName("com.mysql.cj.jdbc.Driver");
      	java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/neosophia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sroot");
      	java.sql.Statement st = con.createStatement();
      String nom=	outils.ConnectionUtil.dividenom(outils.Backup.Etudiant);
      	String prenom=outils.ConnectionUtil.divideprenom(outils.Backup.Etudiant);
        ResultSet myRs11 = st.executeQuery(" Update note SET note.note_exam=la_note where Etudiant_id_etud= (Select id_etud from etudiant where nom_etudiant='"+nom+"' and prenom_etudiant='"+prenom+"') and Matière_Nom=la_matiere");

    }
    @FXML
    void showfilEvent(ActionEvent event) throws Throwable {
    	mat =  (String) combo_matiere.getValue();
    	btn_showfil.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root =FXMLLoader.load(getClass().getResource("/FXML/liste note.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);; 
        stage.setTitle("NeoSophia");
		stage.getIcons().add(new Image("/image/icon.png"));
		stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }
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
    
}
