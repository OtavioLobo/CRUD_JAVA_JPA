package SRC;

import java.util.List;
import java.util.Scanner;

import DAO.DepartamentoDAO;
import DAO.EmpresaDAO;
import DAO.FuncionarioDAO;
import DAO.GenericDAO;
import DTO.DepartamentoDTO;
import DTO.EmpresaDTO;
import DTO.FuncionarioDTO;
import DTO.GerenteDTO;

public class Main {
	
	public static void main(String[] args) {
        // Realize interações com o usuário
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;
        
		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
		EmpresaDAO empresaDAO = new EmpresaDAO();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		GenericDAO<GerenteDTO> gerenteDAO = new GenericDAO<>(GerenteDTO.class);


        while (!sair) {
        	System.out.println();
            System.out.println("Escolha uma opção:");
            System.out.println("1. Criar uma empresa");
            System.out.println("2. Acessar minha empresa");
            System.out.println("3. Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome da empresa:");
                    String nomeEmpresa = scanner.next();
                    
                    EmpresaDTO empresaNova = new EmpresaDTO(nomeEmpresa);
                    
                    empresaDAO.addAtomico(empresaNova);                    
                    System.out.println("EMPRESA ID: " +  empresaNova.getId());
                    System.out.println("NOME: " +  empresaNova.getNome());
                    System.out.println();
                    System.out.println("EMPRESA CRIADA COM SUCESSO");

                    
                    break;

                case 2:
                	System.out.println("Digite o ID da sua empresa: ");
                    Long AcessoEmpresaID = scanner.nextLong();
                   
                	EmpresaDTO AcessoEmpresa = empresaDAO.buscarID(AcessoEmpresaID);
                    System.out.println("---EMPRESA #" + AcessoEmpresa.getId() + "---");
                    System.out.println("Nome: " + AcessoEmpresa.getNome());
                    System.out.println("Gasto Mensal: " + AcessoEmpresa.getGastoMensal());
                    
                    boolean sair2 = false;
                    
                    while (!sair2) {
                    	System.out.println();
                        System.out.println("-------------------:");
                        System.out.println("# CREATE #");
                        System.out.println("(1) Criar um novo departamento");
                        System.out.println("(2) Adicionar funcionário a um departamento");
                        System.out.println();

                        System.out.println("# READ #");
                        System.out.println("(3) Ver os departamento da empresa");
                        System.out.println("(4) Ver os funcionarios de um departamento");
                        System.out.println("(5) Procurar um funcionario");

                        System.out.println();
                        
                        System.out.println("# UPDATE #");
                        System.out.println("(6) Atualizar os dados da Empresa");
                        System.out.println("(7) Atualizar os dados de um Departamento");
                        System.out.println("(8) Atualizar os dados de um funcionario");
                        System.out.println("(9) Promover um funcionario a Gerente");


                        System.out.println();

                        System.out.println("# DELETE #");
                        System.out.println("(10) Excluir um funcionario de um Departamento");

                        System.out.println();

                        System.out.println("# VOLTAR #");
                        System.out.println("(11) Voltar");
                        System.out.println();

                        System.out.println("-------------------:");

                        int escolha2 = scanner.nextInt();

                        switch (escolha2) {
                            case 1:
                                System.out.println("Digite o nome do departamento:");
                                String nomeDepartamento = scanner.next();
                                
                                DepartamentoDTO departamentoNovo = new DepartamentoDTO(nomeDepartamento);
                                
                                departamentoDAO.addAtomico(departamentoNovo);
                                empresaDAO.addDepartamentoEmpresa(departamentoNovo.getId(), AcessoEmpresaID);

                                System.out.println("DEPARTAMENTO ID: " +  departamentoNovo.getId());
                                System.out.println("NOME: " +  departamentoNovo.getNome());
                                System.out.println();
                                System.out.println("DEPARTAMENTO CRIADO COM SUCESSO");
                                break;

                            case 2:
                                System.out.println("Digite o nome do Funcionario:");
                                String nomeFuncionarioNovo = scanner.next();
                                
                                System.out.println("Digite o salario do Funcionario:");
                                double salarioFuncionarioNovo = scanner.nextDouble();
                                
                                System.out.println("---ENDEREÇO---");
                                System.out.println("Rua: ");
                                String ruaFuncionarioNovo = scanner.next();
                                
                                System.out.println("Cidade: ");
                                String cidadeFuncionarioNovo = scanner.nextLine();
                                
                                System.out.println("Estado: ");
                                String estadoFuncionarioNovo = scanner.nextLine();
                                System.out.println("--------------");

                            	
                            	FuncionarioDTO funcionarioNovo = new FuncionarioDTO(nomeFuncionarioNovo, salarioFuncionarioNovo, ruaFuncionarioNovo, cidadeFuncionarioNovo, estadoFuncionarioNovo);
                            	
                                System.out.println("---Digite o id do departamento a qual deseja add o funcionario: ");
                                Long idDep = scanner.nextLong();
                                
                                
                                funcionarioDAO.addAtomico(funcionarioNovo);
                                departamentoDAO.addFuncionarioDepartamento(funcionarioNovo, idDep);
                                departamentoDAO.custoMensal(idDep);
                                empresaDAO.custoMensal(AcessoEmpresaID);
                                
                                break;
                            case 3:
                                System.out.println("---DEPARTAMENTOS-- ");
                                for(DepartamentoDTO dp: AcessoEmpresa.getDepartamentos()) {
                                	System.out.println();
                                    System.out.println("DEPARTAMENTO #" + dp.getId() + "---");
                                    System.out.println("Nome: " + dp.getNome());
                                   
                                    if (dp.getGerente() != null ) System.out.println("Gerente: " + dp.getGerente().getNome());
                                	
                                    System.out.println("Gasto Mensal: " + dp.getGastoMensal());
                                	System.out.println();

                                }
                                
                                break;                                
                            case 4:
                                System.out.println("---Digite o id do departamento: ");
                                Long idDepartamentoConsulta = scanner.nextLong();
                                
                                List<FuncionarioDTO> funcionarios = funcionarioDAO.consultar("FuncionariosDepartamento", "departamentoId", idDepartamentoConsulta);
				            		
				            	for(FuncionarioDTO fun: funcionarios) {
				            			System.out.println();
				            			System.out.println(fun.getNome());
				            		}
                                
                                break;
                            case 5:
                                System.out.println("---Digite o id do funcionario: ");
                                Long idFuncionarioConsulta = scanner.nextLong();
                                                       
                                FuncionarioDTO funcionarioConsulta = funcionarioDAO.buscarID(idFuncionarioConsulta, AcessoEmpresaID);
                                
                                if(funcionarioConsulta != null) {
                                	System.out.println("Nome: " + funcionarioConsulta.getNome());
                                    System.out.println("Salario: " + funcionarioConsulta.getSalario());
                                    System.out.println("Departamento: " + funcionarioConsulta.getDepartamento().getNome());
                                    System.out.println();
                                    
                                    break;
                                }

                                
                                break;
                            case 6:
                                empresaDAO.atualizarDados(AcessoEmpresaID);
                                
                                break;
                            case 7:
                                System.out.println("Digite o id do Departamento: ");
                                Long idDepartamentoDados = scanner.nextLong();
                                departamentoDAO.atualizarDados(idDepartamentoDados, AcessoEmpresaID);
                       
                                
                                break;
                            case 8:
                                System.out.println("Digite o id do funcionario: ");
                                Long idFuncionarioDados = scanner.nextLong();
                                funcionarioDAO.atualizarDados(idFuncionarioDados, AcessoEmpresaID);
                       
                            case 9:
                                System.out.println("Informe o id do funcionario que deseja promover: ");
                                Long idFuncionarioPromo = scanner.nextLong();

                                System.out.println("Informe o id do departamento que ele será gerente: ");
                                Long idDepartamentoPromo = scanner.nextLong();
                                
                                funcionarioDAO.promoverFuncionario(idFuncionarioPromo, idDepartamentoPromo, AcessoEmpresa.getId());
                                
                                break;
                            case 10:
                                System.out.println("Digite o id do funcionario que deseja excluir: ");
                                Long idFuncionarioRemove = scanner.nextLong();
                                
                                funcionarioDAO.demitirFuncionario(idFuncionarioRemove, AcessoEmpresa.getId());

                                
                                break;
                            case 11:
                                sair2 = true;
                                break;

                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                    }

                	
                	break;

                case 3:
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }

        scanner.close();
	
	}

}
