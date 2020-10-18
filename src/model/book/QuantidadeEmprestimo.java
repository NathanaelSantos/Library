package model.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.connection.ConnectionFactory;

public class QuantidadeEmprestimo {

    Connection connection = ConnectionFactory.getConnection();
    PreparedStatement pStment = null;
    ResultSet resultSet = null;

    private int quantity = 0;

    public int quantidadeEmprestimo(int idUser, String tipoUser) {

        try {
       
            pStment = connection.prepareStatement("SELECT A." + tipoUser + " FROM tb_emprestimo AS A WHERE " + tipoUser + " = ?");
            pStment.setInt(1, idUser);

            resultSet = pStment.executeQuery();

            while (resultSet.next()) {
                quantity++;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStment, resultSet);
        }

        return quantity;
    }
}
