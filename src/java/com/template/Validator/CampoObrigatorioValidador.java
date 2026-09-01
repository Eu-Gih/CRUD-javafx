package com.template.Validator;

public class CampoObrigatorioValidador implements IValidador<String> {

    private final String nomeCampo;
    private final String valor;

    public CampoObrigatorioValidador(String nomeCampo, String valor) {
        this.nomeCampo = nomeCampo;
        this.valor = valor;
    }

    @Override
    public boolean validar(String valor) {
        return this.valor != null && this.valor.trim().isEmpty();
    }

    @Override
    public String getMensagemErro() {
        return "O campo " + nomeCampo + "deve ser preenchido";
    }

    @Override
    public String getValor() {
        return valor;
    }
}

