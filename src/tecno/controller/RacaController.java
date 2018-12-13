package tecno.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tecno.dao.DAO;
import tecno.dao.RacaDao;
import tecno.modelo.Raca;
import tecno.modelo.Tipo;
import tecno.modelo.Usuario;

@ViewScoped
@ManagedBean
public class RacaController {

	private Raca raca = new Raca();
	private Integer idTipo;
	private Integer idUsuario;
	
//	FacesContext context = FacesContext.getCurrentInstance();
//	Usuario idUser = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
//	idUser = new DAO<Usuario>(Usuario.class).buscaPorId(idUser.getId());
//	//private Usuario usuario = new Usuario();
//	//private Integer idUser = usuario.getId();
	
	public Raca getRaca() {
		return raca;
	}
	public void setRaca(Raca raca) {
		this.raca = raca;
	}
	public Integer getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	
	public void gravar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		this.raca.setUsuario(usuario);
		
		Tipo tipo = new DAO<Tipo>(Tipo.class).buscaPorId(idTipo);
		this.raca.setTipo(tipo);
		
		DAO<Raca> dao = new DAO<Raca>(Raca.class);
	
		if(raca.getId() == null) 
			dao.adiciona(raca);
		else{
			dao.atualiza(raca);
		}
		
		raca = new Raca();
	}
	
	public void carregar(Raca raca){
		this.raca = raca;
		this.idTipo = raca.getTipo().getId();
	}
	
	public void remover(Raca raca){
		try {
			new DAO<Raca>(Raca.class).remove(raca);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("raca", new
			FacesMessage("A raça não pode ser removida, pois está associada a uma alimentação."));
		}
		
	}
	
	public List<Raca> getRacas(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		this.raca.setUsuario(usuario);
		idUsuario = usuario.getId();
		
		return new DAO<Raca>(Raca.class).listaPorUsuario(idUsuario);
		//return new RacaDao().listaRacaUsuario(idUsuario);
	}
	
	public List<Tipo> getTodosTipos(){
		return new DAO<Tipo>(Tipo.class).listaTodos();
	}
	
	public List<Raca> getTodasRacas(){
		return new RacaDao().buscaRacaPorTipo(idTipo);
	}
	
	public String formRaca(){
		return "raca?faces-redirect=true";
	}
	
	public String redirecionaAlimentacao(Raca raca) {
		this.raca = raca;
		return "alimentacao?faces-redirect=true&includeViewParams=true";
	}
	
//	public List<Raca> getRacaUsuario() {
//		return new RacaDao().buscaRacaPorTipoEUsuario(idTipo, idUsuario);
//	}
}
