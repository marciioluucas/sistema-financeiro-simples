package empresa.model;

import empresa.controller.Cliente;
import empresa.controller.Endereco;
import empresa.controller.Venda;
import empresa.util.Datas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by marci on 07/12/2016.
 */
public class VendaDAO {
    private VendaDAO() {

    }

    public static boolean create(Venda v) throws SQLException {

        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();


            String sql =
                    "insert into vendas (fk_cliente, fk_vendedor, numero, datas) values ("+v.getCliente().getPk_cliente()+"," +
                            v.getVendedor().getPk_funcionario() + ","+retreaveLastSell()+", '"+ Datas.retornaData(new Date())+"')";

            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);

            v.setPk_venda(key);
            try {
                EnderecoDAO.create(c.getEndereco(),"clientes_enderecos","fk_cliente", c.getPk_cliente());
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

    public static boolean update(Venda v) throws SQLException {
        try {
            Statement stm = BancoDados.createConnection().createStatement();
            String sql = "update clientes set nome='" + c.getNome() + "'," +
                    "cpf ='" + c.getCpf() + "' where pk_cliente = " + c.getPk_cliente();
            EnderecoDAO.update(c.getEndereco(),"clientes_enderecos","pk_endereco");
            stm.execute(sql);
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao executar query: ", e.getCause());
        }
    }

    public static boolean delete(Venda v) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "delete from clientes where pk_cliente =" + c.getPk_cliente();
        stm.execute(sql);
        EnderecoDAO.delete(c.getEndereco(),"clientes_enderecos","pk_endereco");
        return true;
    }

    public static int retreaveLastSell() throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select * from vendas order by numero desc limit 1";

        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        return rs.getInt("numero");
    }

}
