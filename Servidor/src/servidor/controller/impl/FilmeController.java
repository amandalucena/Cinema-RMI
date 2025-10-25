package servidor.controller.impl;

import DAO.FilmeDAO;
import contract.model.FilmeModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsável por operações de Filme.
 */
public class FilmeController {

    private final FilmeDAO filmeDAO = new FilmeDAO();

    public List<FilmeModel> listarFilmes() {
        List<FilmeModel> lista = new ArrayList<>();
        List<FilmeModel> models = filmeDAO.buscarTodos();
        // ajustar mapping se necessário; aqui retornamos diretamente o Model
        lista.addAll(models);
        return lista;
    }
}
