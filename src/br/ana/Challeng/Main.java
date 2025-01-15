package br.ana.Challeng;

public class Main {
    public static void main(String[] args) {
        ApiResquest.ApiRequest apiRequest = new ApiResquest.ApiRequest();
        ConversorMoeda conversorMoeda = new ConversorMoeda(apiRequest);
        Menu menu = new Menu(conversorMoeda);

        menu.mostrarMenu();
    }
}
