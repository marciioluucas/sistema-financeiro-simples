package empresa.controller;

import empresa.model.Cliente;
import empresa.model.Funcionario;
import empresa.model.Imovel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by IFGoiano on 05/12/2016.
 */
public class FXMLDashboardController implements Initializable {

    @FXML
    private Label contagemImoveis;

    @FXML
    private Label contagemClientes;

    @FXML
    private Label contagemFuncionarios;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            contagemClientes.setText(String.valueOf(new Cliente().contar()));
            contagemImoveis.setText(String.valueOf(new Imovel().contar()));
            contagemFuncionarios.setText(String.valueOf(new Funcionario().contar()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
