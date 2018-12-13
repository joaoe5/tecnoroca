package tecno.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import tecno.modelo.Cultura;

public class CulturaDao <T> {
	
	private final Class<T> classe;
	
	public CulturaDao(Class<T> classe) {
		this.classe = classe;
	}
	
	public List<T> listaPorCulturaId(Integer id){
		Cultura cultura= new Cultura();
		cultura.setId(id);
		String jpql = "SELECT t FROM " + this.classe.getName()+" t WHERE t.cultura = :pCultura";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery(jpql);
		query.setParameter("pCultura", cultura);
		
		List<T> lista = query.getResultList();
		
		em.close();
		
		return lista;
		}
}
