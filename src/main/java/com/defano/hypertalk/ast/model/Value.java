package com.defano.hypertalk.ast.model;

import com.defano.hypertalk.comparator.StyledComparable;
import com.defano.hypertalk.exception.HtException;
import com.defano.hypertalk.exception.HtSemanticException;
import com.defano.hypertalk.utils.ChunkUtils;
import com.defano.hypertalk.utils.DateUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representation of value in HyperTalk; all values are stored internally
 * as Strings and converted to integers, floats or booleans as required by
 * the expression.
 *
 * The Value object is immutable; once created it cannot change value.
 */
public class Value implements StyledComparable<Value> {

    private final String value;

    // Cache for known value types (all are effectively final)
    private Long longValue;
    private Double floatValue;
    private Boolean booleanValue;

    public static Value ofLines(List<Value> lines) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < lines.size(); index++) {
            builder.append(lines.get(index));

            if (index < lines.size() - 1) {
                builder.append("\n");
            }
        }

        return new Value(builder.toString());
    }

    public static Value ofItems(List<Value> items) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < items.size(); index++) {
            builder.append(items.get(index));

            if (index < items.size() - 1) {
                builder.append(",");
            }
        }

        return new Value(builder.toString());
    }

    public Value () {
        this("");
    }

    public Value (Point p) {
        this(p.x + "," + p.y);
    }

    public Value (Rectangle r) {
        this(r.x + "," + r.y + "," + (r.x + r.width) + "," + (r.y + r.height));
    }

    public Value (Object v) {
        this(v == null ? "" : String.valueOf(v));
    }

    public Value (long v) {
        this(String.valueOf(v));
        longValue = v;
    }
    
    public Value (double f) {
        this(String.valueOf(f));
        floatValue = f;
    }
    
    public Value (boolean v) {
        this(String.valueOf(v));
        booleanValue = v;
    }

    public Value (int x, int y) {
        this(String.valueOf(x) + "," + String.valueOf(y));
    }

    public Value (int x1, int y1, int x2, int y2) {
        this(String.valueOf(x1) + "," + String.valueOf(y1) + "," + String.valueOf(x2) + "," + String.valueOf(y2));
    }

    public Value (char c) {
        this(String.valueOf(c));
    }

    public Value (String value) {
        this.value = value == null ? "" : value;

        // Special case: empty string is a valid int and float
        if (value == null || value.trim().equals("")) {
            longValue = 0L;
            floatValue = 0.0;
            booleanValue = null;
        }
    }
    
    public boolean isInteger () {
        return longValue != null || parseLong() != null;
    }

    public boolean isFloat () {
        return floatValue != null || parseFloat() != null;
    }    
    
    public boolean isNatural () {
        return isInteger() && longValue() >= 0;
    }    

    public boolean isBoolean () {
        return booleanValue != null || parseBoolean() != null;
    }    
    
    public boolean isNumber () {
        return isFloat();
    }

    private Long parseLong() {
        try {
            longValue = Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            longValue = null;
        }

        return longValue;
    }

    private Double parseFloat() {
        try {
            floatValue = Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            floatValue = null;
        }

        return floatValue;
    }

    private Boolean parseBoolean() {
        if (value.trim().equalsIgnoreCase("true")) {
            booleanValue = true;
        } else if (value.trim().equalsIgnoreCase("false")) {
            booleanValue = false;
        } else {
            booleanValue = null;
        }

        return booleanValue;
    }

    public boolean isPoint () {
        List<Value> listValue = getItems();

        return listValue.size() == 2 &&
                new Value(listValue.get(0)).isInteger() &&
                new Value(listValue.get(1)).isInteger();
    }

    public boolean isRect () {
        List<Value> listValue = getItems();

        return listValue.size() == 4 &&
                new Value(listValue.get(0)).isInteger() &&
                new Value(listValue.get(1)).isInteger() &&
                new Value(listValue.get(2)).isInteger() &&
                new Value(listValue.get(3)).isInteger();
    }

    public String stringValue () {
        return value;
    }    

    public int integerValue() {
        if (longValue != null || parseLong() != null) {
            return longValue.intValue();
        } else {
            return 0;
        }
    }

    public long longValue() {
        if (longValue != null || parseLong() != null)
            return longValue;
        else
            return 0;
    }
        
    public double doubleValue() {
        if (floatValue != null || parseFloat() != null)
            return floatValue;
        else
            return 0.0f;
    }
        
    public boolean booleanValue () {
        if (booleanValue != null || parseBoolean() != null)
            return booleanValue;
        else
            return false;
    }

    public boolean checkedBooleanValue() throws HtException {
        if (isBoolean()) {
            return booleanValue();
        } else {
            throw new HtSemanticException("Expected true or false here.");
        }
    }

    public Rectangle rectangleValue() {
        if (isRect()) {
            int left = getItemAt(0).integerValue();
            int top = getItemAt(1).integerValue();
            int height = getItemAt(3).integerValue() - getItemAt(1).integerValue();
            int width = getItemAt(2).integerValue() - getItemAt(0).integerValue();

            return new Rectangle(left, top, width, height);
        }

        return new Rectangle();
    }

    public Point pointValue() {
        if (isPoint()) {
            int left = getItemAt(0).integerValue();
            int top = getItemAt(1).integerValue();

            return new Point(left, top);
        }

        return new Point();
    }

    public List<Value> getItems() {
        List<Value> list = new ArrayList<>();
        
        for (String item : value.split(","))
            list.add(new Value(item));
            
        return list;
    }

    public Value getItemAt(int index) {
        if (getItems().size() > index) {
            return new Value(getItems().get(index));
        } else {
            return new Value();
        }
    }

    public List<Value> getLines() {
        List<Value> list = new ArrayList<>();

        for (String thisLine : value.split("\n")) {
            list.add(new Value(thisLine));
        }

        return list;
    }

    public List<Value> getWords() {
        ArrayList<Value> words = new ArrayList<>();
        for (int i = 1; i <= ChunkUtils.getCount(ChunkType.WORD, stringValue()); i++) {
            words.add(new Value(ChunkUtils.getChunk(ChunkType.WORD, this.stringValue(), i, i)));
        }
        return words;
    }

    public int itemCount () {
        return ChunkUtils.getCount(ChunkType.ITEM, value);
    }
    
    public int wordCount () {
        return ChunkUtils.getCount(ChunkType.WORD, value);
    }
    
    public int charCount () {
        return ChunkUtils.getCount(ChunkType.CHAR, value);
    }
    
    public int lineCount () {
        return ChunkUtils.getCount(ChunkType.LINE, value);
    }
    
    public Value getChunk (Chunk c) throws HtException {

        Value startVal = null;
        Value endVal = null;
        
        int startIdx = 0;
        int endIdx = 0;
        
        if (c.start != null)
            startVal = c.start.evaluate();
        if (c.end != null)
            endVal = c.end.evaluate();

        if (startVal == null || !startVal.isNatural() && !Ordinal.reservedValue(startVal.integerValue()))
            throw new HtSemanticException("Chunk specifier requires natural integer value, but got '" + startVal + "' instead.");
        if (endVal != null && !endVal.isNatural() && !Ordinal.reservedValue(endVal.integerValue()))
            throw new HtSemanticException("Chunk specifier requires natural integer value, but got '" + endVal + "' instead.");

        startIdx = startVal.integerValue();
        if (endVal != null)
            endIdx = endVal.integerValue();

        Value chunkValue = new Value(ChunkUtils.getChunk(c.type, value, startIdx, endIdx));

        // If a composite chunk; evaluate right hand of the expression first
        if (c instanceof CompositeChunk) {
            return chunkValue.getChunk(((CompositeChunk) c).chunkOf);
        } else {
            return chunkValue;
        }
    }

    public static Value setChunk (Value mutable, Preposition p, Chunk c, Object mutator) throws HtException {

        if (c instanceof CompositeChunk) {
            return new Value(ChunkUtils.putCompositeChunk((CompositeChunk) c, p, mutable.stringValue(), String.valueOf(mutator)));
        }

        String mutatorString = mutator.toString();
        String mutableString = mutable.toString();

        Value startVal = null;
        Value endVal = null;

        int startIdx = 0;
        int endIdx = 0;
        
        if (c.start != null)
            startVal = c.start.evaluate();
        if (c.end != null)
            endVal = c.end.evaluate();

        if (startVal == null || !startVal.isNatural() && !Ordinal.reservedValue(startVal.integerValue()))
            throw new HtSemanticException("Chunk specifier requires natural integer value, but got '" + startVal + "' instead.");
        if (endVal != null && !endVal.isNatural() && !Ordinal.reservedValue(endVal.integerValue()))
            throw new HtSemanticException("Chunk specifier requires natural integer value, but got '" + endVal + "' instead.");

        startIdx = startVal.integerValue();
        if (endVal != null)
            endIdx = endVal.integerValue();

        return new Value(ChunkUtils.putChunk(c.type, p, mutableString, startIdx, endIdx, mutatorString));
    }
    
    public static Value setValue (Value mutable, Preposition p, Value mutator) {
        
        switch (p) {
        case BEFORE:    return new Value(mutator.toString() + mutable.toString());
        case INTO:        return new Value(mutator.toString());
        case AFTER:        return new Value(mutable.toString() + mutator.toString());
        default: throw new RuntimeException("Bug! Unhandled preposition.");
        }
    }
    
    public boolean isEmpty () {
        return value.equals("");
    }

    public Value lessThan (Object val) {
        Value v = new Value(val);
        if (isFloat() && v.isFloat())
            return new Value(doubleValue() < v.doubleValue());
        else
            return new Value(toString().compareTo(v.toString()) < 0);
    }

    public Value greaterThan (Object val) {
        Value v = new Value(val);
        if (isFloat() && v.isFloat())
            return new Value(doubleValue() > v.doubleValue());
        else
            return new Value(toString().compareTo(v.toString()) > 0);
    }
    
    public Value greaterThanOrEqualTo (Object val) {
        Value v = new Value(val);
        if (isFloat() && v.isFloat())
            return new Value (doubleValue() >= v.doubleValue());
        else
            return new Value(toString().compareTo(v.toString()) >= 0);
    }
    
    public Value lessThanOrEqualTo (Object val) {
        Value v = new Value(val);
        if (isFloat() && v.isFloat())
            return new Value(doubleValue() <= v.doubleValue());
        else
            return new Value(toString().compareTo(v.toString()) <= 0);
    }
    
    public Value multiply (Object val) throws HtSemanticException {
        Value v = new Value(val);
        if (!isNumber() || !v.isNumber()) {
            throw new HtSemanticException("The value '" + value + "' cannot be multiplied by '" + v + "'.");
        }

        try {
            if (isInteger() && v.isInteger())
                return new Value(Math.multiplyExact(longValue(), v.longValue()));
            else
                return new Value(doubleValue() * v.doubleValue());
        } catch (ArithmeticException e) {
            throw new HtSemanticException("Overflow when trying to multiply " + stringValue() + " by " + v.stringValue() + ".");
        }
    }
    
    public Value divide (Object val) throws HtSemanticException {
        Value v = new Value(val);
        if (!isNumber() || !v.isNumber()) {
            throw new HtSemanticException("The value '" + value + "' cannot be divided by " + v + '.');
        }

        try {
            return new Value(doubleValue() / v.doubleValue());
        } catch (ArithmeticException e) {
            throw new HtSemanticException("Cannot divide " + stringValue() + " by zero.");
        }
    }

    public Value add (Object val) throws HtSemanticException {
        Value v = new Value(val);
        if (!isNumber() || !v.isNumber()) {
            throw new HtSemanticException("The value '" + value + "' cannot be added to '" + v + "'.");
        }

        try {
            if (isInteger() && v.isInteger())
                return new Value(Math.addExact(longValue(), v.longValue()));
            else
                return new Value(doubleValue() + v.doubleValue());
        } catch (ArithmeticException e) {
            throw new HtSemanticException("Overflow when trying to add " + stringValue() + " to " + v.stringValue() + ".");
        }
    }
    
    public Value subtract (Object val) throws HtSemanticException {
        Value v = new Value(val);
        if (!isNumber() || !v.isNumber()) {
            throw new HtSemanticException("The value '" + value + "' cannot be subtracted by '" + v + "'.");
        }

        try {
            if (isInteger() && v.isInteger())
                return new Value(Math.subtractExact(longValue(), v.longValue()));
            else
                return new Value(doubleValue() - v.doubleValue());
        } catch (ArithmeticException e) {
            throw new HtSemanticException("Overflow when trying to subtract " + v.stringValue() + " from " + stringValue() + ".");
        }
    }
    
    public Value exponentiate (Object val) throws HtSemanticException {
        Value v = new Value(val);
        if (!isNumber() || !v.isNumber()) {
            throw new HtSemanticException("The value '" + value + "' cannot be raised to the power of '" + v + "'.");
        }

        return new Value(Math.pow(doubleValue(), v.doubleValue()));
    }

    public Value mod (Object val) throws HtSemanticException {
        Value v = new Value(val);
        if (!isNumber() || !v.isNumber()) {
            throw new HtSemanticException("The value '" + value + "' cannot be divided by '" + v + "'.");
        }

        if (isInteger() && v.isInteger())
            return new Value(longValue() % v.longValue());
        else
            return new Value(doubleValue() % v.doubleValue());
    }
    
    public Value not () throws HtSemanticException {
        if (!isBoolean())
            throw new HtSemanticException("The value '" + value + "' cannot be logically negated because it is not a boolean value.");
        
        return new Value(!booleanValue());
    }

    public Value negate () throws HtSemanticException {
        if (isInteger())
            return new Value(longValue() * -1);
        else if (isFloat())
            return new Value(doubleValue() * -1);
        else {
            throw new HtSemanticException("The value '" + value + "' cannot be negated because it is not a number.");
        }
    }

    public Value and (Value val) throws HtSemanticException {
        Value v = new Value(val);
        if (!isBoolean() || !v.isBoolean()) {
            throw new HtSemanticException("The value '" + value + "' cannot be logically combined with '" + v + "'.");
        }

        return new Value(booleanValue() && v.booleanValue());
    }
    
    public Value or (Value val) throws HtSemanticException {
        Value v = new Value(val);
        if (!isBoolean() || !v.isBoolean()) {
            throw new HtSemanticException("The value '" + value + "' cannot be logically combined with '" + v + "'.");
        }

        return new Value(booleanValue() || v.booleanValue());
    }

    public Value concat (Value val) {
        return new Value(value + val.toString());
    }

    public Value within(Value val) throws HtSemanticException {
        if (!isPoint() || !val.isRect()) {
            throw new HtSemanticException("Cannot determine if '" + value + "' is within the bounds of '" + val.stringValue() + "'.");
        }

        return new Value(val.rectangleValue().contains(pointValue()));
    }

    public Value trunc() throws HtSemanticException {
        if (isInteger()) {
            return new Value(integerValue());
        } else if (isFloat()) {
            return new Value((int) doubleValue());
        }

        throw new HtSemanticException("Cannot trunc the value '" + stringValue() + "' because it is not a number.");
    }

    public Value isA(Value val) throws HtSemanticException {
        KnownType type = KnownType.getTypeByName(val.toString());
        switch (type) {
            case NUMBER: return new Value(this.isNumber());
            case INTEGER: return new Value(this.isInteger());
            case POINT: return new Value(this.isPoint());
            case RECT: return new Value(this.isRect());
            case DATE: return new Value(DateUtils.dateOf(this) != null);
            case LOGICAL: return new Value(this.isBoolean());

            default: throw new HtSemanticException("Bug! Unimplemented type comparison for: " + type);
        }
    }

    public boolean contains (Object v) {
        return value.contains(v.toString());
    }
        
    public String toString () {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Value otherValue = (Value) o;

        if (isBoolean() && otherValue.isBoolean()) {
            return this.booleanValue() == otherValue.booleanValue();
        } else if (isInteger() && otherValue.isInteger()) {
            return this.integerValue() == otherValue.integerValue();
        } else if ((isInteger() || isFloat()) && (otherValue.isInteger() || otherValue.isFloat())) {
            return this.doubleValue() == otherValue.doubleValue();
        } else {
            return this.stringValue().equalsIgnoreCase(otherValue.stringValue());
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Value o) {
        if (this.isInteger() && o.isInteger()) {
            return Integer.compare(this.integerValue(), o.integerValue());
        } else if (this.isFloat() && o.isFloat()) {
            return Double.compare(this.doubleValue(), o.doubleValue());
        } else {
            return this.stringValue().toLowerCase().trim().compareTo(o.stringValue().toLowerCase().trim());
        }
    }

    @Override
    public int compareTo(Value to, SortStyle style) {
        switch (style) {
            case INTERNATIONAL:
            case TEXT:
                return this.stringValue().compareTo(to.stringValue());
            case NUMERIC:
                return Double.compare(this.doubleValue(), to.doubleValue());
            case DATE_TIME:
                Date thisDate = DateUtils.dateOf(this);
                if (thisDate == null) {
                    return 1;
                }

                Date toDateTime = DateUtils.dateOf(to);
                if (toDateTime == null) {
                    return -1;
                }

                return thisDate.compareTo(toDateTime);
        }

        throw new IllegalArgumentException("Bug! Unimplemented comparison style.");
    }
}