package gestion_produit_crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Chemin absolu dans le conteneur Docker
		registry.addResourceHandler("/upload/produits/**").addResourceLocations("file:/app/uploads/produits/");

	}
}
