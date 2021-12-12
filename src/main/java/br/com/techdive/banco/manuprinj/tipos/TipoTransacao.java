package br.com.techdive.banco.manuprinj.tipos;


public enum TipoTransacao {
    SAQUE("Saque"),
    TRANSFERENCIA("Transferência"),
    DEPOSITO("Depósito");

    private final String nome;

    TipoTransacao(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
