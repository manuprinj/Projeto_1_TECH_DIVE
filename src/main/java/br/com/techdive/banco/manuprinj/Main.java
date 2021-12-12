package br.com.techdive.banco.manuprinj;


import static br.com.techdive.banco.manuprinj.util.FormatacaoEntradas.getCPF;
import static br.com.techdive.banco.manuprinj.util.FormatacaoEntradas.getDouble;
import static br.com.techdive.banco.manuprinj.util.FormatacaoEntradas.getInt;
import static br.com.techdive.banco.manuprinj.util.FormatacaoEntradas.getString;
import static br.com.techdive.banco.manuprinj.util.FormatacaoFinanceira.formatar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.techdive.banco.manuprinj.util.FormatacaoEntradas;
import br.com.techdive.banco.manuprinj.util.FormatacaoFinanceira;
import br.com.techdive.banco.manuprinj.util.ValidacaoCPF;


public class Main {

    public static int numeroConta = 1;
    public static List<Conta> contas = new ArrayList<>();
    public static Scanner option = new Scanner(System.in);

    public static void menu() {
        System.out.println("Seja bem-vindo ao banco TechDive");
        System.out.println("Qual operação você deseja realizar?");
        System.out.println("1 - Criar Conta");
        System.out.println("2 - Saque");
        System.out.println("3 - Depósito");
        System.out.println("4 - Visualizar o saldo");
        System.out.println("5 - Extrato da conta");
        System.out.println("6 - Transferência");
        System.out.println("7 - Alteração de dados pessoais");
        System.out.println("8 - Relatórios");
        System.out.println("9 - Simulações");
        int tipoOperacao = getInt();

        if (tipoOperacao < 1 || tipoOperacao > 9) System.out.println("Digite uma opção válida!!");
        if (tipoOperacao == 1) criarConta();
        if (tipoOperacao == 2) saque();
        if (tipoOperacao == 3) deposito();
        if (tipoOperacao == 4) visualizarSaldo();
        if (tipoOperacao == 5) extrato();
        if (tipoOperacao == 6) transferencia();
        if (tipoOperacao == 7) alteracaoDados();
        if (tipoOperacao == 8) Relatorios.relatorios();
        if (tipoOperacao == 9) simulacoes();
    }

    public static void criarConta() {
        System.out.println("Digite que tipo de conta você deseja criar: ");
        System.out.println("1 - Conta Poupança");
        System.out.println("2 - Conta Corrente");
        System.out.println("3 - Conta Investimento");
        int tipoConta = getInt();

        String nome = getString("Digite seu nome:");

        String cpf = getCPF("Digite seu CPF:");

        double rendaMensal = getDouble("Digite sua renda mensal: ");

        System.out.println("Selecione uma das agências abaixo: ");
        System.out.println("1 -> 001 - Florianópolis");
        System.out.println("2 -> 002 - São José");
        int agenciaConta = getInt();

        if(tipoConta==1) {
            ContaPoupanca conta = new ContaPoupanca(nome, cpf, numeroConta, agenciaConta, 0, rendaMensal);
            numeroConta++;
            contas.add(conta);
        }

        if(tipoConta==2) {
            ContaCorrente conta = new ContaCorrente(nome, cpf, numeroConta, agenciaConta, 0, rendaMensal);
            numeroConta++;
            contas.add(conta);
        }

        if(tipoConta==3) {
            System.out.println("Selecione o tipo de investimento: ");
            System.out.println("1 - Tesouro Direto");
            System.out.println("2 - Certificado de Depósito Bancário (CDB)");
            System.out.println("3 - Letra de Crédito Imobiliário (LCI)");
            int tipoInvestimento = getInt();

            double rentabilidade = 0;

            if (tipoInvestimento == 1) {
                rentabilidade = 0.07;
            } else if (tipoInvestimento == 2) {
                rentabilidade = 0.08;
            } else if (tipoInvestimento == 3) {
                rentabilidade = 0.09;
            }

            ContaInvestimento conta = new ContaInvestimento(nome, cpf, numeroConta, agenciaConta, 0, rendaMensal, rentabilidade);
            numeroConta++;
            contas.add(conta);
        }
    }

    public static Conta validacaoContaAgencia() {
        System.out.println("Digite o número da agência: ");
        System.out.println("1 -> 001 - Florianópolis ou 2 ->  002 - São José: ");
        int numAgencia = getInt();
        System.out.println("Digite o número da conta: ");
        int numConta = getInt();

        for (Conta conta : contas) {
            if(conta.getNumeroConta() == numConta && conta.getAgenciaConta() == numAgencia) return conta;
        }

        System.out.println("Conta não encontrada!");
        return null;
    }

    public static void saque() {
        Conta conta = validacaoContaAgencia();
        if (conta == null) return;
        double valorSaque = getDouble("Digite o valor que você deseja sacar: ");
        conta.saque(valorSaque);
    }

    public static void deposito() {
        Conta conta = validacaoContaAgencia();
        if (conta == null) return;
        double valorDeposito = getDouble("Digite o valor que você deseja depositar:");
        conta.deposito(valorDeposito);
    }

    public static void visualizarSaldo() {
        Conta conta = validacaoContaAgencia();
        if (conta != null) System.out.println("Seu saldo é de: " + conta.saldo());
    }

    public static void transferencia() {
        System.out.println("Origem: ");
        Conta contaOrigem = validacaoContaAgencia();
        if (contaOrigem == null) return;

        System.out.println("Destino: ");
        Conta contaDestino = validacaoContaAgencia();
        if (contaDestino == null) return;

        double valorTranferencia = getDouble("Digite o valor que você deseja transferir: ");
        if (contaDestino.equals(contaOrigem)) {
            System.out.println("Conta de Origem não pode ser igual a Conta de Destino");
            return;
        }

        contaOrigem.tranferir(contaDestino, valorTranferencia);
    }

    public static void extrato() {
        Conta conta = validacaoContaAgencia();
        if (conta == null) return;
        for (Transacao extrato : conta.extrato()) {
            System.out.println(extrato);
        }
    }

    public static void alteracaoDados() {
        Conta conta = validacaoContaAgencia();
        if (conta != null) conta.alterarDadosCadastrais();
    }

    public static void simulacoes() {
        Conta conta = validacaoContaAgencia();
        if (conta == null) return;
        if (conta instanceof ContaPoupanca) {
            System.out.println("Digite a quantidade de meses a ser simulada: ");
            int meses = getInt();

            System.out.println("Digite a rentabilidade anual da poupança: ");
            double rentabilidadeAnual = getInt();

            double simulacao = ((ContaPoupanca) conta).simulacaoInvestimento(meses, rentabilidadeAnual);

            System.out.println("O valor da sua simulação é: " + formatar(simulacao));
        }

        if (conta instanceof ContaCorrente) System.out.println("Não há opções de simulações para este tipo de conta!");

        if (conta instanceof ContaInvestimento) {
            System.out.println("Digite a quantidade de meses a ser simulada: ");
            int meses = getInt();

            double simulacao = ((ContaInvestimento) conta).simulacaoInvestimento(meses);

            System.out.println("O valor da sua simulação é: " + formatar(simulacao));
        }
    }

    public static void main(String[] args) {
        while (true){
            menu();
        }
    }
}
