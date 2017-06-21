package empresa.controller;

import empresa.model.Cargo;
import empresa.model.CargoDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
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
public class FXMLMantemCargoController implements Initializable {
    Cargo c;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<Cargo> comboBoxCargos;

    @FXML
    private TextArea textAreaDescricao;

    @FXML
    private TextField textFieldNome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Cargo> l = CargoDAO.retreaveAll();
        comboBoxCargos.getItems().addAll(l);
    }


    public void load() {
        c = comboBoxCargos.getValue();
        textFieldNome.setText(c.getNome());
        textAreaDescricao.setText(c.getDescricao());
    }

    public void limpaTela() {
        textFieldNome.clear();
        textAreaDescricao.clear();
    }
    public void salvar() throws SQLException {
        boolean insert = false;

        if (c == null) {
            c = new Cargo(textFieldNome.getText(),
                    textAreaDescricao.getText());
            insert = true;
        }
        if (insert) {
            try {
                if (c.save()) {
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Sucesso!");
                    dialogoInfo.setHeaderText("Cargo cadastrado com sucesso!");
                    dialogoInfo.setContentText("ID do cargo cadastrado: " + c.getPk_cargo());
                    dialogoInfo.showAndWait();
                    comboBoxCargos.getItems().clear();
                    List<Cargo> l = CargoDAO.retreaveAll();
                    comboBoxCargos.getItems().addAll(l);
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
                c.setNome(textFieldNome.getText());
                c.setDescricao(textAreaDescricao.getText());
                if(c.update()){
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Sucesso!");
                    dialogoInfo.setHeaderText("Cargo alterado com sucesso!");
                    dialogoInfo.setContentText("ID do cargo alterado: " + c.getPk_cargo());
                    dialogoInfo.showAndWait();
                    comboBoxCargos.getItems().clear();
                    List<Cargo> l = CargoDAO.retreaveAll();
                    comboBoxCargos.getItems().addAll(l);
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
        c = comboBoxCargos.getValue();
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        try {
            if(c.delete()){
                dialogoInfo.setTitle("Sucesso!");
                dialogoInfo.setHeaderText("Cargo excluido com sucesso!");
                dialogoInfo.setContentText("ID do cargo excluido: " + c.getPk_cargo());
                dialogoInfo.showAndWait();
                comboBoxCargos.getItems().clear();
                List<Cargo> l = CargoDAO.retreaveAll();
                comboBoxCargos.getItems().addAll(l);
            }

        } catch(Exception e){
            dialogoInfo.setTitle("Erro");
            dialogoInfo.setHeaderText("Um erro aconteceu");
            dialogoInfo.setContentText(e.getMessage());
            dialogoInfo.showAndWait();
        }
    }
}
