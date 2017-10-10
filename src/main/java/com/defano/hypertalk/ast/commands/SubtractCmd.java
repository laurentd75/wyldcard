package com.defano.hypertalk.ast.commands;

import com.defano.hypertalk.ast.expressions.Expression;
import com.defano.hypertalk.ast.statements.Command;
import com.defano.hypertalk.exception.HtException;
import com.defano.hypertalk.ast.containers.Container;
import com.defano.hypertalk.ast.common.Preposition;
import org.antlr.v4.runtime.ParserRuleContext;

public class SubtractCmd extends Command {

    private final Expression expression;
    private final Container container;

    public SubtractCmd(ParserRuleContext context, Expression source, Container container) {
        super(context, "subtract");

        this.expression = source;
        this.container = container;
    }

    public void onExecute() throws HtException {
        container.putValue(container.getValue().subtract(expression.evaluate()), Preposition.INTO);
    }
}
