package com.template.Validator;

import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.List;

import static com.template.Util.DialogUtil.showWarning;

public class UsuarioValidador {

    public boolean validarUsuario(String titulo, String autor, String genero, String pagina) {

        List<ILivroValidator<String>> validadores = new ArrayList<>();

        validadores.add(new CampoObrigatorioValidador("Titulo", titulo));
        validadores.add(new CampoObrigatorioValidador("Autor", autor));
        validadores.add(new CampoObrigatorioValidador("Genero", genero));
        validadores.add(new CampoObrigatorioValidador("Paginas", pagina));

        validadores.add(new NumeroValidador());

        for (ILivroValidator<String> validador : validadores) {

            if (!validador.validar(validador.getValor())) {
                showWarning(validador.getMensagemErro());
                return false;
            }
        }

        return true;
    }
}
