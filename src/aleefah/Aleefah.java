/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aleefah;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author wedfa
 */
public class Aleefah extends Application {
    public static String userName = "";
     public static int userId=-1;
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/screens/WELCOME.fxml"));
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image("/image/2021-10-22 045636_1.gif"));
        primaryStage.setTitle("Aleefah");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
