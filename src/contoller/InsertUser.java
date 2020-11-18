package contoller;

import java.sql.*;
import model.connection.ConnectionFactory;
import javax.swing.JOptionPane;
import model.user.InsertUserBD;

public class InsertUser implements InsertUserBD {

    int x = 10;
    @Override
    public void insertStudentBD(model.pessoa.Estudante stdent) {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null;
        ResultSet resultSet = null;

        try {

            pStment = connection.prepareStatement("INSERT INTO tb_usuario(NomeUsuario,CpfUsuario,SenhaUsuario,DataNascUsuario,TelefoneUsuario,MultaTotal) VALUES (?,?,?,?,?,?)");

            pStment.setString(1, stdent.getNome());
            pStment.setString(2, stdent.getCpf());
            pStment.setString(3, stdent.getPassword());
            pStment.setString(4, stdent.getDataNacimento());
            pStment.setString(5, stdent.getTelefone());
            pStment.setDouble(6, stdent.getMultaTotal());
            pStment.executeUpdate();

            pStment = connection.prepareStatement("SELECT * FROM tb_usuario WHERE CpfUsuario = ?");
            pStment.setString(1, stdent.getCpf());

            int idUsuario = 0;
            resultSet = pStment.executeQuery();

            if (resultSet.next()) {
                idUsuario = resultSet.getInt("IdUsuario");
            }

            pStment = connection.prepareStatement("INSERT INTO tb_papel(IdUsuario,Estudante)VALUES(?,?)");
            pStment.setInt(1, idUsuario);
            pStment.setBoolean(2, true);
            pStment.executeUpdate();

            pStment = connection.prepareStatement("INSERT INTO tb_estudante(MatriculaEstudante, IdUsuario)VALUES(?,?)");
            pStment.setString(1, stdent.getMatriculaEstudante());
            pStment.setInt(2, idUsuario);
            pStment.executeUpdate();

            JOptionPane.showMessageDialog(null, "Estudante cadastrado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar estudante!" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStment, resultSet);
        }
        
    }

    @Override
    public void insertProfessorBD(model.pessoa.Professor pfssor) {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null;
        ResultSet resultSet = null;

        try {
            pStment = connection.prepareStatement("INSERT INTO tb_usuario(NomeUsuario,CpfUsuario,SenhaUsuario,DataNascUsuario,TelefoneUsuario,MultaTotal) VALUES (?,?,?,?,?,?)");

            pStment.setString(1, pfssor.getNome());
            pStment.setString(2, pfssor.getCpf());
            pStment.setString(3, pfssor.getPassword());
            pStment.setString(4, pfssor.getDataNacimento());
            pStment.setString(5, pfssor.getTelefone());
            pStment.setDouble(6, pfssor.getMultaTotal());
            pStment.executeUpdate();

            pStment = connection.prepareStatement("SELECT * FROM tb_usuario WHERE CpfUsuario = ?");
            pStment.setString(1, pfssor.getCpf());

            int idUsuario = 0;
            resultSet = pStment.executeQuery();

            if (resultSet.next()) {
                idUsuario = resultSet.getInt("IdUsuario");
            }

            pStment = connection.prepareStatement("INSERT INTO tb_papel(IdUsuario,Professor)VALUES(?,?)");
            pStment.setInt(1, idUsuario);
            pStment.setBoolean(2, true);
            pStment.executeUpdate();

            pStment = connection.prepareStatement("INSERT INTO tb_professor(MatriculaProfessor, IdUsuario)VALUES(?,?)");
            pStment.setString(1, pfssor.getMatriculaProfessor());
            pStment.setInt(2, idUsuario);
            pStment.executeUpdate();

            JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar professor!" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStment, resultSet);
        }
    }
}
