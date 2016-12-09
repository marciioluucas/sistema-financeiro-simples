package empresa.controller;

/**
 * Created by marci on 07/12/2016.
 */

public class Item {

    private int qtd;
    private double valorUnitario;
    private Produto produto;
    private int fk_origem;
    private int pk_item;

    public Item() {
    }



    public Item(int qtd, double valorUnitario, Produto produto) {
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.produto = produto;

    }

    public Item(int pk_item, int qtd, double valorUnitario, Produto produto, int fk_origem) {
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.produto = produto;
        this.fk_origem = fk_origem;
        this.pk_item = pk_item;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getFk_origem() {
        return fk_origem;
    }

    public void setFk_origem(int fk_origem) {
        this.fk_origem = fk_origem;
    }

    public int getPk_item() {
        return pk_item;
    }

    public void setPk_item(int pk_item) {
        this.pk_item = pk_item;
    }

    @Override
    public String toString() {
        return "Item{" + "qtd=" + qtd + ", valorUnitario=" + valorUnitario + ", produto=" + produto + ", fk_origem=" + fk_origem + ", pk_item=" + pk_item + '}';
    }
}