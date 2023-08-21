package maze.skript.elements.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import maze.model.Cell;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Cell Offset")
@Description("Get the offset of a cell in Vector form.")
@Examples("set {_off} to cell offset of {_cell}")
@Since("1.1.0")
public class ExprCellOffset extends SimplePropertyExpression<Cell, Vector> {

    static {
        register(ExprCellOffset.class, Vector.class, "cell offset", "cells");
    }

    @Override
    public @Nullable Vector convert(Cell cell) {
        return new Vector(cell.getColumn(), 0, cell.getRow());
    }

    @Override
    public @NotNull Class<? extends Vector> getReturnType() {
        return Vector.class;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "cell offset";
    }

}
