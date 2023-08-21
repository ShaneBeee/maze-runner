package maze.skript.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;
import maze.model.Cell;
import maze.model.Maze;
import maze.skript.util.EnumWrapper;
import org.jetbrains.annotations.NotNull;

import java.io.StreamCorruptedException;

@SuppressWarnings("unused")
public class Types {

    static {
        Classes.registerClass(new ClassInfo<>(Maze.class, "maze")
                .user("mazes?")
                .name("Maze")
                .description("Represents a maze object.")
                .since("1.1.0")
                .parser(new Parser<>() {

                    @SuppressWarnings("NullableProblems")
                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public @NotNull String toString(Maze maze, int flags) {
                        return "Maze{height=" + maze.getHeight() + ",width=" + maze.getWidth() + "}";
                    }

                    @Override
                    public @NotNull String toVariableNameString(Maze maze) {
                        return toString(maze, 0);
                    }
                })
                .serializer(new Serializer<>() {
                    @Override
                    public @NotNull Fields serialize(Maze maze) {
                        Fields fields = new Fields();
                        fields.putObject("maze", maze.export());
                        return fields;
                    }

                    @SuppressWarnings("NullableProblems")
                    @Override
                    public void deserialize(Maze o, Fields f) {
                    }

                    @SuppressWarnings("NullableProblems")
                    @Override
                    protected Maze deserialize(Fields fields) throws StreamCorruptedException {
                        String maze = fields.getObject("maze", String.class);
                        return Maze.load(maze);
                    }

                    @Override
                    public boolean mustSyncDeserialization() {
                        return true;
                    }

                    @Override
                    protected boolean canBeInstantiated() {
                        return false;
                    }
                }));

        Classes.registerClass(new ClassInfo<>(Cell.class, "cell")
                .user("cells?")
                .name("Cell")
                .description("Represents a cell in a maze.")
                .since("1.1.0"));

        EnumWrapper<Cell.Type> CELL_TYPE_ENUM = new EnumWrapper<>(Cell.Type.class);
        Classes.registerClass(CELL_TYPE_ENUM.getClassInfo("celltype")
                .user("cell ?types?")
                .name("Cell Type")
                .description("Represents the type of a cell.")
                .since("1.1.0"));
    }

}
