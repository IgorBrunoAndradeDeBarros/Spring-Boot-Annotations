# Projeto: `spring-annotations`

Guia de estudo das principais anotações do Spring / Spring Boot.

### Dependências no Spring Initializr:

- [ ] Spring Web
- [ ] Spring Data JPA
- [ ] Validation
- [ ] Lombok
- [ ] Spring Boot DevTools
- [ ] H2 Database
- [ ] MySQL Driver

---

## 1. Spring Boot

- [ ] `@SpringBootApplication`
- [ ] `@EnableAutoConfiguration`
- [ ] `@ConfigurationProperties`

---

## 2. Stereotype

- [ ] `@Component`
- [ ] `@Service`
- [ ] `@Repository`
- [ ] `@Controller`
- [ ] `@RestController`

---

## 3. Beans / Injeção

- [ ] `@Autowired`
- [ ] `@Qualifier`
- [ ] `@Primary`
- [ ] `@Value`

---

## 4. Context / Configuração

- [ ] `@Configuration`
- [ ] `@ComponentScan`
- [ ] `@Bean`
- [ ] `@Lazy`
- [ ] `@Scope`
- [ ] `@PropertySource`
- [ ] `@PropertySources`
- [ ] `@Profile`

---

## 5. Spring Web / REST

- [ ] `@RestController`
- [ ] `@RequestMapping`
- [ ] `@GetMapping`
- [ ] `@PostMapping`
- [ ] `@PutMapping`
- [ ] `@DeleteMapping`
- [ ] `@PatchMapping`
- [ ] `@RequestBody`
- [ ] `@PathVariable`
- [ ] `@RequestParam`
- [ ] `@RequestHeader`
- [ ] `@CrossOrigin`

---

## 6. JPA

- [ ] `@Entity`
- [ ] `@Table`
- [ ] `@Id`
- [ ] `@GeneratedValue`
- [ ] `@Column`
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

- [ ] `@Transactional`
- [ ] `@Transactional(readOnly = true)`
- [ ] `@Transactional(rollbackFor = Exception.class)`
- [ ] `@Transactional(propagation = Propagation.REQUIRES_NEW)`
- [ ] `@Transactional(isolation = Isolation.READ_COMMITTED)`
- [ ] `@EnableTransactionManagement`

> Geralmente usada na camada de **Service**, controla commit/rollback automático de operações no banco.

---

## 8. Validation

- [ ] `@Valid`
- [ ] `@Validated`
- [ ] `@NotNull`
- [ ] `@NotBlank`
- [ ] `@NotEmpty`
- [ ] `@Size`
- [ ] `@Min`
- [ ] `@Max`
- [ ] `@Email`
- [ ] `@Pattern`
- [ ] `@Positive`
- [ ] `@PastOrPresent`
- [ ] `@FutureOrPresent`

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

## Observações finais

- `@Transactional` é essencial na camada de **Service**, não em Controller ou Repository.
- `@Async` e `@Scheduled` exigem `@EnableAsync` / `@EnableScheduling` na classe principal ou de configuração.
- `@RestControllerAdvice` é o padrão moderno para tratamento global de exceções em APIs REST.
