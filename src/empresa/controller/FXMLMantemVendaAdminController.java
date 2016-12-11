package empresa.controller;

import empresa.model.CargoDAO;
import empresa.model.VendaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLMantemVendaAdminController implements Initializable{

    Venda v;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<Venda> comboBoxVendas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Venda> lv = VendaDAO.retreaveAll();
        comboBoxVendas.getItems().addAll(lv);
    }

    @FXML
    public void apagar(ActionEvent event) {
        v = comboBoxVendas.getValue();
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        try {
            if(v.delete()){
                dialogoInfo.setTitle("Sucesso!");
                dialogoInfo.setHeaderText("Venda excluido com sucesso!");
                dialogoInfo.setContentText("ID da venda excluida: " + v.getPk_venda());
                dialogoInfo.showAndWait();
                comboBoxVendas.getItems().clear();
                List<Venda> lv = VendaDAO.retreaveAll();
                comboBoxVendas.getItems().addAll(lv);
            }

        } catch(Exception e){
            dialogoInfo.setTitle("Erro");
            dialogoInfo.setHeaderText("Um erro aconteceu");
            dialogoInfo.setContentText(e.getMessage());
            dialogoInfo.showAndWait();
        }
    }
}