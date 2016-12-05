package empresa.controller;

import empresa.model.CargoDAO;

/**
 * Created by Marcio on 01/12/2016.
 */
public class Cargo {
    private int pk_cargo;
    private String nome;
    private String descricao;


    public Cargo() {

    }

    public Cargo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Cargo(int pk_cargo, String nome, String descricao) {
        this.pk_cargo = pk_cargo;
        this.nome = nome;
        this.descricao = descricao;
    }

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

    public boolean save() throws Exception {
        return CargoDAO.create(this);
    }

    public boolean update() throws Exception {
        return CargoDAO.update(this);
    }

    public boolean delete() throws Exception {
        return CargoDAO.delete(this);
    }
}
