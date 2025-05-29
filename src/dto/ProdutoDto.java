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


    /**
     * Método responsável por inserir um novo produto no sistema.
     * A inserção ocorre a partir de interações via console, nas quais o usuário fornece
     * os dados do produto, incluindo nome, preço e quantidade.
     *
     * Fluxo do método:
     * - Solicita ao usuário que insira o nome do produto, utilizando o método ScannerNome()
     *   da classe Utils.
     * - Solicita ao usuário que insira o preço do produto, utilizando o método scannerPreco()
     *   da classe Utils. Valores inválidos, como números negativos ou formato incorreto, são tratados.
     * - Solicita ao usuário que insira a quantidade do produto, utilizando o método scannerQuantidade()
     *   da classe Utils. Valores inválidos, como números negativos ou entradas não numéricas, são tratados.
     * - Após validar os dados inseridos, cria uma nova instância de Produto com essas informações.
     * - Envia o objeto Produto para o método createProduto() da classe ProdutoDao,
     *   que realiza a inserção no banco de dados.
     *
     * Exceções:
     * - Pode lançar ParseException em caso de falhas ao interpretar o valor inserido como preço.
     *
     * Este método utiliza as classes auxiliares Utils e ProdutoDao para interagir com
     * o console e o banco de dados, respectivamente.
     *
     * @throws ParseException se ocorrer falha ao interpretar o dado de preço fornecido pelo usuário.
     */
    public static void inserirProduto() throws ParseException {

        Produto produto = new Produto();

        produto.setNome(util.ScannerNome());
        produto.setPreco(util.scannerPreco());
        produto.setQuantidade(util.scannerQuantidade());


        produtoDao.createProduto(produto);
    }

    /**
     * Método responsável por listar produtos a partir de interações via console.
     * Oferece opções ao usuário para buscar produtos pelo nome, listar todos os produtos
     * ou sair do menu.
     *
     * Fluxo do método:
     * - O usuário seleciona uma das opções disponíveis: "Buscar", "Todos" ou "Sair".
     * - Caso a opção seja "Buscar", o usuário insere o nome do produto que deseja buscar,
     *   e o método utiliza a classe ProdutoDao para realizar a busca no banco de dados.
     * - Caso a opção seja "Todos", o método lista todos os produtos do banco de dados.
     * - Caso a opção seja "Sair", o fluxo é finalizado.
     * - Entradas inválidas resultam em uma mensagem de erro, e o menu é exibido novamente.
     */
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

    /**
     * Método responsável por atualizar o preço de um produto existente no sistema.
     * A atualização ocorre a partir de interações via console, onde o usuário deve fornecer
     * o ID do produto e o novo preço. O método valida os dados inseridos antes de prosseguir
     * com a atualização no banco de dados.
     *
     * Fluxo do método:
     * - Solicita ao usuário o ID do produto utilizando o método scannerID() da classe Utils.
     *   Somente IDs válidos (não negativos) são aceitos.
     * - Solicita ao usuário o novo preço do produto utilizando o método scannerPreco() da classe Utils.
     *   Somente preços válidos (não negativos e formatados corretamente) são aceitos.
     * - Cria uma instância da classe Produto com os dados fornecidos pelo usuário.
     * - Envia a instância para o método updateProduto() da classe ProdutoDao, que realiza a atualização
     *   do preço no banco de dados. Caso o ID informado não exista no banco de dados, é exibida uma mensagem
     *   informando que o ID não foi encontrado.
     *
     * Exceções:
     * - Lança ParseException em caso de falhas ao interpretar o valor inserido como preço.
     *
     * Este método utiliza as classes auxiliares Utils e ProdutoDao para manipular as entradas do console
     * e realizar as operações no banco de dados.
     *
     * @throws ParseException se ocorrer falha ao interpretar o dado de preço fornecido pelo usuário.
     */
    public static void atualizarProduto() throws ParseException {

        Produto produto = new Produto();

        produto.setId(util.scannerID());
        produto.setPreco(util.scannerPreco());

        produtoDao.updateProduto(produto);

    }

    /**
     * Método responsável por deletar um produto existente no sistema.
     * A deleção ocorre a partir de interações via console, onde o usuário fornece
     * o ID do produto que deseja deletar. O método valida se o ID informado é válido
     * e executa a remoção do produto no banco de dados.
     *
     * Fluxo do método:
     * - Solicita ao usuário o ID do produto utilizando o método scannerID() da classe Utils.
     *   Somente IDs válidos (não negativos) são aceitos.
     * - Cria uma instância da classe Produto e define o ID fornecido.
     * - Envia a instância para o método deleteProduto() da classe ProdutoDao, que executa a remoção
     *   no banco de dados.
     * - Caso o ID informado não exista no banco de dados, exibe uma mensagem indicando que
     *   o ID não foi encontrado.
     *
     * Exceções:
     * - Em caso de problemas ao acessar o banco de dados, pode lançar uma RuntimeException.
     *
     * Este método utiliza as classes auxiliares Utils e ProdutoDao para obter os dados do console
     * e realizar as operações necessárias no banco de dados.
     */
    public static void deletarProduto() {

        Produto produto = new Produto();

        produto.setId(util.scannerID());

        produtoDao.deleteProduto(produto);

    }

}
