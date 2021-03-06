package com.defano.hypertalk.ast.statement.command;

import com.defano.hypertalk.ast.model.enums.Position;
import com.defano.hypertalk.ast.preemption.Preemption;
import com.defano.hypertalk.ast.statement.Command;
import com.defano.hypertalk.exception.HtException;
import com.defano.wyldcard.NavigationManager;
import com.defano.wyldcard.runtime.ExecutionContext;
import com.google.inject.Inject;
import org.antlr.v4.runtime.ParserRuleContext;

public class GoPositionCmd extends Command {

    @Inject
    private NavigationManager navigationManager;

    private final Position position;

    public GoPositionCmd(ParserRuleContext context, Position position) {
        super(context, "go");
        this.position = position;
    }

    @Override
    protected void onExecute(ExecutionContext context) throws HtException, Preemption {
        switch (position) {
            case NEXT:
                navigationManager.goNextCard(context, context.getCurrentStack());
                break;
            case PREV:
                navigationManager.goPrevCard(context, context.getCurrentStack());
                break;
            case THIS:
                break;
        }
    }
}
