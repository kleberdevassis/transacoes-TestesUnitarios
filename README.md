# transacoes-TestesUnitarios
# Plataforma de Transações Financeiras com Testes Unitários Abrangentes

Este projeto Java Spring Boot é uma simulação de uma plataforma de transações financeiras segura e abrangente, com uma forte ênfase na implementação de testes unitários detalhados para garantir a confiabilidade e a robustez do código. O sistema oferece uma ampla gama de funcionalidades, incluindo criação de contas de usuário, transferências de fundos, notificações, validações de segurança e manipulação de exceções.

## Estrutura e Funcionalidades Principais

A estrutura do projeto é construída com base no framework Spring e utiliza o Hibernate para operações de persistência. Os principais componentes incluem:

- **Controllers:** Responsáveis por lidar com as requisições HTTP e direcionar as chamadas aos serviços apropriados.
- **Services:** Implementam a lógica de negócios, incluindo autorização de transações, validações de usuário e manipulação de notificações.
- **Repositories:** Gerenciam as operações de persistência e consulta de dados no banco de dados.

## Testes Unitários e Cobertura

O projeto abrange uma ampla gama de testes unitários para garantir o funcionamento correto das funcionalidades em diferentes cenários. Os testes incluem:

- **Testes de Serviço:** Validam a lógica de negócios, incluindo a criação de usuários, processamento de transações e serviços de autorização.
- **Testes de Controlador:** Garantem que as rotas HTTP estejam funcionando corretamente e lidam adequadamente com os dados de entrada e saída.
- **Testes de Exceção:** Verificam se as exceções são tratadas de forma apropriada e se as respostas de erro são fornecidas conforme o esperado.

A cobertura de testes é ampla, abrangendo diferentes caminhos de execução e cenários de erro para garantir a estabilidade e confiabilidade da aplicação.

## Executando o Projeto

Para executar o projeto localmente, certifique-se de ter o Java e o Maven instalados. Clone o repositório e importe-o em sua IDE. Use o comando `mvn spring-boot:run` para iniciar a aplicação ou gere um arquivo JAR executável com `mvn clean package` e execute-o com `java -jar nome-do-arquivo.jar`. A configuração correta das variáveis de ambiente e das dependências é essencial para o funcionamento adequado do sistema.

