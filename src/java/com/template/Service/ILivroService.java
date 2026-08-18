package com.template.Service;

import com.template.Model.dto.LivroDTO;
import java.util.List;

public interface ILivroService {

    void cadastrarLivro(LivroDTO livro) throws Exception;

    List<LivroDTO> listarLivro() throws Exception;

    void atualizarLivro(LivroDTO livro) throws Exception;

    void excluirLivro(int id) throws Exception;
}