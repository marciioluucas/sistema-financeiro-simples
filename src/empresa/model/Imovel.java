package empresa.model;

import empresa.model.DAO;
import empresa.model.ImovelDAO;

import java.sql.SQLException;

/**
 * Created by IFGoiano on 01/12/2016.
 */
public class Imovel {

    private int pk_imovel;
    private int quartos; // qnt quartos
    private int banheiros; //qnt banheiros
    private String tipo; //Aluguel ou Venda
    private int garagens;
    private String status; //isAlugado, isVendido, toAlugar or toVender
    private Endereco endereco;

    public Imovel(int pk_imovel, int quartos, int banheiros, String tipo, int garagens, String status) {
        this.pk_imovel = pk_imovel;
        this.quartos = quartos;
        this.banheiros = banheiros;
        this.tipo = tipo;
        this.garagens = garagens;
        this.status = status;
    }
    public Imovel(int pk_imovel, int quartos, int banheiros, String tipo, int garagens, String status, Endereco endereco) {
        this.pk_imovel = pk_imovel;
        this.quartos = quartos;
        this.banheiros = banheiros;
        this.tipo = tipo;
        this.garagens = garagens;
        this.status = status;
        this.endereco = endereco;
    }

    public Imovel(int quartos, int banheiros, String tipo, int garagens, String status) {
        this.quartos = quartos;
        this.banheiros = banheiros;
        this.tipo = tipo;
        this.garagens = garagens;
        this.status = status;
    }

    public Imovel() {

    }

    @Override
    public String toString() {
        return endereco.getLogradouro() + ", " + endereco.getBairro() + ", " + endereco.getCidade()  + ", " + endereco.getEstado();
    }

    public int getPk_imovel() {
        return pk_imovel;
    }

    public void setPk_imovel(int pk_imovel) {
        this.pk_imovel = pk_imovel;
    }

    public int getQuartos() {
        return quartos;
    }

    public void setQuartos(int quartos) {
        this.quartos = quartos;
    }

    public int getBanheiros() {
        return banheiros;
    }

    public void setBanheiros(int banheiros) {
        this.banheiros = banheiros;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getGaragens() {
        return garagens;
    }

    public void setGaragens(int garagens) {
        this.garagens = garagens;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    public int contar() throws SQLException {
        return DAO.contar("imovel","");
    }

    public boolean save() throws SQLException {
     return ImovelDAO.create(this);
    }

    public boolean update() throws SQLException {
        return ImovelDAO.update(this);
    }

    public boolean delete() throws SQLException {

        return ImovelDAO.delete(this);
    }


}
