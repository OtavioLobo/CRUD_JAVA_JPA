package DAO;

import java.util.Scanner;

import javax.transaction.Transactional;

import DTO.*;

public class EmpresaDAO extends GenericDAO<EmpresaDTO> {

	public EmpresaDAO() {
		super(EmpresaDTO.class);
		
	}
	
	public void atualizarDados(Long id_empresa) {
		EmpresaDTO empresaExistente = buscarID(id_empresa);
        Scanner scanner = new Scanner(System.in);

        if(empresaExistente == null) {
        	System.out.println("Empresa não encontrada");
        } else {
        
        	 System.out.println("Nome atual: " + empresaExistente.getNome());
        	 System.out.println("Digite o novo nome da empresa: ");
             String nomeNovo = scanner.nextLine();
             
             em.getTransaction().begin();
             empresaExistente.setNome(nomeNovo);
             em.getTransaction().commit();
             
             System.out.println("Nome novo: " + empresaExistente.getNome());
        	
        }
        
       

	}
	
	public void custoMensal(Long id_empresa) {
		EmpresaDTO empresaExistente = buscarID(id_empresa);
		
		if(empresaExistente == null) {
            throw new IllegalArgumentException("empresa não foi encontrado");

		} else {
			double custoMensal = empresaExistente.getDepartamentos()
												.stream()
												.map(d -> d.getGastoMensal())
												.reduce(0.0, (t, p) -> t+p)
												.doubleValue();
			
			System.out.println("custoMensal: "  + custoMensal);
			
        	em.getTransaction().begin();
        	empresaExistente.setGastoMensal(custoMensal);
			em.getTransaction().commit();
		}
	
		
		
	}
	
	public void addDepartamentoEmpresa(Long id_departamento, Long id_empresa) {
    	DepartamentoDTO dpExistente = em.find(DepartamentoDTO.class, id_departamento);
		EmpresaDTO empresaExistente = em.find(EmpresaDTO.class, id_empresa);
		
		if(empresaExistente == null) {
            throw new IllegalArgumentException("empresa não foi encontrado");
            
		}
		
		if(empresaExistente.getDepartamentos().contains(dpExistente)) {
            throw new IllegalArgumentException("Departamento ja foi cadastrado na empresa");

		} else {
			
			em.getTransaction().begin();
			empresaExistente.getDepartamentos().add(dpExistente);
			em.getTransaction().commit();
		}
		
		
		
		if(dpExistente.getEmpresa() != null) {
            throw new IllegalArgumentException("Empresa já foi cadastrada");

		} else {
			
			em.getTransaction().begin();
			dpExistente.setEmpresa(empresaExistente);
			em.getTransaction().commit();
			
		}
		
		
		
	}
	
}
