/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 *
 * @author L
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLTelaPrincipal.fxml"));
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        
        stage.setTitle("Gerenciamento de empresa v1.0");
        Image icone = new Image(
                getClass().
                   getResourceAsStream(
                     "resources/appicon.png"));
        stage.getIcons().add(icone);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
