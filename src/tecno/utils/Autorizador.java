package tecno.utils;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import tecno.modelo.Funcionalidade;
import tecno.modelo.Usuario;


public class Autorizador implements PhaseListener{
	
	@Override
	public void afterPhase(PhaseEvent event) {
		///pegar o nome da pagina
		FacesContext context = event.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		
		if(nomePagina.equals("/login.xhtml")){
			return ;
		}
		
		//outra página
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		//usuario ja esta logado, nao precisa fazer nada
		if(usuarioLogado != null){
			
			//a página principal pode ser acessada
			if(nomePagina.equals("/principal.xhtml")){
				return;
			}
			
			/// caso nao seja,obtém  a funcionalidade da sessão
			Funcionalidade funcionalidade = (Funcionalidade) context.getExternalContext().getSessionMap().get(nomePagina);
			if(funcionalidade != null){
				return;
			}
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, null, "/principal?faces-redirect=true");
			
			context.renderResponse();
			return;
		}
		
		//usuario nao esta logado
		if (nomePagina.equals("/usuariocomum.xhtml")) {
			return;
		} else {
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, null, "/login?faces-redirect=true");
		}
		
		context.renderResponse();
		
	}
	
	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
