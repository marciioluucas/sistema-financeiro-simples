package empresa.controller;

/**
 * Created by marci on 07/12/2016.
 */

public class VendaItem {

    private int qtd;
    private double valorUnitario;
    private Produto produto;
    private int fk_venda;
    private int pk_vendaItem;

    public VendaItem() {
    }



    public VendaItem(int qtd, double valorUnitario, Produto produto) {
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.produto = produto;

    }

    public VendaItem(int qtd, double valorUnitario, Produto produto, int fk_venda, int pk_vendaItem) {
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.produto = produto;
        this.fk_venda = fk_venda;
        this.pk_vendaItem = pk_vendaItem;
    }


    @Override
    public String toString() {
        return "VendaItem{" + "qtd=" + qtd + ", valorUnitario=" + valorUnitario + ", produto=" + produto + ", fk_venda=" + fk_venda + ", pk_vendaItem=" + pk_vendaItem + '}';
    }






}