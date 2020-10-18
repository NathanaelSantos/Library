package model.pessoa;

import model.user.User;

public class Professor extends User {

    private String matriculaProfessor;

    public String getMatriculaProfessor() {
        return matriculaProfessor;
    }

    public void setMatriculaProfessor(String matriculaProfessor) {
        this.matriculaProfessor = matriculaProfessor;
    }

}
