package model.cadastra;

import View.LoginScreen;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.Date;
import model.book.EqaulEmprestimo;
import model.connection.ConnectionFactory;
import model.user.GetIdUserAdmin;
import model.book.QuantidadeEmprestimo;

public class CadastraEmprestimo {

    private final int MULTA = 0;
    private int idEstudante;
    private int idProfessor;
    boolean check = false;
    String professor = null;
    String estudante = null;
    int idEmprestimo = 0;

    public boolean cadastraEmprestimo(int idUser, int idLivro) {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null;
        ResultSet resultSet = null;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 10);

        try {

            pStment = connection.prepareStatement("SELECT B.IdEstudante, P.IdProfessor, A.Estudante, A.Professor FROM tb_papel AS A INNER JOIN tb_estudante AS B INNER JOIN tb_professor AS P WHERE A.IdUsuario =?");
            pStment.setInt(1, idUser);
            resultSet = pStment.executeQuery();

            while (resultSet.next()) {

                professor = resultSet.getString("Professor");
                estudante = resultSet.getString("Estudante");
                idEstudante = Integer.parseInt(resultSet.getString("IdEstudante"));
                idProfessor = Integer.parseInt(resultSet.getString("IdProfessor"));
            }

            if (estudante != null) {
                if (new EqaulEmprestimo().equalEmprestimo(idLivro, idEstudante, "idEstudante")) {
                    JOptionPane.showMessageDialog(null, "O estudante já pegou emprestado este livro!");
                } else {
                    try {

                        pStment = connection.prepareStatement("INSERT INTO tb_emprestimo(DataPrevista, DataDevolucao, Multa, IdEstudante, IdAdmin) VALUES (?,?,?,?,?)");
                        pStment.setDate(1, new java.sql.Date(cal.getTime().getTime()));
                        pStment.setDate(2, null);
                        pStment.setInt(3, MULTA);
                        pStment.setInt(4, idEstudante);
                        pStment.setInt(5, new GetIdUserAdmin().getIdUserAdmin(new LoginScreen().getIdUserAdmin()));

                        if (new QuantidadeEmprestimo().quantidadeEmprestimo(idEstudante, "IdEstudante") < 5) {
                            pStment.executeUpdate();

                            pStment = connection.prepareStatement("SELECT last_insert_id()");
                            resultSet = pStment.executeQuery();

                            while (resultSet.next()) {
                                idEmprestimo = (int) resultSet.getInt("last_insert_id()");
                            }
                            new CadastraEmprestimo_Livro().lendingBooks("Ativo", idLivro, idEmprestimo);
                            check = true;

                        } else {
                            JOptionPane.showMessageDialog(null, "Quantidade máxima de empréstimo realizada!");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } finally {
                        ConnectionFactory.closeConnection(connection, pStment, resultSet);
                    }
                }
            } else if (professor != null) {
                if (new EqaulEmprestimo().equalEmprestimo(idLivro, idProfessor, "idProfessor")) {
                    JOptionPane.showMessageDialog(null, "O professor já pegou emprestado este livro!");
                } else {
                    try {

                        pStment = connection.prepareStatement("INSERT INTO tb_emprestimo(DataPrevista, DataDevolucao, Multa, IdProfessor, IdAdmin) VALUES (?,?,?,?,?)");
                        pStment.setDate(1, new java.sql.Date(cal.getTime().getTime()));
                        pStment.setDate(2, null);
                        pStment.setInt(3, MULTA);
                        pStment.setInt(4, idProfessor);
                        pStment.setInt(5, new GetIdUserAdmin().getIdUserAdmin(new LoginScreen().getIdUserAdmin()));

                        if (new QuantidadeEmprestimo().quantidadeEmprestimo(idProfessor, "IdProfessor") < 5) {
                            pStment.executeUpdate();

                            pStment = connection.prepareStatement("SELECT last_insert_id()");
                            resultSet = pStment.executeQuery();

                            while (resultSet.next()) {
                                idEmprestimo = (int) resultSet.getInt("last_insert_id()");
                            }
                            new CadastraEmprestimo_Livro().lendingBooks("Ativo", idLivro, idEmprestimo);
                            check = true;

                        } else {
                            JOptionPane.showMessageDialog(null, "Quantidade máxima de empréstimo realizada!");
                        }

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } finally {
                        ConnectionFactory.closeConnection(connection, pStment, resultSet);
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStment, resultSet);
        }
        return check;
    }
}
