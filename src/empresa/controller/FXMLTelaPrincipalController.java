/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author L
 */
public class FXMLTelaPrincipalController implements Initializable {

    @FXML
    private AnchorPane anchorPaneTelas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void abraTelaMantemCliente() throws IOException{
        
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresa/view/FXMLMantemCliente.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    }
    
}
