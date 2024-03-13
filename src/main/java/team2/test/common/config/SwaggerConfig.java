package team2.test.common.config;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class SwaggerConfig {
  // @Bean
  // public ObjectMapper objectMapper() {
  //   var objectMapper = new ObjectMapper();
  //   objectMapper.registerModule(new Jdk8Module());
  //   objectMapper.registerModule(new JavaTimeModule());
  //   // 모르는 json field 무시
  //   objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  //   objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

  //   // 날짜 관련 직렬화
  //   objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  //   objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
  //   return objectMapper;
  // }

  // @Bean
  // @DependsOn({"objectMapper"})
  // public ModelResolver modelResolver(ObjectMapper objectMapper) {
  //   return new ModelResolver(objectMapper);
  // }

  // @Bean
  // public Docket api() {
  //   return new Docket(DocumentationType.OAS_30)
  //       .useDefaultResponseMessages(false)
  //       .apiInfo(apiInfo())
  //       .select()
  //       .apis(RequestHandlerSelectors.basePackage("team2.test"))
  //       .paths(PathSelectors.any())
  //       .build();
  // }

  // public ApiInfo apiInfo() {
  //   return new ApiInfoBuilder()
  //       .title("K-Food API Document")
  //       .description("K-Food Rest API Document")
  //       .version("0.0.1")
  //       .build();
  // }

  // @Bean
  // public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
  //   return new BeanPostProcessor() {

  //     @Override
  //     public Object postProcessAfterInitialization(Object bean, String beanName)
  //         throws BeansException {
  //       if (bean instanceof WebMvcRequestHandlerProvider
  //           || bean instanceof WebFluxRequestHandlerProvider) {
  //         customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
  //       }
  //       return bean;
  //     }

  //     private <T extends RequestMappingInfoHandlerMapping> void
  // customizeSpringfoxHandlerMappings(
  //         List<T> mappings) {
  //       List<T> copy =
  //           mappings.stream()
  //               .filter(mapping -> mapping.getPatternParser() == null)
  //               .collect(Collectors.toList());
  //       mappings.clear();
  //       mappings.addAll(copy);
  //     }

  //     @SuppressWarnings("unchecked")
  //     private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
  //       try {
  //         Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
  //         field.setAccessible(true);
  //         return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
  //       } catch (IllegalArgumentException | IllegalAccessException e) {
  //         throw new IllegalStateException(e);
  //       }
  //     }
  //   };
  // }
}
