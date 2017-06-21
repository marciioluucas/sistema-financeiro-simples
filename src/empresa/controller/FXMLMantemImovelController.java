package empresa.controller;

import empresa.model.Endereco;
import empresa.model.Imovel;
import empresa.model.ImovelDAO;
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
 * Created by IFGoiano on 05/12/2016.
 */
public class FXMLMantemImovelController implements Initializable {

    private Imovel i;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<Imovel> comboBoxImoveis;


    @FXML
    private TextField textFieldQuartos;


    @FXML
    private TextField textFieldBanheiros;

    @FXML
    private ComboBox<String> comboBoxTipo;

    @FXML
    private TextField textFieldGaragens;

    @FXML
    private ComboBox<String> comboBoxStatus;

    @FXML
    private TextField textFieldLogradouro;

    @FXML
    private TextField textFieldBairro;

    @FXML
    private ComboBox<String> comboBoxEstado;

    @FXML
    private TextField textFieldCidade;

    @FXML
    private ComboBox<String> comboPais;

    @FXML
    private TextField textFieldCEP;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Imovel> l = ImovelDAO.retreaveAll();
        comboBoxImoveis.getItems().addAll(l);
        comboBoxEstado.
                getItems().
                addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO"
                );

        comboPais.getItems().add("Brasil");
        comboPais.getItems().add("Estados Unidos");
        comboPais.getItems().add("Canadá");

        comboBoxTipo.getItems().add("Aluguel");
        comboBoxTipo.getItems().add("Venda");

        comboBoxStatus.getItems().add("Vazio");
        comboBoxStatus.getItems().add("Alugado");
        comboBoxStatus.getItems().add("Vendido");
    }

    public void load() {


        i = comboBoxImoveis.getValue();
        textFieldQuartos.setText(String.valueOf(i.getQuartos()));
        textFieldBanheiros.setText(String.valueOf(i.getBanheiros()));
        textFieldGaragens.setText(String.valueOf(i.getGaragens()));
        comboBoxStatus.setValue(i.getStatus());
        comboBoxTipo.setValue(i.getTipo());

        textFieldLogradouro.setText(i.getEndereco().getLogradouro());
        textFieldBairro.setText(i.getEndereco().getBairro());
        textFieldCEP.setText(i.getEndereco().getCep());
        textFieldCidade.setText(i.getEndereco().getCidade());
        comboBoxEstado.setValue(i.getEndereco().getEstado());
        comboPais.setValue(i.getEndereco().getPais());


    }

    public void limpaTela() {

    }

    public void salvar() throws SQLException {
        boolean insert = false;
        if (i == null) {
            i = new Imovel(Integer.parseInt(textFieldQuartos.getText()),
                    Integer.parseInt(textFieldBanheiros.getText()),
                    comboBoxTipo.getValue(),
                    Integer.parseInt(textFieldGaragens.getText()),
                    comboBoxStatus.getValue()
            );
            i.setEndereco(new Endereco());
            i.getEndereco().setBairro(textFieldBairro.getText());
            i.getEndereco().setCep(textFieldCEP.getText());
            i.getEndereco().setCidade(textFieldCidade.getText());
            i.getEndereco().setLogradouro(textFieldLogradouro.getText());
            i.getEndereco().setEstado(comboBoxEstado.getValue());
            i.getEndereco().setPais(comboPais.getValue());
            insert = true;
        }
        if (insert) {
            try {
                if (i.save()) {
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Sucesso!");
                    dialogoInfo.setHeaderText("Imovel cadastrado com sucesso!");
                    dialogoInfo.setContentText("ID do Imovel cadastrado: " + i.getPk_imovel());
                    dialogoInfo.showAndWait();
                    comboBoxImoveis.getItems().clear();
                    List<Imovel> l = ImovelDAO.retreaveAll();
                    comboBoxImoveis.getItems().addAll(l);
                }
            } catch (Exception e) {
                Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
                dialogoInfo.setTitle("Erro");
                dialogoInfo.setHeaderText("Um erro aconteceu");
                dialogoInfo.setContentText(e.getMessage());
                dialogoInfo.showAndWait();
            }
        } else {
            try {
                i.setQuartos(Integer.parseInt(textFieldQuartos.getText()));
                i.setBanheiros(Integer.parseInt(textFieldBanheiros.getText()));
                i.setTipo(comboBoxTipo.getValue());
                i.setGaragens(Integer.parseInt(textFieldGaragens.getText()));
                i.setStatus(comboBoxStatus.getValue());
                if (i.update()) {
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Sucesso!");
                    dialogoInfo.setHeaderText("Imovel alterado com sucesso!");
                    dialogoInfo.setContentText("ID do Imovel alterado: " + i.getPk_imovel());
                    dialogoInfo.showAndWait();
                    comboBoxImoveis.getItems().clear();
                    List<Imovel> l = ImovelDAO.retreaveAll();
                    comboBoxImoveis.getItems().addAll(l);
                }
            } catch (Exception e) {
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
        i = comboBoxImoveis.getValue();
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        try {
            if (i.delete()) {
                dialogoInfo.setTitle("Sucesso!");
                dialogoInfo.setHeaderText("Imovel excluido com sucesso!");
                dialogoInfo.setContentText("ID do Imovel excluido: " + i.getPk_imovel());
                dialogoInfo.showAndWait();
                comboBoxImoveis.getItems().clear();
                List<Imovel> l = ImovelDAO.retreaveAll();
                comboBoxImoveis.getItems().addAll(l);
            }

        } catch (Exception e) {
            dialogoInfo.setTitle("Erro");
            dialogoInfo.setHeaderText("Um erro aconteceu");
            dialogoInfo.setContentText(e.getMessage());
            dialogoInfo.showAndWait();
        }
    }
}
