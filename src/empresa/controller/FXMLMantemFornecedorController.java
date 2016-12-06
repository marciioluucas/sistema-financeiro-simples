package empresa.controller;

import empresa.model.FornecedorDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Marcio on 06/12/2016.
 */
public class FXMLMantemFornecedorController implements Initializable {
    Fornecedor f;
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
    private ComboBox<Fornecedor> comboBoxFornecedores;

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

        List<Fornecedor> l = FornecedorDAO.retreaveAll();
        comboBoxFornecedores.getItems().addAll(l);
    }

    public void load() {
        f = comboBoxFornecedores.getValue();
        textFieldNome.setText(f.getNome());
        textFieldCPF.setText(f.getCpf());
        textFieldEndereco.setText(f.getEndereco().getLogradouro());
        textFieldBairro.setText(f.getEndereco().getBairro());
        textFieldCEP.setText(f.getEndereco().getCep());
        textFieldCidade.setText(f.getEndereco().getCidade());
        comboBoxEstado.setValue(f.getEndereco().getEstado());
        comboPais.setValue(f.getEndereco().getPais());
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

        if (f == null) {
            f = new Fornecedor();
            f.setEndereco(new Endereco());
            insert = true;
        }

        f.getEndereco().setBairro(textFieldBairro.getText());
        f.getEndereco().setCep(textFieldCEP.getText());
        f.getEndereco().setCidade(textFieldCidade.getText());
        f.getEndereco().setLogradouro(textFieldEndereco.getText());
        f.getEndereco().setEstado(comboBoxEstado.getValue());
        f.getEndereco().setPais(comboPais.getValue());

        f.setNome(textFieldNome.getText());
        f.setCpf(textFieldCPF.getText());

        if (insert) {
            try {
                if (f.save()) {
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Sucesso!");
                    dialogoInfo.setHeaderText("Fornecedor cadastrado com sucesso!");
                    dialogoInfo.setContentText("ID do fornecedor cadastrado: " + f.getPk_fornecedor());
                    dialogoInfo.showAndWait();
                    comboBoxFornecedores.getItems().clear();
                    List<Fornecedor> l = FornecedorDAO.retreaveAll();
                    comboBoxFornecedores.getItems().addAll(l);
                }
            } catch (Exception e) {
                Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
                dialogoInfo.setTitle("Erro");
                dialogoInfo.setHeaderText("Um erro aconteceu");
                dialogoInfo.setContentText(e.getMessage());
                dialogoInfo.showAndWait();
            }
        } else {
            try{

                if(f.update()){
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Sucesso!");
                    dialogoInfo.setHeaderText("Fornecedor alterado com sucesso!");
                    dialogoInfo.setContentText("ID do fornecedor alterado: " + f.getPk_fornecedor());
                    dialogoInfo.showAndWait();
                    comboBoxFornecedores.getItems().clear();
                    List<Fornecedor> l = FornecedorDAO.retreaveAll();
                    comboBoxFornecedores.getItems().addAll(l);
                }
            }catch (Exception e) {
                Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
                dialogoInfo.setTitle("Erro");
                dialogoInfo.setHeaderText("Um erro aconteceu");
                dialogoInfo.setContentText(e.getMessage());
                dialogoInfo.showAndWait();
            }
        }
        limpaTela();
    }

    public void cancelar() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("../view/FXMLDashboard.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    public void apagar() {
        f = comboBoxFornecedores.getValue();
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        try {
            if(f.delete()){
                dialogoInfo.setTitle("Sucesso!");
                dialogoInfo.setHeaderText("Fornecedor excluido com sucesso!");
                dialogoInfo.setContentText("ID do fornecedor excluido: " + f.getPk_fornecedor());
                dialogoInfo.showAndWait();
                comboBoxFornecedores.getItems().clear();
                List<Fornecedor> l = FornecedorDAO.retreaveAll();
                comboBoxFornecedores.getItems().addAll(l);
            }

        } catch(Exception e){
            dialogoInfo.setTitle("Erro");
            dialogoInfo.setHeaderText("Um erro aconteceu");
            dialogoInfo.setContentText(e.getMessage());
            dialogoInfo.showAndWait();
        }
    }
}
