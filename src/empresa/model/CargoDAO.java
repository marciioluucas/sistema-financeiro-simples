package empresa.model;

import empresa.controller.Cargo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marcio on 01/12/2016.
 */
public class CargoDAO {
    public CargoDAO() {

    }

    public static boolean create(Cargo c) throws Exception {
        Statement stm = BancoDados.createConnection().createStatement();
        try {
            String sql = "insert into cargos (nome, descricao)" +
                    "VALUES ('" + c.getNome() + "'," + c.getDescricao() + ")";
            ResultSet rs = stm.getGeneratedKeys();
            c.setPk_cargo(rs.getInt(1));
            if (stm.execute(sql)) {
                return true;
            }
        } catch (Exception e) {
            throw new Exception("Erro ao executar query: " + e.getMessage());
        }
        return false;
    }

    public static boolean update(Cargo c) throws Exception {
        Statement stm = BancoDados.createConnection().createStatement();
        try {
            String sql = "update cargos set nome='" + c.getNome() + "'," +
                    "descricao=" + c.getDescricao() + " " +
                    "where pk_cargo=" + c.getPk_cargo();

            stm.execute(sql);
        }catch(Exception e ){
            throw new Exception("Erro ao executar query: "+ e.getMessage());
        }
        return false;
    }
    public static Cargo retreave(int pk_cargo) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "select * from cargos where pk_cargo =" + pk_cargo;

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

            String sql = "SELECT * FROM cargos";

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

    public boolean delete(Cargo c ) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "delete from cargos where pk_cargo ="+c.getPk_cargo();
        stm.execute(sql);
        return false;
    }
}
