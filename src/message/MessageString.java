package message;

public enum MessageString {

    ERRO_LOGIN("Ocorreu um problema ao realizar o login do funcionário no sistema!"),
    
    ERRO_CONNECTION("Erro de conexão: não foi possível conectar ao servidor!"),
    
    SELECT_FROM_TB_USER("SELECT * FROM tb_usuario WHERE CpfUsuario = ?"),
    
    SELECT_FROM_TBUSER("SELECT * FROM tb_usuario WHERE CpfUsuario = ? and DataNascUsuario = ?"),
    
    UPDATE_TB_USER("UPDATE tb_usuario SET SenhaUsuario = ? WHERE IdUsuario = ?"),
    
    CHECK_USER("SELECT * FROM tb_usuario WHERE CpfUsuario = ? and SenhaUsuario = ?"),
    
    DELETE_USER("DELETE FROM tb_usuario WHERE IdUsuario = ?"),
    
    GET_ADMIN("SELECT * FROM tb_admin WHERE IdUsuario = ?"),
    
    INSERT_TB_LIVRO("INSERT INTO tb_emprestimo_livro(Status, IdLivro, IdEmprestimo) VALUES (?,?,?)"),
    
    INSERT_BOOK_TB_LIVRO("INSERT INTO tb_livro(Ano, PesoLivro, NomeAutorAutores, Titulo, NomeEditora, Idioma, Pais, ISBN, NumeroPagina, Quantidade) VALUES (?,?,?,?,?,?,?,?,?,?)");

    private final String description;

    private MessageString(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
