/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import controller.*;
import aleefah.Aleefah;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modal.Animal;

/**
 *
 * @author wedfa
 */
public class DescreptionController implements Initializable{
    public static Animal a;
    
     @FXML
    private  Label contact;

    @FXML
    private Label descreption;

    @FXML
    private Label gender;

    @FXML
    private ImageView imageUrl;

    @FXML
    private Label type;
    @FXML
    private Label age;

    @FXML
    private Label animal;

    @FXML
    private ComboBox comboBox;
    
    @FXML
     private Stage stage;
    
     @FXML
    private MenuButton menu;
     
     @FXML
    private MenuItem lio;
     
     @FXML
    MenuBar myMenuBar;
     
     @FXML
    private TextField searchBox;
     
       
         @FXML
    void loginstate(ActionEvent event) {
       if("".equals(Aleefah.userName)){
        try { 
           FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/screens/LOGIN.fxml"));
            Parent ReportManager = loader.load();
            Scene ReportManagerScene = new Scene(ReportManager);
            Stage window = (Stage)myMenuBar.getScene().getWindow();
            window.setScene(ReportManagerScene);
            window.show();
             
            } catch (IOException ex) {
                Logger.getLogger(DescreptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
       }else{
           Aleefah.userId=-1;
           Aleefah.userName="";
        try { 
              FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/screens/WELCOME.fxml"));
            Parent ReportManager = loader.load();
            Scene ReportManagerScene = new Scene(ReportManager);
            Stage window = (Stage)myMenuBar.getScene().getWindow();
            window.setScene(ReportManagerScene);
            window.show();
            } catch (IOException ex) {
                Logger.getLogger(DescreptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }
      @FXML
    void toProfile(ActionEvent event) {
    
         if("".equals(Aleefah.userName)){
          Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Didn't Login");
        alert.setHeaderText("LogIn First please");
        alert.showAndWait(); 
         }else{
              try { 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/screens/ProfilePage.fxml"));
            Parent ReportManager = loader.load();
            Scene ReportManagerScene = new Scene(ReportManager);
            Stage window = (Stage)myMenuBar.getScene().getWindow();
            window.setScene(ReportManagerScene);
            window.show();
            } catch (IOException ex) {
                Logger.getLogger(DescreptionController.class.getName()).log(Level.SEVERE, null, ex);
            }}}
    /*-------------------------BACK TO MAIN SCREEN ACTION ----------------------*/
      @FXML
    void backButton(ActionEvent event) {
try { 
        Parent root  = FXMLLoader.load(getClass().getResource("/screens/MainScreen.fxml"));
        Scene scene=new Scene(root); 
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        stage.setScene(scene);
     } catch (IOException ex) {
        Logger.getLogger(DescreptionController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    /*-------------------------MENUBUTTON ACTION ----------------------*/

@Override
    public void initialize(URL location, ResourceBundle resources) {
        menu.setText("".equals(Aleefah.userName)?"no user":Aleefah.userName);
        lio.setText("".equals(Aleefah.userName)?"LogIn":"LogOut");

        contact.setText(a.getContact());
        type.setText(a.getType());
        age.setText(a.getAge());
        gender.setText(a.getGender());
        descreption.setText(a.getDescription());
        contact.setText(a.getContact());
        animal.setText(a.getName());
        imageUrl.setImage(new Image(a.getImageUrl()));

    }
    
    
}
