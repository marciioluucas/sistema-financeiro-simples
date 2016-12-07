package empresa.controller;

import empresa.model.DAO;
import empresa.model.FornecedorDAO;

import java.sql.SQLException;

/**
 * Created by Marcio on 01/12/2016.
 */
public class Fornecedor {

    private String nome;
    private String cpf;

    private int pk_fornecedor;

    private Endereco endereco;

    public Fornecedor() {

    }

    public Fornecedor(int pk_fornecedor, String nome, String cpf) {
        this.pk_fornecedor = pk_fornecedor;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Fornecedor(int pk_fornecedor, String nome, String cpf, Endereco endereco) {
        this.pk_fornecedor = pk_fornecedor;
        this.endereco = endereco;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getPk_fornecedor() {
        return pk_fornecedor;
    }

    public void setPk_fornecedor(int pk_fornecedor) {
        this.pk_fornecedor = pk_fornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int contar() throws SQLException {
        return DAO.contar("fornecedores", "");
    }

    @Override
    public String toString() {
        return nome;
    }

    public boolean save() throws Exception {
        return FornecedorDAO.create(this);
    }

    public boolean update() throws Exception {
        return FornecedorDAO.update(this);
    }

    public boolean delete() throws  SQLException {
        return FornecedorDAO.delete(this);
    }

}
