package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
* Classe responsável por fazer a conexão com o banco de dados
* */
public class Conexao {


    /*
    * Dados do banco de dados
    * devem ser setados aqui manualmente para fazer conexão com o banco de dados MySQL
    * Driver de conexao e script do banco se encontram na pasta \SGP\SGP\mysql
    * */
    private static final String URL = "jdbc:mysql://localhost:3306/sgpbd";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";
    private static Connection CONN;

    public static Connection getConexao() {
        try {
            if (CONN == null) {
                //cria uma conexao
                CONN = DriverManager.getConnection(URL, USUARIO, SENHA);
                return CONN;

            } else {
                return CONN;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
