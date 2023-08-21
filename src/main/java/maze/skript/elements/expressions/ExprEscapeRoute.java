package maze.skript.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import maze.model.Cell;
import maze.model.Maze;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Maze Escape Route")
@Description("Represents the cells that make up the route from entrance to exit of a maze.")
@Examples("set {_escape::*} to escape route of {_maze}")
@Since("1.1.0")
public class ExprEscapeRoute extends SimpleExpression<Cell> {

    static {
        Skript.registerExpression(ExprEscapeRoute.class, Cell.class, ExpressionType.PROPERTY,
                "escape (route|cells) of %mazes%");
    }

    private Expression<Maze> mazes;

    @SuppressWarnings({"NullableProblems", "unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        this.mazes = (Expression<Maze>) exprs[0];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected @Nullable Cell[] get(Event event) {
        List<Cell> route = new ArrayList<>();
        for (Maze maze : this.mazes.getArray(event)) {
            route.addAll(maze.findEscapeList());
        }
        return route.toArray(new Cell[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends Cell> getReturnType() {
        return Cell.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean d) {
        return "escape route of " + this.mazes.toString(e, d);
    }

}
