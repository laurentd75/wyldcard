/*
 * PatternPalette
 * hypertalk-java
 *
 * Created by Matt DeFano on 2/19/17 3:10 PM.
 * Copyright © 2017 Matt DeFano. All rights reserved.
 */

package com.defano.hypercard.window.forms;

import com.defano.hypercard.window.HyperCardDialog;
import com.defano.hypercard.paint.ToolsContext;
import com.defano.hypercard.patterns.HyperCardPatternFactory;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class PatternPalette extends HyperCardDialog implements Observer {

    private final int PATTERN_WIDTH = 30;
    private final int PATTERN_HEIGHT = 20;

    private int selectedPattern;
    private JPanel palettePanel;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JButton button20;
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button24;
    private JButton button25;
    private JButton button26;
    private JButton button27;
    private JButton button28;
    private JButton button29;
    private JButton button30;
    private JButton button31;
    private JButton button32;
    private JButton button33;
    private JButton button34;
    private JButton button35;
    private JButton button36;
    private JButton button37;
    private JButton button38;
    private JButton button39;
    private JButton button40;

    private JButton[] allPatterns;

    public PatternPalette() {
        allPatterns = new JButton[]{
                button1, button2, button3, button4, button5, button6, button7, button8, button9,
                button10, button11, button12, button13, button14, button15, button16, button17, button18, button19,
                button20, button21, button22, button23, button24, button25, button26, button27, button28, button29,
                button30, button31, button32, button33, button34, button35, button36, button37, button38, button39,
                button40
        };

        redrawPatternButtons();

        ToolsContext.getInstance().getFillPatternProvider().addObserverAndUpdate(this);
        ToolsContext.getInstance().getBackgroundColorProvider().addObserverAndUpdate(this);
        ToolsContext.getInstance().getForegroundColorProvider().addObserverAndUpdate(this);
    }

    @Override
    public JPanel getWindowPanel() {
        return palettePanel;
    }

    @Override
    public void bindModel(Object data) {
        // Nothing to do
    }

    private void redrawPatternButtons() {
        for (int index = 0; index < allPatterns.length; index++) {
            final int i = index;        // For use in lambda

            allPatterns[index].setIcon(new ImageIcon(createIconForButton(PATTERN_WIDTH, PATTERN_HEIGHT, i)));
            allPatterns[index].setSize(PATTERN_WIDTH, PATTERN_HEIGHT);
            allPatterns[index].addActionListener(e -> {
                selectedPattern = i;
                ToolsContext.getInstance().setPattern(selectedPattern);
            });
        }
    }

    private BufferedImage createIconForButton(int width, int height, int patternId) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setPaint(HyperCardPatternFactory.create(patternId));
        g.fillRect(0, 0, width, height);
        g.dispose();

        return image;
    }

    @Override
    public void update(Observable o, Object newValue) {
        if (newValue instanceof Integer) {
            for (JButton thisButton : allPatterns) {
                thisButton.setEnabled(true);
            }

            allPatterns[(int) newValue].setEnabled(false);
        } else if (newValue instanceof Color) {
            redrawPatternButtons();
            ToolsContext.getInstance().setPattern(selectedPattern);
        }
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        palettePanel = new JPanel();
        palettePanel.setLayout(new GridLayoutManager(10, 4, new Insets(0, 0, 0, 0), 0, 0));
        button1 = new JButton();
        button1.setText("");
        palettePanel.add(button1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button2 = new JButton();
        button2.setText("");
        palettePanel.add(button2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button3 = new JButton();
        button3.setText("");
        palettePanel.add(button3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button4 = new JButton();
        button4.setText("");
        palettePanel.add(button4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button5 = new JButton();
        button5.setText("");
        palettePanel.add(button5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button6 = new JButton();
        button6.setText("");
        palettePanel.add(button6, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button7 = new JButton();
        button7.setText("");
        palettePanel.add(button7, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button8 = new JButton();
        button8.setText("");
        palettePanel.add(button8, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button9 = new JButton();
        button9.setText("");
        palettePanel.add(button9, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button10 = new JButton();
        button10.setText("");
        palettePanel.add(button10, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button11 = new JButton();
        button11.setText("");
        palettePanel.add(button11, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button12 = new JButton();
        button12.setText("");
        palettePanel.add(button12, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button13 = new JButton();
        button13.setText("");
        palettePanel.add(button13, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button14 = new JButton();
        button14.setText("");
        palettePanel.add(button14, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button15 = new JButton();
        button15.setText("");
        palettePanel.add(button15, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button16 = new JButton();
        button16.setText("");
        palettePanel.add(button16, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button17 = new JButton();
        button17.setText("");
        palettePanel.add(button17, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button18 = new JButton();
        button18.setText("");
        palettePanel.add(button18, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button19 = new JButton();
        button19.setText("");
        palettePanel.add(button19, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button20 = new JButton();
        button20.setText("");
        palettePanel.add(button20, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button21 = new JButton();
        button21.setText("");
        palettePanel.add(button21, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button22 = new JButton();
        button22.setText("");
        palettePanel.add(button22, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button23 = new JButton();
        button23.setText("");
        palettePanel.add(button23, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button24 = new JButton();
        button24.setText("");
        palettePanel.add(button24, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button25 = new JButton();
        button25.setText("");
        palettePanel.add(button25, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button26 = new JButton();
        button26.setText("");
        palettePanel.add(button26, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button27 = new JButton();
        button27.setText("");
        palettePanel.add(button27, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button28 = new JButton();
        button28.setText("");
        palettePanel.add(button28, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button29 = new JButton();
        button29.setText("");
        palettePanel.add(button29, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button30 = new JButton();
        button30.setText("");
        palettePanel.add(button30, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button31 = new JButton();
        button31.setText("");
        palettePanel.add(button31, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button32 = new JButton();
        button32.setText("");
        palettePanel.add(button32, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button33 = new JButton();
        button33.setText("");
        palettePanel.add(button33, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button34 = new JButton();
        button34.setText("");
        palettePanel.add(button34, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button35 = new JButton();
        button35.setText("");
        palettePanel.add(button35, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button36 = new JButton();
        button36.setText("");
        palettePanel.add(button36, new GridConstraints(8, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button37 = new JButton();
        button37.setText("");
        palettePanel.add(button37, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button38 = new JButton();
        button38.setText("");
        palettePanel.add(button38, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button39 = new JButton();
        button39.setText("");
        palettePanel.add(button39, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
        button40 = new JButton();
        button40.setText("");
        palettePanel.add(button40, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(34, 24), new Dimension(34, 24), new Dimension(34, 24), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return palettePanel;
    }
}