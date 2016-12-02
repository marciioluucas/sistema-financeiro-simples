package empresa.controller;

/**
 * Created by Marcio on 02/12/2016.
 */
public class Funcionario {

    private String nome;
    private String cpf;

    private int pk_funcionario;
    private int fk_cargo;

    public Funcionario() {

    }

    public Funcionario(int pk_funcionario, int fk_cargo, String nome, String cpf) {

    }

    public Funcionario(String nome, String cpf){

    }

    public int getPk_funcionario() {
        return pk_funcionario;
    }

    public void setPk_funcionario(int pk_funcionario) {
        this.pk_funcionario = pk_funcionario;
    }

    public int getFk_cargo() {
        return fk_cargo;
    }

    public void setFk_cargo(int fk_cargo) {
        this.fk_cargo = fk_cargo;
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
}
