/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.model;

import empresa.controller.Cliente;
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
public class ClienteDAO {

    private ClienteDAO() {
    }

    public static boolean create(Cliente c) throws SQLException {

        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();


            String sql =
                    "insert into clientes (nome, cpf) values ('" +
                            c.getNome() + "','" +
                            c.getCpf() + "')";

            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            c.setPk_cliente(key);
            try {
                EnderecoDAO.create(c.getEndereco());
                return true;

            } catch (Exception e) {
                throw new Exception("Erro ao criar endereço. " + e.getMessage());
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na query: " + ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Cliente retreave(int pk_cliente) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "select * from clientes where pk_cliente =" + pk_cliente;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            Endereco e = EnderecoDAO.retreaveBy("clientes_enderecos","fk_cliente", pk_cliente);
            return new Cliente(pk_cliente,
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    e);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    public static ArrayList<Cliente> retreaveAll() {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "SELECT * FROM clientes";

            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Cliente> cs = new ArrayList<>();
            while (rs.next()) {
                Endereco e = EnderecoDAO.retreaveBy("clientes_enderecos","fk_cliente",rs.getInt("pk_cliente"));
                cs.add(new Cliente(
                        rs.getInt("pk_cliente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        e));
            }

            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    public static boolean update(Cliente c) throws SQLException {
        try {
            Statement stm = BancoDados.createConnection().createStatement();
            String sql = "update clientes set nome='" + c.getNome() + "'," +
                    "cpf ='" + c.getCpf() + "' where pk_cliente = " + c.getPk_cliente();
            EnderecoDAO.update(c.getEndereco());
            stm.execute(sql);
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao executar query: ", e.getCause());
        }
    }

    public static boolean delete(Cliente c) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "delete from clientes where pk_cliente =" + c.getPk_cliente();
        stm.execute(sql);
        EnderecoDAO.delete(c.getEndereco());
        return true;
    }


}
