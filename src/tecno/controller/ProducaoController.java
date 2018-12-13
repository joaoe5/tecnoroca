package tecno.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tecno.dao.AnimalDao;
import tecno.dao.DAO;
import tecno.modelo.Animal;
import tecno.modelo.Producao;
import tecno.modelo.Produto;


@ViewScoped
@ManagedBean
public class ProducaoController {
	private Producao producao = new Producao();
	
	private Integer idProduto;
	private Integer idAnimal;

	public Producao getProducao() {
		return producao;
	}

	public void setProducao(Producao producao) {
		this.producao = producao;
	}
	
	public void gravar(Producao producao) {
		Produto produto = new DAO<Produto>(Produto.class).buscaPorId(idProduto);
		this.producao.setProduto(produto);
		
		Animal animal = new DAO<Animal>(Animal.class).buscaPorId(idAnimal);
		this.producao.setAnimal(animal);
		
		DAO<Producao> dao = new DAO<Producao>(Producao.class);
		if(producao.getId() == null){
			dao.adiciona(producao);
		}
		else{
			dao.atualiza(producao);
		}
		
		this.producao = new Producao();
	}
	
	public void carregar(Producao producao){
		this.producao = producao;
		this.idProduto = producao.getProduto().getId();
		this.idAnimal = producao.getAnimal().getId();
	}
	
	public void remover(Producao producao){
		try {
			new DAO<Producao>(Producao.class).remove(producao);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("producao", new
			FacesMessage("Não é possivel apagar essa produção!"));
		}
		
	}
	
	public List<Producao> getTodasProducoes(){
		return new DAO<Producao>(Producao.class).listaTodos();
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	
	public Integer getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Integer idAnimal) {
		this.idAnimal = idAnimal;
	}

//	public String formProducao(){
//		return "producao?faces-redirect=true";
//	}
//	
//	public String formProduto(){
//		return "produto?faces-redirect=true";
//	}
//	
//	public String formAnimal(){
//		return "animal?faces-redirect=true";
//	}
	
	public String redirecionaProduto(Integer idAnimal) {
		this.idAnimal = idAnimal;
		return "produto?faces-redirect=true&includeViewParams=true";
	}
	
	public String redirecionaProducao(Integer idAnimal) {
		this.idAnimal = idAnimal;
		return "producao?faces-redirect=true&includeViewParams=true";
	}
	
	public List<Producao> getProducoesId(){
		return new AnimalDao<Producao>(Producao.class).listaPorAnimalId(idAnimal);
	}
	
}
