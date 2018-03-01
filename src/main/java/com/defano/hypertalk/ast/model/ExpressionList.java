package com.defano.hypertalk.ast.model;

import com.defano.hypertalk.ast.expressions.Expression;
import com.defano.hypertalk.ast.expressions.LiteralExp;
import com.defano.hypertalk.exception.HtException;
import com.defano.hypertalk.exception.HtSemanticException;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class ExpressionList {

    private final List<Expression> list = new ArrayList<>();

    public ExpressionList() {
    }

    public ExpressionList(ParserRuleContext context, String... boundValues) {
        for (String thisValue : boundValues) {
            addArgument(new LiteralExp(context, thisValue));
        }
    }

    public ExpressionList(ParserRuleContext context, List<Value> boundValues) {
        for (Value thisValue : boundValues) {
            addArgument(new LiteralExp(context, thisValue));
        }
    }

    public ExpressionList(Expression expr) {
        list.add(expr);
    }

    public void addArgument(Expression expr) {
        list.add(expr);
    }

    /**
     * Evaluates each expression in the expression list replacing any value that could be interpreted as a point or
     * rectangle value with a discreet value for each coordinate.
     *
     * For example, given the input (1,2,3,4) this method returns a list of four values, ["1", "2", "3", "4"], whereas
     * {@link #evaluate()} returns a list containing a single, value "1,2,3,4".
     *
     * @return A list of values representing the evaluation of each expression in this ExpressionList.
     * @throws HtSemanticException Thrown if an error occurs evaluating any expression in the list.
     */
    public List<Value> evaluateDisallowingCoordinates() throws HtException {
        List<Value> evaluatedList = new ArrayList<>();

        for (Expression expr : list) {
            if (expr instanceof LiteralExp) {
                evaluatedList.addAll(expr.evaluate().getItems());
            } else {
                evaluatedList.add(expr.evaluate());
            }
        }

        return evaluatedList;
    }

    /**
     * Evaluates each expression in the expression list. Note that expression lists in HyperTalk are inherently
     * ambiguous; the list (1,2) could be interpreted as two values ("1", and "2"), or a single point value ("1,2").
     *
     * This method evaluates the expressions as they are parsed by Antlr (allowing for integer literals to be combined
     * into points and rectangles). Use {@link #evaluateDisallowingCoordinates()} when desiring simple lists.
     *
     * @return A list of values representing the evaluation of each expression in this ExpressionList.
     * @throws HtSemanticException Thrown if an error occurs evaluating any expression in the list.
     */
    public List<Value> evaluate() throws HtException {
        List<Value> evaluatedList = new ArrayList<>();

        for (Expression expr : list) {
            evaluatedList.add(expr.evaluate());
        }

        return evaluatedList;
    }
}