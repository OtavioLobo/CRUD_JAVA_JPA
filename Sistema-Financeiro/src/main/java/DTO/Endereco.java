package DTO;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	
	private String Estado;
	
	private String cidade;
	
	private String rua;
	
	public Endereco() {
		
	}

	public Endereco(String rua, String cidade, String estado) {
		super();
		this.Estado = estado;
		this.cidade = cidade;
		this.rua = rua;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}
	

}
