package com.template.Validator;

import java.util.ArrayList;
import java.util.List;

import static com.template.Util.DialogUtil.showError;
import static com.template.Util.DialogUtil.showWarning;

public class LivroValidador implements ILivroValidador {

    public boolean validarLivro(String titulo, String autor, String genero, String paginas) {

        List<IValidador<String>> validadores = new ArrayList<>();

        validadores.add(new CampoObrigatorioValidador("Titulo", titulo));
        validadores.add(new CampoObrigatorioValidador("Autor", autor));
        validadores.add(new CampoObrigatorioValidador("Genero", genero));
        validadores.add(new CampoObrigatorioValidador("Paginas", paginas));

        validadores.add(new NumeroValidador(paginas));

        for (IValidador<String> validador : validadores) {

            if (!validador.validar(validador.getValor())) {
                showError(validador.getMensagemErro());
                return false;
            }
        }

        return true;
    }
}
