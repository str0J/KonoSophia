package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class gestionController {

    @FXML
    private Button btn_cours;

    @FXML
    
    private Button btn_logout;
    
    @FXML
    private Button btn_etud;

    
    @FXML
    void PressLogout(ActionEvent event) throws Throwable {
    	 btn_logout.getScene().getWindow().hide();
         Stage stage = new Stage();
         Parent root =FXMLLoader.load(getClass().getResource("/application/Interface.fxml"));
         Scene scene = new Scene(root);
         stage.setScene(scene);;  
         stage.setTitle("NeoSophia");
			stage.getIcons().add(new Image("/image/icon.png"));
			stage.resizableProperty().setValue(Boolean.FALSE);
         stage.show();
    }

    @FXML
    void press_etud(ActionEvent event) throws IOException {
    	 btn_etud.getScene().getWindow().hide();
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
    void press_cours(ActionEvent event) throws IOException {
   	 btn_cours.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root =FXMLLoader.load(getClass().getResource("/FXML/gestion_cours.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);;  
        stage.setTitle("NeoSophia");
		stage.getIcons().add(new Image("/image/icon.png"));
		stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
   }

}
