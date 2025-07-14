package Vista;

//import Controlador.ReporteControlador;
//import Controlador.UsuarioControlador;
import DAO.ConexionBD;
import DAO.ReporteDAO;
import Modelo.Producto;
import Modelo.SesionUsuario;
import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

public class reporte extends javax.swing.JFrame {
    //private final ReporteControlador controlador;

    public reporte() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        aplicarRestriccionesPorRol();
        //this.controlador = new ReporteControlador();
    }
    private String rutaArchivoGenerado;
    
    private void aplicarRestriccionesPorRol() {
        String rol = SesionUsuario.getRol();

        if (SesionUsuario.esAdmin()) {
            PantallaTI.setEnabled(false);
        }

        if (SesionUsuario.esTrabajador()) {
            PantallaReportes.setEnabled(false);
            PantallaTI.setEnabled(false);
            PantallaDashboard.setEnabled(false);
        }

        if (SesionUsuario.esTI()) {
            PantallaReportes.setEnabled(false);
            PantallaDashboard.setEnabled(false);
            PantallaInventario.setEnabled(false);
        }
        
        if (SesionUsuario.esSupervisor()) {            
            PantallaTI.setEnabled(false); 
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNombreReporte = new javax.swing.JTextField();
        btnGenerar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        cmbInventario = new javax.swing.JComboBox<>();
        rbtnPDF = new javax.swing.JRadioButton();
        rbtnExcel = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtFechaInicial = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtFechaFinal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnDescargar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        LblDashboard = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        PantallaDashboard = new javax.swing.JButton();
        PantallaInventario = new javax.swing.JButton();
        PantallaTI = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        PantallaReportes = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(254, 241, 241));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel6.setText("GENERAR REPORTE DE INVENTARIO");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 204)));

        jLabel9.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel9.setText("NOMBRE DE REPORTE");

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel10.setText("SELECCIONAR");

        txtNombreReporte.setBackground(new java.awt.Color(255, 204, 204));
        txtNombreReporte.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNombreReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreReporteActionPerformed(evt);
            }
        });

        btnGenerar.setBackground(new java.awt.Color(51, 204, 0));
        btnGenerar.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnGenerar.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerar.setText("GENERAR");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(255, 102, 102));
        btnLimpiar.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        cmbInventario.setBackground(new java.awt.Color(255, 204, 204));
        cmbInventario.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        cmbInventario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inventario completo", "Stock alto", "Stock bajo", "Sin Stock" }));
        cmbInventario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbInventarioItemStateChanged(evt);
            }
        });

        rbtnPDF.setBackground(new java.awt.Color(255, 255, 255));
        rbtnPDF.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        rbtnPDF.setForeground(new java.awt.Color(51, 51, 51));
        rbtnPDF.setText("PDF");
        rbtnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnPDFActionPerformed(evt);
            }
        });

        rbtnExcel.setBackground(new java.awt.Color(255, 255, 255));
        rbtnExcel.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        rbtnExcel.setForeground(new java.awt.Color(51, 51, 51));
        rbtnExcel.setText("EXCEL");
        rbtnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnExcelActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel11.setText("SELECCIONAR TIPO DE ARCHIVO");

        jLabel12.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel12.setText("FECHA INICIAL");

        txtFechaInicial.setBackground(new java.awt.Color(255, 204, 204));
        txtFechaInicial.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtFechaInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaInicialActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel13.setText("FECHA FINAL");

        txtFechaFinal.setBackground(new java.awt.Color(255, 204, 204));
        txtFechaFinal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel14.setText("PONER LAS FECHAS EN EL FORMATO: \"DD/MM/YYYY\"");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombreReporte))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(cmbInventario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbtnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addGap(66, 66, 66)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFechaFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                    .addComponent(rbtnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechaInicial)))
                            .addComponent(jLabel14)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btnGenerar)
                        .addGap(110, 110, 110)
                        .addComponent(btnLimpiar)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNombreReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnExcel)
                    .addComponent(rbtnPDF))
                .addGap(25, 25, 25)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporte.png"))); // NOI18N

        btnDescargar.setBackground(new java.awt.Color(0, 204, 204));
        btnDescargar.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnDescargar.setForeground(new java.awt.Color(255, 255, 255));
        btnDescargar.setText("DESCARGAR");
        btnDescargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Script MT Bold", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 102, 102));
        jLabel4.setText("Reportes de Inventario");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDescargar)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDescargar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));

        LblDashboard.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        LblDashboard.setForeground(new java.awt.Color(255, 255, 255));
        LblDashboard.setText("Dashboard");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Inventario");

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Reportes");

        PantallaDashboard.setBackground(new java.awt.Color(255, 133, 133));
        PantallaDashboard.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        PantallaDashboard.setForeground(new java.awt.Color(255, 255, 255));
        PantallaDashboard.setText("+");
        PantallaDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PantallaDashboardActionPerformed(evt);
            }
        });

        PantallaInventario.setBackground(new java.awt.Color(255, 133, 133));
        PantallaInventario.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        PantallaInventario.setForeground(new java.awt.Color(255, 255, 255));
        PantallaInventario.setText("+");
        PantallaInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PantallaInventarioActionPerformed(evt);
            }
        });

        PantallaTI.setBackground(new java.awt.Color(255, 133, 133));
        PantallaTI.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        PantallaTI.setForeground(new java.awt.Color(255, 255, 255));
        PantallaTI.setText("+");
        PantallaTI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PantallaTIActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pngwing.com.png"))); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Área de TI");

        PantallaReportes.setBackground(new java.awt.Color(255, 133, 133));
        PantallaReportes.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        PantallaReportes.setForeground(new java.awt.Color(255, 255, 255));
        PantallaReportes.setText("+");
        PantallaReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PantallaReportesActionPerformed(evt);
            }
        });

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setAlignmentX(2.0F);
        jSeparator2.setAlignmentY(2.0F);

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setAlignmentX(2.0F);
        jSeparator3.setAlignmentY(2.0F);

        jSeparator4.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator4.setAlignmentX(2.0F);
        jSeparator4.setAlignmentY(2.0F);

        jSeparator5.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator5.setAlignmentX(2.0F);
        jSeparator5.setAlignmentY(2.0F);

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new java.awt.Font("Lucida Console", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("LOG OUT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PantallaDashboard)
                    .addComponent(PantallaInventario)
                    .addComponent(PantallaTI)
                    .addComponent(PantallaReportes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LblDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel20)
                .addGap(32, 32, 32)
                .addComponent(jLabel5)
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LblDashboard)
                            .addComponent(PantallaDashboard))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(PantallaInventario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel21))
                    .addComponent(PantallaTI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PantallaReportes)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
    txtNombreReporte.setText("");
    cmbInventario.setSelectedIndex(0);
    rbtnPDF.setSelected(false);
    rbtnExcel.setSelected(false);
    txtFechaInicial.setText("");
    txtFechaFinal.setText("");            
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
     try {
        String nombre = txtNombreReporte.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre para el reporte.");
            return;
        }

        if (!rbtnPDF.isSelected() && !rbtnExcel.isSelected()) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de archivo (PDF o Excel).");
            return;
        }

        // Parsear fechas del JTextField
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date fechaIni = sdf.parse(txtFechaInicial.getText());
        java.util.Date fechaFin = sdf.parse(txtFechaFinal.getText());

        if (fechaIni.after(fechaFin)) {
            JOptionPane.showMessageDialog(this, "La fecha inicial no puede ser mayor a la final.");
            return;
        }

        // Ajustar horas para incluir el día completo
        Calendar calInicio = Calendar.getInstance();
        calInicio.setTime(fechaIni);
        calInicio.set(Calendar.HOUR_OF_DAY, 0);
        calInicio.set(Calendar.MINUTE, 0);
        calInicio.set(Calendar.SECOND, 0);
        calInicio.set(Calendar.MILLISECOND, 0);

        Calendar calFin = Calendar.getInstance();
        calFin.setTime(fechaFin);
        calFin.set(Calendar.HOUR_OF_DAY, 23);
        calFin.set(Calendar.MINUTE, 59);
        calFin.set(Calendar.SECOND, 59);
        calFin.set(Calendar.MILLISECOND, 999);

        Timestamp fechaInicio = new Timestamp(calInicio.getTimeInMillis());
        Timestamp fechaFinal = new Timestamp(calFin.getTimeInMillis());

        // Obtener productos comparativos
        ReporteDAO dao = new ReporteDAO();
        List<ReporteDAO.ProductoComparativo> productos = dao.obtenerComparativaDeStock(fechaInicio, fechaFinal);

        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron datos de movimientos en ese rango de fechas.");
            return;
        }

        String tipo = rbtnPDF.isSelected() ? "PDF" : "EXCEL";
        String ruta = "C:\\Reportes\\";
        new File(ruta).mkdirs();
        rutaArchivoGenerado = ruta + nombre + (tipo.equals("PDF") ? ".pdf" : ".xlsx");

        if (tipo.equals("PDF")) {
            Reporte.ReporteExportador.exportarPDF(productos, rutaArchivoGenerado);
        } else {
            Reporte.ReporteExportador.exportarExcel(productos, rutaArchivoGenerado);
        }

        JOptionPane.showMessageDialog(this, "Reporte generado exitosamente.");

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al generar reporte: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarActionPerformed
     try {
        if (rutaArchivoGenerado != null && new File(rutaArchivoGenerado).exists()) {
            Desktop.getDesktop().open(new File(rutaArchivoGenerado));
        } else {
            JOptionPane.showMessageDialog(this, "Primero genera un reporte.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "No se puede abrir el archivo: " + e.getMessage());
    }
    }//GEN-LAST:event_btnDescargarActionPerformed

    private void cmbInventarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbInventarioItemStateChanged
       
    }//GEN-LAST:event_cmbInventarioItemStateChanged

    private void rbtnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnPDFActionPerformed
        
    }//GEN-LAST:event_rbtnPDFActionPerformed

    private void rbtnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnExcelActionPerformed
        
    }//GEN-LAST:event_rbtnExcelActionPerformed

    private void PantallaDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PantallaDashboardActionPerformed
        dashboard db = new dashboard ();
        db.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_PantallaDashboardActionPerformed

    private void PantallaInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PantallaInventarioActionPerformed
        inventario inv1 = new inventario ();
        inv1.setVisible (true);
        this.dispose();
    }//GEN-LAST:event_PantallaInventarioActionPerformed

    private void PantallaTIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PantallaTIActionPerformed
        try {
        Connection conn = ConexionBD.obtenerConexion(); // puede lanzar SQLException
        TI edt1 = new TI(conn);
        edt1.setVisible(true);
        this.dispose();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al abrir Área TI: " + ex.getMessage());
    }
    }//GEN-LAST:event_PantallaTIActionPerformed

    private void PantallaReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PantallaReportesActionPerformed
        reporte rep1=new reporte();
        rep1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_PantallaReportesActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        login lg1= new login ();
        lg1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtNombreReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreReporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreReporteActionPerformed

    private void txtFechaInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaInicialActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblDashboard;
    private javax.swing.JButton PantallaDashboard;
    private javax.swing.JButton PantallaInventario;
    private javax.swing.JButton PantallaReportes;
    private javax.swing.JButton PantallaTI;
    private javax.swing.JButton btnDescargar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbInventario;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JRadioButton rbtnExcel;
    private javax.swing.JRadioButton rbtnPDF;
    private javax.swing.JTextField txtFechaFinal;
    private javax.swing.JTextField txtFechaInicial;
    private javax.swing.JTextField txtNombreReporte;
    // End of variables declaration//GEN-END:variables
}
