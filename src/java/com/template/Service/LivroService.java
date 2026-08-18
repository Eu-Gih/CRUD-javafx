package com.template.Service;

import com.template.Model.dao.LivroDAO;
import com.template.Model.dto.LivroDTO;
import java.util.List;

public class LivroService implements ILivroService{
    private final LivroDAO livroDAO;

    public LivroService() {
        this.livroDAO = new LivroDAO();
    }

    @Override
    public void cadastrarLivro(LivroDTO livro) throws Exception {
        livroDAO.cadastrarLivro(livro);
    }

    @Override
    public List<LivroDTO> listarLivro() throws Exception {
        return livroDAO.listarLivros();
    }

    @Override
    public void atualizarLivro(LivroDTO livro) throws Exception {
        livroDAO.atualizarLivro(livro);
    }

    @Override
    public void excluirLivro(int id) throws Exception {
        livroDAO.excluirLivro(id);
    }

}




