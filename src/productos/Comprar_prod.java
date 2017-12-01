/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin
 */
public class Comprar_prod extends javax.swing.JDialog {

    /**
     * Creates new form Comprar_prod
     */
    private Connection connection = null;
    private ResultSet rs = null;
    private Statement s = null;
    private DefaultComboBoxModel<Productos_herencia> pro;

    public Comprar_prod(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Comprar un Producto");
        Productos_user.setEnabled(false);
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

    public void insertarCompra() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Conexion_Base_datos();
        try {
            String nombre = Nombre_user.getText();
            int edad = Integer.parseInt(Edad_user.getText());
            String sexo = Sexo_user.getSelectedItem().toString();
            String fecha = formatter.format(Fecha_user.getDate());

            Productos_herencia he = (Productos_herencia) Productos_user.getSelectedItem();

            Compras_herencia pro = new Compras_herencia(nombre, sexo, edad, he.getId_prod(), fecha);
            s = connection.createStatement();
            int z = s.executeUpdate("INSERT INTO compras (nombre, edad, sexo, id_pro, fecha) VALUES ('" + pro.getNombre() + "', '" + pro.getPrecio() + "', '" + pro.getSexo() + "', '" + pro.getId_prod() + "', '" + pro.getFecha() + "' )");
            if (z == 1) {
                JOptionPane.showMessageDialog(null, "Se realizo la Compra de manera exitosa");
                Nombre_user.setText("");
                Edad_user.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "Error al insertar el Código, Ingrese Números en el Codigo!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la Compra!" + e);
        }

    }

    public ArrayList<Productos_herencia> obtenerproniño() {
        ArrayList<Productos_herencia> listamarca = new ArrayList();

        Conexion_Base_datos();
        try {

            s = connection.createStatement();
            rs = s.executeQuery("SELECT * FROM productos WHERE tipo_prod = 'Niño'");

            while (rs.next()) {
                String id = rs.getString("id_prod");
                String nombre = rs.getString("nombre_prod");
                String precio = rs.getString("precio_prod");
                String tipo = rs.getString("tipo_prod");
                Productos_herencia marca = new Productos_herencia(nombre, Integer.parseInt(id), tipo, Integer.parseInt(precio));

                listamarca.add(marca);

            }
        } catch (Exception e) {
            System.out.println("Error de conexión");
        }

        return listamarca;
    }

    public ArrayList<Productos_herencia> obtenerprojoven() {
        ArrayList<Productos_herencia> listamarca = new ArrayList();

        Conexion_Base_datos();
        try {

            s = connection.createStatement();
            rs = s.executeQuery("SELECT * FROM productos WHERE tipo_prod = 'Jovén'");

            while (rs.next()) {
                String id = rs.getString("id_prod");
                String nombre = rs.getString("nombre_prod");
                String precio = rs.getString("precio_prod");
                String tipo = rs.getString("tipo_prod");
                Productos_herencia marca = new Productos_herencia(nombre, Integer.parseInt(id), tipo, Integer.parseInt(precio));

                listamarca.add(marca);

            }
        } catch (Exception e) {
            System.out.println("Error de conexión");
        }

        return listamarca;
    }

    public ArrayList<Productos_herencia> obtenerproadulto() {
        ArrayList<Productos_herencia> listamarca = new ArrayList();

        Conexion_Base_datos();
        try {

            s = connection.createStatement();
            rs = s.executeQuery("SELECT * FROM productos WHERE tipo_prod = 'Adúlto'");

            while (rs.next()) {
                String id = rs.getString("id_prod");
                String nombre = rs.getString("nombre_prod");
                String precio = rs.getString("precio_prod");
                String tipo = rs.getString("tipo_prod");
                Productos_herencia marca = new Productos_herencia(nombre, Integer.parseInt(id), tipo, Integer.parseInt(precio));

                listamarca.add(marca);

            }
        } catch (Exception e) {
            System.out.println("Error de conexión");
        }

        return listamarca;
    }

    public void llenarCombo() {
        try {
            int edad = Integer.parseInt(Edad_user.getText());

            if (edad >= 0 & edad <= 12) {
                Conexion_Base_datos();
                try {

                    pro = new DefaultComboBoxModel<Productos_herencia>();
                    ArrayList<Productos_herencia> listamarca = new ArrayList();
                    listamarca = obtenerproniño();

                    for (Productos_herencia marca2 : listamarca) {
                        pro.addElement(marca2);
                    }
                    Productos_user.setModel(pro);
                } catch (Exception e) {
                    System.out.println("Error de conexión");
                }
            } else if (edad >= 13 & edad <= 17) {
                Conexion_Base_datos();
                try {

                    pro = new DefaultComboBoxModel<Productos_herencia>();
                    ArrayList<Productos_herencia> listamarca = new ArrayList();
                    listamarca = obtenerprojoven();

                    for (Productos_herencia marca2 : listamarca) {
                        pro.addElement(marca2);
                    }
                    Productos_user.setModel(pro);
                } catch (Exception e) {
                    System.out.println("Error de conexión");
                }
            } else if (edad >= 18) {

                Conexion_Base_datos();
                try {

                    pro = new DefaultComboBoxModel<Productos_herencia>();
                    ArrayList<Productos_herencia> listamarca = new ArrayList();
                    listamarca = obtenerproadulto();

                    for (Productos_herencia marca2 : listamarca) {
                        pro.addElement(marca2);
                    }
                    Productos_user.setModel(pro);
                } catch (Exception e) {
                    System.out.println("Error de conexión");
                }

            }
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese su Edad para Cargar los Productos");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Nombre_user = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Edad_user = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Sexo_user = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        Fecha_user = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        Productos_user = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Sitka Text", 3, 24)); // NOI18N
        jLabel1.setText("Compre un Producto");

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        jLabel2.setText("Ingrese su Nombre");

        jLabel3.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        jLabel3.setText("Ingrese su Edad");

        Edad_user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Edad_userKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        jLabel4.setText("Sexo");

        Sexo_user.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino" }));

        jLabel5.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        jLabel5.setText("Seleccione una Fecha Compra");

        jLabel6.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel6.setText("Productos");

        jButton1.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jButton1.setText("Realizar Compra");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        jButton2.setText("Mostrar Productos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Atrás");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(170, 170, 170))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Productos_user, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Nombre_user)
                            .addComponent(Edad_user)
                            .addComponent(Sexo_user, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Fecha_user, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                        .addGap(74, 74, 74)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton3))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Nombre_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(26, 26, 26)
                .addComponent(Productos_user, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Edad_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Sexo_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(Fecha_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Edad_userKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Edad_userKeyTyped
        char c = evt.getKeyChar();

        if (Character.isLetter(c)) {
            getToolkit().beep();

            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo Números");
        }
    }//GEN-LAST:event_Edad_userKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Productos_user.setEnabled(true);
        llenarCombo();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        Menú me = new Menú(null, true);
        me.pack();
        me.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insertarCompra();
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
            java.util.logging.Logger.getLogger(Comprar_prod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Comprar_prod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Comprar_prod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Comprar_prod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Comprar_prod dialog = new Comprar_prod(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField Edad_user;
    private com.toedter.calendar.JDateChooser Fecha_user;
    private javax.swing.JTextField Nombre_user;
    private javax.swing.JComboBox<Productos_herencia> Productos_user;
    private javax.swing.JComboBox<String> Sexo_user;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
