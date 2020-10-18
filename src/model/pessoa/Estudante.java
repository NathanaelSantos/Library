package model.pessoa;

import model.user.User;

public class Estudante extends User {

    private String matriculaEstudante;

    public String getMatriculaEstudante() {
        return matriculaEstudante;
    }

    public void setMatriculaEstudante(String matriculaEstudante) {
        this.matriculaEstudante = matriculaEstudante;
    }

}
