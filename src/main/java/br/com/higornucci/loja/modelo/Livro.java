package br.com.higornucci.loja.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

@Entity
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String titulo = "Testando";
	private String isbn = "232323";
	private double preco;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataLancamento = Calendar.getInstance();

	@ManyToMany(fetch=FetchType.EAGER)
	private List<Autor> autores = new ArrayList<>();

	public List<Autor> getAutores() {
		return autores;
	}

	public void adicionaAutor(Autor autor) {
		this.autores.add(autor);
	}

	public void removeAutor(Autor autor) {
		this.autores.remove(autor);
	}

	public Livro() {
	}

	public Integer getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

}