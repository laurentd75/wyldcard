package com.defano.hypertalk.ast.statements.commands;

import com.defano.hypertalk.ast.model.SortDirection;
import com.defano.hypertalk.ast.model.Value;
import com.defano.hypertalk.ast.model.Preposition;
import com.defano.hypertalk.ast.expressions.containers.ContainerExp;
import com.defano.hypertalk.ast.expressions.Expression;
import com.defano.hypertalk.ast.statements.Command;
import com.defano.hypertalk.comparator.ExpressionValueComparator;
import com.defano.hypertalk.ast.model.SortStyle;
import com.defano.hypertalk.comparator.ValueComparator;
import com.defano.hypertalk.exception.HtException;
import com.defano.hypertalk.ast.model.ChunkType;
import com.defano.hypertalk.exception.HtSemanticException;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class SortCmd extends Command {

    public final SortDirection direction;
    public final ChunkType chunkType;
    public final Expression container;
    public final Expression expression;
    public final SortStyle sortStyle;

    public SortCmd(ParserRuleContext context, Expression container, ChunkType chunkType, Expression expression, SortDirection direction, SortStyle sortStyle) {
        super(context, "sort");

        this.container = container;
        this.chunkType = chunkType;
        this.expression = expression;
        this.direction = direction;
        this.sortStyle = sortStyle;
    }

    public SortCmd(ParserRuleContext context, Expression container, ChunkType chunkType, SortDirection direction, SortStyle sortStyle) {
        this(context, container, chunkType, null, direction, sortStyle);
    }

    public void onExecute() throws HtException {
        ContainerExp factor = container.factor(ContainerExp.class, new HtSemanticException("Can't sort that."));
        List<Value> items = getItemsToSort(factor);

        // Sort by direction
        if (expression == null) {
            items.sort(new ValueComparator(direction, sortStyle));
        }

        // Sort by expression
        else {
            items.sort(new ExpressionValueComparator(expression, direction, sortStyle));
        }

        putSortedItems(factor, items);
    }

    private void putSortedItems(ContainerExp container, List<Value> sortedItems) throws HtException {
        switch (chunkType) {
            case WORD:
                container.putValue(Value.ofWords(sortedItems), Preposition.INTO);
                break;
            case LINE:
                container.putValue(Value.ofLines(sortedItems), Preposition.INTO);
                break;
            case ITEM:
                container.putValue(Value.ofItems(sortedItems), Preposition.INTO);
                break;
            case CHAR:
                container.putValue(Value.ofChars(sortedItems), Preposition.INTO);
                break;

            default:
                throw new HtSemanticException("Can't sort by that.");
        }
    }

    private List<Value> getItemsToSort(ContainerExp container) throws HtException {
        switch (chunkType) {
            case WORD:
                return container.evaluate().getWords();
            case ITEM:
                return container.evaluate().getItems();
            case LINE:
                return container.evaluate().getLines();
            case CHAR:
                return container.evaluate().getChars();

            default:
                throw new HtSemanticException("Can't sort by that.");
        }

    }

}
