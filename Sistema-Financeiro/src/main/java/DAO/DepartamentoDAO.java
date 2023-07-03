package DAO;

import java.util.Scanner;

import DTO.DepartamentoDTO;
import DTO.EmpresaDTO;
import DTO.FuncionarioDTO;
import DTO.GerenteDTO;

public class DepartamentoDAO extends GenericDAO<DepartamentoDTO>{

	public DepartamentoDAO() {
		super(DepartamentoDTO.class);
		// TODO Auto-generated constructor stub
	}
	
	public void atualizarDados(Long id_departamento, Long id_empresa) {
		DepartamentoDTO departamentoExistente = buscarID(id_departamento);
		EmpresaDTO empresaExistente = em.find(EmpresaDTO.class,id_empresa);

        Scanner scanner = new Scanner(System.in);

        if(departamentoExistente == null) {
        	System.out.println("Empresa não encontrada");
        
        
        } 
        else if (departamentoExistente.getEmpresa().getId() != empresaExistente.getId()) {
        	System.out.println("Departamento não pertence a essa empresa");
        }
        else {
        
        	 System.out.println("Nome atual: " + departamentoExistente.getNome());
        	 System.out.println("Digite o novo nome do departamento: ");
             String nomeNovo = scanner.nextLine();
             
             em.getTransaction().begin();
             empresaExistente.setNome(nomeNovo);
             em.getTransaction().commit();
             
             System.out.println("Nome novo: " + empresaExistente.getNome());
        	
        }
	}
	
	public void custoMensal(Long id_departamento) {
		DepartamentoDTO depExistente = em.find(DepartamentoDTO.class, id_departamento);
		
		if(depExistente == null) {
			throw new  IllegalArgumentException("Departamento não foi encontrado");
			
		} else {
			
			double custoMensal = depExistente.getFuncionarios()
											 .stream().map(p -> p.getSalario())
											 .reduce(0.0, (t, p) -> t+p)
											 .doubleValue();
			
        	em.getTransaction().begin();
			depExistente.setGastoMensal(custoMensal);
			em.getTransaction().commit();
		}
		
	}
	
	
	public void addGerente(Long id_gerente, Long id_departamento) {
		DepartamentoDTO depExistente = em.find(DepartamentoDTO.class, id_departamento);
		GerenteDTO gerenteExistente = em.find(GerenteDTO.class, id_gerente);
		
		if(depExistente == null) {
			throw new  IllegalArgumentException("Departamento não foi encontrado");
		}
		
		if(depExistente.getFuncionarios().contains(gerenteExistente)) {
			em.getTransaction().begin();
			depExistente.setGerente(gerenteExistente);
			em.getTransaction().commit();
			
			custoMensal(depExistente.getId());
			
		} else {
			throw new  IllegalArgumentException("Gerente não foi encontrado no departamento");

		}
	}
	
	
	public void addFuncionarioDepartamento(FuncionarioDTO funcionarioDTO, Long id_departamento) {
    	FuncionarioDTO funcionario = em.find(FuncionarioDTO.class, funcionarioDTO.getId());
    	DepartamentoDTO depExistente = em.find(DepartamentoDTO.class, id_departamento);

//    	for(FuncionarioDTO fun: depExistente.getFuncionarios()) {
//    		System.out.println("Nome: " + fun.getNome());
//    	}
    	
    	if(depExistente == null) {
            throw new IllegalArgumentException("Departamento não foi encontrado");
    	}
    	
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado");
        }
        
        
        if (depExistente.getFuncionarios().contains(funcionario)) {
        	System.out.println("Departamento ja tem o " + funcionario.getNome());

    	} else {
    		em.getTransaction().begin();
    		depExistente.getFuncionarios().add(funcionario);
        	em.getTransaction().commit();
        	
    	}
        
        if (funcionario.getDepartamento() != null) {
        	System.out.println("Funcionario ja esta associado ao departamento de " + depExistente.getNome());
        	return;
        } else {
        	em.getTransaction().begin();
        	funcionario.setDepartamento(depExistente);
        	em.merge(funcionario);
        	em.getTransaction().commit();
  

        }
      

        
    }

	
   
}

