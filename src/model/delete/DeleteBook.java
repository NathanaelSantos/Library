package model.delete;

import java.sql.*;
import model.connection.ConnectionFactory;

public class DeleteBook {

    private boolean check = false;

    public boolean deletalivros(Object idLivro) {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement prepared = null;
       
        try {
            
            prepared = connection.prepareStatement("DELETE FROM tb_livro WHERE IdLivro = ?");
            prepared.setObject(1, idLivro);

            prepared.executeUpdate();
            check = true;

        } catch (SQLException ex) {
            System.out.println("Error when deleting book! " + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, prepared);
        }
        return check;
    }
}
