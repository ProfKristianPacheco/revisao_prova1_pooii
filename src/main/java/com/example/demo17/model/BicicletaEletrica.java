package com.example.demo17.model;

public class BicicletaEletrica extends VeiculoLocacao{
    private final double TAXA_INICIAL = 5;
    private final double VALOR_MINUTO = 0.35;
    private int porcentagemBateria;

    public BicicletaEletrica(String nomeCliente, String cpfCliente, int qtdMinutos, String situacaoDemanda) {
        super(nomeCliente, cpfCliente, qtdMinutos, situacaoDemanda);
    }

    public BicicletaEletrica(String nomeCliente, String cpfCliente, int qtdMinutos, String situacaoDemanda, int porcentagemBateria) {
        super(nomeCliente, cpfCliente, qtdMinutos, situacaoDemanda);
        this.porcentagemBateria = porcentagemBateria;

        if(porcentagemBateria < 20){
            throw new IllegalArgumentException("Percentual de bateria mínimo de 20% para Bicicleta Elétrica!");
        }
        if(qtdMinutos > 120){
            throw new IllegalArgumentException("Locação máxima de 120 minutos para Bicicleta Elétrica!");
        }

    }

    @Override
    public double calcularValorLocacao() {
        if(getSituacaoDemanda().equalsIgnoreCase("Alta")){
            return (TAXA_INICIAL + (getQtdMinutos() * VALOR_MINUTO)) * 1.2;
        }
        if(getSituacaoDemanda().equalsIgnoreCase("Baixa")){
            return (TAXA_INICIAL + (getQtdMinutos() * VALOR_MINUTO)) * 0.85;
        }
        return TAXA_INICIAL + (getQtdMinutos() * VALOR_MINUTO);
    }

    public double calcularValorLocacao(String cupomDesconto){
        if(cupomDesconto.equalsIgnoreCase("MOBI10")){
            return  calcularValorLocacao() * 0.9;
        }
        if(cupomDesconto.equalsIgnoreCase("URBANO20")){
            return calcularValorLocacao() * 0.8;
        }
        throw new IllegalArgumentException("CUPOM INVÁLIDO!");
    }

    public int getPorcentagemBateria() {
        return porcentagemBateria;
    }

    public void setPorcentagemBateria(int porcentagemBateria) {
        this.porcentagemBateria = porcentagemBateria;
    }

    @Override
    public String toString() {
        return super.toString() + "\nTaxa Inicial: " + TAXA_INICIAL + "\nValor por minuto: " + VALOR_MINUTO + "\nPorcentagem de Bateria: " + porcentagemBateria;
    }
}
