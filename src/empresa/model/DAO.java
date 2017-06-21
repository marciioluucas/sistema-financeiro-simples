package empresa.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IFGoiano on 05/12/2016.
 */
public class DAO {


    public static int contar(String tabela, String condicoes) throws SQLException{
        Statement stm = BancoDados.createConnection().createStatement();
        String sql;
        if(!condicoes.equals("")){
            sql = "select count(*) as contagem from "+tabela+" where "+condicoes;
        }else{
            sql = "select count(*) as contagem from "+tabela;
        }
        ResultSet rs = stm.executeQuery(sql);
        rs.next();

        return rs.getInt("contagem");
    }
}