
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.connection.ConnectionFactory;

public class EfetuaDevolucaoLivro {
    public boolean efetuaDevolucaoLivro(int idLivro){
        
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null;
        
         try {

            pStment = connection.prepareStatement("DELETE FROM tb_emprestimo WHERE IdEmprestimo = ? ");
            pStment.setInt(1, idLivro);
            
            pStment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStment);
        }
        
        return true;
    }
}
