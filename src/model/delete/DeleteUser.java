package model.delete;

import java.sql.*;
import message.Message;
import message.MessageString;
import model.connection.ConnectionFactory;

public final class DeleteUser extends Message {

    boolean check = false;

    public boolean deleteUser(int idUser) {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement prepared = null;

        try {

            prepared = connection.prepareStatement(MessageString.DELETE_USER.getDescription());
            prepared.setInt(1, idUser);

            prepared.executeUpdate();
            check = true;

        } catch (SQLException ex) {
            System.out.println("Error when deleting user " + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, prepared);
        }
        return check;
    }
}
