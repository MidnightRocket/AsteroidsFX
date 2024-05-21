package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.ICollisionDetectionService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.ServiceLoader;

@Configuration
public class ModuleConfig {
	private static <S> Collection<S> loadServices(final Class<S> serviceClass) {
		return ServiceLoader.load(serviceClass).stream().map(ServiceLoader.Provider::get).toList();
	}

	@Bean
	public Game game() {
		return new Game(gamePluginServices(), entityProcessingServiceList(), collisionDetectionServices());
	}

	@Bean
	public Collection<IEntityProcessingService> entityProcessingServiceList() {
		return loadServices(IEntityProcessingService.class);
	}

	@Bean
	public Collection<IGamePluginService> gamePluginServices() {
		return loadServices(IGamePluginService.class);
	}

	@Bean
	public ICollisionDetectionService collisionDetectionServices() {
		return ServiceLoader.load(ICollisionDetectionService.class).findFirst().orElse(new ICollisionDetectionService.Default());
	}
}
