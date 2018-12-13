package tecno.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tecno.dao.AnimalDao;
import tecno.dao.DAO;
import tecno.modelo.Animal;
import tecno.modelo.Medicacao;
import tecno.modelo.Medicamento;

@ViewScoped
@ManagedBean
public class MedicacaoController {
	private Medicacao medicacao = new Medicacao();
	
	private Integer idMedicamento;
	private Integer idAnimal;

	public Medicacao getMedicacao() {
		return medicacao;
	}

	public void setMedicacao(Medicacao medicacao) {
		this.medicacao = medicacao;
	}

	public Integer getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Integer idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	
	public Integer getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Integer idAnimal) {
		this.idAnimal = idAnimal;
	}

	public void gravar(Medicacao medicacao) {
		Medicamento medicamento = new DAO<Medicamento>(Medicamento.class).buscaPorId(idMedicamento);
		this.medicacao.setMedicamento(medicamento);
		
		Animal animal = new DAO<Animal>(Animal.class).buscaPorId(idAnimal);
		this.medicacao.setAnimal(animal);
		
		DAO<Medicacao> dao = new DAO<Medicacao>(Medicacao.class);
		if(medicacao.getId() == null){
			dao.adiciona(medicacao);
		}
		else{
			dao.atualiza(medicacao);
		}
		this.medicacao = new Medicacao();
	}
	
	public void carregar(Medicacao medicacao){
		this.medicacao = medicacao;
		this.idMedicamento = medicacao.getMedicamento().getId();
		this.idAnimal = medicacao.getAnimal().getId();
	}
	
	public void remover(Medicacao medicacao){
		new DAO<Medicacao>(Medicacao.class).remove(medicacao);
	}
	
	public List<Medicacao> getTodasMedicacoes(){
		return new DAO<Medicacao>(Medicacao.class).listaTodos();
	}
	
	public List<Medicacao> getMedicacoesId(){
		return new AnimalDao<Medicacao>(Medicacao.class).listaPorAnimalId(idAnimal);
	}
	
	public String formMedicacao(){
		return "medicacao?faces-redirect=true";
	}
	
	public String redirecionaMedicamento(Integer idAnimal) {
		this.idAnimal = idAnimal;
		return "medicamento?faces-redirect=true&includeViewParams=true";
	}
	
	public String redirecionaMedicacao(Integer idAnimal) {
		this.idAnimal = idAnimal;
		return "medicacao?faces-redirect=true&includeViewParams=true";
	}
}
