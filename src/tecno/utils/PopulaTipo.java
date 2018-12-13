package tecno.utils;

import tecno.dao.DAO;
import tecno.modelo.Tipo;

public class PopulaTipo {
	
	public static void main(String[] args) {
		DAO<Tipo> dao = new DAO<Tipo>(Tipo.class);
		
		Tipo t1 = new Tipo();
		t1.setNome("Equino");
		t1.setTempo_gestacao(330);
		t1.setUnidade_gestacao("Dias");
		
		Tipo t2 = new Tipo();
		t2.setNome("Bovino");
		t2.setTempo_gestacao(284);
		t2.setUnidade_gestacao("Dias");
		
		Tipo t3 = new Tipo();
		t3.setNome("Caprino");
		t3.setTempo_gestacao(151);
		t3.setUnidade_gestacao("Dias");
		
		Tipo t4 = new Tipo();
		t4.setNome("Suino");
		t4.setTempo_gestacao(112);
		t4.setUnidade_gestacao("Dias");
		
		Tipo t5 = new Tipo();
		t5.setNome("Ovino");
		t5.setTempo_gestacao(152);
		t5.setUnidade_gestacao("Dias");
	
		dao.adiciona(t1);
		dao.adiciona(t2);
		dao.adiciona(t3);
		dao.adiciona(t4);
		dao.adiciona(t5);
		}

}
