package empresa.controller;

import empresa.model.ProdutoDAO;
import empresa.util.Datas;
import empresa.util.MaskFieldUtil;
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
    private TextField textFieldDinheiro;

    @FXML
    private TextField textFieldCartao;

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
    private Label labelPrecoTotal;
    @FXML
    private Label labelTroco;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<Produto> l = ProdutoDAO.retreaveAll();
        comboBoxProdutos.getItems().addAll(l);

        MaskFieldUtil.monetaryField(textFieldDinheiro);
        MaskFieldUtil.monetaryField(textFieldCartao);
        MaskFieldUtil.monetaryField(textFieldPrecoUnitario);
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
        textFieldCodigo.setText(String.valueOf(p.getPk_produto()));
        textFieldSubtotal.setText(String.valueOf(i.getValorUnitario()));
        labelPrecoTotal.setText(String.valueOf(v.somaValorTotal()));
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
        if (textAreaNf.getText().equals("")) {
            textAreaNf.setText(
                    "           *** SUPERMERCADO DO XIBIMBA ***\n" +
                    "              Sistema do Marcinho Bioca\n" +
                    "            IF Goiano - Campus Morrinhos\n" +
                    " CNPJ: 05.055.202/0007-03\n" +
                    " IE: 10.436.561-7\n" +
                    " --------------------------------------------------------\n" +
                    " " + Datas.retornaData(new Date()) + "                 CCF: 050719    COO:071734\n" +
                    "                     CUPOM FISCAL\n" +
                    " ITEM  CODIGO   DESCRIÇÃO     QTD   VL ITEM( R$)\n" +
                    " --------------------------------------------------------\n");
        }

        String strToConcaten = v.getItens().size() + " " + p.getPk_produto() + " " + p.getNome() + "   " + i.getQtd() + "       " + i.getValorUnitario() + "\n";
        textAreaNf.setText(textAreaNf.getText() + strToConcaten);
    }

    @FXML
    public void somaMetodosPagamento() {
        if(textFieldDinheiro.getText().equals("")){
            textFieldDinheiro.setText("0.00");
        }
        if(textFieldCartao.getText().equals("")) {
            textFieldCartao.setText("0.00");
        }
        labelTroco.setText(
         String.valueOf(
                 v.voltaTroco(v.somaValoresMetodosPagamento(Double.parseDouble(textFieldDinheiro.getText()),
                Double.parseDouble(textFieldCartao.getText())),v.somaValorTotal())));
    }
}
