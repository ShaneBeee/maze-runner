package maze.skript.elements.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import maze.model.Cell;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Cell Type")
@Description("Represents the type of a cell.")
@Examples({"loop cells of {_maze}:",
        "\tif cell type of loop-cell = wall:"})
@Since("INSERT VERSION")
public class ExprCellType extends SimplePropertyExpression<Cell, Cell.Type> {

    static {
        register(ExprCellType.class, Cell.Type.class, "cell type", "cells");
    }

    @Override
    public @Nullable Cell.Type convert(Cell cell) {
        return cell.getType();
    }

    @Override
    public @NotNull Class<? extends Cell.Type> getReturnType() {
        return Cell.Type.class;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "cell type";
    }

}
