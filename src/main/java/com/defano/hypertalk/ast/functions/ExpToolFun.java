/*
 * ExpToolFun
 * hypertalk-java
 *
 * Created by Matt DeFano on 2/19/17 3:11 PM.
 * Copyright © 2017 Matt DeFano. All rights reserved.
 */

package com.defano.hypertalk.ast.functions;

import com.defano.hypercard.context.ToolsContext;
import com.defano.hypertalk.ast.common.Value;
import com.defano.hypertalk.ast.expressions.Expression;
import com.defano.hypertalk.exception.HtSemanticException;

public class ExpToolFun extends Expression {

    @Override
    public Value evaluate() throws HtSemanticException {
        return new Value(ToolsContext.getInstance().getSelectedTool().name().toLowerCase());
    }
}