package servidor.controller.impl;

import DAO.SessoesDAO;
import contract.model.SessaoModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessoesController {

    private final SessoesDAO sessoesDAO = new SessoesDAO();

    public List<SessaoModel> listarPorFilme(int filmeId) throws SQLException {
        List<SessaoModel> lista = new ArrayList<>();
        List<SessaoModel> models = sessoesDAO.buscarPorFilmeId(filmeId);
        lista.addAll(models);
        return lista;
    }

    public SessaoModel buscarPorId(int id) throws SQLException {
        return sessoesDAO.pesquisarPorId(id);
    }
}
