package DTO;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "funcionarios")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("F")
public class FuncionarioDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;
		
	@Column(nullable = false, precision = 11, scale = 2)
	private double salario;
	
    @Embedded
	private Endereco endereco;
    
    @ManyToOne
    private DepartamentoDTO departamento;
	
	public FuncionarioDTO() {
		
	}

	public FuncionarioDTO(String nome, double salario, String rua, String cidade, String estado) {
		super();
		this.nome = nome;
		this.salario = salario;
		this.endereco = new Endereco(rua, cidade, estado);
		this.departamento = null;
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

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public DepartamentoDTO getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoDTO departamento) {
		this.departamento = departamento;
	}
	
	

}
