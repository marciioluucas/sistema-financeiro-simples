/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.model;

import empresa.controller.Endereco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author L
 */
public class EnderecoDAO {

    public static boolean create(Endereco objetoEndereco, String tabela, String fk, int valorFk) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();
            //INSERT INTO clientes_enderecos(
            //pk_enderenco, fk_cliente, logradouro, bairro, cidade, estado,
            //pais, cep)VALUES (?, ?, ?, ?, ?, ?, ?, ?);


            String sql =
                    "insert into "+tabela+" ("+fk+", logradouro, bairro, cidade, estado, pais, cep) values (" +
                            valorFk + ",'" +
                            objetoEndereco.getLogradouro() + "','" +
                            objetoEndereco.getBairro() + "','" +
                            objetoEndereco.getCidade() + "','" +
                            objetoEndereco.getEstado() + "','" +
                            objetoEndereco.getPais() + "','" +
                            objetoEndereco.getCep() + "')";
            System.out.println(sql);
            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            objetoEndereco.setPk_endereco(key);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Endereco retreave(int pkEndereco) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();
            String sql = "Select * from clientes_enderecos where pk_endereco =" + pkEndereco;
            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            return new Endereco(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_cliente"));
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Endereco retreaveBy(String tabela, String foreignKey, int valorForeignKey) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();
            String sql = "Select * from "+tabela+" where "+foreignKey+" = " + valorForeignKey;
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {

                return new Endereco(
                        rs.getString("logradouro"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getInt("pk_endereco"),
                        rs.getInt(foreignKey));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Endereco> retreaveAll() {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();
            String sql = "SELECT * FROM clientes_enderecos";
            ResultSet rs = stm.executeQuery(sql);

            ArrayList<Endereco> e = new ArrayList<>();

            while (rs.next()) {
                e.add(new Endereco(
                        rs.getString("logradouro"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getInt("pk_endereco"),
                        rs.getInt("fk_cliente")));
            }
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void delete(Endereco objetoEndereco, String tabela, String pk) {

        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "delete from "+tabela+" where "+pk+"="
                    + objetoEndereco.getPk_endereco();
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean update(Endereco objetoEndereco, String tabela, String pk) throws SQLException {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();
            //update ... set logradouro = 'Rua tal', bairo = 'kk'
            String sql = "update "+tabela+" set " +
                    "logradouro = '" + objetoEndereco.getLogradouro() +
                    "', bairro = '" + objetoEndereco.getBairro() +
                    "', cidade = '" + objetoEndereco.getCidade() +
                    "', estado = '" + objetoEndereco.getEstado() +
                    "', pais = '" + objetoEndereco.getPais() +
                    "', cep = '" + objetoEndereco.getCep() +
                    "' where "+pk+"="
                    + objetoEndereco.getPk_endereco();
            System.out.println(sql);
            if(stm.execute(sql)){
                return true;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao executar query: " + ex.getCause());
        }
        return false;
    }


}
