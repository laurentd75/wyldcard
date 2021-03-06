package com.defano.hypertalk.ast.expression;

import com.defano.wyldcard.message.Message;
import com.defano.wyldcard.message.MessageBuilder;
import com.defano.wyldcard.part.model.PartModel;
import com.defano.wyldcard.runtime.ExecutionContext;
import com.defano.hypertalk.ast.model.Value;
import com.defano.hypertalk.ast.model.specifier.PartSpecifier;
import com.defano.hypertalk.exception.HtException;
import org.antlr.v4.runtime.ParserRuleContext;

public class UserFunctionExp extends Expression {

    public final String function;
    public final ListExp arguments;

    public UserFunctionExp(ParserRuleContext context, String function, ListExp arguments) {
        super(context);
        this.function = function;
        this.arguments = arguments;
    }

    public Value onEvaluate(ExecutionContext context) throws HtException {

        PartSpecifier ps = context.getStackFrame().getMe();
        PartModel part = context.getPart(ps);
        Message msg = MessageBuilder.named(function).withArgumentExpression(context, arguments).build();

        return part.invokeFunction(context, this, msg);
    }
}
