package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.check.CheckUser;
import model.connection.ConnectionFactory;
import model.user.User;

public class GetUserEmprestimoBooks extends User {

    public List<GetUserEmprestimoBooks> getUserEmprestimoBooks(String cpfUser) {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null, pStment1 = null;
        ResultSet resultSet = null;
       
        List<GetUserEmprestimoBooks> listDadosDev = new ArrayList();
        
        CheckUser CheckUser = new CheckUser();

        try {

            pStment = connection.prepareStatement("SELECT U.NomeUsuario, U.CpfUsuario, L.ISBN, J.Multa, J.DataEmprestimo, J.DataPrevista, J.DataDevolucao, T.IdEmprestimo FROM tb_usuario AS U JOIN tb_papel AS P ON U.IdUsuario = P.IdUsuario JOIN tb_estudante AS E ON P.IdUsuario = E.IdUsuario JOIN tb_emprestimo AS J ON E.IdEstudante = J.IdEstudante JOIN tb_emprestimo_livro AS T ON J.IdEmprestimo = T.IdEmp_Livro JOIN tb_livro AS L ON T.IdLivro = L.IdLivro WHERE U.IdUsuario = ?");
            pStment.setInt(1, CheckUser.idUser());
            
            System.out.println(CheckUser.idUser());

            resultSet = pStment.executeQuery();

            while (resultSet.next()) {

                GetUserEmprestimoBooks dadosDevolucao = new GetUserEmprestimoBooks();

                dadosDevolucao.setNome(resultSet.getString("NomeUsuario"));
                dadosDevolucao.setCpf(resultSet.getString("CpfUsuario"));
                dadosDevolucao.setISBN(resultSet.getString("ISBN"));
                dadosDevolucao.setDataEmp(resultSet.getString("DataEmprestimo"));
                dadosDevolucao.setDataPrev(resultSet.getString("DataPrevista"));
                dadosDevolucao.setDataDev(resultSet.getString("DataDevolucao"));       
                dadosDevolucao.setIdEmp(resultSet.getInt("IdEmprestimo"));
                
                
               
                if (!(resultSet.getDate("DataDevolucao") == null)) { 
                    
                    long day = (resultSet.getDate("DataDevolucao").getTime() - resultSet.getDate("DataPrevista").getTime()) + 3600000; // 1 hora para compensar horário de verão
                    dadosDevolucao.setMulta(day / 86400000L);
                    
                    pStment1 = connection.prepareStatement("UPDATE tb_emprestimo SET Multa = ? WHERE IdEmprestimo = " +  resultSet.getInt("IdEmprestimo"));
                    pStment1.setFloat(1, day / 86400000L);
                    
                    pStment1.executeUpdate();
                   
                }

                listDadosDev.add(dadosDevolucao);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStment, resultSet);
        }

        return listDadosDev;
    }
}
