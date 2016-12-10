package empresa.controller;

import empresa.model.ProdutoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.*;

/**
 * Created by marcio on 09/12/2016.
 */
public class FXMLMantemVendaController implements Initializable {

    private Venda v = new Venda();
    private Produto p;
    private Item i;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<Produto> comboBoxProdutos;

    @FXML
    private TextField textFieldQuantidade;

    @FXML
    private TextArea textAreaNf;

    @FXML
    private TextField textFieldCodigo;

    @FXML
    private TextField textFieldPrecoUnitario;

    @FXML
    private TextField textFieldSubtotal;

    @FXML
    private ComboBox<?> comboBoxMetodoPagamento;

    @FXML
    private Label txtPrecoTotal;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<Produto> l = ProdutoDAO.retreaveAll();
        comboBoxProdutos.getItems().addAll(l);
    }

    @FXML
    public void apagar(ActionEvent event) {

    }

    @FXML
    public void cancelar(ActionEvent event) {

    }

    @FXML
    public void adicionarProduto(ActionEvent event) {
        p = comboBoxProdutos.getValue();
        i = new Item();
        i.setProduto(p);
        i.setQtd(Integer.parseInt(textFieldQuantidade.getText()));
        i.setValorUnitario(Double.parseDouble(textFieldPrecoUnitario.getText()));
        v.addItem(i);
        escreveNF();
    }

    @FXML
    public void limpaTela(ActionEvent event) {

    }

    @FXML
    public void load(ActionEvent event) {

    }

    @FXML
    public void salvar(ActionEvent event) {

    }

    @FXML
    private void escreveNF() {
      if(textAreaNf.getText().equals("")){
          textAreaNf.setText("--- NOTINHA FISCAL DO FERA ---\n");
      }

        String strToConcaten = "Prod: "+p.getNome()+" Qtd: "+i.getQtd()+"x\n";
        textAreaNf.setText(textAreaNf.getText()+strToConcaten);
    }
}
