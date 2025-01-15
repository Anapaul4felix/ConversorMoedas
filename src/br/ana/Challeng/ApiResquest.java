package br.ana.Challeng;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiResquest {

    public static class ApiRequest {
        private static final String API_URL = "https://v6.exchangerate-api.com/v6/56eaf685f824115b408abf3b/latest/";

        public double obterTaxaDeCambio(String moedaOrigem, String moedaDestino) throws IOException, InterruptedException {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + moedaOrigem))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                JsonObject taxaDeCambioJson = gson.fromJson(response.body(), JsonObject.class);
                JsonObject rates = taxaDeCambioJson.getAsJsonObject("conversion_rates");
                return rates.get(moedaDestino).getAsDouble();
            } else {
                throw new RuntimeException("Falha ao obter taxa de câmbio. Código de status: " + response.statusCode());
            }
        }
    }

}
