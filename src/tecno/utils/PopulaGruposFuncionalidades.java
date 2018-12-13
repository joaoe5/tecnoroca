package tecno.utils;

import tecno.dao.DAO;
import tecno.modelo.Funcionalidade;
import tecno.modelo.Grupo;

public class PopulaGruposFuncionalidades {

	public static void main(String[] args) {
		Funcionalidade f1 = new Funcionalidade();
		f1.setNome("Administrador");
		f1.setPagina("/usuario.xhtml");
		
		Funcionalidade f2 = new Funcionalidade();
		f2.setNome("Animal");
		f2.setPagina("/animal.xhtml");
		
		Funcionalidade f3 = new Funcionalidade();
		f3.setNome("Cultura");
		f3.setPagina("/cultura.xhtml");
		
		Funcionalidade f4 = new Funcionalidade();
		f4.setNome("Atividades");
		f4.setPagina("/atividades.xhtml");
		
		Funcionalidade f5 = new Funcionalidade();
		f5.setNome("Serviços");
		f5.setPagina("/servico.xhtml");
		
		Funcionalidade f6 = new Funcionalidade();
		f6.setNome("Medicação");
		f6.setPagina("/medicacao.xhtml");
		
		Funcionalidade f7 = new Funcionalidade();
		f7.setNome("Medicamento");
		f7.setPagina("/medicamento.xhtml");
		
		Funcionalidade f8 = new Funcionalidade();
		f8.setNome("Raça");
		f8.setPagina("/raca.xhtml");
		
		Funcionalidade f9 = new Funcionalidade();
		f9.setNome("Tipo");
		f9.setPagina("/tipo.xhtml");
		
		Funcionalidade f10 = new Funcionalidade();
		f10.setNome("Produção");
		f10.setPagina("/producao.xhtml");
		
		Funcionalidade f11 = new Funcionalidade();
		f11.setNome("Produto");
		f11.setPagina("/produto.xhtml");
		
		Funcionalidade f12 = new Funcionalidade();
		f12.setNome("Inseminação - Cio");
		f12.setPagina("/inseminacao.xhtml");
		
		Funcionalidade f13 = new Funcionalidade();
		f13.setNome("Alimentação");
		f13.setPagina("/alimentacao.xhtml");
		
		Funcionalidade f14 = new Funcionalidade();
		f14.setNome("Alimento");
		f14.setPagina("/alimento.xhtml");
		
		Funcionalidade f15 = new Funcionalidade();
		f15.setNome("Todos Usuários");
		f15.setPagina("/adm.xhtml");
		
		Funcionalidade f16 = new Funcionalidade();
		f16.setNome("Animal por usuário");
		f16.setPagina("/adm-animal.xhtml");
		
		Funcionalidade f17 = new Funcionalidade();
		f17.setNome("Cultura por animal");
		f17.setPagina("/adm-cultura.xhtml");
		
		Funcionalidade f18 = new Funcionalidade();
		f18.setNome("Atualizar cadastro");
		f18.setPagina("/atualiza-cadastro.xhtml");
		
		DAO<Funcionalidade> daoFuncionalidade = new DAO<Funcionalidade>(Funcionalidade.class);
		daoFuncionalidade.adiciona(f1);
		daoFuncionalidade.adiciona(f2);
		daoFuncionalidade.adiciona(f3);
		daoFuncionalidade.adiciona(f4);
		daoFuncionalidade.adiciona(f5);
		daoFuncionalidade.adiciona(f6);
		daoFuncionalidade.adiciona(f7);
		daoFuncionalidade.adiciona(f8);
		daoFuncionalidade.adiciona(f9);
		daoFuncionalidade.adiciona(f10);
		daoFuncionalidade.adiciona(f11);
		daoFuncionalidade.adiciona(f12);
		daoFuncionalidade.adiciona(f13);
		daoFuncionalidade.adiciona(f14);
		daoFuncionalidade.adiciona(f15);
		daoFuncionalidade.adiciona(f16);
		daoFuncionalidade.adiciona(f17);
		daoFuncionalidade.adiciona(f18);
		
		f1.setId(1);
		f2.setId(2);
		f3.setId(3);
		f4.setId(4);
		f5.setId(5);
		f6.setId(6);
		f7.setId(7);
		f8.setId(8);
		f9.setId(9);
		f10.setId(10);
		f11.setId(11);
		f12.setId(12);
		f13.setId(13);
		f14.setId(14);
		f15.setId(15);
		f16.setId(16);
		f17.setId(17);
		f18.setId(18);
		
		Grupo gAdm = new Grupo();
		gAdm.setNome("Administrador");
		gAdm.add(f1);
		gAdm.add(f2);
		gAdm.add(f3);
		gAdm.add(f4);
		gAdm.add(f5);
		gAdm.add(f6);
		gAdm.add(f7);
		gAdm.add(f8);
		gAdm.add(f9);
		gAdm.add(f10);
		gAdm.add(f11);
		gAdm.add(f12);
		gAdm.add(f13);
		gAdm.add(f14);
		gAdm.add(f15);
		gAdm.add(f16);
		gAdm.add(f17);
		gAdm.add(f18);
		
		Grupo gUsu = new Grupo();
		gUsu.setNome("Usuário");
		gUsu.add(f2);
		gUsu.add(f3);
		gUsu.add(f4);
		gUsu.add(f5);
		gUsu.add(f6);
		gUsu.add(f7);
		gUsu.add(f8);
		gUsu.add(f9);
		gUsu.add(f10);
		gUsu.add(f11);
		gUsu.add(f12);
		gUsu.add(f13);
		gUsu.add(f14);
		gUsu.add(f18);
		
		
		DAO<Grupo> daoGrupo = new DAO<Grupo>(Grupo.class);
		daoGrupo.adiciona(gUsu);
		daoGrupo.adiciona(gAdm);
	}
}
