package br.com.techdive.banco.manuprinj.util;


import java.math.BigDecimal;
import java.math.RoundingMode;


public class FormatacaoFinanceira {

    public static String formatar(double valor) {
        return "R$ "+ BigDecimal.valueOf(valor)
                .setScale(2, RoundingMode.HALF_EVEN)
                .toString().replace(".", ",");
    }
}
