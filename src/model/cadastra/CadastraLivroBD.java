package model.cadastra;

import java.sql.*;
import message.MessageString;
import model.connection.ConnectionFactory;
import model.book.Book;

public class CadastraLivroBD {

    public boolean cadastrarLivroBD(Book book) {
        
        boolean check = false;
        
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null;

        try {
            
            pStment = connection.prepareStatement(MessageString.INSERT_BOOK_TB_LIVRO.getDescription());

            pStment.setInt(1, book.getAno());
            pStment.setFloat(2, book.getPesoLivro());
            pStment.setString(3, book.getNomeAutor());
            pStment.setObject(4, book.getTitulo());
            pStment.setString(5, book.getNomeEditora());
            pStment.setString(6, book.getIdioma());
            pStment.setString(7, book.getPais());
            pStment.setString(8, book.getISBN());
            pStment.setInt(9, book.getNumeroPaginas());
            pStment.setInt(10, book.getQuantidade());

            pStment.executeUpdate();
            check = true;

        } catch (SQLException ex) {
            throw new RuntimeException("It was not possible to register the book!" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStment);
        }        
        return check;
    }
}
