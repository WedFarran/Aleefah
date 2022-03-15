/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import aleefah.Aleefah;
import aleefah.HibernateUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modal.Animal;
import modal.Globalvariables;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddController implements Initializable{
 @FXML
    private Button SaveButton;
    @FXML
    private TextField Name;
    @FXML
    private TextField Country;
    @FXML
    private TextField Post;
    @FXML
    private TextField Descriotion;
    @FXML
    private TextField Gender;
    @FXML
    private TextField Age;
    @FXML
    private TextField Type;
    @FXML
    private TextField Animal_Type;
    @FXML
    private TextField Contact;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
     @FXML
    private MenuBar myMenuBar;
    
    @FXML
    private MenuItem lio;

    @FXML
    private MenuButton menu;
    @FXML
    void loginstate(ActionEvent event) {
   
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menu.setText("".equals(Aleefah.userName)?"no user":Aleefah.userName);
        lio.setText("LogOut");
        
        
    }
   
   @FXML
    private void ToProfile(ActionEvent event) throws Exception {
        

            String animal_type = Animal_Type.getText();
            String name = Name.getText();
            String type = Type.getText();
            String age = Age.getText();
            String gender = Gender.getText();
            String descriotion = Descriotion.getText();
            String Image_url = Post.getText();
            String country = Country.getText();
            String contact = Contact.getText();

            Animal new_animal = new Animal();
            new_animal.setType(animal_type);
            new_animal.setName(name);
            new_animal.setType(type);
            new_animal.setAge(age);
            new_animal.setGender(gender);
            new_animal.setDescription(descriotion);
            new_animal.setImageUrl(Image_url);
            new_animal.setCity(country);
            new_animal.setContact(contact);
            new_animal.getAnimalID();
            new_animal.setUserId(Globalvariables.user_id);
            Globalvariables.animals.add(new_animal);

            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session2.beginTransaction();
            session2.save(new_animal);
            tx.commit();
            session2.close();
         
      
            root = FXMLLoader.load(getClass().getResource("ProfilePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 
    
    
    
    
    
}
