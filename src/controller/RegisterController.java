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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modal.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author shooo
 */
public class RegisterController implements Initializable {
    
    @FXML
    Label label;
    @FXML
    Label label1;
    @FXML
    TextField text;
    @FXML
     
    private TextField confirmP;

    @FXML
    private TextField fName;

     @FXML
    private TextField lName;
      @FXML
    private TextField password;

    @FXML
    private TextField phone;
    @FXML
     private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private String phoneNum="";
    /*-------------------------REGISTER NEW USER ACTION  ----------------------*/
      @FXML
    void rigister(ActionEvent event) throws IOException {
        List <User> users = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User");
        users = query.list();
        session.close();
        for(int i=0;i<users.size();i++){
            
            if(users.get(i).getPhoneNumber().equals(phone.getText())){
                JOptionPane.showMessageDialog(null," you are already registered! ");
                break;
            } else{
                if(password.getText().equals(confirmP.getText())){
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    User newUser = new User(fName.getText(),lName.getText(),phone.getText(),password.getText());
                    session.save(newUser);
                    tx.commit();
                    session.close();
                    Aleefah.userName= fName.getText()+lName.getText();
                    phoneNum=phone.getText();
                    break;
                    
                    
                }
                else{
                    JOptionPane.showMessageDialog(null," the password dosent match! ");
                }
                
            } }
        
        
        
        
        
        if(Aleefah.userName==""){
         Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("can't Register");
        alert.setHeaderText("somthing went wrong !");
        alert.showAndWait();
        }else{
           session = HibernateUtil.getSessionFactory().openSession();
     query = session.createQuery("from User");
     users = query.list();
       session.close();
         for(int i=0;i<users.size();i++){
      if(users.get(i).getPhoneNumber().equals(phoneNum)){
      Aleefah.userId = users.get(i).getId();
       }  }
        Parent root  = FXMLLoader.load(getClass().getResource("/screens/MainScreen.fxml")); 
      Scene scene=new Scene(root);
      stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
      stage.setScene(scene);
      stage.setTitle("ALEEFAH");}
   
    
    }
}
    
    

