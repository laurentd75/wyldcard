package com.defano.hypertalk.ast.expression.part;

import com.defano.hypertalk.ast.expression.Expression;
import com.defano.hypertalk.ast.model.enums.Owner;
import com.defano.hypertalk.ast.model.enums.PartType;
import com.defano.hypertalk.ast.expression.container.PartExp;
import com.defano.hypertalk.ast.model.specifier.PartIdSpecifier;
import com.defano.hypertalk.ast.model.specifier.PartSpecifier;
import com.defano.hypertalk.exception.HtException;
import com.defano.wyldcard.runtime.ExecutionContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class PartIdExp extends PartExp {

    public final Owner layer;
    public final PartType type;
    public final Expression id;

    public PartIdExp(ParserRuleContext context, PartType type, Expression id) {
        this(context, null, type, id);
    }

    public PartIdExp(ParserRuleContext context, Owner layer, PartType type, Expression id) {
        super(context);
        this.layer = layer;
        this.type = type;
        this.id = id;
    }
    
    public PartSpecifier evaluateAsSpecifier(ExecutionContext context) throws HtException {
        return new PartIdSpecifier(layer, type, id.evaluate(context).integerValue());
    }
    
}
