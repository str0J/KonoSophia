package application;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import outils.ConnectionUtil;

public class InterfaceController {
	ObservableList<String> Role= FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField input_user;

    @FXML
    private PasswordField input_pass;

    @FXML
    private Button btn_login;

    @FXML
    private ComboBox input_combo;

    @FXML
    private Label label;

    @FXML
    void Select(ActionEvent event) {
    	System.out.println("Le choix:"+ input_combo.getValue());
    	System.out.println(ConnectionUtil.RoleTMP);
    }
    @FXML
    void login(ActionEvent event) throws SQLException, Throwable {
    	 Object acc = input_combo.getValue();
    	 String role=acc.toString();
    	 String user = input_user.getText();
    	 String pass = input_pass.getText();
    	 boolean status = ConnectionUtil.check(user,pass,role);
    	 if(status==true) {
    		 btn_login.getScene().getWindow().hide();
             Stage stage = new Stage();
             Parent root =FXMLLoader.load(getClass().getResource("/FXML/gestion.fxml"));
             Scene scene = new Scene(root);
             stage.setScene(scene);; 
             stage.setTitle("NeoSophia");
 			stage.getIcons().add(new Image("/image/icon.png"));
 			stage.resizableProperty().setValue(Boolean.FALSE);
             stage.show();
    	 }
    	 else {
    		 System.out.println("notconnected");
             Alert d = new Alert(AlertType.ERROR);
             d.setTitle("Erreur d'authentification");
             d.setContentText("Erreur d'authentification! Veuillez réessayer.");
             d.show();
    	 }
    }

    @FXML
    void initialize() {
    	Role.add("Enseignant");
    	Role.add("Coordinateur");
    	input_combo.setValue("- Rôle -");
    	input_combo.setItems(Role);
    }
}
