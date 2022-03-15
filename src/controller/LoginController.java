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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modal.User;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ABH
 */
public class LoginController implements Initializable {
    
    @FXML  //fx:id="Register"
    private Label Register;

    @FXML  // fx:id="passwordField"
    private PasswordField passwordField;

    @FXML // fx:id="phoneField"
    private TextField phoneField;
    
     @FXML
     private Stage stage;
  
     @FXML
     private Parent root;
  
     /*-------------------------GO TO REGISTER SCREEN ----------------------*/
     @FXML
     public void goregiter(MouseEvent event) throws IOException {
      root  = FXMLLoader.load(getClass().getResource("/screens/Register.fxml")); 
     stage =(Stage)((Node)event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    

 /*-------------------------LOG IN ACTION ----------------------*/
      public void onLogin(ActionEvent event) throws IOException {
          
     try{
          List <User> users = new ArrayList<>();
           Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createQuery("from User");
     users = query.list();
       session.close();
       for(int i=0;i<users.size();i++){
       
         // matches//الدالة متشس اذا قام المستخدم بكتبة يسمح له بالدخول //
      if(users.get(i).getPhoneNumber().equals(phoneField.getText())&&users.get(i).getPassword().equals(passwordField.getText())){
      Aleefah.userName= users.get(i).getFirstName()+users.get(i).getLastName();
      Aleefah.userId = users.get(i).getId();
         // phoneField.getText().matches("[+966]dmin ")&& passwordField.getText().equals(" ")
      root  = FXMLLoader.load(getClass().getResource("/screens/MainScreen.fxml")); 
      Scene scene=new Scene(root);
       //getSource//تخص مصدار onLogin
      //وضع nood//بدل من الزر لان كل الاطفال والبيرانت يعتبرو  نود
     //getScene//تجلب المشهد الخاص بالنود // ومن المشهد يجلب ليا النافذة الجديدة 
      stage = (Stage)((Node)event.getSource()).getScene().getWindow(); //وهذا السطر يخص الواجة المفتوحة حالينا 
      stage.setScene(scene);
      
       }  }  
       if(Aleefah.userName==""){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("can't Login");
        alert.setHeaderText("somthing went wrong !");
        alert.showAndWait();} 
       }catch(IOException ex){ex.printStackTrace();}
       }//onLogin
         
     

    /*-------------------------BACK TO WELLCOME SREEN ACTION ----------------------*/
    public void onHome(ActionEvent event) throws IOException {
       root  = FXMLLoader.load(getClass().getResource("/screens/WELCOME.fxml"));  
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      Scene scene=new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
          
    }

    
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   