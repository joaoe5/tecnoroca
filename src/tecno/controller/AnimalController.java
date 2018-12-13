package tecno.controller;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tecno.dao.DAO;
import tecno.dao.RacaDao;
import tecno.dao.UsuarioDao;
import tecno.modelo.Animal;
import tecno.modelo.Genero;
import tecno.modelo.Raca;
import tecno.modelo.Tipo;
import tecno.modelo.Usuario;


@ViewScoped
@ManagedBean
public class AnimalController {
	private Animal animal = new Animal();
	private Integer idRaca;
	private Integer idUsuario;
	private Integer idTipo;
	
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public Integer getIdRaca() {
		return idRaca;
	}
	public void setIdRaca(Integer idRaca) {
		this.idRaca = idRaca;
	}
	
	public Integer getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public List<Genero> getGeneros(){
		return Arrays.asList(Genero.values());
	}

	public void gravar(Animal animal) {
		Raca raca = new DAO<Raca>(Raca.class).buscaPorId(idRaca);
		this.animal.setRaca(raca);
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		this.animal.setUsuario(usuario);
		
		DAO<Animal> dao = new DAO<Animal>(Animal.class);
		if(animal.getId() == null){
			dao.adiciona(animal);
		}
		else{
			dao.atualiza(animal);
		}
		
		this.animal = new Animal();
		this.setIdTipo(null);
		this.setIdRaca(null);
	}
	
	public void carregar(Animal animal){
		this.animal = animal;
		//this.setIdRaca(animal.getRaca().getId());
		//this.setIdTipo(animal.getRaca().getTipo().getId());
		this.idTipo = animal.getRaca().getTipo().getId();
		this.idRaca = animal.getRaca().getId();
		//this.idUsuario = animal.getUsuario().getId();
		
		
	}
	
	public void remover(Animal animal){
		try {
			new DAO<Animal>(Animal.class).remove(animal);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("animal", new
			FacesMessage("O animal não pode ser apagado, pois tem Medicações, Produções ou Inseminação associada a ele!"));
		}
		
	}
	
	public List<Animal> getTodosAnimais(){
		return new DAO<Animal>(Animal.class).listaTodos();
	}
	
	public List<Tipo> getTodosTipos(){
		return new DAO<Tipo>(Tipo.class).listaTodos();
	}
	
	public List<Raca> getTodasRacas(){
		return new RacaDao().buscaRacaPorTipo(idTipo);
	}
	
	public List<Animal> getAnimaisId(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		idUsuario = usuario.getId();
		
		return new UsuarioDao().listaPorAnimalId(idUsuario);
	}
	
	public List<Animal> getAnimaisAdmId(){
		return new UsuarioDao().listaPorAnimalId(idUsuario);
	}
	
	public String redirecionaProducao(Animal animal) {
		this.animal = animal;
		return "producao?faces-redirect=true&includeViewParams=true";
	}
	
	public String redirecionaMedicacao(Animal animal) {
		this.animal = animal;
		return "medicacao?faces-redirect=true&includeViewParams=true";
	}
	
	public String redirecionaInseminacao(Animal animal) {
		this.animal = animal;
		return "inseminacao?faces-redirect=true&includeViewParams=true";
	}
	
//	public String redirecionaAdmAnimal(Integer usuario) {
//		this.idUsuario = usuario;
//		return "adm-animal?faces-redirect=true&includeViewParams=true";
//	}
	
	public String formAnimal(){
		return "animal?faces-redirect=true";
	}
	
//	public String redirecionaMedicamento(Integer idAnimal) {
//		this.idAnimal = idAnimal;
//		return "medicamento?faces-redirect=true&includeViewParams=true";
//	}
	
//	public List<Raca> getTodasRacas(){
//		Integer t = (Integer) animal.getRaca().getTipo().getId();
//		return new RacaDao().buscaRacaPorTipo(t);
//	}
	
	public List<Raca> getRacaUsuario() {
		return new RacaDao().buscaRacaPorTipoEUsuario(idTipo, idUsuario);
	}
	
//	public List<Raca> getRacas(){
//		return new RacaDao().listaRacaUsuario(idUsuario);
//	}
//	

}
