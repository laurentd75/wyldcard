package com.defano.hypertalk.ast.statement.command;

import com.defano.hypertalk.ast.expression.Expression;
import com.defano.hypertalk.ast.model.enums.ArrowDirection;
import com.defano.hypertalk.ast.statement.Statement;
import com.defano.hypertalk.exception.HtException;
import com.defano.wyldcard.awt.keyboard.RoboticTypist;
import com.defano.wyldcard.runtime.ExecutionContext;
import com.google.inject.Inject;
import org.antlr.v4.runtime.ParserRuleContext;

public class ArrowKeyCmd extends Statement {

    @Inject
    private RoboticTypist roboticTypist;

    private final Expression directionExpr;

    public ArrowKeyCmd(ParserRuleContext context, Expression directionExpr) {
        super(context);
        this.directionExpr = directionExpr;
    }

    @Override
    protected void onExecute(ExecutionContext context) throws HtException {
        roboticTypist.type(ArrowDirection.fromValue(directionExpr.evaluate(context)));
    }
}
