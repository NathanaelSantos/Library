/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.connection.ConnectionFactory;

/**
 *
 * @author nathan
 */
public class EqaulEmprestimo {

    boolean check = false;

    public boolean equalEmprestimo(int idLivro, int idUser, String idNomeUser) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null, pStment1 = null;
        ResultSet resultSet = null;

        try {

            pStment = connection.prepareStatement("SELECT L.IdLivro, U." + idNomeUser + " FROM tb_emprestimo_livro AS L JOIN tb_emprestimo AS U ON L.IdEmp_Livro = U.IdEmprestimo WHERE U." + idNomeUser + " = ?");
            pStment.setInt(1, idUser);

            resultSet = pStment.executeQuery();

            while (resultSet.next()) {
                if (idLivro == resultSet.getInt("IdLivro")) {
                    check = true;
                    break;
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStment, resultSet);
        }

        return check;
    }

}
