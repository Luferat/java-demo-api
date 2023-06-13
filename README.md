# Java.Demo.API.1.0

API de demonstração com Java, Maven, Spring Boot e H2 Database.

## Obtendo o Eclipse

 1. [https://www.eclipse.org/downloads/packages/](https://www.eclipse.org/downloads/packages/)
 2. Eclipse IDE for Enterprise Java and Web Developers
 3. Versão atual → Eclipse IDE 2023-03 R

## Sequência de Criação

1. Criar o aplicativo em [https://start.spring.io/](https://start.spring.io/):
    - Project → Maven
    - Language → Java
    - Spring Boot → 3.1.0

2. Project Metadata:
    - Group → net.luferat.java
    - Artifact → demoapi
    - Name → demoapi
    - Description → API de demonstração com Java, Maven, Spring Boot e H2 Database.
    - Package name → net.luferat.java.demoapi
    - Packaging → Jar
    - Java → 17

3. Dependências:
    - Spring Boot DevTools
    - Lombok
    - Spring Web
    - Spring Data JPA
    - H2 Database

4. Importar como *Maven Project* no Eclipse IDE

5. Selecionar a versão do Java:
    - Window → Preferences
    - Java → Installed JREs
    - Se necessário, remover a versão atual (já vem com o Eclipse) e adicionar o JDK mais recente, por exemplo, `C:\Program Files\Java\jdk-18`
    - [Apply and Close]
    - Abrir o arquivo `pom.xml` no Eclipse
    - Se necessário, alterar o valor da chave `<java.version>17</java.version>` para a versão do Java instalada no Eclipse, por exemplo, `<java.version>18</java.version>`

> *Recomenda-se usar a versão LTS disponível (atualmente a JDK 17) em projetos finais.*

## Package de Trabalho
 - Os códigos fonte da aplicação devem ser criados à partir de `src/main/java`, no package `net.luferat.java.demoapi`.
 - Não altere a classe principal `DemoApi6Application.java`

## Criando o "model"
 - O "model" contém a modelagem dos dados no banco de dados
 - Cada tabela do banco de dados tem seu prório "model"
 - Como estamos usando JPA/Hibernate, o "model" será usado para criar as tabelas no banco de dados caso elas ainda não existam
 - Podem ser usadas diversas predefinições para criar os campos (colunas) da tabela através dos diversos *Annotations* disponíveis
 - Usando o *Lombok*, não precisamos criar os construtores, getters e setters manualmente

```
Treco.java
```
```java
package net.luferat.java.demoapi;

// Imports omitidos. Use [Ctrl]+[Shift]+[O].

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Treco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;

	@Column(length = 63)
	private String name;

	@Column(length = 127)
	private String description;

}
```

## Criando o *Repository*
 - O *Repository* é a **interface** responsável pela persistência dos dados
 - Faz a interface entre o aplicativo e o banco de dados
 
```
TrecoRepository.java
```
```java
package net.luferat.java.demoapi6;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrecoRepository extends JpaRepository<Treco, Long> {}
```

 - Observações em `JpaRepository<Treco, Long>`:
     - `Treco` → Tipo que será manipulado pelo repository
     - `Long` → Tipo da chave primária (`@Id`) 

## Criando o *Controller*
 - O *Controller* faz a conexão entre o banco de dados e a *view*
 - Neste caso, a *view* será uma *API REST* usando *JSON*
 - A API é provida pelo próprio *Spring Boot*, por meio do *Apache Toncat*
 
```
TrecoController.java
```
```java
package net.luferat.java.demoapi;

// Imports omitidos. Use [Ctrl]+[Shift]+[O].

@RestController
@RequestMapping("/trecos")
public class TrecoController {

    @Autowired
    private TrecoRepository trecoRepository;

    @GetMapping
    public List<Treco> getAll()  {
        return  trecoRepository.findAll();
    }

    @PostMapping
    public Treco post(@RequestBody Treco treco)  {
        return trecoRepository.save(treco);
    }

    @GetMapping("/{id}")
    public Treco getOne(@PathVariable Long id) {
        if  (trecoRepository.existsById(id))
            return trecoRepository.findById(id).get();
        return null;
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public String delete(@PathVariable Long id) {
        if  (trecoRepository.existsById(id)) {
            trecoRepository.deleteById(id);
            return "{\"status\":\"deleted\"}";
        }
        return "{\"status\":\"error\"}";
    }

    @PutMapping("/{id}")
    public Treco put(@PathVariable Long id, @RequestBody Treco treco) {
        return null;
    }

    @PatchMapping()
    public Treco patch(@PathVariable Long id, @RequestBody Treco treco) {
        return null;
    }

}
```

 - Observações em `net.luferat.java.demoapi.Trecos.java`:
     - Dada a complexidade, não desenvolvemos os métodos `PUT` e `PATCH` ainda
 
## Configurando a Persistência (banco de dados)
 - Abra `src/main/resources`
 
```
application.properties
```
```java
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.data.jpa.repositories.bootstrap-mode=default
```

 - Essas configurações servem para o *H2 Database* rodando na *RAM*
 - Essas configurações podem variar dependendo do banco de dados e também do sistema onde vai rodar
 - O *Spring Boot* disponibiliza um "*H2 Console*" que permite manipular o banco de dados usando *SQL*
     - `http://localhost:8080/h2-console`
     - As credenciais predefinidas na configuração são:
         -  User Name: `sa`
         - Password: `password`

## Preparando para *Rodar*
 - Em caso de erros nestes passos, revise/refatore os códigos
     - Não resolvendo, peça ajuda
 - Clique direito no projeto → `Run As → Maven install
 - Clique direito no projeto → `Run As → Run Configurations...
 - Localize e abra `Maven Build`
     - Se houverem pontos de execução abaixo de `Maven Build`, remova todos
 - Clique direito em `Maven Build` → New Configuration:
     - Name: `demoapi-run-1`
     - Base directory: `Digite/cole/acesse a pasta raiz do projeto no sistema`
     - Goals: `spring-boot:run`
     - Clique em [Run] para *rodar* o aplicativo
 - Para rodar outras vezes, por exemplo, após alterações, pare qualquer instância do aplicativo:
     - Clique direito no console → Terminate / Disconnect All (*Repita enquanto estiver vermelho*)
     - Clique direito no console → Remove All Terminated
     - Na barra de ferramentas, clique na setinha para baixo 🔽 ao lado do `Play` (▶) 
     - Clique em `demoapi-run-1` 

##### Observações sobre *Lombok*:
Ao fazer alterações nas *@Annotations* do *Lombok*, este pode parar de funcionar, não gerando o código legado. 
Percebe-se quando os métodos HTTP param de funcionar.

Para resolver:

 - Clique direito no console → Terminate / Disconnect All (*Repita enquanto estiver vermelho*)
 - Clique direito no console → Remove All Terminated
 - Clique direito no projeto → Run As → Maven clean
 - Rode o aplicativo novamente
    
---
<small><em>2023 By Luferat</em><br>
Licensed By Creative Commons</small>
