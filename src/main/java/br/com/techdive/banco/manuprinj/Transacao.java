package br.com.techdive.banco.manuprinj;


import java.time.LocalDateTime;


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
        return "Transacao{" + "origem=" + origem + ", destino=" + destino + ", valor=" + valor + ", data=" + data
                + ", tipo=" + tipo + '}';
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
