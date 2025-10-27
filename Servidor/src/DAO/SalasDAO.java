package DAO;

import contract.model.SalasModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import servidor.util.ConexaoDB;

public class SalasDAO {

    public boolean inserir(SalasModel sala) throws SQLException {
        String sql = "INSERT INTO salas (numero_sala, capacidade) VALUES (?, ?)";
        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, sala.getNumeroSala());
            ps.setInt(2, sala.getCapacidade());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean editar(SalasModel sala) throws SQLException {
        String sql = "UPDATE salas SET numero_sala = ?, capacidade = ? WHERE id = ?";
        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, sala.getNumeroSala());
            ps.setInt(2, sala.getCapacidade());
            ps.setInt(3, sala.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int idSala) throws SQLException {
        String sql = "DELETE FROM salas WHERE id = ?";
        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSala);
            return ps.executeUpdate() > 0;
        }
    }

    public SalasModel pesquisarPorId(int idSala) throws SQLException {
        SalasModel retorno = null;
        String sql = "SELECT id, numero_sala, capacidade FROM salas WHERE id = ?";
        try (Connection con = ConexaoDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSala);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    retorno = new SalasModel();
                    retorno.setId(rs.getInt("id"));
                    retorno.setNumeroSala(rs.getInt("numero_sala"));
                    retorno.setCapacidade(rs.getInt("capacidade"));
                }
            }
        }
        return retorno;
    }

} 
