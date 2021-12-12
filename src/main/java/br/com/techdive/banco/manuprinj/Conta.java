package br.com.techdive.banco.manuprinj;


import static br.com.techdive.banco.manuprinj.util.FormatacaoEntradas.getDouble;
import static br.com.techdive.banco.manuprinj.util.FormatacaoEntradas.getInt;
import static br.com.techdive.banco.manuprinj.util.FormatacaoEntradas.getString;
import static br.com.techdive.banco.manuprinj.util.FormatacaoFinanceira.formatar;
import static br.com.techdive.banco.manuprinj.util.ValidacaoCPF.getMascaraCPF;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import br.com.techdive.banco.manuprinj.tipos.TipoTransacao;


public abstract class Conta {

    private String nome;
    private String cpf;
    private int numeroConta;
    private int agenciaConta;
    private double saldo;
    private double rendaMensal;
    private List<Transacao> historico = new ArrayList<>();

    public Conta(String nome, String cpf, int numeroConta, int agenciaConta, double saldo, double rendaMensal) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroConta = numeroConta;
        this.agenciaConta = agenciaConta;
        this.saldo = saldo;
        this.rendaMensal = rendaMensal;
    }

    Scanner sc = new Scanner(System.in);

    public void saque(double valorSaque) {
        if (isSaldoPermiteRetirada(valorSaque)) {
            saldo -= valorSaque;
            historico.add(new Transacao(this, null, valorSaque, TipoTransacao.SAQUE));
        } else {
            System.out.println("Saldo Insuficiente");
        }
    }

    public void deposito(double valorDeposito) {
        saldo += valorDeposito;
        historico.add(new Transacao(this, null, valorDeposito, TipoTransacao.DEPOSITO));
    }

    public String saldo() {
        return formatar(saldo);
    }

    public void tranferir(Conta contaDestino, double valorTranferencia) {
        if (isSaldoPermiteRetirada(valorTranferencia)) {
            saldo -= valorTranferencia;
            contaDestino.saldo += valorTranferencia;
            Transacao transacao = new Transacao(this, contaDestino, valorTranferencia, TipoTransacao.TRANSFERENCIA);
            historico.add(transacao);
            contaDestino.historico.add(transacao);
        } else {
            System.out.println("Saldo Insuficiente");
        }
    }

    private boolean isSaldoPermiteRetirada(double valor) {
        return valor <= saldo || isChequeEspecial() && valor <= saldo + rendaMensal;
    }

    public boolean isChequeEspecial() {
        return false;
    }

    public List<Transacao> extrato() {
        return historico;
    }

    public void alterarDadosCadastrais() {
        System.out.println("Qual dado você deseja alterar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Renda Mensal");
        System.out.println("3 - Agência");
        int opcao = getInt();

        if (opcao == 1) {
            System.out.println("Seu nome atual é: " + getNome());
            String nomeNovo = getString("Digite o novo nome:");

            setNome(nomeNovo);
            System.out.println(getNome());
        }

        if (opcao == 2) {
            System.out.println("Sua renda atual é: " + getRendaMensal());
            double rendaMensalNova = getDouble("Digite a nova renda mensal: ");

            setRendaMensal(rendaMensalNova);
            System.out.println(getRendaMensal());
        }

        if (opcao == 3) {
            System.out.println("Sua agência atual é: " + getAgenciaConta());
            System.out.println("Digite a nova agência: ");
            System.out.println("1 -> 001 - Florianópolis");
            System.out.println("2 -> 002 - São José");
            int agenciaNova = getInt();

            setAgenciaConta(agenciaNova);
            System.out.println(getAgenciaConta());
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" | ")
                .add(getTipo())
                .add("Nome: " + getNome())
                .add("CPF: " + getMascaraCPF(getCpf()))
                .add("Agência: " + getAgenciaConta() + " Conta: " + getNumeroConta());

        return joiner.toString();
    }

    public abstract String getTipo();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public int getAgenciaConta() {
        return agenciaConta;
    }

    public void setAgenciaConta(int agenciaConta) {
        this.agenciaConta = agenciaConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }
}
