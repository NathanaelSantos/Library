package model.check;

import java.sql.*;
import javax.swing.JOptionPane;
import message.MessageString;
import model.connection.ConnectionFactory;

public final class CheckRecPassword {

    private final String ID_USUARIO = "IdUsuario";
    private static int idUser;

    public boolean checkUser(String cpfUsuario, String dataNascimento) {

        boolean check = false;

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = connection.prepareStatement(MessageString.SELECT_FROM_TBUSER.getDescription());
            preparedStatement.setString(1, cpfUsuario);
            preparedStatement.setString(2, dataNascimento);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                idUser = resultSet.getInt(ID_USUARIO);
                check = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
        }
        return check;
    }

    public boolean insetNewPassword(String newPassword) {

        boolean check = false;
        
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStatement = null;

        try {

            pStatement = connection.prepareStatement(MessageString.UPDATE_TB_USER.getDescription());
            pStatement.setString(1, newPassword);
            pStatement.setInt(2, idUser);

            pStatement.executeUpdate();
            check = true;
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStatement);
        }
        
        return check;
    }
}
