package br.ana.Challeng;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private ConversorMoeda conversorMoeda;
    private Scanner scanner;

    public Menu(ConversorMoeda conversorMoeda) {
        this.conversorMoeda = conversorMoeda;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean sair = false;

        while (!sair) {
            System.out.println("Selecione uma opção de conversão:");
            System.out.println("1. USD para ARS (Peso argentino)");
            System.out.println("2. ARS para USD");
            System.out.println("3. USD para BRL (Real brasileiro)");
            System.out.println("4. BRL para USD");
            System.out.println("5. JPY para BRL (Real brasileiro)");
            System.out.println("6. BRL para JPY");
            System.out.println("7. Sair");

            int opcao = -1;

            // Loop até que uma opção válida seja fornecida
            while (true) {
                try {
                    opcao = scanner.nextInt();
                    if (opcao < 1 || opcao > 7) {
                        throw new InputMismatchException(); // Força a exceção se a opção estiver fora do intervalo
                    }
                    break; // Sai do loop se a opção for válida
                } catch (InputMismatchException e) {
                    System.out.println("Opção inválida. Por favor, insira um número entre 1 e 7.");
                    scanner.next(); // Limpa o buffer do scanner
                }
            }

            switch (opcao) {
                case 1:
                    realizarConversao("USD", "ARS");
                    break;
                case 2:
                    realizarConversao("ARS", "USD");
                    break;
                case 3:
                    realizarConversao("USD", "BRL");
                    break;
                case 4:
                    realizarConversao("BRL", "USD");
                    break;
                case 5:
                    realizarConversao("JPY", "BRL");
                    break;
                case 6:
                    realizarConversao("BRL", "JPY");
                    break;
                case 7:
                    sair = true;
                    System.out.println("Sistema finalizado com sucesso até breve !!");
                    break;
            }
        }

        scanner.close();
    }

    private void realizarConversao(String moedaOrigem, String moedaDestino) {
        double valor = -1;

        // Loop até que um valor válido seja fornecido
        while (true) {
            System.out.println("Insira o valor em " + moedaOrigem + " a ser convertido para " + moedaDestino + ":");
            try {
                valor = scanner.nextDouble();
                if (valor < 0) { // Verifica se o valor é negativo
                    throw new InputMismatchException();
                }
                break; // Sai do loop se o valor for válido
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Por favor, insira um número válido.");
                scanner.next(); // Limpa o buffer do scanner
            }
        }

        try {
            double valorConvertido = conversorMoeda.converter(moedaOrigem, moedaDestino, valor);
            System.out.println("O valor convertido é: " + valorConvertido + " " + moedaDestino);
        } catch (Exception e) {
            System.out.println("Erro ao obter taxa de câmbio: " + e.getMessage());
        }
    }
}
