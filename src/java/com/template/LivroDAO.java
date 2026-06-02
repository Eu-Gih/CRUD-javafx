package com.template;
import com.template.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

public class LivroDAO {

    static final Logger logger = Logger.getLogger(LivroDAO.class.getName());

    public void cadastrarLivro(LivroDTO livro) {
        String sql = "INSERT INTO livros (titulo, autor, genero, paginas) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setString(3, livro.getGenero());
            pstmt.setInt(4, livro.getPaginas());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao cadastrar livros", e);
        }
    }

    public ArrayList<LivroDTO> listarLivros() {
        String sql = "SELECT * FROM livros ORDER BY id";
        ArrayList<LivroDTO> listaLivros = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                LivroDTO livro = new LivroDTO();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setGenero(rs.getString("genero"));
                livro.setPaginas(rs.getInt("paginas"));
                listaLivros.add(livro);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar livros", e);
        }
        return listaLivros;
    }

    public void atualizarLivro(LivroDTO livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, genero = ?, paginas = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setString(3, livro.getGenero());
            pstmt.setInt(4, livro.getPaginas());
            pstmt.setInt(5, livro.getId());

            pstmt.executeUpdate(); // <-- LINHA CORRIGIDA (Faltava executar o update no banco!)

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar livros", e);
        }
    }

    public void excluirLivro(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir livros", e);
        }
    }
}