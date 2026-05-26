package com.template;

// usado apenas para transitar informações pelo sistema
public class LivroDTO {

    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private int paginas;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getPaginas() { return paginas; }
    public void setPaginas(int paginas) { this.paginas = paginas; }

    @Override
    public String toString() {
        return String.format("ID: %d | Titulo: %-20s | Autor: %-15s | Genero: %-10s | Pags: %d \n",
                id, titulo, autor, genero, paginas);
    }
}