package empresa.model;

import empresa.controller.Endereco;
import empresa.controller.Fornecedor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marcio on 01/12/2016.
 */
public class FornecedorDAO {

    public FornecedorDAO() {

    }


    public static boolean create(Fornecedor f) throws Exception {
        Statement stm = BancoDados.createConnection().createStatement();
        try {
            String sql = "insert into fornecedores (nome, cpf)" +
                    "VALUES ('" + f.getNome() + "','" + f.getCpf() + "')";
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            f.setPk_fornecedor(rs.getInt(1));
            if (stm.execute(sql)) {
                return true;
            }
        } catch (Exception e) {
            throw new Exception("Erro ao executar query: " + e.getMessage());
        }
        return false;
    }

    public static boolean update(Fornecedor f) throws Exception {
        Statement stm = BancoDados.createConnection().createStatement();
        try {
            String sql = "update fornecedores set nome='" + f.getNome() + "', " +
                    "cpf='" + f.getCpf() + "'" +
                    "where pk_fornecedor=" + f.getPk_fornecedor();

            stm.execute(sql);
        } catch (Exception e) {
            throw new Exception("Erro ao executar query: " + e.getMessage());
        }
        return false;
    }

    public static Fornecedor retreave(int pk_fornecedor) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "select * from fornecedors where pk_fornecedor =" + pk_fornecedor;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();


            return new Fornecedor(pk_fornecedor,
                    rs.getString("nome"),
                    rs.getString("cpf"));
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    public static ArrayList<Fornecedor> retreaveAll() {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "SELECT * FROM fornecedores";

            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Fornecedor> cs = new ArrayList<>();
            while (rs.next()) {
                Endereco e = EnderecoDAO.retreaveBy("fornecedores_enderecos", "fk_fornecedor", rs.getInt("pk_fornecedor"));
                cs.add(new Fornecedor(rs.getInt("pk_fornecedor"), rs.getString("nome"), rs.getString("cpf"), e));
            }

            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean delete(Fornecedor f) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "delete from fornecedores where pk_fornecedor =" + f.getPk_fornecedor();
        stm.execute(sql);
        return false;
    }
}
