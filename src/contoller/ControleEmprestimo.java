package contoller;

import model.cadastra.CadastraEmprestimo;
import model.book.QuantidadeLivro;
import model.check.CheckUser;
import model.book.DecreQtdLivro;

public class ControleEmprestimo {

    private boolean check = false;
    private int idLivro;
    private int idUsuario;
    
    public boolean controleEmprestimo(int idLivro) {
       
        this.idLivro = idLivro;

        if (new QuantidadeLivro().quantidadeLivro(this.idLivro) == 0) {
             return check;
        } else {
            idUsuario = new CheckUser().idUser();
            if (new CadastraEmprestimo().cadastraEmprestimo(idUsuario, idLivro)) {
                new DecreQtdLivro().auteraQuantidadeLivro(idLivro);
                check = true;
            }
        }      
        return check;
    }
}
