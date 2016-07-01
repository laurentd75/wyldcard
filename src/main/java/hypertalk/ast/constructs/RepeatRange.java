/**
 * RepeatRange.java
 * @author matt.defano@gmail.com
 * 
 * Encapsulation of the "x to y" and "x downto y" constructs (used within the
 * RepeatWith construct). 
 */

package hypertalk.ast.constructs;

import hypertalk.ast.expressions.Expression;
import java.io.Serializable;

public class RepeatRange extends RepeatSpecifier implements Serializable {
private static final long serialVersionUID = -7806110203950062655L;

	public static final boolean POLARITY_UPTO = true;
	public static final boolean POLARITY_DOWNTO = false;
	
	public final boolean polarity;
	public final Expression from;
	public final Expression to;
	
	public RepeatRange(boolean polarity, Expression from, Expression to) {
		this.polarity = polarity;
		this.from = from;
		this.to = to;
	}
}