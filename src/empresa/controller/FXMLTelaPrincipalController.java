/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import empresa.model.DAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author L
 */
public class FXMLTelaPrincipalController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label contagemProdutos;

    @FXML
    private Label contagemClientes;

    @FXML
    private AnchorPane anchorPaneTelas;

    @FXML
    private Label contagemFornecedores;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            abraTelaDashboard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abraTelaMantemCliente() throws IOException{
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresa/view/FXMLMantemCliente.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    }

    public void abraTelaDashboard() throws IOException{
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresa/view/FXMLDashboard.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    }

    public void abraTelaMantemProduto() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresa/view/FXMLMantemProduto.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    }

    public void abraTelaMantemCargo() throws  IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresa/view/FXMLMantemCargo.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    }

    public void abraTelaMantemFuncionario() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresa/view/FXMLMantemFuncionario.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    }

    public void abraTelaMantemFornecedor() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresa/view/FXMLMantemFornecedor.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    }

    public void abraTelaMantemVenda() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresa/view/FXMLMantemVenda.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    }

}
