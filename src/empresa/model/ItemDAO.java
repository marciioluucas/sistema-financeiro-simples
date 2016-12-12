package empresa.model;

import empresa.controller.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by marcio on 07/12/2016.
 */
public class ItemDAO {
    ItemDAO() {
    }

    public static boolean create(Item i, String tabela, String origem) throws Exception {
        Statement stm = BancoDados.createConnection().createStatement();
        try {
            String sql = "insert into " + tabela + " (fk_" + origem + ", fk_produto, qtd, valor_unitario)" +
                    "VALUES (" + i.getFk_origem() + ", " + i.getProduto().getPk_produto() + ", " + i.getQtd() + ", " + i.getValorUnitario() + ")";
            stm.execute(sql);

            return true;

        } catch (Exception e) {
            throw new Exception("Erro ao executar query: " + e.getMessage());
        }
    }


    public static Item retreaveByPk(String origem, int valor_pk, String tabela) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "select * from " + tabela + " where pk_" + origem + " =" + valor_pk;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();


            return new Item(valor_pk, rs.getInt("qtd"), rs.getDouble("valor_unitario"),
                    ProdutoDAO.retreave(rs.getInt("fk_produto")), rs.getInt("fk_" + origem));
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    public static ArrayList<Item> retreaveBy(String tabela, String origem, String condicao) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "SELECT * FROM "+tabela+" WHERE "+condicao;

            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Item> cs = new ArrayList<>();
            while (rs.next()) {
                cs.add(new Item(rs.getInt("pk_"+origem), rs.getInt("qtd"), rs.getDouble("valor_unitario"),
                        ProdutoDAO.retreave(rs.getInt("fk_produto")), rs.getInt("fk_" + origem)));
            }

            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Item> retreaveAll(String tabela, String origem) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "SELECT * FROM tabela";

            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Item> cs = new ArrayList<>();
            while (rs.next()) {
                cs.add(new Item(rs.getInt("pk_"+origem), rs.getInt("qtd"), rs.getDouble("valor_unitario"),
                        ProdutoDAO.retreave(rs.getInt("fk_produto")), rs.getInt("fk_" + origem)));
            }

            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
