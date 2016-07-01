/**
 * StatIf.java
 * @author matt.defano@gmail.com
 * 
 * Encapsulation of an if-then-else statement
 */

package hypertalk.ast.statements;

import hypertalk.ast.constructs.ThenElseBlock;
import hypertalk.ast.expressions.Expression;
import hypertalk.exception.HtSyntaxException;

import java.io.Serializable;

public class StatIf extends Statement implements Serializable {
private static final long serialVersionUID = 7723910699503292875L;

	public final Expression condition;
	public final ThenElseBlock then;
	
	public StatIf (Expression condition, ThenElseBlock then) {
		this.condition = condition;
		this.then = then;
	}
	
	public void execute () throws HtSyntaxException {
		if (condition.evaluate().booleanValue())
			then.thenBranch.execute();
		else
			then.elseBranch.execute();
	}
}