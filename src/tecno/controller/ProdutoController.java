package tecno.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tecno.dao.DAO;
import tecno.modelo.Produto;
import tecno.modelo.Usuario;


@ViewScoped
@ManagedBean
public class ProdutoController {
	private Produto produto = new Produto();
	private Integer idUsuario;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public void gravar(Produto produto) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		this.produto.setUsuario(usuario);
		
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		if(produto.getId() == null){
			dao.adiciona(produto);
		}
		else{
			dao.atualiza(produto);
		}
		this.produto = new Produto();
	}
	
	public void carregar(Produto produto){
		this.produto = produto;
	}
	
//	public void remover(Produto produto){
//		new DAO<Produto>(Produto.class).remove(produto);
//	}
	
	public void remover(Produto produto){
		try {
			new DAO<Produto>(Produto.class).remove(produto);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("produto", new
			FacesMessage("O produto não pode ser removido pois está vinculado a uma produção!"));
		}
		
	}
	
	public List<Produto> getProdutos(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuariologado");
		
		usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		this.produto.setUsuario(usuario);
		idUsuario = usuario.getId();
		
		return new DAO<Produto>(Produto.class).listaPorUsuario(idUsuario);
	}
	
	public List<Produto> getTodosProdutos(){
		return new DAO<Produto>(Produto.class).listaTodos();
	}

}
