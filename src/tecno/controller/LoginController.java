package tecno.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tecno.dao.GrupoDao;
import tecno.dao.UsuarioDao;
import tecno.modelo.Funcionalidade;
import tecno.modelo.Grupo;
import tecno.modelo.Usuario;
import tecno.utils.Utils;

@ManagedBean
@ViewScoped
public class LoginController {
	private Usuario usuario = new Usuario();
			
	public String efetuarLogin(){
		usuario.setSenha(Utils.toMD5(usuario.getSenha()));
		usuario = new UsuarioDao().buscarPorLoginESenha(usuario);	
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(usuario != null){
			//adicionar funcionario na sessao http
			
			context.getExternalContext().getSessionMap().put("usuariologado", usuario);
			
			Grupo grupo = new GrupoDao().getGrupoFuncionalidades(usuario.getGrupo());
			
			for (Funcionalidade f: grupo.getFuncionalidades()) {
				context.getExternalContext().getSessionMap().put(f.getPagina(), f);
			}
			
			return "principal?faces-redirect=true";
		}else{
			///para manter a mensagem na tela 
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Usuário ou senha incorretos."));
			return "login?faces-redirect=true";
		}
		
	}

	public String deslogar(){
		//remover usuario da sessao
		FacesContext context = FacesContext.getCurrentInstance();
//		context.getExternalContext().getSessionMap().remove("usuariologado");
		context.getExternalContext().getSessionMap().clear();
		
		return "login?faces-redirect=true";
	}
	// getters e setters

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public String formLogin(){
		return "login?faces-redirect=true";
	}
	

}

