/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balikOkno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventListener;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author student
 */
public class okno extends javax.swing.JFrame {
private final DefaultTableModel model;
private DefaultTableModel model1;
    private Connection spojeni;
    private int prepinacTrate = 0;
    
    
    public okno() {
        initComponents();
        model = (DefaultTableModel) tabulka.getModel();
        if(!dbConnection()){
            System.exit(0);
        }else{
            listData(getAllRecords("SELECT * FROM vysledek"));
        }
        
    }
    
    // Spojení s db
    private boolean dbConnection() {
        try {
            this.spojeni = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/vysledky", "root", "");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Nedošlo k připojení databáze"+ ex, 
                "Chyba", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    // čtení z db
    private ResultSet getAllRecords(String s){
        ResultSet vysledek = null;
        try{
            PreparedStatement dotaz = spojeni.prepareStatement(s);
            vysledek = dotaz.executeQuery();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(this, "Chyba při komunikaci s db" + ex, 
                "Chyba", JOptionPane.ERROR_MESSAGE);
        } 
        return vysledek;
    }
    
    
    
    private void listData(ResultSet data){
        for (int i = tabulka.getRowCount()-1;i>=0;i--){
            model.removeRow(i);
        }
        try{
            while(data.next()){
                int id = data.getInt(1);
                String jmeno = data.getString("jmeno");
                String prijmeni = data.getString("prijmeni");
                String rocnik = data.getString("rocnik");
                String trat = data.getString("trat")+" km";
                String cas = data.getString("cas");
                model.addRow(new Object[]{id,jmeno,prijmeni,rocnik,trat,cas});
                
                
            }
            if(tabulka.getRowCount() > 0){
                tabulka.setRowSelectionInterval(0, 0);
                //edit.setEnabled(true);
               // smazat.setEnabled(true);
            }else{
               // edit.setEnabled(false);
               // smazat.setEnabled(false);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "špatný vypis", 
                "Chyba", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jRadioB_0 = new javax.swing.JRadioButton();
        RadioB_42 = new javax.swing.JRadioButton();
        RadioB_21 = new javax.swing.JRadioButton();
        RadioB_10 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabulka = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        Soubor = new javax.swing.JMenu();
        Upravit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Nový záznam");

        jButton2.setText("upravit");

        jButton3.setText("Smazat");

        buttonGroup1.add(jRadioB_0);
        jRadioB_0.setSelected(true);
        jRadioB_0.setText("vše");
        jRadioB_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioB_0ActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadioB_42);
        RadioB_42.setText("42 km");
        RadioB_42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioB_42ActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadioB_21);
        RadioB_21.setText("21 km");
        RadioB_21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioB_21ActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadioB_10);
        RadioB_10.setText("10 km");
        RadioB_10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioB_10ActionPerformed(evt);
            }
        });

        jLabel1.setText("Trať:");

        tabulka.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "č.", "Jméno", "Přijmení", "Ročnik", "Délka tratě", "Čas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabulka.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tabulkaCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tabulkaInputMethodTextChanged(evt);
            }
        });
        tabulka.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tabulkaPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tabulka);
        if (tabulka.getColumnModel().getColumnCount() > 0) {
            tabulka.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        Soubor.setText("Soubor");
        jMenuBar1.add(Soubor);

        Upravit.setText("Upravit");
        jMenuBar1.add(Upravit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RadioB_42)
                        .addGap(5, 5, 5)
                        .addComponent(RadioB_21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RadioB_10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioB_0))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RadioB_21)
                    .addComponent(RadioB_10)
                    .addComponent(jLabel1)
                    .addComponent(RadioB_42)
                    .addComponent(jRadioB_0))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RadioB_21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioB_21ActionPerformed
        prepinacTrate = 21;
        listData(getAllRecords("SELECT * FROM vysledek WHERE trat LIKE 21"));
    }//GEN-LAST:event_RadioB_21ActionPerformed

    private void jRadioB_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioB_0ActionPerformed
        prepinacTrate = 0;
        listData(getAllRecords("SELECT * FROM vysledek"));
    }//GEN-LAST:event_jRadioB_0ActionPerformed

    private void RadioB_10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioB_10ActionPerformed
        prepinacTrate = 10;
        listData(getAllRecords("SELECT * FROM vysledek WHERE trat LIKE 10"));
    }//GEN-LAST:event_RadioB_10ActionPerformed

    private void RadioB_42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioB_42ActionPerformed
        prepinacTrate = 42;
        listData(getAllRecords("SELECT * FROM vysledek WHERE trat LIKE 42"));
    }//GEN-LAST:event_RadioB_42ActionPerformed

    private void tabulkaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tabulkaPropertyChange
        model1 = (DefaultTableModel) tabulka.getModel();

    }//GEN-LAST:event_tabulkaPropertyChange

    private void tabulkaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tabulkaInputMethodTextChanged
        
    }//GEN-LAST:event_tabulkaInputMethodTextChanged

    private void tabulkaCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tabulkaCaretPositionChanged
        System.out.println("ů");
    }//GEN-LAST:event_tabulkaCaretPositionChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(okno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(okno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(okno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(okno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new okno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioB_10;
    private javax.swing.JRadioButton RadioB_21;
    private javax.swing.JRadioButton RadioB_42;
    private javax.swing.JMenu Soubor;
    private javax.swing.JMenu Upravit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButton jRadioB_0;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabulka;
    // End of variables declaration//GEN-END:variables
}
