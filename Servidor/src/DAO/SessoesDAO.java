
package DAO;

import contract.model.SessaoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import servidor.util.ConexaoDB;

public class SessoesDAO {

    public List<SessaoModel> buscarPorFilmeId(int filmeId) throws SQLException {
        List<SessaoModel> lista = new ArrayList<>();
        String sql = "SELECT s.id, s.filme_id, s.sala_id, s.data_hora, s.preco_ingresso, sa.numero_sala "
                + "FROM sessoes s JOIN salas sa ON s.sala_id = sa.id WHERE s.filme_id = ?";

        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, filmeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SessaoModel sm = new SessaoModel();
                    sm.setId(rs.getInt("id"));
                    sm.setFilmeId(rs.getInt("filme_id"));
                    sm.setSalaId(rs.getInt("sala_id"));
                    Timestamp ts = rs.getTimestamp("data_hora");
                    if (ts != null) {
                        sm.setDataHora(ts.toLocalDateTime());
                    }
                    sm.setPrecoIngresso(rs.getDouble("preco_ingresso"));
                    sm.setNumeroSala(rs.getInt("numero_sala"));
                    lista.add(sm);
                }
            }
        }

        return lista;
    }

    public SessaoModel pesquisarPorId(int id) throws SQLException {
        SessaoModel retorno = null;
        String sql = "SELECT s.id, s.filme_id, s.sala_id, s.data_hora, s.preco_ingresso, sa.numero_sala "
                + "FROM sessoes s JOIN salas sa ON s.sala_id = sa.id WHERE s.id = ?";

        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    retorno = new SessaoModel();
                    retorno.setId(rs.getInt("id"));
                    retorno.setFilmeId(rs.getInt("filme_id"));
                    retorno.setSalaId(rs.getInt("sala_id"));
                    Timestamp ts = rs.getTimestamp("data_hora");
                    if (ts != null) retorno.setDataHora(ts.toLocalDateTime());
                    retorno.setPrecoIngresso(rs.getDouble("preco_ingresso"));
                    retorno.setNumeroSala(rs.getInt("numero_sala"));
                }
            }
        }

        return retorno;
    }

    // Métodos de inserção/edição/exclusão podem ser adicionados conforme necessário
}
