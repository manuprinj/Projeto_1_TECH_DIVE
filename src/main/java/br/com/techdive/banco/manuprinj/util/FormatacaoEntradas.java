package br.com.techdive.banco.manuprinj.util;


import java.util.Scanner;


public class FormatacaoEntradas {

    public static final Scanner SC = new Scanner(System.in);
    public static final String VALOR_INVALIDO = "Valor inválido!";

    public static double getDouble(String mensagem) {
        Double valor = null;
        while (valor == null) {
            System.out.println(mensagem);
            try {
                valor = Double.parseDouble(SC.nextLine().replace(",", "."));
            } catch (Exception e) {
                System.out.println(VALOR_INVALIDO);
            }
        }
        return valor;
    }

    public static int getInt() {
        Integer valor = null;
        while (valor == null) {
            try {
                valor = Integer.parseInt(SC.nextLine());
            } catch (Exception e) {
                System.out.println(VALOR_INVALIDO);
            }
        }
        return valor;
    }

    public static String getString(String mensagem) {
        String valor = "";
        while (valor.isBlank()) {
            System.out.println(mensagem);
            valor = SC.nextLine();
        }
        return valor;
    }

    public static String getCPF(String mensagem) {
        String cpf = "";
        while (cpf.isBlank() && !ValidacaoCPF.isCPFValido(cpf)) {
            System.out.println(mensagem);
            cpf = SC.nextLine();
        }
        return cpf;
    }
}
