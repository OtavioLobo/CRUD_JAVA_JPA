package DTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "departamentos")
public class DepartamentoDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;

	@OneToMany(mappedBy = "departamento")
	List<FuncionarioDTO> funcionarios;
	
	@OneToOne
	private GerenteDTO gerente;
	
	@ManyToOne
	private EmpresaDTO empresa;
	
	private double gastoMensal;
	
	public DepartamentoDTO() {
		
	}
	
	public DepartamentoDTO(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GerenteDTO getGerente() {
		return gerente;
	}

	public void setGerente(GerenteDTO gerente) {
			this.gerente = gerente;
		
	}
	
	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public double getGastoMensal() {
		return gastoMensal;
	}

	public void setGastoMensal(double gastoMensal) {
		this.gastoMensal = gastoMensal;
	}

	public List<FuncionarioDTO> getFuncionarios() {
		if(funcionarios == null ) {
			funcionarios = new ArrayList<>();
		}
		
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioDTO> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
//	public void add(FuncionarioDTO funcionario) {
//		if(funcionario != null && !getFuncionarios().contains(funcionario)) {
//			getFuncionarios().add(funcionario);
//			
//			if(funcionario.getDepartamento() != null) {
//				funcionario.setDepartamento(this); 
//			}
//		}
//	}
	
}
