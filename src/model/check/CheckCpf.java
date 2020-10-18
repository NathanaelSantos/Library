package model.check;

import java.sql.*;
import message.MessageString;
import model.connection.ConnectionFactory;

public final class CheckCpf {

    private boolean check = false;

    public boolean verificaCpfUsuario(String cpfUsuario) {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null;
        ResultSet resultSet = null;

        try {

            pStment = connection.prepareStatement(MessageString.SELECT_FROM_TB_USER.getDescription());
            pStment.setString(1, cpfUsuario);

            resultSet = pStment.executeQuery();

            if (resultSet.next()) {
                check = true;
            }
        } catch (java.sql.SQLException ex) {
        } finally {
            ConnectionFactory.closeConnection(connection, pStment, resultSet);
        }

        return check;
    }
}
