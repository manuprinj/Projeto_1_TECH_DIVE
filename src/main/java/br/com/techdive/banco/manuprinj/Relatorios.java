package br.com.techdive.banco.manuprinj;


import static br.com.techdive.banco.manuprinj.util.FormatacaoEntradas.getCPF;
import static br.com.techdive.banco.manuprinj.util.FormatacaoEntradas.getInt;
import static br.com.techdive.banco.manuprinj.util.FormatacaoFinanceira.formatar;

import java.util.Scanner;

import br.com.techdive.banco.manuprinj.util.FormatacaoFinanceira;


public class Relatorios {

    public static Scanner option = new Scanner(System.in);

    public static void relatorios() {
        System.out.println("Qual relatório você deseja?");
        System.out.println("1 - Listar todas as contas");
        System.out.println("2 - Contas com saldo negativo");
        System.out.println("3 - Total do valor investido");
        System.out.println("4 - Todas as transações de um cliente");
        int tipoOperacao = getInt();

        if (tipoOperacao < 1 || tipoOperacao > 4) System.out.println("Digite uma opção válida!!");
        if (tipoOperacao == 1) menuListarTodasContas();
        if (tipoOperacao == 2) contasSaldoNegativo();
        if (tipoOperacao == 3) totalValorInvestido();
        if (tipoOperacao == 4) todasTransacoesCliente();
    }

    public static void menuListarTodasContas() {
        System.out.println("Qual tipo de conta você deseja?");
        System.out.println("1 - Contas Poupança");
        System.out.println("2 - Contas Corrente");
        System.out.println("3 - Contas Investimento");
        int tipoOperacao = getInt();

        if (tipoOperacao < 1 || tipoOperacao > 3) System.out.println("Digite uma opção válida!!");

        for (Conta conta : Main.contas) {
            if (conta instanceof ContaPoupanca && tipoOperacao == 1) {
                System.out.println(conta);
            } else if (conta instanceof ContaCorrente && tipoOperacao == 2) {
                System.out.println(conta);
            } else if (conta instanceof ContaInvestimento && tipoOperacao == 3) {
                System.out.println(conta);
            }
        }
    }

    public static void contasSaldoNegativo() {
        for (Conta conta : Main.contas) {
            if (conta.getSaldo() < 0) {
                System.out.println(conta);
            }
        }
    }

    public static void totalValorInvestido() {
        double total = 0;
        for (Conta conta : Main.contas) {
            if (conta instanceof ContaInvestimento) {
                total += conta.getSaldo();
            }
        }
        System.out.println("O valor total investido é: " + formatar(total));
    }

    public static void todasTransacoesCliente() {
        String cpf = getCPF("Digite o CPF do cliente:");

        for (Conta conta : Main.contas) {
            if (conta.getCpf().equals(cpf)) {
                System.out.println(conta);
                for (Transacao extrato : conta.extrato()) {
                    System.out.println(extrato);
                }
            }
        }
    }
}
