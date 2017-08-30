package br.com.higornucci.loja.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DAO<T> implements Serializable {

	private final EntityManager entityManager;
	private final Class<T> classe;

	public DAO(EntityManager entityManager, Class<T> classe) {
		this.entityManager = entityManager;
		this.classe = classe;
	}

	public void adiciona(T t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	public int quantidadeDeElementos() {
		long result = (Long) entityManager.createQuery("select count(n) from " + classe.getSimpleName() + " n")
				.getSingleResult();
		return (int) result;
	}

	public void remove(T t) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.merge(t));
		entityManager.getTransaction().commit();
	}

	public void atualiza(T t) {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		return entityManager.createQuery(query).getResultList();
	}

	public T buscaPorId(Integer id) {
		return entityManager.find(classe, id);
	}

	public int contaTodos() {
		long result = (Long) entityManager.createQuery("select count(n) from livro n")
				.getSingleResult();
		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valor) {
		CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(classe);
		Root<T> root = query.from(classe);
		if(valor != null) {
			query = query.where(entityManager.getCriteriaBuilder().like(root.get(coluna), valor + "%"));
		}
		return entityManager.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}

}
