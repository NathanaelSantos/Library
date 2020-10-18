package model.book;

public class Book extends DevolucaoLivro {

    private String nomeEditora;
    private short numeroPaginas;
    private String nomeAutor;
    private float pesoLivro;
    private short quantidade;
    private String idioma;
    private Object titulo;
    private String pais;
    private String ISBN;
    private short ano;
    private int id;

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(short numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getNomeEditora() {
        return nomeEditora;
    }

    public void setNomeEditora(String nomeEditora) {
        this.nomeEditora = nomeEditora;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(short quantidade) {
        this.quantidade = quantidade;
    }

    public float getPesoLivro() {
        return pesoLivro;
    }

    public void setPesoLivro(float pesoLivro) {
        this.pesoLivro = pesoLivro;
    }

    public Object getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(short ano) {
        this.ano = ano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
