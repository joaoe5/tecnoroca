package tecno.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tecno.modelo.Animal;
import tecno.modelo.Cultura;
import tecno.modelo.Tipo;
import tecno.modelo.Usuario;


public class UsuarioDao {
	
	public Usuario buscarPorLoginESenha(Usuario f){
		Usuario resultado = null;
		
		String jpql = "SELECT f FROM Usuario f WHERE f.login = :pLogin and f.senha = :pSenha";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setParameter("pLogin", f.getLogin());
		query.setParameter("pSenha", f.getSenha());
		
		
		try{
		resultado = query.getSingleResult();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		
		em.close();
		
		return resultado;
	}
	
	public List<Tipo>listaPorTipoId(Integer id){
		Usuario usuario = new Usuario();
		usuario.setId(id);
		String jpql = "SELECT t FROM Tipo t WHERE t.usuario = :pUsuario";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery(jpql);
		query.setParameter("pUsuario", usuario);
		
		List<Tipo> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}
	
	public List<Animal>listaPorAnimalId(Integer id){
		Usuario usuario = new Usuario();
		usuario.setId(id);
		String jpql = "SELECT t FROM Animal t WHERE t.usuario = :pUsuario";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery(jpql);
		query.setParameter("pUsuario", usuario);
		
		List<Animal> lista = query.getResultList();
		em.close();
		
		return lista;
	}
	
	public List<Usuario>listaUsuariosNotLogado(Integer id){
		Usuario usuario = new Usuario();
		usuario.setId(id);
		String jpql = "SELECT t FROM Usuario t WHERE NOT t = :pUsuario";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery(jpql);
		query.setParameter("pUsuario", usuario);
		
		List<Usuario> lista = query.getResultList();
		em.close();
		
		return lista;
	}
	
	public List<Cultura>listaPorCulturaId(Integer id){
		Usuario usuario = new Usuario();
		usuario.setId(id);
		String jpql = "SELECT t FROM Cultura t WHERE t.usuario = :pUsuario";
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery(jpql);
		query.setParameter("pUsuario", usuario);
		
		List<Cultura> lista = query.getResultList();
		em.close();
		
		return lista;
	}
	
}
