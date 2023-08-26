package disney.cardGame.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringDocConfig {

	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI()
			.info(new Info()
				.title("Investments API")
				.description("API for planning investments and get balances")
				.version("1.0.0"))
			.servers(List.of(
				new Server()
					.description("Desenvolvimento")
					.url("http://localhost:8080"),
				new Server()
					.description("Homologação")
					.url("https://api-test"),
				new Server()
					.description("Produção")
					.url("https://api")
			));
	}

	@Bean
	public GroupedOpenApi httpApi() {
		return GroupedOpenApi.builder()
			.group("http")
			.pathsToMatch("/**")
			.build();
	}
}


