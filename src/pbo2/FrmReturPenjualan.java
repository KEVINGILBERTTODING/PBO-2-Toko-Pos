/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo2;

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
/**
 *
 * @author bagus
 */
public class FrmReturPenjualan extends javax.swing.JFrame {
    koneksi k;
    Statement statement;
    ResultSet resultSet; 
    int tot, byr, kmbli; 
    String tanggal;

    /**
     * Creates new form FrmReturPenjualan
     */
    public FrmReturPenjualan() {
        initComponents();
        k = new koneksi(); 
        tampilIcon();
        changeColor();
        tampilTabel();
        bacaPenjualan();
        bacaBarang();
    }
    
    private void changeColor() {
        btnBaru.setBackground(Color.decode("#0A84FF"));
        btnSimpan.setBackground(Color.decode("#0A84FF"));
        btnBatal.setBackground(Color.decode("#E81123"));
        btnHapus.setBackground(Color.decode("#E81123"));
        btnKeluar.setBackground(Color.decode("#FF453A"));
        btnTambah.setBackground(Color.decode("#00AD64"));
    }
    
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
    private void bersihTabel()
    {
    DefaultTableModel modelBarang = (DefaultTableModel)tabelReturJual.getModel();
    int brs = modelBarang.getRowCount();
    if (brs>0){
    for (int i = brs-1; i >=0; i--) { 
        modelBarang.removeRow(i); 
        jmlTotal();
            }
        }
    }
    private void bersih(){
        mKodeReturJual.setText(null); 
        cKodePenjualan.setSelectedIndex(0); 
        mNamaKonsumen.setText(null);
        cKodeBarang.setSelectedIndex(0);
        mNamaBarang.setText(null);
        mHargaBarang.setText(null); 
        mJmlReturJual.setText(null);
        mSubTotalBarang.setText(null); 
        mTotal.setText(null); 
        mBayar.setText(null);
        mKembali.setText(null);
        sTglReturJual.setValue(new Date());
    }
    private void tampilIcon(){
        btnBaru.setIcon(new ImageIcon("./gambar/new_16x16.png"));
        btnSimpan.setIcon(new ImageIcon("./gambar/save.png"));
        btnBatal.setIcon(new ImageIcon("./gambar/Cancel_16x16.png"));
        btnKeluar.setIcon(new ImageIcon("./gambar/log_out.png"));
        btnTambah.setIcon(new ImageIcon("./gambar/add_16x16.png"));
        btnHapus.setIcon(new ImageIcon("./gambar/delete.png"));
    }
    private void setEdit(boolean yKodeReturJual, boolean yTglReturJual, boolean yKodePenjualan,boolean yNamaKonsumen, boolean yKodeBarang, boolean yNamaBarang, boolean yHargaBarang, boolean yJmlJual, boolean ySubTotalBarang, boolean yTotal, boolean yBayar, boolean yKembali){
        mKodeReturJual.setEnabled(yKodeReturJual); 
        sTglReturJual.setEnabled(yTglReturJual); 
        cKodePenjualan.setEnabled(yKodePenjualan);
        mNamaKonsumen.setEnabled(yNamaKonsumen); 
        cKodeBarang.setEnabled(yKodeBarang); 
        mNamaBarang.setEnabled(yNamaBarang); 
        mHargaBarang.setEnabled(yHargaBarang); 
        mJmlReturJual.setEnabled(yJmlJual); 
        mSubTotalBarang.setEnabled(ySubTotalBarang);
        mTotal.setEnabled(yTotal); 
        mBayar.setEnabled(yBayar); 
        mKembali.setEnabled(yKembali);
    }
    private void setTombol(boolean xBaru, boolean xSimpan, boolean xBatal,boolean xKeluar, boolean xTambah, boolean xHapus){
        btnBaru.setEnabled(xBaru); 
        btnSimpan.setEnabled(xSimpan); 
        btnBatal.setEnabled(xBatal);
        btnKeluar.setEnabled(xKeluar);
        btnTambah.setEnabled(xTambah); 
        btnHapus.setEnabled(xHapus);
    }
    public void uangRp() {
        String tampung_harga = mHargaBarang.getText();
        try {
            double harga = Double.parseDouble(mHargaBarang.getText());
            DecimalFormat df = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols dfs = new DecimalFormatSymbols(); dfs.setCurrencySymbol(""); dfs.setMonetaryDecimalSeparator(','); dfs.setGroupingSeparator('.'); df.setDecimalFormatSymbols(dfs);
            String hsl = "Rp." + df.format(harga);
            mHargaBarang.setText(hsl);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Pengisian harga tidak boleh kosong");
        }
    }
    private void tampilTabel(){
    Object header[]={"Kode Barang","Nama Barang","Harga Barang","Qty","Sub Total"};
    DefaultTableModel modelBarang = new DefaultTableModel(null, header)
    {
    @Override
    // Membuat jTable read only
    public boolean isCellEditable(int row, int column)
    {
    return false;
    }
    };
    tabelReturJual.setModel(modelBarang);
    }
    private void jmlTotal(){
    DefaultTableModel modelBarang = (DefaultTableModel)tabelReturJual.getModel();
    int brs = modelBarang.getRowCount();
    int subTot = 0;
    for (int i = 0; i < brs; i++) {
    int dataJumlah = Integer.parseInt(modelBarang.getValueAt(i, 4).toString());
    subTot += dataJumlah;
    }
    mTotal.setText(String.valueOf(subTot));
    }
    private void bacaPenjualan(){
    String sql_select = "SELECT penjualan.*,konsumen.namakonsumen FROM penjualan JOIN konsumen ON penjualan.kodekonsumen=konsumen.kodekonsumen ";
    try {
    statement = k.con.createStatement();
    resultSet = statement.executeQuery(sql_select);
    resultSet.beforeFirst();
    while(resultSet.next()){
        cKodePenjualan.addItem(resultSet.getString(1));
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

        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        mKodeReturJual = new javax.swing.JTextField();
        sTglReturJual = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        mNamaKonsumen = new javax.swing.JTextField();
        cKodePenjualan = new javax.swing.JComboBox<>();
        mKodeKonsumen = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cKodeBarang = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        mNamaBarang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        mHargaBarang = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        mJmlReturJual = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        mSubTotalBarang = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelReturJual = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnBaru = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        mKembali = new javax.swing.JTextField();
        mBayar = new javax.swing.JTextField();
        mTotal = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Retur Penjualan");

        jPanel6.setBackground(new java.awt.Color(28, 28, 30));

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("PENDATAAN RETUR PENJUALAN");

        jPanel1.setBackground(new java.awt.Color(44, 44, 46));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Kode Retur Penjualan");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Tgl Retur Penjualan");

        mKodeReturJual.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mKodeReturJual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 3));
        mKodeReturJual.setEnabled(false);
        mKodeReturJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mKodeReturJualActionPerformed(evt);
            }
        });

        sTglReturJual.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        sTglReturJual.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1620274020000L), null, null, java.util.Calendar.DAY_OF_WEEK_IN_MONTH));
        sTglReturJual.setEditor(new javax.swing.JSpinner.DateEditor(sTglReturJual, "dd-MMMM-yyyy"));
        sTglReturJual.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Kode Penjualan");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Nama Konsumen");

        mNamaKonsumen.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mNamaKonsumen.setEnabled(false);
        mNamaKonsumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mNamaKonsumenActionPerformed(evt);
            }
        });

        cKodePenjualan.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        cKodePenjualan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--", " " }));
        cKodePenjualan.setEnabled(false);
        cKodePenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cKodePenjualanActionPerformed(evt);
            }
        });

        mKodeKonsumen.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mKodeKonsumen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51), 3));
        mKodeKonsumen.setEnabled(false);
        mKodeKonsumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mKodeKonsumenActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("Kode Konsumen");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mKodeReturJual)
                    .addComponent(sTglReturJual, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                .addGap(417, 417, 417)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel13)
                    .addComponent(jLabel3))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cKodePenjualan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mNamaKonsumen)
                    .addComponent(mKodeKonsumen, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(mKodeReturJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(sTglReturJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cKodePenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mKodeKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mNamaKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(44, 44, 46));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(44, 44, 46));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Kode Barang");

        cKodeBarang.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        cKodeBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--", " " }));
        cKodeBarang.setEnabled(false);
        cKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cKodeBarangActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Nama Barang");

        mNamaBarang.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mNamaBarang.setEnabled(false);
        mNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mNamaBarangActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Harga");

        mHargaBarang.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mHargaBarang.setEnabled(false);
        mHargaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mHargaBarangActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Qty");

        mJmlReturJual.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mJmlReturJual.setEnabled(false);
        mJmlReturJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mJmlReturJualActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("Sub Total");

        mSubTotalBarang.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mSubTotalBarang.setEnabled(false);
        mSubTotalBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSubTotalBarangActionPerformed(evt);
            }
        });

        btnTambah.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("Tambah");
        btnTambah.setEnabled(false);
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("Hapus");
        btnHapus.setEnabled(false);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        tabelReturJual.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tabelReturJual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kd Barang", "Nama Barang", "Harga Barang", "Jml Retuir Jual", "Sub Total Retur Jual"
            }
        ));
        jScrollPane1.setViewportView(tabelReturJual);

        jPanel7.setBackground(new java.awt.Color(28, 28, 30));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(mNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(mHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mJmlReturJual, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel6)
                        .addGap(154, 154, 154)
                        .addComponent(jLabel7)
                        .addGap(94, 94, 94)
                        .addComponent(jLabel8)
                        .addGap(45, 45, 45)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(mSubTotalBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mSubTotalBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mJmlReturJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTambah)
                            .addComponent(btnHapus)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        btnBaru.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnBaru.setForeground(new java.awt.Color(255, 255, 255));
        btnBaru.setText("Transaksi Baru");
        btnBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaruActionPerformed(evt);
            }
        });

        btnSimpan.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Simpan");
        btnSimpan.setEnabled(false);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setText("Batal");
        btnBatal.setEnabled(false);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnKeluar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnKeluar.setForeground(new java.awt.Color(255, 255, 255));
        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 153, 255));
        jLabel10.setText("Total");

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 51, 51));
        jLabel11.setText("Bayar");

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 153, 0));
        jLabel12.setText("Kembali");

        mKembali.setEnabled(false);
        mKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mKembaliActionPerformed(evt);
            }
        });

        mBayar.setEnabled(false);
        mBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mBayarActionPerformed(evt);
            }
        });

        mTotal.setEnabled(false);
        mTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mTotalActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(64, 64, 66));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(44, 44, 46));

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(453, 453, 453)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnBaru)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBatal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKeluar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(mBayar)
                    .addComponent(mKembali)
                    .addComponent(mTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel14)
                .addGap(33, 33, 33)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan)
                            .addComponent(btnBatal)
                            .addComponent(btnKeluar)
                            .addComponent(btnBaru))
                        .addGap(47, 47, 47))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(mTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(mBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(mKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(54, 54, 54)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mKodeReturJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mKodeReturJualActionPerformed
        // TODO add your handling code here:
        if (mKodeReturJual.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Kode Retur Penjualan masih kosong...", "Kesalahan",JOptionPane.ERROR_MESSAGE); mKodeReturJual.requestFocus();
        } else {
            String sql_select = "select * from returpenjualan where kodereturpenjualan='"+mKodeReturJual.getText()+"'";
            try {
                statement = k.con.createStatement();
                resultSet = statement.executeQuery(sql_select);
                int baris=0;
                while (resultSet.next()){
                    baris = resultSet.getRow();
                }
                if (baris==0){
                    setEdit(false, true, true, false, false, false, false, false, false, false, false, false);
                    sTglReturJual.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Kode Retur Penjualan sudah ada...",
                        "Kesalahan",JOptionPane.ERROR_MESSAGE);
                    mKodeReturJual.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_mKodeReturJualActionPerformed

    private void mNamaKonsumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mNamaKonsumenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mNamaKonsumenActionPerformed

    private void cKodePenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cKodePenjualanActionPerformed
        // TODO add your handling code here:
     
        if (cKodePenjualan.getSelectedItem()=="-- Pilih --"){
            mNamaKonsumen.setText(null);
            cKodePenjualan.setSelectedIndex(0);
            cKodeBarang.setEnabled(false);
            cKodePenjualan.requestFocus();
        } else {
            String sql_select = "SELECT penjualan.*,konsumen.namakonsumen FROM penjualan JOIN konsumen ON penjualan.kodekonsumen=konsumen.kodekonsumen  where penjualan.kodepenjualan='"+cKodePenjualan.getSelectedItem()+"'";
            try {
                statement = k.con.createStatement();
                resultSet = statement.executeQuery(sql_select);
                resultSet.beforeFirst();
                while(resultSet.next()){
                    mNamaKonsumen.setText(resultSet.getString(4));
                    mKodeKonsumen.setText(resultSet.getString(2));

                }
                cKodeBarang.setEnabled(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_cKodePenjualanActionPerformed

    private void cKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cKodeBarangActionPerformed
        // TODO add your handling code here:
        if (cKodeBarang.getSelectedItem()=="-- Pilih --"){
            mNamaBarang.setText(null);
            mHargaBarang.setText(null);
            mJmlReturJual.setText(null);
            mSubTotalBarang.setText(null);
            mJmlReturJual.setEnabled(false);
        } else {
            String sql_select = "select * from barang where kodebarang='"+cKodeBarang.getSelectedItem()+"'";
            try {
                statement = k.con.createStatement();
                resultSet = statement.executeQuery(sql_select);
                resultSet.beforeFirst();
                while(resultSet.next()){ mNamaBarang.setText(resultSet.getString(2)); mHargaBarang.setText(resultSet.getString(4));
                }
                mJmlReturJual.setEnabled(true);
                mJmlReturJual.requestFocus();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_cKodeBarangActionPerformed

    private void mNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mNamaBarangActionPerformed

    private void mHargaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mHargaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mHargaBarangActionPerformed

    private void mJmlReturJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mJmlReturJualActionPerformed
        // TODO add your handling code here:
        int harga = Integer.parseInt(mHargaBarang.getText());
        int jml = Integer.parseInt(mJmlReturJual.getText());
        int sub = harga * jml;
        mSubTotalBarang.setText(String.valueOf(sub));
        btnTambah.setEnabled(true);
        btnTambah.requestFocus();
    }//GEN-LAST:event_mJmlReturJualActionPerformed

    private void mSubTotalBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSubTotalBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mSubTotalBarangActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelBarang = (DefaultTableModel)tabelReturJual.getModel();
        String[]data = new String[5];
        data[0] = cKodeBarang.getSelectedItem().toString();
        data[1] = mNamaBarang.getText();
        data[2] = mHargaBarang.getText();
        data[3] = mJmlReturJual.getText();
        data[4] = mSubTotalBarang.getText(); modelBarang.addRow(data);
        cKodeBarang.setSelectedIndex(0);
        mNamaBarang.setText(null);
        mHargaBarang.setText(null);
        mJmlReturJual.setEnabled(false);
        mJmlReturJual.setText(null);
        mSubTotalBarang.setText(null); jmlTotal();
        mBayar.setEnabled(true);
        btnHapus.setEnabled(true);
        btnTambah.setEnabled(false);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel tableModel = (DefaultTableModel)tabelReturJual.getModel();
            int x = tabelReturJual.getSelectedRow();
            tableModel.removeRow(x);
            jmlTotal();
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Tabel Belum Dipilih",
                "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        format_tanggal();
        String sql_insert  = "insert into returpenjualan values ('"+mKodeReturJual.getText()+"','"+
        cKodePenjualan.getSelectedItem()+"','"+tanggal+"')";
        try {
            statement.executeUpdate(sql_insert);
            DefaultTableModel modelBarang = (DefaultTableModel)tabelReturJual.getModel();
            int brs = modelBarang.getRowCount();
            btnHapus.setEnabled(false);
            cKodeBarang.setEnabled(false);
            cKodeBarang.setSelectedIndex(0);
            mKodeKonsumen.setText(null);
            mJmlReturJual.requestFocus(false);
            
            for(int i=0;i<brs;i++)
            {
                String xkd=(String)tabelReturJual.getValueAt(i,0);
                int xhrg = Integer.parseInt(tabelReturJual.getValueAt(i,2).toString()); int xjml = Integer.parseInt(tabelReturJual.getValueAt(i,3).toString()); String zsql="insert into dreturpenjualan values('"+mKodeReturJual.getText()+
                "','"+xkd+"',"+xhrg+","+xjml+")";
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
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        setTombol(true, false, false, true, false, false);
        setEdit(false, false, false, false, false, false, false, false, false, false, false, false);
        bersihTabel();
        bersih();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaruActionPerformed
        // TODO add your handling code here:
        setTombol(false, false, true, false, false, false);
        setEdit(true, false, false, false, false, false, false, false, false, false, false, false);
        mKodeReturJual.requestFocus();
    }//GEN-LAST:event_btnBaruActionPerformed

    private void mTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mTotalActionPerformed

    private void mBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mBayarActionPerformed
        // TODO add your handling code here:
        tot= Integer.parseInt(mTotal.getText());
        byr= Integer.parseInt(mBayar.getText());
        kmbli = byr - tot;
        mKembali.setText(String.valueOf(kmbli));
        btnSimpan.setEnabled(true);
    }//GEN-LAST:event_mBayarActionPerformed

    private void mKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mKembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mKembaliActionPerformed

    private void mKodeKonsumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mKodeKonsumenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mKodeKonsumenActionPerformed

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
            java.util.logging.Logger.getLogger(FrmReturPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmReturPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmReturPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmReturPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmReturPenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaru;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cKodeBarang;
    private javax.swing.JComboBox<String> cKodePenjualan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mBayar;
    private javax.swing.JTextField mHargaBarang;
    private javax.swing.JTextField mJmlReturJual;
    private javax.swing.JTextField mKembali;
    private javax.swing.JTextField mKodeKonsumen;
    private javax.swing.JTextField mKodeReturJual;
    private javax.swing.JTextField mNamaBarang;
    private javax.swing.JTextField mNamaKonsumen;
    private javax.swing.JTextField mSubTotalBarang;
    private javax.swing.JTextField mTotal;
    private javax.swing.JSpinner sTglReturJual;
    private javax.swing.JTable tabelReturJual;
    // End of variables declaration//GEN-END:variables
}
