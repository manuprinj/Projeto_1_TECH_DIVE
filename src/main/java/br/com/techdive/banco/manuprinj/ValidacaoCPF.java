package br.com.techdive.banco.manuprinj;


public class ValidacaoCPF {

    public static void main(String[] args) {
        System.out.println(isCPFValido("09817663906"));
    }

    public static boolean isCPFValido(String cpf) {
        if (cpf.length() != 11) return false;

        int multiplicador = 10;
        int soma = 0;
        int digitoVerficador1;
        int digitoVerficador2;


        for (int i = 0; i < cpf.length() - 2; i++) {
            int num = (cpf.charAt(i) - 48) * multiplicador;
            multiplicador--;
            soma += num;
        }

        int resto = soma%11;
        if (resto >= 2) {
            digitoVerficador1 = 11 - resto;
        } else {
            digitoVerficador1 = 0;
        }

        soma = 0;
        multiplicador = 11;
        for (int i = 0; i < cpf.length() - 2; i++) {
            int num = (cpf.charAt(i) - 48) * multiplicador;
            multiplicador--;
            soma += num;
        }

       resto = soma%11;
        if (resto >= 2) {
            digitoVerficador2 = 11 - resto;
        } else {
            digitoVerficador2 = 0;
        }

        return digitoVerficador1 == (cpf.charAt(9) - 48) && digitoVerficador2 == (cpf.charAt(10) - 48);
    }
}
