package com.template.Validator;

import java.util.ArrayList;
import java.util.List;

import static com.template.Util.DialogUtil.showWarning;

public class UsuarioValidator {

    public boolean validarUsuario(
            String nome,
            String email,
            String senha,
            String login,
            String numero
    ) {

        List<ILivroValidator<String>> validadores = new ArrayList<>();

        validadores.add(new CampoObrigatorioValidador("Nome", nome));
        validadores.add(new CampoObrigatorioValidador("E-mail", email));
        validadores.add(new CampoObrigatorioValidador("Senha", senha));
        validadores.add(new CampoObrigatorioValidador("Login", login));

        validadores.add(new NumeroValidator(numero));

        for (ILivroValidator<String> validador : validadores) {
            if (!validador.validar(validador.getValor())) {
                showWarning( "Atenção", "Erro de validação",validador.getMensagemErro());
                return false;
            }
        }

        return true;
    }
}
