package com.template.Validator;

public interface IValidador<T>{
    boolean validar (T valor);
    String getMensagemErro();
    T getValor();
}
