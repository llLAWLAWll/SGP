package dao;

import conexao.Conexao;
import model.Produto;
import utils.Utils;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *Classe responsável para acesso ao banco de dados
 * */
public class ProdutoDao {

    Utils metodo = new Utils();
    private PreparedStatement ps = null;
    private ResultSet resultado = null;
    private String sql = null;

/*
* Metodo responsavel por fazer a inserção no banco de dados.
* Recebe como parâmetro um objeto contendo os dados do novo INSERT.
* */
    public void createProduto(Produto produto) throws RuntimeException {

        sql = "INSERT INTO produtos(nome, preco, quantidade) VALUES (?, ?, ?)";

        try {

            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQuantidade());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /*
     * Metodo responsável por fazer a seleção no banco de dados.
     * Recebe como parâmetro o filtro contendo o valor da busca .
     * */
    public void selectProdutos(String filtro) {

        Produto produto = new Produto();


        try {

            if (filtro.equals("*")) {
                String sql = "SELECT * FROM produtos";

                ps = Conexao.getConexao().prepareStatement(sql);

                resultado = ps.executeQuery();

                impressaoSelect(produto);

            } else {

                String sql = "SELECT * FROM produtos WHERE nome = ?";

                ps = Conexao.getConexao().prepareStatement(sql);

                ps.setString(1, filtro);

                resultado = ps.executeQuery();

                impressaoSelect(produto);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /*
     * Metodo responsável por fazer a Atualização de preço no banco de dados.
     * Recebe como parâmetro um produto contendo ID e o PREÇO a ser atualizado.
     * */
    public void updateProduto(Produto produto) {

        String sql = "SELECT id FROM produtos WHERE id = ?";

        try {

            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setInt(1, produto.getId());

            resultado = ps.executeQuery();

            if (resultado.next()) {
                String sql2 = "UPDATE produtos SET preco = ? WHERE id = ?";

                ps = Conexao.getConexao().prepareStatement(sql2);


                ps.setDouble(1, produto.getPreco());
                ps.setInt(2, produto.getId());

                ps.execute();

                metodo.printTexto("Preço ATUALIZADO com sucesso");

            } else {
                metodo.printTexto("ID não encontrado");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    /*
     * Metodo responsável por deletar um dado no banco de dados.
     * Recebe como parâmetro um objeto contendo ID do produto a ser deletado.
     * */
    public void deleteProduto(Produto produto) throws RuntimeException {

        String sql = "SELECT id FROM produtos WHERE id = ?";

        try {

            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setInt(1, produto.getId());

            resultado = ps.executeQuery();

            if (resultado.next()) {
                String sql2 = "DELETE FROM produtos WHERE id = ?";

                ps = Conexao.getConexao().prepareStatement(sql2);

                ps.setInt(1, produto.getId());

                ps.execute();

                metodo.printTexto("Produto DELETADO com sucesso");

            } else {
                metodo.printTexto("ID não encontrado");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    /*
     * Metodo responsável por fazer impressão dos dados no metodo selectProdutos
     * Recebe como parametro o produto que foi selecionado
     * */
    private void impressaoSelect(Produto produto) throws SQLException {
        if (resultado.next()) {
            do {

                produto.setId(resultado.getInt("id"));
                produto.setNome(resultado.getString("nome"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));

                System.out.println("| " + produto.getId() + " | " + produto.getNome() + " | " + produto.getPreco() + " | " + produto.getQuantidade());

            } while (resultado.next());
        } else {
            metodo.printTexto("Nenhum produto encontrado");
        }
    }
}

