package empresa.model;

import java.sql.SQLException;

/**
 * Created by IFGoiano on 02/12/2016.
 */
public class Funcionario {

    private String nome;
    private String cpf;
    private String senha;
    private int pk_funcionario;
    private Cargo cargo;

    private Endereco endereco;

    public Funcionario() {

    }

    public Funcionario(int pk_funcionario, String nome, String cpf, Endereco endereco) {
        this.pk_funcionario = pk_funcionario;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public Funcionario(Cargo cargo, int pk_funcionario, String nome, String cpf, Endereco endereco) {
        this.pk_funcionario = pk_funcionario;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int contar() throws SQLException {
        return DAO.contar("funcionario", "");
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
