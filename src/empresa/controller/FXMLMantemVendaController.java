package empresa.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by marcio on 09/12/2016.
 */
public class FXMLMantemVendaController implements Initializable {

    Venda v;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<Produto> comboBoxProdutos;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldQtdMinima;

    @FXML
    private TextField textFieldQtdEstoque;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void apagar(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void limpaTela(ActionEvent event) {

    }

    @FXML
    void load(ActionEvent event) {

    }

    @FXML
    void salvar(ActionEvent event) {

    }
}
