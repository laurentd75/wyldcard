package com.defano.wyldcard.stackreader.record;

import com.defano.wyldcard.stackreader.block.Block;
import com.defano.wyldcard.stackreader.misc.ImportException;
import com.defano.wyldcard.stackreader.misc.StackInputStream;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.IOException;

@SuppressWarnings("unused")
public class PartContentRecord {

    private short partId;
    private boolean isPlaintext;
    private StyleSpanRecord[] styleSpans = new StyleSpanRecord[0];
    private String text;

    public static PartContentRecord deserialize(Block parent, short partId, byte[] data) throws ImportException {
        PartContentRecord partContent = new PartContentRecord();

        partContent.partId = partId;

        int length = data.length;
        int styleLength = 1;

        try (StackInputStream sis = new StackInputStream(data)) {
            byte highByte = sis.readByte();

            // When high bit is set, data provides a table of style spans
            if ((highByte & 0x80) > 0) {
                byte lowByte = sis.readByte();

                styleLength = ((highByte & 0x7f) << 8) | (lowByte & 0xff);

                partContent.styleSpans = new StyleSpanRecord[styleLength / 4];
                for (int styleIdx = 0; styleIdx < styleLength / 4; styleIdx++) {
                    short textPosition = sis.readShort();
                    short styleId = sis.readShort();
                    partContent.styleSpans[styleIdx] = new StyleSpanRecord(textPosition, styleId);
                }
            }

            // When bit is clear, contents are plaintext
            else {
                partContent.isPlaintext = true;
            }

            partContent.text = sis.readString(length - styleLength);

        } catch (IOException e) {
            throw new ImportException(parent, "Malformed part content record; stack is corrupt.", e);
        }

        return partContent;
    }

    /**
     * Gets the part id as present in this record. A negative value refers to a card part whose actual id is this value
     * times -1; a positive value refers to a background part with this value.
     *
     * @return The raw part id as parsed from the stack.
     */
    public short getRawPartId() {
        return partId;
    }

    /**
     * Gets the ID of the part that this record refers to. Use {@link #isBackgroundPart()} to disambiguate whether this
     * ID refers to a card or background-layer part.
     *
     * @return The ID of the part that this record applies to.
     */
    public int getPartId() {
        return Math.abs(partId);
    }

    /**
     * Determines if this record applies to a background part.
     *
     * @return True if this part record applies to a background part; false if it applies to a card part.
     */
    public boolean isBackgroundPart() {
        return partId >= 0;
    }

    public boolean isPlaintext() {
        return isPlaintext;
    }

    public StyleSpanRecord[] getStyleSpans() {
        return styleSpans;
    }

    public String getText() {
        return text;
    }

    /**
     * Determines if a background button whose 'sharedHilite' flag is false should be hilited.
     *
     * Background buttons with the "sharedHilite" flag set to false use the text content of their PartContentRecord to
     * flag whether they should be hilited on the card to which the part record belongs. This return value of this
     * method has no meaning if this PartContentRecord does not apply to a background button, or if the button's
     * 'sharedHilite' flag is not false..
     *
     * @return True if this background button should be hilited.
     */
    public boolean isBkgndButtonHilited() {
        return text != null && text.equals("1");
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
