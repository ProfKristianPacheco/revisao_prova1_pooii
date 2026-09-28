package com.example.demo17.model;

public class BicicletaConvencional extends VeiculoLocacao{
    private final double TAXA_INICIAL = 3; //constante
    private final double VALOR_MINUTO = 0.2; //valor constante

    public BicicletaConvencional(String nomeCliente, String cpfCliente, int qtdMinutos, String situacaoDemanda) {
        super(nomeCliente, cpfCliente, qtdMinutos, situacaoDemanda);
        if(qtdMinutos > 180){
            throw new IllegalArgumentException("Locação máxima de 180 minutos para Bicicleta Convencional!");
        }
    }

    @Override
    public double calcularValorLocacao() { //sobrecarga
        if(getSituacaoDemanda().equalsIgnoreCase("Alta")) {
            return (TAXA_INICIAL + (VALOR_MINUTO * getQtdMinutos() )) * 1.2;
        }
        if(getSituacaoDemanda().equalsIgnoreCase("Baixa")){
            return (TAXA_INICIAL + (VALOR_MINUTO * getQtdMinutos())) * 0.85;
        }
        return TAXA_INICIAL + (VALOR_MINUTO * getQtdMinutos());
    }

    public double calcularValorLocacao(String cupomDesconto){ //sobrecarga
        if(cupomDesconto.equalsIgnoreCase("MOBI10")) {
            return calcularValorLocacao() * 0.9;
        }
        if(cupomDesconto.equalsIgnoreCase("URBANO20")){
            return calcularValorLocacao() * 0.8;
        }
        throw new IllegalArgumentException("Cupom inválido!");
    }


    @Override
    public String toString() {
        return super.toString() + "\nTaxa Inicial: " + TAXA_INICIAL + "\nValor por minuto: " + VALOR_MINUTO;
    }
}
