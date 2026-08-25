package com.template.Validator;

public class NumeroValidador implements ILivroValidator<String>{

        private final String valor;
        private String mensagemError;

        public NumeroValidador(String valor) {
            this.valor = valor;
            this.mensagemError = "O valor informado deve ser um número.";
        }

        @Override
        public boolean validar(String valor) {
            if (valor == null || valor.trim().isEmpty()) {
                return false;
            }

            try {
                Double.parseDouble(valor);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @Override
        public String getValor() {
            return valor;
        }

        public String getMensagemErro() {
            return mensagemError;
        }
    }

