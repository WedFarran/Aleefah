/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import controller.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author ABH
 */
public class WelcomeController implements Initializable{
    
  
    @FXML
    private ImageView IM;
    
    @FXML 
    private Parent wel;
      
    @FXML
    private Stage stage;
    
    
    
   
    @FXML
    private void gotopage1(ActionEvent event) throws IOException {
      wel = FXMLLoader.load(getClass().getResource("/screens/MainScreen.fxml")); 
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      Scene scene= new Scene(wel);
      stage.setTitle("ALEEFAH");
      stage.setScene(scene);
      stage.show();}  // عرض النافذة الجديدة
        
     
    @FXML
    private void gotopage2(ActionEvent event) throws IOException {
      wel = FXMLLoader.load(getClass().getResource("/screens/LOGIN.fxml"));  
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      Scene scene= new Scene(wel);
      stage.setTitle("ALEEFAH");
      stage.setScene(scene);
      stage.show();}
    



///////////////////////onActions//////////////////////
    
    

///////////////////////onActions//////////////////////

 







    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
  