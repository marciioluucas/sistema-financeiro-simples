package empresa.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IFGoiano on 01/12/2016.
 */
public class ImovelDAO extends DAO{

    private ImovelDAO() {
    }

    public static boolean create(Imovel i) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        try {
            String sql = "insert into imovel (quartos, banheiros, tipo, garagens, status)" +
                    "VALUES ('" + i.getQuartos() + "'," + i.getBanheiros() + "," +
                    "'" + i.getTipo() + "', '" + i.getGaragens() + "', '" + i.getStatus() + "')";
            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            i.setPk_imovel(key);
            try {
                EnderecoDAO.create(i.getEndereco(),"imovel_endereco","fk_imovel", i.getPk_imovel());
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

    public static boolean update(Imovel i) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        try {
            String sql = "update imovel set quartos='" + i.getQuartos() + "'," +
                    "banheiros=" + i.getBanheiros() + "," +
                    "tipo=" + i.getTipo() + "," +
                    "garagens=" + i.getGaragens() + "," +
                    "status=" + i.getStatus() + " " +
                    "where pk_imovel=" + i.getPk_imovel();

            stm.execute(sql);
            return true;
        } catch (Exception e) {
            throw new SQLException("Erro ao executar query: " + e.getMessage());
        }
    }

    public static Imovel retreave(int pk_imovel) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "select * from imovel where pk_imovel =" + pk_imovel;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            Endereco e = EnderecoDAO.retreaveBy("imovel_endereco","fk_imovel", rs.getInt("pk_imovel"));
            return new Imovel(pk_imovel,
                    rs.getInt("quartos"),
                    rs.getInt("banheiros"),
                    rs.getString("tipo"),
                    rs.getInt("garagens"),
                    rs.getString("status"),
                    e);
        } catch (SQLException ex) {
            Logger.getLogger(ImovelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    public static ArrayList<Imovel> retreaveAll() {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "SELECT * FROM imovel";

            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Imovel> cs = new ArrayList<>();

            while (rs.next()) {
                Endereco e = EnderecoDAO.retreaveBy("imovel_endereco","fk_imovel", rs.getInt("pk_imovel"));
                cs.add(new Imovel(rs.getInt("pk_imovel"),
                        rs.getInt("quartos"),
                        rs.getInt("banheiros"),
                        rs.getString("tipo"),
                        rs.getInt("garagens"),
                        rs.getString("status"),
                        e));
            }

            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(ImovelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean delete(Imovel i) throws SQLException {

        try {
            Statement stm = BancoDados.createConnection().createStatement();
            String sql = "delete from imovel where pk_imovel =" + i.getPk_imovel();
            stm.execute(sql);
            try{
                EnderecoDAO.delete(i.getEndereco(),"imovel_endereco");
            } catch (Exception e) {
                throw new SQLException("Erro ao tentar excluir o endereço do imovel: ", e.getMessage());
            }
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao executar query: ", e.getCause());
        }
    }
}
