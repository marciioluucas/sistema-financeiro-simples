package empresa.controller;

/**
 * Created by Marcio on 01/12/2016.
 */
public class Cargo {
    private int pk_cargo;
    private String nome;
    private String descricao;


    public int getPk_cargo() {
        return pk_cargo;
    }

    public void setPk_cargo(int pk_cargo) {
        this.pk_cargo = pk_cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
