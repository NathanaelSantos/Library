package model.user;

import java.sql.*;
import message.MessageString;
import model.connection.ConnectionFactory;

public class GetIdUserAdmin {

    private static int varIdUser;
    private final String ID_ADMIN = "IdAdmin";

    public int getIdUserAdmin(int idUsuarioAdmin) {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null;
        ResultSet resultSet = null;

        try {
            
            pStment = connection.prepareStatement(MessageString.GET_ADMIN.getDescription());
            pStment.setInt(1, idUsuarioAdmin);

            resultSet = pStment.executeQuery();

            while (resultSet.next()) {
                varIdUser = Integer.parseInt(resultSet.getString(ID_ADMIN));
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro");
        } finally {
            ConnectionFactory.closeConnection(connection, pStment, resultSet);
        }

        return varIdUser;
    }
}
