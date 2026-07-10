# Relatório de Testes - Jornada de Cadastro e Instanciação de Livros no Catálogo


## 3. Objetivo dos testes
O objetivo dos testes implementados foi validar uma jornada de usuário relacionada ao processo de cadastro de livros no catálogo da biblioteca. Essa jornada inclui:
1. O operador ou usuário informa os dados de um novo livro.
2. O sistema valida as informações fornecidas.
3. O livro é cadastrado com sucesso quando os dados são válidos.
4. O sistema permite a criação de uma instância do livro apenas quando o livro já está presente no catálogo.
5. O sistema rejeita operações inválidas ou impossíveis de executar.

Essa jornada foi escolhida porque envolve regras de negócio importantes e não se limita a uma simples operação de CRUD.

## 4. Casos de teste implementados
### Testes unitários
Foram criados testes unitários para validar:
- cadastro de um livro válido;
- criação de uma instância de livro quando o livro já existe;
- rejeição da criação de instância quando o ISBN não está cadastrado;
- falha da operação quando os dados de entrada são inválidos.

### Testes de integração
Foram adicionados testes de integração para verificar:
- persistência do livro no repositório;
- recuperação do livro a partir do ISBN;
- fluxo completo de cadastro e criação de instância utilizando o contexto Spring.

### Testes de sistema
Foi implementado um cenário de sistema para validar a jornada completa de cadastro e instanciação em um fluxo mais próximo do uso real da aplicação.

## 5. Testes adicionados
Os testes foram implementados nos arquivos:
- src/test/groovy/io/pillopl/library/catalogue/CatalogueUserJourneyUnitTest.groovy
- src/test/groovy/io/pillopl/library/catalogue/CatalogueUserJourneySystemTest.groovy
- src/integration-test/groovy/io/pillopl/library/catalogue/CatalogueUserJourneyIT.groovy

## 6. Resultado dos testes

