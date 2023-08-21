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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Cell Column/Row")
@Description("Represents the column/row of a cell (essentially the offset from the base).")
@Examples("set {_r} to cell row of {_cell}")
@Since("1.1.0")
public class ExprCellColumnRow extends SimplePropertyExpression<Cell, Number> {

    static {
        register(ExprCellColumnRow.class, Number.class, "cell (column|:row)", "cells");
    }

    private boolean row;

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        this.row = parseResult.hasTag("row");
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    public @Nullable Number convert(Cell cell) {
        return this.row ? cell.getRow() : cell.getColumn();
    }

    @Override
    public @NotNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return this.row ? "cell row" : "cell column";
    }

}
