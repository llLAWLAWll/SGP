package utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Scanner;


/*
 * Classe responsável por metodos que vão utilitarios para o programa
 * */
public class Utils {
    static Scanner sc = new Scanner(System.in);
    static boolean entradaValida = false;



    /*
    * Metodo utilizado para scanner de teclado
    * */
    public String scannertxt() {
        String texto = "";
        texto = sc.nextLine();
        return texto;
    }

    /*
     * Metodo utilizado para scannear o nome
     * */
    public String ScannerNome() {
        String nome = "";

        while (!entradaValida) {
            printTexto("Digite o NOME:");
            nome = scannertxt();
            if (!nome.trim().isEmpty()) {
                entradaValida = true;
            } else {
                printTexto("NOME Inválido. Por favor, digite um nome válido");
            }
        }
        entradaValida = false;
        return nome;

    }

    /*
     * Metodo utilizado para scannear a quantidade e verificar se o valor inserido não é negativo
     * */

    public int scannerQuantidade() {
        int nInteiro = 0;

        while (!entradaValida) {
            printTexto("Digite a QUANTIDADE:");
            if (sc.hasNextInt()) {
                nInteiro = sc.nextInt();
                if (nInteiro < 0) { // Verifica se não é negativo
                    printTexto("Entrada inválida. A QUANTIDADE não pode ser negativa.");
                } else {
                    entradaValida = true;
                }
            } else {
                printTexto("Entrada inválida. Por favor, digite uma QUANTIDADE válida.");
                sc.next(); // Descarta entrada inválida
            }

        }
        entradaValida = false;
        return nInteiro;
    }

    /*
     * Metodo utilizado para scannear o preço inserido e formatar para conter apenas duas casas  decimais
     * */
    public double scannerPreco() throws ParseException {

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.UP);


        double nDouble = 0.00;

        while (!entradaValida) {
            printTexto("Digite o PREÇO: ");
            if (sc.hasNextDouble()) {
                nDouble = sc.nextDouble();
                String nDoubleFormatado = df.format(nDouble);
                nDouble = df.parse(nDoubleFormatado).doubleValue();
                if (nDouble < 0.00) { // Verifica se não é negativo
                    printTexto("Entrada inválida. O PREÇO não pode ser negativo.");
                } else {
                    entradaValida = true;
                }
            } else {
                printTexto("Entrada inválida. Por favor, digite um PREÇO válido.");
                sc.next(); // Descarta entrada inválida
            }

        }
        entradaValida = false;
        return nDouble;
    }


    /*
     * Metodo utilizado para scannear o ID e verificar se o valor inserido não é negativo
     * */
    public int scannerID() {
        int nID = -1;

        while (!entradaValida) {
            printTexto("Digite o ID do produto:");
            if (sc.hasNextInt()) {
                nID = sc.nextInt();
                if (nID <= 0) { // Verifica se não é negativo
                    printTexto("Entrada inválida. o ID não pode ser negativo.");
                } else {
                    entradaValida = true;
                }
            } else {
                printTexto("Entrada inválida. Por favor, digite um ID válido.");
                sc.next(); // Descarta entrada inválida
            }

        }
        entradaValida = false;
        return nID;

    }

    /*
     * Metodo utilizado para escrever o texto na tela
     * */
    public void printTexto(String texto) {
        System.out.println(texto);
    }

}
