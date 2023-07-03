package DTO;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Gerentes")
@DiscriminatorValue("G")
public class GerenteDTO extends FuncionarioDTO {
	
	private double bonus;
	
	public GerenteDTO() {
		
	}

	public GerenteDTO(String nome, double salario, String rua, String cidade, String estado, double bonus) {		
		super(nome, salario, rua, cidade, estado);
		this.setSalario(salario + bonus);;
		this.bonus = bonus;
	}
	
	public GerenteDTO(FuncionarioDTO funcionario, double bonus) {
		super(funcionario.getNome(), funcionario.getSalario(), funcionario.getEndereco().getRua(), funcionario.getEndereco().getCidade(), funcionario.getEndereco().getEstado());
		this.setSalario(funcionario.getSalario() + bonus);
		this.bonus = bonus;

	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	
	

}
