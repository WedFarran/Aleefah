
package controller;

//import controller.*;
import aleefah.MyListener;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modal.Animal;

/**
 *
 * @author wedfa
 */
public class AnimalController {
     @FXML
    private Label animalAge;

    @FXML
    private ImageView animalImage;

    @FXML
    private Label animalName;

    @FXML
    private Label animalType;
    @FXML
     private Stage stage;
    
     @FXML
      public  void presed(MouseEvent event) throws IOException {
         myListener.onClickListener(animal);
      Parent root  = FXMLLoader.load(getClass().getResource("/screens/description.fxml")); 
      Scene scene=new Scene(root);
      stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
      stage.setScene(scene);
      stage.setTitle("Aleefah");
    }
    
    private Animal animal;
    private MyListener myListener;
    
    public void getData(Animal animal,MyListener myListener){
        this.animal=animal;
        this.myListener= myListener;
        animalAge.setText(animal.getAge());
        animalName.setText(animal.getName());
        animalType.setText(animal.getType());
        Image image = new Image(animal.getImageUrl());
        animalImage.setImage(image);
    
    }

    
}
