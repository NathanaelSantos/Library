package model.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.connection.ConnectionFactory;

public class QuantidadeLivro {
    
    private static int quantLivro;
    
    public int quantidadeLivro(int idLivro) {
       
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null;
        ResultSet resultSet = null;

        try {

            pStment = connection.prepareStatement("SELECT A.Quantidade FROM tb_livro AS A WHERE IdLivro = ?");
            pStment.setInt(1, idLivro);

            resultSet = pStment.executeQuery();

            while (resultSet.next()) {
                quantLivro = Integer.parseInt(resultSet.getString("Quantidade"));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStment, resultSet);
        }

        return getQuantLivro();
    }

    public int getQuantLivro() {
        return quantLivro;
    }
}
