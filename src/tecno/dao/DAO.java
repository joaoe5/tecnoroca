package tecno.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import tecno.modelo.Animal;
import tecno.modelo.Raca;
import tecno.modelo.Usuario;

public class DAO <T> {
	private final Class<T> classe;
	
	public DAO(Class<T> classe) {
		this.classe = classe;
	}
	
	public void adiciona(T t){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(t);
		
		em.getTransaction().commit();
		
		em.close();
		
	}
	
	public void removeID(T t, Integer id){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		t = em.find(classe, id);
		em.remove(t);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	public void remove(T t){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		t = em.merge(t);
		em.remove(t);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	public void atualiza(T t){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.merge(t);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	public List<T> listaTodos(){
		String jpql = "SELECT s FROM "+ this.classe.getName()+" s";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery(jpql);
		
		List<T> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}
	
//	public List<T> listaPorId(Integer id){
//		Animal animal = new Animal();
//		animal.setId(id);
//		String jpql = "SELECT t FROM " + this.classe.getName()+" t WHERE t.animal = :pAnimal";
//		
//		EntityManager em = JPAUtil.getEntityManager();
//		
//		Query query = em.createQuery(jpql);
//		query.setParameter("pAnimal", animal);
//		
//		List<T> lista = query.getResultList();
//		
//		em.close();
//		
//		return lista;
//	}
	
	public T buscaPorId(int id){
		EntityManager em = JPAUtil.getEntityManager();
		
		T t = em.find(this.classe,id);
		
		em.close();
		
		return t;
	}
	
	public List<T>listaPorUsuario(Integer id){
		Usuario usuario = new Usuario();
		usuario.setId(id);
		String jpql = "SELECT t FROM " + this.classe.getName()+" t WHERE t.usuario = :pUsuario";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery(jpql);
		query.setParameter("pUsuario", usuario);
		
		List<T> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}
	
	public Long contaTodos(){
		String jpql = "SELECT count(s) FROM " + this.classe.getName() + " s";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery(jpql);
		
		Long qtd = (Long)query.getSingleResult();
		
		em.close();
		
		return qtd;
	}

}
