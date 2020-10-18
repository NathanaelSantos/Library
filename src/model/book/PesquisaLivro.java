package model.book;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import model.connection.ConnectionFactory;

public class PesquisaLivro {

    public List<Book> pesquisaLivro(String titulo) {
       
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Book> listLivro = new ArrayList();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM tb_livro WHERE Titulo LIKE ?");
            preparedStatement.setString(1, titulo + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Book livro = new Book();

                livro.setTitulo(resultSet.getString("Titulo"));
                livro.setNomeAutor(resultSet.getString("NomeAutorAutores"));
                livro.setNomeEditora(resultSet.getString("NomeEditora"));
                livro.setISBN(resultSet.getString("ISBN"));
                livro.setQuantidade(resultSet.getShort("Quantidade"));
                livro.setId(resultSet.getInt("IdLivro"));

                listLivro.add(livro);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
        }
        return listLivro;
    }
}
