package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import DTO.DepartamentoDTO;
import DTO.FuncionarioDTO;


public class GenericDAO<E> {

	static EntityManagerFactory emf;
	static EntityManager em;
	private Class<E> classe;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("sistema-financeiro");
			
		}catch (Exception e) {
			throw new UnsupportedOperationException("Erro no emf: " + e);
		}
	}
	
	public GenericDAO() {
		this(null);
	}
	
	
	public GenericDAO(Class<E> classe) {
		super();
		em = emf.createEntityManager();
		this.classe = classe;
	}
	

	public void abrirT() {
		em.getTransaction().begin();
	}
	
	public void fecharT() {
		em.getTransaction().commit();;

	}
	
	public void add(E entidade) {
		em.persist(entidade);
	}
	
	public void addAtomico(E entidade) {
		abrirT();
		add(entidade);
		fecharT();
	}
	
	public void atualizar(E entidade) {		
			em.merge(entidade);
	}
	
	public void remove(Long id) {
		E entidadeExistente = buscarID(id);
		
		if(entidadeExistente != null) {
			abrirT();
			em.remove(entidadeExistente);
			fecharT();
		}
		
	}
	
	 
	public E buscarID(Long id) {
		return em.find(classe, id);
	}
	
	public List<E> buscarTodos() {
		if(classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
		}
		String jpql = "Select u from " + classe.getSimpleName() + " u";
		
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(5);
		
		return query.getResultList();
	}
	
	public List<E> consultar(String nomeConsulta, Object... params) {
		TypedQuery<E> query = em.createNamedQuery(nomeConsulta, classe);
		
		for(int i = 0; i < params.length; i+= 2) {
			query.setParameter(params[i].toString(), params[i + 1]);
		}
		
		return query.getResultList();
		
	}
	
	public void fechar() {
		if (!em.getTransaction().isActive()) {
	        em.close();
	    }
	}
	
}
