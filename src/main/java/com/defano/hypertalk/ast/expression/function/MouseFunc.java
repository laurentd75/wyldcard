package com.defano.hypertalk.ast.expression.function;

import com.defano.hypertalk.ast.expression.Expression;
import com.defano.hypertalk.ast.model.Value;
import com.defano.wyldcard.WyldCard;
import com.defano.wyldcard.awt.mouse.MouseManager;
import com.defano.wyldcard.runtime.ExecutionContext;
import com.google.inject.Inject;
import org.antlr.v4.runtime.ParserRuleContext;

public class MouseFunc extends Expression {

    @Inject
    private MouseManager mouseManager;

    public MouseFunc(ParserRuleContext context) {
        super(context);
    }
    
    public Value onEvaluate(ExecutionContext context) {
        return WyldCard.getInstance().getMouseManager().isMouseDown() ? new Value("down") : new Value("up");
    }
}
