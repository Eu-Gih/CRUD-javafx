package com.template.Validator;

import com.template.Model.dto.LivroDTO;
import com.template.Util.DialogUtil;

public class LivroValidator {

    public boolean validarLivro(LivroDTO dto) {
        if (dto.getTitulo().trim().isEmpty()
                || dto.getGenero().trim().isEmpty()
                || dto.getAutor().trim().isEmpty()
                || dto.getPaginas() <= 0) {

            DialogUtil.showWarning("Atenção", "Faltam informações!", "Por favor, preencha todos os campos obrigatórios.");
            return false;
        }
        return true;
    }
}