package br.com.higornucci.loja.bean;

import br.com.higornucci.loja.dao.DAO;
import br.com.higornucci.loja.modelo.Autor;
import br.com.higornucci.loja.modelo.Livro;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

	private Livro livro = new Livro();

	private Integer autorId;

	public Livro getLivro() {
		return livro;
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}

		if (this.livro.getId() == null) {
			new DAO<>(Livro.class).adiciona(this.livro);
		} else {
			new DAO<>(Livro.class).atualiza(this.livro);
		}

		this.livro = new Livro();
	}

	public void gravarAutor() {
		Autor autor = new DAO<>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
	}

	public List<Livro> getLivros() {
		return new DAO<>(Livro.class).listaTodos();
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Deveria começar com 1"));
		}
	}

	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}
	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public List<Autor> getAutores() {
		return new DAO<>(Autor.class).listaTodos();
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public String formAutor() {
		System.out.println("Chamanda o formulario do Autor");
		return "autor?faces-redirect=true";
	}

	public void remover(Livro livro) {
		System.out.println("Removendo livro " + livro.getTitulo());
		new DAO<>(Livro.class).remove(livro);
	}

	public void carregar(Livro livro) {
		System.out.println("Carregando livro " + livro.getTitulo());
		this.livro = livro;
	}
}
