package tecno.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tecno.dao.DAO;
import tecno.dao.UsuarioDao;
import tecno.modelo.Animal;
import tecno.modelo.Cultura;
import tecno.modelo.Usuario;

@ViewScoped
@ManagedBean
public class CulturaController {
	private Cultura cultura = new Cultura();
	private Integer idUsuario;
	
	public Cultura getCultura() {
		return cultura;
	}

	public void setCultura(Cultura cultura) {
		this.cultura = cultura;
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public void gravar(Cultura cultura) {

		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		this.cultura.setUsuario(usuario);
		
		DAO<Cultura> dao = new DAO<Cultura>(Cultura.class);
		if(cultura.getId() == null){
			dao.adiciona(cultura);
		}
		else{
			dao.atualiza(cultura);
		}
		this.cultura = new Cultura();
	}
	
	public void carregar(Cultura cultura){
		this.cultura = cultura;
		//this.idUsuario = cultura.getUsuario().getId();
	}
	
	public void remover(Cultura cultura){
		try {
			new DAO<Cultura>(Cultura.class).remove(cultura);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("cultura", new
			FacesMessage("A cultura não pode ser removida pois tem uma atividade vinculada a ela!"));
		}
		
	}
	
	public List<Cultura> getTodasCulturas(){
		return new DAO<Cultura>(Cultura.class).listaTodos();
	}
	
	public String redirecionaAtividades(Cultura cultura) {
		this.cultura = cultura;
		return "atividades?faces-redirect=true&includeViewParams=true";
	}
	
	public List<Cultura> getCulturasId(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		idUsuario = usuario.getId();
		
		return new UsuarioDao().listaPorCulturaId(idUsuario);
	}
	
}
