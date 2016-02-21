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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class okno extends javax.swing.JFrame {

    private final DefaultTableModel model;
    private DefaultTableModel model1;
    private Connection spojeni;
    private int prepinacTrate = 0;
    private String[] nazvy_poli = {"id", "jmeno", "prijmeni", "rocnik", "trat", "cas"};

    public okno() {
        initComponents();
        model = (DefaultTableModel) tabulka.getModel();
        if (!dbConnection()) {
            System.exit(0);
        } else {
            try {
                novaTabulka();
            } catch (SQLException ex) {
                Logger.getLogger(okno.class.getName()).log(Level.SEVERE, null, ex);
            }
            listData(getAllRecords("SELECT * FROM vysledek"));
        }

    }

    // Spojení s db
    private boolean dbConnection() {
        try {
            this.spojeni = (Connection) DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net/sql2107733", "sql2107733", "bZ3*fM2!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Nedošlo k připojení databáze" + ex,
                    "Chyba", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void novaTabulka() throws SQLException {

        PreparedStatement s2 = spojeni.prepareStatement("Show Tables;");
        ResultSet rs = s2.executeQuery();
        while (rs.next()) {
            if (!"vysledek".equals(rs.getString(1))) {
                try {
                    dotaz("CREATE TABLE `sql2107733`.`vysledek` "
                            + "( `id` INT NOT NULL AUTO_INCREMENT , "
                            + "`jmeno` VARCHAR(50) NOT NULL , "
                            + "`prijmeni` VARCHAR(50) NOT NULL , "
                            + "`rocnik` INT NULL , "
                            + "`trat` ENUM('42','21','10') NULL , "
                            + "`cas` TIME NULL , "
                            + "PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_czech_ci;");
                } catch (SQLException e) {
                    errory.setText("Inicializace tabulky byla úspěšná");
                    return;
                }
            }  
        }
    }

    // čtení z db
    private ResultSet getAllRecords(String s) {

        ResultSet vysledek = null;
        try {
            PreparedStatement dotaz = spojeni.prepareStatement(s);
            vysledek = dotaz.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Chyba při komunikaci s db" + ex,
                    "Chyba", JOptionPane.ERROR_MESSAGE);
        }
        return vysledek;
    }

    private void dotaz(String s) throws SQLException {
        try (Statement stmt = spojeni.createStatement()) {
            stmt.executeUpdate(s);
        }
    }

    private void listData(ResultSet data) {
        for (int i = tabulka.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        try {
            while (data.next()) {
                int id = data.getInt(1);
                String jmeno = data.getString(nazvy_poli[1]);
                String prijmeni = data.getString(nazvy_poli[2]);
                String rocnik = data.getString(nazvy_poli[3]);
                String trat = data.getString(nazvy_poli[4]);
                String cas = data.getString(nazvy_poli[5]);
                model.addRow(new Object[]{id, jmeno, prijmeni, rocnik, trat, cas});

            }
            if (tabulka.getRowCount() > 0) {
                tabulka.setRowSelectionInterval(0, 0);
                //edit.setEnabled(true);
                // smazat.setEnabled(true);
            } else {
                // edit.setEnabled(false);
                // smazat.setEnabled(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "špatný vypis",
                    "Chyba", JOptionPane.ERROR_MESSAGE);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        novy_radek = new javax.swing.JButton();
        smazat = new javax.swing.JButton();
        jRadioB_0 = new javax.swing.JRadioButton();
        RadioB_42 = new javax.swing.JRadioButton();
        RadioB_21 = new javax.swing.JRadioButton();
        RadioB_10 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabulka = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        errory = new javax.swing.JLabel();
        detail = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        Soubor = new javax.swing.JMenu();
        Upravit = new javax.swing.JMenu();
        Import = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        novy_radek.setText("Nový záznam");
        novy_radek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novy_radekActionPerformed(evt);
            }
        });

        smazat.setText("Smazat");
        smazat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smazatActionPerformed(evt);
            }
        });

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
                "č.", "Jméno", "Přijmení", "Ročnik", "Délka tratě [km]", "Čas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
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

        jToolBar1.setRollover(true);

        errory.setForeground(new java.awt.Color(255, 0, 0));
        jToolBar1.add(errory);

        detail.setText("Detail");
        detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailActionPerformed(evt);
            }
        });

        Soubor.setText("Soubor");
        jMenuBar1.add(Soubor);

        Upravit.setText("Upravit");
        jMenuBar1.add(Upravit);

        Import.setText("Import");
        Import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportActionPerformed(evt);
            }
        });
        jMenuBar1.add(Import);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(novy_radek)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(smazat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(detail))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RadioB_42)
                        .addGap(5, 5, 5)
                        .addComponent(RadioB_21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RadioB_10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioB_0)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(novy_radek)
                    .addComponent(smazat)
                    .addComponent(detail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RadioB_21)
                    .addComponent(RadioB_10)
                    .addComponent(jLabel1)
                    .addComponent(RadioB_42)
                    .addComponent(jRadioB_0))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        errory.setText("");

        if (tabulka.getEditingColumn() != -1 && tabulka.getEditingRow() != -1) {
            if (tabulka.getEditingColumn() == 3 && !kontrola_data()) {
                errory.setText("Zadej platné datum");
                listData(getAllRecords("SELECT * FROM vysledek"));
                return;
            }
            String ktere_pole = nazvy_poli[tabulka.getEditingColumn()];
            String zmena_hodnoty_pole = model.getValueAt(tabulka.getEditingRow(), tabulka.getEditingColumn()).toString();
            String id_pole = model.getValueAt(tabulka.getEditingRow(), 0).toString();

            try {
                dotaz("UPDATE vysledek SET " + ktere_pole + "='" + zmena_hodnoty_pole + "' WHERE id='" + id_pole + "';");
            } catch (SQLException ex) {
                errory.setText("aktualizace se nezdařila");
                listData(getAllRecords("SELECT * FROM vysledek"));
                if (tabulka.getEditingColumn() == 4) {
                    errory.setText("aktualizace se nezdařila - zadej [ 10, 21, 42 ]");
                }
            }

        }
    }//GEN-LAST:event_tabulkaPropertyChange

    private boolean kontrola_data() {
        int hodnota_pole = Integer.parseInt((String) model.getValueAt(tabulka.getEditingRow(), tabulka.getEditingColumn()));
        return (hodnota_pole > 1900 && hodnota_pole < Calendar.getInstance().get(Calendar.YEAR));
    }
    private void tabulkaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tabulkaInputMethodTextChanged

    }//GEN-LAST:event_tabulkaInputMethodTextChanged

    private void tabulkaCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tabulkaCaretPositionChanged

    }//GEN-LAST:event_tabulkaCaretPositionChanged

    private void ImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportActionPerformed

    }//GEN-LAST:event_ImportActionPerformed

    private void novy_radekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novy_radekActionPerformed

        try {
            dotaz("INSERT INTO `vysledek` (`id`, `jmeno`, `prijmeni`, `rocnik`, `trat`, `cas`) VALUES (NULL, '', '', null, null, null);");
        } catch (SQLException ex) {
            Logger.getLogger(okno.class.getName()).log(Level.SEVERE, null, ex);
            errory.setText("Přidání nového záznamu se nezdařilo");
        }
        listData(getAllRecords("SELECT * FROM vysledek"));
    }//GEN-LAST:event_novy_radekActionPerformed

    private void smazatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smazatActionPerformed

        if (tabulka.getSelectedRow() != -1) {
            String id_pole = model.getValueAt(tabulka.getSelectedRow(), 0).toString();
            try {
                dotaz("DELETE FROM vysledek WHERE id='" + id_pole + "';");
            } catch (SQLException ex) {
                Logger.getLogger(okno.class.getName()).log(Level.SEVERE, null, ex);
                errory.setText("Odstranění se nezdařilo");
            }
            listData(getAllRecords("SELECT * FROM vysledek"));
        }
    }//GEN-LAST:event_smazatActionPerformed

    private void detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_detailActionPerformed

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
    private javax.swing.JMenu Import;
    private javax.swing.JRadioButton RadioB_10;
    private javax.swing.JRadioButton RadioB_21;
    private javax.swing.JRadioButton RadioB_42;
    private javax.swing.JMenu Soubor;
    private javax.swing.JMenu Upravit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton detail;
    private javax.swing.JLabel errory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButton jRadioB_0;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton novy_radek;
    private javax.swing.JButton smazat;
    private javax.swing.JTable tabulka;
    // End of variables declaration//GEN-END:variables
}

/*
 SQL - tabulka
 CREATE TABLE `vysledky`.`vysledek` ( `id` INT NOT NULL AUTO_INCREMENT , `jmeno` VARCHAR(50) NOT NULL , `prijmeni` VARCHAR(50) NOT NULL , `rocnik` INT NULL , `trat` ENUM('42','21','10') NOT NULL , `cas` TIME NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_czech_ci;
 
 připojení na db
    Your account number is: 137008
    Host: sql2.freemysqlhosting.net
    Database name: sql2107733
    Database user: sql2107733
    Database password: bZ3*fM2!
    Port number: 3306
*/
