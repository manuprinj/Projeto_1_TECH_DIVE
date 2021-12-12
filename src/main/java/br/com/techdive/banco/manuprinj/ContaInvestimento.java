package br.com.techdive.banco.manuprinj;


public class ContaInvestimento extends Conta {

    private double rentabilidadeAnual;

    public ContaInvestimento(String nome, String cpf, int numeroConta, int agenciaConta, double saldo,
            double rendaMensal, double rentabilidadeAnual) {
        super(nome, cpf, numeroConta, agenciaConta, saldo, rendaMensal);
        this.rentabilidadeAnual = rentabilidadeAnual;
    }

    public double simulacaoInvestimento(int meses) {
        double rentabilidadeMensal = Math.pow(1+rentabilidadeAnual, 1.0/12);

        double simulacao = saldo();
        for (int i = 0; i < meses; i++) {
            simulacao *= rentabilidadeMensal;
        }
        return simulacao;
    }

    public double getRentabilidadeAnual() {
        return rentabilidadeAnual;
    }

    public void setRentabilidadeAnual(double rentabilidadeAnual) {
        this.rentabilidadeAnual = rentabilidadeAnual;
    }
}
