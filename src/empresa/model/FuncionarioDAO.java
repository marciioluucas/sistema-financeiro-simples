package empresa.model;

import empresa.controller.Cargo;
import empresa.controller.Endereco;
import empresa.controller.Funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marcio on 02/12/2016.
 */
public class FuncionarioDAO {

    public static boolean create(Funcionario f) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        try {
            String sql = "insert into funcionarios (fk_cargo, nome, cpf)" +
                    "VALUES (" + f.getCargo().getPk_cargo() + ", '" + f.getNome() + "','" + f.getCpf() + "')";
            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            f.setPk_funcionario(key);
            try {
                EnderecoDAO.create(f.getEndereco(), "funcionarios_enderecos", "fk_funcionario", f.getPk_funcionario());
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

    public static boolean update(Funcionario f) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        try {
            String sql = "update funcionarios set nome='" + f.getNome() + "', " +
                    "cpf='" + f.getCpf() + "'" +
                    "where pk_funcionario=" + f.getPk_funcionario();
            stm.execute(sql);
            return true;
        } catch (Exception e) {
            throw new SQLException("Erro ao executar query: " + e.getMessage());
        }

    }

    public static Funcionario retreave(int pk_funcionario) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "select * from funcionarios where pk_funcionario =" + pk_funcionario;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            //TODO: fazer consulta do cargo aqui e fazer no cargo as parada de retreaveByFuncionario;

            return new Funcionario(pk_funcionario,
                    rs.getString("nome"),
                    rs.getString("cpf"));
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    public static ArrayList<Funcionario> retreaveAll() {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "SELECT * FROM funcionarios";

            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Funcionario> cs = new ArrayList<>();
            while (rs.next()) {
                Endereco e = EnderecoDAO.retreaveBy("funcionarios_enderecos", "fk_funcionario", rs.getInt("pk_funcionario"));
                Cargo c = CargoDAO.retreave(rs.getInt("fk_cargo"));
                cs.add(new Funcionario(
                        c,
                        rs.getInt("pk_funcionario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        e));
            }

            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean delete(Funcionario f) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "delete from funcionarios where pk_funcionario =" + f.getPk_funcionario();
        try {
            stm.execute(sql);
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro no delete, birl: " + e.getMessage());
        }
    }
}
