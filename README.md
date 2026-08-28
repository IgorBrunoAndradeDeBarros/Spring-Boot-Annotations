# Projeto: `spring-annotations`

Guia de estudo das principais anotações do Spring / Spring Boot.

### Dependências no Spring Initializr:

- [x] Spring Web
- [x] Spring Data JPA
- [x] Validation
- [x] Lombok
- [x] Spring Boot DevTools
- [x] H2 Database
- [x] MySQL Driver
- [x] Jackson Datatype JSR310 (`jackson-datatype-jsr310`) — necessário para `LocalDate`/`LocalDateTime` (não vem por padrão no Initializr, adicionar manualmente no pom.xml/build.gradle)

---

## 1. Spring Boot

- [X] `@SpringBootApplication` Liga tudo: ativa auto-configuração, component scan e configuração da aplicação.
- [ ] `@EnableAutoConfiguration`
- [ ] `@ConfigurationProperties`

---

## 2. Stereotype

- [x] `@Component`  anotação genérica que diz pro Spring "gerencia essa classe pra mim
- [x] `@Service` classe como camada de regra de negócio.
- [x] `@Repository` acessar e fazer operações no banco.
- [x] `@Controller`recebe as requisições HTTP 
- [x] `@RestController` `@Controller` + `@ResponseBody`; expõe endpoints REST que retornam JSON direto.

---

## 3. Beans / Injeção

- [ ] `@Autowired`
- [ ] `@Qualifier`
- [x] `@Primary` Quando existe mais de um bean do mesmo tipo, indica qual deve ser priorizado na injeção.
- [ ] `@Value`

---

## 4. Context / Configuração

- [x] `@Configuration` Marca a classe como fonte de definição de beans
- [ ] `@ComponentScan`
- [ ] `@Bean`
- [ ] `@Lazy`
- [ ] `@Scope`
- [ ] `@PropertySource`
- [ ] `@PropertySources`
- [ ] `@Profile`

---

## 5. Spring Web / REST

- [x] `@RequestMapping` Define o path base do controller.
- [x] `@GetMapping` esponde requisição GET (buscar)
- [x] `@PostMapping` responde requisição POST (criar)
- [x] `@PutMapping` responde requisição PUT (atualizar tudo)
- [x] `@DeleteMapping` responde requisição DELETE (apagar)
- [x] `@PatchMapping` responde requisição PATCH (atualizar só parte)
- [x] `@RequestBody` pega o JSON e transforma em objeto Java.
- [x] `@PathVariable` Captura valores da URL
- [x] `@RequestParam`  um jeito de pedir um valor que vem "solto" na URL.
- [x] `@RequestHeader` pega valores enviados no cabeçalho (header) da requisição HTT
- [x] `@CrossOrigin` Libera requisições de outras origens (CORS).

---

## 6. JPA

- [x] `@Entity` Marca a classe como uma tabela do banco.
- [x] `@Table`Define o nome da tabela no banco.
- [X] `@Id ` Marca o campo como chave primária.
- [x] `@GeneratedValue` Define a estratégia de geração do valor da chave primária (ex: `UUID` automático).
- [x] `@Column` Customiza a coluna do banco.
- [x] `@Column(nullable = false)` A coluna não pode ser nula/vazia — é obrigatória.
- [x] `@Column(unique = true)` A coluna não pode ter valor repetido .
- [x] `@Column(length = 10)` Define o tamanho máximo da coluna.
- [ ] `@OneToOne`
- [ ] `@OneToMany`
- [ ] `@ManyToOne`
- [ ] `@ManyToMany`
- [ ] `@JoinColumn`
- [ ] `@JoinTable`
- [ ] `@Transient`
- [ ] `@Embedded`
- [ ] `@Embeddable`

---

## 7. Transações (MUITO IMPORTANTE)

- [x] `@Transactional` tudo ou nada: erro desfaz, sucesso salva
- [ ] `@Transactional(readOnly = true)` só leitura, sem alterar dado
- [ ] `@Transactional(rollbackFor = Exception.class)` desfaz também em exceções checked
- [ ] `@Transactional(propagation = Propagation.REQUIRES_NEW)` abre transação nova e separada
- [ ] `@Transactional(isolation = Isolation.READ_COMMITTED)` controla o que uma transação vê de outra rodando junto
- [ ] `@EnableTransactionManagement` liga o suporte a @Transactional (Spring Boot já ativa sozinho)

> Geralmente usada na camada de **Service**, controla commit/rollback automático de operações no banco.

---

## 8. Validation

- [x] `@Valid` ativa a validação de um objeto (DTO) inteiro,
- [ ] `@Validated` igual @Valid, mas com mais recursos (permite grupos de validação); usado em nível de classe
- [ ] `@NotNull` não pode ser nulo
- [x] `@NotBlank` não pode ser nulo, vazio ou só espaço
- [ ] `@NotEmpty` não pode ser nulo ou vazio
- [x] `@Size` define tamanho min/max
- [ ] `@Min` valor mínimo
- [ ] `@Max` valor máximo
- [ ] `@Email` valida formato de e-mail
- [ ] `@Pattern` valida com regex
- [ ] `@Positive` precisa ser maior que zero
- [ ] `@PastOrPresent` data hoje ou passada
- [ ] `@FutureOrPresent` data hoje ou futura

---

## 9. Lombok

- [ ] `@Getter`
- [ ] `@Setter`
- [ ] `@NoArgsConstructor`
- [ ] `@AllArgsConstructor`
- [ ] `@RequiredArgsConstructor`
- [ ] `@Builder`
- [ ] `@Data`
- [ ] `@ToString`
- [ ] `@EqualsAndHashCode`
- [ ] `@Slf4j`

---

## 10. DTO / API / Tratamento de Erros

- [ ] `@ResponseBody`
- [ ] `@ResponseStatus`
- [ ] `@ExceptionHandler`
- [ ] `@RestControllerAdvice`
- [ ] `@ControllerAdvice`

---

## 11. Assíncrono, Agendamento e Cache

- [ ] `@Async`
- [ ] `@EnableAsync`
- [ ] `@Scheduled`
- [ ] `@EnableScheduling`
- [ ] `@Cacheable`
- [ ] `@CacheEvict`
- [ ] `@CachePut`
- [ ] `@EnableCaching`

---

## 12. Segurança (Spring Security)

- [ ] `@EnableWebSecurity`
- [ ] `@PreAuthorize`
- [ ] `@PostAuthorize`
- [ ] `@Secured`
- [ ] `@RolesAllowed`

---

## 13. Testes

- [ ] `@SpringBootTest`
- [ ] `@WebMvcTest`
- [ ] `@DataJpaTest`
- [ ] `@MockBean`
- [ ] `@Mock`
- [ ] `@InjectMocks`
- [ ] `@Test`
- [ ] `@BeforeEach`
- [ ] `@AfterEach`

---

## Dicionário rápido

**Classe** → o molde/planta de um objeto (ex: `ParkingSpotFormatter`)

**Instância** → um objeto real criado a partir de uma classe (ex: `new ParkingSpotFormatter()`). A classe é a receita, a instância é o bolo pronto.

**Bean** → uma instância, só que quem cria e guarda ela é o Spring, não você. Em vez de dar `new` na mão, o Spring cria o objeto e entrega pronto pra quem precisar.

**Injeção de dependência** → quando o Spring entrega um bean pronto pra dentro de outra classe (geralmente pelo construtor), sem você precisar criar esse objeto manualmente.

**Component scan** → processo em que o Spring, ao subir a aplicação, varre os pacotes do projeto procurando classes anotadas (`@Component`, `@Service`, `@Repository`, `@Controller`) pra transformar em beans.

**Container / ApplicationContext** → o "armário" onde o Spring guarda todos os beans já criados, prontos pra serem injetados quando alguém precisar.
 
---

## Observações finais

- `@Transactional` é essencial na camada de **Service**, não em Controller ou Repository.
- `@Async` e `@Scheduled` exigem `@EnableAsync` / `@EnableScheduling` na classe principal ou de configuração.
- `@RestControllerAdvice` é o padrão moderno para tratamento global de exceções em APIs REST.
