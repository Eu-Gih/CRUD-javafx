package com.template.Validator;

public interface ILivroValidator <T>{
    boolean validar (T valor);
    boolean validarNumero (T valor);
    String getMensagemErro();
    String getMensagemErroNumero();
    T getValor();
}


