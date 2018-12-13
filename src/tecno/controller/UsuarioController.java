package tecno.controller;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import tecno.dao.DAO;
import tecno.dao.UsuarioDao;
import tecno.modelo.Grupo;
import tecno.modelo.Sexo;
import tecno.modelo.Usuario;
import tecno.utils.Utils;

@ViewScoped
@ManagedBean
public class UsuarioController {
	
	Usuario usuario = new Usuario();
	private String senhaBD;
	private Integer idGrupo;
	private Integer idUsuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaBD() {
		return senhaBD;
	}


	public void setSenhaBD(String senhaBD) {
		this.senhaBD = senhaBD;
	}


	public Integer getIdGrupo() {
		return idGrupo;
	}


	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public List<Sexo> getSexos(){
		return Arrays.asList(Sexo.values());
	}
	
//	public List<Usuario> getTodosUsuarios(){
//		return new DAO<Usuario>(Usuario.class).listaTodos();
//	}
	
	public List<Usuario> getTodosUsuariosExcetoLogado(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		return new UsuarioDao().listaUsuariosNotLogado(usuario.getId());
	}
	
	public void carregar(Usuario usuario){
		this.usuario = usuario;
		this.senhaBD = null;
		this.usuario.setSenha(null);
		//this.idGrupo = usuario.getGrupo().getId();
	}
	
	public void gravar(Usuario usuario) {
		if(!usuario.getSenha().equals(senhaBD)){
			usuario.setSenha(Utils.toMD5(usuario.getSenha()));
		}
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario user = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		if(usuario == user){
			new DAO<Usuario>(Usuario.class).atualiza(usuario);
			FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("Sucesso ao alterar cadastro."));
		}
		else {
			try{
				Grupo grupo = new DAO<Grupo>(Grupo.class).buscaPorId(idGrupo);
				this.usuario.setGrupo(grupo);
				
				DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
				if(usuario.getId() == null){
					dao.adiciona(usuario);
				}
				else{
					dao.atualiza(usuario);
				}
				this.usuario = new Usuario();
				FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("Usuário cadastrado com sucesso!"));
			}catch(Exception e){
				this.senhaBD = null;
				this.usuario.setSenha(null);
				FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("Login já existe."));
				e.printStackTrace();
			}
		}
		
	}
	
	public void gravarComum(Usuario usuario) {
		if(!usuario.getSenha().equals(senhaBD)){
			usuario.setSenha(Utils.toMD5(usuario.getSenha()));
		}
		try{
			idGrupo = 1;
			Grupo grupo = new DAO<Grupo>(Grupo.class).buscaPorId(idGrupo);
			this.usuario.setGrupo(grupo);
			
			DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
			if(usuario.getId() == null){
				dao.adiciona(usuario);
			}
			else{
				dao.atualiza(usuario);
			}
			this.usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage("usuariocomum", new FacesMessage("Usuário cadastrado com sucesso!"));
		}catch(Exception e){
			this.senhaBD = null;
			this.usuario.setSenha(null);
			FacesContext.getCurrentInstance().addMessage("usuariocomum", new FacesMessage("Login já existe."));
			e.printStackTrace();
			
		}
		
	}
	
	public void gravarAtualiza(Usuario usuario) {
		if(!usuario.getSenha().equals(senhaBD)){
			usuario.setSenha(Utils.toMD5(usuario.getSenha()));
		}
		FacesContext context = FacesContext.getCurrentInstance();
    	Usuario user = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		try{
			DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
			usuario.setId(user.getId());
			usuario.setGrupo(user.getGrupo());
			dao.atualiza(usuario);
			context.getExternalContext().getSessionMap().replace("usuariologado", usuario);
			this.usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("Sucesso ao alterar cadastro."));
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("Login já existe."));
			e.printStackTrace();
		}
	
	}
	
	
	public void remover(Usuario usuario){
		new DAO<Usuario>(Usuario.class).remove(usuario);
	}
	
	public void removerE(Usuario usuario){
		try {
			new DAO<Usuario>(Usuario.class).remove(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("adm", new FacesMessage("Não é possivel remover este usuário."));
		}
		
	}
	
	public List<Grupo> getTodosGrupos(){
		return new DAO<Grupo>(Grupo.class).listaTodos();
	}
	
	public String formAdm(){
		return "adm?faces-redirect=true";
	}
	
	public String formUsuario(){
		return "usuario?faces-redirect=true";
	}
	
	public String formUsuariocomum(){
		return "usuariocomum?faces-redirect=true";
	}
	
	public String redirecionaAdmAnimal(Usuario usuario) {
		this.usuario = usuario;
		return "adm-animal?faces-redirect=true&includeViewParams=true";
	}
	
	public String redirecionaAdmCultura(Usuario usuario) {
		this.usuario = usuario;
		return "adm-cultura?faces-redirect=true&includeViewParams=true";
	}
	
	public String redirecionaAtualiza() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
				
		usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		this.usuario = usuario;
		return "atualiza-cadastro?faces-redirect=true&includeViewParams=true";
	}
	
	public Usuario getUsuarios(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		return usuario;
	}
	
	public void senhaMaiorque8(FacesContext fc, UIComponent component, Object value)
		throws ValidatorException{
		String str = value.toString();
		int t = str.length();
		
		if(!(t > 8)){
			throw new ValidatorException(new FacesMessage("A senha deve conter mais de 8 caracteres!"));
		}
		}

}