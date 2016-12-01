/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controller;

import empresa.model.ClienteDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author L
 */
public class FXMLMantemClienteController implements Initializable {
    private Endereco e;
    private Cliente c;
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldCPF;

    @FXML
    private TextField textFieldEndereco;

    @FXML
    private TextField textFieldBairro;

    @FXML
    private ComboBox<String> comboBoxEstado;

    @FXML
    private TextField textFieldCidade;

    @FXML
    private ComboBox<String> comboPais;
    
    @FXML
    private ComboBox<Cliente> comboBoxClientes;

    @FXML
    private TextField textFieldCEP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxEstado.
                getItems().
                addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO"
                );

        comboPais.getItems().add("Brasil");
        comboPais.getItems().add("Estados Unidos");
        comboPais.getItems().add("Canadá");
        
        List<Cliente> l = ClienteDAO.retreaveAll();
        comboBoxClientes.getItems().addAll(l);
    }
    
    public void load(){
        c = comboBoxClientes.getValue();
        
        textFieldNome.setText(c.getNome());
        textFieldCPF.setText(c.getCpf());
        textFieldEndereco.setText(c.getEndereco().getLogradouro());
        textFieldBairro.setText(c.getEndereco().getBairro());
        textFieldCEP.setText(c.getEndereco().getCep());
        textFieldCidade.setText(c.getEndereco().getCidade());
        comboBoxEstado.setValue(c.getEndereco().getEstado());
        comboPais.setValue(c.getEndereco().getPais());
    }

    public void limpaTela() {
        textFieldBairro.clear();
        textFieldCEP.clear();
        textFieldCPF.clear();
        textFieldCidade.clear();
        textFieldEndereco.clear();
        textFieldNome.clear();
        comboBoxEstado.getSelectionModel().clearSelection();
        comboPais.getSelectionModel().clearSelection();
    }

    public void salvar() throws SQLException {
        boolean insert = false;
        
        if (c == null){
            c = new Cliente();
            e = new Endereco();
            insert = true;
        }
        
        e.setBairro(textFieldBairro.getText());
        e.setCep(textFieldCEP.getText());
        e.setCidade(textFieldCidade.getText());
        e.setLogradouro(textFieldEndereco.getText());
        e.setEstado(comboBoxEstado.getValue());
        e.setPais(comboPais.getValue());

        c.setNome(textFieldNome.getText());
        c.setCpf(textFieldCPF.getText());
        c.setEndereco(e);
        
        if (insert){
           c.save();
        } else {
           c.update();
        }
        limpaTela();
    }

    public void cancelar() {

        limpaTela();
    }

}
