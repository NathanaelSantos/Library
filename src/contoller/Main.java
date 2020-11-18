package contoller;

import View.LoginScreen;

public class Main {

    public static void main(String[] args) {

//        new XamppExec().startApacheAndMysql();
        java.awt.EventQueue.invokeLater(() -> {
            new LoginScreen().setVisible(true);
        });
    }
}
