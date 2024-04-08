import dto.ProdutoDto;
import utils.Utils;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {

        /*
         * MENU PRINCIPAL VIA CONSOLE
         * */
        Utils util = new Utils();
        String menu = "";

// Criação do Menu
        util.printTexto(" ----- Gerenciamento de Produtos -----");
        while (!menu.equals("sair")) {
            util.printTexto("MENU" +
                    "\nGerenciamento de Produtos");
            util.printTexto("- Inserir" +
                    "\n- Listar" +
                    "\n- Atualizar" +
                    "\n- Deletar" +
                    "\n- Sair" +
                    "\nDigite uma opção:");
            menu = util.scannertxt().toLowerCase();


            switch (menu.trim().toLowerCase()) {
                case "inserir":
                    util.printTexto("INSERIR PRODUTO");
                    ProdutoDto.inserirProduto();
                    break;
                case "listar":
                    util.printTexto("LISTA DE PRODUTOS");
                    ProdutoDto.listarProdutos();
                    break;
                case "atualizar":
                    util.printTexto("ATUALIZAR PRODUTO");
                    ProdutoDto.atualizarProduto();
                    break;
                case "deletar":
                    util.printTexto("DELETAR PRODUTO");
                    ProdutoDto.deletarProduto();
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
}