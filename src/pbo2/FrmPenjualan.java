/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo2;

/**
 *
 * @author xxivcvnbxxx
 */

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;

public class FrmPenjualan extends javax.swing.JFrame {
    
    koneksi k;
    Statement statement;
    ResultSet resultSet;
    int tot, byr, kmbli;
    String tanggal;

    /**
     * Creates new form FrmPenjualan
     */
    
    public FrmPenjualan() {
        initComponents();
        k = new koneksi(); 
        tampilIcon(); 
        tampilTabel();
        changeColor();
        bacaKonsumen(); 
        bacaBarang();
    }
    
     private void changeColor() {
        BtnBaru.setBackground(Color.decode("#0A84FF"));
        BtnSimpan.setBackground(Color.decode("#0A84FF"));
        BtnBatal.setBackground(Color.decode("#E81123"));
        BtnHapus.setBackground(Color.decode("#E81123"));
        BtnKeluar.setBackground(Color.decode("#FF453A"));
        BtnTambah.setBackground(Color.decode("#00AD64"));
    }
    
    
  
    
    //method membuat format tanggal sesuai dengan MySQL
    private void format_tanggal()
    {
        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance();
        int year=c1.get(Calendar.YEAR);
        int month=c1.get(Calendar.MONTH)+1;
        int day=c1.get(Calendar.DAY_OF_MONTH);
        tanggal=Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
    }
    
    private void bersihTabel(){
        DefaultTableModel modelBarang = (DefaultTableModel)tabelPenjualan.getModel();
        int brs = modelBarang.getRowCount();
        if (brs>0){
            for (int i = brs-1; i >=0; i--) { 
                modelBarang.removeRow(i); 
                jmlTotal();
            }
        }
    }
    
    private void bersih(){
        mKodeJual.setText(null); 
        cKodeKonsumen.setSelectedIndex(0); 
        mNamaKonsumen.setText(null); 
        cKodeBarang.setSelectedIndex(0); 
        mNamaBarang.setText(null); 
        mHargaBarang.setText(null); 
        mJmlJual.setText(null); 
        mSubTotalBarang.setText(null); 
        mTotal.setText(null); 
        mBayar.setText(null); 
        mKembali.setText(null); 
        sTglJual.setValue(new Date());
    }
    
    private void tampilIcon(){
        BtnBaru.setIcon(new ImageIcon("./gambar/new.png")); 
        BtnSimpan.setIcon(new ImageIcon("./gambar/save.png")); 
        BtnBatal.setIcon(new ImageIcon("./gambar/Cancel_16x16.png"));
        BtnKeluar.setIcon(new ImageIcon("./gambar/log_out.png"));
        BtnTambah.setIcon(new ImageIcon("./gambar/Add_16x16.png"));
        BtnHapus.setIcon(new ImageIcon("./gambar/delete.png"));
        BtnBaru.setIcon(new ImageIcon ("./gambar/New_16x16.png"));
    }
    
    private void setEdit(boolean yKodeJual, boolean yTglJual, boolean yKodeKonsumen,boolean yNamaKonsumen, boolean yKodeBarang, 
            boolean yNamaBarang, boolean yHargaBarang, boolean yJmlJual, boolean ySubTotalBarang, boolean yTotal, boolean yBayar, boolean yKembali){
        mKodeJual.setEnabled(yKodeJual); 
        sTglJual.setEnabled(yTglJual); 
        cKodeKonsumen.setEnabled(yKodeKonsumen);
        mNamaKonsumen.setEnabled(yNamaKonsumen);
        cKodeBarang.setEnabled(yKodeBarang); 
        mNamaBarang.setEnabled(yNamaBarang); 
        mHargaBarang.setEnabled(yHargaBarang); 
        mJmlJual.setEnabled(yJmlJual); 
        mSubTotalBarang.setEnabled(ySubTotalBarang); 
        mTotal.setEnabled(yTotal); 
        mBayar.setEnabled(yBayar); 
        mKembali.setEnabled(yKembali);
    }
    
    private void setTombol(boolean xBaru, boolean xSimpan, boolean xBatal,boolean xKeluar, boolean xTambah, boolean xHapus){ 
        BtnBaru.setEnabled(xBaru); 
        BtnSimpan.setEnabled(xSimpan); 
        BtnBatal.setEnabled(xBatal); 
        BtnKeluar.setEnabled(xKeluar); 
        BtnTambah.setEnabled(xTambah); 
        BtnHapus.setEnabled(xHapus);
    }
    
    public void uangRp() {
        String tampung_harga = mHargaBarang.getText();
        try {
            double harga = Double.parseDouble(mHargaBarang.getText());
            DecimalFormat df = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols dfs = new DecimalFormatSymbols(); 
            dfs.setCurrencySymbol(""); 
            dfs.setMonetaryDecimalSeparator(','); 
            dfs.setGroupingSeparator('.'); 
            df.setDecimalFormatSymbols(dfs);
            String hsl = "Rp." + df.format(harga);
            mHargaBarang.setText(hsl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Pengisian harga tidak boleh kosong");
        }
    }

    private void tampilTabel(){
        Object header[]={"Kd Barang","Nama Barang","Harga Barang","Jml Jual","Sub Total Jual"};
        DefaultTableModel modelBarang = new DefaultTableModel(null, header)
        {
            @Override
            // Membuat jTable read only
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        tabelPenjualan.setModel(modelBarang);
    }
    
    private void jmlTotal(){
        DefaultTableModel modelBarang = (DefaultTableModel)tabelPenjualan.getModel();
        int brs = modelBarang.getRowCount();
        int subTot = 0;
        for (int i = 0; i < brs; i++) {
            int dataJumlah = Integer.parseInt(modelBarang.getValueAt(i, 4).toString());
            subTot += dataJumlah;
        }
        mTotal.setText(String.valueOf(subTot));
    }
    
    private void bacaKonsumen(){
        String sql_select = "select * from konsumen";
        try {
            statement = k.con.createStatement();
            resultSet = statement.executeQuery(sql_select);
            resultSet.beforeFirst();
            while(resultSet.next()){
                cKodeKonsumen.addItem(resultSet.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private void bacaBarang(){
        String sql_select = "select * from barang";
        try {
            statement = k.con.createStatement();
            resultSet = statement.executeQuery(sql_select);
            resultSet.beforeFirst();
            while(resultSet.next()){
                cKodeBarang.addItem(resultSet.getString(1));
            }
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

        jTextField3 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        mKodeJual = new javax.swing.JTextField();
        mNamaKonsumen = new javax.swing.JTextField();
        cKodeKonsumen = new javax.swing.JComboBox<>();
        sTglJual = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        mNamaBarang = new javax.swing.JTextField();
        mHargaBarang = new javax.swing.JTextField();
        mJmlJual = new javax.swing.JTextField();
        mSubTotalBarang = new javax.swing.JTextField();
        cKodeBarang = new javax.swing.JComboBox<>();
        BtnTambah = new javax.swing.JButton();
        BtnHapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPenjualan = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        BtnBaru = new javax.swing.JButton();
        BtnSimpan = new javax.swing.JButton();
        BtnBatal = new javax.swing.JButton();
        BtnKeluar = new javax.swing.JButton();
        mTotal = new javax.swing.JTextField();
        mBayar = new javax.swing.JTextField();
        mKembali = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Penjualan Barang");

        jPanel5.setBackground(new java.awt.Color(28, 28, 30));

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PENDATAAN PENJUALAN");

        jPanel1.setBackground(new java.awt.Color(44, 44, 46));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Kode Jual");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Tanggal Jual");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Kode Konsumen");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Nama Konsumen");

        mKodeJual.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mKodeJual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 3));
        mKodeJual.setEnabled(false);
        mKodeJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mKodeJualActionPerformed(evt);
            }
        });

        mNamaKonsumen.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mNamaKonsumen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51), 3));
        mNamaKonsumen.setEnabled(false);

        cKodeKonsumen.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        cKodeKonsumen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        cKodeKonsumen.setEnabled(false);
        cKodeKonsumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cKodeKonsumenActionPerformed(evt);
            }
        });

        sTglJual.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        sTglJual.setModel(new javax.swing.SpinnerDateModel());
        sTglJual.setEditor(new javax.swing.JSpinner.DateEditor(sTglJual, "dd-MMMM-yyyy"));
        sTglJual.setEnabled(false);
        sTglJual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sTglJualFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sTglJual, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mKodeJual, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mNamaKonsumen)
                    .addComponent(cKodeKonsumen, 0, 240, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cKodeKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mNamaKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(mKodeJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(sTglJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(64, 64, 66));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Kode Barang");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Nama Barang");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Harga Barang");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Jml Jual");

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("Sub Total");

        mNamaBarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mNamaBarang.setEnabled(false);

        mHargaBarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mHargaBarang.setEnabled(false);

        mJmlJual.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mJmlJual.setEnabled(false);
        mJmlJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mJmlJualActionPerformed(evt);
            }
        });

        mSubTotalBarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mSubTotalBarang.setEnabled(false);

        cKodeBarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cKodeBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        cKodeBarang.setEnabled(false);
        cKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cKodeBarangActionPerformed(evt);
            }
        });

        BtnTambah.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        BtnTambah.setForeground(new java.awt.Color(255, 255, 255));
        BtnTambah.setText("Tambah");
        BtnTambah.setEnabled(false);
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });

        BtnHapus.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        BtnHapus.setForeground(new java.awt.Color(255, 255, 255));
        BtnHapus.setText("Hapus");
        BtnHapus.setEnabled(false);
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });

        tabelPenjualan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabelPenjualan.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tabelPenjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelPenjualan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cKodeBarang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel5)
                                .addGap(0, 210, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(83, 83, 83)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(12, 12, 12)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mJmlJual, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(mSubTotalBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(BtnTambah)
                                .addGap(18, 18, 18)
                                .addComponent(BtnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel9)))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mJmlJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mSubTotalBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnTambah)
                    .addComponent(BtnHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(64, 64, 66));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(28, 28, 30));

        BtnBaru.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        BtnBaru.setForeground(new java.awt.Color(255, 255, 255));
        BtnBaru.setText("Transaksi Baru");
        BtnBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruActionPerformed(evt);
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

        mTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mTotal.setEnabled(false);

        mBayar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mBayar.setEnabled(false);
        mBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mBayarActionPerformed(evt);
            }
        });

        mKembali.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mKembali.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 153, 255));
        jLabel17.setText("Total");

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 51, 51));
        jLabel16.setText("Bayar");

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 153, 0));
        jLabel15.setText("Kembali");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnBaru)
                .addGap(8, 8, 8)
                .addComponent(BtnSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnBatal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnKeluar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(58, 58, 58))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(36, 36, 36)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mTotal)
                    .addComponent(mBayar)
                    .addComponent(mKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnBaru)
                        .addComponent(BtnSimpan)
                        .addComponent(BtnBatal)
                        .addComponent(BtnKeluar))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(mKembali, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jPanel3.setBackground(new java.awt.Color(64, 64, 66));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(463, 463, 463)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel13)
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        // TODO add your handling code here:
        setTombol(true, false, false, true, false, false);
        setEdit(false, false, false, false, false, false, false, false, false, false, false, false);
        bersihTabel();
        bersih();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruActionPerformed
        // TODO add your handling code here:
        setTombol(false, false, true, false, false, false);
        setEdit(true, false, false, false, false, false, false, false, false, false, false, false);
        mKodeJual.requestFocus();
    }//GEN-LAST:event_BtnBaruActionPerformed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        // TODO add your handling code here:
        // Simpan ke tabel penjualan 
        format_tanggal();
        String sql_insert  = "insert into penjualan values ('"+mKodeJual.getText()+"','"+
                cKodeKonsumen.getSelectedItem()+"','"+tanggal+"')";
        try {
            statement.executeUpdate(sql_insert);
            DefaultTableModel modelBarang = (DefaultTableModel)tabelPenjualan.getModel();
            int brs = modelBarang.getRowCount();
            for(int i=0;i<brs;i++)
            {
                String xkd=(String)tabelPenjualan.getValueAt(i,0);
                //int xhrg=(Integer)tabelBarang.getValueAt(i,2);
                // int xjml=(Integer)tabelBarang.getValueAt(i,3);
                int xhrg = Integer.parseInt(tabelPenjualan.getValueAt(i,2).toString()); 
                int xjml = Integer.parseInt(tabelPenjualan.getValueAt(i,3).toString()); 
                String zsql="insert into dpenjualan values('"+mKodeJual.getText()+"','"+xkd+"',"+xhrg+","+xjml+")";
                statement.executeUpdate(zsql);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        // Simpan ke detail penjualan
        setTombol(true, false, false, true, false, false);
        setEdit(false, false, false, false, false, false, false, false, false, false, false, false);
        bersihTabel();
        bersih();
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelBarang = (DefaultTableModel)tabelPenjualan.getModel();
        String[]data = new String[5];
        data[0] = cKodeBarang.getSelectedItem().toString();
        data[1] = mNamaBarang.getText();
        data[2] = mHargaBarang.getText();
        data[3] = mJmlJual.getText();
        data[4] = mSubTotalBarang.getText(); 
        modelBarang.addRow(data); 
        cKodeBarang.setSelectedIndex(0); 
        mNamaBarang.setText(null); 
        mHargaBarang.setText(null);
        mJmlJual.setText(null);
        mSubTotalBarang.setText(null); 
        jmlTotal(); 
        mBayar.setEnabled(true); 
        BtnTambah.setEnabled(false);
    }//GEN-LAST:event_BtnTambahActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel tableModel = (DefaultTableModel)tabelPenjualan.getModel();
            int x = tabelPenjualan.getSelectedRow();
            tableModel.removeRow(x);
            jmlTotal();
        } catch (ArrayIndexOutOfBoundsException e) { 
            JOptionPane.showMessageDialog(this, "Tabel Belum Dipilih", "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void mKodeJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mKodeJualActionPerformed
        // TODO add your handling code here:
        if (mKodeJual.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Kode Penjualan masih kosong...", "Kesalahan",JOptionPane.ERROR_MESSAGE); mKodeJual.requestFocus();
        } else {
            String sql_select = "select * from penjualan where KodePenjualan='"+mKodeJual.getText()+"'";
            try {
                statement = k.con.createStatement();
                resultSet = statement.executeQuery(sql_select);
                int baris=0;
                while (resultSet.next()){
                    baris = resultSet.getRow();
                }
                if (baris==0){
                    setEdit(false, true, true, false, false, false, false, false, false, false, false, false);
                
                    sTglJual.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Kode Penjualan sudah ada...",
                        "Kesalahan",JOptionPane.ERROR_MESSAGE);
                    mKodeJual.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_mKodeJualActionPerformed

    private void cKodeKonsumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cKodeKonsumenActionPerformed
        // TODO add your handling code here:
        if (cKodeKonsumen.getSelectedItem()=="-- Pilih --"){ 
            mNamaKonsumen.setText(null);
            cKodeKonsumen.setSelectedIndex(0); 
            cKodeBarang.setEnabled(false); 
            cKodeKonsumen.requestFocus();
        } else {
            String sql_select = "select * from konsumen where KodeKonsumen='"+cKodeKonsumen.getSelectedItem()+"'";
            try {
                statement = k.con.createStatement();
                resultSet = statement.executeQuery(sql_select);
                resultSet.beforeFirst();
                while(resultSet.next()){
                    mNamaKonsumen.setText(resultSet.getString(2));
                } 
                cKodeBarang.setEnabled(true); 
                BtnHapus.setEnabled(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_cKodeKonsumenActionPerformed

    private void cKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cKodeBarangActionPerformed
        // TODO add your handling code here:
        if (cKodeBarang.getSelectedItem()=="-- Pilih --"){ 
            mNamaBarang.setText(null);
            mHargaBarang.setText(null); 
            mJmlJual.setText(null); 
            mSubTotalBarang.setText(null);
            mJmlJual.setEnabled(false);
        } else {
            String sql_select = "select * from barang where KodeBarang='"+cKodeBarang.getSelectedItem()+"'";
            try {
                statement = k.con.createStatement();
                resultSet = statement.executeQuery(sql_select);
                resultSet.beforeFirst();
                while(resultSet.next()){ 
                    mNamaBarang.setText(resultSet.getString(2));
                    mHargaBarang.setText(resultSet.getString(4));
                }
                mJmlJual.setEnabled(true);
                mJmlJual.requestFocus();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_cKodeBarangActionPerformed

    private void mJmlJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mJmlJualActionPerformed
        // TODO add your handling code here:
        int harga = Integer.parseInt(mHargaBarang.getText());
        int jml = Integer.parseInt(mJmlJual.getText()); 
        int sub = harga * jml; 
        mSubTotalBarang.setText(String.valueOf(sub)); 
        BtnTambah.setEnabled(true);
        BtnTambah.requestFocus();
    }//GEN-LAST:event_mJmlJualActionPerformed

    private void mBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mBayarActionPerformed
        // TODO add your handling code here:
        tot= Integer.parseInt(mTotal.getText()); 
        byr= Integer.parseInt(mBayar.getText()); 
        kmbli = byr - tot; 
        mKembali.setText(String.valueOf(kmbli)); 
        BtnSimpan.setEnabled(true);
    }//GEN-LAST:event_mBayarActionPerformed

    private void sTglJualFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sTglJualFocusGained
        // TODO add your handling code here:
       
    }//GEN-LAST:event_sTglJualFocusGained

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
            java.util.logging.Logger.getLogger(FrmPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBaru;
    private javax.swing.JButton BtnBatal;
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton BtnKeluar;
    private javax.swing.JButton BtnSimpan;
    private javax.swing.JButton BtnTambah;
    private javax.swing.JComboBox<String> cKodeBarang;
    private javax.swing.JComboBox<String> cKodeKonsumen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField mBayar;
    private javax.swing.JTextField mHargaBarang;
    private javax.swing.JTextField mJmlJual;
    private javax.swing.JTextField mKembali;
    private javax.swing.JTextField mKodeJual;
    private javax.swing.JTextField mNamaBarang;
    private javax.swing.JTextField mNamaKonsumen;
    private javax.swing.JTextField mSubTotalBarang;
    private javax.swing.JTextField mTotal;
    private javax.swing.JSpinner sTglJual;
    private javax.swing.JTable tabelPenjualan;
    // End of variables declaration//GEN-END:variables
}
