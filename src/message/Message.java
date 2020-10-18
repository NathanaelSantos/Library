package message;

public class Message {

    private final String MESSAGE_CPF_INVALID = "Cpf is not valid!";
    private final String DELETE_USER = "Usuário deletado com sucesso!";
    private final String ERRO_DELETE_USER = "Não foi possível deletar usuário!";
    private final String DELETE_BOOK = "Livro deletado com sucesso!";
    private final String ERRO_DELETE_BOOK = "Não foi possível deletar o livro!";
    private final String ALERT_PASSWORD = "A senha dever ter 8 dígitos!";
    private final String ERRO_LOGIN = "Ocorreu um problema ao realizar o login do funcionário no sistema";
    private final String EMPTY_FIELD = "Algum campo está vazio!";
    private final String ICON_ATENCAO = "C:\\Users\\natha\\Music\\Library-master\\src\\Images\\icons8-atenção-35.png";
    private final String ICON_ERRO = "C:\\Users\\natha\\Documents\\Github\\Library\\src\\Images\\erro.png";
    private final String ADD_USER_LIST_WAIT = "Adicionar usuário à lista de espera?";
    private final String CPF_CAD = "Cpf já cadastrado!";
    private final String PASSWORD_MODIFIED = "Senha alterada com sucesso!";
    private final String ERRO_RECOVERY_PASSWORD = "Não foi possível redefinir a senha!";

    public String getMESSAGE_CPF_INVALID() {
        return MESSAGE_CPF_INVALID;
    }

    public String getDELETE_USER() {
        return DELETE_USER;
    }

    public String getERRO_DELETE_USER() {
        return ERRO_DELETE_USER;
    }

    public String getDELETE_BOOK() {
        return DELETE_BOOK;
    }

    public String getERRO_DELETE_BOOK() {
        return ERRO_DELETE_BOOK;
    }

    public String getALERT_PASSWORD() {
        return ALERT_PASSWORD;
    }

    public String getERRO_LOGIN() {
        return ERRO_LOGIN;
    }

    public String getEMPTY_FIELD() {
        return EMPTY_FIELD;
    }

    public String getICON_ERRO() {
        return ICON_ERRO;
    }
    
    public String getADD_USER_LIST_WAIT() {
        return ADD_USER_LIST_WAIT;
    }

    public String getICON_ATENCAO() {
        return ICON_ATENCAO;
    }
    
    public String getCPF_CAD() {
        return CPF_CAD;
    }

    public String getPASSWORD_MODIFIED() {
        return PASSWORD_MODIFIED;
    }

    public String getERRO_RECOVERY_PASSWORD() {
        return ERRO_RECOVERY_PASSWORD;
    }

}
