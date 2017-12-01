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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevin
 */
public class Reportes_pro extends javax.swing.JDialog {

    /**
     * Creates new form Reportes_pro
     */
    private Connection connection = null;
    private ResultSet rs = null;
    private Statement s = null;
    private DefaultListModel modelo;

    public Reportes_pro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Reportes");
        reporte3();
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

    public void reporte1() {
        SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList fechaprin = null;
        String fecha_ini = formato2.format(Fecha_ini.getDate());
        String fecha_fin = formato2.format(Fecha_fin.getDate());

        Conexion_Base_datos();
        try {

            modelo = new DefaultListModel();
            s = connection.createStatement();
            rs = s.executeQuery("select e.nombre_prod from compras t,  productos e where cast (t.fecha as Date)  between cast ('" + fecha_ini + "'  as Date) and cast('" + fecha_fin + "' as Date)\n"
                    + "and t.id_pro = e.id_prod group by e.nombre_prod;");

            while (rs.next()) {

                modelo.addElement(rs.getString("nombre_prod"));

            }

            Fechas.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error de conexión"+e);
        }

    }

    public void reporte2() {

        ArrayList<String> pro = null;
        ArrayList<String> precio = null;
        ArrayList<String> tipo = null;
        Conexion_Base_datos();

        try {
            pro = new <String>ArrayList();
            precio = new <String>ArrayList();
            tipo = new <String>ArrayList();
            s = connection.createStatement();
            rs = s.executeQuery("SELECT nombre_prod, precio_prod, tipo_prod FROM productos WHERE tipo_prod = '" + Usuario.getSelectedItem().toString() + "' ORDER BY nombre_prod ASC");

            while (rs.next()) {
                pro.add(rs.getString("nombre_prod"));
                precio.add(rs.getString("precio_prod"));
                tipo.add(rs.getString("tipo_prod"));

            }
        } catch (Exception e) {
            System.out.println("Error de conexión" + e);
        }

        for (int i = 0; i < pro.size(); i++) {
            usuario.setValueAt(pro.get(i), i, 0);
            usuario.setValueAt(precio.get(i), i, 1);
            usuario.setValueAt(tipo.get(i), i, 2);

            if (pro.size() >= usuario.getRowCount()) {
                DefaultTableModel temp2 = (DefaultTableModel) usuario.getModel();
                Object nuevo[] = {temp2.getRowCount()};
                temp2.addRow(nuevo);
            }

        }
        if (usuario.getRowCount() > pro.size()) {

            try {
                DefaultTableModel temp2 = (DefaultTableModel) usuario.getModel();
                usuario.getModel();
                temp2.removeRow(temp2.getRowCount() - 1);

            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }

    public void reporte3() {
        ArrayList<String> nombre = null;
        ArrayList<String> edad = null;
        ArrayList<String> sexo = null;
        ArrayList<String> pro = null;
        Conexion_Base_datos();

        try {
            pro = new <String>ArrayList();
            nombre = new <String>ArrayList();
            edad = new <String>ArrayList();
            sexo = new <String>ArrayList();
            s = connection.createStatement();
            rs = s.executeQuery("select e.nombre, e.edad, e.sexo, t.nombre_prod from compras e, productos t where e.id_pro = t.id_prod and\n"
                    + "e.sexo = 'Femenino' and e.edad > 12;");

            while (rs.next()) {
                pro.add(rs.getString("nombre_prod"));
                edad.add(rs.getString("edad"));
                sexo.add(rs.getString("sexo"));
                nombre.add(rs.getString("nombre"));

            }
        } catch (Exception e) {
            System.out.println("Error de conexión" + e);
        }

        for (int i = 0; i < pro.size(); i++) {
            Femenino.setValueAt(nombre.get(i), i, 0);
            Femenino.setValueAt(sexo.get(i), i, 1);
            Femenino.setValueAt(edad.get(i), i, 2);
            Femenino.setValueAt(pro.get(i), i, 3);

            if (pro.size() >= Femenino.getRowCount()) {
                DefaultTableModel temp2 = (DefaultTableModel) Femenino.getModel();
                Object nuevo[] = {temp2.getRowCount()};
                temp2.addRow(nuevo);
            }

        }
        if (Femenino.getRowCount() > pro.size()) {

            try {
                DefaultTableModel temp2 = (DefaultTableModel) Femenino.getModel();
                Femenino.getModel();
                temp2.removeRow(temp2.getRowCount() - 1);

            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Fecha_ini = new com.toedter.calendar.JDateChooser();
        Fecha_fin = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Fechas = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        usuario = new javax.swing.JTable();
        Usuario = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Femenino = new javax.swing.JTable();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel1.setText("Rango de Fechas");

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        jLabel2.setText("Fecha Inicio");

        jLabel3.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        jLabel3.setText("Fecha Fin");

        jScrollPane1.setViewportView(Fechas);

        jButton1.setText("Buscar Productos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel4.setText("Tipo de Usuario");

        usuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre Producto", "Precio", "Tipo"
            }
        ));
        jScrollPane2.setViewportView(usuario);

        Usuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Niño", "Jovén", "Adúlto" }));

        jButton2.setText("Buscar Información");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel5.setText("Sexo Femenino Edad mayor a 12");

        Femenino.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Sexo", "Edad", "Producto"
            }
        ));
        jScrollPane4.setViewportView(Femenino);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(197, 197, 197))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(137, 137, 137))))
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(181, 181, 181)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Usuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Fecha_ini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Fecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(53, 53, 53))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Fecha_ini, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Fecha_fin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reporte1();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        reporte2();
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
            java.util.logging.Logger.getLogger(Reportes_pro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reportes_pro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reportes_pro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reportes_pro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Reportes_pro dialog = new Reportes_pro(new javax.swing.JFrame(), true);
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
    private com.toedter.calendar.JDateChooser Fecha_fin;
    private com.toedter.calendar.JDateChooser Fecha_ini;
    private javax.swing.JList<String> Fechas;
    private javax.swing.JTable Femenino;
    private javax.swing.JComboBox<String> Usuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable usuario;
    // End of variables declaration//GEN-END:variables
}
