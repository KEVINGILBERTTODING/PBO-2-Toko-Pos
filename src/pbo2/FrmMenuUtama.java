/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo2;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author XXIVKVNGLRT
 */
public class FrmMenuUtama extends javax.swing.JFrame {

    /**
     * Creates new form FrmMenuUtama
     */
     koneksi k; 
    public FrmMenuUtama() {
        
        initComponents();
        k = new koneksi();
        tampilIcon();
        
         
    }
    
     
   

    
    private void tampilIcon(){
        mnuFile.setIcon(new ImageIcon("./gambar/File_64x64.png"));
        mnuMaster.setIcon(new
        ImageIcon("./gambar/Master_64x64.png"));
         mnuTransaksi.setIcon(new
        ImageIcon("./gambar/Shopping_64x64.png"));
         mnuLaporan.setIcon(new
        ImageIcon("./gambar/Report_64x64.png"));
         mnuAbout.setIcon(new
        ImageIcon("./gambar/About_64x64.png"));
         mnuKeluar.setIcon(new
        ImageIcon("./gambar/Keluar_16x16.png"));
          mnuKonsumen.setIcon(new
        ImageIcon("./gambar/Customer_16x16.png"));
          mnuSupplier.setIcon(new
        ImageIcon("./gambar/Supplier_16x16.png"));
          mnuBarang.setIcon(new
        ImageIcon("./gambar/Barang_16x16.png"));
         mnuPenjualan.setIcon(new
        ImageIcon("./gambar/Penjualan_16x16.png"));
         mnuPembelian.setIcon(new
        ImageIcon("./gambar/Pembelian_16x16.png"));
          mnuReturPembelian.setIcon(new
        ImageIcon("./gambar/ReturPembelian_16x16.png"));
          mnuReturPenjualan.setIcon(new
        ImageIcon("./gambar/ReturPenjualan_16x16.png"));
         mnuLapKonsumen.setIcon(new
        ImageIcon("./gambar/Customer_16x16.png"));
         mnuLapSupplier.setIcon(new
        ImageIcon("./gambar/Supplier_16x16.png"));
         mnuLapBarang.setIcon(new
        ImageIcon("./gambar/Barang_16x16.png"));
         mnuLapPenjualan.setIcon(new
        ImageIcon("./gambar/Penjualan_16x16.png"));
         mnuLapPembelian.setIcon(new
        ImageIcon("./gambar/Pembelian_16x16.png"));
         mnuLapReturPenjualan.setIcon(new
        ImageIcon("./gambar/LapPenjualan_16x16.png"));
         mnuLapReturPembelian.setIcon(new
        ImageIcon("./gambar/LapPembelian_16x16.png"));
         mnuLapKonsumen.setIcon(new
        ImageIcon("./gambar/LapKonsumen_16x16.png"));
         mnuLapSupplier.setIcon(new
        ImageIcon("./gambar/LapSupplier_16x16.png"));
         mnuLapBarang.setIcon(new
        ImageIcon("./gambar/LapBarang_16x16.png"));
         mnuLapPenjualan.setIcon(new
        ImageIcon("./gambar/LapPenjualan_16x16.png"));
         mnuLapPembelian.setIcon(new
        ImageIcon("./gambar/LapPembelian_16x16.png"));
         mnuLapReturPenjualan.setIcon(new
        ImageIcon("./gambar/LapReturPenjualan_16x16.png"));
         mnuLapReturPembelian.setIcon(new
        ImageIcon("./gambar/LapReturPembelian_16x16.png")); 
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
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuKeluar = new javax.swing.JMenuItem();
        mnuMaster = new javax.swing.JMenu();
        mnuKonsumen = new javax.swing.JMenuItem();
        mnuSupplier = new javax.swing.JMenuItem();
        mnuBarang = new javax.swing.JMenuItem();
        mnuTransaksi = new javax.swing.JMenu();
        mnuPenjualan = new javax.swing.JMenuItem();
        mnuPembelian = new javax.swing.JMenuItem();
        mnuReturPenjualan = new javax.swing.JMenuItem();
        mnuReturPembelian = new javax.swing.JMenuItem();
        mnuLaporan = new javax.swing.JMenu();
        mnuLapKonsumen = new javax.swing.JMenuItem();
        mnuLapSupplier = new javax.swing.JMenuItem();
        mnuLapBarang = new javax.swing.JMenuItem();
        mnuLapPenjualan = new javax.swing.JMenuItem();
        mnuLapPembelian = new javax.swing.JMenuItem();
        mnuLapReturPenjualan = new javax.swing.JMenuItem();
        mnuLapReturPembelian = new javax.swing.JMenuItem();
        mnuAbout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Utama");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
        );

        mnuFile.setText("File");
        mnuFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnuFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnuFile.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        mnuFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mnuKeluar.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuKeluar.setText("Keluar");
        mnuKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuKeluarActionPerformed(evt);
            }
        });
        mnuFile.add(mnuKeluar);

        jMenuBar1.add(mnuFile);

        mnuMaster.setText("Master");
        mnuMaster.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnuMaster.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnuMaster.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        mnuMaster.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mnuKonsumen.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuKonsumen.setText("Konsumen");
        mnuKonsumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuKonsumenActionPerformed(evt);
            }
        });
        mnuMaster.add(mnuKonsumen);

        mnuSupplier.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuSupplier.setText("Supplier");
        mnuSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSupplierActionPerformed(evt);
            }
        });
        mnuMaster.add(mnuSupplier);

        mnuBarang.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuBarang.setText("Barang");
        mnuBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuBarangActionPerformed(evt);
            }
        });
        mnuMaster.add(mnuBarang);

        jMenuBar1.add(mnuMaster);

        mnuTransaksi.setText("Transaksi");
        mnuTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnuTransaksi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnuTransaksi.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        mnuTransaksi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mnuPenjualan.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuPenjualan.setText("Penjualan");
        mnuPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPenjualanActionPerformed(evt);
            }
        });
        mnuTransaksi.add(mnuPenjualan);

        mnuPembelian.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuPembelian.setText("Pembelian");
        mnuPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPembelianActionPerformed(evt);
            }
        });
        mnuTransaksi.add(mnuPembelian);

        mnuReturPenjualan.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuReturPenjualan.setText("Retur Penjualan");
        mnuReturPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuReturPenjualanActionPerformed(evt);
            }
        });
        mnuTransaksi.add(mnuReturPenjualan);

        mnuReturPembelian.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuReturPembelian.setText("Retur Pembelian");
        mnuReturPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuReturPembelianActionPerformed(evt);
            }
        });
        mnuTransaksi.add(mnuReturPembelian);

        jMenuBar1.add(mnuTransaksi);

        mnuLaporan.setText("Laporan");
        mnuLaporan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnuLaporan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnuLaporan.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        mnuLaporan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mnuLapKonsumen.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuLapKonsumen.setText("Konsumen");
        mnuLapKonsumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLapKonsumenActionPerformed(evt);
            }
        });
        mnuLaporan.add(mnuLapKonsumen);

        mnuLapSupplier.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuLapSupplier.setText("Supplier");
        mnuLaporan.add(mnuLapSupplier);

        mnuLapBarang.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuLapBarang.setText("Barang");
        mnuLapBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLapBarangActionPerformed(evt);
            }
        });
        mnuLaporan.add(mnuLapBarang);

        mnuLapPenjualan.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuLapPenjualan.setText("Penjualan");
        mnuLaporan.add(mnuLapPenjualan);

        mnuLapPembelian.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuLapPembelian.setText("Pembelian");
        mnuLaporan.add(mnuLapPembelian);

        mnuLapReturPenjualan.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuLapReturPenjualan.setText("Retur Penjualan");
        mnuLaporan.add(mnuLapReturPenjualan);

        mnuLapReturPembelian.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mnuLapReturPembelian.setText("Retur Pembelian");
        mnuLaporan.add(mnuLapReturPembelian);

        jMenuBar1.add(mnuLaporan);

        mnuAbout.setText("About");
        mnuAbout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnuAbout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnuAbout.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        mnuAbout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMenuBar1.add(mnuAbout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuReturPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuReturPembelianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuReturPembelianActionPerformed

    private void mnuLapKonsumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLapKonsumenActionPerformed
        // TODO add your handling code here:
         String filename = "src/pbo2/LapKonsumen.jasper";
        Map param = new HashMap();
        try {
        JasperPrint cetak = JasperFillManager.fillReport(filename, param, k.con);
        JasperViewer.viewReport(cetak, false);
        } catch (Exception e){
        }
    }//GEN-LAST:event_mnuLapKonsumenActionPerformed

    private void mnuLapBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLapBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuLapBarangActionPerformed

    private void mnuKonsumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuKonsumenActionPerformed
        // TODO add your handling code here:
        
        new FrmKonsumen().setVisible(true);
    }//GEN-LAST:event_mnuKonsumenActionPerformed

    private void mnuKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuKeluarActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_mnuKeluarActionPerformed

    private void mnuSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSupplierActionPerformed
        // TODO add your handling code here:
         new FrmSupplier().setVisible(true);
    }//GEN-LAST:event_mnuSupplierActionPerformed

    private void mnuBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuBarangActionPerformed
        // TODO add your handling code here:
        new FrmBarang().setVisible(true);
    }//GEN-LAST:event_mnuBarangActionPerformed

    private void mnuPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPenjualanActionPerformed
        // TODO add your handling code here:
        new FrmPenjualan().setVisible(true);
    }//GEN-LAST:event_mnuPenjualanActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("./gambar/mainicon.png");
        setIconImage(icon.getImage());
    }//GEN-LAST:event_formWindowActivated

    private void mnuPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPembelianActionPerformed
        // TODO add your handling code here:
        new FrmPembelian().setVisible(true);
    }//GEN-LAST:event_mnuPembelianActionPerformed

    private void mnuReturPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuReturPenjualanActionPerformed
        // TODO add your handling code here:
        new FrmReturPenjualan().setVisible(true);
    }//GEN-LAST:event_mnuReturPenjualanActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Membuat form maksimal 
                FrmMenuUtama maks = new FrmMenuUtama();
                maks.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                maks.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu mnuAbout;
    private javax.swing.JMenuItem mnuBarang;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuKeluar;
    private javax.swing.JMenuItem mnuKonsumen;
    private javax.swing.JMenuItem mnuLapBarang;
    private javax.swing.JMenuItem mnuLapKonsumen;
    private javax.swing.JMenuItem mnuLapPembelian;
    private javax.swing.JMenuItem mnuLapPenjualan;
    private javax.swing.JMenuItem mnuLapReturPembelian;
    private javax.swing.JMenuItem mnuLapReturPenjualan;
    private javax.swing.JMenuItem mnuLapSupplier;
    private javax.swing.JMenu mnuLaporan;
    private javax.swing.JMenu mnuMaster;
    private javax.swing.JMenuItem mnuPembelian;
    private javax.swing.JMenuItem mnuPenjualan;
    private javax.swing.JMenuItem mnuReturPembelian;
    private javax.swing.JMenuItem mnuReturPenjualan;
    private javax.swing.JMenuItem mnuSupplier;
    private javax.swing.JMenu mnuTransaksi;
    // End of variables declaration//GEN-END:variables

   
}
