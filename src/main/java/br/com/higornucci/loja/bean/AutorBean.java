package br.com.higornucci.loja.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.higornucci.loja.dao.DAO;
import br.com.higornucci.loja.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Integer autorId;

	private Autor autor = new Autor();

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void carregarAutorPelaId() {
		this.autor = new DAO<>(Autor.class).buscaPorId(autorId);
	}

	public Autor getAutor() {
		return autor;
	}

	public List<Autor> getAutores() {
		return new DAO<>(Autor.class).listaTodos();
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			new DAO<>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<>(Autor.class).atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}

	public void carregar(Autor autor) {
		System.out.println("Carregando autor");
		this.autor = autor;
	}

	public void remover(Autor autor) {
		System.out.println("Removendo autor");
		new DAO<>(Autor.class).remove(autor);
	}

}
