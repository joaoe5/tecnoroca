package tecno.controller;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tecno.dao.AnimalDao;
import tecno.dao.DAO;
import tecno.dao.RacaDao;
import tecno.modelo.Alimentacao;
import tecno.modelo.Alimento;
import tecno.modelo.Producao;
import tecno.modelo.Raca;
import tecno.modelo.UnidadeConsumo;

@ViewScoped
@ManagedBean
public class AlimentacaoController {
	
	private Alimentacao alimentacao = new Alimentacao();
	
	private Integer idAlimento;
	private Integer idRaca;

	public Integer getIdRaca() {
		return idRaca;
	}

	public void setIdRaca(Integer idRaca) {
		this.idRaca = idRaca;
	}

	public Alimentacao getAlimentacao() {
		return alimentacao;
	}

	public void setAlimentacao(Alimentacao alimentacao) {
		this.alimentacao = alimentacao;
	}
	
	public List<UnidadeConsumo> getUnidadeConsumo(){
		return Arrays.asList(UnidadeConsumo.values());
	}

	public void gravar(Alimentacao alimentacao) {
		Alimento alimento = new DAO<Alimento>(Alimento.class).buscaPorId(idAlimento);
		this.alimentacao.setAlimento(alimento);
		
		Raca raca = new DAO<Raca>(Raca.class).buscaPorId(idRaca);
		this.alimentacao.setRaca(raca);
		
		DAO<Alimentacao> dao = new DAO<Alimentacao>(Alimentacao.class);
		
		if(alimentacao.getId() == null){
			dao.adiciona(alimentacao);
		}
		else{
			dao.atualiza(alimentacao);
		}
		this.alimentacao = new Alimentacao();
	}
	
	public void carregar(Alimentacao alimentacao){
		this.alimentacao = alimentacao;
		this.idAlimento = alimentacao.getAlimento().getId();
		this.idRaca = alimentacao.getRaca().getId();
	}
	
	public void remover(Alimentacao producao){
		new DAO<Alimentacao>(Alimentacao.class).remove(producao);
	}
	
	public List<Alimentacao> getTodasAlimentacoes(){
		return new DAO<Alimentacao>(Alimentacao.class).listaTodos();
	}
	
	public List<Alimentacao> getAlimentacoesId(){
		return new RacaDao().listaPorRacaId(idRaca);
	}

	public Integer getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(Integer idAlimento) {
		this.idAlimento = idAlimento;
	}
	
	public String formAlimentacao(){
		return "alimentacao?faces-redirect=true";
	}
	
	public String redirecionaAlimento(Integer idRaca) {
		this.idRaca = idRaca;
		return "alimento?faces-redirect=true&includeViewParams=true";
	}
	
	public String redirecionaAlimentacao(Integer idRaca) {
		this.idRaca = idRaca;
		return "alimentacao?faces-redirect=true&includeViewParams=true";
	}

}
