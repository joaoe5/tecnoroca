package tecno.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import tecno.modelo.Alimentacao;
import tecno.modelo.Raca;
import tecno.modelo.Tipo;
import tecno.modelo.Usuario;


public class RacaDao {
	
	public List<Raca> buscaRacaPorTipo(Integer tipo){
		String jpql = "SELECT r FROM Raca r WHERE r.tipo.id=:pTipo";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery(jpql);
		query.setParameter("pTipo", tipo);
		
		List<Raca> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}
	
	public List<Raca> buscaRacaPorTipoEUsuario(Integer tipo, Integer idUser){
		Usuario usuario = new Usuario();
		usuario.setId(idUser);
		String jpql = "SELECT r FROM Raca r JOIN FETCH r.tipo t WHERE t.id = :pTipo and r.usuario.id = :pUser";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery(jpql);
		query.setParameter("pTipo", tipo);
		query.setParameter("pUser", idUser);
		
		List<Raca> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}
	
	public List<Raca>listaRacaUsuario(Integer id){
		Usuario usuario = new Usuario();
		usuario.setId(id);
		String jpql = "SELECT t FROM Raca t WHERE t.usuario = :pUsuario";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery(jpql);
		query.setParameter("pUsuario", usuario);
		
		List<Raca> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}
	
	public List<Alimentacao>listaPorRacaId(Integer id){
		Raca raca = new Raca();
		raca.setId(id);
		String jpql = "SELECT t FROM Alimentacao t WHERE t.raca = :pRaca";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery(jpql);
		query.setParameter("pRaca", raca);
		
		List<Alimentacao> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}

}
