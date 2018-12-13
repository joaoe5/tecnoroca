package tecno.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tecno.dao.CulturaDao;
import tecno.dao.DAO;
import tecno.modelo.Atividades;
import tecno.modelo.Cultura;
import tecno.modelo.Servico;

@ViewScoped
@ManagedBean
public class AtividadesController {
	private Atividades atividades = new Atividades();
	
	private Integer idServico;
	private Integer idCultura;
	
	public Atividades getAtividades() {
		return atividades;
	}

	public void setAtividades(Atividades atividades) {
		this.atividades = atividades;
	}

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}

	public Integer getIdCultura() {
		return idCultura;
	}

	public void setIdCultura(Integer idCultura) {
		this.idCultura = idCultura;
	}

	public void gravar(Atividades atividades) {
		Servico servico = new DAO<Servico>(Servico.class).buscaPorId(idServico);
		this.atividades.setServico(servico);
		
		Cultura cultura = new DAO<Cultura>(Cultura.class).buscaPorId(idCultura);
		this.atividades.setCultura(cultura);
		
		DAO<Atividades> dao = new DAO<Atividades>(Atividades.class);
		if(atividades.getId() == null){
			dao.adiciona(atividades);
		}
		else{
			dao.atualiza(atividades);
		}
		this.atividades = new Atividades();
	}
	
	public void carregar(Atividades atividades){
		this.atividades = atividades;
		this.idServico = atividades.getServico().getId();
		this.idCultura = atividades.getCultura().getId();
	}
	
	public void remover(Atividades atividades){
		new DAO<Atividades>(Atividades.class).remove(atividades);	
	}
	
	public List<Atividades> getTodasAtividades(){
		return new DAO<Atividades>(Atividades.class).listaTodos();
	}
	
	public List<Atividades> getAtividadesId(){
		return new CulturaDao<Atividades>(Atividades.class).listaPorCulturaId(idCultura);
	}
	
	public String redirecionaServico(Integer idCultura) {
		this.idCultura = idCultura;
		return "servico?faces-redirect=true&includeViewParams=true";
	}
	
	public String redirecionaAtividades(Integer idCultura) {
		this.idCultura = idCultura;
		return "atividades?faces-redirect=true&includeViewParams=true";
	}
	
	public String formServico(){
		return "servico?faces-redirect=true";
	}
	
	public String formCultura(){
		return "cultura?faces-redirect=true";
	}
	
	
}
