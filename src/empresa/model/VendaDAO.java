package empresa.model;


import empresa.controller.Cliente;
import empresa.controller.Funcionario;
import empresa.controller.Item;
import empresa.controller.Venda;
import empresa.util.Datas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
                    "insert into vendas (fk_cliente, fk_vendedor, numero, datas) values (" + v.getCliente().getPk_cliente() + "," +
                            v.getVendedor().getPk_funcionario() + "," + retreaveLastSell() + ", '" + Datas.retornaData(new Date()) + "')";

            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);

            v.setPk_venda(key);
            try {
                ArrayList<Item> items = v.getItens();
                for (Item item : items) {
                    ItemDAO.create(item, "vendas_itens", "venda");
                }
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

    public static Venda retreave(int pk_cliente) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "select * from clientes where pk_cliente =" + pk_cliente;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            ArrayList<Item> i = ItemDAO.retreaveBy("vendas_itens", "venda",
                    "fk_venda = " + rs.getInt("pk_venda"));

            Cliente c = ClienteDAO.retreave(rs.getInt("fk_cliente"));

            Funcionario f = FuncionarioDAO.retreave(rs.getInt("fk_vendedor"));

            return new Venda(rs.getInt("pk_venda"), rs.getInt("numero"),
                    Datas.retornaDataTipoDateByString(rs.getString("datas")), c, f, i);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    public static ArrayList<Venda> retreaveAll() {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "SELECT * FROM clientes";

            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Venda> cs = new ArrayList<>();
            while (rs.next()) {
                ArrayList<Item> i = ItemDAO.retreaveBy("vendas_itens", "venda",
                        "fk_venda = " + rs.getInt("pk_venda"));

                Cliente c = ClienteDAO.retreave(rs.getInt("fk_cliente"));

                Funcionario f = FuncionarioDAO.retreave(rs.getInt("fk_vendedor"));

                cs.add(new Venda(rs.getInt("pk_venda"), rs.getInt("numero"),
                        Datas.retornaDataTipoDateByString(rs.getString("datas")), c, f, i));
            }

            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

//    public static boolean update(Venda v) throws SQLException {
//        try {
//            Statement stm = BancoDados.createConnection().createStatement();
//            String sql = "update clientes set nome='" + c.getNome() + "'," +
//                    "cpf ='" + c.getCpf() + "' where pk_cliente = " + c.getPk_cliente();
//            EnderecoDAO.update(c.getEndereco(), "clientes_enderecos", "pk_endereco");
//            stm.execute(sql);
//            return true;
//        } catch (SQLException e) {
//            throw new SQLException("Erro ao executar query: ", e.getCause());
//        }
//    }
//
//    public static boolean delete(Venda v) throws SQLException {
//        Statement stm = BancoDados.createConnection().createStatement();
//        String sql = "delete from clientes where pk_cliente =" + c.getPk_cliente();
//        stm.execute(sql);
//        EnderecoDAO.delete(c.getEndereco(), "clientes_enderecos", "pk_endereco");
//        return true;
//    }

    public static int retreaveLastSell() throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "SELECT * FROM vendas ORDER BY numero DESC LIMIT 1";

        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        return rs.getInt("numero");
    }

    public static boolean delete(Venda v) throws SQLException {
        try {
            Statement stm = BancoDados.createConnection().createStatement();
            String sql = "delete from vendas where pk_venda = " + v.getPk_venda();
            stm.execute(sql);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro na query: " + ex.getMessage());
        }
    }

}
