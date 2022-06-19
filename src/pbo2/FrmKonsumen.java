/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo2;


import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author XXIVKVNGLRT
 */
public class FrmKonsumen extends javax.swing.JFrame {

    /**
     * Creates new form FrmKonsumen
     */
    
koneksi k;
Statement statement;
ResultSet resultSet;
int pil;


    public FrmKonsumen() {
        initComponents();
        k = new koneksi();
        tampilIcon();
        tampilTabel();
        changeColor();
    }
    
    private void tampilIcon(){
        BtnTambah.setIcon(new
        ImageIcon("./gambar/Add_16x16.png"));
        BtnKoreksi.setIcon(new
        ImageIcon("./gambar/edit.png"));
        BtnHapus.setIcon(new
        ImageIcon("./gambar/delete.png"));
        BtnSimpan.setIcon(new
        ImageIcon("./gambar/save.png"));
        BtnBatal.setIcon(new
        ImageIcon("./gambar/Cancel_16x16.png"));
        BtnKeluar.setIcon(new 
        ImageIcon("./gambar/log_out.png"));
        
}
    
    private void changeColor() {
        BtnTambah.setBackground(Color.decode("#0A84FF"));
        BtnKoreksi.setBackground(Color.decode("#5E5CE6"));
        BtnSimpan.setBackground(Color.decode("#0A84FF"));
        BtnBatal.setBackground(Color.decode("#E81123"));
        BtnHapus.setBackground(Color.decode("#E81123"));
        BtnKeluar.setBackground(Color.decode("#FF453A"));
    }
    
   
    
    private void tampilTabel(){
            Object
            header[]={"Kode","Nama","Alamat","Kota","Telp.","HP"};
            DefaultTableModel modelKonsumen = new
            DefaultTableModel(null,header)
            {
            @Override
            // Membuat jTable read only
            public boolean isCellEditable(int row, int column)
            {
            return false;
            }
            };
            tabelKonsumen.setModel(modelKonsumen);
            int baris = tabelKonsumen.getRowCount();
            for (int i = 0; i < baris; i++) {
            modelKonsumen.removeRow(i);
            }
            String sql_select = "select * from konsumen";
            try {
            statement = k.con.createStatement();
            resultSet = statement.executeQuery(sql_select);
            while(resultSet.next()){
            String kode = resultSet.getString(1);
            String nama = resultSet.getString(2);
            String alamat = resultSet.getString(3);
            String kota = resultSet.getString(4);
            String telp = resultSet.getString(5);
            String hp = resultSet.getString(6);
            String kolom[] = {kode,nama,alamat,kota,telp,hp};
            modelKonsumen.addRow(kolom);
            }
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
}
    
    private void bersih(){
        mKodeKonsumen.setText(null);
        mNamaKonsumen.setText(null);
        mAlamatKonsumen.setText(null);
        mKotaKonsumen.setText(null);
        mTelpKonsumen.setText(null);
        mHPKonsumen.setText(null);
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
        mKodeKonsumen.setEnabled(yKode);
        mNamaKonsumen.setEnabled(yNama);
        mAlamatKonsumen.setEnabled(yAlamat);
        mKotaKonsumen.setEnabled(yKota);
        mTelpKonsumen.setEnabled(yTelp);
        mHPKonsumen.setEnabled(yHP);
}
    
    private void simpanData(){
        String sql_insert = "insert into konsumen values ('"+mKodeKonsumen.getText()+"','"+ mNamaKonsumen.getText()+"','"+mAlamatKonsumen.getText()+"','"+mKotaKonsumen.getText() +"','"+mTelpKonsumen.getText()+"','"+mHPKonsumen.getText()+"')";
        try {
        statement.executeUpdate(sql_insert);
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
        }
}
     
     private void koreksiData(){
        String sql_edit = "update konsumen set namakonsumen='"+mNamaKonsumen.getText()+"', alamatkonsumen='"+mAlamatKonsumen.getText()+"', kotakonsumen='"+mKotaKonsumen.getText()+ "', telpkonsumen='"+ mTelpKonsumen.getText()+"', hpkonsumen='"+mHPKonsumen.getText()+ "' where kodekonsumen='"+mKodeKonsumen.getText()+"'";
        try {
            statement.executeUpdate(sql_edit);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
}
     
private void hapusData(){
String sql_delete = "delete from konsumen where kodekonsumen='"+mKodeKonsumen.getText()+"'";
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

        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        mKodeKonsumen = new javax.swing.JTextField();
        mNamaKonsumen = new javax.swing.JTextField();
        mAlamatKonsumen = new javax.swing.JTextField();
        mKotaKonsumen = new javax.swing.JTextField();
        mTelpKonsumen = new javax.swing.JTextField();
        mHPKonsumen = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKonsumen = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        BtnTambah = new javax.swing.JButton();
        BtnKoreksi = new javax.swing.JButton();
        BtnHapus = new javax.swing.JButton();
        BtnSimpan = new javax.swing.JButton();
        BtnBatal = new javax.swing.JButton();
        BtnKeluar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pendataan Konsumen");
        setBackground(new java.awt.Color(102, 102, 102));

        jPanel3.setBackground(new java.awt.Color(28, 28, 30));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(243, 243, 244));
        jLabel7.setText("PENDATAAN KONSUMEN");

        jPanel1.setBackground(new java.awt.Color(44, 44, 46));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(243, 243, 244));
        jLabel1.setText("Kode");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(243, 243, 244));
        jLabel2.setText("Nama");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(243, 243, 244));
        jLabel3.setText("Alamat");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(243, 243, 244));
        jLabel4.setText("Kota");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(243, 243, 244));
        jLabel5.setText("Telp");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(243, 243, 244));
        jLabel6.setText("HP");

        mKodeKonsumen.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mKodeKonsumen.setEnabled(false);
        mKodeKonsumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mKodeKonsumenActionPerformed(evt);
            }
        });

        mNamaKonsumen.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mNamaKonsumen.setEnabled(false);

        mAlamatKonsumen.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mAlamatKonsumen.setEnabled(false);

        mKotaKonsumen.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mKotaKonsumen.setEnabled(false);

        mTelpKonsumen.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mTelpKonsumen.setEnabled(false);

        mHPKonsumen.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        mHPKonsumen.setEnabled(false);

        tabelKonsumen.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tabelKonsumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Nama", "Alamat", "Kota", "Telp.", "HP"
            }
        ));
        tabelKonsumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKonsumenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelKonsumen);

        jPanel5.setBackground(new java.awt.Color(28, 28, 30));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mKodeKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mAlamatKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mNamaKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mKotaKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(mHPKonsumen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                .addComponent(mTelpKonsumen, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(25, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mKodeKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(mNamaKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(mAlamatKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(mKotaKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(mTelpKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(mHPKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel2.setBackground(new java.awt.Color(28, 28, 30));

        BtnTambah.setBackground(new java.awt.Color(10, 132, 255));
        BtnTambah.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        BtnTambah.setForeground(new java.awt.Color(255, 255, 255));
        BtnTambah.setText("Tambah");
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });

        BtnKoreksi.setBackground(new java.awt.Color(94, 92, 230));
        BtnKoreksi.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        BtnKoreksi.setForeground(new java.awt.Color(255, 255, 255));
        BtnKoreksi.setText("Koreksi");
        BtnKoreksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKoreksiActionPerformed(evt);
            }
        });

        BtnHapus.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        BtnHapus.setForeground(new java.awt.Color(255, 255, 255));
        BtnHapus.setText("Hapus");
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });

        BtnSimpan.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        BtnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        BtnSimpan.setText("Simpan");
        BtnSimpan.setEnabled(false);
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });

        BtnBatal.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        BtnBatal.setForeground(new java.awt.Color(255, 255, 255));
        BtnBatal.setText("Batal");
        BtnBatal.setEnabled(false);
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });

        BtnKeluar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        BtnKeluar.setForeground(new java.awt.Color(255, 255, 255));
        BtnKeluar.setText("Keluar");
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(44, 44, 46));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnTambah)
                    .addComponent(BtnKoreksi)
                    .addComponent(BtnHapus)
                    .addComponent(BtnSimpan)
                    .addComponent(BtnBatal)
                    .addComponent(BtnKeluar))
                .addGap(27, 27, 27)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(500, 500, 500)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
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
        mKodeKonsumen.requestFocus();
    }//GEN-LAST:event_BtnKoreksiActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        // TODO add your handling code here:
        
        pil = 3;
        setTombol(false, false, false, false, true, false);
        setEdit(true, false, false, false, false, false);
        mKodeKonsumen.requestFocus();
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

    private void mKodeKonsumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mKodeKonsumenActionPerformed
        // TODO add your handling code here:
        
        if (mKodeKonsumen.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Kode Konsumen masih kosong...","Kesalahan",JOptionPane.ERROR_MESSAGE);
        mKodeKonsumen.requestFocus();
        } else {
        // Button Tambah
        if (pil==1){
        String sql_select = "select * from konsumen where kodekonsumen='"+mKodeKonsumen.getText()+"'";
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
        mNamaKonsumen.requestFocus();
        } else {
        JOptionPane.showMessageDialog(null, "Kode Konsumen sudah ada...", "Kesalahan",JOptionPane.ERROR_MESSAGE);
        mKodeKonsumen.requestFocus();
        }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
        e.getMessage());
        }
        } // Akhir Button Tambah
        // Button Koreksi
        if (pil==2){
        String sql_select = "select * from konsumen where kodekonsumen='"+mKodeKonsumen.getText()+"'";
        try {
        statement = k.con.createStatement();
        resultSet = statement.executeQuery(sql_select);
        if (resultSet.next()){
        mNamaKonsumen.setText(resultSet.getString(2));
        mAlamatKonsumen.setText(resultSet.getString(3));
        mKotaKonsumen.setText(resultSet.getString(4));
        mTelpKonsumen.setText(resultSet.getString(5));
        mHPKonsumen.setText(resultSet.getString(6));
        setEdit(false, true, true, true, true, true);
        BtnSimpan.setEnabled(true);
        mNamaKonsumen.requestFocus();
        } else {
        JOptionPane.showMessageDialog(null, "Kode Konsumen tidak diketemukan...","Kesalahan",JOptionPane.ERROR_MESSAGE);
        mKodeKonsumen.requestFocus();
        }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
        e.getMessage());
        }
        } // Akhir Button Koreksi
        // Button Hapus
        if (pil==3){
        String sql_select = "select * from konsumen where kodekonsumen='"+mKodeKonsumen.getText()+"'";
        try {
        statement = k.con.createStatement();
        resultSet = statement.executeQuery(sql_select);
        if (resultSet.next()){
        mNamaKonsumen.setText(resultSet.getString(2));
        mAlamatKonsumen.setText(resultSet.getString(3));
        mKotaKonsumen.setText(resultSet.getString(4));
        mTelpKonsumen.setText(resultSet.getString(5));
        mHPKonsumen.setText(resultSet.getString(6));
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
        JOptionPane.showMessageDialog(null, "Kode Konsumen tidak diketemukan...","Kesalahan",JOptionPane.ERROR_MESSAGE);
        mKodeKonsumen.requestFocus();
        }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
        e.getMessage());
        }
        } // Akhir Button Hapus
        }
    }//GEN-LAST:event_mKodeKonsumenActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        // TODO add your handling code here:
        pil = 1;
        setTombol(false, false, false, false, true, false);
        setEdit(true, false, false, false, false, false);
        mKodeKonsumen.requestFocus();

    }//GEN-LAST:event_BtnTambahActionPerformed

    private void tabelKonsumenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKonsumenMouseClicked
        // TODO add your handling code here:
        
         if (pil==2 || pil==3){
        mKodeKonsumen.setText(tabelKonsumen.getValueAt(tabelKonsumen.getSelectedRow(), 0).toString());
        mNamaKonsumen.setText(tabelKonsumen.getValueAt(tabelKonsumen.getSelectedRow(), 1).toString());
        mAlamatKonsumen.setText(tabelKonsumen.getValueAt(tabelKonsumen.getSelectedRow(), 2).toString());
        mKotaKonsumen.setText(tabelKonsumen.getValueAt(tabelKonsumen.getSelectedRow(), 3).toString());
        mTelpKonsumen.setText(tabelKonsumen.getValueAt(tabelKonsumen.getSelectedRow(), 4).toString());
        mHPKonsumen.setText(tabelKonsumen.getValueAt(tabelKonsumen.getSelectedRow(), 5).toString());
        }
        if (pil == 2) {
        setEdit(false, true, true, true, true, true);
        BtnSimpan.setEnabled(true);
        mNamaKonsumen.requestFocus();
        }
        if (pil == 3) {
        mKodeKonsumen.setEnabled(false);
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
    }//GEN-LAST:event_tabelKonsumenMouseClicked

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
            java.util.logging.Logger.getLogger(FrmKonsumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmKonsumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmKonsumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmKonsumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmKonsumen().setVisible(true);
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mAlamatKonsumen;
    private javax.swing.JTextField mHPKonsumen;
    private javax.swing.JTextField mKodeKonsumen;
    private javax.swing.JTextField mKotaKonsumen;
    private javax.swing.JTextField mNamaKonsumen;
    private javax.swing.JTextField mTelpKonsumen;
    private javax.swing.JTable tabelKonsumen;
    // End of variables declaration//GEN-END:variables

   
}
