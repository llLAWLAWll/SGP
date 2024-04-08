package dto;

import dao.ProdutoDao;
import model.Produto;
import utils.Utils;

import java.text.ParseException;


/*
 * Classe responsável por pegar os valores recebidos do teclado e envia-los para a classe de acesso ao banco
 * */
public class ProdutoDto {

    private static Utils util = new Utils();

    private static ProdutoDao produtoDao = new ProdutoDao();


    public static void inserirProduto() throws ParseException {

        Produto produto = new Produto();

        produto.setNome(util.ScannerNome());
        produto.setPreco(util.scannerPreco());
        produto.setQuantidade(util.scannerQuantidade());


        produtoDao.createProduto(produto);
    }

    public static void listarProdutos() {

        Produto produto = new Produto();

        String opcaoValida = "";
        String busca;

        while (!opcaoValida.equals("sair")) {


            util.printTexto("Selecione uma opção:");
            util.printTexto("Buscar (por nome)");
            util.printTexto("Todos");
            util.printTexto("Sair");
            util.printTexto("Digite uma opção:");

            opcaoValida = util.scannertxt();

            switch (opcaoValida.trim().toLowerCase()) {
                case "buscar":
                    util.printTexto("Insira o Nome que deseja buscar:");
                    busca = util.scannertxt();
                    produtoDao.selectProdutos(busca);
                    break;
                case "todos":
                    busca = "*";
                    produtoDao.selectProdutos(busca);
                    break;
                case "sair":
                    util.printTexto("Saindo...");
                    break;
                default:
                    util.printTexto("Entrada inválida. Por favor, digite uma ENTRADA válida.");
                    break;

            }

        }

    }

    public static void atualizarProduto() throws ParseException {

        Produto produto = new Produto();

        produto.setId(util.scannerID());
        produto.setPreco(util.scannerPreco());

        produtoDao.updateProduto(produto);

    }

    public static void deletarProduto() {

        Produto produto = new Produto();

        produto.setId(util.scannerID());

        produtoDao.deleteProduto(produto);

    }

}
