package com.live.callbackservice;

public class summary {
    // Java Version of Callback Management System - Kafka + Spring Boot

// 1. Dependencies (pom.xml for Maven)
/*
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
*/

    // 2. CallbackRequest Entity
    @Entity
    public class CallbackRequest {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String customerNumber;
        private String preferredTime;
        private String status;
        private LocalDateTime createdAt = LocalDateTime.now();

        // Getters and Setters
    }

    // 3. Repository
    public interface CallbackRequestRepository extends JpaRepository<CallbackRequest, Long> {
        List<CallbackRequest> findByStatus(String status);
    }

    // 4. Kafka Producer Configuration
    @Configuration
    public class KafkaProducerConfig {
        @Bean
        public ProducerFactory<String, CallbackRequest> producerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean
        public KafkaTemplate<String, CallbackRequest> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }
    }

    // 5. Producer Service
    @Service
    public class CallbackProducerService {
        private final KafkaTemplate<String, CallbackRequest> kafkaTemplate;

        public CallbackProducerService(KafkaTemplate<String, CallbackRequest> kafkaTemplate) {
            this.kafkaTemplate = kafkaTemplate;
        }

        public void sendCallbackRequest(CallbackRequest request) {
            kafkaTemplate.send("callback-topic", request);
        }
    }

    // 6. Kafka Consumer
    @Component
    public class CallbackConsumer {
        private final CallbackRequestRepository repository;

        public CallbackConsumer(CallbackRequestRepository repository) {
            this.repository = repository;
        }

        @KafkaListener(topics = "callback-topic", groupId = "callback-group")
        public void listen(CallbackRequest request) {
            request.setStatus("PENDING");
            repository.save(request);
            System.out.println("Stored callback request for: " + request.getCustomerNumber());
        }
    }

    // 7. REST Controller
    @RestController
    @RequestMapping("/callbacks")
    public class CallbackController {
        private final CallbackProducerService producerService;

        public CallbackController(CallbackProducerService producerService) {
            this.producerService = producerService;
        }

        @PostMapping
        public ResponseEntity<String> createCallback(@RequestBody CallbackRequest request) {
            producerService.sendCallbackRequest(request);
            return ResponseEntity.ok("Callback request submitted successfully");
        }
    }

// 8. application.yml
/*
spring:
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: callback-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: '*'
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
*/

    // 9. Unit Tests (using JUnit and Spring Boot Test)
    @SpringBootTest
    @AutoConfigureMockMvc
    public class CallbackControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private CallbackProducerService producerService;

        @Test
        public void testCreateCallback() throws Exception {
            String json = "{\"customerNumber\":\"1234567890\",\"preferredTime\":\"10:00 AM\"}";

            mockMvc.perform(post("/callbacks")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Callback request submitted successfully"));

            verify(producerService, times(1)).sendCallbackRequest(any(CallbackRequest.class));
        }
    }

}
