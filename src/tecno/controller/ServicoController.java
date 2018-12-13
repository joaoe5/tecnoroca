package tecno.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tecno.dao.DAO;
import tecno.modelo.Servico;
import tecno.modelo.Usuario;

@ViewScoped
@ManagedBean
public class ServicoController {
	private Servico servico = new Servico();
	private Integer idUsuario;
	
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public void gravar(Servico servico) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		this.servico.setUsuario(usuario);
		
		DAO<Servico> dao = new DAO<Servico>(Servico.class);
		if(servico.getId() == null){
			dao.adiciona(servico);
		}
		else{
			dao.atualiza(servico);
		}
		this.servico = new Servico();
	}
	
	public void carregar(Servico servico){
		this.servico = servico;
	}
	
//	public void remover(Servico servico){
//		new DAO<Servico>(Servico.class).remove(servico);
//	}
	
	public void remover(Servico servico){
		try {
			new DAO<Servico>(Servico.class).remove(servico);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("servico", new
			FacesMessage("O servico não pode ser removido pois está vinculado a uma cultura!"));
		}
	}
	
	public List<Servico> getTodosServicos(){
		return new DAO<Servico>(Servico.class).listaTodos();
	}
	
	public List<Servico> getServicos(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		this.servico.setUsuario(usuario);
		idUsuario = usuario.getId();
		
		return new DAO<Servico>(Servico.class).listaPorUsuario(idUsuario);
	}

}
