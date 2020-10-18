package model.user;

import model.pessoa.Pessoa;

public class User extends Pessoa {
    
    private double multaTotal;
    private double multa;
    private String password;

    public double getMultaTotal() {
        return multaTotal;
    }

    public void setMultaTotal(double multaTotal) {
        this.multaTotal = multaTotal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public double getMulta(){
        return multa;
    }
    
    public void setMulta(double multa){
        this.multa = multa;
    }

}
