package servidor.controller.impl;

import DAO.IngressoDAO;
import java.sql.SQLException;
import java.util.List;
import servidor.model.IngressoModel;

public class IngressoController {

    private final IngressoDAO ingressoDAO = new IngressoDAO();

    public List<String> getPoltronasOcupadas(int sessaoId) throws SQLException {
        return ingressoDAO.buscarPoltronasPorSessaoId(sessaoId);
    }

    public boolean comprarIngresso(IngressoModel ingresso) throws SQLException {
        return ingressoDAO.inserir(ingresso);
    }
}
