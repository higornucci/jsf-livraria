package br.com.higornucci.loja.modelo;

import br.com.higornucci.loja.dao.LivroDao;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class LivroDataModel extends LazyDataModel<Livro> {
    @Inject private LivroDao dao;

    public LivroDataModel() {
        super.setRowCount(dao.quantidadeDeElementos());
    }

    @Override
    public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
        String titulo = (String) filtros.get("titulo");
        return dao.listaTodosPaginada(inicio, quantidade, "titulo", titulo);
    }
}
