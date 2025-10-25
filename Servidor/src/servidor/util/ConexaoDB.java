package servidor.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitária para gerenciar a conexão com o banco de dados.
 * * Principais correções:
 * 1. Renomeada para 'ConexaoDB' para evitar conflito com 'java.sql.Connection'.
 * 2. URL do JDBC corrigida (porta 3306 adicionada).
 * 3. Métodos 'conectar/desconectar' removidos em favor de um método estático 'getConnection()'.
 * Isso permite o uso de 'try-with-resources' nos DAOs, que é mais seguro.
 * 4. Tratamento de erro melhorado (lança exceção em vez de imprimir no console).
 */
public class ConexaoDB {

    // 1. Defina suas credenciais como constantes
    private static final String URL = "jdbc:mysql://localhost:3306/cinema_db";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    /* * Bloco estático para carregar o driver do MySQL uma vez
     * quando a classe é carregada pela primeira vez.
     */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ERRO: Driver JDBC do MySQL não encontrado!");
            // Lança uma exceção grave, pois a aplicação não pode funcionar sem o driver.
            throw new RuntimeException("Driver JDBC não encontrado", e);
        }
    }

    /**
     * Obtém uma nova conexão com o banco de dados.
     * O método que chama (o DAO) é responsável por fechar esta conexão.
     * * @return um objeto java.sql.Connection
     * @throws SQLException se a conexão falhar
     */
    public static Connection getConnection() throws SQLException {
        // 2. Retorna uma nova conexão diretamente.
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}