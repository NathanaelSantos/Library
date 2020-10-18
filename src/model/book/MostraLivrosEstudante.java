package model.book;

import model.connection.ConnectionFactory;
import model.book.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MostraLivrosEstudante {

    public List<Book> mostraLivrosEstudante(int idEstudante) {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Book> listLivro = new ArrayList();

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM tb_emprestimo E JOIN tb_emprestimo_livro L ON E.IdEmprestimo = L.IdEmp_Livro JOIN tb_livro T ON T.IdLivro = L.IdLivro WHERE E.IdEstudante = ?");
            preparedStatement.setInt(1, idEstudante);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Book livro = new Book();

                livro.setTitulo(resultSet.getString("Titulo"));
                livro.setNomeAutor(resultSet.getString("NomeAutorAutores"));
                livro.setNomeEditora(resultSet.getString("NomeEditora"));
                livro.setISBN(resultSet.getString("ISBN"));
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
