package model.check;

import java.sql.*;
import message.MessageString;
import model.connection.ConnectionFactory;

public class CheckUser {

    private final String MULTA_TOTAL = "MultaTotal";
    private final String NAME_USER = "NomeUsuario";
    private final String ID_USER = "IdUsuario";
    
    private static String nomeUsuario;
    private static int valorMulta;
    private static int idUser = 0;
    private boolean check = false;
   
    
   
    public boolean verificaUsuario(String cpfUsuario, String senhaUsuario, String usuario) {
       
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null;
        ResultSet resultSet = null;

        try {
           
            pStment = connection.prepareStatement(MessageString.CHECK_USER.getDescription());
            pStment.setString(1, cpfUsuario);
            pStment.setString(2, senhaUsuario);

            resultSet = pStment.executeQuery();

            if (resultSet.next()) {
                
                idUser = resultSet.getInt(ID_USER);
                nomeUsuario = resultSet.getString(NAME_USER);
                check = true;

                pStment = connection.prepareStatement("SELECT E." + usuario + ", U.MultaTotal FROM tb_papel AS E INNER JOIN tb_usuario AS U ON E.IdUsuario = U.IdUsuario WHERE U.IdUsuario = ?");
                pStment.setInt(1, idUser);
               
                resultSet = pStment.executeQuery();

                if (resultSet.next()) {
                    check = resultSet.getBoolean(usuario);
                    valorMulta = resultSet.getInt(MULTA_TOTAL);
                }else{
                    check = false;
                }
            } 
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStment, resultSet);
        }
        
        return check;
    }

    public String nomeUsuario() {
        return nomeUsuario;
    }

    public int valorMulta() {
        return valorMulta;
    }

    public int idUser() {
        return idUser;
    }
}
