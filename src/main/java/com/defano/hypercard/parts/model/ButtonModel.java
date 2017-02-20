/*
 * ButtonModel
 * hypertalk-java
 *
 * Created by Matt DeFano on 2/19/17 3:10 PM.
 * Copyright © 2017 Matt DeFano. All rights reserved.
 */

package com.defano.hypercard.parts.model;

import com.defano.hypercard.parts.buttons.ButtonStyle;
import com.defano.hypertalk.ast.common.PartType;
import com.defano.hypertalk.ast.common.Value;

import javax.swing.*;
import java.awt.*;

public class ButtonModel extends AbstractPartModel {

    public static final String PROP_STYLE = "style";
    public static final String PROP_FAMILY = "family";
    public static final String PROP_HILITE = "hilite";
    public static final String PROP_AUTOHILIGHT = "autohilite";
    public static final String PROP_SHOWNAME = "showname";
    public static final String PROP_ENABLED = "enabled";
    public static final String PROP_CONTENTS = "contents";
    public static final String PROP_TEXTSIZE = "textsize";
    public static final String PROP_TEXTFONT = "textfont";
    public static final String PROP_TEXTSTYLE = "textstyle";
    public static final String PROP_TEXTALIGN = "textalign";

    private ButtonModel() {
        super(PartType.BUTTON);
    }

    public static ButtonModel newButtonModel(Integer id, Rectangle geometry) {
        ButtonModel partModel = new ButtonModel();

        partModel.defineProperty(PROP_SCRIPT, new Value(), false);
        partModel.defineProperty(PROP_ID, new Value(id), true);
        partModel.defineProperty(PROP_NAME, new Value("Button"), false);
        partModel.defineProperty(PROP_LEFT, new Value(geometry.x), false);
        partModel.defineProperty(PROP_TOP, new Value(geometry.y), false);
        partModel.defineProperty(PROP_WIDTH, new Value(geometry.width), false);
        partModel.defineProperty(PROP_HEIGHT, new Value(geometry.height), false);
        partModel.defineProperty(PROP_SHOWNAME, new Value(true), false);
        partModel.defineProperty(PROP_ENABLED, new Value(true), false);
        partModel.defineProperty(PROP_STYLE, new Value(ButtonStyle.DEFAULT.getName()), false);
        partModel.defineProperty(PROP_FAMILY, new Value(), false);
        partModel.defineProperty(PROP_HILITE, new Value(false), false);
        partModel.defineProperty(PROP_AUTOHILIGHT, new Value(true), false);
        partModel.defineProperty(PROP_CONTENTS, new Value(), false);
        partModel.defineProperty(PROP_TEXTSIZE, new Value(((Font)UIManager.get("Button.font")).getSize()), false);
        partModel.defineProperty(PROP_TEXTFONT, new Value("Chicago"), false);
        partModel.defineProperty(PROP_TEXTSTYLE, new Value("plain"), false);
        partModel.defineProperty(PROP_TEXTALIGN, new Value("center"), false);

        return partModel;
    }
}