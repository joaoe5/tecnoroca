package tecno.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tecno.dao.AnimalDao;
import tecno.dao.DAO;
import tecno.modelo.Animal;
import tecno.modelo.InseminacaoCio;

@ViewScoped
@ManagedBean
public class InseminacaoController {
	InseminacaoCio inseminacao = new InseminacaoCio();
	
	private Integer idAnimal;
	
	public InseminacaoCio getInseminacao() {
		return inseminacao;
	}
	public void setInseminacao(InseminacaoCio inseminacao) {
		this.inseminacao = inseminacao;
	}
	public Integer getIdAnimal() {
		return idAnimal;
	}
	public void setIdAnimal(Integer idAnimal) {
		this.idAnimal = idAnimal;
	}
	public void gravar(InseminacaoCio inseminacao) {
		
		Animal animal = new DAO<Animal>(Animal.class).buscaPorId(idAnimal);
		this.inseminacao.setAnimal(animal);
			
		DAO<InseminacaoCio> dao = new DAO<InseminacaoCio>(InseminacaoCio.class);
		if(inseminacao.getId() == null){
			dao.adiciona(inseminacao);
		}
		else{
			dao.atualiza(inseminacao);
		}
		this.inseminacao = new InseminacaoCio();
	}
		
	public void carregar(InseminacaoCio inseminacao){
		this.inseminacao = inseminacao;
		this.idAnimal = inseminacao.getAnimal().getId();
	}
	
	public void remover(InseminacaoCio inseminacao){
		new DAO<InseminacaoCio>(InseminacaoCio.class).remove(inseminacao);
	}
		
	public List<InseminacaoCio> getTodasInseminacoes(){
		return new DAO<InseminacaoCio>(InseminacaoCio.class).listaTodos();
	}
	
	public List<InseminacaoCio> getInseminacoesId(){
		return new AnimalDao <InseminacaoCio>(InseminacaoCio.class).listaPorAnimalId(idAnimal);
	}

}
