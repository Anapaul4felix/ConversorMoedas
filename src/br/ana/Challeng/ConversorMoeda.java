package br.ana.Challeng;

public class ConversorMoeda {
    private ApiResquest.ApiRequest apiRequest;

    public ConversorMoeda(ApiResquest.ApiRequest apiRequest) {
        this.apiRequest = apiRequest;
    }

    public double converter(String moedaOrigem, String moedaDestino, double valor) throws Exception {
        double taxaDeCambio = apiRequest.obterTaxaDeCambio(moedaOrigem, moedaDestino);
        return valor * taxaDeCambio;
    }
}
