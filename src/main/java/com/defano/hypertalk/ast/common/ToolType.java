/*
 * Tool
 * hypertalk-java
 *
 * Created by Matt DeFano on 2/19/17 3:11 PM.
 * Copyright © 2017 Matt DeFano. All rights reserved.
 */

package com.defano.hypertalk.ast.common;

import com.defano.jmonet.model.PaintToolType;
import com.defano.jmonet.tools.*;
import com.google.common.collect.Lists;
import com.defano.hypercard.context.ToolMode;
import com.defano.hypertalk.exception.HtSemanticException;

import java.util.ArrayList;
import java.util.List;

public enum ToolType {

    BROWSE(1, "browse"),
    OVAL(14, "oval"),
    BRUSH(7, "brush"),
    PENCIL(6, "pencil"),
    BUCKET(13, "bucket"),
    POLYGON(18, "poly", "polygon"),
    BUTTON(2, "button"),
    RECTANGLE(11, "rect", "rectangle"),
    CURVE(15, "curve"),
    SHAPE(17, "reg poly", "regular poly", "reg polygon", "regular polygon"),
    ERASER(8, "eraser"),
    ROUNDRECT(12, "round rect", "round rectangle"),
    FIELD(3, "field"),
    SELECT(4, "select"),
    LASSO(5, "lasso"),
    SPRAY(10, "spray", "spray can"),
    LINE(9, "line"),
    TEXT(16, "text"),
    SLANT,
    ROTATE,
    SCALE,
    MAGNIFIER,
    PROJECTION,
    PERSPECTIVE,
    RUBBERSHEET;

    private final int toolNumber;
    private final List<String> toolNames;

    ToolType() {
        this.toolNumber = -1;
        this.toolNames = new ArrayList<>();
    }

    ToolType(int toolNumber, String... names) {
        this.toolNumber = toolNumber;
        this.toolNames = Lists.newArrayList(names);
    }

    public static ToolType fromToolMode(ToolMode mode, PaintToolType paintTool) {
        switch (mode) {
            case BROWSE: return BROWSE;
            case FIELD: return FIELD;
            case PAINT: return fromPaintTool(paintTool);
        }

        throw new IllegalArgumentException("Bug! Unimplemented tool mode: " + mode);
    }

    public PaintToolType toPaintTool() {
        switch (this) {
            case BROWSE: return PaintToolType.ARROW;
            case OVAL: return PaintToolType.OVAL;
            case BRUSH: return PaintToolType.PAINTBRUSH;
            case PENCIL: return PaintToolType.PENCIL;
            case BUCKET: return PaintToolType.FILL;
            case POLYGON: return PaintToolType.POLYGON;
            case BUTTON: return PaintToolType.ARROW;
            case RECTANGLE: return PaintToolType.RECTANGLE;
            case CURVE: return PaintToolType.FREEFORM;
            case SHAPE: return PaintToolType.SHAPE;
            case ERASER: return PaintToolType.ERASER;
            case ROUNDRECT: return PaintToolType.ROUND_RECTANGLE;
            case FIELD: return PaintToolType.ARROW;
            case SELECT: return PaintToolType.SELECTION;
            case LASSO: return PaintToolType.LASSO;
            case SPRAY: return PaintToolType.AIRBRUSH;
            case LINE: return PaintToolType.LINE;
            case TEXT: return PaintToolType.TEXT;
            case SLANT: return PaintToolType.SLANT;
            case ROTATE: return PaintToolType.ROTATE;
            case SCALE: return PaintToolType.SCALE;
            case MAGNIFIER: return PaintToolType.MAGNIFIER;
            case PROJECTION: return PaintToolType.PROJECTION;
            case PERSPECTIVE: return PaintToolType.PERSPECTIVE;
            case RUBBERSHEET: return PaintToolType.RUBBERSHEET;
        }

        throw new IllegalStateException("Bug! Unimplemented tool.");
    }

    public static ToolType fromPaintTool(PaintToolType paintTool) {
        switch (paintTool) {
            case ARROW: return BROWSE;
            case PENCIL: return PENCIL;
            case RECTANGLE: return RECTANGLE;
            case ROUND_RECTANGLE: return ROUNDRECT;
            case OVAL: return OVAL;
            case PAINTBRUSH: return BRUSH;
            case ERASER: return ERASER;
            case LINE: return LINE;
            case POLYGON: return POLYGON;
            case SHAPE: return SHAPE;
            case FREEFORM: return CURVE;
            case SELECTION: return SELECT;
            case LASSO: return LASSO;
            case TEXT: return TEXT;
            case FILL: return BUCKET;
            case AIRBRUSH: return SPRAY;
            case CURVE: return CURVE;
            case SLANT: return SLANT;
            case ROTATE: return ROTATE;
            case SCALE: return SCALE;
            case PROJECTION: return PROJECTION;
            case PERSPECTIVE: return PERSPECTIVE;
            case RUBBERSHEET: return RUBBERSHEET;
            case MAGNIFIER: return MAGNIFIER;
        }

        throw new IllegalArgumentException("Bug! Unimplemented paint tool: " + paintTool);
    }

    public static ToolType byName(String toolName) throws HtSemanticException {
        for (ToolType thisTool : ToolType.values()) {
            if (thisTool.toolNames.contains(toolName.toLowerCase())) {
                return thisTool;
            }
        }

        throw new HtSemanticException("No tool named " + toolName);
    }

    public static ToolType byNumber(int toolNumber) throws HtSemanticException {
        for (ToolType thisTool : ToolType.values()) {
            if (thisTool.toolNumber == toolNumber) {
                return thisTool;
            }
        }

        throw new HtSemanticException("No tool number " + toolNumber +". (Tools are numbered 1-18.)");
    }

    public String getPrimaryToolName() {
        if (toolNames.size() == 0) {
            throw new IllegalStateException("This tool does not have a name in HyperTalk.");
        }

        return toolNames.get(0);
    }

    public int getToolNumber() {
        return toolNumber;
    }
}