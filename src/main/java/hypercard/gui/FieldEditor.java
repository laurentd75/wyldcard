/**
 * FieldEditor.java
 * @author matt.defano@motorola.com
 * 
 * Implements the field properties editor window. This code was generated 
 * using the Netbeans IDE Swing editor.
 */

package hypercard.gui;

import hypercard.parts.FieldPart;
import hypertalk.ast.common.Value;
import hypertalk.properties.Properties;

import java.awt.Frame;
import java.io.Serializable;

public class FieldEditor extends javax.swing.JFrame implements Serializable {
private static final long serialVersionUID = 4162159591565669342L;

	private Properties properties;
    
    /** Creates new form FieldEditor */
    public FieldEditor(Frame parent, Properties p) {
        this.properties = p;
        
        initComponents();
        loadValues();
        
        this.setLocationRelativeTo(parent);
    }
    
    private void loadValues() {
        jTextFieldFieldName.setText(properties.getKnownProperty(FieldPart.PROP_NAME).stringValue());
        jLabelTheId.setText(properties.getKnownProperty(FieldPart.PROP_ID).stringValue());
        jTextFieldTop.setText(properties.getKnownProperty(FieldPart.PROP_TOP).stringValue());
        jTextFieldLeft.setText(properties.getKnownProperty(FieldPart.PROP_LEFT).stringValue());
        jTextFieldHeight.setText(properties.getKnownProperty(FieldPart.PROP_HEIGHT).stringValue());
        jTextFieldWidth.setText(properties.getKnownProperty(FieldPart.PROP_WIDTH).stringValue());
        jCheckBoxLockText.setSelected(properties.getKnownProperty(FieldPart.PROP_LOCKTEXT).booleanValue());
        jCheckBoxVisible.setSelected(properties.getKnownProperty(FieldPart.PROP_VISIBLE).booleanValue());
        jCheckBoxWrapText.setSelected(properties.getKnownProperty(FieldPart.PROP_WRAPTEXT).booleanValue());
    }
    
    private void saveValues() {
        try {
            properties.setProperty(FieldPart.PROP_NAME, new Value(jTextFieldFieldName.getText()));
            properties.setProperty(FieldPart.PROP_TOP, new Value(jTextFieldTop.getText()));
            properties.setProperty(FieldPart.PROP_LEFT, new Value(jTextFieldLeft.getText()));
            properties.setProperty(FieldPart.PROP_HEIGHT, new Value(jTextFieldHeight.getText()));
            properties.setProperty(FieldPart.PROP_WIDTH, new Value(jTextFieldWidth.getText()));
            properties.setProperty(FieldPart.PROP_LOCKTEXT, new Value(jCheckBoxLockText.getSelectedObjects() != null));
            properties.setProperty(FieldPart.PROP_VISIBLE, new Value(jCheckBoxVisible.getSelectedObjects() != null));
            properties.setProperty(FieldPart.PROP_WRAPTEXT, new Value(jCheckBoxWrapText.getSelectedObjects() != null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelIdentification = new javax.swing.JPanel();
        jLabelFieldName = new javax.swing.JLabel();
        jTextFieldFieldName = new javax.swing.JTextField();
        jLabelId = new javax.swing.JLabel();
        jLabelTheId = new javax.swing.JLabel();
        jPanelProperties = new javax.swing.JPanel();
        jLabel4Height = new javax.swing.JLabel();
        jTextFieldHeight = new javax.swing.JTextField();
        jLabelWidth = new javax.swing.JLabel();
        jTextFieldWidth = new javax.swing.JTextField();
        jCheckBoxVisible = new javax.swing.JCheckBox();
        jCheckBoxWrapText = new javax.swing.JCheckBox();
        jLabelTop = new javax.swing.JLabel();
        jTextFieldLeft = new javax.swing.JTextField();
        jLabelLeft = new javax.swing.JLabel();
        jTextFieldTop = new javax.swing.JTextField();
        jCheckBoxLockText = new javax.swing.JCheckBox();
        jButtonEditScript = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setTitle("Field Properties Editor");

        jPanelIdentification.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Identification"));

        jLabelFieldName.setText("Field Name:");

        jLabelId.setText("ID:");

        jLabelTheId.setText("00000000");

        org.jdesktop.layout.GroupLayout jPanelIdentificationLayout = new org.jdesktop.layout.GroupLayout(jPanelIdentification);
        jPanelIdentification.setLayout(jPanelIdentificationLayout);
        jPanelIdentificationLayout.setHorizontalGroup(
            jPanelIdentificationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelIdentificationLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelIdentificationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabelFieldName)
                    .add(jLabelId))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelIdentificationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextFieldFieldName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .add(jLabelTheId))
                .addContainerGap())
        );
        jPanelIdentificationLayout.setVerticalGroup(
            jPanelIdentificationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelIdentificationLayout.createSequentialGroup()
                .add(jPanelIdentificationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelFieldName)
                    .add(jTextFieldFieldName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelIdentificationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelId)
                    .add(jLabelTheId))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelProperties.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Properties"));

        jLabel4Height.setText("Height:");

        jLabelWidth.setText("Width:");

        jCheckBoxVisible.setText("Visible");
        jCheckBoxVisible.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBoxVisible.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jCheckBoxWrapText.setText("Wrap Text");
        jCheckBoxWrapText.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBoxWrapText.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabelTop.setText("Top:");

        jLabelLeft.setText("Left:");

        jCheckBoxLockText.setText("Lock Text");

        org.jdesktop.layout.GroupLayout jPanelPropertiesLayout = new org.jdesktop.layout.GroupLayout(jPanelProperties);
        jPanelProperties.setLayout(jPanelPropertiesLayout);
        jPanelPropertiesLayout.setHorizontalGroup(
            jPanelPropertiesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelPropertiesLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelPropertiesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4Height)
                    .add(jLabelWidth))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelPropertiesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTextFieldWidth)
                    .add(jTextFieldHeight, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanelPropertiesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelPropertiesLayout.createSequentialGroup()
                        .add(jLabelTop)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextFieldTop, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                    .add(jPanelPropertiesLayout.createSequentialGroup()
                        .add(jLabelLeft)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextFieldLeft, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)))
                .add(50, 50, 50)
                .add(jPanelPropertiesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jCheckBoxWrapText)
                    .add(jPanelPropertiesLayout.createSequentialGroup()
                        .add(jCheckBoxVisible)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jCheckBoxLockText)))
                .addContainerGap())
        );
        jPanelPropertiesLayout.setVerticalGroup(
            jPanelPropertiesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelPropertiesLayout.createSequentialGroup()
                .add(jPanelPropertiesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4Height)
                    .add(jTextFieldHeight, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCheckBoxVisible)
                    .add(jLabelTop)
                    .add(jTextFieldTop, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCheckBoxLockText))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelPropertiesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelWidth)
                    .add(jTextFieldWidth, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCheckBoxWrapText)
                    .add(jLabelLeft)
                    .add(jTextFieldLeft, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jButtonEditScript.setText("Edit Script...");
        jButtonEditScript.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditScriptActionPerformed(evt);
            }
        });

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelProperties, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanelIdentification, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jButtonEditScript)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 159, Short.MAX_VALUE)
                        .add(jButtonCancel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButtonSave)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelIdentification, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelProperties, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButtonEditScript)
                    .add(jButtonSave)
                    .add(jButtonCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEditScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditScriptActionPerformed
        String theScript = properties.getKnownProperty(FieldPart.PROP_SCRIPT).stringValue();
        ScriptEditor se = new ScriptEditor(this, theScript);
        se.setVisible(true);
        
        try {
            if (!se.wasCanceled())
                properties.setProperty(FieldPart.PROP_SCRIPT, new Value(se.getScript()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_jButtonEditScriptActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        saveValues();
        this.dispose();
    }//GEN-LAST:event_jButtonSaveActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonEditScript;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBoxLockText;
    private javax.swing.JCheckBox jCheckBoxVisible;
    private javax.swing.JCheckBox jCheckBoxWrapText;
    private javax.swing.JLabel jLabel4Height;
    private javax.swing.JLabel jLabelFieldName;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelLeft;
    private javax.swing.JLabel jLabelTheId;
    private javax.swing.JLabel jLabelTop;
    private javax.swing.JLabel jLabelWidth;
    private javax.swing.JPanel jPanelIdentification;
    private javax.swing.JPanel jPanelProperties;
    private javax.swing.JTextField jTextFieldFieldName;
    private javax.swing.JTextField jTextFieldHeight;
    private javax.swing.JTextField jTextFieldLeft;
    private javax.swing.JTextField jTextFieldTop;
    private javax.swing.JTextField jTextFieldWidth;
    // End of variables declaration//GEN-END:variables
    
}