package com.example.demo17.model;

public abstract class VeiculoLocacao {
    private String nomeCliente;
    private String cpfCliente;
    private int qtdMinutos;
    private String situacaoDemanda; //alta, normal, baixa

    public VeiculoLocacao(String nomeCliente, String cpfCliente, int qtdMinutos, String situacaoDemanda) {
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.qtdMinutos = qtdMinutos;
        this.situacaoDemanda = situacaoDemanda;
        if(qtdMinutos <= 0){
            throw new IllegalArgumentException("Quantidade de minutos inválida!");
        }
    }

    public abstract double calcularValorLocacao();
    public abstract double calcularValorLocacao(String cupomDesconto);

    @Override
    public String toString() {
        return  "Nome do cliente: " + nomeCliente +
                "\nCPF: " + cpfCliente +
                "\nTempo de locação: " + qtdMinutos + " minutos" +
                "\nDemanda: " + situacaoDemanda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public int getQtdMinutos() {
        return qtdMinutos;
    }

    public void setQtdMinutos(int qtdMinutos) {
        this.qtdMinutos = qtdMinutos;
    }

    public String getSituacaoDemanda() {
        return situacaoDemanda;
    }

    public void setSituacaoDemanda(String situacaoDemanda) {
        this.situacaoDemanda = situacaoDemanda;
    }
}
