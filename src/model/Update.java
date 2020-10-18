package model;

import model.connection.ConnectionFactory;
import javax.swing.JOptionPane;
import java.sql.*;

public final class Update {

    public void update(String value, String id, String table) {
        
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            
            Date date = new Date(System.currentTimeMillis());
            
            preparedStatement = connection.prepareStatement(table);
            preparedStatement.setString(1, value);
            preparedStatement.setDate(2, date);
            preparedStatement.setString(3, id);
            
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement);
        }
    }
}
