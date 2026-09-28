package com.example.demo17.model;

public class PatineteEletrico extends VeiculoLocacao{
    private final double TAXA_INICIAL = 4;
    private final double VALOR_MINUTO = 0.3;

    private int porcentagemBateria;

    public int getPorcentagemBateria() {
        return porcentagemBateria;
    }

    public void setPorcentagemBateria(int porcentagemBateria) {
        this.porcentagemBateria = porcentagemBateria;
    }

    public PatineteEletrico(String nomeCliente, String cpfCliente, int qtdMinutos, String situacaoDemanda, int porcentagemBateria) {
        super(nomeCliente, cpfCliente, qtdMinutos, situacaoDemanda);
        this.porcentagemBateria = porcentagemBateria;
        if(porcentagemBateria < 15){
            throw new IllegalArgumentException("Percentual de bateria mínimo de 15% para patinete elétrico!");
        }
        if(qtdMinutos > 90){
            throw new IllegalArgumentException("Locação máxima de 90 minutos para Patinete Elétrico!");
        }
    }

    @Override
    public double calcularValorLocacao() {
        if(getSituacaoDemanda().equalsIgnoreCase("Alta")){
            return (TAXA_INICIAL + (getQtdMinutos() * VALOR_MINUTO) ) * 1.2;
        }
        if(getSituacaoDemanda().equalsIgnoreCase("Baixa")){
            return (TAXA_INICIAL + (getQtdMinutos() * VALOR_MINUTO)) * 0.85;
        }
        return TAXA_INICIAL + (getQtdMinutos() * VALOR_MINUTO);
    }

    public double calcularValorLocacao(String cupomDesconto){
        if(cupomDesconto.equalsIgnoreCase("MOBI10")){
            return calcularValorLocacao() * 0.9;
        }
        if(cupomDesconto.equalsIgnoreCase("URBANO20")){
            return calcularValorLocacao() * 0.8;
        }
        throw new IllegalArgumentException("CUPOM INVÁLIDO!");
    }

    @Override
    public String toString() {
        return super.toString() + "\nTaxa inicial: " + TAXA_INICIAL + "\n Preço por minuto: " + VALOR_MINUTO + "\n" + "Percentual de Bateria: " + porcentagemBateria;
    }
}
