/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin
 */
public class Insertar_prod extends javax.swing.JDialog {

    /**
     * Creates new form Insertar_prod
     */
    private Connection connection = null;
    private ResultSet rs = null;
    private Statement s = null;

    public Insertar_prod(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Insertar Producto");
    }

    public void Conexion_Base_datos() {
        if (connection != null) {
            return;
        }

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/producto", "postgres", "kevin");
            if (connection != null) {

            }
        } catch (Exception e) {
            System.out.println("Problem when connecting to the database");
        }
    }

    public void registrarPro() {

        Conexion_Base_datos();
        try {
            String nombre = Nombre_prod.getText();
            int idpro = Integer.parseInt(Codigo_prod.getText());
            int precio = Integer.parseInt(Precio_prod.getText());
            String tipo = Tipo_prod.getSelectedItem().toString();
            Productos_herencia pro = new Productos_herencia(nombre, idpro, tipo, precio);
            s = connection.createStatement();
            int z = s.executeUpdate("INSERT INTO productos (id_prod ,nombre_prod, precio_prod, tipo_prod) VALUES ('" + pro.getId_prod() + "', '" + pro.getNombre() + "', '" + pro.getPrecio() + "', '" + pro.getTipo() + "' )");
            if (z == 1) {
                JOptionPane.showMessageDialog(null, "Se agregó el Productos de manera exitosa");
                Nombre_prod.setText("");
                Codigo_prod.setText("");
                Precio_prod.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Error al insertar el Código, Ingrese Números en el Codigo!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar el Producto, ya existe ese código");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Codigo_prod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Nombre_prod = new javax.swing.JTextField();
        label2 = new javax.swing.JLabel();
        Precio_prod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Tipo_prod = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Sitka Text", 3, 24)); // NOI18N
        jLabel1.setText("Inserte un Producto");

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        jLabel2.setText("Código Producto");

        Codigo_prod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Codigo_prodKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        jLabel3.setText("Nombre Producto");

        label2.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        label2.setText("Precio");

        Precio_prod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Precio_prodKeyTyped(evt);
            }
        });

        jLabel4.setText("Tipo");

        Tipo_prod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Adúlto", "Jovén", "Niño" }));

        jButton1.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jButton1.setText("Insertar Producto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Atrás");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(label2)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Nombre_prod)
                            .addComponent(Codigo_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Precio_prod)
                            .addComponent(Tipo_prod, 0, 116, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1)))
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(28, 28, 28)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Codigo_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nombre_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2)
                    .addComponent(Precio_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Tipo_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Codigo_prodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Codigo_prodKeyTyped
        char c = evt.getKeyChar();

        if (Character.isLetter(c)) {
            getToolkit().beep();

            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo Números");
        }
    }//GEN-LAST:event_Codigo_prodKeyTyped

    private void Precio_prodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Precio_prodKeyTyped
        char c = evt.getKeyChar();

        if (Character.isLetter(c)) {
            getToolkit().beep();

            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo Números");
        }
    }//GEN-LAST:event_Precio_prodKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        registrarPro();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        Menú me = new Menú(null, true);
        me.pack();
        me.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Insertar_prod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Insertar_prod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Insertar_prod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Insertar_prod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Insertar_prod dialog = new Insertar_prod(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField Codigo_prod;
    private javax.swing.JTextField Nombre_prod;
    private javax.swing.JTextField Precio_prod;
    private javax.swing.JComboBox<String> Tipo_prod;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel label2;
    // End of variables declaration//GEN-END:variables
}
