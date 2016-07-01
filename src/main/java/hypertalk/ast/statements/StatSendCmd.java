/**
 * StatSendCmd.java
 * @author matt.defano@gmail.com
 * 
 * Encapsulation of the "send" command (for passing an event message to a part)
 */

package hypertalk.ast.statements;

import hypercard.context.GlobalContext;
import hypercard.runtime.RuntimeEnv;
import hypertalk.ast.expressions.ExpPart;
import hypertalk.ast.expressions.Expression;
import java.io.Serializable;

public class StatSendCmd extends Statement implements Serializable {
private static final long serialVersionUID = 3491353876231731415L;

	public final ExpPart part;
	public final Expression message;
	
	public StatSendCmd(ExpPart part, Expression message) {
		this.part = part;
		this.message = message;
	}
	
	public void execute () {
		try {
			GlobalContext.getContext().sendMessage(part.evaluateAsSpecifier(), message.evaluate().stringValue());
		} catch (Exception e) {
			RuntimeEnv.getRuntimeEnv().dialogSyntaxError(e);
		}
	}
}