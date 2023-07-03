package DTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Empresas")
public class EmpresaDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false)
	private String nome;
	
	@OneToMany(mappedBy = "empresa")
	private List<DepartamentoDTO> departamentos;
	
	private double gastoMensal;

	public EmpresaDTO() {
		
	}
	
	public EmpresaDTO(String nome) {
		super();
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getGastoMensal() {
		return gastoMensal;
	}

	public void setGastoMensal(double gastoMensal) {
		this.gastoMensal = gastoMensal;
	}

	public List<DepartamentoDTO> getDepartamentos() {
		if(departamentos  == null ) {
			departamentos = new ArrayList<>();
		}
		
		return departamentos;
	}

	public void setDepartamentos(List<DepartamentoDTO> departamentos) {
		this.departamentos = departamentos;
	}
	
	 
	

}
