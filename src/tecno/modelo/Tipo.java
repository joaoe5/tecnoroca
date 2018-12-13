package tecno.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private int tempo_gestacao;
	private String unidade_gestacao;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTempo_gestacao() {
		return tempo_gestacao;
	}

	public void setTempo_gestacao(int tempo_gestacao) {
		this.tempo_gestacao = tempo_gestacao;
	}

	public String getUnidade_gestacao() {
		return unidade_gestacao;
	}

	public void setUnidade_gestacao(String unidade_gestacao) {
		this.unidade_gestacao = unidade_gestacao;
	}
}
