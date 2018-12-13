package tecno.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tecno.dao.DAO;
import tecno.modelo.Alimento;
import tecno.modelo.Usuario;

@ViewScoped
@ManagedBean
public class AlimentoController {

private Alimento alimento = new Alimento();
	
	private Integer idAlimento;
	private Integer idUsuario;
	
	public Integer getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(Integer idAlimento) {
		this.idAlimento = idAlimento;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public void gravar(Alimento alimento) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		this.alimento.setUsuario(usuario);
		idUsuario = usuario.getId();
		
		DAO<Alimento> dao = new DAO<Alimento>(Alimento.class);
		if(alimento.getId() == null){
			dao.adiciona(alimento);
		}
		else{
			dao.atualiza(alimento);
		}
		this.alimento = new Alimento();
	}
	
	public void carregar(Alimento alimento){
		this.alimento = alimento;
	}
	
	public void remover(Alimento alimento){
		try {
			new DAO<Alimento>(Alimento.class).remove(alimento);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("alimento", new
			FacesMessage("O alimento não pode ser removido pois está vinculado a uma alimentação!"));
		}
		
	}
	
	public List<Alimento> getAlimentos(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		this.alimento.setUsuario(usuario);
		idUsuario = usuario.getId();
		
		return new DAO<Alimento>(Alimento.class).listaPorUsuario(idUsuario);
	}
	
	public List<Alimento> getTodosAlimentos(){
		return new DAO<Alimento>(Alimento.class).listaTodos();
	}
	
	public String formAlimento(){
		return "alimento?faces-redirect=true";
	}
}
