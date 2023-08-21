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
import maze.model.Maze;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Maze - Create")
@Description({"Create a new maze. Height/Width needs to be greater than 3 and should be odd numbers.",
        "The first pattern (just size) will create a maze with the same height/width."})
@Examples({"set {_maze} to new maze with height 11 and width 15",
        "set {_maze} to new maze with size 15"})
@Since("1.1.0")
public class ExprMaze extends SimpleExpression<Maze> {

    static {
        Skript.registerExpression(ExprMaze.class, Maze.class, ExpressionType.COMBINED,
                "[a] [new] maze with size %number%",
                "[a] [new] maze with height %number% and width %number%");
    }

    private Expression<Number> height;
    private Expression<Number> width;

    @SuppressWarnings({"NullableProblems", "unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        this.height = (Expression<Number>) exprs[0];
        this.width = (Expression<Number>) exprs[matchedPattern];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected @Nullable Maze[] get(Event event) {
        Number heightNum = this.height.getSingle(event);
        Number widthNum = this.width.getSingle(event);
        if (heightNum == null || widthNum == null) return null;

        int height = heightNum.intValue();
        int width = widthNum.intValue();
        if (height < 3 || width < 3) return null;

        Maze maze = new Maze(height, width);
        // setup cells to properly show escape
        maze.findEscape();
        return new Maze[]{maze};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Maze> getReturnType() {
        return Maze.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean d) {
        return "maze with height " + this.height.toString(e, d) + " and width " + this.width.toString(e, d);
    }

}
