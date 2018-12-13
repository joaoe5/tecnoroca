package tecno.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import tecno.modelo.Animal;

public class AnimalDao <T> {
	
	private final Class<T> classe;
	
	public AnimalDao(Class<T> classe) {
		this.classe = classe;
	}
	
	public List<T> listaPorAnimalId(Integer id){
		Animal animal = new Animal();
		animal.setId(id);
		String jpql = "SELECT t FROM " + this.classe.getName()+" t WHERE t.animal = :pAnimal";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery(jpql);
		query.setParameter("pAnimal", animal);
		
		List<T> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}

}
