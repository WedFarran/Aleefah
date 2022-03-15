package controller;

import aleefah.Aleefah;
import aleefah.HibernateUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import modal.Animal;
import modal.Animal;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modal.Globalvariables;
import modal.Globalvariables;

public class ProfileScreenController implements Initializable {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    private GridPane profile_animals_gridpane;

    @FXML
    private AnchorPane rootAnchorPane;
    
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

    private List<Animal> getData() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List <Animal> animals= new ArrayList<>();
        String queryStr = "from Animal";
        Query query = session.createQuery(queryStr);
        animals = query.list();
        session.close();
        for(int i=0 ; i<animals.size();i++){
        if(Globalvariables.user_id == animals.get(i).getUserId()){
        Globalvariables.animals.add(animals.get(i));
        }
        }
        return Globalvariables.animals;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
   menu.setText("".equals(Aleefah.userName)?"no user":Aleefah.userName);
        lio.setText("LogOut");
        getData();

        loadElements();
    }
Boolean x=false;
    public void loadElements() {
        
        int i = 0;
        profile_animals_gridpane.getChildren().clear();
        for (Animal a : Globalvariables.animals) {

            HBox hbox = new HBox(10);
            hbox.setPadding(new Insets(20, 12, 15, 12));
            hbox.setSpacing(30);
            hbox.setAlignment(Pos.CENTER);
            hbox.setPrefSize(487, 126);
            hbox.setStyle("-fx-background-color: #F2F2F2;");

            BorderPane borderpane = new BorderPane();
            borderpane.setPrefSize(180, 126);
            borderpane.setPadding(new Insets(5, 5, 5, 5));
            borderpane.setStyle("-fx-background-color: #bbcfcd;");
            Image img = new Image(a.getImageUrl());
            ImageView im = new ImageView(img);
            im.setFitWidth(173);
            im.setFitHeight(117);
            borderpane.setCenter(im);
            //Button Delete = new Button("Delete " + a.getAnimal_id()+a.getUser_id());
            Button Delete = new Button("Delete " + a.getAnimalID());
            Delete.setPrefSize(110, 42);
            Delete.setAlignment(Pos.CENTER);
            Delete.setStyle("-fx-background-color: #bbcfcd;" + " -fx-font-family: Kufi Extended Outline;"
                    + " -fx-font-size:20; " + "-fx-font-style: normal;" + " -fx-text-fill: #4d4d4f;"
                    + " -fx-background-radius: 10 10 10 10;" + "-fx-alignment: CENTER;"
                    + "-fx-effect: dropshadow(three-pass-box,rgba(75,75,77,0.6),10.0,0.0,0.0,4.0);");
            Button Modify = new Button("Modify" + a.getAnimalID());
            Modify.setPrefSize(110, 42);
            Modify.setAlignment(Pos.CENTER);
            Modify.setStyle("-fx-background-color: #bbcfcd;" + " -fx-font-family: Kufi Extended Outline;"
                    + " -fx-font-size:20; " + "-fx-font-style: normal;" + " -fx-text-fill: #4d4d4f;"
                    + " -fx-background-radius: 10 10 10 10;" + "-fx-alignment: CENTER;"
                    + "-fx-effect: dropshadow(three-pass-box,rgba(75,75,77,0.6),10.0,0.0,0.0,4.0);");
            hbox.getChildren().addAll(borderpane, Delete, Modify);
            profile_animals_gridpane.addRow(i, hbox);
            i++;
//________________________________2. Delete ____________________________________   
            
            Delete.setOnAction(e -> {
                BoxBlur blur = new BoxBlur(3, 3, 3);
                rootAnchorPane.setEffect(blur);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Are you sure want to delete this ?");
                Optional<ButtonType> choise = alert.showAndWait();
                if (choise.get() == ButtonType.OK) {
                    Button aid = (Button) e.getSource();
                    int anid = Integer.parseInt(aid.getText().split(" ")[1]);
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    Animal animal = null;// creat query for patient
                    for (Animal aa : Globalvariables.animals) {
                        if (aa.getAnimalID()== anid) {
                            session.delete(aa);
                            tx.commit();
                            animal = aa; }}
                    Globalvariables.animals.remove(animal);
                    session.close();
                    loadElements();
                   // x=true;}
                //if(x==true){Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                   // alert2.setContentText("Animal Was Deleted Successfully ");
                rootAnchorPane.setEffect(null); }
                
                    
                
                else if (choise.get() == ButtonType.CANCEL) {
                    rootAnchorPane.setEffect(null);
                    return ;
                }
            });
            
            /*-------------------------TO MODIFY SCREEN ACTION  ----------------------*/
            Modify.setOnAction(e->{
                ModifyController.mUserId= a.getAnimalID();
                try {
                    root = FXMLLoader.load(getClass().getResource("/screens/modify.fxml"));
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ProfileScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            });
            
        }
        System.out.print(i);
        
        
    }
    
    
    
    
//____________________Profile Switch Between Scenes Controller__________________

    /*@FXML
    public void BackToMainScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/
    @FXML
    private void ToAddPage(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/screens/add.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    void backButton(ActionEvent event) throws IOException {
       root = FXMLLoader.load(getClass().getResource("/screens/MainScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   

}
