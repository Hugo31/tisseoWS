/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaceTisseoWS;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tisseows.Depart;
import tisseows.RequestTisseo;

/**
 *
 * @author Hugo
 */
public final class ST1 extends javax.swing.JFrame {

    
    private List<Depart> departs;
    /**
     * Creates new form ST1
     * @throws org.json.simple.parser.ParseException
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     */
    public ST1() throws ParseException, IOException, URISyntaxException {
        initComponents();
        departs = new ArrayList<>();
        init();
    }
    
    public void init() throws ParseException, IOException, URISyntaxException{
        RequestTisseo r = new RequestTisseo();
        JSONParser parser = new JSONParser();
        
        r.setPathURIB("/placesList");
        r.addParamURIB("term", "Univ. Paul Sabatier (Ut3) (TOULOUSE)");
        r.addParamURIB("displayOnlyStopAreas", "1");
        
        Object obj = parser.parse(r.request());
        JSONObject array = (JSONObject)obj;

        r.resetURIB();
        r.setPathURIB("/stopPointsList");
        r.addParamURIB("stopAreaId", (String) ((JSONObject)((JSONArray)((JSONObject)array.get("placesList")).get("place")).get(0)).get("id"));
        r.addParamURIB("network", "Tisséo");
        
        obj = parser.parse(r.request());
        array = (JSONObject)obj;
        int nbStops = ((JSONArray)((JSONObject)array.get("physicalStops")).get("physicalStop")).size();
        int nbDeparts;
        Object obj2;
        JSONObject array2;
        for(int i = 0 ; i < nbStops ; i++){
            r.resetURIB();
            r.setPathURIB("/departureBoard");
            r.addParamURIB("stopPointId", (String) ((JSONObject)((JSONArray)((JSONObject)array.get("physicalStops")).get("physicalStop")).get(i)).get("id"));
            obj2 = parser.parse(r.request());
            array2 = (JSONObject)obj2;
            
            nbDeparts = ((JSONArray)((JSONObject)array2.get("departures")).get("departure")).size();
            if(nbDeparts > 1){
                departs.add(new Depart((JSONObject)array2.get("departures")));
                listeLignes.addItem(departs.get(departs.size()-1).toString());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        listeLignes = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        horaires = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();

        setTitle("ST1");

        listeLignes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sélectionner la ligne" }));
        listeLignes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listeLignesItemStateChanged(evt);
            }
        });

        jLabel1.setText("Veuillez choisir une ligne :");

        jScrollPane1.setViewportView(horaires);

        jButton1.setText("Retour");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(listeLignes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(listeLignes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listeLignesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listeLignesItemStateChanged
        DefaultListModel lm = new DefaultListModel();
        for(int i = 0 ; i < departs.get(listeLignes.getSelectedIndex()-1).nbDeparts() ; i++){
            lm.addElement(departs.get(listeLignes.getSelectedIndex()-1).toString(i));
        }
        
        horaires.setModel(lm);
        
    }//GEN-LAST:event_listeLignesItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ST1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ST1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ST1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ST1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ST1().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(ST1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ST1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(ST1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList horaires;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox listeLignes;
    // End of variables declaration//GEN-END:variables
}
