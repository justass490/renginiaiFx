package renginiaiFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import renginiaiFx.Utilities.loginValid;
import renginiaiFx.Utilities.registerValid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Controller {
    @FXML
    javafx.scene.control.TextField usernameRegister;
    @FXML
    javafx.scene.control.PasswordField passwordRegister;
    @FXML
    javafx.scene.control.PasswordField passwordConfirmRegister;
    @FXML
    javafx.scene.control.TextField emailRegister;
    @FXML
    javafx.scene.control.CheckBox adminCheckbox;
    String isAdmin;
    @FXML
    javafx.scene.control.Label registerNotice;

    @FXML
    javafx.scene.control.TextField usernameOrEmailLogin;
    @FXML
    javafx.scene.control.TextField passwordLogin;
    @FXML
    javafx.scene.control.Label loginNotice;

    @FXML
    javafx.scene.control.Button goToRegisterButton;
    @FXML
    javafx.scene.control.Button goToLoginButton;
    @FXML
    javafx.scene.control.Button registerButton;

    @FXML
    public void closeWindow(){
        System.exit(0);
    }

    @FXML
    public void goToRegister(ActionEvent event){
        try {
            Parent register = FXMLLoader.load(getClass().getResource("View/register.fxml"));
            Scene scene2 = new Scene(register);
            Stage registerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            registerStage.setScene(scene2);
            registerStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void goToLogin(ActionEvent event){
        try {
            Parent login = FXMLLoader.load(getClass().getResource("View/login.fxml"));
            Scene scene2 = new Scene(login);
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.setScene(scene2);
            loginStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void goToDashboard(ActionEvent event){
        try {
            Parent dashboard = FXMLLoader.load(getClass().getResource("View/dashboard.fxml"));
            Scene scene3 = new Scene(dashboard);
            Stage dashboardStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            dashboardStage.setScene(scene3);
            dashboardStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void registerValid(){
        if (registerValid.isValidUsername(usernameRegister.getText()) && registerValid.isValidEmail(emailRegister.getText()) && registerValid.isValidPassword(passwordRegister.getText()) && passwordRegister.getText().equals(passwordConfirmRegister.getText())){
            if (adminCheckbox.isSelected()){
                isAdmin = "true";
            }else{
                isAdmin = "false";
            }
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/renginiaiUserInfo", "root", "");
                PreparedStatement preparedStatement = connection.prepareStatement("insert into `credentials`(username, password, email, is_admin)" + "values (?,?,?,?)");
                preparedStatement.setString(1,usernameRegister.getText());
                preparedStatement.setString(2,passwordRegister.getText());
                preparedStatement.setString(3,emailRegister.getText());
                preparedStatement.setString(4, isAdmin);
                preparedStatement.executeUpdate();
                registerNotice.setTextFill(Color.GREEN);
                registerNotice.setText("Registration successful!");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else {
            registerNotice.setTextFill(Color.RED);
            registerNotice.setText("Something went wrong.");
        }
    }
    @FXML
    public void loginValid(){
        if (loginValid.isValidUsername(usernameOrEmailLogin.getText()) && loginValid.isValidPassword(passwordLogin.getText())){

        }else if (loginValid.isValidEmail(usernameOrEmailLogin.getText()) && loginValid.isValidPassword(passwordLogin.getText())){

        }else {
            loginNotice.setTextFill(Color.RED);
            loginNotice.setText("Something went wrong.");
        }
    }
}
