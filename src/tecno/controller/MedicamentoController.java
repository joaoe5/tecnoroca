package tecno.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tecno.dao.DAO;
import tecno.modelo.Medicamento;
import tecno.modelo.Usuario;

@ViewScoped
@ManagedBean
public class MedicamentoController {
	private Medicamento medicamento = new Medicamento();
	private Integer idUsuario;

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
	
public void gravar(Medicamento medicamento) {
	
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
	
	    usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
	    this.medicamento.setUsuario(usuario);
	    idUsuario = usuario.getId();
		
		DAO<Medicamento> dao = new DAO<Medicamento>(Medicamento.class);
		if(medicamento.getId() == null){
			dao.adiciona(medicamento);
		}
		else{
			dao.atualiza(medicamento);
		}
		this.medicamento = new Medicamento();
	}
	
	public void carregar(Medicamento medicamento){
		this.medicamento = medicamento;
	}
	
	public void remover(Medicamento medicamento){
		try {
			new DAO<Medicamento>(Medicamento.class).remove(medicamento);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("medicamento", new
			FacesMessage("O medicamento não pode ser removido pois está vinculado a uma medicação!"));
		}
		
	}
	
	public List<Medicamento> getMedicamentos(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		this.medicamento.setUsuario(usuario);
		idUsuario = usuario.getId();
		
		return new DAO<Medicamento>(Medicamento.class).listaPorUsuario(idUsuario);
	}
	
	public List<Medicamento> getTodosMedicamentos(){
		return new DAO<Medicamento>(Medicamento.class).listaTodos();
	}
	
	public String formMedicamento(){
		return "medicamento?faces-redirect=true";
	}
	
}
