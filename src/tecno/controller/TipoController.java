package tecno.controller;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tecno.dao.DAO;
import tecno.modelo.Tipo;
import tecno.modelo.UnidadeGestacao;


@ViewScoped
@ManagedBean
public class TipoController {
	private Tipo tipo = new Tipo();
	
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public List<UnidadeGestacao> getUnidadeGestacao(){
		return Arrays.asList(UnidadeGestacao.values());
	}
	
	public void gravar(){
		DAO<Tipo> dao = new DAO<Tipo>(Tipo.class);
	
		if(tipo.getId() == null) //novo
			dao.adiciona(tipo);
		else{
			dao.atualiza(tipo);
		}
		
		tipo = new Tipo();
	}

	public void carregar(Tipo tipo){
		this.tipo = tipo;
	}
	
	public void remover(Tipo tipo){
		try {
			new DAO<Tipo>(Tipo.class).remove(tipo);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("tipo", new
			FacesMessage("O tipo não pode ser removido pois está associado a uma raça"));
		}
		
	}
	
	public List<Tipo> getTipos(){
		return new DAO<Tipo>(Tipo.class).listaTodos();
	}
	
	public String formTipo(){
		return "tipo?faces-redirect=true";
	}
	
//	public List<Tipo> getTiposId(){
//		FacesContext context = FacesContext.getCurrentInstance();
//		
//		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
//		idUsuario = usuario.getId();
//		
//		return new UsuarioDao().listaPorTipoId(idUsuario);
//	}

}
