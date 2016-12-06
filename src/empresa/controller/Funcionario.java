package empresa.controller;

import empresa.model.DAO;
import empresa.model.FuncionarioDAO;

import java.sql.SQLException;

/**
 * Created by Marcio on 02/12/2016.
 */
public class Funcionario {

    private String nome;
    private String cpf;

    private int pk_funcionario;
    private Cargo cargo;

    private Endereco endereco;

    public Funcionario() {

    }

    public Funcionario(int pk_funcionario, Cargo cargo, String nome, String cpf) {
        this.pk_funcionario = pk_funcionario;
        this.cargo = cargo;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Funcionario(Cargo cargo, String nome, String cpf, Endereco endereco) {

        this.cargo = cargo;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public Funcionario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getPk_funcionario() {
        return pk_funcionario;
    }

    public void setPk_funcionario(int pk_funcionario) {
        this.pk_funcionario = pk_funcionario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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
        return DAO.contar("funcionarios","");
    }

    @Override
    public String toString() {
        return nome;
    }

    public boolean save() throws SQLException {
        return FuncionarioDAO.create(this);
    }

    public boolean update() throws SQLException {
        return FuncionarioDAO.update(this);
    }

    public boolean delete() throws SQLException {
        return FuncionarioDAO.delete(this);
    }
}
