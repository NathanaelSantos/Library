package model.pessoa;

import model.user.User;

public class Admin extends User {

    private String matriculaAdmin;

    public String getMatriculaAdmin() {
        return matriculaAdmin;
    }

    public void setMatriculaAdmin(String matriculaAdmin) {
        this.matriculaAdmin = matriculaAdmin;
    }
}
