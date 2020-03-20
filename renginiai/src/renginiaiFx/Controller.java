package renginiaiFx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class Controller {
    @FXML
    javafx.scene.control.Button goToRegisterButton;
    @FXML
    javafx.scene.control.Button goToLoginButton;


    @FXML
    public void goToRegister(javafx.event.ActionEvent event){
        try {
            Parent register = FXMLLoader.load(getClass().getResource("View/register.fxml"));
            Scene scene2 = new Scene(register);
            Stage registerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            registerStage.setScene(scene2);
            registerStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void goToLogin(javafx.event.ActionEvent event){
        try {
            Parent login = FXMLLoader.load(getClass().getResource("View/login.fxml"));
            Scene scene2 = new Scene(login);
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.setScene(scene2);
            loginStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
