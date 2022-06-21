package application;
/*Classe d'affichage des infos de l'étudiant*/
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import application.DBConnector;

public class gerer_etudController {
	private Connection con;
	private Statement st;
	private ResultSet rs;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private Label label_name;

    @FXML
    private Button btn_tp;

    @FXML
    private TextField txt_TP;

    @FXML
    private Button btn_TD;

    @FXML
    private TextField txt_TD;

    @FXML
    private Button btn_exam;

    @FXML
    private TextField txt_exam;

    @FXML
    private Label label_moyenne;

    @FXML
    private Label label_matiere;
    @FXML
    private ComboBox combo_matiere;
    @FXML
    private ImageView etud_image;

   
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

    @FXML
   
    

   public void initialize() throws  Exception {
    	
   	label_name.setText(outils.Backup.Etudiant);
   	etud_image.setImage(new Image("/photos/"+outils.Backup.Etudiant+".png")); 
   	
   	}
   	}
    	
    

