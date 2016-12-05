package empresa.controller;

import empresa.model.ClienteDAO;
import empresa.model.ProdutoDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Marcio on 05/12/2016.
 */
public class FXMLMantemProdutoController implements Initializable {

    Produto p;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<Produto> comboBoxProdutos;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Produto> l = ProdutoDAO.retreaveAll();
      comboBoxProdutos.getItems().addAll(l);
    }

    public void load() {
//        p = comboBoxProdutos.getValue();
//        textFieldNome.setText(p.getNome());
//        textFieldCPF.setText(p.getCpf());
//        textFieldEndereco.setText(p.getEndereco().getLogradouro());
//        textFieldBairro.setText(p.getEndereco().getBairro());
//        textFieldCEP.setText(p.getEndereco().getCep());
//        textFieldCidade.setText(p.getEndereco().getCidade());
//        comboBoxEstado.setValue(p.getEndereco().getEstado());
//        comboPais.setValue(p.getEndereco().getPais());
    }

    public void limpaTela() {
//        textFieldBairro.clear();
//        textFieldCEP.clear();
//        textFieldCPF.clear();
//        textFieldCidade.clear();
//        textFieldEndereco.clear();
//        textFieldNome.clear();
//        comboBoxEstado.getSelectionModel().clearSelection();
//        comboPais.getSelectionModel().clearSelection();
    }

    public void salvar() throws SQLException {
        boolean insert = false;

        if (p == null) {
            p = new Produto();
            insert = true;
        }

//        p.getEndereco().setBairro(textFieldBairro.getText());
//        p.getEndereco().setCep(textFieldCEP.getText());
//        p.getEndereco().setCidade(textFieldCidade.getText());
//        p.getEndereco().setLogradouro(textFieldEndereco.getText());
//        p.getEndereco().setEstado(comboBoxEstado.getValue());
//        p.getEndereco().setPais(comboPais.getValue());
//
//        p.setNome(textFieldNome.getText());
//        p.setCpf(textFieldCPF.getText());

        if (insert) {
            try {
                if (p.save()) {
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Sucesso!");
                    dialogoInfo.setHeaderText("Produto cadastrado com sucesso!");
                    dialogoInfo.setContentText("ID do produto cadastrado: " + p.getPk_produto());
                    dialogoInfo.showAndWait();
                    comboBoxProdutos.getItems().clear();
                    List<Produto> l = ProdutoDAO.retreaveAll();
                    comboBoxProdutos.getItems().addAll(l);
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

                if(p.update()){
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Sucesso!");
                    dialogoInfo.setHeaderText("Produto alterado com sucesso!");
                    dialogoInfo.setContentText("ID do produto alterado: " + p.getPk_produto());
                    dialogoInfo.showAndWait();
                    comboBoxProdutos.getItems().clear();
                    List<Produto> l = ProdutoDAO.retreaveAll();
                    comboBoxProdutos.getItems().addAll(l);
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
        p = comboBoxProdutos.getValue();
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        try {
            if(p.delete()){
                dialogoInfo.setTitle("Sucesso!");
                dialogoInfo.setHeaderText("Produto excluido com sucesso!");
                dialogoInfo.setContentText("ID do produto excluido: " + p.getPk_produto());
                dialogoInfo.showAndWait();
                comboBoxProdutos.getItems().clear();
                List<Produto> l = ProdutoDAO.retreaveAll();
                comboBoxProdutos.getItems().addAll(l);
            }

        } catch(Exception e){
            dialogoInfo.setTitle("Erro");
            dialogoInfo.setHeaderText("Um erro aconteceu");
            dialogoInfo.setContentText(e.getMessage());
            dialogoInfo.showAndWait();
        }
    }
}
