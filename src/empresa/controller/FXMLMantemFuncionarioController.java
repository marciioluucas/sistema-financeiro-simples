package empresa.controller;

import empresa.model.CargoDAO;
import empresa.model.FuncionarioDAO;
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
public class FXMLMantemFuncionarioController implements Initializable{
    Funcionario f;
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
    private ComboBox<Funcionario> comboBoxFuncionarios;

    @FXML
    private TextField textFieldCEP;

    @FXML
    private ComboBox<Cargo> comboBoxCargos;

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

        List<Funcionario> l = FuncionarioDAO.retreaveAll();
        comboBoxFuncionarios.getItems().addAll(l);
        List<Cargo> lc = CargoDAO.retreaveAll();
        comboBoxCargos.getItems().addAll(lc);
    }

    public void load() {
        f = comboBoxFuncionarios.getValue();
        textFieldNome.setText(f.getNome());
        textFieldCPF.setText(f.getCpf());
        textFieldEndereco.setText(f.getEndereco().getLogradouro());
        textFieldBairro.setText(f.getEndereco().getBairro());
        textFieldCEP.setText(f.getEndereco().getCep());
        textFieldCidade.setText(f.getEndereco().getCidade());
        comboBoxEstado.setValue(f.getEndereco().getEstado());
        comboPais.setValue(f.getEndereco().getPais());
        comboBoxCargos.setValue(f.getCargo());
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
        comboBoxCargos.getSelectionModel().clearSelection();
    }

    public void salvar() throws SQLException {
        boolean insert = false;

        if (f == null) {
            f = new Funcionario();
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

        f.setCargo(comboBoxCargos.getValue());

        if (insert) {
            try {
                if (f.save()) {
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Sucesso!");
                    dialogoInfo.setHeaderText("Funcionario cadastrado com sucesso!");
                    dialogoInfo.setContentText("ID do funcionario cadastrado: " + f.getPk_funcionario());
                    dialogoInfo.showAndWait();
                    comboBoxFuncionarios.getItems().clear();
                    List<Funcionario> l = FuncionarioDAO.retreaveAll();
                    comboBoxFuncionarios.getItems().addAll(l);
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
                    dialogoInfo.setHeaderText("Funcionario alterado com sucesso!");
                    dialogoInfo.setContentText("ID do funcionario alterado: " + f.getPk_funcionario());
                    dialogoInfo.showAndWait();
                    comboBoxFuncionarios.getItems().clear();
                    List<Funcionario> l = FuncionarioDAO.retreaveAll();
                    comboBoxFuncionarios.getItems().addAll(l);
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
        f = comboBoxFuncionarios.getValue();
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        try {
            if(f.delete()){
                dialogoInfo.setTitle("Sucesso!");
                dialogoInfo.setHeaderText("Funcionario excluido com sucesso!");
                dialogoInfo.setContentText("ID do funcionario excluido: " + f.getPk_funcionario());
                dialogoInfo.showAndWait();
                comboBoxFuncionarios.getItems().clear();
                List<Funcionario> l = FuncionarioDAO.retreaveAll();
                comboBoxFuncionarios.getItems().addAll(l);
            }

        } catch(Exception e){
            dialogoInfo.setTitle("Erro");
            dialogoInfo.setHeaderText("Um erro aconteceu");
            dialogoInfo.setContentText(e.getMessage());
            dialogoInfo.showAndWait();
        }
    }
}
