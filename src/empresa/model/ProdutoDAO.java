package empresa.model;

import empresa.controller.Produto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marcio on 01/12/2016.
 */
public class ProdutoDAO {

    private ProdutoDAO() {
    }

    public static boolean create(Produto p) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        try {
            String sql = "insert into produtos (nome, estoque_minimo, qtd_estoque)" +
                    "VALUES ('" + p.getNome() + "'," + p.getEstoque_minimo() + "," +
                    "" + p.getQtd_estoque() + ")";
            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            p.setPk_produto(key);
            return true;
        } catch (Exception e) {
            throw new SQLException("Erro ao executar query: " + e.getMessage());
        }
    }

    public static boolean update(Produto p) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        try {
            String sql = "update produtos set nome='" + p.getNome() + "'," +
                    "estoque_minimo=" + p.getEstoque_minimo() + "," +
                    "qtd_estoque=" + p.getQtd_estoque() + " " +
                    "where pk_produto=" + p.getPk_produto();

            stm.execute(sql);
            return true;
        }catch(Exception e ){
            throw new SQLException("Erro ao executar query: "+ e.getMessage());
        }
    }
    public static Produto retreave(int pk_produto) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "select * from produtos where pk_produto =" + pk_produto;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();


            return new Produto(pk_produto,
                    rs.getString("nome"),
                    rs.getInt("estoque_minimo"),
                    rs.getInt("qtd_estoque"));
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    public static ArrayList<Produto> retreaveAll() {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "SELECT * FROM produtos";

            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Produto> cs = new ArrayList<>();
            while (rs.next()) {
                cs.add(new Produto(rs.getInt("pk_produto"),
                        rs.getString("nome"),
                        rs.getInt("estoque_minimo"),
                        rs.getInt("qtd_estoque")));
            }

            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean delete(Produto p ) throws SQLException {

        try {
            Statement stm = BancoDados.createConnection().createStatement();
            String sql = "delete from produtos where pk_produto ="+p.getPk_produto();
            stm.execute(sql);
            return true;
        }catch (SQLException ex) {
            throw new SQLException("Erro na execução da query: "+ ex.getMessage());
        }

    }
}
