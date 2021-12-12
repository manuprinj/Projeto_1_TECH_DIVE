package br.com.techdive.banco.manuprinj;


public class ContaCorrente extends Conta {

    public ContaCorrente(String nome, String cpf, int numeroConta, int agenciaConta, double saldo, double rendaMensal) {
        super(nome, cpf, numeroConta, agenciaConta, saldo, rendaMensal);
    }

    @Override
    public boolean isChequeEspecial() {
        return true;
    }

    @Override
    public String getTipo() {
        return "Conta Corrente";
    }

}
