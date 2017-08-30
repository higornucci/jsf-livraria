package br.com.higornucci.loja.dao;

import br.com.higornucci.loja.modelo.Livro;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class LivroDao implements Serializable {

    @Inject
    private EntityManager em;
    private DAO<Livro> dao;

    @PostConstruct
    void init() {
        this.dao = new DAO<>(em, Livro.class);
    }

    public Livro buscaPorId(Integer livroId) {
        return this.dao.buscaPorId(livroId);
    }

    public void adiciona(Livro livro) {
        this.dao.adiciona(livro);
    }

    public void atualiza(Livro livro) {
        this.dao.atualiza(livro);
    }

    public void remove(Livro livro) {
        this.dao.remove(livro);
    }

    public List<Livro> listaTodos() {
        return this.dao.listaTodos();
    }
}
