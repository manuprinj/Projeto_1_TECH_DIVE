package br.com.techdive.banco.manuprinj;


public class ContaPoupanca extends Conta {

    public ContaPoupanca(String nome, String cpf, int numeroConta, int agenciaConta, double saldo, double rendaMensal) {
        super(nome, cpf, numeroConta, agenciaConta, saldo, rendaMensal);
    }

    @Override
    public String getTipo() {
        return "Conta Poupança";
    }

    public double simulacaoInvestimento(int meses, double rentabilidadeAnual) {
        double simulacao = getSaldo();
        double rentabilidadeMensal = Math.pow(1+rentabilidadeAnual, 1.0/12);
        for (int i = 0; i < meses; i++) {
            simulacao *= rentabilidadeMensal;
        }
        return simulacao;
    }
}
