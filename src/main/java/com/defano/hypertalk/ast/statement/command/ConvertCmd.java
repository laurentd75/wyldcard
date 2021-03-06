package com.defano.hypertalk.ast.statement.command;

import com.defano.wyldcard.runtime.ExecutionContext;
import com.defano.hypertalk.ast.model.Convertible;
import com.defano.hypertalk.ast.expression.container.ContainerExp;
import com.defano.hypertalk.ast.model.enums.Preposition;
import com.defano.hypertalk.ast.expression.Expression;
import com.defano.hypertalk.ast.statement.Command;
import com.defano.hypertalk.exception.HtException;
import com.defano.hypertalk.exception.HtSemanticException;
import com.defano.hypertalk.util.DateUtils;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Date;

public class ConvertCmd extends Command {

    private final Expression expression;
    private final Convertible from;
    private final Convertible to;

    public ConvertCmd(ParserRuleContext context, Expression expression, Convertible to) {
        this(context, expression, null, to);
    }

    public ConvertCmd(ParserRuleContext context, Expression expression, Convertible from, Convertible to) {
        super(context, "convert");

        this.expression = expression;
        this.from = from;
        this.to = to;
    }

    @Override
    public void onExecute(ExecutionContext context) throws HtException {
        Date timestamp = DateUtils.dateOf(expression.evaluate(context), from);

        if (timestamp == null) {
            throw new HtSemanticException("Invalid date.");
        }

        ContainerExp container = expression.factor(context, ContainerExp.class);
        if (container == null) {
            context.setIt(DateUtils.valueOf(timestamp, to));
        } else {
            container.putValue(context, DateUtils.valueOf(timestamp, to), Preposition.INTO);
        }
    }
}
