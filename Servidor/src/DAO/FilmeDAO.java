package DAO;

import contract.model.FilmeModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.util.ConexaoDB; // 1. Usando a classe de conexão corrigida

// 2. DAO é uma classe simples. NÃO usa 'extends UnicastRemoteObject'
//    e NÃO usa 'throws RemoteException'.
public class FilmeDAO {

    private static final Logger LOGGER = Logger.getLogger(FilmeDAO.class.getName());

    /**
     * Este é o método que o CinemaRMIImpl vai chamar para o cliente.
     * Estava faltando no seu código.
     */
    public List<FilmeModel> buscarTodos() {
        List<FilmeModel> filmes = new ArrayList<>();
        // 3. SQL corrigido (tabela 'filmes')
        String sql = "SELECT * FROM filmes";

        // 4. Usando 'try-with-resources' para garantir que a conexão feche
        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FilmeModel filme = new FilmeModel();
                // 5. Assumindo que seu model usa os nomes corretos (contract.model)
                filme.setId(rs.getInt("id"));
                filme.setTitulo(rs.getString("titulo"));
                filme.setGenero(rs.getString("genero"));
                filme.setDuracaoMinutos(rs.getInt("duracao_minutos"));
                filme.setClassificacaoIndicativa(rs.getString("classificacao_indicativa"));
                filmes.add(filme);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar filmes: " + e.getMessage(), e);
        }
        return filmes;
    }

    public boolean inserir(FilmeModel filme) {
        // SQL corrigido: tabela 'filmes', não 'filme'
        String sql = "INSERT INTO filmes(titulo, genero, duracao_minutos, classificacao_indicativa) VALUES (?, ?, ?, ?)";
        
        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getGenero());
            stmt.setInt(3, filme.getDuracaoMinutos());
            stmt.setString(4, filme.getClassificacaoIndicativa());

            // 6. 'executeUpdate()' é melhor para INSERT/UPDATE/DELETE
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se 1 linha foi inserida

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir filme: " + e.getMessage(), e);
            return false;
        }
    }

    public boolean editar(FilmeModel filme) {
        // 7. SQL com MUITAS CORREÇÕES:
        //    - 'filmes' (tabela)
        //    - 'genero' (sem '0')
        //    - 'classificacao_indicativa' (sem '0')
        //    - ADICIONADO "WHERE id = ?" (CRÍTICO! Senão você atualiza o banco inteiro)
        String sql = "UPDATE filmes SET titulo = ?, genero = ?, duracao_minutos = ?, classificacao_indicativa = ? WHERE id = ?";
        
        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getGenero());
            stmt.setInt(3, filme.getDuracao());
            stmt.setString(4, filme.getClassificacaoIndicativa());
            stmt.setInt(5, filme.getId()); // Passando o ID para o "WHERE"

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se 1 linha foi atualizada

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao editar filme: " + e.getMessage(), e);
            return false;
        }
    }

    public boolean excluir(int id) {
        // SQL corrigido: tabela 'filmes' e coluna 'id'
        String sql = "DELETE FROM filmes WHERE id = ?";
        
        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao excluir filme: " + e.getMessage(), e);
            return false;
        }
    }

    public FilmeModel pesquisarPorId(int id) {
        FilmeModel filme = null;
        // SQL corrigido: tabela 'filmes'
        String sql = "SELECT * FROM filmes WHERE id = ?";

        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    filme = new FilmeModel();
                    filme.setId(rs.getInt("id"));
                    filme.setTitulo(rs.getString("titulo"));
                    filme.setGenero(rs.getString("genero"));
                    filme.setDuracaoMinutos(rs.getInt("duracao_minutos"));
                    filme.setClassificacaoIndicativa(rs.getString("classificacao_indicativa"));
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao pesquisar filme: " + e.getMessage(), e);
        }
        return filme;
    }
}