
package DAO;

import contract.model.IngressoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import servidor.util.ConexaoDB;

public class IngressoDAO {

    public boolean inserir(IngressoModel ingresso) throws SQLException {
        String sql = "INSERT INTO ingressos (sessao_id, nome_cliente, poltrona) VALUES (?, ?, ?)";
        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ingresso.getSessao_id());
            ps.setString(2, ingresso.getNome_cliente());
            ps.setString(3, ingresso.getPoltrona());

            int n = ps.executeUpdate();
            return n > 0;
        } catch (SQLIntegrityConstraintViolationException dup) {
            // Violação de chave única (poltrona já ocupada) -> retorna false para indicar falha
            return false;
        }
    }

    public boolean excluir(int idIngresso) throws SQLException {
        String sql = "DELETE FROM ingressos WHERE id = ?";
        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idIngresso);
            return ps.executeUpdate() > 0;
        }
    }

    public IngressoModel pesquisarPorId(int idIngresso) throws SQLException {
        IngressoModel retorno = null;
        String sql = "SELECT id, sessao_id, nome_cliente, poltrona FROM ingressos WHERE id = ?";
        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idIngresso);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    retorno = new IngressoModel();
                    retorno.setId(rs.getInt("id"));
                    retorno.setSessao_id(rs.getInt("sessao_id"));
                    retorno.setNome_cliente(rs.getString("nome_cliente"));
                    retorno.setPoltrona(rs.getString("poltrona"));
                }
            }
        }
        return retorno;
    }

    public List<String> buscarPoltronasPorSessaoId(int sessaoId) throws SQLException {
        List<String> poltronas = new ArrayList<>();
        String sql = "SELECT poltrona FROM ingressos WHERE sessao_id = ?";
        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, sessaoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    poltronas.add(rs.getString("poltrona"));
                }
            }
        }
        return poltronas;
    }
}
 