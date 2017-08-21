package br.com.higornucci.loja.bean;

import br.com.higornucci.loja.model.Livro;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LivroBean {

    private Livro livro = new Livro();

    public void gravar() {
        System.out.println("Gravando livro " + livro.getTitulo());
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
