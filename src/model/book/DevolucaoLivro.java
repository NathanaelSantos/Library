package model.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.connection.ConnectionFactory;

public class DevolucaoLivro implements EmprestimoDevolucaoLivro {

    int newValue;
    private String dataEmp;
    private String dataDev;
    private String dataPrev;
    private int idEmp;
    
    @Override
    public void auteraQuantidadeLivro(int idLivro) {
        
        QuantidadeLivro quantidadeLivro = new QuantidadeLivro();
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pStment = null;

        newValue = quantidadeLivro.getQuantLivro();

        try {
            
            pStment = connection.prepareStatement("UPDATE tb_livro SET Quantidade = ? WHERE IdLivro = ?");
            pStment.setInt(1, ++newValue);
            pStment.setInt(2, idLivro);

            pStment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, pStment);
        }
    }

    public String getDataEmp() {
        return dataEmp;
    }

    public void setDataEmp(String dataEmp) {
        this.dataEmp = dataEmp;
    }

    public String getDataDev() {
        return dataDev;
    }

    public void setDataDev(String dataDev) {
        this.dataDev = dataDev;
    }

    public String getDataPrev() {
        return dataPrev;
    }

    public void setDataPrev(String dataPrev) {
        this.dataPrev = dataPrev;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

}
