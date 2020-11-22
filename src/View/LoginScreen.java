package View;

import contoller.ControleEmprestimo;
import contoller.InsertUser;
import contoller.XamppExec;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.check.CheckUser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import message.Message;
import model.EfetuaDevolucaoLivro;
import model.FrameDragList;
import model.GetUserEmprestimoBooks;
import model.GetWeekday;
import model.book.Book;
import model.book.PesquisaLivro;
import model.cadastra.CadastraLivroBD;
import model.check.CheckCpf;
import model.check.CheckRecPassword;
import model.delete.DeleteBook;
import model.delete.DeleteUser;
import model.pessoa.Estudante;
import model.pessoa.Professor;
import model.validate.ValidateCPF;

public final class LoginScreen extends javax.swing.JFrame {

    private final LineBorder lineBordeON = new LineBorder(new Color(255, 255, 0), 2, true);
    LineBorder lineBordeOFF;
    final int COLUMN_ID_USUARIO = 4;
    private Timer time;
    private static int idUserAdmin;
    private static boolean estado = false;
    public String novaSenha, confSenha;
    private static String user;
    private final Color newColor = new Color(99, 99, 132);
    XamppExec xamppExec = new XamppExec();

    CheckUser verificaUsuario = new CheckUser();
    Message message = new Message();

    GetWeekday getWeekday = new GetWeekday();

    public LoginScreen() {

        initComponents();
        jFormattedTextFieldCpfUsuarioLogin.setCaretPosition(0);
        
        searchInput(pesquisaLivro.getText(), jTableBookEmprestimo);

        // Panels
        jPanelLoginScreen(false);
        jPanelTelaDeletaUsuario(false);
        jPanelTelaDeletarBooks(false);
        jPanelTelaLoginFuncionario(false);
        jPanelTelaEmprestimo(false);
        jPanelTelaProfessor(false);
        jPanelEstudante(false);
        jPanelTelaDevolucao(false);
        jPanelTelaCadastraProfessor(false);
        desabledjPanelCadastraEstudante(false);
        jPanelTelaCadastraLivros(false);
        jPanelTelaTecAdmin(false);
        jPanelTelaRecuperarSenha(false);

        loadProgressBar();
        minimizeClose();
        FrameDragList();

        // Button back
        jLabelBackTelaEmprestimo.setVisible(false);
        jLabelBack3.setVisible(false);
        jLabelBackTelaAdmin.setVisible(false);
        jLabelBackTelaDeletaUsuario.setVisible(false);
        jLabelBackTelaCadastroLivros.setVisible(false);
        jLabelBackTelaEstudante.setVisible(false);
        jLabelBackVisiblejPanelTelaEstudante.setVisible(false);
        jLabelBackTelaProfessor.setVisible(false);
        jLabelMinimizeTelaDeleteLivros.setVisible(false);
        jLabelCloseTelaDeleteLivros.setVisible(false);
        jLabelBackTelaDeleteLivros.setVisible(false);
        jLabelBackTelaDevolucao.setVisible(false);

        // minimize and close
        jLabelMinimizejPanelTelaDevolucao.setVisible(false);
        jLabelClosejPanelTelaDevolucao.setVisible(false);
        minimizeTelaUsuario.setVisible(false);
        jLabelMinimizejPanelTelaEstudante.setVisible(false);
        jLabelClosejPanelTelaEstudante.setVisible(false);
        closeDeletarUsuario.setVisible(false);
        minimizeTelaCadEstudante.setVisible(false);
        closeTelaCadEstudante.setVisible(false);
        minimizeTelaCadProfessor.setVisible(false);
        closeTelaCadProfessor.setVisible(false);
        minimizeTelaAdmin.setVisible(false);
        closeTelaAdmin.setVisible(false);

        // Messages alerts
        jLabelImageOK.setVisible(false);
        jLabelTextoOK.setVisible(false);
        jButtonOkRecuperarSenha.setVisible(false);
        jProgressBar1.setBorder(null);
        jLabelAlertCpfFuncionario.setVisible(false);
        jLabelAlertSenhaFuncionario.setVisible(false);
        jLabelImageErro.setVisible(false);
        jButtonRecuperarSenhaPanelErro.setVisible(false);
        alertCpfRecuperaSenha.setVisible(false);
        alertDataNascimentoRecuperaSenha.setVisible(false);
        alertNewPasswordRecuperaSenha.setVisible(false);
        alertConfPasswordRecuperaSenha.setVisible(false);
        jLabelAlertCpfUsuarioLogin.setVisible(false);
        jLabelAlertSenhaLogin.setVisible(false);
        alertNewPasswordRecuperaSenha.setVisible(false);

        alertjPanelRecuperaSenha(false, false);

        UIManager.getDefaults().put("OptionPane.background", new Color(255, 85, 114));
        UIManager.put("Panel.background", new Color(255, 85, 114));
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 13)));
    }
   

    private void FrameDragList() {

        FrameDragList.FrameDragListener frameDragListener = new FrameDragList.FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);

        setLocation(frameDragListener.getX(), frameDragListener.getY());
    }

    private void jPanel_splashScreen(boolean ativa) {

        jPanel_splashScreen.setVisible(ativa);
        jPanel_splashScreen.setEnabled(ativa);

        jLabelImageSplashScreen.setVisible(ativa);
        jProgressBar1.setVisible(ativa);
    }

    // Buttons Minimize and close
    private void minimizeClose() {

        //jPanel_loginScreen
        minimizeTelaLogin.setVisible(false);
        closeTelaLogin.setVisible(false);

        //jPanel_estudante
        jLabelMinimizejPanelTelaEstudante.setVisible(false);
        jLabelClosejPanelTelaEstudante.setVisible(false);

        //jPanel_telaEmprestimo
        minimizeTelaEmprestimo.setVisible(false);
        closeTelaEmprestimo.setVisible(false);

        //jPanel_telaProfessor
        minimizeTelaProfessor.setVisible(false);
        closeTelaProfessor.setVisible(false);

        //jPanel_telaRecuperarSenha
        minimizeTelaRecuperaSenha.setVisible(false);
        closeTelaRecuperaSenha.setVisible(false);

        //jPanel_telaLoginFuncionario
        minimizeTelaLoginFuncionario.setVisible(false);
        closeTelaLoginFuncionario.setVisible(false);

        //jPanel_cadastraEstudante
        minimizeTelaCadEstudante.setVisible(false);
        closeTelaCadEstudante.setVisible(false);

        //jPanel_TelaTecAdmin
        minimizeTelaAdmin.setVisible(false);
        closeTelaAdmin.setVisible(false);

        //jPanel_TelaTecAdmin
        minimizeTelaCadLivros.setVisible(false);
        closeTelaCadAdmin.setVisible(false);
    }

    private void loadProgressBar() {

        ActionListener action = (ActionEvent e) -> {
            if (jProgressBar1.getValue() < 100) {
                jProgressBar1.setValue(jProgressBar1.getValue() + 1);
            } else {

                time.stop();
                jPanel_splashScreen(false);
                jPanelTelaLoginFuncionario(true);

            }
        };

        time = new Timer(50, action);
        time.start();
    }

    private void searchInput(String title, JTable jTableName) {

        DefaultTableModel model = (DefaultTableModel) jTableName.getModel();
        model.setRowCount(0);

        new PesquisaLivro().pesquisaLivro(title).forEach((livro) -> {
            model.addRow(new Object[]{
                livro.getTitulo(),
                livro.getNomeAutor(),
                livro.getISBN(),
                livro.getQuantidade(),
                livro.getId()
            });
        });
    }



    private void showBooksTelaDevolucao(String cpfUsuario) {

        DefaultTableModel model = (DefaultTableModel) jTableBookDevolucao.getModel();
        model.setRowCount(0);

        new GetUserEmprestimoBooks().getUserEmprestimoBooks(cpfUsuario).forEach((dadosDevolucao) -> {

            model.addRow(new Object[]{
                dadosDevolucao.getNome(),
                dadosDevolucao.getCpf(),
                dadosDevolucao.getISBN(),
                dadosDevolucao.getDataEmp(),
                dadosDevolucao.getDataPrev(),
                dadosDevolucao.getDataDev(),
                dadosDevolucao.getIdEmp(),
                dadosDevolucao.getMulta(),
            });
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroupDeletaUser = new javax.swing.ButtonGroup();
        jPanelLoginScreen = new javax.swing.JPanel();
        jLabelMinimizejPanelLoginScreen = new javax.swing.JLabel();
        minimizeTelaLogin = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelClosejPanelLoginScreen = new javax.swing.JLabel();
        closeTelaLogin = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jFormattedTextFieldCpfUsuarioLogin = new javax.swing.JFormattedTextField();
        jFormattedTextFieldSenhaUsuarioLogin = new javax.swing.JPasswordField();
        jLabelSenha = new javax.swing.JLabel();
        jLabelCpf = new javax.swing.JLabel();
        recuperarSenhaLogin = new javax.swing.JLabel();
        jLabelAlertCpfUsuarioLogin = new javax.swing.JLabel();
        jLabelAlertSenhaLogin = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanelProfessorEstudanteAdmin = new javax.swing.JPanel();
        botaoAdminLogin = new javax.swing.JRadioButton();
        botaoEstudanteLogin = new javax.swing.JRadioButton();
        botaoProfessorLogin = new javax.swing.JRadioButton();
        jButtonjPanelLoginScreen = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabelBackTelaDeleteLivros = new javax.swing.JLabel();
        jLabelBackground2TelaLogin = new javax.swing.JLabel();
        jLabelBackgroundjPanelLoginScreen = new javax.swing.JLabel();
        jPanelTelaEstudante = new javax.swing.JPanel();
        jLabelCloseTelaEstudante = new javax.swing.JLabel();
        jButtonEmprestimo = new javax.swing.JLabel();
        infoUser = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabelIcon = new javax.swing.JLabel();
        jLabelClosejPanelTelaEstudante = new javax.swing.JLabel();
        jLabelMinimizejPanelTelaEstudante = new javax.swing.JLabel();
        jLabelBackVisiblejPanelTelaEstudante = new javax.swing.JLabel();
        jLabelBackjPanelTelaEstudante = new javax.swing.JLabel();
        jButtonAlteraçãoTelaAdmin1 = new javax.swing.JButton();
        jPanelTelaEmprestimo = new javax.swing.JPanel();
        jLabelBackTelaEmprestimo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        isbnLivro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBookEmprestimo = new javax.swing.JTable();
        efetuarEmprestimo = new javax.swing.JButton();
        jLabelMinimizeTelaEmprestimo = new javax.swing.JLabel();
        minimizeTelaEmprestimo = new javax.swing.JLabel();
        jLabelCloseTelaEmprestimo = new javax.swing.JLabel();
        closeTelaEmprestimo = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanelTelaProfessor = new javax.swing.JPanel();
        jLabelCloseTelaProfessor = new javax.swing.JLabel();
        jLabelMinimizeTelaProfessor = new javax.swing.JLabel();
        minimizeTelaProfessor = new javax.swing.JLabel();
        closeTelaProfessor = new javax.swing.JLabel();
        backTelaProfessor = new javax.swing.JLabel();
        jLabelEmprestimo = new javax.swing.JLabel();
        jLabelIconTelaProfessor = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        showNameProfessor = new javax.swing.JLabel();
        jLabelBackTelaProfessor = new javax.swing.JLabel();
        jPanelTelaRecuperarSenha = new javax.swing.JPanel();
        close = new javax.swing.JLabel();
        minimize = new javax.swing.JLabel();
        minimizeTelaRecuperaSenha = new javax.swing.JLabel();
        closeTelaRecuperaSenha = new javax.swing.JLabel();
        jLabelCpfEstudante = new javax.swing.JLabel();
        jLabelData = new javax.swing.JLabel();
        jLabelNovaSenha = new javax.swing.JLabel();
        confirmaSenhaRecuperaSenha = new javax.swing.JPasswordField();
        jLabelConfirmarSenha = new javax.swing.JLabel();
        cadastrarNewPasswordRecuperaSenha = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        alertCpfRecuperaSenha = new javax.swing.JLabel();
        alertNewPasswordRecuperaSenha = new javax.swing.JLabel();
        alertDataNascimentoRecuperaSenha = new javax.swing.JLabel();
        alertConfPasswordRecuperaSenha = new javax.swing.JLabel();
        dataNasciRecuperaSenha = new javax.swing.JFormattedTextField();
        cpfRecuperaSenha = new javax.swing.JFormattedTextField();
        newPasswordRecuperaSenha = new javax.swing.JPasswordField();
        backTelaRecuperaSenha = new javax.swing.JLabel();
        jLabelBack3 = new javax.swing.JLabel();
        jPanelAlertErroRecuperaSenha = new javax.swing.JPanel();
        jButtonRecuperarSenhaPanelErro = new javax.swing.JButton();
        jLabelTextoErro = new javax.swing.JLabel();
        jLabelImageErro = new javax.swing.JLabel();
        jPanelAlertSucessoRecuperaSenha = new javax.swing.JPanel();
        jButtonOkRecuperarSenha = new javax.swing.JButton();
        jLabelTextoOK = new javax.swing.JLabel();
        jLabelImageOK = new javax.swing.JLabel();
        jLabeBackgroundlRecuperarSenha = new javax.swing.JLabel();
        jPanelTelaLoginFuncionario = new javax.swing.JPanel();
        jLabelMinimizeTelaFuncionario = new javax.swing.JLabel();
        minimizeTelaLoginFuncionario = new javax.swing.JLabel();
        jLabelCloseTelaFuncionario = new javax.swing.JLabel();
        closeTelaLoginFuncionario = new javax.swing.JLabel();
        jLabelAlertSenhaFuncionario = new javax.swing.JLabel();
        jLabelAlertCpfFuncionario = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jButtonAutenticaFuncionario = new javax.swing.JButton();
        jLabelEsqueceSenhaFuncionario = new javax.swing.JLabel();
        jLabelCpf1 = new javax.swing.JLabel();
        jLabelSenha1 = new javax.swing.JLabel();
        senhaFuncionario = new javax.swing.JPasswordField();
        cpfFuncionario = new javax.swing.JFormattedTextField();
        jLabelIconFuncionario = new javax.swing.JLabel();
        jLabelBackground2TelaFuncionario = new javax.swing.JLabel();
        jLabelBackgroundTelaFuncionario = new javax.swing.JLabel();
        jPanel_splashScreen = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabelImageSplashScreen = new javax.swing.JLabel();
        jPanelCadastraEstudante = new javax.swing.JPanel();
        nameStudent = new javax.swing.JTextField();
        jLabelNomeEstudante = new javax.swing.JLabel();
        jLabelCpfEstudante1 = new javax.swing.JLabel();
        jLabelSenha2 = new javax.swing.JLabel();
        jLabelConfirmSenha = new javax.swing.JLabel();
        jLabelData1 = new javax.swing.JLabel();
        jLabelBanner = new javax.swing.JLabel();
        backTelaCadastraEstudante = new javax.swing.JLabel();
        jLabelTelefoneEstudante = new javax.swing.JLabel();
        cpfUser = new javax.swing.JFormattedTextField();
        phoneUser = new javax.swing.JFormattedTextField();
        birthDate = new javax.swing.JFormattedTextField();
        botaoCadastrarEstudante = new javax.swing.JLabel();
        jLabelIdEstudante = new javax.swing.JLabel();
        codUsuario = new javax.swing.JFormattedTextField();
        passwordUser = new javax.swing.JPasswordField();
        cofimacaoSenha = new javax.swing.JPasswordField();
        jLabelMinimizeTelaCadastroEstudante = new javax.swing.JLabel();
        jLabelCloseTelaCadastroEstudante = new javax.swing.JLabel();
        minimizeTelaCadEstudante = new javax.swing.JLabel();
        closeTelaCadEstudante = new javax.swing.JLabel();
        jLabelBackTelaEstudante = new javax.swing.JLabel();
        jPanelTelaTecAdmin = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        minimizeTelaAdmin = new javax.swing.JLabel();
        closeTelaAdmin = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jButtonCadastraLivroTelaAdmin = new javax.swing.JButton();
        backTelaAdmin = new javax.swing.JLabel();
        jButtonDeletarLivroTelaAdmin = new javax.swing.JButton();
        jButtonDeletarUsuarioTelaAdmin = new javax.swing.JButton();
        jButtonCadastraEstudanteTelaAdmin = new javax.swing.JButton();
        jButtonCadastraProfessorTelaAdmin = new javax.swing.JButton();
        jLabelBackTelaAdmin = new javax.swing.JLabel();
        jPanelTelaCadastraLivros = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabelBookYear = new javax.swing.JLabel();
        jLabelBookWeight = new javax.swing.JLabel();
        nomeAutor = new javax.swing.JTextField();
        jLabelAutorName = new javax.swing.JLabel();
        tituloLivro = new javax.swing.JTextField();
        jLabelBookTitle = new javax.swing.JLabel();
        nomeEditora = new javax.swing.JTextField();
        jLabelPublisherName = new javax.swing.JLabel();
        jReturn = new javax.swing.JLabel();
        anoLivro = new javax.swing.JFormattedTextField();
        jL_CadLivro = new javax.swing.JLabel();
        pesoLivro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        idioma = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        pais = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        numPaginas = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        quantidadeBook = new javax.swing.JTextField();
        isbn = new javax.swing.JFormattedTextField();
        jLabelBackTelaCadastroLivros = new javax.swing.JLabel();
        jLabelMinimizeTelaCadLivros = new javax.swing.JLabel();
        minimizeTelaCadLivros = new javax.swing.JLabel();
        jLabelClose = new javax.swing.JLabel();
        closeTelaCadAdmin = new javax.swing.JLabel();
        jPanelTelaDeletaUsuario = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        minimizeTelaUsuario = new javax.swing.JLabel();
        closeDeletarUsuario = new javax.swing.JLabel();
        senhaDeletaUsuario = new javax.swing.JPasswordField();
        professor = new javax.swing.JRadioButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        deletaUser = new javax.swing.JLabel();
        cpfDeletaUsuario = new javax.swing.JFormattedTextField();
        estudante = new javax.swing.JRadioButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabelBackTelaDeletaUser = new javax.swing.JLabel();
        jLabelBackTelaDeletaUsuario = new javax.swing.JLabel();
        jLabelBackgroundTelaDeletaUsuario = new javax.swing.JLabel();
        jPanelTelaCadastraProfessor = new javax.swing.JPanel();
        nomeProfessorr = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabelTextCadProfessor = new javax.swing.JLabel();
        jLabelReturn = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        cpfProfessor = new javax.swing.JFormattedTextField();
        telefoneProfessorr = new javax.swing.JFormattedTextField();
        dataNascimento = new javax.swing.JFormattedTextField();
        senhaProfessor = new javax.swing.JPasswordField();
        confimSenha = new javax.swing.JPasswordField();
        jLabel33 = new javax.swing.JLabel();
        matriculaProfessor = new javax.swing.JFormattedTextField();
        jLabelMinimizaTelaCadastraProfessor = new javax.swing.JLabel();
        minimizeTelaCadProfessor = new javax.swing.JLabel();
        jLabelCloseTelaCadastraProfessor = new javax.swing.JLabel();
        closeTelaCadProfessor = new javax.swing.JLabel();
        jLabelBackTelaCadProfessor = new javax.swing.JLabel();
        jButtonCadastraProfessor = new javax.swing.JButton();
        jPanelTelaDeletarBooks = new javax.swing.JPanel();
        jLabelCloseTelaDeletaLivros = new javax.swing.JLabel();
        jLabelMinimizeTelaDeleteLivro = new javax.swing.JLabel();
        jLabelMinimizeTelaDeleteLivros = new javax.swing.JLabel();
        jLabelCloseTelaDeleteLivros = new javax.swing.JLabel();
        jLabelBack = new javax.swing.JLabel();
        jLabelText = new javax.swing.JLabel();
        pesquisaLivro = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDeleteBooks = new javax.swing.JTable();
        deletaBook = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanelTelaDevolucao = new javax.swing.JPanel();
        jLabelBack1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableBookDevolucao = new javax.swing.JTable();
        jLabelCloseTelaDevolucao = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabelMinimizejPanelTelaDevolucao = new javax.swing.JLabel();
        jLabelClosejPanelTelaDevolucao = new javax.swing.JLabel();
        jLabelBackTelaDevolucao = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(51, 51, 255));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelLoginScreen.setBackground(new java.awt.Color(255, 59, 94));
        jPanelLoginScreen.setForeground(new java.awt.Color(255, 255, 255));
        jPanelLoginScreen.setPreferredSize(new java.awt.Dimension(960, 615));
        jPanelLoginScreen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelMinimizejPanelLoginScreen.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelMinimizejPanelLoginScreen.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMinimizejPanelLoginScreen.setText("  -");
        jLabelMinimizejPanelLoginScreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinimizejPanelLoginScreenMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMinimizejPanelLoginScreenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMinimizejPanelLoginScreenMouseExited(evt);
            }
        });
        jPanelLoginScreen.add(jLabelMinimizejPanelLoginScreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 5, 30, 20));

        minimizeTelaLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button.png"))); // NOI18N
        jPanelLoginScreen.add(minimizeTelaLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 5, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText(" Empréstimos de livros");
        jPanelLoginScreen.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 155, 15));

        jLabelClosejPanelLoginScreen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelClosejPanelLoginScreen.setForeground(new java.awt.Color(51, 51, 51));
        jLabelClosejPanelLoginScreen.setText("   X");
        jLabelClosejPanelLoginScreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelClosejPanelLoginScreenMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelClosejPanelLoginScreenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelClosejPanelLoginScreenMouseExited(evt);
            }
        });
        jPanelLoginScreen.add(jLabelClosejPanelLoginScreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(918, 9, 30, -1));

        closeTelaLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button.png"))); // NOI18N
        jPanelLoginScreen.add(closeTelaLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 5, -1, -1));

        jPanel2.setBackground(new java.awt.Color(252, 48, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jFormattedTextFieldCpfUsuarioLogin.setBorder(null);
        jFormattedTextFieldCpfUsuarioLogin.setForeground(new java.awt.Color(255, 0, 85));
        try {
            jFormattedTextFieldCpfUsuarioLogin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCpfUsuarioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldCpfUsuarioLoginActionPerformed(evt);
            }
        });
        jPanel2.add(jFormattedTextFieldCpfUsuarioLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 50, 298, 28));

        jFormattedTextFieldSenhaUsuarioLogin.setForeground(new java.awt.Color(255, 0, 85));
        jFormattedTextFieldSenhaUsuarioLogin.setToolTipText("");
        jFormattedTextFieldSenhaUsuarioLogin.setBorder(null);
        jFormattedTextFieldSenhaUsuarioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldSenhaUsuarioLoginActionPerformed(evt);
            }
        });
        jFormattedTextFieldSenhaUsuarioLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldSenhaUsuarioLoginKeyReleased(evt);
            }
        });
        jPanel2.add(jFormattedTextFieldSenhaUsuarioLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 114, 298, 28));

        jLabelSenha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelSenha.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSenha.setText("Senha");
        jPanel2.add(jLabelSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 98, -1, -1));

        jLabelCpf.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelCpf.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCpf.setText("CPF");
        jPanel2.add(jLabelCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 35, -1, -1));

        recuperarSenhaLogin.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        recuperarSenhaLogin.setForeground(new java.awt.Color(255, 255, 255));
        recuperarSenhaLogin.setText("Esqueci minha Senha");
        recuperarSenhaLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                recuperarSenhaLoginMouseClicked(evt);
            }
        });
        jPanel2.add(recuperarSenhaLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 168, -1, 15));

        jLabelAlertCpfUsuarioLogin.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabelAlertCpfUsuarioLogin.setForeground(new java.awt.Color(255, 255, 0));
        jLabelAlertCpfUsuarioLogin.setText("Por favor, entre com um cpf");
        jPanel2.add(jLabelAlertCpfUsuarioLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        jLabelAlertSenhaLogin.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabelAlertSenhaLogin.setForeground(new java.awt.Color(255, 255, 0));
        jLabelAlertSenhaLogin.setText("Por favor, entre com uma senha");
        jPanel2.add(jLabelAlertSenhaLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 144, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("Identifique-se para prosseguir");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 0, -1, -1));

        jPanelLoginScreen.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(569, 180, 300, 190));

        jPanelProfessorEstudanteAdmin.setBackground(new java.awt.Color(252, 48, 82));
        jPanelProfessorEstudanteAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botaoAdminLogin.setBackground(new java.awt.Color(252, 48, 82));
        buttonGroup1.add(botaoAdminLogin);
        botaoAdminLogin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        botaoAdminLogin.setForeground(new java.awt.Color(255, 255, 255));
        botaoAdminLogin.setText("Admin");
        botaoAdminLogin.setFocusPainted(false);
        botaoAdminLogin.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botaoAdminLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdminLoginActionPerformed(evt);
            }
        });
        jPanelProfessorEstudanteAdmin.add(botaoAdminLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 10, -1, 14));

        botaoEstudanteLogin.setBackground(new java.awt.Color(252, 48, 82));
        buttonGroup1.add(botaoEstudanteLogin);
        botaoEstudanteLogin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        botaoEstudanteLogin.setForeground(new java.awt.Color(255, 255, 255));
        botaoEstudanteLogin.setText("Estudante");
        botaoEstudanteLogin.setBorder(null);
        botaoEstudanteLogin.setFocusPainted(false);
        botaoEstudanteLogin.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botaoEstudanteLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEstudanteLoginActionPerformed(evt);
            }
        });
        jPanelProfessorEstudanteAdmin.add(botaoEstudanteLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 10, -1, 14));

        botaoProfessorLogin.setBackground(new java.awt.Color(252, 48, 82));
        buttonGroup1.add(botaoProfessorLogin);
        botaoProfessorLogin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        botaoProfessorLogin.setForeground(new java.awt.Color(254, 254, 254));
        botaoProfessorLogin.setText("Professor");
        botaoProfessorLogin.setBorder(null);
        botaoProfessorLogin.setFocusPainted(false);
        botaoProfessorLogin.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botaoProfessorLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoProfessorLoginActionPerformed(evt);
            }
        });
        jPanelProfessorEstudanteAdmin.add(botaoProfessorLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 10, -1, 14));

        jPanelLoginScreen.add(jPanelProfessorEstudanteAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 392, 298, 30));

        jButtonjPanelLoginScreen.setBackground(new java.awt.Color(255, 255, 255));
        jButtonjPanelLoginScreen.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonjPanelLoginScreen.setForeground(new java.awt.Color(255, 59, 94));
        jButtonjPanelLoginScreen.setText("Login");
        jButtonjPanelLoginScreen.setBorder(null);
        jButtonjPanelLoginScreen.setFocusPainted(false);
        jButtonjPanelLoginScreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonjPanelLoginScreenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonjPanelLoginScreenMouseExited(evt);
            }
        });
        jButtonjPanelLoginScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonjPanelLoginScreenActionPerformed(evt);
            }
        });
        jPanelLoginScreen.add(jButtonjPanelLoginScreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 280, 28));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Login");
        jPanelLoginScreen.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, -1, -1));

        jLabelBackTelaDeleteLivros.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabelBackTelaDeleteLivros.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBackTelaDeleteLivros.setText("Biblioteca");
        jPanelLoginScreen.add(jLabelBackTelaDeleteLivros, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        jLabelBackground2TelaLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Annotation 2020-04-25 163815.png"))); // NOI18N
        jPanelLoginScreen.add(jLabelBackground2TelaLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, -1, -1));

        jLabelBackgroundjPanelLoginScreen.setBackground(new java.awt.Color(252, 240, 240));
        jLabelBackgroundjPanelLoginScreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Annotation 2020-04-16 150218.png"))); // NOI18N
        jLabelBackgroundjPanelLoginScreen.setText("jLabel4");
        jLabelBackgroundjPanelLoginScreen.setPreferredSize(new java.awt.Dimension(960, 615));
        jPanelLoginScreen.add(jLabelBackgroundjPanelLoginScreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 615));

        getContentPane().add(jPanelLoginScreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanelTelaEstudante.setBackground(new java.awt.Color(255, 59, 94));
        jPanelTelaEstudante.setEnabled(false);
        jPanelTelaEstudante.setPreferredSize(new java.awt.Dimension(960, 615));
        jPanelTelaEstudante.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCloseTelaEstudante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCloseTelaEstudante.setForeground(new java.awt.Color(51, 51, 51));
        jLabelCloseTelaEstudante.setText("   X");
        jLabelCloseTelaEstudante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaEstudanteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaEstudanteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaEstudanteMouseExited(evt);
            }
        });
        jPanelTelaEstudante.add(jLabelCloseTelaEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(918, 9, 30, -1));

        jButtonEmprestimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button_emprestimos.png"))); // NOI18N
        jButtonEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonEmprestimoMouseClicked(evt);
            }
        });
        jPanelTelaEstudante.add(jButtonEmprestimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, -1, -1));

        infoUser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        infoUser.setForeground(new java.awt.Color(255, 255, 255));
        jPanelTelaEstudante.add(infoUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 360, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nome:");
        jPanelTelaEstudante.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 128, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 85, 114));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanelTelaEstudante.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 400, 30));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("  -");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
        });
        jPanelTelaEstudante.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 5, 30, 20));

        jLabelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/graduate (1).png"))); // NOI18N
        jPanelTelaEstudante.add(jLabelIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 122, 30, -1));

        jLabelClosejPanelTelaEstudante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaEstudante.add(jLabelClosejPanelTelaEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 5, -1, -1));

        jLabelMinimizejPanelTelaEstudante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaEstudante.add(jLabelMinimizejPanelTelaEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 5, -1, -1));

        jLabelBackVisiblejPanelTelaEstudante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-20.png"))); // NOI18N
        jPanelTelaEstudante.add(jLabelBackVisiblejPanelTelaEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jLabelBackjPanelTelaEstudante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-200.png"))); // NOI18N
        jLabelBackjPanelTelaEstudante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackjPanelTelaEstudanteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBackjPanelTelaEstudanteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBackjPanelTelaEstudanteMouseExited(evt);
            }
        });
        jPanelTelaEstudante.add(jLabelBackjPanelTelaEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jButtonAlteraçãoTelaAdmin1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAlteraçãoTelaAdmin1.setForeground(new java.awt.Color(255, 59, 94));
        jButtonAlteraçãoTelaAdmin1.setText("Efetuar devolucao");
        jButtonAlteraçãoTelaAdmin1.setBorder(null);
        jButtonAlteraçãoTelaAdmin1.setFocusPainted(false);
        jButtonAlteraçãoTelaAdmin1.setPreferredSize(new java.awt.Dimension(97, 16));
        jButtonAlteraçãoTelaAdmin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlteraçãoTelaAdmin1ActionPerformed(evt);
            }
        });
        jPanelTelaEstudante.add(jButtonAlteraçãoTelaAdmin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 440, 28));

        getContentPane().add(jPanelTelaEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 615));

        jPanelTelaEmprestimo.setBackground(new java.awt.Color(255, 59, 94));
        jPanelTelaEmprestimo.setPreferredSize(new java.awt.Dimension(960, 615));
        jPanelTelaEmprestimo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBackTelaEmprestimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-200.png"))); // NOI18N
        jLabelBackTelaEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackTelaEmprestimoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBackTelaEmprestimoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBackTelaEmprestimoMouseExited(evt);
            }
        });
        jPanelTelaEmprestimo.add(jLabelBackTelaEmprestimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 254));
        jLabel15.setText("Digite o título");
        jPanelTelaEmprestimo.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 12, -1, -1));

        isbnLivro.setBorder(null);
        isbnLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isbnLivroActionPerformed(evt);
            }
        });
        isbnLivro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                isbnLivroKeyReleased(evt);
            }
        });
        jPanelTelaEmprestimo.add(isbnLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 6, 310, 28));

        jScrollPane1.setForeground(new java.awt.Color(255, 0, 0));
        jScrollPane1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jTableBookEmprestimo.setBackground(new java.awt.Color(254, 254, 254));
        jTableBookEmprestimo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTableBookEmprestimo.setForeground(new java.awt.Color(51, 51, 51));
        jTableBookEmprestimo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título", "Nome do Autor", "ISBN", "Quantidade", "Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBookEmprestimo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableBookEmprestimo.setGridColor(new java.awt.Color(255, 255, 255));
        jTableBookEmprestimo.setRequestFocusEnabled(false);
        jTableBookEmprestimo.setRowHeight(25);
        jTableBookEmprestimo.setSelectionBackground(new java.awt.Color(240, 156, 176));
        jTableBookEmprestimo.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jTableBookEmprestimo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableBookEmprestimo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableBookEmprestimo.setShowGrid(false);
        jTableBookEmprestimo.getTableHeader().setReorderingAllowed(false);
        jTableBookEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBookEmprestimoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBookEmprestimo);
        if (jTableBookEmprestimo.getColumnModel().getColumnCount() > 0) {
            jTableBookEmprestimo.getColumnModel().getColumn(3).setPreferredWidth(1);
            jTableBookEmprestimo.getColumnModel().getColumn(4).setPreferredWidth(1);
        }

        jPanelTelaEmprestimo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 40, 965, 577));

        efetuarEmprestimo.setBackground(new java.awt.Color(255, 255, 255));
        efetuarEmprestimo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        efetuarEmprestimo.setForeground(new java.awt.Color(255, 59, 94));
        efetuarEmprestimo.setText("Efetua empréstimo");
        efetuarEmprestimo.setBorder(null);
        efetuarEmprestimo.setFocusPainted(false);
        efetuarEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                efetuarEmprestimoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                efetuarEmprestimoMouseExited(evt);
            }
        });
        efetuarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efetuarEmprestimoActionPerformed(evt);
            }
        });
        jPanelTelaEmprestimo.add(efetuarEmprestimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 6, 130, 28));

        jLabelMinimizeTelaEmprestimo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelMinimizeTelaEmprestimo.setForeground(new java.awt.Color(51, 51, 51));
        jLabelMinimizeTelaEmprestimo.setText("  -");
        jLabelMinimizeTelaEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaEmprestimoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaEmprestimoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaEmprestimoMouseExited(evt);
            }
        });
        jPanelTelaEmprestimo.add(jLabelMinimizeTelaEmprestimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 5, 30, 20));

        minimizeTelaEmprestimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaEmprestimo.add(minimizeTelaEmprestimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 5, -1, -1));

        jLabelCloseTelaEmprestimo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCloseTelaEmprestimo.setForeground(new java.awt.Color(51, 51, 51));
        jLabelCloseTelaEmprestimo.setText("   X");
        jLabelCloseTelaEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaEmprestimoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaEmprestimoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaEmprestimoMouseExited(evt);
            }
        });
        jPanelTelaEmprestimo.add(jLabelCloseTelaEmprestimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(918, 9, 30, -1));

        closeTelaEmprestimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaEmprestimo.add(closeTelaEmprestimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 5, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-20.png"))); // NOI18N
        jPanelTelaEmprestimo.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        getContentPane().add(jPanelTelaEmprestimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 615));

        jPanelTelaProfessor.setBackground(new java.awt.Color(255, 59, 94));
        jPanelTelaProfessor.setEnabled(false);
        jPanelTelaProfessor.setPreferredSize(new java.awt.Dimension(960, 615));
        jPanelTelaProfessor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCloseTelaProfessor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCloseTelaProfessor.setForeground(new java.awt.Color(51, 51, 51));
        jLabelCloseTelaProfessor.setText("   X");
        jLabelCloseTelaProfessor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaProfessorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaProfessorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaProfessorMouseExited(evt);
            }
        });
        jPanelTelaProfessor.add(jLabelCloseTelaProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(918, 9, 30, -1));

        jLabelMinimizeTelaProfessor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelMinimizeTelaProfessor.setForeground(new java.awt.Color(51, 51, 51));
        jLabelMinimizeTelaProfessor.setText("  -");
        jLabelMinimizeTelaProfessor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaProfessorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaProfessorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaProfessorMouseExited(evt);
            }
        });
        jPanelTelaProfessor.add(jLabelMinimizeTelaProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 5, 30, 20));

        minimizeTelaProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaProfessor.add(minimizeTelaProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 5, -1, -1));

        closeTelaProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaProfessor.add(closeTelaProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 5, -1, -1));

        backTelaProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-200.png"))); // NOI18N
        backTelaProfessor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backTelaProfessorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backTelaProfessorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backTelaProfessorMouseExited(evt);
            }
        });
        jPanelTelaProfessor.add(backTelaProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jLabelEmprestimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button_emprestimos.png"))); // NOI18N
        jLabelEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEmprestimoMouseClicked(evt);
            }
        });
        jPanelTelaProfessor.add(jLabelEmprestimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, -1, -1));

        jLabelIconTelaProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/graduate (1).png"))); // NOI18N
        jPanelTelaProfessor.add(jLabelIconTelaProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 122, 30, -1));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Nome:");
        jPanelTelaProfessor.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 128, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 85, 114));

        showNameProfessor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        showNameProfessor.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 40, Short.MAX_VALUE)
                .addComponent(showNameProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(showNameProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanelTelaProfessor.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 400, 30));

        jLabelBackTelaProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-20.png"))); // NOI18N
        jPanelTelaProfessor.add(jLabelBackTelaProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        getContentPane().add(jPanelTelaProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanelTelaRecuperarSenha.setBackground(new java.awt.Color(255, 59, 94));
        jPanelTelaRecuperarSenha.setEnabled(false);
        jPanelTelaRecuperarSenha.setPreferredSize(new java.awt.Dimension(960, 615));
        jPanelTelaRecuperarSenha.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        close.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        close.setForeground(new java.awt.Color(51, 51, 51));
        close.setText("   X");
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeMouseExited(evt);
            }
        });
        jPanelTelaRecuperarSenha.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(918, 9, 30, -1));

        minimize.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        minimize.setForeground(new java.awt.Color(51, 51, 51));
        minimize.setText("  -");
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizeMouseExited(evt);
            }
        });
        jPanelTelaRecuperarSenha.add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 5, 30, 20));

        minimizeTelaRecuperaSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaRecuperarSenha.add(minimizeTelaRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 5, -1, -1));

        closeTelaRecuperaSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaRecuperarSenha.add(closeTelaRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 5, -1, -1));

        jLabelCpfEstudante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelCpfEstudante.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCpfEstudante.setText("CPF");
        jPanelTelaRecuperarSenha.add(jLabelCpfEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, -1, -1));

        jLabelData.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelData.setForeground(new java.awt.Color(255, 255, 255));
        jLabelData.setText("Data de nascimento");
        jPanelTelaRecuperarSenha.add(jLabelData, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, -1, -1));

        jLabelNovaSenha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelNovaSenha.setForeground(new java.awt.Color(254, 254, 254));
        jLabelNovaSenha.setText("Nova senha");
        jPanelTelaRecuperarSenha.add(jLabelNovaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, -1, -1));

        confirmaSenhaRecuperaSenha.setForeground(new java.awt.Color(255, 0, 88));
        confirmaSenhaRecuperaSenha.setBorder(null);
        confirmaSenhaRecuperaSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmaSenhaRecuperaSenhaActionPerformed(evt);
            }
        });
        confirmaSenhaRecuperaSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                confirmaSenhaRecuperaSenhaKeyReleased(evt);
            }
        });
        jPanelTelaRecuperarSenha.add(confirmaSenhaRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 387, 300, 28));

        jLabelConfirmarSenha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelConfirmarSenha.setForeground(new java.awt.Color(254, 254, 254));
        jLabelConfirmarSenha.setText("Confirmar senha");
        jPanelTelaRecuperarSenha.add(jLabelConfirmarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 370, 100, -1));

        cadastrarNewPasswordRecuperaSenha.setBackground(new java.awt.Color(255, 255, 255));
        cadastrarNewPasswordRecuperaSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button_cadastrar-nova-senha (1)_1.png"))); // NOI18N
        cadastrarNewPasswordRecuperaSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrarNewPasswordRecuperaSenhaMouseClicked(evt);
            }
        });
        jPanelTelaRecuperarSenha.add(cadastrarNewPasswordRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 530, -1, -1));

        jLabel28.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Recuperar senha");
        jPanelTelaRecuperarSenha.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 60, -1, -1));

        alertCpfRecuperaSenha.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        alertCpfRecuperaSenha.setForeground(new java.awt.Color(255, 255, 0));
        alertCpfRecuperaSenha.setText("Por favor, entre com um cpf");
        jPanelTelaRecuperarSenha.add(alertCpfRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(329, 207, -1, -1));

        alertNewPasswordRecuperaSenha.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        alertNewPasswordRecuperaSenha.setForeground(new java.awt.Color(255, 255, 0));
        alertNewPasswordRecuperaSenha.setText("Por favor, entre com uma senha");
        jPanelTelaRecuperarSenha.add(alertNewPasswordRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(329, 347, -1, -1));

        alertDataNascimentoRecuperaSenha.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        alertDataNascimentoRecuperaSenha.setForeground(new java.awt.Color(255, 255, 0));
        alertDataNascimentoRecuperaSenha.setText("Por favor, entre com uma data de nascimento");
        jPanelTelaRecuperarSenha.add(alertDataNascimentoRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(329, 277, -1, -1));

        alertConfPasswordRecuperaSenha.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        alertConfPasswordRecuperaSenha.setForeground(new java.awt.Color(255, 255, 0));
        alertConfPasswordRecuperaSenha.setText("Por favor, entre com uma senha");
        jPanelTelaRecuperarSenha.add(alertConfPasswordRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(329, 417, -1, -1));

        dataNasciRecuperaSenha.setBorder(null);
        dataNasciRecuperaSenha.setForeground(new java.awt.Color(255, 0, 88));
        try {
            dataNasciRecuperaSenha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataNasciRecuperaSenha.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        dataNasciRecuperaSenha.setText("");
        dataNasciRecuperaSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataNasciRecuperaSenhaActionPerformed(evt);
            }
        });
        jPanelTelaRecuperarSenha.add(dataNasciRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 247, 300, 28));

        cpfRecuperaSenha.setBorder(null);
        cpfRecuperaSenha.setForeground(new java.awt.Color(255, 0, 88));
        try {
            cpfRecuperaSenha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cpfRecuperaSenha.setText("");
        cpfRecuperaSenha.setToolTipText("");
        cpfRecuperaSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpfRecuperaSenhaActionPerformed(evt);
            }
        });
        jPanelTelaRecuperarSenha.add(cpfRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 177, 300, 28));

        newPasswordRecuperaSenha.setForeground(new java.awt.Color(255, 0, 88));
        newPasswordRecuperaSenha.setBorder(null);
        newPasswordRecuperaSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPasswordRecuperaSenhaActionPerformed(evt);
            }
        });
        newPasswordRecuperaSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                newPasswordRecuperaSenhaKeyReleased(evt);
            }
        });
        jPanelTelaRecuperarSenha.add(newPasswordRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 317, 300, 28));

        backTelaRecuperaSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-200.png"))); // NOI18N
        backTelaRecuperaSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backTelaRecuperaSenhaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backTelaRecuperaSenhaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backTelaRecuperaSenhaMouseExited(evt);
            }
        });
        jPanelTelaRecuperarSenha.add(backTelaRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jLabelBack3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-20.png"))); // NOI18N
        jPanelTelaRecuperarSenha.add(jLabelBack3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, 20, 20));

        jPanelAlertErroRecuperaSenha.setBackground(new java.awt.Color(255, 59, 94));
        jPanelAlertErroRecuperaSenha.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonRecuperarSenhaPanelErro.setBackground(new java.awt.Color(255, 59, 94));
        jButtonRecuperarSenhaPanelErro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButtonRecuperarSenhaPanelErro.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRecuperarSenhaPanelErro.setText("OK");
        jButtonRecuperarSenhaPanelErro.setBorder(null);
        jButtonRecuperarSenhaPanelErro.setFocusPainted(false);
        jButtonRecuperarSenhaPanelErro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecuperarSenhaPanelErroActionPerformed(evt);
            }
        });
        jPanelAlertErroRecuperaSenha.add(jButtonRecuperarSenhaPanelErro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 143, 210, 28));

        jLabelTextoErro.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelTextoErro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTextoErro.setText("Usuário não encontrado!");
        jPanelAlertErroRecuperaSenha.add(jLabelTextoErro, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 86, -1, -1));

        jLabelImageErro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/jLabelAlertErro.png"))); // NOI18N
        jPanelAlertErroRecuperaSenha.add(jLabelImageErro, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 30, 300, 150));

        jPanelTelaRecuperarSenha.add(jPanelAlertErroRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 180, 310, 210));

        jPanelAlertSucessoRecuperaSenha.setBackground(new java.awt.Color(255, 59, 94));
        jPanelAlertSucessoRecuperaSenha.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonOkRecuperarSenha.setBackground(new java.awt.Color(255, 59, 94));
        jButtonOkRecuperarSenha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonOkRecuperarSenha.setForeground(new java.awt.Color(255, 255, 255));
        jButtonOkRecuperarSenha.setText("ok");
        jButtonOkRecuperarSenha.setBorder(null);
        jButtonOkRecuperarSenha.setFocusPainted(false);
        jButtonOkRecuperarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkRecuperarSenhaActionPerformed(evt);
            }
        });
        jPanelAlertSucessoRecuperaSenha.add(jButtonOkRecuperarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 143, 210, 28));

        jLabelTextoOK.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelTextoOK.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTextoOK.setText("Senha alterada com sucesso!");
        jPanelAlertSucessoRecuperaSenha.add(jLabelTextoOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 85, 170, -1));

        jLabelImageOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/jLabelAlert.png"))); // NOI18N
        jPanelAlertSucessoRecuperaSenha.add(jLabelImageOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 30, 300, 150));

        jPanelTelaRecuperarSenha.add(jPanelAlertSucessoRecuperaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 180, 310, 210));

        jLabeBackgroundlRecuperarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Annotation 2020-04-25 163815.png"))); // NOI18N
        jPanelTelaRecuperarSenha.add(jLabeBackgroundlRecuperarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 320, -1));

        getContentPane().add(jPanelTelaRecuperarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 615));

        jPanelTelaLoginFuncionario.setBackground(new java.awt.Color(255, 59, 94));
        jPanelTelaLoginFuncionario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelMinimizeTelaFuncionario.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelMinimizeTelaFuncionario.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMinimizeTelaFuncionario.setText("  -");
        jLabelMinimizeTelaFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaFuncionarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaFuncionarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaFuncionarioMouseExited(evt);
            }
        });
        jPanelTelaLoginFuncionario.add(jLabelMinimizeTelaFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 5, 30, 20));

        minimizeTelaLoginFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button.png"))); // NOI18N
        jPanelTelaLoginFuncionario.add(minimizeTelaLoginFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 5, -1, -1));

        jLabelCloseTelaFuncionario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCloseTelaFuncionario.setForeground(new java.awt.Color(51, 51, 51));
        jLabelCloseTelaFuncionario.setText("   X");
        jLabelCloseTelaFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaFuncionarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaFuncionarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaFuncionarioMouseExited(evt);
            }
        });
        jPanelTelaLoginFuncionario.add(jLabelCloseTelaFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(918, 9, 30, -1));

        closeTelaLoginFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button.png"))); // NOI18N
        jPanelTelaLoginFuncionario.add(closeTelaLoginFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 5, -1, -1));

        jLabelAlertSenhaFuncionario.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabelAlertSenhaFuncionario.setForeground(new java.awt.Color(255, 255, 0));
        jLabelAlertSenhaFuncionario.setText("Por favor, entre com uma senha");
        jPanelTelaLoginFuncionario.add(jLabelAlertSenhaFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, -1, -1));

        jLabelAlertCpfFuncionario.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabelAlertCpfFuncionario.setForeground(new java.awt.Color(255, 255, 0));
        jLabelAlertCpfFuncionario.setText("Por favor, entre com um cpf");
        jPanelTelaLoginFuncionario.add(jLabelAlertCpfFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, -1, -1));

        jLabel34.setBackground(new java.awt.Color(51, 51, 51));
        jLabel34.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Digite suas informações para acessar");
        jPanelTelaLoginFuncionario.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, -1, -1));

        jButtonAutenticaFuncionario.setBackground(new java.awt.Color(36, 84, 113));
        jButtonAutenticaFuncionario.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButtonAutenticaFuncionario.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAutenticaFuncionario.setText("Autenticar");
        jButtonAutenticaFuncionario.setBorder(null);
        jButtonAutenticaFuncionario.setFocusPainted(false);
        jButtonAutenticaFuncionario.setPreferredSize(new java.awt.Dimension(59, 28));
        jButtonAutenticaFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonAutenticaFuncionarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonAutenticaFuncionarioMouseExited(evt);
            }
        });
        jButtonAutenticaFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAutenticaFuncionarioActionPerformed(evt);
            }
        });
        jPanelTelaLoginFuncionario.add(jButtonAutenticaFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, 301, -1));

        jLabelEsqueceSenhaFuncionario.setBackground(new java.awt.Color(234, 234, 234));
        jLabelEsqueceSenhaFuncionario.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabelEsqueceSenhaFuncionario.setForeground(new java.awt.Color(233, 233, 233));
        jLabelEsqueceSenhaFuncionario.setText("Esqueci minha Senha");
        jLabelEsqueceSenhaFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEsqueceSenhaFuncionarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelEsqueceSenhaFuncionarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelEsqueceSenhaFuncionarioMouseExited(evt);
            }
        });
        jPanelTelaLoginFuncionario.add(jLabelEsqueceSenhaFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 460, -1, 14));

        jLabelCpf1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelCpf1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCpf1.setText("CPF");
        jPanelTelaLoginFuncionario.add(jLabelCpf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, -1, -1));

        jLabelSenha1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelSenha1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSenha1.setText("Senha");
        jPanelTelaLoginFuncionario.add(jLabelSenha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, -1, -1));

        senhaFuncionario.setColumns(5);
        senhaFuncionario.setForeground(new java.awt.Color(255, 0, 85));
        senhaFuncionario.setToolTipText("");
        senhaFuncionario.setBorder(null);
        senhaFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senhaFuncionarioActionPerformed(evt);
            }
        });
        senhaFuncionario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                senhaFuncionarioKeyReleased(evt);
            }
        });
        jPanelTelaLoginFuncionario.add(senhaFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 350, 300, 28));

        cpfFuncionario.setBorder(null);
        cpfFuncionario.setForeground(new java.awt.Color(255, 0, 85));
        try {
            cpfFuncionario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cpfFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpfFuncionarioActionPerformed(evt);
            }
        });
        jPanelTelaLoginFuncionario.add(cpfFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, 300, 28));

        jLabelIconFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-pontilhado-60.png"))); // NOI18N
        jPanelTelaLoginFuncionario.add(jLabelIconFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 60, 70));

        jLabelBackground2TelaFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Annotation 2020-04-25 163815.png"))); // NOI18N
        jPanelTelaLoginFuncionario.add(jLabelBackground2TelaFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 120, 320, -1));

        jLabelBackgroundTelaFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Annotation 2020-04-16 150218.png"))); // NOI18N
        jLabelBackgroundTelaFuncionario.setText("jLabel3");
        jPanelTelaLoginFuncionario.add(jLabelBackgroundTelaFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 615));

        getContentPane().add(jPanelTelaLoginFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 615));

        jPanel_splashScreen.setMinimumSize(new java.awt.Dimension(960, 615));
        jPanel_splashScreen.setPreferredSize(new java.awt.Dimension(960, 615));
        jPanel_splashScreen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jProgressBar1.setBackground(new java.awt.Color(255, 59, 94));
        jProgressBar1.setForeground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setAlignmentX(0.0F);
        jProgressBar1.setAlignmentY(1.0F);
        jProgressBar1.setBorderPainted(false);
        jProgressBar1.setPreferredSize(new java.awt.Dimension(148, 8));
        jPanel_splashScreen.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 579, 870, 2));

        jLabelImageSplashScreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Annnotation 2020-05-21 101923.png"))); // NOI18N
        jPanel_splashScreen.add(jLabelImageSplashScreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 615));

        getContentPane().add(jPanel_splashScreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 615));

        jPanelCadastraEstudante.setBackground(new java.awt.Color(255, 59, 94));
        jPanelCadastraEstudante.setPreferredSize(new java.awt.Dimension(960, 615));
        jPanelCadastraEstudante.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nameStudent.setForeground(new java.awt.Color(255, 0, 88));
        nameStudent.setBorder(null);
        nameStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameStudentActionPerformed(evt);
            }
        });
        nameStudent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameStudentKeyReleased(evt);
            }
        });
        jPanelCadastraEstudante.add(nameStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 640, 28));

        jLabelNomeEstudante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelNomeEstudante.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNomeEstudante.setText("Nome");
        jPanelCadastraEstudante.add(jLabelNomeEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 133, -1, -1));

        jLabelCpfEstudante1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelCpfEstudante1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCpfEstudante1.setText("CPF");
        jPanelCadastraEstudante.add(jLabelCpfEstudante1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 203, -1, -1));

        jLabelSenha2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelSenha2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSenha2.setText("Senha");
        jPanelCadastraEstudante.add(jLabelSenha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 273, -1, -1));

        jLabelConfirmSenha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelConfirmSenha.setForeground(new java.awt.Color(255, 255, 255));
        jLabelConfirmSenha.setText("Confirmar senha");
        jPanelCadastraEstudante.add(jLabelConfirmSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 343, -1, -1));

        jLabelData1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelData1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelData1.setText("Datade nascimento");
        jPanelCadastraEstudante.add(jLabelData1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 273, -1, -1));

        jLabelBanner.setBackground(new java.awt.Color(188, 69, 206));
        jLabelBanner.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelBanner.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBanner.setText("Cadastrar estudante");
        jPanelCadastraEstudante.add(jLabelBanner, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 60, -1, -1));

        backTelaCadastraEstudante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-200.png"))); // NOI18N
        backTelaCadastraEstudante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backTelaCadastraEstudanteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backTelaCadastraEstudanteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backTelaCadastraEstudanteMouseExited(evt);
            }
        });
        jPanelCadastraEstudante.add(backTelaCadastraEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jLabelTelefoneEstudante.setBackground(new java.awt.Color(254, 254, 254));
        jLabelTelefoneEstudante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelTelefoneEstudante.setForeground(new java.awt.Color(254, 254, 254));
        jLabelTelefoneEstudante.setText("Telefone");
        jPanelCadastraEstudante.add(jLabelTelefoneEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 203, -1, -1));

        cpfUser.setBorder(null);
        cpfUser.setForeground(new java.awt.Color(255, 0, 88));
        try {
            cpfUser.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cpfUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpfUserActionPerformed(evt);
            }
        });
        jPanelCadastraEstudante.add(cpfUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 300, 28));

        phoneUser.setBorder(null);
        phoneUser.setForeground(new java.awt.Color(255, 0, 88));
        try {
            phoneUser.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("## # #### ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        phoneUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneUserActionPerformed(evt);
            }
        });
        jPanelCadastraEstudante.add(phoneUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, 300, 28));

        birthDate.setBorder(null);
        birthDate.setForeground(new java.awt.Color(255, 0, 88));
        try {
            birthDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        birthDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                birthDateActionPerformed(evt);
            }
        });
        jPanelCadastraEstudante.add(birthDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, 300, 28));

        botaoCadastrarEstudante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button_cadastrar-estudante.png"))); // NOI18N
        botaoCadastrarEstudante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoCadastrarEstudanteMouseClicked(evt);
            }
        });
        jPanelCadastraEstudante.add(botaoCadastrarEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 530, -1, -1));

        jLabelIdEstudante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelIdEstudante.setForeground(new java.awt.Color(254, 254, 254));
        jLabelIdEstudante.setText("ID estudante 12 digitos");
        jPanelCadastraEstudante.add(jLabelIdEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 343, -1, -1));

        codUsuario.setBorder(null);
        codUsuario.setForeground(new java.awt.Color(255, 0, 88));
        try {
            codUsuario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("############")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        codUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codUsuarioActionPerformed(evt);
            }
        });
        jPanelCadastraEstudante.add(codUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 360, 300, 28));

        passwordUser.setForeground(new java.awt.Color(255, 0, 88));
        passwordUser.setBorder(null);
        passwordUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordUserActionPerformed(evt);
            }
        });
        passwordUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordUserKeyReleased(evt);
            }
        });
        jPanelCadastraEstudante.add(passwordUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 300, 28));

        cofimacaoSenha.setForeground(new java.awt.Color(255, 0, 88));
        cofimacaoSenha.setBorder(null);
        cofimacaoSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cofimacaoSenhaActionPerformed(evt);
            }
        });
        cofimacaoSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cofimacaoSenhaKeyReleased(evt);
            }
        });
        jPanelCadastraEstudante.add(cofimacaoSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 300, 28));

        jLabelMinimizeTelaCadastroEstudante.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelMinimizeTelaCadastroEstudante.setForeground(new java.awt.Color(51, 51, 51));
        jLabelMinimizeTelaCadastroEstudante.setText("  -");
        jLabelMinimizeTelaCadastroEstudante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaCadastroEstudanteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaCadastroEstudanteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaCadastroEstudanteMouseExited(evt);
            }
        });
        jPanelCadastraEstudante.add(jLabelMinimizeTelaCadastroEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 5, 30, 20));

        jLabelCloseTelaCadastroEstudante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCloseTelaCadastroEstudante.setForeground(new java.awt.Color(51, 51, 51));
        jLabelCloseTelaCadastroEstudante.setText("   X");
        jLabelCloseTelaCadastroEstudante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaCadastroEstudanteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaCadastroEstudanteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaCadastroEstudanteMouseExited(evt);
            }
        });
        jPanelCadastraEstudante.add(jLabelCloseTelaCadastroEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(918, 9, 30, -1));

        minimizeTelaCadEstudante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelCadastraEstudante.add(minimizeTelaCadEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 5, -1, -1));

        closeTelaCadEstudante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelCadastraEstudante.add(closeTelaCadEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 5, -1, -1));

        jLabelBackTelaEstudante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-20.png"))); // NOI18N
        jPanelCadastraEstudante.add(jLabelBackTelaEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        getContentPane().add(jPanelCadastraEstudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanelTelaTecAdmin.setBackground(new java.awt.Color(255, 59, 94));
        jPanelTelaTecAdmin.setPreferredSize(new java.awt.Dimension(960, 615));
        jPanelTelaTecAdmin.setVerifyInputWhenFocusTarget(false);
        jPanelTelaTecAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("  -");
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel30MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel30MouseExited(evt);
            }
        });
        jPanelTelaTecAdmin.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 5, 30, 20));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("   X");
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel39MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel39MouseExited(evt);
            }
        });
        jPanelTelaTecAdmin.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(918, 9, 30, -1));

        minimizeTelaAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaTecAdmin.add(minimizeTelaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 5, -1, -1));

        closeTelaAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaTecAdmin.add(closeTelaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 5, -1, -1));

        jLabel40.setBackground(new java.awt.Color(0, 0, 0));
        jLabel40.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Tela de administrador");
        jPanelTelaTecAdmin.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 60, -1, -1));

        jButtonCadastraLivroTelaAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCadastraLivroTelaAdmin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonCadastraLivroTelaAdmin.setForeground(new java.awt.Color(255, 59, 94));
        jButtonCadastraLivroTelaAdmin.setText("Cadastrar Livro");
        jButtonCadastraLivroTelaAdmin.setBorder(null);
        jButtonCadastraLivroTelaAdmin.setBorderPainted(false);
        jButtonCadastraLivroTelaAdmin.setFocusPainted(false);
        jButtonCadastraLivroTelaAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastraLivroTelaAdminActionPerformed(evt);
            }
        });
        jPanelTelaTecAdmin.add(jButtonCadastraLivroTelaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 440, 28));

        backTelaAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-200.png"))); // NOI18N
        backTelaAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backTelaAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backTelaAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backTelaAdminMouseExited(evt);
            }
        });
        jPanelTelaTecAdmin.add(backTelaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jButtonDeletarLivroTelaAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jButtonDeletarLivroTelaAdmin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonDeletarLivroTelaAdmin.setForeground(new java.awt.Color(255, 59, 94));
        jButtonDeletarLivroTelaAdmin.setText("Deletar livro");
        jButtonDeletarLivroTelaAdmin.setBorder(null);
        jButtonDeletarLivroTelaAdmin.setFocusPainted(false);
        jButtonDeletarLivroTelaAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletarLivroTelaAdminActionPerformed(evt);
            }
        });
        jPanelTelaTecAdmin.add(jButtonDeletarLivroTelaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 440, 28));

        jButtonDeletarUsuarioTelaAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jButtonDeletarUsuarioTelaAdmin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonDeletarUsuarioTelaAdmin.setForeground(new java.awt.Color(255, 59, 94));
        jButtonDeletarUsuarioTelaAdmin.setText("Deletar usuário");
        jButtonDeletarUsuarioTelaAdmin.setBorder(null);
        jButtonDeletarUsuarioTelaAdmin.setFocusPainted(false);
        jButtonDeletarUsuarioTelaAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletarUsuarioTelaAdminActionPerformed(evt);
            }
        });
        jPanelTelaTecAdmin.add(jButtonDeletarUsuarioTelaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 440, 28));

        jButtonCadastraEstudanteTelaAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCadastraEstudanteTelaAdmin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonCadastraEstudanteTelaAdmin.setForeground(new java.awt.Color(255, 59, 94));
        jButtonCadastraEstudanteTelaAdmin.setText("Cadastrar estudante");
        jButtonCadastraEstudanteTelaAdmin.setBorder(null);
        jButtonCadastraEstudanteTelaAdmin.setFocusPainted(false);
        jButtonCadastraEstudanteTelaAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastraEstudanteTelaAdminActionPerformed(evt);
            }
        });
        jPanelTelaTecAdmin.add(jButtonCadastraEstudanteTelaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 440, 28));

        jButtonCadastraProfessorTelaAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCadastraProfessorTelaAdmin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonCadastraProfessorTelaAdmin.setForeground(new java.awt.Color(255, 59, 94));
        jButtonCadastraProfessorTelaAdmin.setText("Cadastrar professor");
        jButtonCadastraProfessorTelaAdmin.setBorder(null);
        jButtonCadastraProfessorTelaAdmin.setFocusPainted(false);
        jButtonCadastraProfessorTelaAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastraProfessorTelaAdminActionPerformed(evt);
            }
        });
        jPanelTelaTecAdmin.add(jButtonCadastraProfessorTelaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 440, 28));

        jLabelBackTelaAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-20.png"))); // NOI18N
        jPanelTelaTecAdmin.add(jLabelBackTelaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        getContentPane().add(jPanelTelaTecAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanelTelaCadastraLivros.setBackground(new java.awt.Color(255, 59, 94));
        jPanelTelaCadastraLivros.setPreferredSize(new java.awt.Dimension(750, 500));
        jPanelTelaCadastraLivros.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tela de Cadastro de livros");
        jPanelTelaCadastraLivros.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 60, -1, -1));

        jLabelBookYear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelBookYear.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBookYear.setText("Ano");
        jPanelTelaCadastraLivros.add(jLabelBookYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 143, -1, -1));

        jLabelBookWeight.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelBookWeight.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBookWeight.setText("Peso do livro");
        jPanelTelaCadastraLivros.add(jLabelBookWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 203, -1, -1));

        nomeAutor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nomeAutor.setForeground(new java.awt.Color(255, 0, 88));
        nomeAutor.setBorder(null);
        nomeAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeAutorActionPerformed(evt);
            }
        });
        nomeAutor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomeAutorKeyReleased(evt);
            }
        });
        jPanelTelaCadastraLivros.add(nomeAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 640, 28));

        jLabelAutorName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelAutorName.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAutorName.setText("Nome do autor(es)");
        jPanelTelaCadastraLivros.add(jLabelAutorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 263, 120, -1));

        tituloLivro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tituloLivro.setForeground(new java.awt.Color(255, 0, 88));
        tituloLivro.setBorder(null);
        tituloLivro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tituloLivroKeyReleased(evt);
            }
        });
        jPanelTelaCadastraLivros.add(tituloLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 640, 28));

        jLabelBookTitle.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelBookTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBookTitle.setText("Título do livro");
        jPanelTelaCadastraLivros.add(jLabelBookTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 323, -1, -1));

        nomeEditora.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nomeEditora.setForeground(new java.awt.Color(255, 0, 88));
        nomeEditora.setBorder(null);
        nomeEditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeEditoraActionPerformed(evt);
            }
        });
        nomeEditora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomeEditoraKeyReleased(evt);
            }
        });
        jPanelTelaCadastraLivros.add(nomeEditora, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, 640, 28));

        jLabelPublisherName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelPublisherName.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPublisherName.setText("Nome da editora");
        jPanelTelaCadastraLivros.add(jLabelPublisherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 383, -1, -1));

        jReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-200.png"))); // NOI18N
        jReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jReturnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jReturnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jReturnMouseExited(evt);
            }
        });
        jPanelTelaCadastraLivros.add(jReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        anoLivro.setBorder(null);
        anoLivro.setForeground(new java.awt.Color(255, 0, 88));
        try {
            anoLivro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        anoLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anoLivroActionPerformed(evt);
            }
        });
        jPanelTelaCadastraLivros.add(anoLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 100, 28));

        jL_CadLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button_cadastrar-livro.png"))); // NOI18N
        jL_CadLivro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_CadLivroMouseClicked(evt);
            }
        });
        jPanelTelaCadastraLivros.add(jL_CadLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 530, -1, -1));

        pesoLivro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pesoLivro.setForeground(new java.awt.Color(255, 59, 94));
        pesoLivro.setBorder(null);
        pesoLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesoLivroActionPerformed(evt);
            }
        });
        pesoLivro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pesoLivroKeyReleased(evt);
            }
        });
        jPanelTelaCadastraLivros.add(pesoLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 100, 28));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Idioma");
        jPanelTelaCadastraLivros.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 143, -1, -1));

        idioma.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        idioma.setForeground(new java.awt.Color(255, 59, 94));
        idioma.setBorder(null);
        idioma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idiomaKeyReleased(evt);
            }
        });
        jPanelTelaCadastraLivros.add(idioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 230, 28));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("País");
        jPanelTelaCadastraLivros.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 143, -1, -1));

        pais.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pais.setForeground(new java.awt.Color(255, 59, 94));
        pais.setBorder(null);
        pais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paisActionPerformed(evt);
            }
        });
        pais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paisKeyReleased(evt);
            }
        });
        jPanelTelaCadastraLivros.add(pais, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 270, 28));

        jLabel26.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("ISBN");
        jPanelTelaCadastraLivros.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 203, -1, -1));

        jLabel27.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Nº páginas");
        jPanelTelaCadastraLivros.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 203, -1, -1));

        numPaginas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        numPaginas.setForeground(new java.awt.Color(255, 59, 94));
        numPaginas.setBorder(null);
        numPaginas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numPaginasActionPerformed(evt);
            }
        });
        numPaginas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numPaginasKeyReleased(evt);
            }
        });
        jPanelTelaCadastraLivros.add(numPaginas, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 180, 28));

        jLabel31.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Quantidade");
        jPanelTelaCadastraLivros.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 203, -1, -1));

        quantidadeBook.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        quantidadeBook.setForeground(new java.awt.Color(255, 59, 94));
        quantidadeBook.setBorder(null);
        quantidadeBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantidadeBookActionPerformed(evt);
            }
        });
        quantidadeBook.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantidadeBookKeyReleased(evt);
            }
        });
        jPanelTelaCadastraLivros.add(quantidadeBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, 110, 28));

        isbn.setBorder(null);
        isbn.setForeground(new java.awt.Color(255, 59, 94));
        try {
            isbn.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#############")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanelTelaCadastraLivros.add(isbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 220, 190, 28));

        jLabelBackTelaCadastroLivros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-20.png"))); // NOI18N
        jPanelTelaCadastraLivros.add(jLabelBackTelaCadastroLivros, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jLabelMinimizeTelaCadLivros.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelMinimizeTelaCadLivros.setForeground(new java.awt.Color(51, 51, 51));
        jLabelMinimizeTelaCadLivros.setText("  -");
        jLabelMinimizeTelaCadLivros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaCadLivrosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaCadLivrosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaCadLivrosMouseExited(evt);
            }
        });
        jPanelTelaCadastraLivros.add(jLabelMinimizeTelaCadLivros, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 5, 30, 20));

        minimizeTelaCadLivros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaCadastraLivros.add(minimizeTelaCadLivros, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 5, -1, -1));

        jLabelClose.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelClose.setForeground(new java.awt.Color(51, 51, 51));
        jLabelClose.setText("   X");
        jLabelClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseExited(evt);
            }
        });
        jPanelTelaCadastraLivros.add(jLabelClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(918, 9, 30, -1));

        closeTelaCadAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaCadastraLivros.add(closeTelaCadAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 5, -1, -1));

        getContentPane().add(jPanelTelaCadastraLivros, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 615));

        jPanelTelaDeletaUsuario.setBackground(new java.awt.Color(255, 59, 94));
        jPanelTelaDeletaUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("  -");
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel35MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel35MouseExited(evt);
            }
        });
        jPanelTelaDeletaUsuario.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 5, 30, 20));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("   X");
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel36MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel36MouseExited(evt);
            }
        });
        jPanelTelaDeletaUsuario.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(918, 9, 30, -1));

        minimizeTelaUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button.png"))); // NOI18N
        jPanelTelaDeletaUsuario.add(minimizeTelaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 5, -1, -1));

        closeDeletarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button.png"))); // NOI18N
        jPanelTelaDeletaUsuario.add(closeDeletarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 5, -1, -1));

        senhaDeletaUsuario.setForeground(new java.awt.Color(252, 48, 82));
        senhaDeletaUsuario.setBorder(null);
        senhaDeletaUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                senhaDeletaUsuarioKeyReleased(evt);
            }
        });
        jPanelTelaDeletaUsuario.add(senhaDeletaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 300, 28));

        professor.setBackground(new java.awt.Color(252, 48, 82));
        buttonGroupDeletaUser.add(professor);
        professor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        professor.setForeground(new java.awt.Color(255, 255, 255));
        professor.setText("Professor");
        professor.setBorder(null);
        professor.setContentAreaFilled(false);
        professor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        professor.setFocusPainted(false);
        jPanelTelaDeletaUsuario.add(professor, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 330, -1, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("CPF");
        jPanelTelaDeletaUsuario.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(329, 173, -1, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Senha");
        jPanelTelaDeletaUsuario.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(329, 243, -1, -1));

        deletaUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button (1).png"))); // NOI18N
        deletaUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletaUserMouseClicked(evt);
            }
        });
        jPanelTelaDeletaUsuario.add(deletaUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, -1, -1));

        cpfDeletaUsuario.setBorder(null);
        cpfDeletaUsuario.setForeground(new java.awt.Color(252, 48, 82));
        try {
            cpfDeletaUsuario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanelTelaDeletaUsuario.add(cpfDeletaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 300, 28));

        estudante.setBackground(new java.awt.Color(252, 48, 82));
        buttonGroupDeletaUser.add(estudante);
        estudante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        estudante.setForeground(new java.awt.Color(255, 255, 255));
        estudante.setText("Estudante");
        estudante.setBorder(null);
        estudante.setFocusPainted(false);
        jPanelTelaDeletaUsuario.add(estudante, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 330, -1, -1));

        jLabel43.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Deletar usuário");
        jPanelTelaDeletaUsuario.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 110, -1, -1));

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Annotation 2020-04-25 163815.png"))); // NOI18N
        jPanelTelaDeletaUsuario.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 100, 320, -1));

        jLabelBackTelaDeletaUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-200.png"))); // NOI18N
        jLabelBackTelaDeletaUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackTelaDeletaUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBackTelaDeletaUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBackTelaDeletaUserMouseExited(evt);
            }
        });
        jPanelTelaDeletaUsuario.add(jLabelBackTelaDeletaUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jLabelBackTelaDeletaUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-20.png"))); // NOI18N
        jPanelTelaDeletaUsuario.add(jLabelBackTelaDeletaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jLabelBackgroundTelaDeletaUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Annotation 2020-04-16 150218.png"))); // NOI18N
        jLabelBackgroundTelaDeletaUsuario.setText("jLabel5");
        jLabelBackgroundTelaDeletaUsuario.setPreferredSize(new java.awt.Dimension(960, 615));
        jPanelTelaDeletaUsuario.add(jLabelBackgroundTelaDeletaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 615));

        getContentPane().add(jPanelTelaDeletaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanelTelaCadastraProfessor.setBackground(new java.awt.Color(255, 59, 94));
        jPanelTelaCadastraProfessor.setPreferredSize(new java.awt.Dimension(960, 615));
        jPanelTelaCadastraProfessor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nomeProfessorr.setForeground(new java.awt.Color(255, 0, 88));
        nomeProfessorr.setBorder(null);
        nomeProfessorr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                nomeProfessorrMouseReleased(evt);
            }
        });
        nomeProfessorr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeProfessorrActionPerformed(evt);
            }
        });
        jPanelTelaCadastraProfessor.add(nomeProfessorr, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 640, 28));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nome");
        jPanelTelaCadastraProfessor.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 133, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("CPF");
        jPanelTelaCadastraProfessor.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 203, -1, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Senha");
        jPanelTelaCadastraProfessor.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 273, -1, -1));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Confirmar senha");
        jPanelTelaCadastraProfessor.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 343, -1, -1));

        jLabel25.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Data de nascimento");
        jPanelTelaCadastraProfessor.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 273, -1, -1));

        jLabelTextCadProfessor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelTextCadProfessor.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTextCadProfessor.setText("Cadastrar professor");
        jPanelTelaCadastraProfessor.add(jLabelTextCadProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 60, -1, -1));

        jLabelReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-200.png"))); // NOI18N
        jLabelReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelReturnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelReturnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelReturnMouseExited(evt);
            }
        });
        jPanelTelaCadastraProfessor.add(jLabelReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jLabel32.setBackground(new java.awt.Color(254, 254, 254));
        jLabel32.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(254, 254, 254));
        jLabel32.setText("Telefone");
        jPanelTelaCadastraProfessor.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 203, -1, -1));

        cpfProfessor.setBorder(null);
        cpfProfessor.setForeground(new java.awt.Color(255, 0, 88));
        try {
            cpfProfessor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cpfProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpfProfessorActionPerformed(evt);
            }
        });
        jPanelTelaCadastraProfessor.add(cpfProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 300, 28));

        telefoneProfessorr.setBorder(null);
        telefoneProfessorr.setForeground(new java.awt.Color(255, 0, 88));
        try {
            telefoneProfessorr.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("## # #### ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanelTelaCadastraProfessor.add(telefoneProfessorr, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, 300, 28));

        dataNascimento.setBorder(null);
        dataNascimento.setForeground(new java.awt.Color(255, 0, 88));
        try {
            dataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataNascimentoActionPerformed(evt);
            }
        });
        jPanelTelaCadastraProfessor.add(dataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, 300, 28));

        senhaProfessor.setForeground(new java.awt.Color(255, 0, 88));
        senhaProfessor.setBorder(null);
        senhaProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senhaProfessorActionPerformed(evt);
            }
        });
        senhaProfessor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                senhaProfessorKeyReleased(evt);
            }
        });
        jPanelTelaCadastraProfessor.add(senhaProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 300, 28));

        confimSenha.setForeground(new java.awt.Color(255, 0, 88));
        confimSenha.setBorder(null);
        confimSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confimSenhaActionPerformed(evt);
            }
        });
        confimSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                confimSenhaKeyReleased(evt);
            }
        });
        jPanelTelaCadastraProfessor.add(confimSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 300, 28));

        jLabel33.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(254, 254, 254));
        jLabel33.setText("ID docente 12 digitos");
        jPanelTelaCadastraProfessor.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 343, -1, -1));

        matriculaProfessor.setBorder(null);
        matriculaProfessor.setForeground(new java.awt.Color(255, 0, 88));
        try {
            matriculaProfessor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("############")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanelTelaCadastraProfessor.add(matriculaProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 360, 300, 28));

        jLabelMinimizaTelaCadastraProfessor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelMinimizaTelaCadastraProfessor.setForeground(new java.awt.Color(51, 51, 51));
        jLabelMinimizaTelaCadastraProfessor.setText("  -");
        jLabelMinimizaTelaCadastraProfessor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinimizaTelaCadastraProfessorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMinimizaTelaCadastraProfessorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMinimizaTelaCadastraProfessorMouseExited(evt);
            }
        });
        jPanelTelaCadastraProfessor.add(jLabelMinimizaTelaCadastraProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 5, 30, 20));

        minimizeTelaCadProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaCadastraProfessor.add(minimizeTelaCadProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 5, -1, -1));

        jLabelCloseTelaCadastraProfessor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCloseTelaCadastraProfessor.setForeground(new java.awt.Color(51, 51, 51));
        jLabelCloseTelaCadastraProfessor.setText("   X");
        jLabelCloseTelaCadastraProfessor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaCadastraProfessorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaCadastraProfessorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaCadastraProfessorMouseExited(evt);
            }
        });
        jPanelTelaCadastraProfessor.add(jLabelCloseTelaCadastraProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(918, 9, 30, -1));

        closeTelaCadProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaCadastraProfessor.add(closeTelaCadProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 5, -1, -1));

        jLabelBackTelaCadProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-20.png"))); // NOI18N
        jPanelTelaCadastraProfessor.add(jLabelBackTelaCadProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jButtonCadastraProfessor.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCadastraProfessor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonCadastraProfessor.setForeground(new java.awt.Color(255, 59, 94));
        jButtonCadastraProfessor.setText("Cadastrar professor");
        jButtonCadastraProfessor.setBorder(null);
        jButtonCadastraProfessor.setFocusPainted(false);
        jButtonCadastraProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastraProfessorActionPerformed(evt);
            }
        });
        jPanelTelaCadastraProfessor.add(jButtonCadastraProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 530, 352, 28));

        getContentPane().add(jPanelTelaCadastraProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanelTelaDeletarBooks.setBackground(new java.awt.Color(252, 48, 82));
        jPanelTelaDeletarBooks.setPreferredSize(new java.awt.Dimension(960, 615));
        jPanelTelaDeletarBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCloseTelaDeletaLivros.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCloseTelaDeletaLivros.setForeground(new java.awt.Color(51, 51, 51));
        jLabelCloseTelaDeletaLivros.setText("   X");
        jLabelCloseTelaDeletaLivros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaDeletaLivrosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaDeletaLivrosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaDeletaLivrosMouseExited(evt);
            }
        });
        jPanelTelaDeletarBooks.add(jLabelCloseTelaDeletaLivros, new org.netbeans.lib.awtextra.AbsoluteConstraints(918, 9, 30, -1));

        jLabelMinimizeTelaDeleteLivro.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelMinimizeTelaDeleteLivro.setForeground(new java.awt.Color(51, 51, 51));
        jLabelMinimizeTelaDeleteLivro.setText("  -");
        jLabelMinimizeTelaDeleteLivro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaDeleteLivroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaDeleteLivroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMinimizeTelaDeleteLivroMouseExited(evt);
            }
        });
        jPanelTelaDeletarBooks.add(jLabelMinimizeTelaDeleteLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 5, 30, 20));

        jLabelMinimizeTelaDeleteLivros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaDeletarBooks.add(jLabelMinimizeTelaDeleteLivros, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 5, -1, -1));

        jLabelCloseTelaDeleteLivros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaDeletarBooks.add(jLabelCloseTelaDeleteLivros, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 5, -1, -1));

        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-200.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBackMouseExited(evt);
            }
        });
        jPanelTelaDeletarBooks.add(jLabelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jLabelText.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelText.setForeground(new java.awt.Color(255, 255, 254));
        jLabelText.setText("Digite o título");
        jPanelTelaDeletarBooks.add(jLabelText, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 12, -1, -1));

        pesquisaLivro.setBorder(null);
        pesquisaLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisaLivroActionPerformed(evt);
            }
        });
        pesquisaLivro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pesquisaLivroKeyReleased(evt);
            }
        });
        jPanelTelaDeletarBooks.add(pesquisaLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 6, 310, 26));

        jTableDeleteBooks.setBackground(new java.awt.Color(254, 254, 254));
        jTableDeleteBooks.setForeground(new java.awt.Color(255, 0, 88));
        jTableDeleteBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título", "Nome do Autor", "Nome da editora", "ISBN", "Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDeleteBooks.setGridColor(new java.awt.Color(255, 255, 255));
        jTableDeleteBooks.setRequestFocusEnabled(false);
        jTableDeleteBooks.setRowHeight(25);
        jTableDeleteBooks.setSelectionBackground(new java.awt.Color(255, 102, 102));
        jTableDeleteBooks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableDeleteBooks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableDeleteBooks.setShowHorizontalLines(false);
        jTableDeleteBooks.setShowVerticalLines(false);
        jTableDeleteBooks.getTableHeader().setReorderingAllowed(false);
        jTableDeleteBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDeleteBooksMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableDeleteBooks);

        jPanelTelaDeletarBooks.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 40, 965, 577));

        deletaBook.setBackground(new java.awt.Color(250, 251, 254));
        deletaBook.setForeground(new java.awt.Color(252, 48, 82));
        deletaBook.setText("Deletar");
        deletaBook.setBorder(null);
        deletaBook.setFocusPainted(false);
        deletaBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletaBookActionPerformed(evt);
            }
        });
        jPanelTelaDeletarBooks.add(deletaBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 6, 148, 26));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-20.png"))); // NOI18N
        jPanelTelaDeletarBooks.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        getContentPane().add(jPanelTelaDeletarBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 615));

        jPanelTelaDevolucao.setBackground(new java.awt.Color(255, 59, 94));
        jPanelTelaDevolucao.setEnabled(false);
        jPanelTelaDevolucao.setPreferredSize(new java.awt.Dimension(960, 615));
        jPanelTelaDevolucao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBack1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-200.png"))); // NOI18N
        jLabelBack1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBack1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBack1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBack1MouseExited(evt);
            }
        });
        jPanelTelaDevolucao.add(jLabelBack1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jTableBookDevolucao.setBackground(new java.awt.Color(254, 254, 254));
        jTableBookDevolucao.setForeground(new java.awt.Color(51, 51, 51));
        jTableBookDevolucao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome do usuário", "CPF ", "ISBN", "Data empréstimo", "Data prevista", "Data devolução", "IdEmp", "Multa "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBookDevolucao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableBookDevolucao.setGridColor(new java.awt.Color(255, 255, 255));
        jTableBookDevolucao.setRequestFocusEnabled(false);
        jTableBookDevolucao.setRowHeight(25);
        jTableBookDevolucao.setSelectionBackground(new java.awt.Color(255, 102, 102));
        jTableBookDevolucao.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jTableBookDevolucao.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableBookDevolucao.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableBookDevolucao.setShowHorizontalLines(false);
        jTableBookDevolucao.setShowVerticalLines(false);
        jTableBookDevolucao.getTableHeader().setReorderingAllowed(false);
        jTableBookDevolucao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBookDevolucaoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableBookDevolucao);
        if (jTableBookDevolucao.getColumnModel().getColumnCount() > 0) {
            jTableBookDevolucao.getColumnModel().getColumn(0).setResizable(false);
            jTableBookDevolucao.getColumnModel().getColumn(0).setPreferredWidth(400);
            jTableBookDevolucao.getColumnModel().getColumn(1).setResizable(false);
            jTableBookDevolucao.getColumnModel().getColumn(1).setPreferredWidth(55);
            jTableBookDevolucao.getColumnModel().getColumn(2).setResizable(false);
            jTableBookDevolucao.getColumnModel().getColumn(2).setPreferredWidth(70);
            jTableBookDevolucao.getColumnModel().getColumn(3).setResizable(false);
            jTableBookDevolucao.getColumnModel().getColumn(4).setResizable(false);
            jTableBookDevolucao.getColumnModel().getColumn(4).setPreferredWidth(50);
            jTableBookDevolucao.getColumnModel().getColumn(5).setResizable(false);
            jTableBookDevolucao.getColumnModel().getColumn(5).setPreferredWidth(60);
            jTableBookDevolucao.getColumnModel().getColumn(6).setResizable(false);
            jTableBookDevolucao.getColumnModel().getColumn(6).setPreferredWidth(5);
            jTableBookDevolucao.getColumnModel().getColumn(7).setResizable(false);
            jTableBookDevolucao.getColumnModel().getColumn(7).setPreferredWidth(15);
        }

        jPanelTelaDevolucao.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 30, 964, 590));

        jLabelCloseTelaDevolucao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCloseTelaDevolucao.setForeground(new java.awt.Color(51, 51, 51));
        jLabelCloseTelaDevolucao.setText("   X");
        jLabelCloseTelaDevolucao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaDevolucaoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaDevolucaoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCloseTelaDevolucaoMouseExited(evt);
            }
        });
        jPanelTelaDevolucao.add(jLabelCloseTelaDevolucao, new org.netbeans.lib.awtextra.AbsoluteConstraints(918, 9, 30, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("  -");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel13MouseExited(evt);
            }
        });
        jPanelTelaDevolucao.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 5, 30, 20));

        jLabelMinimizejPanelTelaDevolucao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaDevolucao.add(jLabelMinimizejPanelTelaDevolucao, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 5, -1, -1));

        jLabelClosejPanelTelaDevolucao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonBranco.png"))); // NOI18N
        jPanelTelaDevolucao.add(jLabelClosejPanelTelaDevolucao, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 5, -1, -1));

        jLabelBackTelaDevolucao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/171489-20.png"))); // NOI18N
        jPanelTelaDevolucao.add(jLabelBackTelaDevolucao, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nº CPF");
        jPanelTelaDevolucao.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 9, 60, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 59, 94));
        jButton1.setText("Pesquisar");
        jButton1.setBorder(null);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelTelaDevolucao.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 3, 90, 25));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 59, 94));
        jButton2.setText("Efetuar devolução");
        jButton2.setBorder(null);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanelTelaDevolucao.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 3, 130, 25));

        jFormattedTextField1.setBorder(null);
        jFormattedTextField1.setForeground(new java.awt.Color(255, 59, 94));
        jFormattedTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField1KeyReleased(evt);
            }
        });
        jPanelTelaDevolucao.add(jFormattedTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 3, 230, 25));

        getContentPane().add(jPanelTelaDevolucao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 615));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    private void cleanFieldsJPanelLoginScreen() {

        jFormattedTextFieldCpfUsuarioLogin.setText(null);
        jFormattedTextFieldCpfUsuarioLogin.setVisible(true);
        jFormattedTextFieldCpfUsuarioLogin.setBorder(null);
        jLabelAlertCpfUsuarioLogin.setVisible(false);

        jFormattedTextFieldSenhaUsuarioLogin.setText(null);
        jFormattedTextFieldSenhaUsuarioLogin.setVisible(true);
        jFormattedTextFieldSenhaUsuarioLogin.setBorder(null);
        jLabelAlertSenhaLogin.setVisible(false);
        jLabelBackTelaDeleteLivros.setVisible(true);
    }
    
    private void cleanFieldsJPanelCadastraEstudante() {
        nameStudent.setText(null);
        cpfUser.setText(null);
        phoneUser.setText(null);
        passwordUser.setText(null);
        birthDate.setText(null);
        cofimacaoSenha.setText(null);
        codUsuario.setText(null);
    }
    private void cleanFieldsJPanelCadastraProfessor() {
        nomeProfessorr.setText(null);
        cpfProfessor.setText(null);
        telefoneProfessorr.setText(null);
        senhaProfessor.setText(null);
        dataNascimento.setText(null);
        confimSenha.setText(null);
        matriculaProfessor.setText(null);
    }

    private void jPanelTelaRecuperarSenha(boolean ativaTelaRecuperaSenha) {

        jPanelTelaRecuperarSenha.setVisible(ativaTelaRecuperaSenha);
        jLabel28.setVisible(ativaTelaRecuperaSenha);
        jLabeBackgroundlRecuperarSenha.setVisible(ativaTelaRecuperaSenha);
        jLabelCpfEstudante.setVisible(ativaTelaRecuperaSenha);
        jLabelData.setVisible(ativaTelaRecuperaSenha);
        jLabelNovaSenha.setVisible(ativaTelaRecuperaSenha);
        jLabelConfirmarSenha.setVisible(ativaTelaRecuperaSenha);

        cpfRecuperaSenha.setVisible(ativaTelaRecuperaSenha);
        cpfRecuperaSenha.setEnabled(ativaTelaRecuperaSenha);

        dataNasciRecuperaSenha.setVisible(ativaTelaRecuperaSenha);
        dataNasciRecuperaSenha.setEnabled(ativaTelaRecuperaSenha);

        newPasswordRecuperaSenha.setVisible(ativaTelaRecuperaSenha);
        newPasswordRecuperaSenha.setEnabled(ativaTelaRecuperaSenha);

        confirmaSenhaRecuperaSenha.setVisible(ativaTelaRecuperaSenha);
        confirmaSenhaRecuperaSenha.setEnabled(ativaTelaRecuperaSenha);

        minimize.setVisible(ativaTelaRecuperaSenha);
        minimize.setEnabled(ativaTelaRecuperaSenha);

        close.setVisible(ativaTelaRecuperaSenha);
        close.setEnabled(ativaTelaRecuperaSenha);

        backTelaRecuperaSenha.setVisible(ativaTelaRecuperaSenha);
        backTelaRecuperaSenha.setEnabled(ativaTelaRecuperaSenha);

    }

    public void alertjPanelRecuperaSenha(boolean alertSuscesso, boolean alertErro) {
        jPanelAlertSucessoRecuperaSenha.setVisible(alertSuscesso);
        jPanelAlertErroRecuperaSenha.setVisible(alertErro);
    }

    private void setNullFieldsTelaRecuperaSenha() {

        jLabeBackgroundlRecuperarSenha.setVisible(false);
        cpfRecuperaSenha.setText(null);
        dataNasciRecuperaSenha.setText(null);
        newPasswordRecuperaSenha.setText(null);
        confirmaSenhaRecuperaSenha.setText(null);

    }

    private void recuperarSenhaLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recuperarSenhaLoginMouseClicked

        cleanTelaRecuperaSenha();
        setNullFieldsTelaRecuperaSenha();
        jPanelTelaRecuperarSenha(true);

        jFormattedTextFieldCpfUsuarioLogin.setValue(null);
        jFormattedTextFieldCpfUsuarioLogin.setEnabled(false);
        jFormattedTextFieldCpfUsuarioLogin.setVisible(false);
        jLabelAlertCpfUsuarioLogin.setVisible(false);
        jFormattedTextFieldCpfUsuarioLogin.setBorder(lineBordeOFF);

        jFormattedTextFieldSenhaUsuarioLogin.setText(null);
        jFormattedTextFieldSenhaUsuarioLogin.setEnabled(false);
        jFormattedTextFieldSenhaUsuarioLogin.setVisible(false);
        jLabelAlertSenhaLogin.setVisible(false);
        jFormattedTextFieldSenhaUsuarioLogin.setBorder(lineBordeOFF);

        jPanelProfessorEstudanteAdmin.setEnabled(false);
        jPanelProfessorEstudanteAdmin.setVisible(false);
        jPanelProfessorEstudanteAdmin.setBorder(null);

        buttonGroup1.clearSelection();

        botaoProfessorLogin.setEnabled(false);
        botaoProfessorLogin.setVisible(false);

        botaoEstudanteLogin.setEnabled(false);
        botaoEstudanteLogin.setVisible(false);

        botaoAdminLogin.setEnabled(false);
        botaoAdminLogin.setVisible(false);

        jPanelLoginScreen.setEnabled(false);
        jPanelLoginScreen.setVisible(false);
        jPanelTelaRecuperarSenha.setVisible(true);

    }//GEN-LAST:event_recuperarSenhaLoginMouseClicked

    private void jFormattedTextFieldSenhaUsuarioLoginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldSenhaUsuarioLoginKeyReleased

        if (String.valueOf(jFormattedTextFieldSenhaUsuarioLogin.getPassword()).trim().length() > 8)
            jFormattedTextFieldSenhaUsuarioLogin.setText(String.valueOf(jFormattedTextFieldSenhaUsuarioLogin.getPassword()).trim().substring(0, 8));
    }//GEN-LAST:event_jFormattedTextFieldSenhaUsuarioLoginKeyReleased

    private void jFormattedTextFieldSenhaUsuarioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldSenhaUsuarioLoginActionPerformed

    }//GEN-LAST:event_jFormattedTextFieldSenhaUsuarioLoginActionPerformed

    private void jFormattedTextFieldCpfUsuarioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCpfUsuarioLoginActionPerformed

    }//GEN-LAST:event_jFormattedTextFieldCpfUsuarioLoginActionPerformed

    private void jLabelClosejPanelLoginScreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelClosejPanelLoginScreenMouseClicked
        xamppExec.closeApacheAndMysql();
        System.exit(0);
    }//GEN-LAST:event_jLabelClosejPanelLoginScreenMouseClicked

    private void jLabelClosejPanelLoginScreenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelClosejPanelLoginScreenMouseEntered
        closeTelaLogin.setVisible(true);
    }//GEN-LAST:event_jLabelClosejPanelLoginScreenMouseEntered

    private void jLabelClosejPanelLoginScreenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelClosejPanelLoginScreenMouseExited
        closeTelaLogin.setVisible(false);
    }//GEN-LAST:event_jLabelClosejPanelLoginScreenMouseExited

    private void jLabelMinimizejPanelLoginScreenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizejPanelLoginScreenMouseEntered
        minimizeTelaLogin.setVisible(true);
    }//GEN-LAST:event_jLabelMinimizejPanelLoginScreenMouseEntered

    private void jLabelMinimizejPanelLoginScreenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizejPanelLoginScreenMouseExited
        minimizeTelaLogin.setVisible(false);
    }//GEN-LAST:event_jLabelMinimizejPanelLoginScreenMouseExited

    private void jLabelMinimizejPanelLoginScreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizejPanelLoginScreenMouseClicked
        minimizeTelaLogin.setVisible(false);
        setState(Frame.ICONIFIED);     
    }//GEN-LAST:event_jLabelMinimizejPanelLoginScreenMouseClicked

    private void jButtonjPanelLoginScreenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonjPanelLoginScreenMouseEntered
        jButtonjPanelLoginScreen.setBackground(newColor);
    }//GEN-LAST:event_jButtonjPanelLoginScreenMouseEntered

    private void jButtonjPanelLoginScreenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonjPanelLoginScreenMouseExited
        jButtonjPanelLoginScreen.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonjPanelLoginScreenMouseExited

    private boolean loginEmptyFields() {

        if (jFormattedTextFieldCpfUsuarioLogin.getText().trim().isEmpty()) {
            jLabelAlertCpfUsuarioLogin.setVisible(true);
            jFormattedTextFieldCpfUsuarioLogin.setValue(null);
            jFormattedTextFieldCpfUsuarioLogin.setBorder(lineBordeON);
            return true;
        } else {
            jLabelAlertCpfUsuarioLogin.setVisible(false);
            jFormattedTextFieldCpfUsuarioLogin.setBorder(lineBordeOFF);
        }

        if (new String(jFormattedTextFieldSenhaUsuarioLogin.getPassword()).trim().isEmpty()) {
            jLabelAlertSenhaLogin.setVisible(true);
            jFormattedTextFieldSenhaUsuarioLogin.setBorder(lineBordeON);
            return true;
        } else {
            jLabelAlertSenhaLogin.setVisible(false);
            jFormattedTextFieldSenhaUsuarioLogin.setBorder(lineBordeOFF);
        }

        return false;
    }

    public void desabilitaSplashScreen() {

        jPanel_splashScreen.setVisible(false);
        jProgressBar1.setVisible(false);
        jLabelImageSplashScreen.setVisible(false);

    }

    public void jPanelTelaTecAdmin(boolean ativa) {

        jPanelTelaTecAdmin.setVisible(ativa);
        jLabel40.setVisible(ativa);
        jButtonCadastraLivroTelaAdmin.setVisible(ativa);
        jButtonDeletarLivroTelaAdmin.setVisible(ativa);
        jButtonDeletarUsuarioTelaAdmin.setVisible(ativa);
        jButtonCadastraProfessorTelaAdmin.setVisible(ativa);
        jButtonCadastraEstudanteTelaAdmin.setVisible(ativa);

        jLabel30.setVisible(ativa);
        jLabel30.setEnabled(ativa);

        jLabel39.setVisible(ativa);
        jLabel39.setEnabled(ativa);

        backTelaAdmin.setVisible(ativa);
        backTelaAdmin.setEnabled(ativa);

    }

    private void jPanelEstudante(boolean ativa) {

        jPanelTelaEstudante.setVisible(ativa);
        jLabelIcon.setVisible(ativa);
        jPanel5.setVisible(ativa);
        jLabel7.setVisible(ativa);
        infoUser.setVisible(ativa);
        jButtonEmprestimo.setVisible(ativa);
        jButtonAlteraçãoTelaAdmin1.setVisible(ativa);
        jButtonAlteraçãoTelaAdmin1.setEnabled(ativa);
        jLabelBackjPanelTelaEstudante.setVisible(ativa);
        jLabelBackjPanelTelaEstudante.setEnabled(ativa);

    }


    private void jButtonjPanelLoginScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonjPanelLoginScreenActionPerformed

        if (loginEmptyFields()) {
            loginEmptyFields();
        } else {

            String passwordFieldSenha = new String(jFormattedTextFieldSenhaUsuarioLogin.getPassword());

            if (botaoProfessorLogin.isSelected()) {

                jPanelProfessorEstudanteAdmin.setBorder(lineBordeOFF);

                if (new CheckUser().verificaUsuario(jFormattedTextFieldCpfUsuarioLogin.getText(), passwordFieldSenha, "Professor")) {

                    new LoginScreen().setUser("Professor");

                    showNameProfessor.setText(verificaUsuario.nomeUsuario());
                    jPanelLoginScreen(false);
                    jPanelTelaProfessor(true);
                    buttonGroup1.clearSelection();

                } else {
                    JOptionPane.showMessageDialog(null, "Professor não encontrado!");
                }
            } else if (botaoEstudanteLogin.isSelected()) {

                jPanelProfessorEstudanteAdmin.setBorder(lineBordeOFF);

                if (new CheckUser().verificaUsuario(jFormattedTextFieldCpfUsuarioLogin.getText(), passwordFieldSenha, "Estudante")) {

                    new LoginScreen().setUser("Estudante");

                    infoUser.setText(verificaUsuario.nomeUsuario());
                    jPanelLoginScreen(false);
                    jPanelEstudante(true);
                    buttonGroup1.clearSelection();

                } else {
                    JOptionPane.showMessageDialog(null, "Estudante não encontrado!");
                }
            } else if (botaoAdminLogin.isSelected()) {

                jPanelProfessorEstudanteAdmin.setBorder(lineBordeOFF);

                if (new CheckUser().verificaUsuario(jFormattedTextFieldCpfUsuarioLogin.getText(), passwordFieldSenha, "Admin")) {

                    new LoginScreen().setUser("Admin");

                    jPanelLoginScreen(false);
                    jPanelTelaTecAdmin(true);
                    buttonGroup1.clearSelection();

                } else {
                    JOptionPane.showMessageDialog(null, "Admin não encontrado!");
                }
            } else {
                jPanelProfessorEstudanteAdmin.setBorder(lineBordeON);
                JOptionPane.showMessageDialog(null, "Nenhuma opcao selecionada!");
            }
        }
    }//GEN-LAST:event_jButtonjPanelLoginScreenActionPerformed

    private void jPanelTelaProfessor(boolean ativa) {

        jPanelTelaProfessor.setVisible(ativa);
        jLabelIconTelaProfessor.setVisible(ativa);
        jLabel22.setVisible(ativa);
        jPanel4.setVisible(ativa);
        showNameProfessor.setVisible(ativa);
        jLabelEmprestimo.setVisible(ativa);

        jLabelMinimizeTelaProfessor.setVisible(ativa);
        jLabelMinimizeTelaProfessor.setEnabled(ativa);

        jLabelCloseTelaProfessor.setVisible(ativa);
        jLabelCloseTelaProfessor.setEnabled(ativa);

        backTelaProfessor.setVisible(ativa);
        backTelaProfessor.setEnabled(ativa);

    }
    private void botaoAdminLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdminLoginActionPerformed

    }//GEN-LAST:event_botaoAdminLoginActionPerformed

    private void botaoEstudanteLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEstudanteLoginActionPerformed

    }//GEN-LAST:event_botaoEstudanteLoginActionPerformed

    private void botaoProfessorLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoProfessorLoginActionPerformed

    }//GEN-LAST:event_botaoProfessorLoginActionPerformed

    private void jLabelCloseTelaEstudanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaEstudanteMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseTelaEstudanteMouseClicked

    private void jLabelCloseTelaEstudanteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaEstudanteMouseEntered
        jLabelClosejPanelTelaEstudante.setVisible(true);
    }//GEN-LAST:event_jLabelCloseTelaEstudanteMouseEntered

    private void jLabelCloseTelaEstudanteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaEstudanteMouseExited
        jLabelClosejPanelTelaEstudante.setVisible(false);
    }//GEN-LAST:event_jLabelCloseTelaEstudanteMouseExited

    private void jPanelTelaEmprestimo(boolean ativa) {

        jLabelBackTelaEmprestimo.setEnabled(ativa);
        jLabelBackTelaEmprestimo.setVisible(ativa);
        
        jPanelTelaEmprestimo.setVisible(ativa);
        jLabel15.setVisible(ativa);
        efetuarEmprestimo.setVisible(ativa);

        isbnLivro.setEnabled(ativa);
        isbnLivro.setVisible(ativa);

        jLabelMinimizeTelaEmprestimo.setEnabled(ativa);
        jLabelMinimizeTelaEmprestimo.setVisible(ativa);

        jLabelCloseTelaEmprestimo.setEnabled(ativa);
        jLabelCloseTelaEmprestimo.setVisible(ativa);

        jTableBookEmprestimo.setEnabled(ativa);
        jTableBookEmprestimo.setVisible(ativa);

    }

    private void jButtonEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEmprestimoMouseClicked

        searchInput(isbnLivro.getText(), jTableBookEmprestimo);
        jPanelEstudante(false);
        jPanelTelaEmprestimo(true);


    }//GEN-LAST:event_jButtonEmprestimoMouseClicked

    public void jPanelTelaDevolucao(boolean ativa) {

        jFormattedTextField1.setEnabled(ativa);
        jFormattedTextField1.setVisible(ativa);
        jPanelTelaDevolucao.setEnabled(ativa);
        jPanelTelaDevolucao.setVisible(ativa);
        jTableBookDevolucao.setEnabled(ativa);
        jTableBookDevolucao.setVisible(ativa);
        jLabelBack1.setEnabled(ativa);
        jLabelBack1.setVisible(ativa);
        jLabel13.setEnabled(ativa);
        jLabel13.setVisible(ativa);
        jButton2.setVisible(ativa);
        jButton2.setEnabled(ativa);
        jButton1.setEnabled(ativa);
        jButton1.setVisible(ativa);

    }


    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        jLabelMinimizejPanelTelaEstudante.setVisible(true);
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        jLabelMinimizejPanelTelaEstudante.setVisible(false);
    }//GEN-LAST:event_jLabel12MouseExited

    private void jLabelBackjPanelTelaEstudanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackjPanelTelaEstudanteMouseClicked
        jPanelLoginScreen(true);
        jPanelEstudante(false);
    }//GEN-LAST:event_jLabelBackjPanelTelaEstudanteMouseClicked

    private void jLabelBackjPanelTelaEstudanteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackjPanelTelaEstudanteMouseEntered
        jLabelBackVisiblejPanelTelaEstudante.setVisible(true);
    }//GEN-LAST:event_jLabelBackjPanelTelaEstudanteMouseEntered

    private void jLabelBackjPanelTelaEstudanteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackjPanelTelaEstudanteMouseExited
        jLabelBackVisiblejPanelTelaEstudante.setVisible(false);
    }//GEN-LAST:event_jLabelBackjPanelTelaEstudanteMouseExited

    private void jLabelBackTelaEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackTelaEmprestimoMouseClicked

        if ("Professor".equals(new LoginScreen().getUser())) {

            jPanelTelaEmprestimo(false);
            jPanelTelaProfessor(true);

        } else {

            jPanelTelaEmprestimo(false);
            jPanelEstudante(true);

        }
    }//GEN-LAST:event_jLabelBackTelaEmprestimoMouseClicked

    private void jLabelBackTelaEmprestimoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackTelaEmprestimoMouseEntered
        jLabel18.setVisible(true);
    }//GEN-LAST:event_jLabelBackTelaEmprestimoMouseEntered

    private void jLabelBackTelaEmprestimoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackTelaEmprestimoMouseExited
        jLabel18.setVisible(false);
    }//GEN-LAST:event_jLabelBackTelaEmprestimoMouseExited

    private void isbnLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isbnLivroActionPerformed

    }//GEN-LAST:event_isbnLivroActionPerformed

    private void isbnLivroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_isbnLivroKeyReleased
        searchInput(isbnLivro.getText(), jTableBookEmprestimo);
    }//GEN-LAST:event_isbnLivroKeyReleased

    private void jTableBookEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBookEmprestimoMouseClicked

    }//GEN-LAST:event_jTableBookEmprestimoMouseClicked

    private void efetuarEmprestimoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efetuarEmprestimoMouseEntered
        efetuarEmprestimo.setBackground(newColor);
    }//GEN-LAST:event_efetuarEmprestimoMouseEntered

    private void efetuarEmprestimoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efetuarEmprestimoMouseExited
        efetuarEmprestimo.setBackground(Color.WHITE);
    }//GEN-LAST:event_efetuarEmprestimoMouseExited

    private void efetuarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efetuarEmprestimoActionPerformed

        if (jTableBookEmprestimo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Escolha um livro!");
        } else {

            int idLivro = (int) jTableBookEmprestimo.getValueAt(jTableBookEmprestimo.getSelectedRow(), COLUMN_ID_USUARIO);

            if (verificaUsuario.valorMulta() > 0) {
                JOptionPane.showMessageDialog(null, verificaUsuario.nomeUsuario() + " está com multa de R$ " + verificaUsuario.valorMulta() + "\nNão foi possível efeturar o empréstimo.");
            } else if (new ControleEmprestimo().controleEmprestimo(idLivro)) {
                JOptionPane.showMessageDialog(null, "Empréstimo realizado com sucesso!");
                searchInput(isbnLivro.getText(), jTableBookEmprestimo);
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível realizar o empréstimo!");
            }
        }
    }//GEN-LAST:event_efetuarEmprestimoActionPerformed

    private void jLabelMinimizeTelaEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaEmprestimoMouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabelMinimizeTelaEmprestimoMouseClicked

    private void jLabelMinimizeTelaEmprestimoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaEmprestimoMouseEntered
        minimizeTelaEmprestimo.setVisible(true);
    }//GEN-LAST:event_jLabelMinimizeTelaEmprestimoMouseEntered

    private void jLabelMinimizeTelaEmprestimoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaEmprestimoMouseExited
        minimizeTelaEmprestimo.setVisible(false);
    }//GEN-LAST:event_jLabelMinimizeTelaEmprestimoMouseExited

    private void jLabelCloseTelaEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaEmprestimoMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseTelaEmprestimoMouseClicked

    private void jLabelCloseTelaEmprestimoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaEmprestimoMouseEntered
        closeTelaEmprestimo.setVisible(true);
    }//GEN-LAST:event_jLabelCloseTelaEmprestimoMouseEntered

    private void jLabelCloseTelaEmprestimoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaEmprestimoMouseExited
        closeTelaEmprestimo.setVisible(false);
    }//GEN-LAST:event_jLabelCloseTelaEmprestimoMouseExited

    private void jLabelCloseTelaProfessorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaProfessorMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseTelaProfessorMouseClicked

    private void jLabelCloseTelaProfessorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaProfessorMouseEntered
        closeTelaProfessor.setVisible(true);
    }//GEN-LAST:event_jLabelCloseTelaProfessorMouseEntered

    private void jLabelCloseTelaProfessorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaProfessorMouseExited
        closeTelaProfessor.setVisible(false);
    }//GEN-LAST:event_jLabelCloseTelaProfessorMouseExited

    private void jLabelMinimizeTelaProfessorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaProfessorMouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabelMinimizeTelaProfessorMouseClicked

    private void jLabelMinimizeTelaProfessorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaProfessorMouseEntered
        minimizeTelaProfessor.setVisible(true);
    }//GEN-LAST:event_jLabelMinimizeTelaProfessorMouseEntered

    private void jLabelMinimizeTelaProfessorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaProfessorMouseExited
        minimizeTelaProfessor.setVisible(false);
    }//GEN-LAST:event_jLabelMinimizeTelaProfessorMouseExited

    private void backTelaProfessorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backTelaProfessorMouseClicked

        jPanelLoginScreen(true);
        jPanelTelaProfessor(false);

    }//GEN-LAST:event_backTelaProfessorMouseClicked

    private void backTelaProfessorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backTelaProfessorMouseEntered
        jLabelBackTelaProfessor.setVisible(true);
    }//GEN-LAST:event_backTelaProfessorMouseEntered

    private void backTelaProfessorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backTelaProfessorMouseExited
        jLabelBackTelaProfessor.setVisible(false);
    }//GEN-LAST:event_backTelaProfessorMouseExited

    private void jLabelEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEmprestimoMouseClicked

        jPanelTelaProfessor(false);
        searchInput(isbnLivro.getText(), jTableBookEmprestimo);
        jPanelTelaEmprestimo(true);

    }//GEN-LAST:event_jLabelEmprestimoMouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeMouseClicked

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        closeTelaRecuperaSenha.setVisible(true);
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        closeTelaRecuperaSenha.setVisible(false);
    }//GEN-LAST:event_closeMouseExited

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked

    private void minimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseEntered
        minimizeTelaRecuperaSenha.setVisible(true);
    }//GEN-LAST:event_minimizeMouseEntered

    private void minimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseExited
        minimizeTelaRecuperaSenha.setVisible(false);
    }//GEN-LAST:event_minimizeMouseExited

    private void confirmaSenhaRecuperaSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmaSenhaRecuperaSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmaSenhaRecuperaSenhaActionPerformed

    private void confirmaSenhaRecuperaSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmaSenhaRecuperaSenhaKeyReleased
        if (String.valueOf(confirmaSenhaRecuperaSenha.getPassword()).trim().length() > 8) {
            confirmaSenhaRecuperaSenha.setText(String.valueOf(confirmaSenhaRecuperaSenha.getPassword()).trim().substring(0, 8));
        }
    }//GEN-LAST:event_confirmaSenhaRecuperaSenhaKeyReleased

    private boolean loginEmptyFields1() {

        if (cpfRecuperaSenha.getText().trim().isEmpty()) {
            alertCpfRecuperaSenha.setVisible(true);
            cpfRecuperaSenha.setBorder(lineBordeON);
            return true;
        } else {
            alertCpfRecuperaSenha.setVisible(false);
            cpfRecuperaSenha.setBorder(lineBordeOFF);
        }

        if (dataNasciRecuperaSenha.getText().trim().isEmpty()) {
            alertDataNascimentoRecuperaSenha.setVisible(true);
            dataNasciRecuperaSenha.setBorder(lineBordeON);
            return true;
        } else {
            alertDataNascimentoRecuperaSenha.setVisible(false);
            dataNasciRecuperaSenha.setBorder(lineBordeOFF);
        }

        if (this.novaSenha.trim().isEmpty()) {
            newPasswordRecuperaSenha.setBorder(lineBordeON);
            alertNewPasswordRecuperaSenha.setVisible(true);
            return true;
        } else {
            newPasswordRecuperaSenha.setBorder(lineBordeOFF);
            alertNewPasswordRecuperaSenha.setVisible(false);
        }

        if (this.confSenha.trim().isEmpty()) {
            confirmaSenhaRecuperaSenha.setBorder(lineBordeON);
            alertConfPasswordRecuperaSenha.setVisible(true);
            return true;
        } else {
            confirmaSenhaRecuperaSenha.setBorder(lineBordeOFF);
            alertConfPasswordRecuperaSenha.setVisible(false);
        }
        return false;
    }

    private void closeFieldsTelaRecuperaSenha() {

        jLabeBackgroundlRecuperarSenha.setVisible(false);

        jLabelCpfEstudante.setVisible(false);
        cpfRecuperaSenha.setVisible(false);
        cpfRecuperaSenha.setEnabled(false);

        jLabelData.setVisible(false);
        dataNasciRecuperaSenha.setVisible(false);
        dataNasciRecuperaSenha.setEnabled(false);

        jLabelNovaSenha.setVisible(false);
        newPasswordRecuperaSenha.setVisible(false);
        newPasswordRecuperaSenha.setEnabled(false);

        jLabelConfirmarSenha.setVisible(false);
        confirmaSenhaRecuperaSenha.setVisible(false);
        confirmaSenhaRecuperaSenha.setEnabled(false);
    }

    public void jPanelAlertSucessoRecuperaSenha(boolean sucesso) {

        jPanelAlertSucessoRecuperaSenha.setVisible(sucesso);
        jLabelImageOK.setVisible(sucesso);
        jLabelTextoOK.setVisible(sucesso);
        jButtonOkRecuperarSenha.setVisible(sucesso);

    }

    public void jPanelAlertErroRecuperaSenha(boolean erro) {

        jPanelAlertErroRecuperaSenha.setVisible(erro);
        jLabelImageErro.setVisible(erro);
        jButtonRecuperarSenhaPanelErro.setVisible(erro);
        jLabelTextoErro.setVisible(erro);

    }

    private void cadastrarNewPasswordRecuperaSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarNewPasswordRecuperaSenhaMouseClicked

        this.novaSenha = new String(newPasswordRecuperaSenha.getPassword());
        this.confSenha = new String(confirmaSenhaRecuperaSenha.getPassword());

        if (loginEmptyFields1()) {
            loginEmptyFields1();
        } else {

            if (this.novaSenha.length() < 8 || confSenha.length() < 8) {
                JOptionPane.showMessageDialog(null, "Senha não pode ser menor que 8!", "ATENÇÃO!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("C:\\Users\\natha\\Music\\Library-master\\src\\Images\\icons8-atenção-35.png"));
            } else if (this.novaSenha.equals(confSenha)) {

                if (new CheckRecPassword().checkUser(cpfRecuperaSenha.getText(), dataNasciRecuperaSenha.getText())) {
                    if (new CheckRecPassword().insetNewPassword(this.novaSenha)) {

                        setNullFieldsTelaRecuperaSenha();
                        closeFieldsTelaRecuperaSenha();
                        jPanelAlertErroRecuperaSenha(false);
                        jPanelAlertSucessoRecuperaSenha(true);

                    } else {
                        JOptionPane.showMessageDialog(null, new Message().getERRO_RECOVERY_PASSWORD());
                    }
                } else {
                    closeFieldsTelaRecuperaSenha();
                    jPanelAlertSucessoRecuperaSenha(false);
                    jPanelAlertErroRecuperaSenha(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Senhas diferentes!", "ATENÇÃO!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("C:\\Users\\natha\\Music\\Library-master\\src\\Images\\icons8-atenção-35.png"));
            }
        }
    }//GEN-LAST:event_cadastrarNewPasswordRecuperaSenhaMouseClicked


    private void dataNasciRecuperaSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataNasciRecuperaSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataNasciRecuperaSenhaActionPerformed

    private void cpfRecuperaSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpfRecuperaSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpfRecuperaSenhaActionPerformed

    private void newPasswordRecuperaSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newPasswordRecuperaSenhaKeyReleased
        if (String.valueOf(newPasswordRecuperaSenha.getPassword()).trim().length() > 8) {
            newPasswordRecuperaSenha.setText(String.valueOf(newPasswordRecuperaSenha.getPassword()).trim().substring(0, 8));
        }
    }//GEN-LAST:event_newPasswordRecuperaSenhaKeyReleased

    private void cleanFieldsRecoversPasswords() {

        cpfRecuperaSenha.setText(null);
        cpfRecuperaSenha.setBorder(lineBordeOFF);
        alertCpfRecuperaSenha.setVisible(false);

        dataNasciRecuperaSenha.setText(null);
        dataNasciRecuperaSenha.setBorder(null);
        alertDataNascimentoRecuperaSenha.setVisible(false);

        newPasswordRecuperaSenha.setText(null);
        newPasswordRecuperaSenha.setBorder(lineBordeOFF);
        alertNewPasswordRecuperaSenha.setVisible(false);

        confirmaSenhaRecuperaSenha.setText(null);
        confirmaSenhaRecuperaSenha.setBorder(lineBordeOFF);
        alertConfPasswordRecuperaSenha.setVisible(false);
    }

    public void jPanelLoginScreen(boolean ativa) {

        cleanFieldsJPanelLoginScreen();

        jPanelLoginScreen.setVisible(ativa);
        jLabelBackgroundjPanelLoginScreen.setVisible(ativa);
        jLabelBackground2TelaLogin.setVisible(ativa);

        jLabelMinimizejPanelLoginScreen.setVisible(ativa);
        jLabelMinimizejPanelLoginScreen.setEnabled(ativa);

        jLabelClosejPanelLoginScreen.setVisible(ativa);
        jLabelClosejPanelLoginScreen.setEnabled(ativa);

        jLabelBackTelaDeleteLivros.setVisible(ativa);
        jLabel8.setVisible(ativa);

        jFormattedTextFieldCpfUsuarioLogin.setEnabled(ativa);
        jFormattedTextFieldCpfUsuarioLogin.setVisible(ativa);

        jFormattedTextFieldSenhaUsuarioLogin.setEnabled(ativa);
        jFormattedTextFieldSenhaUsuarioLogin.setVisible(ativa);

        jPanelProfessorEstudanteAdmin.setEnabled(ativa);
        jPanelProfessorEstudanteAdmin.setVisible(ativa);
        jPanelProfessorEstudanteAdmin.setBorder(null);

        botaoProfessorLogin.setEnabled(ativa);
        botaoProfessorLogin.setVisible(ativa);

        botaoEstudanteLogin.setEnabled(ativa);
        botaoEstudanteLogin.setVisible(ativa);

        botaoAdminLogin.setEnabled(ativa);
        botaoAdminLogin.setVisible(ativa);

    }

    private void backTelaRecuperaSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backTelaRecuperaSenhaMouseClicked

        cleansFieldsJPanelTelaLoginFuncionario();
        cleanFieldsRecoversPasswords();

        if (isEstado()) {
            jPanelLoginScreen(true);
        } else {
            jPanelTelaLoginFuncionario(true);
        }

        jPanelTelaRecuperarSenha(false);

    }//GEN-LAST:event_backTelaRecuperaSenhaMouseClicked

    private void backTelaRecuperaSenhaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backTelaRecuperaSenhaMouseEntered
        jLabelBack3.setVisible(true);
    }//GEN-LAST:event_backTelaRecuperaSenhaMouseEntered

    private void backTelaRecuperaSenhaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backTelaRecuperaSenhaMouseExited
        jLabelBack3.setVisible(false);
    }//GEN-LAST:event_backTelaRecuperaSenhaMouseExited

    private void jLabelMinimizeTelaFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaFuncionarioMouseClicked
        minimizeTelaLoginFuncionario.setVisible(false);
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabelMinimizeTelaFuncionarioMouseClicked

    private void jLabelMinimizeTelaFuncionarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaFuncionarioMouseEntered
        minimizeTelaLoginFuncionario.setVisible(true);
    }//GEN-LAST:event_jLabelMinimizeTelaFuncionarioMouseEntered

    private void jLabelMinimizeTelaFuncionarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaFuncionarioMouseExited
        minimizeTelaLoginFuncionario.setVisible(false);
    }//GEN-LAST:event_jLabelMinimizeTelaFuncionarioMouseExited

    private void jLabelCloseTelaFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaFuncionarioMouseClicked
        xamppExec.closeApacheAndMysql();
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseTelaFuncionarioMouseClicked

    private void jLabelCloseTelaFuncionarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaFuncionarioMouseEntered
        closeTelaLoginFuncionario.setVisible(true);
    }//GEN-LAST:event_jLabelCloseTelaFuncionarioMouseEntered

    private void jLabelCloseTelaFuncionarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaFuncionarioMouseExited
        closeTelaLoginFuncionario.setVisible(false);
    }//GEN-LAST:event_jLabelCloseTelaFuncionarioMouseExited

    private void cpfFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpfFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpfFuncionarioActionPerformed

    private void senhaFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senhaFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_senhaFuncionarioActionPerformed

    private void senhaFuncionarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaFuncionarioKeyReleased
        if (String.valueOf(senhaFuncionario.getPassword()).trim().length() > 8) {
            senhaFuncionario.setText(String.valueOf(senhaFuncionario.getPassword()).trim().substring(0, 8));
        }
    }//GEN-LAST:event_senhaFuncionarioKeyReleased

    private void cleansFieldsJPanelTelaLoginFuncionario() {

        cpfFuncionario.setText(null);
        cpfFuncionario.setBorder(lineBordeOFF);
        jLabelAlertCpfFuncionario.setVisible(false);

        senhaFuncionario.setText(null);
        senhaFuncionario.setBorder(lineBordeOFF);
        jLabelAlertSenhaFuncionario.setVisible(false);
    }

    private void cleanTelaRecuperaSenha() {

        jLabelImageErro.setVisible(false);
        jLabelTextoErro.setVisible(false);

        jLabelImageOK.setVisible(false);
        jLabelTextoOK.setVisible(false);
        jButtonOkRecuperarSenha.setVisible(false);

        jLabeBackgroundlRecuperarSenha.setVisible(true);
        cpfRecuperaSenha.setText(null);
        cpfRecuperaSenha.setVisible(true);
        cpfRecuperaSenha.setEnabled(true);

        dataNasciRecuperaSenha.setText(null);
        dataNasciRecuperaSenha.setVisible(true);
        dataNasciRecuperaSenha.setEnabled(true);

        newPasswordRecuperaSenha.setText(null);
        newPasswordRecuperaSenha.setVisible(true);
        newPasswordRecuperaSenha.setEnabled(true);

        confirmaSenhaRecuperaSenha.setText(null);
        confirmaSenhaRecuperaSenha.setVisible(true);
        confirmaSenhaRecuperaSenha.setEnabled(true);
    }

    private void jLabelEsqueceSenhaFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEsqueceSenhaFuncionarioMouseClicked

        cleanTelaRecuperaSenha();
        alertjPanelRecuperaSenha(false, false);
        cleansFieldsJPanelTelaLoginFuncionario();

        jPanelTelaLoginFuncionario(false);
        jPanelTelaRecuperarSenha(true);

    }//GEN-LAST:event_jLabelEsqueceSenhaFuncionarioMouseClicked

    private void jLabelEsqueceSenhaFuncionarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEsqueceSenhaFuncionarioMouseEntered
        jLabelEsqueceSenhaFuncionario.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_jLabelEsqueceSenhaFuncionarioMouseEntered

    private void jLabelEsqueceSenhaFuncionarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEsqueceSenhaFuncionarioMouseExited
        jLabelEsqueceSenhaFuncionario.setForeground(new Color(239, 239, 240));
    }//GEN-LAST:event_jLabelEsqueceSenhaFuncionarioMouseExited

    private void jButtonAutenticaFuncionarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAutenticaFuncionarioMouseEntered
        jButtonAutenticaFuncionario.setBackground(newColor);
    }//GEN-LAST:event_jButtonAutenticaFuncionarioMouseEntered

    private void jButtonAutenticaFuncionarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAutenticaFuncionarioMouseExited
        jButtonAutenticaFuncionario.setBackground(new Color(0, 52, 84));
    }//GEN-LAST:event_jButtonAutenticaFuncionarioMouseExited

    private boolean loginEmptyFieldsjPanelTelaLoginFuncionario() {

        if (cpfFuncionario.getText().trim().isEmpty()) {
            jLabelAlertCpfFuncionario.setVisible(true);
            cpfFuncionario.setBorder(lineBordeON);
            return true;
        } else {
            jLabelAlertCpfFuncionario.setVisible(false);
            cpfFuncionario.setBorder(lineBordeOFF);
        }

        if (new String(senhaFuncionario.getPassword()).trim().isEmpty()) {
            jLabelAlertSenhaFuncionario.setVisible(true);
            senhaFuncionario.setBorder(lineBordeON);
            return true;
        } else {
            jLabelAlertSenhaFuncionario.setVisible(false);
            senhaFuncionario.setBorder(lineBordeOFF);
        }

        return false;
    }

    private void FieldsONjPanel_loginScreen() {

        jFormattedTextFieldCpfUsuarioLogin.setEnabled(true);
        jFormattedTextFieldCpfUsuarioLogin.setVisible(true);
        jLabelAlertCpfUsuarioLogin.setVisible(false);

        jFormattedTextFieldSenhaUsuarioLogin.setEnabled(true);
        jFormattedTextFieldSenhaUsuarioLogin.setVisible(true);

        jLabelAlertSenhaLogin.setVisible(false);

        jPanelProfessorEstudanteAdmin.setVisible(true);

        recuperarSenhaLogin.setVisible(true);
        recuperarSenhaLogin.setEnabled(true);

        botaoProfessorLogin.setEnabled(true);
        botaoProfessorLogin.setVisible(true);

        botaoEstudanteLogin.setVisible(true);
        botaoEstudanteLogin.setEnabled(true);

        botaoAdminLogin.setEnabled(true);
        botaoAdminLogin.setVisible(true);

    }

    private void jPanelTelaLoginFuncionario(boolean ativa) {

        jPanelTelaLoginFuncionario.setVisible(ativa);

        jLabelBackgroundTelaFuncionario.setVisible(ativa);
        jLabelBackground2TelaFuncionario.setVisible(ativa);
        jLabelIconFuncionario.setVisible(ativa);
        jLabel34.setVisible(ativa);
        jLabelCpf1.setVisible(ativa);
        jLabelSenha1.setVisible(ativa);

        jLabelEsqueceSenhaFuncionario.setVisible(ativa);
        jLabelEsqueceSenhaFuncionario.setEnabled(ativa);

        jButtonAutenticaFuncionario.setEnabled(ativa);
        jButtonAutenticaFuncionario.setVisible(ativa);

        cpfFuncionario.setEnabled(ativa);
        cpfFuncionario.setVisible(ativa);

        senhaFuncionario.setEnabled(ativa);
        senhaFuncionario.setVisible(ativa);

        senhaFuncionario.setEnabled(ativa);
        senhaFuncionario.setVisible(ativa);

        jLabelCloseTelaFuncionario.setEnabled(ativa);
        jLabelCloseTelaFuncionario.setVisible(ativa);

    }

    private void jButtonAutenticaFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAutenticaFuncionarioActionPerformed

        if (loginEmptyFieldsjPanelTelaLoginFuncionario()) {
            loginEmptyFieldsjPanelTelaLoginFuncionario();
        } else {

            String passwordFieldSenha = new String(senhaFuncionario.getPassword());

            if (passwordFieldSenha.length() < 8) {
                JOptionPane.showMessageDialog(null, message.getALERT_PASSWORD());
            } else {

                final String USER_ADMIN = "Admin";

                if (verificaUsuario.verificaUsuario(cpfFuncionario.getText(), passwordFieldSenha, USER_ADMIN)) {

                    cleansFieldsJPanelTelaLoginFuncionario();
                    FieldsONjPanel_loginScreen();

                    idUserAdmin = verificaUsuario.idUser();
                    LoginScreen.estado = true;

                    jPanelTelaLoginFuncionario(false);
                    jPanelLoginScreen(true);

                } else {
                    JOptionPane.showMessageDialog(null, message.getERRO_LOGIN(), "Oops...!", JOptionPane.ERROR_MESSAGE, new ImageIcon(message.getICON_ERRO()));
                }
            }
        }
    }//GEN-LAST:event_jButtonAutenticaFuncionarioActionPerformed

    private void jButtonOkRecuperarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkRecuperarSenhaActionPerformed

        cleanFieldsRecoversPasswords();
        setNullFieldsTelaRecuperaSenha();

        jLabelConfirmarSenha.setVisible(true);
        jLabelNovaSenha.setVisible(true);
        jLabelCpfEstudante.setVisible(true);
        jLabelData.setVisible(true);

        if (isEstado()) {
            jPanelLoginScreen(true);
            jPanelTelaRecuperarSenha.setVisible(false);
        } else {
            jPanelTelaRecuperarSenha.setVisible(false);
            jPanelTelaLoginFuncionario.setVisible(true);
        }
    }//GEN-LAST:event_jButtonOkRecuperarSenhaActionPerformed

    private void jButtonRecuperarSenhaPanelErroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecuperarSenhaPanelErroActionPerformed

        jPanelAlertErroRecuperaSenha.setVisible(false);
        jLabelImageErro.setVisible(false);
        jLabelTextoErro.setVisible(false);
        jButtonRecuperarSenhaPanelErro.setVisible(false);

        jLabeBackgroundlRecuperarSenha.setVisible(true);
        jLabelConfirmarSenha.setVisible(true);
        jLabelNovaSenha.setVisible(true);
        jLabelCpfEstudante.setVisible(true);
        jLabelData.setVisible(true);

        cpfRecuperaSenha.setVisible(true);
        cpfRecuperaSenha.setEnabled(true);

        dataNasciRecuperaSenha.setBorder(null);
        dataNasciRecuperaSenha.setVisible(true);
        dataNasciRecuperaSenha.setEnabled(true);

        newPasswordRecuperaSenha.setVisible(true);
        newPasswordRecuperaSenha.setEnabled(true);

        confirmaSenhaRecuperaSenha.setVisible(true);
        confirmaSenhaRecuperaSenha.setEnabled(true);
        confirmaSenhaRecuperaSenha.setBorder(lineBordeOFF);

    }//GEN-LAST:event_jButtonRecuperarSenhaPanelErroActionPerformed

    private void newPasswordRecuperaSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPasswordRecuperaSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newPasswordRecuperaSenhaActionPerformed

    private void nameStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameStudentActionPerformed

    }//GEN-LAST:event_nameStudentActionPerformed

    private void nameStudentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameStudentKeyReleased

        if (String.valueOf(nameStudent.getText()).trim().length() > 50) {
            nameStudent.setText(String.valueOf(nameStudent.getText()).trim().substring(0, 50));
        }
    }//GEN-LAST:event_nameStudentKeyReleased

    private void backTelaCadastraEstudanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backTelaCadastraEstudanteMouseClicked

        desabledjPanelCadastraEstudante(false);
        jPanelTelaTecAdmin(true);

    }//GEN-LAST:event_backTelaCadastraEstudanteMouseClicked

    private void backTelaCadastraEstudanteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backTelaCadastraEstudanteMouseEntered
        jLabelBackTelaEstudante.setVisible(true);
    }//GEN-LAST:event_backTelaCadastraEstudanteMouseEntered

    private void backTelaCadastraEstudanteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backTelaCadastraEstudanteMouseExited
        jLabelBackTelaEstudante.setVisible(false);
    }//GEN-LAST:event_backTelaCadastraEstudanteMouseExited

    private void cpfUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpfUserActionPerformed

    }//GEN-LAST:event_cpfUserActionPerformed

    private void phoneUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneUserActionPerformed

    }//GEN-LAST:event_phoneUserActionPerformed

    private void birthDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_birthDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_birthDateActionPerformed

    private void desabledjPanelCadastraEstudante(boolean ativa) {

        jPanelCadastraEstudante.setVisible(ativa);

        jLabelCpfEstudante1.setVisible(ativa);
        jLabelTelefoneEstudante.setVisible(ativa);
        jLabelSenha2.setVisible(ativa);
        jLabelData1.setVisible(ativa);
        jLabelConfirmSenha.setVisible(ativa);
        jLabelIdEstudante.setVisible(ativa);
        jLabelBanner.setVisible(ativa);
        botaoCadastrarEstudante.setVisible(ativa);

        jLabelMinimizeTelaCadastroEstudante.setVisible(ativa);
        jLabelMinimizeTelaCadastroEstudante.setEnabled(ativa);

        jLabelCloseTelaCadastroEstudante.setVisible(ativa);
        jLabelCloseTelaCadastroEstudante.setEnabled(ativa);

        nameStudent.setVisible(ativa);
        nameStudent.setEnabled(ativa);

        cpfUser.setVisible(ativa);
        cpfUser.setEnabled(ativa);

        phoneUser.setVisible(ativa);
        phoneUser.setEnabled(ativa);

        passwordUser.setVisible(ativa);
        passwordUser.setEnabled(ativa);

        birthDate.setVisible(ativa);
        birthDate.setEnabled(ativa);

        cofimacaoSenha.setVisible(ativa);
        cofimacaoSenha.setEnabled(ativa);

        codUsuario.setVisible(ativa);
        codUsuario.setEnabled(ativa);

        backTelaCadastraEstudante.setVisible(ativa);
        backTelaCadastraEstudante.setEnabled(ativa);

    }

    public boolean verificaCampo() {

        return (nameStudent.getText().trim().isEmpty()
                || cpfUser.getText().trim().isEmpty()
                || passwordUser.getPassword() == null
                || cofimacaoSenha.getPassword() == null
                || codUsuario.getText().trim().isEmpty()
                || phoneUser.getText().trim().isEmpty()
                || birthDate.getText().trim().isEmpty());
    }

    private void botaoCadastrarEstudanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoCadastrarEstudanteMouseClicked

        Message message = new Message();

        if (verificaCampo()) {
            JOptionPane.showMessageDialog(null, message.getEMPTY_FIELD());
        } else {

            if (new ValidateCPF().validate(cpfUser.getText())) {

                if (new CheckCpf().verificaCpfUsuario(cpfUser.getText())) {
                    JOptionPane.showMessageDialog(null, message.getCPF_CAD());
                } else {

                    String password = new String(passwordUser.getPassword());

                    if (password.length() < 8) {
                        JOptionPane.showMessageDialog(null, message.getALERT_PASSWORD());
                    } else {

                        if (password.equals(new String(cofimacaoSenha.getPassword()))) {

                            Estudante student = new Estudante();

                            student.setNome(nameStudent.getText());
                            student.setCpf(cpfUser.getText());
                            student.setPassword(password);
                            student.setDataNacimento(birthDate.getText());
                            student.setTelefone(phoneUser.getText());
                            student.setMatriculaEstudante(codUsuario.getText());

                            InsertUser insertUser = new InsertUser();
                            insertUser.insertStudentBD(student);

                            desabledjPanelCadastraEstudante(false);
                            jPanelTelaTecAdmin(true);

                        } else {
                            JOptionPane.showMessageDialog(null, "Senhas diferentes!");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, message.getMESSAGE_CPF_INVALID());
            }
        }
    }//GEN-LAST:event_botaoCadastrarEstudanteMouseClicked

    private void codUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codUsuarioActionPerformed

    }//GEN-LAST:event_codUsuarioActionPerformed

    private void passwordUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordUserActionPerformed

    }//GEN-LAST:event_passwordUserActionPerformed

    private void passwordUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordUserKeyReleased

        if (String.valueOf(passwordUser.getPassword()).trim().length() > 8)
            passwordUser.setText(String.valueOf(passwordUser.getPassword()).trim().substring(0, 8));
    }//GEN-LAST:event_passwordUserKeyReleased

    private void cofimacaoSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cofimacaoSenhaActionPerformed

    }//GEN-LAST:event_cofimacaoSenhaActionPerformed

    private void cofimacaoSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cofimacaoSenhaKeyReleased
        if (String.valueOf(cofimacaoSenha.getPassword()).trim().length() > 8)
            cofimacaoSenha.setText(String.valueOf(cofimacaoSenha.getPassword()).trim().substring(0, 8));
    }//GEN-LAST:event_cofimacaoSenhaKeyReleased

    private void jLabelMinimizeTelaCadastroEstudanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaCadastroEstudanteMouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabelMinimizeTelaCadastroEstudanteMouseClicked

    private void jLabelMinimizeTelaCadastroEstudanteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaCadastroEstudanteMouseEntered
        minimizeTelaCadEstudante.setVisible(true);
    }//GEN-LAST:event_jLabelMinimizeTelaCadastroEstudanteMouseEntered

    private void jLabelMinimizeTelaCadastroEstudanteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaCadastroEstudanteMouseExited
        minimizeTelaCadEstudante.setVisible(false);
    }//GEN-LAST:event_jLabelMinimizeTelaCadastroEstudanteMouseExited

    private void jLabelCloseTelaCadastroEstudanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaCadastroEstudanteMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseTelaCadastroEstudanteMouseClicked

    private void jLabelCloseTelaCadastroEstudanteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaCadastroEstudanteMouseEntered
        closeTelaCadEstudante.setVisible(true);
    }//GEN-LAST:event_jLabelCloseTelaCadastroEstudanteMouseEntered

    private void jLabelCloseTelaCadastroEstudanteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaCadastroEstudanteMouseExited
        closeTelaCadEstudante.setVisible(false);
    }//GEN-LAST:event_jLabelCloseTelaCadastroEstudanteMouseExited

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked

        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabel30MouseClicked

    private void jLabel30MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseEntered

        minimizeTelaAdmin.setVisible(true);
    }//GEN-LAST:event_jLabel30MouseEntered

    private void jLabel30MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseExited
        minimizeTelaAdmin.setVisible(false);
    }//GEN-LAST:event_jLabel30MouseExited

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel39MouseClicked

    private void jLabel39MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseEntered
        closeTelaAdmin.setVisible(true);
    }//GEN-LAST:event_jLabel39MouseEntered

    private void jLabel39MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseExited
        closeTelaAdmin.setVisible(false);
    }//GEN-LAST:event_jLabel39MouseExited

    public void jPanelTelaCadastraLivros(boolean ativa) {

        jPanelTelaCadastraLivros.setVisible(ativa);

        jLabel4.setVisible(ativa);
        jLabelBookYear.setVisible(ativa);
        jLabelBookWeight.setVisible(ativa);
        jLabel5.setVisible(ativa);
        jLabel27.setVisible(ativa);
        jLabel14.setVisible(ativa);
        jLabel31.setVisible(ativa);
        jLabel26.setVisible(ativa);
        jLabelAutorName.setVisible(ativa);
        jLabelBookTitle.setVisible(ativa);
        jLabelPublisherName.setVisible(ativa);
        jL_CadLivro.setVisible(ativa);

        jReturn.setVisible(ativa);
        jReturn.setEnabled(ativa);

        anoLivro.setVisible(ativa);
        anoLivro.setEnabled(ativa);

        idioma.setVisible(ativa);
        idioma.setEnabled(ativa);

        pais.setVisible(ativa);
        pais.setEnabled(ativa);

        pesoLivro.setVisible(ativa);
        pesoLivro.setEnabled(ativa);

        numPaginas.setVisible(ativa);
        numPaginas.setEnabled(ativa);

        quantidadeBook.setVisible(ativa);
        quantidadeBook.setEnabled(ativa);

        isbn.setVisible(ativa);
        isbn.setEnabled(ativa);

        nomeAutor.setVisible(ativa);
        nomeAutor.setEnabled(ativa);

        tituloLivro.setVisible(ativa);
        tituloLivro.setEnabled(ativa);

        nomeEditora.setVisible(ativa);
        nomeEditora.setEnabled(ativa);

    }

    private void jButtonCadastraLivroTelaAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastraLivroTelaAdminActionPerformed

        jPanelTelaTecAdmin(false);
        jPanelTelaCadastraLivros(true);

    }//GEN-LAST:event_jButtonCadastraLivroTelaAdminActionPerformed

    private void backTelaAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backTelaAdminMouseClicked

        jPanelTelaTecAdmin(false);
        jPanelLoginScreen(true);
    }//GEN-LAST:event_backTelaAdminMouseClicked

    private void backTelaAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backTelaAdminMouseEntered
        jLabelBackTelaAdmin.setVisible(true);

    }//GEN-LAST:event_backTelaAdminMouseEntered

    private void backTelaAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backTelaAdminMouseExited
        jLabelBackTelaAdmin.setVisible(false);
    }//GEN-LAST:event_backTelaAdminMouseExited

    private void jPanelTelaDeletarBooks(boolean activeSwing) {

        jPanelTelaDeletarBooks.setVisible(activeSwing);
        deletaBook.setVisible(activeSwing);
        jLabelText.setVisible(activeSwing);

        jTableDeleteBooks.setEnabled(activeSwing);
        jTableDeleteBooks.setVisible(activeSwing);

        jLabelMinimizeTelaDeleteLivro.setEnabled(activeSwing);
        jLabelMinimizeTelaDeleteLivro.setVisible(activeSwing);

        jLabelCloseTelaDeletaLivros.setEnabled(activeSwing);
        jLabelCloseTelaDeletaLivros.setVisible(activeSwing);

        pesquisaLivro.setEnabled(activeSwing);
        pesquisaLivro.setVisible(activeSwing);

    }

    private void jButtonDeletarLivroTelaAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletarLivroTelaAdminActionPerformed

        jPanelTelaTecAdmin(false);
        jPanelTelaDeletarBooks(true);
        searchInput(pesquisaLivro.getText(), jTableDeleteBooks);

    }//GEN-LAST:event_jButtonDeletarLivroTelaAdminActionPerformed

    public void jPanelTelaDeletaUsuario(boolean ativa) {

        jPanelTelaDeletaUsuario.setVisible(ativa);
        jLabelBackgroundTelaDeletaUsuario.setVisible(ativa);
        jLabelBackgroundTelaDeletaUsuario.setEnabled(ativa);

        jLabel43.setVisible(ativa);
        jLabel44.setVisible(ativa);
        jLabel38.setVisible(ativa);
        deletaUser.setVisible(ativa);
        jLabel42.setVisible(ativa);

        jLabel35.setVisible(ativa);
        jLabel35.setEnabled(ativa);

        jLabel36.setVisible(ativa);
        jLabel36.setEnabled(ativa);

        estudante.setVisible(ativa);
        estudante.setEnabled(ativa);
        professor.setVisible(ativa);
        professor.setEnabled(ativa);

        cpfDeletaUsuario.setVisible(ativa);
        cpfDeletaUsuario.setEnabled(ativa);

        senhaDeletaUsuario.setVisible(ativa);
        senhaDeletaUsuario.setEnabled(ativa);

        jLabelBackTelaDeletaUser.setVisible(ativa);
        jLabelBackTelaDeletaUser.setEnabled(ativa);
    }

    private void jButtonDeletarUsuarioTelaAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletarUsuarioTelaAdminActionPerformed

        cleanjPanelTelaDeletaUsuario();
        jPanelTelaTecAdmin(false);
        jPanelTelaDeletaUsuario(true);

    }//GEN-LAST:event_jButtonDeletarUsuarioTelaAdminActionPerformed


    private void jButtonCadastraEstudanteTelaAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastraEstudanteTelaAdminActionPerformed

        jPanelTelaTecAdmin(false);
        cleanFieldsJPanelCadastraEstudante();
        desabledjPanelCadastraEstudante(true);

    }//GEN-LAST:event_jButtonCadastraEstudanteTelaAdminActionPerformed

    private void jButtonCadastraProfessorTelaAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastraProfessorTelaAdminActionPerformed

        jPanelTelaDeletaUsuario(false);
        jPanelTelaTecAdmin(false);
        cleanFieldsJPanelCadastraProfessor();
        jPanelTelaCadastraProfessor(true);

    }//GEN-LAST:event_jButtonCadastraProfessorTelaAdminActionPerformed

    private void nomeAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeAutorActionPerformed

    }//GEN-LAST:event_nomeAutorActionPerformed

    private void nomeAutorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeAutorKeyReleased
        if (String.valueOf(nomeAutor.getText()).trim().length() > 80)
            nomeAutor.setText(String.valueOf(nomeAutor.getText()).trim().substring(0, 80));
    }//GEN-LAST:event_nomeAutorKeyReleased

    private void tituloLivroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tituloLivroKeyReleased
        if (String.valueOf(tituloLivro.getText()).trim().length() > 80)
            tituloLivro.setText(String.valueOf(tituloLivro.getText()).trim().substring(0, 80));
    }//GEN-LAST:event_tituloLivroKeyReleased

    private void nomeEditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeEditoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeEditoraActionPerformed

    private void nomeEditoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeEditoraKeyReleased
        if (String.valueOf(nomeEditora.getText()).trim().length() > 80)
            nomeEditora.setText(String.valueOf(nomeEditora.getText()).trim().substring(0, 80));
    }//GEN-LAST:event_nomeEditoraKeyReleased

    private void clearFieldsTelaCadastroLivros() {

        anoLivro.setText(null);
        idioma.setText(null);
        pais.setText(null);
        pesoLivro.setText(null);
        numPaginas.setText(null);
        quantidadeBook.setText(null);
        isbn.setText(null);
        nomeAutor.setText(null);
        tituloLivro.setText(null);
        nomeEditora.setText(null);

    }

    private void jReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jReturnMouseClicked

        clearFieldsTelaCadastroLivros();
        jPanelTelaCadastraLivros(false);
        jPanelTelaTecAdmin(true);

    }//GEN-LAST:event_jReturnMouseClicked

    private void jReturnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jReturnMouseEntered
        jLabelBackTelaCadastroLivros.setVisible(true);
    }//GEN-LAST:event_jReturnMouseEntered

    private void jReturnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jReturnMouseExited
        jLabelBackTelaCadastroLivros.setVisible(false);
    }//GEN-LAST:event_jReturnMouseExited

    private void anoLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anoLivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anoLivroActionPerformed

    private boolean emptyFields() {

        boolean check = false;

        if (anoLivro.getText().trim().isEmpty()) {
            anoLivro.setBorder(lineBordeON);
            check = true;
        } else {
            anoLivro.setBorder(lineBordeOFF);
        }

        if (quantidadeBook.getText().trim().isEmpty()) {
            quantidadeBook.setBorder(lineBordeON);
            check = true;
        } else {
            quantidadeBook.setBorder(lineBordeOFF);
        }

        if (idioma.getText().trim().isEmpty()) {
            idioma.setBorder(lineBordeON);
            check = true;
        } else {
            idioma.setBorder(lineBordeOFF);
        }

        if (pais.getText().trim().isEmpty()) {
            pais.setBorder(lineBordeON);
            check = true;
        } else {
            pais.setBorder(lineBordeOFF);
        }

        if (pesoLivro.getText().trim().isEmpty()) {
            pesoLivro.setBorder(lineBordeON);
            check = true;
        } else {
            pesoLivro.setBorder(lineBordeOFF);
        }

        if (numPaginas.getText().trim().isEmpty()) {
            numPaginas.setBorder(lineBordeON);
            check = true;
        } else {
            numPaginas.setBorder(lineBordeOFF);
        }

        if (isbn.getText().trim().isEmpty()) {
            isbn.setBorder(lineBordeON);
            check = true;
        } else {
            isbn.setBorder(lineBordeOFF);
        }

        if (nomeAutor.getText().trim().isEmpty()) {
            nomeAutor.setBorder(lineBordeON);
            check = true;
        } else {
            nomeAutor.setBorder(lineBordeOFF);
        }

        if (tituloLivro.getText().trim().isEmpty()) {
            tituloLivro.setBorder(lineBordeON);
            check = true;
        } else {
            tituloLivro.setBorder(lineBordeOFF);
        }

        if (nomeEditora.getText().trim().isEmpty()) {
            nomeEditora.setBorder(lineBordeON);
            check = true;
        } else {
            nomeEditora.setBorder(lineBordeOFF);
        }

        return check;
    }

    private void jL_CadLivroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_CadLivroMouseClicked

        if (emptyFields()) {
            JOptionPane.showMessageDialog(null, "Campo vazio!");
        } else {
            Book livro = new Book();

            livro.setNumeroPaginas(Short.parseShort(numPaginas.getText()));
            livro.setQuantidade(Short.parseShort(quantidadeBook.getText()));
            livro.setPesoLivro(Float.parseFloat(pesoLivro.getText()));
            livro.setAno(Short.parseShort(anoLivro.getText()));
            livro.setNomeEditora(nomeEditora.getText());
            livro.setNomeAutor(nomeAutor.getText());
            livro.setTitulo(tituloLivro.getText());
            livro.setIdioma(idioma.getText());
            livro.setISBN(isbn.getText());
            livro.setPais(pais.getText());

            if (new CadastraLivroBD().cadastrarLivroBD(livro)) {

                JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
                clearFieldsTelaCadastroLivros();

//                java.awt.EventQueue.invokeLater(() -> {
//                    new TelaTecAdmin().setVisible(true);
//                });
//                dispose();;
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o livro!");
            }
        }
    }//GEN-LAST:event_jL_CadLivroMouseClicked

    private void pesoLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesoLivroActionPerformed

    }//GEN-LAST:event_pesoLivroActionPerformed

    private void pesoLivroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pesoLivroKeyReleased
        if (String.valueOf(pesoLivro.getText()).trim().length() > 5)
            pesoLivro.setText(String.valueOf(pesoLivro.getText()).trim().substring(0, 5));
    }//GEN-LAST:event_pesoLivroKeyReleased

    private void idiomaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idiomaKeyReleased
        if (String.valueOf(idioma.getText()).trim().length() > 50)
            idioma.setText(String.valueOf(idioma.getText()).trim().substring(0, 50));
    }//GEN-LAST:event_idiomaKeyReleased

    private void paisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paisActionPerformed

    private void paisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paisKeyReleased
        if (String.valueOf(pais.getText()).trim().length() > 50)
            pais.setText(String.valueOf(pais.getText()).trim().substring(0, 50));
    }//GEN-LAST:event_paisKeyReleased

    private void numPaginasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numPaginasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numPaginasActionPerformed

    private void numPaginasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numPaginasKeyReleased
        if (String.valueOf(numPaginas.getText()).trim().length() > 4)
            numPaginas.setText(String.valueOf(numPaginas.getText()).trim().substring(0, 4));
    }//GEN-LAST:event_numPaginasKeyReleased

    private void quantidadeBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantidadeBookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantidadeBookActionPerformed

    private void quantidadeBookKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantidadeBookKeyReleased
        if (String.valueOf(quantidadeBook.getText()).trim().length() > 2)
            quantidadeBook.setText(String.valueOf(quantidadeBook.getText()).trim().substring(0, 2));
    }//GEN-LAST:event_quantidadeBookKeyReleased

    private void jLabelMinimizeTelaCadLivrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaCadLivrosMouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabelMinimizeTelaCadLivrosMouseClicked

    private void jLabelMinimizeTelaCadLivrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaCadLivrosMouseEntered
        minimizeTelaCadLivros.setVisible(true);
    }//GEN-LAST:event_jLabelMinimizeTelaCadLivrosMouseEntered

    private void jLabelMinimizeTelaCadLivrosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaCadLivrosMouseExited
        minimizeTelaCadLivros.setVisible(false);
    }//GEN-LAST:event_jLabelMinimizeTelaCadLivrosMouseExited

    private void jLabelCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseMouseClicked

    private void jLabelCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseEntered
        closeTelaCadAdmin.setVisible(true);
    }//GEN-LAST:event_jLabelCloseMouseEntered

    private void jLabelCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseExited
        closeTelaCadAdmin.setVisible(false);
    }//GEN-LAST:event_jLabelCloseMouseExited

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel35MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseEntered
        minimizeTelaUsuario.setVisible(true);
    }//GEN-LAST:event_jLabel35MouseEntered

    private void jLabel35MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseExited
        minimizeTelaUsuario.setVisible(false);
    }//GEN-LAST:event_jLabel35MouseExited

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel36MouseClicked

    private void jLabel36MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseEntered
        closeDeletarUsuario.setVisible(true);
    }//GEN-LAST:event_jLabel36MouseEntered

    private void jLabel36MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseExited
        closeDeletarUsuario.setVisible(false);
    }//GEN-LAST:event_jLabel36MouseExited

    private void senhaDeletaUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaDeletaUsuarioKeyReleased
        if (String.valueOf(senhaDeletaUsuario.getPassword()).trim().length() > 8)
            senhaDeletaUsuario.setText(String.valueOf(senhaDeletaUsuario.getPassword()).trim().substring(0, 8));
    }//GEN-LAST:event_senhaDeletaUsuarioKeyReleased

    private void deletaUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletaUserMouseClicked

        CheckUser verifica = new CheckUser();
        String pSenha = new String(senhaDeletaUsuario.getPassword());

        if (cpfDeletaUsuario.getText().trim().isEmpty() || pSenha.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo vazio!");
        } else {
            if (professor.isSelected()) {
                if (verifica.verificaUsuario(cpfDeletaUsuario.getText(), pSenha, "Professor")) {
                    if (verifica.valorMulta() == 0) {
                        if (JOptionPane.showConfirmDialog(rootPane, "Deletar Professor?") == 0) {

                            if (new DeleteUser().deleteUser(verifica.idUser())) {

                                UIManager.getDefaults().put("OptionPane.background", new Color(102, 255, 102));
                                UIManager.put("Panel.background", new Color(102, 255, 102));
                                UIManager.put("OptionPane.messageForeground", Color.black);

                                JOptionPane.showMessageDialog(null, new Message().getDELETE_USER());

                                UIManager.getDefaults().put("OptionPane.background", new Color(255, 85, 114));
                                UIManager.put("Panel.background", new Color(255, 85, 114));
                                UIManager.put("OptionPane.messageForeground", Color.white);

                                jPanelTelaDeletaUsuario(false);
                                jPanelTelaTecAdmin(true);

                            } else {
                                JOptionPane.showMessageDialog(null, new Message().getERRO_DELETE_USER());
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Professor está pendente!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Professor nao encontrado");
                }
            } else if (estudante.isSelected()) {
                if (verifica.verificaUsuario(cpfDeletaUsuario.getText(), pSenha, "Estudante")) {
                    if (verifica.valorMulta() == 0) {
                        if (JOptionPane.showConfirmDialog(rootPane, "Deletar estudante?") == 0) {

                            if (new DeleteUser().deleteUser(verifica.idUser())) {

                                UIManager.getDefaults().put("OptionPane.background", new Color(102, 255, 102));
                                UIManager.put("Panel.background", new Color(102, 255, 102));
                                UIManager.put("OptionPane.messageForeground", Color.black);

                                JOptionPane.showMessageDialog(null, new Message().getDELETE_USER(), "Delete", JOptionPane.ERROR_MESSAGE, new ImageIcon("C:\\Users\\natha\\Documents\\Github\\Library\\src\\Images\\verificar-simbolo (1).png"));

                                UIManager.getDefaults().put("OptionPane.background", new Color(255, 85, 114));
                                UIManager.put("Panel.background", new Color(255, 85, 114));
                                UIManager.put("OptionPane.messageForeground", Color.white);

//                                java.awt.EventQueue.invokeLater(() -> {
//                                    new TelaTecAdmin().setVisible(true);
//                                });
//                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, new Message().getERRO_DELETE_USER());
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Estudante está pendente!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Estudante nao encontrado");
                }
            }
        }
    }//GEN-LAST:event_deletaUserMouseClicked

    private void cleanjPanelTelaDeletaUsuario() {

        cpfDeletaUsuario.setText(null);
        senhaDeletaUsuario.setText(null);

        buttonGroupDeletaUser.clearSelection();

    }

    private void jLabelBackTelaDeletaUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackTelaDeletaUserMouseClicked

        cleanjPanelTelaDeletaUsuario();
        jPanelTelaDeletaUsuario(false);
        jPanelTelaTecAdmin(true);

        minimizeTelaUsuario.setVisible(false);
        closeDeletarUsuario.setVisible(false);

    }//GEN-LAST:event_jLabelBackTelaDeletaUserMouseClicked

    private void jLabelBackTelaDeletaUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackTelaDeletaUserMouseEntered
        jLabelBackTelaDeletaUsuario.setVisible(true);
    }//GEN-LAST:event_jLabelBackTelaDeletaUserMouseEntered

    private void jLabelBackTelaDeletaUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackTelaDeletaUserMouseExited
        jLabelBackTelaDeletaUsuario.setVisible(false);
    }//GEN-LAST:event_jLabelBackTelaDeletaUserMouseExited

    private void nomeProfessorrMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomeProfessorrMouseReleased
        if (String.valueOf(nomeProfessorr.getText()).trim().length() > 50)
            nomeProfessorr.setText(String.valueOf(nomeProfessorr.getText()).trim().substring(0, 50));
    }//GEN-LAST:event_nomeProfessorrMouseReleased

    private void nomeProfessorrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeProfessorrActionPerformed

    }//GEN-LAST:event_nomeProfessorrActionPerformed

    private void jLabelReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelReturnMouseClicked

        jPanelTelaCadastraProfessor(false);
        jPanelTelaTecAdmin(true);

    }//GEN-LAST:event_jLabelReturnMouseClicked

    private void jLabelReturnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelReturnMouseEntered
        jLabelBackTelaCadProfessor.setVisible(true);
    }//GEN-LAST:event_jLabelReturnMouseEntered

    private void jLabelReturnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelReturnMouseExited
        jLabelBackTelaCadProfessor.setVisible(false);
    }//GEN-LAST:event_jLabelReturnMouseExited

    private void cpfProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpfProfessorActionPerformed

    }//GEN-LAST:event_cpfProfessorActionPerformed

    private void dataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataNascimentoActionPerformed

    }//GEN-LAST:event_dataNascimentoActionPerformed

    private void senhaProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senhaProfessorActionPerformed

    }//GEN-LAST:event_senhaProfessorActionPerformed

    private void senhaProfessorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaProfessorKeyReleased
        if (String.valueOf(senhaProfessor.getPassword()).trim().length() > 8)
            senhaProfessor.setText(String.valueOf(senhaProfessor.getPassword()).trim().substring(0, 8));
    }//GEN-LAST:event_senhaProfessorKeyReleased

    private void confimSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confimSenhaActionPerformed

    }//GEN-LAST:event_confimSenhaActionPerformed

    private void confimSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confimSenhaKeyReleased
        if (String.valueOf(confimSenha.getPassword()).trim().length() > 8)
            confimSenha.setText(String.valueOf(confimSenha.getPassword()).trim().substring(0, 8));
    }//GEN-LAST:event_confimSenhaKeyReleased

    private void jLabelMinimizaTelaCadastraProfessorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizaTelaCadastraProfessorMouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabelMinimizaTelaCadastraProfessorMouseClicked

    private void jLabelMinimizaTelaCadastraProfessorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizaTelaCadastraProfessorMouseEntered
        minimizeTelaCadProfessor.setVisible(true);
    }//GEN-LAST:event_jLabelMinimizaTelaCadastraProfessorMouseEntered

    private void jLabelMinimizaTelaCadastraProfessorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizaTelaCadastraProfessorMouseExited
        minimizeTelaCadProfessor.setVisible(false);
    }//GEN-LAST:event_jLabelMinimizaTelaCadastraProfessorMouseExited

    private void jLabelCloseTelaCadastraProfessorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaCadastraProfessorMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseTelaCadastraProfessorMouseClicked

    private void jLabelCloseTelaCadastraProfessorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaCadastraProfessorMouseEntered
        closeTelaCadProfessor.setVisible(true);
    }//GEN-LAST:event_jLabelCloseTelaCadastraProfessorMouseEntered

    private void jLabelCloseTelaCadastraProfessorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaCadastraProfessorMouseExited
        closeTelaCadProfessor.setVisible(false);
    }//GEN-LAST:event_jLabelCloseTelaCadastraProfessorMouseExited

    private void jPanelTelaCadastraProfessor(boolean ativa) {

        jPanelTelaCadastraProfessor.setVisible(ativa);
        jLabelMinimizaTelaCadastraProfessor.setVisible(ativa);
        jLabelCloseTelaCadastraProfessor.setVisible(ativa);
        jLabel6.setVisible(ativa);
        jLabel9.setVisible(ativa);
        jLabel32.setVisible(ativa);
        jLabel11.setVisible(ativa);
        jLabel25.setVisible(ativa);
        jLabel24.setVisible(ativa);
        jLabelTextCadProfessor.setVisible(ativa);
        jLabel33.setVisible(ativa);
        jButtonCadastraProfessor.setVisible(ativa);

        nomeProfessorr.setVisible(ativa);
        nomeProfessorr.setEnabled(ativa);

        cpfProfessor.setVisible(ativa);
        cpfProfessor.setEnabled(ativa);

        telefoneProfessorr.setVisible(ativa);
        telefoneProfessorr.setEnabled(ativa);

        senhaProfessor.setVisible(ativa);
        senhaProfessor.setEnabled(ativa);

        dataNascimento.setVisible(ativa);
        dataNascimento.setEnabled(ativa);

        confimSenha.setVisible(ativa);
        confimSenha.setEnabled(ativa);

        matriculaProfessor.setVisible(ativa);
        matriculaProfessor.setEnabled(ativa);

        jLabelReturn.setVisible(ativa);
        jLabelReturn.setEnabled(ativa);

    }

    public boolean verificaCampoProfessor() {

        return (nomeProfessorr.getText().trim().isEmpty()
                || cpfProfessor.getText().trim().isEmpty()
                || senhaProfessor.getPassword() == null
                || confimSenha.getPassword() == null
                || matriculaProfessor.getText().trim().isEmpty()
                || telefoneProfessorr.getText().trim().isEmpty()
                || dataNascimento.getText().trim().isEmpty());
    }

    private void jButtonCadastraProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastraProfessorActionPerformed

        Message message = new Message();

        if (verificaCampoProfessor()) {
            JOptionPane.showMessageDialog(null, message.getEMPTY_FIELD());
        } else {
            if (new ValidateCPF().validate(cpfProfessor.getText())) {

                if (new CheckCpf().verificaCpfUsuario(cpfProfessor.getText())) {
                    JOptionPane.showMessageDialog(rootPane, message.getCPF_CAD());
                } else {
                    String varSenha = new String(senhaProfessor.getPassword());

                    if (senhaProfessor.getPassword() == null) {
                        JOptionPane.showMessageDialog(null, message.getEMPTY_FIELD());

                    } else if (varSenha.length() < 6) {
                        JOptionPane.showMessageDialog(null, message.getALERT_PASSWORD());

                    } else if (varSenha.equals(new String(confimSenha.getPassword()))) {

                        Professor professor = new Professor();

                        professor.setNome(nomeProfessorr.getText());
                        professor.setCpf(cpfProfessor.getText());
                        professor.setPassword(varSenha);
                        professor.setDataNacimento(dataNascimento.getText());
                        professor.setTelefone(telefoneProfessorr.getText());
                        professor.setMatriculaProfessor(matriculaProfessor.getText());

                        new InsertUser().insertProfessorBD(professor);

                        jPanelTelaCadastraProfessor(false);
                        jPanelTelaTecAdmin(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Senha diferentes");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, message.getMESSAGE_CPF_INVALID());
            }
        }
    }//GEN-LAST:event_jButtonCadastraProfessorActionPerformed

    private void jLabelCloseTelaDeletaLivrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaDeletaLivrosMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseTelaDeletaLivrosMouseClicked

    private void jLabelCloseTelaDeletaLivrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaDeletaLivrosMouseEntered
        jLabelCloseTelaDeleteLivros.setVisible(true);
    }//GEN-LAST:event_jLabelCloseTelaDeletaLivrosMouseEntered

    private void jLabelCloseTelaDeletaLivrosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaDeletaLivrosMouseExited
        jLabelCloseTelaDeleteLivros.setVisible(false);
    }//GEN-LAST:event_jLabelCloseTelaDeletaLivrosMouseExited

    private void jLabelMinimizeTelaDeleteLivroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaDeleteLivroMouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabelMinimizeTelaDeleteLivroMouseClicked

    private void jLabelMinimizeTelaDeleteLivroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaDeleteLivroMouseEntered
        jLabelMinimizeTelaDeleteLivros.setVisible(true);
    }//GEN-LAST:event_jLabelMinimizeTelaDeleteLivroMouseEntered

    private void jLabelMinimizeTelaDeleteLivroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeTelaDeleteLivroMouseExited
        jLabelMinimizeTelaDeleteLivros.setVisible(false);
    }//GEN-LAST:event_jLabelMinimizeTelaDeleteLivroMouseExited

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked

        jPanelTelaDeletarBooks(false);
        jPanelTelaTecAdmin(true);

    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jLabelBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseEntered
        jLabel1.setVisible(true);
    }//GEN-LAST:event_jLabelBackMouseEntered

    private void jLabelBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseExited
        jLabel1.setVisible(false);
    }//GEN-LAST:event_jLabelBackMouseExited

    private void pesquisaLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisaLivroActionPerformed
        searchInput(pesquisaLivro.getText(), jTableDeleteBooks);
    }//GEN-LAST:event_pesquisaLivroActionPerformed

    private void pesquisaLivroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pesquisaLivroKeyReleased
        searchInput(pesquisaLivro.getText(),jTableDeleteBooks);
    }//GEN-LAST:event_pesquisaLivroKeyReleased

    private void jTableDeleteBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDeleteBooksMouseClicked

    }//GEN-LAST:event_jTableDeleteBooksMouseClicked

    private void deletaBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletaBookActionPerformed

        if (jTableDeleteBooks.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Você não escolheu nenhum livro!");
        } else {

            if (JOptionPane.showConfirmDialog(null, "Deseja deletar o livro?") == 0) { // Return == 0, delete.

                UIManager.getDefaults().put("OptionPane.background", new Color(102, 255, 102));
                UIManager.put("Panel.background", new Color(102, 255, 102));

                if (new DeleteBook().deletalivros(jTableDeleteBooks.getValueAt(jTableDeleteBooks.getSelectedRow(), 4))) {
                    searchInput(pesquisaLivro.getText(),jTableDeleteBooks);
                    JOptionPane.showMessageDialog(null, new Message().getDELETE_BOOK());
                } else {
                    JOptionPane.showMessageDialog(null, new Message().getERRO_DELETE_BOOK());
                }

                UIManager.getDefaults().put("OptionPane.background", new Color(255, 85, 114));
                UIManager.put("Panel.background", new Color(255, 85, 114));
            }
        }
    }//GEN-LAST:event_deletaBookActionPerformed

    private void jLabelBack1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBack1MouseClicked

        jPanelTelaDevolucao(false);
        jPanelEstudante(true);
 
    }//GEN-LAST:event_jLabelBack1MouseClicked

    private void jTableBookDevolucaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBookDevolucaoMouseClicked

    }//GEN-LAST:event_jTableBookDevolucaoMouseClicked

    private void jLabelBack1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBack1MouseEntered
        jLabelBackTelaDevolucao.setVisible(true);
    }//GEN-LAST:event_jLabelBack1MouseEntered

    private void jLabelBack1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBack1MouseExited
        jLabelBackTelaDevolucao.setVisible(false);
    }//GEN-LAST:event_jLabelBack1MouseExited

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
        jLabelMinimizejPanelTelaDevolucao.setVisible(true);
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
        jLabelMinimizejPanelTelaDevolucao.setVisible(false);
    }//GEN-LAST:event_jLabel13MouseExited

    private void jLabelCloseTelaDevolucaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaDevolucaoMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseTelaDevolucaoMouseClicked

    private void jLabelCloseTelaDevolucaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaDevolucaoMouseEntered
        jLabelClosejPanelTelaDevolucao.setVisible(true);
    }//GEN-LAST:event_jLabelCloseTelaDevolucaoMouseEntered

    private void jLabelCloseTelaDevolucaoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseTelaDevolucaoMouseExited
        jLabelClosejPanelTelaDevolucao.setVisible(false);
    }//GEN-LAST:event_jLabelCloseTelaDevolucaoMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        showBooksTelaDevolucao(jFormattedTextField1.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
        if (jTableBookDevolucao.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Escolha um livro!");
        } else if (Float.valueOf(jTableBookDevolucao.getValueAt(jTableBookDevolucao.getSelectedRow(), 7).toString()) > 0) {

            if(JOptionPane.showConfirmDialog(null, "Usuário está negativado! Deseja efetuar a devolução assim mesmo?") == 0){
                EfetuaDevolucaoLivro efetuaDevolucaoLivro = new EfetuaDevolucaoLivro();
                
                if(efetuaDevolucaoLivro.efetuaDevolucaoLivro((int) jTableBookDevolucao.getValueAt(jTableBookDevolucao.getSelectedRow(), 6))){
                    System.out.println("livro deletado com sucesso");
                }             
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonAlteraçãoTelaAdmin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlteraçãoTelaAdmin1ActionPerformed

        jPanelEstudante(false);
        jPanelTelaDevolucao(true);
        
        showBooksTelaDevolucao(jFormattedTextField1.getText());
    }//GEN-LAST:event_jButtonAlteraçãoTelaAdmin1ActionPerformed

    private void jFormattedTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField1KeyReleased
        showBooksTelaDevolucao(jFormattedTextField1.getText());
    }//GEN-LAST:event_jFormattedTextField1KeyReleased

    public String getUser() {
        return user;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setUser(String user) {
        LoginScreen.user = user;
    }

    public int getIdUserAdmin() {
        return idUserAdmin;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertConfPasswordRecuperaSenha;
    private javax.swing.JLabel alertCpfRecuperaSenha;
    private javax.swing.JLabel alertDataNascimentoRecuperaSenha;
    private javax.swing.JLabel alertNewPasswordRecuperaSenha;
    private javax.swing.JFormattedTextField anoLivro;
    private javax.swing.JLabel backTelaAdmin;
    private javax.swing.JLabel backTelaCadastraEstudante;
    private javax.swing.JLabel backTelaProfessor;
    private javax.swing.JLabel backTelaRecuperaSenha;
    private javax.swing.JFormattedTextField birthDate;
    private javax.swing.JRadioButton botaoAdminLogin;
    private javax.swing.JLabel botaoCadastrarEstudante;
    private javax.swing.JRadioButton botaoEstudanteLogin;
    private javax.swing.JRadioButton botaoProfessorLogin;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroupDeletaUser;
    private javax.swing.JLabel cadastrarNewPasswordRecuperaSenha;
    private javax.swing.JLabel close;
    private javax.swing.JLabel closeDeletarUsuario;
    private javax.swing.JLabel closeTelaAdmin;
    private javax.swing.JLabel closeTelaCadAdmin;
    private javax.swing.JLabel closeTelaCadEstudante;
    private javax.swing.JLabel closeTelaCadProfessor;
    private javax.swing.JLabel closeTelaEmprestimo;
    private javax.swing.JLabel closeTelaLogin;
    private javax.swing.JLabel closeTelaLoginFuncionario;
    private javax.swing.JLabel closeTelaProfessor;
    private javax.swing.JLabel closeTelaRecuperaSenha;
    private javax.swing.JFormattedTextField codUsuario;
    private javax.swing.JPasswordField cofimacaoSenha;
    private javax.swing.JPasswordField confimSenha;
    private javax.swing.JPasswordField confirmaSenhaRecuperaSenha;
    private javax.swing.JFormattedTextField cpfDeletaUsuario;
    private javax.swing.JFormattedTextField cpfFuncionario;
    private javax.swing.JFormattedTextField cpfProfessor;
    private javax.swing.JFormattedTextField cpfRecuperaSenha;
    private javax.swing.JFormattedTextField cpfUser;
    private javax.swing.JFormattedTextField dataNasciRecuperaSenha;
    private javax.swing.JFormattedTextField dataNascimento;
    private javax.swing.JButton deletaBook;
    private javax.swing.JLabel deletaUser;
    private javax.swing.JButton efetuarEmprestimo;
    private javax.swing.JRadioButton estudante;
    private javax.swing.JTextField idioma;
    private javax.swing.JLabel infoUser;
    private javax.swing.JFormattedTextField isbn;
    private javax.swing.JTextField isbnLivro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAlteraçãoTelaAdmin1;
    private javax.swing.JButton jButtonAutenticaFuncionario;
    private javax.swing.JButton jButtonCadastraEstudanteTelaAdmin;
    private javax.swing.JButton jButtonCadastraLivroTelaAdmin;
    private javax.swing.JButton jButtonCadastraProfessor;
    private javax.swing.JButton jButtonCadastraProfessorTelaAdmin;
    private javax.swing.JButton jButtonDeletarLivroTelaAdmin;
    private javax.swing.JButton jButtonDeletarUsuarioTelaAdmin;
    private javax.swing.JLabel jButtonEmprestimo;
    private javax.swing.JButton jButtonOkRecuperarSenha;
    private javax.swing.JButton jButtonRecuperarSenhaPanelErro;
    private javax.swing.JButton jButtonjPanelLoginScreen;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpfUsuarioLogin;
    private javax.swing.JPasswordField jFormattedTextFieldSenhaUsuarioLogin;
    private javax.swing.JLabel jL_CadLivro;
    private javax.swing.JLabel jLabeBackgroundlRecuperarSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAlertCpfFuncionario;
    private javax.swing.JLabel jLabelAlertCpfUsuarioLogin;
    private javax.swing.JLabel jLabelAlertSenhaFuncionario;
    private javax.swing.JLabel jLabelAlertSenhaLogin;
    private javax.swing.JLabel jLabelAutorName;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelBack1;
    private javax.swing.JLabel jLabelBack3;
    private javax.swing.JLabel jLabelBackTelaAdmin;
    private javax.swing.JLabel jLabelBackTelaCadProfessor;
    private javax.swing.JLabel jLabelBackTelaCadastroLivros;
    private javax.swing.JLabel jLabelBackTelaDeletaUser;
    private javax.swing.JLabel jLabelBackTelaDeletaUsuario;
    private javax.swing.JLabel jLabelBackTelaDeleteLivros;
    private javax.swing.JLabel jLabelBackTelaDevolucao;
    private javax.swing.JLabel jLabelBackTelaEmprestimo;
    private javax.swing.JLabel jLabelBackTelaEstudante;
    private javax.swing.JLabel jLabelBackTelaProfessor;
    private javax.swing.JLabel jLabelBackVisiblejPanelTelaEstudante;
    private javax.swing.JLabel jLabelBackground2TelaFuncionario;
    private javax.swing.JLabel jLabelBackground2TelaLogin;
    private javax.swing.JLabel jLabelBackgroundTelaDeletaUsuario;
    private javax.swing.JLabel jLabelBackgroundTelaFuncionario;
    private javax.swing.JLabel jLabelBackgroundjPanelLoginScreen;
    private javax.swing.JLabel jLabelBackjPanelTelaEstudante;
    private javax.swing.JLabel jLabelBanner;
    private javax.swing.JLabel jLabelBookTitle;
    private javax.swing.JLabel jLabelBookWeight;
    private javax.swing.JLabel jLabelBookYear;
    private javax.swing.JLabel jLabelClose;
    private javax.swing.JLabel jLabelCloseTelaCadastraProfessor;
    private javax.swing.JLabel jLabelCloseTelaCadastroEstudante;
    private javax.swing.JLabel jLabelCloseTelaDeletaLivros;
    private javax.swing.JLabel jLabelCloseTelaDeleteLivros;
    private javax.swing.JLabel jLabelCloseTelaDevolucao;
    private javax.swing.JLabel jLabelCloseTelaEmprestimo;
    private javax.swing.JLabel jLabelCloseTelaEstudante;
    private javax.swing.JLabel jLabelCloseTelaFuncionario;
    private javax.swing.JLabel jLabelCloseTelaProfessor;
    private javax.swing.JLabel jLabelClosejPanelLoginScreen;
    private javax.swing.JLabel jLabelClosejPanelTelaDevolucao;
    private javax.swing.JLabel jLabelClosejPanelTelaEstudante;
    private javax.swing.JLabel jLabelConfirmSenha;
    private javax.swing.JLabel jLabelConfirmarSenha;
    private javax.swing.JLabel jLabelCpf;
    private javax.swing.JLabel jLabelCpf1;
    private javax.swing.JLabel jLabelCpfEstudante;
    private javax.swing.JLabel jLabelCpfEstudante1;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelData1;
    private javax.swing.JLabel jLabelEmprestimo;
    private javax.swing.JLabel jLabelEsqueceSenhaFuncionario;
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JLabel jLabelIconFuncionario;
    private javax.swing.JLabel jLabelIconTelaProfessor;
    private javax.swing.JLabel jLabelIdEstudante;
    private javax.swing.JLabel jLabelImageErro;
    private javax.swing.JLabel jLabelImageOK;
    private javax.swing.JLabel jLabelImageSplashScreen;
    private javax.swing.JLabel jLabelMinimizaTelaCadastraProfessor;
    private javax.swing.JLabel jLabelMinimizeTelaCadLivros;
    private javax.swing.JLabel jLabelMinimizeTelaCadastroEstudante;
    private javax.swing.JLabel jLabelMinimizeTelaDeleteLivro;
    private javax.swing.JLabel jLabelMinimizeTelaDeleteLivros;
    private javax.swing.JLabel jLabelMinimizeTelaEmprestimo;
    private javax.swing.JLabel jLabelMinimizeTelaFuncionario;
    private javax.swing.JLabel jLabelMinimizeTelaProfessor;
    private javax.swing.JLabel jLabelMinimizejPanelLoginScreen;
    private javax.swing.JLabel jLabelMinimizejPanelTelaDevolucao;
    private javax.swing.JLabel jLabelMinimizejPanelTelaEstudante;
    private javax.swing.JLabel jLabelNomeEstudante;
    private javax.swing.JLabel jLabelNovaSenha;
    private javax.swing.JLabel jLabelPublisherName;
    private javax.swing.JLabel jLabelReturn;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JLabel jLabelSenha1;
    private javax.swing.JLabel jLabelSenha2;
    private javax.swing.JLabel jLabelTelefoneEstudante;
    private javax.swing.JLabel jLabelText;
    private javax.swing.JLabel jLabelTextCadProfessor;
    private javax.swing.JLabel jLabelTextoErro;
    private javax.swing.JLabel jLabelTextoOK;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelAlertErroRecuperaSenha;
    private javax.swing.JPanel jPanelAlertSucessoRecuperaSenha;
    private javax.swing.JPanel jPanelCadastraEstudante;
    private javax.swing.JPanel jPanelLoginScreen;
    private javax.swing.JPanel jPanelProfessorEstudanteAdmin;
    private javax.swing.JPanel jPanelTelaCadastraLivros;
    private javax.swing.JPanel jPanelTelaCadastraProfessor;
    private javax.swing.JPanel jPanelTelaDeletaUsuario;
    private javax.swing.JPanel jPanelTelaDeletarBooks;
    private javax.swing.JPanel jPanelTelaDevolucao;
    private javax.swing.JPanel jPanelTelaEmprestimo;
    private javax.swing.JPanel jPanelTelaEstudante;
    private javax.swing.JPanel jPanelTelaLoginFuncionario;
    private javax.swing.JPanel jPanelTelaProfessor;
    private javax.swing.JPanel jPanelTelaRecuperarSenha;
    private javax.swing.JPanel jPanelTelaTecAdmin;
    private javax.swing.JPanel jPanel_splashScreen;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel jReturn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableBookDevolucao;
    private javax.swing.JTable jTableBookEmprestimo;
    private javax.swing.JTable jTableDeleteBooks;
    private javax.swing.JFormattedTextField matriculaProfessor;
    private javax.swing.JLabel minimize;
    private javax.swing.JLabel minimizeTelaAdmin;
    private javax.swing.JLabel minimizeTelaCadEstudante;
    private javax.swing.JLabel minimizeTelaCadLivros;
    private javax.swing.JLabel minimizeTelaCadProfessor;
    private javax.swing.JLabel minimizeTelaEmprestimo;
    private javax.swing.JLabel minimizeTelaLogin;
    private javax.swing.JLabel minimizeTelaLoginFuncionario;
    private javax.swing.JLabel minimizeTelaProfessor;
    private javax.swing.JLabel minimizeTelaRecuperaSenha;
    private javax.swing.JLabel minimizeTelaUsuario;
    private javax.swing.JTextField nameStudent;
    private javax.swing.JPasswordField newPasswordRecuperaSenha;
    private javax.swing.JTextField nomeAutor;
    private javax.swing.JTextField nomeEditora;
    private javax.swing.JTextField nomeProfessorr;
    private javax.swing.JTextField numPaginas;
    private javax.swing.JTextField pais;
    private javax.swing.JPasswordField passwordUser;
    private javax.swing.JTextField pesoLivro;
    private javax.swing.JTextField pesquisaLivro;
    private javax.swing.JFormattedTextField phoneUser;
    private javax.swing.JRadioButton professor;
    private javax.swing.JTextField quantidadeBook;
    private javax.swing.JLabel recuperarSenhaLogin;
    private javax.swing.JPasswordField senhaDeletaUsuario;
    private javax.swing.JPasswordField senhaFuncionario;
    private javax.swing.JPasswordField senhaProfessor;
    private javax.swing.JLabel showNameProfessor;
    private javax.swing.JFormattedTextField telefoneProfessorr;
    private javax.swing.JTextField tituloLivro;
    // End of variables declaration//GEN-END:variables

}
