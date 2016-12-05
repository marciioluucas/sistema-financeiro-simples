package empresa.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Marcio on 05/12/2016.
 */
public class FXMLDashboardController implements Initializable {

    @FXML
    private Label contagemProdutos;

    @FXML
    private Label contagemClientes;

    @FXML
    private Label contagemFornecedores;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            contagemClientes.setText(String.valueOf(new Cliente().contar()));
            contagemFornecedores.setText(String.valueOf(new Fornecedor().contar()));
            contagemProdutos.setText(String.valueOf(new Produto().contar()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
