# CRUD_JAVA_JPA
Este é um porjeto bem simples de "gerenciamento de empresas" em Java, que permite criar e interagir com funcionários, gerentes, departamentos e empresas.

Funcionalidades

Criação de funcionários, gerentes, departamentos e empresas.
Associação de funcionários a departamentos e departamentos a empresas.
Cálculo do custo mensal total de salários de todos os funcionários de um departamento.
Cálculo do custo mensal total de salários de todos os departamentos de uma empresa.

Configurações de Relacionamentos

A classe Gerente é uma subclasse da classe Funcionario, herdando os atributos básicos de um funcionário e adicionando atributos específicos, como o bônus.
A classe Departamento possui uma lista de funcionários (List<Funcionario>) para representar a relação de 1 para muitos entre departamentos e funcionários. Um departamento pode ter vários funcionários.
A classe Empresa possui uma lista de departamentos (List<Departamento>) para representar a relação de 1 para muitos entre empresas e departamentos. Uma empresa pode ter vários departamentos.


