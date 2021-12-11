package br.com.techdive.banco.manuprinj;


public class ContaInvestimento extends Conta {

    private double rentabilidade;

    public ContaInvestimento(String nome, String cpf, int numeroConta, int agenciaConta, double saldo,
            double rendaMensal, double rentabilidade) {
        super(nome, cpf, numeroConta, agenciaConta, saldo, rendaMensal);
        this.rentabilidade = rentabilidade;
    }

    public double simulacaoInvestimento(int meses) {
        double simulacao = saldo();
        for (int i = 0; i < meses; i++) {
            simulacao *= rentabilidade;
        }
        return simulacao;
    }

    public double getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(double rentabilidade) {
        this.rentabilidade = rentabilidade;
    }
}
