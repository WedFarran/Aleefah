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
import org.hibernate.Session;

/**
 *
 * @author wedfa
 */
public class ModifyController implements Initializable {
    public static int mUserId;
      @FXML
    private TextField Age;

    @FXML
    private TextField Animal_Type;

    @FXML
    private TextField Contact;

    @FXML
    private TextField Country;

    @FXML
    private TextField Descriotion;

    @FXML
    private TextField Gender;

    @FXML
    private TextField Name;

    @FXML
    private TextField Post;

    @FXML
    private TextField Type;
      @FXML
    private Stage stage;
    @FXML
    private Scene scene;
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

    @FXML
    void apdate(ActionEvent event) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Animal s2 = null;
        s2 = (Animal)session.get(Animal.class, mUserId);
        s2.setAge(Age.getText());
        s2.setType(Animal_Type.getText());
        s2.setContact(Contact.getText());
        s2.setCity(Country.getText());
        s2.setDescription(Descriotion.getText());
        s2.setGender(Gender.getText());
        s2.setName(Name.getText());
        s2.setImageUrl(Post.getText());
        s2.setType(Type.getText());
        session.getTransaction().commit();
        session.close();
       Parent root = FXMLLoader.load(getClass().getResource("/screens/ProfilePage.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         menu.setText("".equals(Aleefah.userName)?"no user":Aleefah.userName);
        lio.setText("LogOut");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Animal s2 = null;
        s2 = (Animal)session.get(Animal.class, mUserId);
        session.getTransaction().commit();
        session.close();
        Age.setText(s2.getAge());
        Animal_Type.setText(s2.getType());
        Contact.setText(s2.getContact());
        Country.setText(s2.getCity());
        Descriotion.setText(s2.getDescription());
        Gender.setText(s2.getGender());
        Name.setText(s2.getName());
        Post.setText(s2.getImageUrl());
        Type.setText(s2.getType());
    }
    
}
