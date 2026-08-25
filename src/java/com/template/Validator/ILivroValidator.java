package com.template.Validator;

public interface ILivroValidator <T>{
    boolean validar (T valor);
    String getMensagemErro();
    T getValor();
}


