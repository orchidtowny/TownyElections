package site.remlit.townyelections;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage")
public class TownyElectionsPluginLoader implements PluginLoader {

	@Override
	public void classloader(@NotNull PluginClasspathBuilder classpathBuilder) {
		MavenLibraryResolver resolver = new MavenLibraryResolver();

		// - DEPENDENCIES ---
		resolver.addDependency(new Dependency(new DefaultArtifact("org.jetbrains.kotlin:kotlin-stdlib-jdk8"), null));

		resolver.addDependency(new Dependency(new DefaultArtifact("org.spongepowered:configurate-yaml:4.2.0"), null));
		resolver.addDependency(new Dependency(new DefaultArtifact("org.spongepowered:configurate-extra-kotlin:4.2.0"), null));

		resolver.addDependency(new Dependency(new DefaultArtifact("org.hibernate.orm:hibernate-core:7.4.0.Final"), null));
		resolver.addDependency(new Dependency(new DefaultArtifact("org.hibernate.orm:hibernate-hikaricp:7.4.0.Final"), null));
		resolver.addDependency(new Dependency(new DefaultArtifact("jakarta.transaction:transaction-api:2.0.1"), null));

		resolver.addDependency(new Dependency(new DefaultArtifact("org.postgresql:postgresql:42.7.11"), null));
		resolver.addDependency(new Dependency(new DefaultArtifact("com.mysql:mysql-connector-j:9.7.0"), null));

		// - REPOS ---
		resolver.addRepository(
				new RemoteRepository.Builder(
						"paper",
						"default",
						"https://repo.papermc.io/repository/maven-public/"
				).build()
		);

		classpathBuilder.addLibrary(resolver);
	}

}
