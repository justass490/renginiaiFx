package renginiaiFx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import renginiaiFx.Model.Renginiai;
import renginiaiFx.Utilities.loginValid;
import renginiaiFx.Utilities.registerValid;

import java.sql.*;
import java.util.ArrayList;


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
    javafx.scene.control.Label loggedInAs;
    @FXML
    javafx.scene.control.Label status;

    @FXML
    javafx.scene.control.TextField pavadinimas;
    @FXML
    javafx.scene.control.TextField vardasPavarde;
    @FXML
    javafx.scene.control.TextField adresas;
    @FXML
    javafx.scene.control.ComboBox zmoniuSkaicius;
    @FXML
    javafx.scene.control.Label noticeDashboard;
    @FXML
    javafx.scene.control.CheckBox checkbox1;
    @FXML
    javafx.scene.control.CheckBox checkbox2;
    @FXML
    javafx.scene.control.CheckBox checkbox3;
    @FXML
    javafx.scene.control.CheckBox checkbox4;
    @FXML
    javafx.scene.control.CheckBox checkbox5;
    @FXML
    javafx.scene.control.CheckBox checkbox6;

    private String usernameTable;
    private String passwordTable;
    private String emailTable;
    private String isAdminTable;

    @FXML
    javafx.scene.control.Button goToRegisterButton;
    @FXML
    javafx.scene.control.Button goToLoginButton;
    @FXML
    javafx.scene.control.Button registerButton;

    @FXML
    javafx.scene.control.TextField searchId;
    @FXML
    TableView<Renginiai> tableView;
    @FXML
    TableColumn<Renginiai, Integer> tableId;
    @FXML
    TableColumn<Renginiai, String> tablePavadinimas;
    @FXML
    TableColumn<Renginiai, String> tableVardas;
    @FXML
    TableColumn<Renginiai, String> tableZmoniuSkaicius;
    @FXML
    TableColumn<Renginiai, String> tableAdresas;
    @FXML
    TableColumn<Renginiai, String> tablePriedai;

    ObservableList<Renginiai> list;

    @FXML
    public void closeWindow() {
        System.exit(0);
    }

    @FXML
    public void goToRegister(ActionEvent event) {
        try {
            Parent register = FXMLLoader.load(getClass().getResource("View/register.fxml"));
            Scene scene2 = new Scene(register);
            Stage registerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            registerStage.setScene(scene2);
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToLogin(ActionEvent event) {
        try {
            Parent login = FXMLLoader.load(getClass().getResource("View/login.fxml"));
            Scene scene2 = new Scene(login);
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.setScene(scene2);
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToDashboard(ActionEvent event) {
        try {
            Parent dashboard = FXMLLoader.load(getClass().getResource("View/dashboard.fxml"));
            Scene scene3 = new Scene(dashboard);
            Stage dashboardStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            dashboardStage.setScene(scene3);
            dashboardStage.show();
        } catch (Exception e) {
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
    public void loginValid(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/renginiaiUserInfo?serverTimezone=UTC", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from `credentials` where username = '" + usernameOrEmailLogin.getText() + "' and password = '" + passwordLogin.getText() + "' or email = '" + usernameOrEmailLogin.getText() + "' and password = '" + passwordLogin.getText() + "'");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                usernameTable = result.getString("username");
                passwordTable = result.getString("password");
                emailTable = result.getString("email");
                isAdminTable = result.getString("is_admin");
            }
            if (loginValid.isValidUsername(usernameOrEmailLogin.getText())) {
                if (usernameOrEmailLogin.getText().equals(usernameTable)) {
                    if (passwordLogin.getText().equals(passwordTable)) {
                        connection.close();
                        goToDashboard(event);
                    } else {
                        loginNotice.setTextFill(Color.RED);
                        loginNotice.setText("Incorrect password");
                        connection.close();
                    }
                } else {
                    loginNotice.setTextFill(Color.RED);
                    loginNotice.setText("User does not exist");
                    connection.close();
                }
            } else if (loginValid.isValidEmail(usernameOrEmailLogin.getText())) {
                if (usernameOrEmailLogin.getText().equals(emailTable)) {
                    if (passwordLogin.getText().equals(passwordTable)) {
                        connection.close();
                        goToDashboard(event);
                    } else {
                        loginNotice.setTextFill(Color.RED);
                        loginNotice.setText("Incorrect password");
                        connection.close();
                    }
                } else {
                    loginNotice.setTextFill(Color.RED);
                    loginNotice.setText("User does not exist");
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createTable() {
        ArrayList<String> list = new ArrayList<>();
        if (checkbox1.isSelected()) {
            list.add(checkbox1.getText());
        }
        if (checkbox2.isSelected()) {
            list.add(checkbox2.getText());
        }
        if (checkbox3.isSelected()) {
            list.add(checkbox3.getText());
        }
        if (checkbox4.isSelected()) {
            list.add(checkbox4.getText());
        }
        if (checkbox5.isSelected()) {
            list.add(checkbox5.getText());
        }
        if (checkbox6.isSelected()) {
            list.add(checkbox6.getText());
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/renginiaiUserInfo?serverTimezone=UTC", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `renginiai_table`(pavadinimas, vardas_pavarde, adresas, zmoniu_skaicius, priedai)" + "VALUES (?,?,?,?,?)");
            if ((!pavadinimas.getText().isEmpty()) && (!vardasPavarde.getText().isEmpty()) && (!adresas.getText().isEmpty()) && (zmoniuSkaicius != null)) {
                preparedStatement.setString(1, pavadinimas.getText());
                preparedStatement.setString(2, vardasPavarde.getText());
                preparedStatement.setString(3, adresas.getText());
                preparedStatement.setString(4, (String) zmoniuSkaicius.getValue());
                preparedStatement.setString(5, list.toString());
                preparedStatement.executeUpdate();
                noticeDashboard.setTextFill(Color.GREEN);
                noticeDashboard.setText("Entry added!");
                connection.close();
            } else {
                noticeDashboard.setTextFill(Color.RED);
                noticeDashboard.setText("Please fill out all fields");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    String idSearch;
    public void tableFill(){
        if (searchId.getText().isEmpty()){
            idSearch = "SELECT * FROM renginiai_table";
        }else {
            idSearch = "SELECT * FROM renginiai_table WHERE id LIKE " + searchId.getText();
        }
        searchId.setText("");
        list = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/renginiaiUserInfo?serverTimezone=UTC", "root", "");
            ResultSet set = connection.createStatement().executeQuery(idSearch);
            while (set.next()){
                Renginiai renginys = new Renginiai();
                renginys.setId(set.getInt("id"));
                renginys.setPavadinimas(set.getString("pavadinimas"));
                renginys.setVardas(set.getString("vardas_pavarde"));
                renginys.setZmoniuSkaicius(set.getString("zmoniu_skaicius"));
                renginys.setAdresas(set.getString("adresas"));
                renginys.setPriedai(set.getString("priedai"));

                list.add(renginys);
            }
            tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tablePavadinimas.setCellValueFactory(new PropertyValueFactory<>("pavadinimas"));
            tableVardas.setCellValueFactory(new PropertyValueFactory<>("vardas"));
            tableZmoniuSkaicius.setCellValueFactory(new PropertyValueFactory<>("zmoniuSkaicius"));
            tableAdresas.setCellValueFactory(new PropertyValueFactory<>("adresas"));
            tablePriedai.setCellValueFactory(new PropertyValueFactory<>("priedai"));

            tableView.setItems(list);
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void tableDrop(){
        if (searchId.getText().isEmpty()){
            idSearch = "";
        } else {
            idSearch = "DELETE FROM `renginiai_table` WHERE id LIKE " + searchId.getText();
        }
        searchId.setText("");
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/renginiaiUserInfo?serverTimezone=UTC", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(idSearch);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
