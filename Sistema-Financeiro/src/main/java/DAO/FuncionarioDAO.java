package DAO;

import java.util.Scanner;

import org.hibernate.internal.build.AllowSysOut;

import DTO.DepartamentoDTO;
import DTO.EmpresaDTO;
import DTO.FuncionarioDTO;
import DTO.GerenteDTO;

public class FuncionarioDAO extends GenericDAO<FuncionarioDTO> {
	
	public FuncionarioDAO() {
		super(FuncionarioDTO.class);
		
	}
	
	public void atualizarDados(Long id_funcionario, Long id_empresa) {
		FuncionarioDTO funcionarioExistente = buscarID(id_funcionario);
		EmpresaDTO empresaExistente = em.find(EmpresaDTO.class, id_empresa);
        Scanner scanner = new Scanner(System.in);

        if(funcionarioExistente == null) {
        	System.out.println("Funcionario não encontrada");
        
        
        }
        
        
		if(funcionarioExistente.getDepartamento().getEmpresa().getId() == empresaExistente.getId()) {
			boolean sair = false;
			
			System.out.println("<-- Atual -->");
			System.out.println("Nome: " + funcionarioExistente.getNome());
			System.out.println("Salario: " + funcionarioExistente.getSalario());
			System.out.println("Rua: " + funcionarioExistente.getEndereco().getRua());
			System.out.println("Cidade: " + funcionarioExistente.getEndereco().getCidade());
			System.out.println("Estado: " + funcionarioExistente.getEndereco().getEstado());

			
			 while (!sair) {
		        	System.out.println();
		            System.out.println("	Escolha o que deseja alterar:");
		            System.out.println("	1. Nome");
		            System.out.println("	2. Salario");
		            System.out.println("	3. Rua");
		            System.out.println("	4. Cidade");
		            System.out.println("	5. Estado");
		            System.out.println("	6. Sair");
		            int opcao = scanner.nextInt();
		            scanner.nextLine(); 

		            switch (opcao) {
		                case 1:
		                	System.out.println();
		               	    System.out.println("	Nome atual: " + funcionarioExistente.getNome());
		                    System.out.println("	Digite o nome do funcionario:");;

		               	    String nomeNovo = scanner.nextLine();
		                 
		                    em.getTransaction().begin();
		                    funcionarioExistente.setNome(nomeNovo);
		                    em.getTransaction().commit();
		                 
		                    System.out.println("	Nome novo: " + funcionarioExistente.getNome());
		                    
		                    break;
		                case 2:
		                	System.out.println();
		               	    System.out.println("	Salario atual: " + funcionarioExistente.getSalario());
		                    System.out.println("	Digite o novo salario do funcionario:");;

		               	    Double salarioNovo = scanner.nextDouble();
		                 
		                    em.getTransaction().begin();
		                    funcionarioExistente.setSalario(salarioNovo);
		                    em.getTransaction().commit();
		                 
		                    System.out.println("Salario novo: " + funcionarioExistente.getSalario());
		                    
		                    break;
		                case 3:
		                	System.out.println();
		               	    System.out.println("	Rua atual: " + funcionarioExistente.getEndereco().getRua());
		                    System.out.println("	Digite o nome da Rua:");;

		               	    String ruaNova = scanner.nextLine();
		                 
		                    em.getTransaction().begin();
		                    funcionarioExistente.getEndereco().setRua(ruaNova);;
		                    em.getTransaction().commit();
		                 
		                    System.out.println("	Rua novo: " + funcionarioExistente.getEndereco().getRua());
		                    
		                    break;
		                case 4:
		                	System.out.println();
		               	    System.out.println("	Nome atual: " + funcionarioExistente.getEndereco().getCidade());
		                    System.out.println("	Digite o nome da cidade:");;

		               	    String cidadeNova = scanner.nextLine();
		                 
		                    em.getTransaction().begin();
		                    funcionarioExistente.getEndereco().setCidade(cidadeNova);
		                    em.getTransaction().commit();
		                 
		                    System.out.println("	Cidade nova: " + funcionarioExistente.getEndereco().getCidade());
		                    
		                    break;
		                case 5:
		                	System.out.println();
		               	    System.out.println("	Estado atual: " + funcionarioExistente.getNome());
		                    System.out.println("	Digite o Estado:");
		               	    String estadoNovo = scanner.nextLine();
		                 
		                    em.getTransaction().begin();
		                    funcionarioExistente.getEndereco().setEstado(estadoNovo);;
		                    em.getTransaction().commit();
		                 
		                    System.out.println("	Estado novo: " + funcionarioExistente.getEndereco().getEstado());
		                    
		                    break;
                        case 6:
                            sair = true;
                            break;

                        default:
                            System.out.println("Opção inválida");
                            break;
		            } 
			 }
		} else {
			System.out.println("Funcionario não existe");
		}
		
	}
	
	public void demitirFuncionario(Long id_funcionario, Long id_empresa) {
		FuncionarioDTO funcionario = em.find(FuncionarioDTO.class, id_funcionario);
		EmpresaDTO empresa = em.find(EmpresaDTO.class, id_empresa);
        DepartamentoDAO dpDAO = new DepartamentoDAO();

		
		if(funcionario.getDepartamento().getEmpresa().getId()  != empresa.getId()) {
            throw new IllegalArgumentException("funcionario não foi encontrado nesse departamento");

		} else {
			remove(funcionario.getId());
			dpDAO.custoMensal(funcionario.getDepartamento().getId());


			
		}
		
	}
	
	public void promoverFuncionario(Long id_funcionario, Long id_departamento, Long id_empresa) {
		FuncionarioDTO funcionario = em.find(FuncionarioDTO.class, id_funcionario);
		DepartamentoDTO departamento = em.find(DepartamentoDTO.class, id_departamento);
		EmpresaDTO empresa = em.find(EmpresaDTO.class, id_empresa);
        Scanner scanner = new Scanner(System.in);

        DepartamentoDAO dpDAO = new DepartamentoDAO();
        FuncionarioDAO funDAO = new FuncionarioDAO();

        
		if(funcionario.getDepartamento().getId() != departamento.getId() || funcionario.getDepartamento().getEmpresa().getId()  != empresa.getId()) {
            throw new IllegalArgumentException("funcionario não foi encontrado nesse departamento");

		}
		else {
			System.out.println("Informe o bonus que o(a) " + funcionario.getNome() + " vai receber: ");
			double bonus = scanner.nextDouble();
			
			em.getTransaction().begin();
			GerenteDTO gerenteNovo = new GerenteDTO(funcionario, bonus);
			em.persist(gerenteNovo);
			gerenteNovo.setDepartamento(departamento);
			em.getTransaction().commit();

			dpDAO.addGerente(gerenteNovo.getId(), departamento.getId());
			funDAO.remove(funcionario.getId());
			dpDAO.custoMensal(departamento.getId());
			
			System.out.println("Gerente Criado com SUCESSO");
			System.out.println("Nome: " + gerenteNovo.getNome());
			System.out.println("Salario: " + gerenteNovo.getSalario());
			System.out.println("Departamento: " + gerenteNovo.getDepartamento().getNome());


			
		}
	
	}
	
	public FuncionarioDTO buscarID(Long id_funcionario, Long id_empresa) {
		FuncionarioDTO funcionario = em.find(FuncionarioDTO.class, id_funcionario);
		EmpresaDTO empresa = em.find(EmpresaDTO.class, id_empresa);
		
		if(funcionario.getDepartamento().getEmpresa().getId() == empresa.getId()) {
			return funcionario;
		} else {
			System.out.println("Funcionario não encontrado!");
		}
		
		return null;
	}
	
	
	
}
