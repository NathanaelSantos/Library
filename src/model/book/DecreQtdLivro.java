package model.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.connection.ConnectionFactory;

public class DecreQtdLivro implements EmprestimoDevolucaoLivro {

    private int newValue;

    @Override
    public void auteraQuantidadeLivro(int idLivro) {

        QuantidadeLivro quantidadeLivro = new QuantidadeLivro();
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null;

        newValue = quantidadeLivro.getQuantLivro();

        try {
            pStment = connection.prepareStatement("UPDATE tb_livro SET Quantidade = ? WHERE IdLivro = ?");
            pStment.setInt(1, --newValue);
            pStment.setInt(2, idLivro);

            pStment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStment);
        }
    }
}
