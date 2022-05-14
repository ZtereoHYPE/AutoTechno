import java.nio.file.Path;
import io.github.coolcrabs.brachyura.decompiler.BrachyuraDecompiler;
import io.github.coolcrabs.brachyura.decompiler.fernflower.FernflowerDecompiler;
import io.github.coolcrabs.brachyura.fabric.FabricContext;
import io.github.coolcrabs.brachyura.fabric.FabricContext.ModDependencyCollector;
import io.github.coolcrabs.brachyura.fabric.FabricLoader;
import io.github.coolcrabs.brachyura.fabric.FabricMaven;
import io.github.coolcrabs.brachyura.fabric.Intermediary;
import io.github.coolcrabs.brachyura.fabric.SimpleFabricProject;
import io.github.coolcrabs.brachyura.fabric.SimpleFabricProject.SimpleFabricContext;
import io.github.coolcrabs.brachyura.fabric.Yarn;
import io.github.coolcrabs.brachyura.maven.Maven;
import io.github.coolcrabs.brachyura.maven.MavenId;
import io.github.coolcrabs.brachyura.minecraft.Minecraft;
import io.github.coolcrabs.brachyura.minecraft.VersionMeta;
import io.github.coolcrabs.brachyura.processing.ProcessorChain;
import net.fabricmc.mappingio.tree.MappingTree;

public class Buildscript extends SimpleFabricProject {
    @Override
    protected FabricContext createContext() {
        return new IntermediaryPatcher();
    }
    @Override
    public VersionMeta createMcVersion() {
        // Minecraft Version
        return Minecraft.getVersion("1.8.9");
    }

    @Override
    public MappingTree createMappings() {
        // Uses Mojang Official Mappings
        return Yarn.ofMaven("https://maven.legacyfabric.net", FabricMaven.yarn("1.8.9+build.202203281833")).tree;
    }

    @Override
    public FabricLoader getLoader() {
        // Fabric Loader Version
        return new FabricLoader(FabricMaven.URL, FabricMaven.loader("0.13.3"));
    }

    @Override
    public String getModId() {
        // Mod Name
        return "autogg";
    }

    @Override
    public String getVersion() {
        // Mod Version
        return "0.4.0";
    }

    @Override
    public void getModDependencies(ModDependencyCollector d) {}

    @Override
    public BrachyuraDecompiler decompiler() {
        // Uses QuiltFlower instead of CFR
        return new FernflowerDecompiler(Maven.getMavenJarDep("https://maven.quiltmc.org/repository/release", new MavenId("org.quiltmc:quiltflower:1.8.1"))); 
    };

    @Override
    public Path getBuildJarPath() {
        // Changes the jar file name
        return getBuildLibsDir().resolve(getModId() + "-" + "mc" + createMcVersion().version + "-" + getVersion() + ".jar");
    }

    @Override
    public ProcessorChain resourcesProcessingChain() {
        // Adds version to fabric.mod.json
        return new ProcessorChain(super.resourcesProcessingChain(), new FmjVersionFixer(this));
    }

    public class IntermediaryPatcher extends SimpleFabricContext {
        @Override
        protected MappingTree createIntermediary() {
            // use legacy fabric intermediary
            return Intermediary.ofMaven("https://maven.legacyfabric.net", new MavenId("net.fabricmc", "intermediary", "1.8.9")).tree;
        }
    }
}
