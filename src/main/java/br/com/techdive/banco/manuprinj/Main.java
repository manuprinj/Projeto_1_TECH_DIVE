package br.com.techdive.banco.manuprinj;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
        int tipoOperacao = option.nextInt();
        option.nextLine();

        if (tipoOperacao < 1 || tipoOperacao > 7) System.out.println("Digite uma opção válida!!");
        if (tipoOperacao == 1) criarConta();
        if (tipoOperacao == 2) saque();
        if (tipoOperacao == 3) deposito();
        if (tipoOperacao == 4) visualizarSaldo();
        if (tipoOperacao == 5) extrato();
        if (tipoOperacao == 6) transferencia();
        if (tipoOperacao == 7) alteracaoDados();
        if (tipoOperacao == 8) ;
    }

    public static void criarConta() {
        System.out.println("Digite que tipo de conta você deseja criar: ");
        System.out.println("1 - Conta Poupança");
        System.out.println("2 - Conta Corrente");
        System.out.println("3 - Conta Investimento");
        int tipoConta = option.nextInt();
        option.nextLine();

        System.out.println("Digite seu nome: ");
        String nome = option.nextLine();

        System.out.println("Digite seu CPF: ");
        String cpf = option.nextLine();

        System.out.println("Digite sua renda mensal: ");
        double rendaMensal = option.nextDouble();
        option.nextLine();

        System.out.println("Selecione uma das agências abaixo: ");
        System.out.println("1 -> 001 - Florianópolis");
        System.out.println("2 -> 002 - São José");
        int agenciaConta = option.nextInt();
        option.nextLine();

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
            ContaInvestimento conta = new ContaInvestimento(nome, cpf, numeroConta, agenciaConta, 0, rendaMensal);
            numeroConta++;
            contas.add(conta);
        }
    }

    public static Conta validacaoContaAgencia() {
        System.out.println("Digite o número da agência (1 -> 001 - Florianópolis ou 2 ->  002 - São José: ");
        int numAgencia = option.nextInt();
        option.nextLine();
        System.out.println("Digite o número da conta ");
        int numConta = option.nextInt();
        option.nextLine();

        for (Conta conta : contas) {
            if(conta.getNumeroConta() == numConta && conta.getAgenciaConta() == numAgencia) return conta;
        }

        System.out.println("Conta não encontrada!");
        return null;
    }

    public static void saque() {
        Conta conta = validacaoContaAgencia();
        if (conta != null) conta.saque();
    }

    public static void deposito() {
        Conta conta = validacaoContaAgencia();
        if (conta != null) conta.deposito();
    }

    public static void visualizarSaldo() {
        Conta conta = validacaoContaAgencia();
        if (conta != null) System.out.println("Se saldo é de: RS " + conta.saldo());
    }

    public static void transferencia() {
        System.out.println("Origem: ");
        Conta contaOrigem = validacaoContaAgencia();
        if (contaOrigem == null) return;

        System.out.println("Destino: ");
        Conta contaDestino = validacaoContaAgencia();
        if (contaDestino == null) return;

        System.out.println("Digite o valor que você deseja transferir: ");
        double valorTranferencia = option.nextDouble();
        option.nextLine();

        contaOrigem.tranferir(contaDestino, valorTranferencia);
    }

    public static void extrato() {
        Conta conta = validacaoContaAgencia();
        for (Transacao extrato : conta.extrato()) {
            System.out.println(extrato);
        }
    }

    public static void alteracaoDados() {
        Conta conta = validacaoContaAgencia();
        if (conta != null) conta.alterarDadosCadastrais();
    }

    public static void main(String[] args) {
        while (true){
            menu();
        }
    }
}
