/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo2;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static javax.management.remote.JMXConnectorFactory.connect;
import static javax.management.remote.JMXConnectorFactory.connect;
import static javax.rmi.PortableRemoteObject.connect;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author XXIVKVNGLRT
 */
public class FrmBarang extends javax.swing.JFrame {

    /**
     * Creates new form FrmBarang
     */
    
koneksi k;
Statement statement;
ResultSet resultSet;
int pil;


    public FrmBarang() {
        initComponents();
         
        k = new koneksi();
        tampilIcon();
       
        tampilTabel();
    }
    
    private void tampilIcon(){
        BtnTambah.setIcon(new
        ImageIcon("./gambar/add.png"));
        BtnKoreksi.setIcon(new
        ImageIcon("./gambar/edit.png"));
        BtnHapus.setIcon(new
        ImageIcon("./gambar/delete.png"));
        BtnSimpan.setIcon(new
        ImageIcon("./gambar/save.png"));
        BtnBatal.setIcon(new
        ImageIcon("./gambar/cancel.png"));
        BtnKeluar.setIcon(new 
        ImageIcon("./gambar/log_out.png"));
}
    
    private void tampilTabel(){
            Object
            header[]={"Kode","Nama Barang","Hrg Beli Barang","Hrg Jual Barang","Stok Barang","Stok Min"};
            DefaultTableModel modelBarang = new
            DefaultTableModel(null,header)
            {
            @Override
            // Membuat jTable read only
            public boolean isCellEditable(int row, int column)
            {
            return false;
            }
            };
            tabelBarang.setModel(modelBarang);
            int baris = tabelBarang.getRowCount();
            for (int i = 0; i < baris; i++) {
            modelBarang.removeRow(i);
            }
            String sql_select = "select * from barang";
            try {
            statement = k.con.createStatement();
            resultSet = statement.executeQuery(sql_select);
            while(resultSet.next()){
            String kodeBarang = resultSet.getString(1);
            String namaBarang = resultSet.getString(2);
            String hrgBeliBarang = resultSet.getString(3);
            String hrgJualBarang = resultSet.getString(4);
            String stokBarang = resultSet.getString(5);
            String stokMinBarang = resultSet.getString(6);
            String kolom[] = {kodeBarang,namaBarang,hrgJualBarang,hrgBeliBarang,stokBarang,stokMinBarang};
            modelBarang.addRow(kolom);
            }
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
}
    
    private void bersih(){
        mKodeBarang.setText(null);
        mNamaBarang.setText(null);
        mHargaBeliBarang.setText(null);
        mHargaJualBarang.setText(null);
        mStockBarang.setText(null);
        mStokMinBarang.setText(null);
}
    
    private void setTombol(boolean xTambah,boolean
        xKoreksi,boolean xHapus,
        boolean xSimpan,boolean xBatal,boolean xKeluar){
        BtnTambah.setEnabled(xTambah);
        BtnKoreksi.setEnabled(xKoreksi);
        BtnHapus.setEnabled(xHapus);
        BtnSimpan.setEnabled(xSimpan);
        BtnBatal.setEnabled(xBatal);
        BtnKeluar.setEnabled(xKeluar);
}
    
    private void setEdit(boolean yKode,boolean yNama,boolean
        yAlamat,boolean yKota,
        boolean yTelp,boolean yHP){
        mKodeBarang.setEnabled(yKode);
        mNamaBarang.setEnabled(yNama);
        mHargaBeliBarang.setEnabled(yAlamat);
        mHargaJualBarang.setEnabled(yKota);
        mStockBarang.setEnabled(yTelp);
        mStokMinBarang.setEnabled(yHP);
}
    
    private void simpanData(){
//        int  hrgJual, hrgBeli, stokBrg, stokMin, hrgg;
//        String kode, namaBrg;
//        
//      kode    =   mKodeBarang.getText();
//      namaBrg    =   mNamaBarang.getText();
//      hrgBeli=Integer.parseInt(mHargaBeliBarang.getText());
//      hrgJual=Integer.parseInt(mHargaJualBarang.getText());
//      stokBrg=Integer.parseInt(mStockBarang.getText());
//      stokMin=Integer.parseInt(mStokMinBarang.getText());
      
  
    String sql_insert = "insert into barang values ('"+mKodeBarang.getText()+"','"+ mNamaBarang.getText()+"','"+mHargaBeliBarang.getText()+"','"+mHargaJualBarang.getText() +"','"+mStockBarang.getText()+"','"+mStokMinBarang.getText()+"')";
     try {
         
        statement.executeUpdate(sql_insert);
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
        }
}
     
     private void koreksiData(){
        String sql_edit = "update barang set namabarang='"+mNamaBarang.getText()+"', hargabelibarang='"+mHargaBeliBarang.getText()+"', hargajualbarang='"+mHargaJualBarang.getText()+ "', stokbarang='"+ mStockBarang.getText()+"', stokminbarang='"+mStokMinBarang.getText()+ "' where kodebarang='"+mKodeBarang.getText()+"'";
        try {
            statement.executeUpdate(sql_edit);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
}
     
private void hapusData(){
String sql_delete = "delete from barang where kodebarang='"+mKodeBarang.getText()+"'";
try {
statement.executeUpdate(sql_delete);
} catch (Exception e) {
JOptionPane.showMessageDialog(null, e.getMessage());
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        mKodeBarang = new javax.swing.JTextField();
        mNamaBarang = new javax.swing.JTextField();
        mHargaBeliBarang = new javax.swing.JTextField();
        mHargaJualBarang = new javax.swing.JTextField();
        mStockBarang = new javax.swing.JTextField();
        mStokMinBarang = new javax.swing.JTextField();
        BtnTambah = new javax.swing.JButton();
        BtnKoreksi = new javax.swing.JButton();
        BtnHapus = new javax.swing.JButton();
        BtnSimpan = new javax.swing.JButton();
        BtnBatal = new javax.swing.JButton();
        BtnKeluar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pendataan Barang");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Kode Barang");

        jLabel2.setText("Nama Barang");

        jLabel3.setText("Harga Beli Barang");

        jLabel4.setText("Harga Jual Barang");

        jLabel5.setText("Stok Barang");

        jLabel6.setText("Stok Min Barang");

        mKodeBarang.setEnabled(false);
        mKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mKodeBarangActionPerformed(evt);
            }
        });

        mNamaBarang.setEnabled(false);

        mHargaBeliBarang.setEnabled(false);

        mHargaJualBarang.setEnabled(false);

        mStockBarang.setEnabled(false);

        mStokMinBarang.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mHargaBeliBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mHargaJualBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(mStokMinBarang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                        .addComponent(mStockBarang, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(mNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(mHargaBeliBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(mHargaJualBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(mStockBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(mStokMinBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BtnTambah.setText("Tambah");
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });

        BtnKoreksi.setText("Koreksi");
        BtnKoreksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKoreksiActionPerformed(evt);
            }
        });

        BtnHapus.setText("Hapus");
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });

        BtnSimpan.setText("Simpan");
        BtnSimpan.setEnabled(false);
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });

        BtnBatal.setText("Batal");
        BtnBatal.setEnabled(false);
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });

        BtnKeluar.setText("Keluar");
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });

        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kode", "Nama", "Alamat", "Kota", "Telp.", "HP"
            }
        ));
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBarang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(BtnTambah)
                        .addGap(18, 18, 18)
                        .addComponent(BtnKoreksi)
                        .addGap(18, 18, 18)
                        .addComponent(BtnHapus)
                        .addGap(18, 18, 18)
                        .addComponent(BtnSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(BtnBatal)
                        .addGap(18, 18, 18)
                        .addComponent(BtnKeluar)
                        .addGap(0, 126, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnTambah)
                    .addComponent(BtnKoreksi)
                    .addComponent(BtnHapus)
                    .addComponent(BtnSimpan)
                    .addComponent(BtnBatal)
                    .addComponent(BtnKeluar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        // TODO add your handling code here:
        
        if (pil==3){
        BtnSimpan.setText("Simpan");
        }
        pil = 5;
        setTombol(true, true, true, false, false, true);
        setEdit(false, false, false, false, false, false);
        bersih();
        BtnTambah.requestFocus();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnKoreksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKoreksiActionPerformed
        // TODO add your handling code here:pil=2;
        
        pil=2;
        setTombol(false, false, false, false, true, false);
        setEdit(true, false, false, false, false, false);
        mKodeBarang.requestFocus();
    }//GEN-LAST:event_BtnKoreksiActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        // TODO add your handling code here:
        
        pil = 3;
        setTombol(false, false, false, false, true, false);
        setEdit(true, false, false, false, false, false);
        mKodeBarang.requestFocus();
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        // TODO add your handling code here:
        
                // TODO add your handling code here:
        if (pil==1){
        simpanData();
        } else if (pil==2){
        koreksiData();
        }
        tampilTabel();
        pil = 4;
        setTombol(true, true, true, false, false, true);
        setEdit(false, false, false, false, false, false);
        bersih();
        BtnTambah.requestFocus();
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void mKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mKodeBarangActionPerformed
        // TODO add your handling code here:
        
        if (mKodeBarang.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Kode Barang masih kosong...","Kesalahan",JOptionPane.ERROR_MESSAGE);
        mKodeBarang.requestFocus();
        } else {
        // Button Tambah
        if (pil==1){
        String sql_select = "select * from barang where kodebarang='"+mKodeBarang.getText()+"'";
        try {
        statement = k.con.createStatement();
        resultSet = statement.executeQuery(sql_select);
        int baris=0;
        while (resultSet.next()){
        baris = resultSet.getRow();
        }
        if (baris==0){
        setEdit(false, true, true, true, true, true);
        BtnSimpan.setEnabled(true);
        mNamaBarang.requestFocus();
        } else {
        JOptionPane.showMessageDialog(null, "Kode Barang sudah ada...", "Kesalahan",JOptionPane.ERROR_MESSAGE);
        mKodeBarang.requestFocus();
        }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
        e.getMessage());
        }
        } // Akhir Button Tambah
        // Button Koreksi
        if (pil==2){
        String sql_select = "select * from barang where kodebarang='"+mKodeBarang.getText()+"'";
        try {
        statement = k.con.createStatement();
        resultSet = statement.executeQuery(sql_select);
        if (resultSet.next()){
        mNamaBarang.setText(resultSet.getString(2));
        mHargaBeliBarang.setText(resultSet.getString(3));
        mHargaJualBarang.setText(resultSet.getString(4));
        mStockBarang.setText(resultSet.getString(5));
        mStokMinBarang.setText(resultSet.getString(6));
        setEdit(false, true, true, true, true, true);
        BtnSimpan.setEnabled(true);
        mNamaBarang.requestFocus();
        } else {
        JOptionPane.showMessageDialog(null, "Kode Barang tidak diketemukan...","Kesalahan",JOptionPane.ERROR_MESSAGE);
        mKodeBarang.requestFocus();
        }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
        e.getMessage());
        }
        } // Akhir Button Koreksi
        // Button Hapus
        if (pil==3){
        String sql_select = "select * from barang where kodebarang='"+mKodeBarang.getText()+"'";
        try {
        statement = k.con.createStatement();
        resultSet = statement.executeQuery(sql_select);
        if (resultSet.next()){
        mNamaBarang.setText(resultSet.getString(2));
        mHargaBeliBarang.setText(resultSet.getString(3));
        mHargaJualBarang.setText(resultSet.getString(4));
        mStockBarang.setText(resultSet.getString(5));
        mStokMinBarang.setText(resultSet.getString(6));
        setEdit(false, false, false, false, false,
        false);
        int opsi = JOptionPane.showConfirmDialog(null,
        "Benarkah anda ingin menghapus data ini ?", "Penghapusan Data",
        JOptionPane.YES_NO_OPTION);
        if (opsi == JOptionPane.YES_OPTION){
        hapusData();
        }
        tampilTabel();
        setTombol(true, true, true, false, false,
        true);
        setEdit(false, false, false, false, false,
        false);
        bersih();
        pil=5;
        BtnTambah.requestFocus();
        } else {
        JOptionPane.showMessageDialog(null, "Kode Barang tidak diketemukan...","Kesalahan",JOptionPane.ERROR_MESSAGE);
        mKodeBarang.requestFocus();
        }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
        e.getMessage());
        }
        } // Akhir Button Hapus
        }
    }//GEN-LAST:event_mKodeBarangActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        // TODO add your handling code here:
        pil = 1;
        setTombol(false, false, false, false, true, false);
        setEdit(true, false, false, false, false, false);
        mKodeBarang.requestFocus();

    }//GEN-LAST:event_BtnTambahActionPerformed

    private void tabelBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseClicked
        // TODO add your handling code here:
        
         if (pil==2 || pil==3){
        mKodeBarang.setText(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 0).toString());
        mNamaBarang.setText(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 1).toString());
        mHargaBeliBarang.setText(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 2).toString());
        mHargaJualBarang.setText(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 3).toString());
        mStockBarang.setText(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 4).toString());
        mStokMinBarang.setText(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 5).toString());
        }
        if (pil == 2) {
        setEdit(false, true, true, true, true, true);
        BtnSimpan.setEnabled(true);
        mNamaBarang.requestFocus();
        }
        if (pil == 3) {
        mKodeBarang.setEnabled(false);
        int opsi = JOptionPane.showConfirmDialog(null, "Benarkah anda ingin menghapus data ini ?", "Penghapusan Data",
        JOptionPane.YES_NO_OPTION);
        if (opsi == JOptionPane.YES_OPTION){
        hapusData();
        }
        tampilTabel();
        setTombol(true, true, true, false, false, true);
        setEdit(false, false, false, false, false, false);
        bersih();
        pil=5;
        BtnTambah.requestFocus();
        }
    }//GEN-LAST:event_tabelBarangMouseClicked

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
            java.util.logging.Logger.getLogger(FrmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBarang().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBatal;
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton BtnKeluar;
    private javax.swing.JButton BtnKoreksi;
    private javax.swing.JButton BtnSimpan;
    private javax.swing.JButton BtnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mHargaBeliBarang;
    private javax.swing.JTextField mHargaJualBarang;
    private javax.swing.JTextField mKodeBarang;
    private javax.swing.JTextField mNamaBarang;
    private javax.swing.JTextField mStockBarang;
    private javax.swing.JTextField mStokMinBarang;
    private javax.swing.JTable tabelBarang;
    // End of variables declaration//GEN-END:variables

   
}
