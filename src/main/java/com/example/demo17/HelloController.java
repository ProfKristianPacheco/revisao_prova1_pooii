package com.example.demo17;

import com.example.demo17.model.BicicletaConvencional;
import com.example.demo17.model.BicicletaEletrica;
import com.example.demo17.model.PatineteEletrico;
import com.example.demo17.model.VeiculoLocacao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private RadioButton idBicicletaEletrica;
    @FXML
    private RadioButton idPatineteEletrico;
    @FXML
    private RadioButton idBicicletaConvencional;

    @FXML
    private ToggleGroup groupSituacaoDemanda;

    @FXML
    private ToggleGroup groupTipoVeiculo;

    @FXML
    private Button idCalcularLocacao;

    @FXML
    private TextField idCpfCliente;

    @FXML
    private TextField idCupomDesconto;

    @FXML
    private TextField idNomeCliente;

    @FXML
    private TextField idPercentualBateria;

    @FXML
    private TextArea idResultado;

    @FXML
    private TextField idTempoLocacao;

    public void initialize(){
        idPercentualBateria.setVisible(false);
        idPercentualBateria.setManaged(false);

        idCalcularLocacao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calcularLocacaoVeiculo();
            }
        });

        idBicicletaEletrica.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                idPercentualBateria.setVisible(true);
                idPercentualBateria.setManaged(true);
            }
        });

        idPatineteEletrico.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                idPercentualBateria.setManaged(true);
                idPercentualBateria.setVisible(true);
            }
        });

        idBicicletaConvencional.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                idPercentualBateria.setVisible(false);
                idPercentualBateria.setManaged(false);
            }
        });

    }

    private void calcularLocacaoVeiculo() {
        String nomeCliente;
        String cpfCliente;
        RadioButton tipoVeiculo;
        int tempoLocacao;
        RadioButton situacaoDemanda;
        String cupomDesconto;
        int percentualBateria = 0;

        if(idNomeCliente.getText().isBlank() || idCpfCliente.getText().isBlank() || groupTipoVeiculo.getSelectedToggle() == null || idTempoLocacao.getText().isBlank() || groupSituacaoDemanda.getSelectedToggle() == null ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos obrigatórios");
            alert.setHeaderText("Campos obrigatórios não preenchidos");
            alert.setContentText("Por favor, preencha todos os campos!");
            alert.showAndWait();
            return;
        }
        try{
            tempoLocacao = Integer.parseInt(idTempoLocacao.getText());
        }catch (NumberFormatException excecao){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de dados");
            alert.setHeaderText("Dados inválidos!");
            alert.setContentText("Tempo de locação não é um número!");
            alert.showAndWait();
            return;
        }
        nomeCliente = idNomeCliente.getText();
        cpfCliente = idCpfCliente.getText();
        tipoVeiculo = (RadioButton) groupTipoVeiculo.getSelectedToggle();
        situacaoDemanda = (RadioButton) groupSituacaoDemanda.getSelectedToggle();
        cupomDesconto = idCupomDesconto.getText();

        if(tipoVeiculo.getText().equalsIgnoreCase("Patinete Elétrico") || tipoVeiculo.getText().equalsIgnoreCase("Bicicleta Elétrica")){
            try{
                percentualBateria = Integer.parseInt(idPercentualBateria.getText());
            }catch (NumberFormatException excecao){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro de dadados");
                alert.setHeaderText("Dados inválidos!");
                alert.setContentText("Percentual de bateria não é um número!");
                alert.showAndWait();
                return;
            }
        }
        String tipoVeiculoInformado = tipoVeiculo.getText();
        String situacaoDemandaInformada = situacaoDemanda.getText();
        VeiculoLocacao veiculo;
        double valorLocacao;
        switch (tipoVeiculoInformado){
            case "Bicicleta Convencional":
                try {
                    veiculo = new BicicletaConvencional(nomeCliente, cpfCliente, tempoLocacao, situacaoDemandaInformada);
                }catch (IllegalArgumentException exception){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Parâmetros inválidos!");
                    alert.setHeaderText("Informações inválidas para locação!");
                    alert.setContentText(exception.getMessage());
                    alert.showAndWait();
                    return;
                }
                break;
            case "Bicicleta Elétrica":
                try {
                    veiculo = new BicicletaEletrica(nomeCliente, cpfCliente, tempoLocacao, situacaoDemandaInformada, percentualBateria);
                }catch (IllegalArgumentException exception){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Parâmetros inválidos!");
                    alert.setHeaderText("Informações inválidas para locação!");
                    alert.setContentText(exception.getMessage());
                    alert.showAndWait();
                    return;
                }
                break;
            case "Patinete Elétrico":
                try{
                    veiculo = new PatineteEletrico(nomeCliente, cpfCliente, tempoLocacao, situacaoDemandaInformada, percentualBateria);
                }catch (IllegalArgumentException exception){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Parâmetros inválidos!");
                    alert.setHeaderText("Informações inválidas para locação!");
                    alert.setContentText(exception.getMessage());
                    alert.showAndWait();
                    return;
                }
                break;
            default:
                return;
        }
        if(cupomDesconto.isEmpty()){
            valorLocacao = veiculo.calcularValorLocacao();
        }else{
            try {
                valorLocacao = veiculo.calcularValorLocacao(cupomDesconto);
            }catch (IllegalArgumentException exception){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(exception.getMessage());
                alert.setHeaderText(exception.getMessage());
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
                return;
            }
        }
        idResultado.setText(veiculo + "\nValor locação: " + valorLocacao);

    }

}
