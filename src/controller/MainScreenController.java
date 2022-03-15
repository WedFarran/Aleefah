/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
//import controller.*;
//import aleefah.HibernateUtil;
import aleefah.Aleefah;
import aleefah.HibernateUtil;
import aleefah.MyListener;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import modal.Animal;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author wedfa
 */
public class MainScreenController implements Initializable{
       @FXML
    private GridPane grid;
       
       @FXML
MenuBar myMenuBar;
       
    @FXML
    private TextField searchBar;

        @FXML
    private Line parent;
     
     @FXML
     private Stage stage;
  
     private MyListener myListener;
     
     private List <Animal> animals =  FXCollections.observableArrayList();
     @FXML
    private MenuButton menu;
     
     @FXML
    private MenuItem lio;
     
     @FXML
    void enterpresed(KeyEvent event) {
      if(event.getCode() == KeyCode.ENTER){
        grid.getChildren().clear();
       animals.addAll(searchAlgorithm()) ;
        if(animals.size()>0){
        myListener = new MyListener(){
            @Override
            public void onClickListener(Animal animal) {
                setChoosenAnimal(animal);
            }
        };
        }
        display(animals);
      }
    }
    
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
            }}
    }
    
     
     /*-------------------------BACK TO WELLCOME SREEN ACTION ----------------------*/
     @FXML
    void backButton(ActionEvent event) {
  try { 
        Parent root  = FXMLLoader.load(getClass().getResource("/screens/WELCOME.fxml"));
        Scene scene=new Scene(root); 
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        stage.setScene(scene);
              
       } catch (IOException ex) {
        Logger.getLogger(DescreptionController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    /*-------------------------COMBOBOX ACTION ----------------------*/
  

    /*-------------------------SEARCH ALGORITHM AND SEARCH BUTTON ----------------------*/
    public List<Animal> searchAlgorithm(){
        List <Animal> searchAnimals = new ArrayList(animals);
        animals.clear();
        List <Animal> filterdAnimals = new ArrayList() ;
       for(int i=0 ; i<searchAnimals.size();i++){
         if( searchAnimals.get(i).getType().equals(searchBar.getText())){
         filterdAnimals.add(searchAnimals.get(i));
         }
       }
      
       return filterdAnimals; 
    }
     @FXML
    public void searchButton(ActionEvent event) throws IOException {
       grid.getChildren().clear();
       animals.addAll(searchAlgorithm()) ;
        if(animals.size()>0){
        myListener = new MyListener(){
            @Override
            public void onClickListener(Animal animal) {
                setChoosenAnimal(animal);
            }
        };
        }
        display(animals);
    }
    
    /*------------------------- GET ANIMAL LIST FROM DATABASE ----------------------*/
    private List <Animal> getData(){ 
     List <Animal> animals = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createQuery("from Animal");
     animals = query.list();
       session.close();
    return animals;
    }
    
    /*-------------------------TO DISPLAY SHOOSEN ANIMAL IN DESCREPTION SCREEN ----------------------*/
    private void setChoosenAnimal(Animal animal){
    DescreptionController.a=animal;
    } 
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menu.setText("".equals(Aleefah.userName)?"no user":Aleefah.userName);
        lio.setText("".equals(Aleefah.userName)?"LogIn":"LogOut");
        animals.addAll(getData());
        if(animals.size()>0){
        myListener = new MyListener(){
            @Override
            public void onClickListener(Animal animal) {
                setChoosenAnimal(animal);
            }
        };
        }
       display(animals);
        
        }
    
    void display (List<Animal> animals){
     int column = 0,row=0;
        try {
        for(int i=0 ; i<animals.size();i++){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/screens/animal.fxml"));
        AnchorPane anchorePane = loader.load();
           
        AnimalController animalController = loader.getController();
        animalController.getData(animals.get(i), myListener);
        if(column ==3){
        column =0;
        row++;
        }
        grid.add(anchorePane,++column,row);
        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
        grid.setMaxWidth(Region.USE_PREF_SIZE);
        
        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
        grid.setMaxHeight(Region.USE_PREF_SIZE);
        GridPane.setMargin(anchorePane, new Insets(8));
        }
       } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
   
    }