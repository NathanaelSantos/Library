package contoller;

import View.LoginScreen;

public class Main {

    public static void main(String[] args) {
        
        XamppExec exec = new XamppExec();
        exec.startApacheAndMysql();
        
        java.awt.EventQueue.invokeLater(() -> {
            new LoginScreen().setVisible(true);
        });
    }
}
