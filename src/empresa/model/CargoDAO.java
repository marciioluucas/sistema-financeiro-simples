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
public class CargoDAO extends DAO {
    public CargoDAO() {

    }

    public static boolean create(Cargo c) throws Exception {
        Statement stm = BancoDados.createConnection().createStatement();
        try {
            String sql = "insert into cargo (nome, descricao)" +
                    "VALUES ('" + c.getNome() + "', '" + c.getDescricao() + "')";
            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            c.setPk_cargo(key);
            return true;

        } catch (Exception e) {
            throw new Exception("Erro ao executar query: " + e.getMessage());
        }
    }

    public static boolean update(Cargo c) throws Exception {
        Statement stm = BancoDados.createConnection().createStatement();
        try {
            String sql = "update cargo set nome='" + c.getNome() + "'," +
                    "descricao='" + c.getDescricao() + "' " +
                    "where pk_cargo=" + c.getPk_cargo();

            stm.execute(sql);
        } catch (Exception e) {
            throw new Exception("Erro ao executar query: " + e.getMessage());
        }
        return false;
    }

    public static Cargo retreave(int pk_cargo) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "select * from cargo where pk_cargo =" + pk_cargo;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();


            return new Cargo(pk_cargo,
                    rs.getString("nome"),
                    rs.getString("descricao"));
        } catch (SQLException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    public static ArrayList<Cargo> retreaveAll() {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "SELECT * FROM cargo";

            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Cargo> cs = new ArrayList<>();
            while (rs.next()) {
                cs.add(new Cargo(rs.getInt("pk_cargo"),
                        rs.getString("nome"),
                        rs.getString("descricao")));
            }

            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean delete(Cargo c) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "delete from cargo where pk_cargo =" + c.getPk_cargo();
        stm.execute(sql);
        return false;
    }
}
