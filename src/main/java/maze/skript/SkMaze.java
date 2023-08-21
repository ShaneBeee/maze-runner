package maze.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

@SuppressWarnings("unused")
public class SkMaze extends JavaPlugin {

    private static SkMaze INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        try {
            SkriptAddon addon = Skript.registerAddon(this);
            addon.loadClasses("maze.skript.elements");
            addon.setLanguageFileDirectory("lang");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
    }

    public static SkMaze getInstance() {
        return INSTANCE;
    }

}
