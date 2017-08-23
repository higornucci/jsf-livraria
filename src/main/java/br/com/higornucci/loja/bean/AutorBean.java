package br.com.higornucci.loja.bean;

import br.com.higornucci.loja.dao.DAO;
import br.com.higornucci.loja.modelo.Autor;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class AutorBean implements Serializable {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<>(Autor.class).adiciona(this.autor);

		this.autor = new Autor();
	}
}
