package com.template.Validator;

public class NumeroValidator implements ILivroValidator<String> {

    private final String valor;

    public NumeroValidator(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean validar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getMensagemErro() {
        return "O valor deve ser um número válido.";
    }

    @Override
    public String getValor() {
        return valor;
    }
}
