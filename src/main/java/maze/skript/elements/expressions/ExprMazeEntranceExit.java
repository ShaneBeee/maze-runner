package maze.skript.elements.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import maze.model.Cell;
import maze.model.Maze;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Maze Entrance/Exit")
@Description("Represents the entrance/exit cells of a maze.")
@Examples("set {_exit} to exit cell of {_maze}")
@Since("1.1.0")
public class ExprMazeEntranceExit extends SimplePropertyExpression<Maze, Cell> {

    static {
        register(ExprMazeEntranceExit.class, Cell.class, "(entrance|:exit) cell", "mazes");
    }

    private boolean exit;

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        this.exit = parseResult.hasTag("exit");
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    public @Nullable Cell convert(Maze maze) {
        return this.exit ? maze.getExit() : maze.getEntrance();
    }

    @Override
    public @NotNull Class<? extends Cell> getReturnType() {
        return Cell.class;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return (this.exit ? "exit" : "entrance") + " cell";
    }

}
