package model.cadastra;

import java.sql.*;
import message.MessageString;
import model.connection.ConnectionFactory;

public class CadastraEmprestimo_Livro {

    public void lendingBooks(String status, int idBook, int idLending) {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null;

        try {

            pStment = connection.prepareStatement(MessageString.INSERT_TB_LIVRO.getDescription());
            pStment.setString(1, status);
            pStment.setInt(2, idBook);
            pStment.setInt(3, idLending);

            pStment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error when registering Loan: " + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStment);
        }
    }
}
