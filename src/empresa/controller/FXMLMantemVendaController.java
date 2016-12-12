package empresa.controller;

import empresa.model.ClienteDAO;
import empresa.model.FuncionarioDAO;
import empresa.model.ProdutoDAO;
import empresa.util.Datas;
import empresa.util.MaskFieldUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by marcio on 09/12/2016.
 */
public class FXMLMantemVendaController implements Initializable {

    private Venda v = new Venda();
    private Produto p;
    private Cliente c;
    private Funcionario f;
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
    private ComboBox<Cliente> comboBoxClientes;

    @FXML
    private ComboBox<Funcionario> comboBoxVendedores;

    @FXML
    private Label labelPrecoTotal;
    @FXML
    private Label labelTroco;

    @FXML
    private Button btnCancelaVenda;

    @FXML
    private Button btnCancelaItem;

    @FXML
    private Button btnFinalizarVenda;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<Produto> lp = ProdutoDAO.retreaveAll();
        java.util.List<Cliente> lc = ClienteDAO.retreaveAll();
        java.util.List<Funcionario> lf = FuncionarioDAO.retreaveAll();
        comboBoxProdutos.getItems().addAll(lp);
        comboBoxClientes.getItems().addAll(lc);
        comboBoxVendedores.getItems().addAll(lf);

        MaskFieldUtil.monetaryField(textFieldDinheiro);
        MaskFieldUtil.monetaryField(textFieldCartao);
        MaskFieldUtil.monetaryField(textFieldPrecoUnitario);

        btnCancelaItem.setDisable(true);
        btnCancelaVenda.setDisable(true);
        btnFinalizarVenda.setDisable(true);
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
        escreveNF(true);
        if (v.getItens().size() > 0) {
            btnCancelaItem.setDisable(false);
            btnCancelaVenda.setDisable(false);
            btnFinalizarVenda.setDisable(false);
        } else {
            btnCancelaItem.setDisable(true);
            btnCancelaVenda.setDisable(true);
            btnFinalizarVenda.setDisable(true);
        }
    }

    @FXML
    public void limpaTela() {

    }

    @FXML
    public void load(ActionEvent event) {

    }

    @FXML
    public void salvar(ActionEvent event) throws Exception {
        f = comboBoxVendedores.getValue();
        c = comboBoxClientes.getValue();
        v = new Venda(c, f, v.getItens());
        if (v.create()) {
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Sucesso!");
            dialogoInfo.setHeaderText("Cargo cadastrado com sucesso!");
            dialogoInfo.setContentText("ID da venda cadastrada: " + v.getPk_venda());
            dialogoInfo.showAndWait();

        }
        limpaTela();
    }

    @FXML
    private void escreveNF(boolean addItem) {
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
        String strToConcaten = "";
        if (addItem) {
            strToConcaten = v.getItens().size() + " " + i.getProduto().getPk_produto() + " " + i.getProduto().getNome() + "   " + i.getQtd() + "       " + i.getValorUnitario() + "\n";
            textAreaNf.setText(textAreaNf.getText() + strToConcaten);
        } else {
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
            for (Item item : v.getItens()) {
                strToConcaten = (v.getItens().indexOf(item) + 1) + " " + item.getProduto().getPk_produto() + " " + item.getProduto().getNome() + "   " + item.getQtd() + "       " + item.getValorUnitario() + "\n";
                textAreaNf.setText(textAreaNf.getText() + strToConcaten);
            }
        }


    }

    @FXML
    public void somaMetodosPagamento() {
        if (textFieldDinheiro.getText().equals("")) {
            textFieldDinheiro.setText("0.00");
        }
        if (textFieldCartao.getText().equals("")) {
            textFieldCartao.setText("0.00");
        }
        labelTroco.setText(
                String.valueOf(
                        v.voltaTroco(v.somaValoresMetodosPagamento(Double.parseDouble(textFieldDinheiro.getText()),
                                Double.parseDouble(textFieldCartao.getText())), v.somaValorTotal())));
    }


    @FXML
    public void removerUltimoItem() {
        if (v.getItens().size() != 0) {
            v.removerUltimoItem();
            btnCancelaItem.setDisable(false);
            btnCancelaVenda.setDisable(false);
            btnFinalizarVenda.setDisable(false);
        } else {
            btnCancelaItem.setDisable(true);
            btnCancelaVenda.setDisable(true);
            btnFinalizarVenda.setDisable(true);
        }
        escreveNF(false);
        labelPrecoTotal.setText(String.valueOf(v.somaValorTotal()));
    }


}
