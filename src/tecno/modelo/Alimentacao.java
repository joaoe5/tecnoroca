package tecno.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Alimentacao")
public class Alimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer consumo;
	
	@Enumerated(EnumType.STRING)
	private UnidadeConsumo unidade_consumo;
	
	@ManyToOne
	private Raca raca;
	
	@ManyToOne
	private Alimento alimento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConsumo() {
		return consumo;
	}

	public void setConsumo(Integer consumo) {
		this.consumo = consumo;
	}

	public UnidadeConsumo getUnidade_consumo() {
		return unidade_consumo;
	}

	public void setUnidade_consumo(UnidadeConsumo unidade_consumo) {
		this.unidade_consumo = unidade_consumo;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}
}
