package br.com.techdive.banco.manuprinj;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Conta {

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

    public void saque() {
        System.out.println("Digite o valor que você deseja sacar: ");
        double valorSaque = sc.nextDouble();
        sc.nextLine();

        if (isSaldoPermiteRetirada(valorSaque)) {
            saldo -= valorSaque;
            historico.add(new Transacao(this, null, valorSaque, TipoTransacao.SAQUE));
        } else {
            System.out.println("Saldo Insuficiente");
        }
    }

    public void deposito() {
        System.out.println("Digite o valor que você deseja depositar: ");
        double valorDeposito = sc.nextDouble();
        sc.nextLine();

        saldo += valorDeposito;
        historico.add(new Transacao(this, null, valorDeposito, TipoTransacao.DEPOSITO));
    }

    public double saldo() {
        return saldo;
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
        return valor < saldo || isChequeEspecial() && valor < saldo + rendaMensal;
    }

    public boolean isChequeEspecial() {
        return false;
    }

    public List<Transacao> extrato() {
        return historico;
    }

    public void alterarDadosCadastrais() {

    }

    @Override
    public String toString() {
        return "Conta{" + "nome='" + nome + '\'' + ", cpf='" + cpf + '\'' + ", numeroConta=" + numeroConta
                + ", agenciaConta=" + agenciaConta + ", saldo=" + saldo + ", rendaMensal=" + rendaMensal + '}';
    }

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
