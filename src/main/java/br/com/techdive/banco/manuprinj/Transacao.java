package br.com.techdive.banco.manuprinj;


import static br.com.techdive.banco.manuprinj.util.FormatacaoFinanceira.formatar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;


public class Transacao {

    private Conta origem;
    private Conta destino;
    private double valor;
    private LocalDateTime data;
    private TipoTransacao tipo;

    public Transacao(Conta origem, Conta destino, double valor, TipoTransacao tipo) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.data = LocalDateTime.now();
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" | ")
                .add(data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .add("'Origem' Agência: " + origem.getAgenciaConta() + " Conta: " + origem.getNumeroConta());
        if (destino != null) {
            joiner.add("'Destino' Agência: " + destino.getAgenciaConta() + " Conta: " + destino.getNumeroConta());
        }
        return joiner.add("Valor: " + formatar(valor))
                .add("Transação: " + tipo).toString();
    }

    public Conta getOrigem() {
        return origem;
    }

    public void setOrigem(Conta origem) {
        this.origem = origem;
    }

    public Conta getDestino() {
        return destino;
    }

    public void setDestino(Conta destino) {
        this.destino = destino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }
}
