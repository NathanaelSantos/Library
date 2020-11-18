package model.connection;

import java.sql.*;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import message.Message;

public final class ConnectionFactory extends Message {

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3301/biblioteca",
                    "root",
                    "");

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexão: não foi possível conectar ao banco de dados!");
            System.exit(0);
            throw new RuntimeException("Connection erro!: ", ex);
        }
    }

    public static void closeConnection(Connection connection) {

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement pStment) {

        closeConnection(connection);
        try {
            if (pStment != null) {
                pStment.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement pStment, ResultSet resultSet) {

        closeConnection(connection, pStment);
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
