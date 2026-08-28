# Anotações já usadas no projeto `spring-annotations`

## 1. Spring Boot

### `@SpringBootApplication`
Liga tudo: ativa auto-configuração, component scan e configuração da aplicação.
📍 Exemplo: `AnnotationsApplication.java`

---

## 2. Stereotype (marcam a classe como bean gerenciado pelo Spring)

### `@Service`
Marca a classe como camada de regra de negócio.
📍 Exemplo: `ParkingSpotService.java`

### `@Repository`
Marca a interface/classe como camada de acesso a dados; ativa tradução de exceções de persistência.
📍 Exemplo: `ParkingSpotRepository.java`

### `@RestController`
É `@Controller` + `@ResponseBody`; expõe endpoints REST que retornam JSON direto.
📍 Exemplo: `ParkingSpotController.java`

### `@Component`
**Onde acontece:** na classe `ParkingSpotFormatter.java`.

**Como acontece:** quando a aplicação sobe, o Spring faz o **component scan** — varre todos os pacotes dentro (e abaixo) do pacote onde está a classe com `@SpringBootApplication` (`com.spring.boot.annotations`), procurando classes anotadas com `@Component` (ou qualquer anotação que "por dentro" seja `@Component`, como `@Service`, `@Repository`, `@Controller`). Ao achar `ParkingSpotFormatter`, o Spring cria **uma instância única** dela e guarda no container (`ApplicationContext`).

**O que acontece:**
1. Spring sobe → escaneia pacotes → encontra `@Component` em `ParkingSpotFormatter`
2. Cria a instância: `new ParkingSpotFormatter()` (por baixo dos panos) e registra no container
3. Quando `ParkingSpotService` é criado, o Spring olha o construtor dele e vê que precisa de um `ParkingSpotFormatter`
4. Como já existe um bean desse tipo no container, o Spring **injeta essa mesma instância** no construtor do `ParkingSpotService` — sem você escrever `new ParkingSpotFormatter()` em lugar nenhum
   **Por que usar `@Component` e não `@Service`/`@Repository`:** `ParkingSpotFormatter` não representa regra de negócio (Service) nem acesso a dados (Repository) — é só uma classe utilitária. `@Component` é o "genérico" pra esse tipo de caso.

**Sem o `@Component`:** o Spring nunca acharia essa classe no scan, e ao tentar injetar `ParkingSpotFormatter` no construtor de `ParkingSpotService`, a aplicação **não subiria** — erro de `NoSuchBeanDefinitionException` (bean não encontrado).
 
---

## 3. Beans / Injeção


## 3. Beans / Injeção

### `@Primary`
Quando existe mais de um bean do mesmo tipo, indica qual deve ser priorizado na injeção.
📍 Exemplo: `DateConfig.java` (no bean `objectMapper()`)

> `@Autowired` não aparece explicitamente porque você está usando **injeção via construtor**, que é a forma recomendada — o Spring injeta automaticamente sem precisar da anotação.
 
---

## O que é um Bean?

**Bean = um objeto que o Spring cria e gerencia pra você**, em vez de você fazer `new MinhaClasse()` na mão toda vez que precisa dele.

Sem Spring, você faria:

```java
public class A {
    private B b = new B(); // você cria na mão
}
```

## 4. Context / Configuração

### `@Configuration`
Marca a classe como fonte de definição de beans (métodos `@Bean`).
📍 Exemplo: `DateConfig.java`

### `@Bean`
Declara manualmente um bean dentro de uma classe `@Configuration`.
📍 Exemplo: `DateConfig.java` (método `objectMapper()`)

---

## 5. Spring Web / REST

### `@RequestMapping("/parking-spot")`
Define o path base do controller.
📍 Exemplo: `ParkingSpotController.java`

### `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
Mapeiam os métodos HTTP para os métodos Java correspondentes.
📍 Exemplo: `ParkingSpotController.java` (todos os métodos)

### `@RequestBody`
Converte o JSON do corpo da requisição em objeto Java (DTO).
📍 Exemplo: `saveParkingSpot()` e `updateParkingSpot()` em `ParkingSpotController.java`

### `@PathVariable`
Captura valores da URL (ex: `/parking-spot/{id}`).
📍 Exemplo: `getOneParkingSpot()`, `deleteParkingSpot()`, `updateParkingSpot()`

### `@CrossOrigin`
Libera requisições de outras origens (CORS).
📍 Exemplo: `ParkingSpotController.java` (topo da classe)

---

## 6. JPA

### `@Entity`
Marca a classe como uma tabela do banco.
📍 Exemplo: `ParkingSpotModel.java`

### `@Table(name = "TB_PARKING_SPOT")`
Define o nome da tabela no banco.
📍 Exemplo: `ParkingSpotModel.java`

### `@Id`
Marca o campo como chave primária.
📍 Exemplo: `ParkingSpotModel.java` (campo `id`)

### `@GeneratedValue`
Define a estratégia de geração do valor da chave primária (ex: `UUID` automático).
📍 Exemplo: `ParkingSpotModel.java`

### `@Column`
Configura detalhes da coluna: obrigatoriedade, tamanho, unicidade. Sem ela, o JPA já cria a coluna sozinho (padrão), então ela serve pra você customizar em vez de deixar no automático.
📍 Exemplo: `ParkingSpotModel.java` (todos os campos)

```java
@Column(nullable = false, unique = true, length = 10)
private String parkingSpotNumber;
```
- `nullable = false` → não deixa salvar vazio
- `unique = true` → não deixa duplicar
- `length = 10` → limita tamanho (padrão seria 255)
---

---

## 7. Transações

### `@Transactional`
**Onde acontece:** nos métodos `save()` e `delete()` de `ParkingSpotService.java`.

**Como acontece:** o Spring cria um "proxy" em volta do bean `ParkingSpotService`. Quando você chama `parkingSpotService.save(...)` de fora (ex: no Controller), na verdade quem é chamado primeiro é esse proxy, não o método real. O proxy abre uma transação no banco **antes** do método rodar, e decide o que fazer **depois** que o método termina.

**O que acontece:**
1. Proxy intercepta a chamada → abre uma transação (`BEGIN`)
2. O código do método roda normalmente (ex: `parkingSpotRepository.save(parkingSpotModel)`)
3. Se tudo correu bem → o proxy faz **commit** (salva de vez no banco)
4. Se alguma `RuntimeException` for lançada dentro do método → o proxy faz **rollback** (desfaz tudo que foi feito ali dentro, como se nada tivesse acontecido)
   **Por que importa no seu `save()`:** dentro dele você primeiro formata o número da vaga (`parkingSpotFormatter.formatCode(...)`) e só depois chama `parkingSpotRepository.save(...)`. Se o `save()` do repository falhar (ex: violação de constraint `unique = true`), o `@Transactional` garante que nenhuma alteração parcial fique salva no banco — tudo ou nada.

**Sem o `@Transactional`:** cada operação de banco dentro do método rodaria isolada, sem essa garantia de "tudo ou nada", e um erro no meio do caminho poderia deixar dado inconsistente.
 
---
## 8. Validation

### `@Valid`
Ativa a validação do objeto recebido no controller, com base nas anotações do DTO.
📍 Exemplo: `saveParkingSpot()` e `updateParkingSpot()` em `ParkingSpotController.java`

### `@NotBlank`
Garante que o campo `String` não seja nulo nem vazio (nem só espaços).
📍 Exemplo: `ParkingSpotDto.java` (quase todos os campos)

### `@Size(max = 7)`
Limita o tamanho máximo de um campo `String`.
📍 Exemplo: `ParkingSpotDto.java` (campo `licensePlateCar`)

---

## Ainda não usadas

`@Autowired`,`@EnableAutoConfiguration`, `@ConfigurationProperties`, `@Qualifier`, `@Value`, `@ComponentScan`, `@Lazy`, `@Scope`, `@PropertySource(s)`, `@Profile`, `@RequestParam`, `@RequestHeader`, `@PatchMapping`, todas de JPA relacional (`@OneToOne`, `@OneToMany` etc.), Lombok (`@Getter`, `@Setter`, `@Data` etc.), `@Validated`, demais validações (`@Min`, `@Max`, `@Email`...), `@ResponseStatus`, `@ExceptionHandler`, `@RestControllerAdvice`, assíncrono/agendamento/cache, segurança e testes.