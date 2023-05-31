package tugas.pkgfinal.uts.fix;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuUtama extends javax.swing.JFrame {
    int dataMbr = 1, maxMember = 10;
    cMembership mbr[];
    cMakanan mkn[];
    cMinuman mnm[];
    int dataMkn = 5, dataMnm = 2;
    cTransaksi trans[];
    private String tanggal;
    private int dataTrs = 0;
    private int temp = dataTrs;
    private boolean sudahtambah = false;
    public Statement stat;
    Connection con = Koneksi.BukaKoneksi();
    public ResultSet res;
    
    
    public MenuUtama() {
        mbr = new cMembership[10];
        mbr[0] = new cMembership("MBR001", "Adam", "Surabaya", "081357890234"); 
        mbr[1] = new cMembership("MBR002", "Septi", "Sidoarjo", "081234567897"); 
        mbr[2] = new cMembership("MBR003", "Yuana", "Gresik", "081357345672"); 

        mkn = new cMakanan[10]; 
        mkn[0] = new cMakanan("MKN001","Mie Goreng","Makanan",6000);
        mkn[1] = new cMakanan("MKN002","Mie Rendang","Makanan",7000);
        mkn[2] = new cMakanan("MKN003","Mie Kari","Makanan",8000);
        mkn[3] = new cMakanan("MKN004","Telur","Makanan",3000);
        mkn[4] = new cMakanan("MKN005","Nasi","Makanan",5000);
        mnm = new cMinuman[10]; 
        mnm[0] = new cMinuman("MNM001","Es Jeruk","Minuman",4000); 
        mnm[1] = new cMinuman("MNM002","Es Teh","Minuman",3000);
        mnm[2] = new cMinuman("MNM003","Es Coklat","Minuman",8000);
        trans = new cTransaksi[10]; 
        
        initComponents();
        TampilHeaderMember();
        TampilHeaderBarang();
        TampilHeaderTransaksi();
        txtHargaT.setEditable(false);
        txtTotalT.setEditable(false);
        txtNamaBT.setEditable(false);
    }
    private void TampilrekapP(){
        try {
            stat = con.createStatement();
            res = stat.executeQuery("SELECT * FROM rekap_pembeli");
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn ("Nama Pembeli");
            model.addColumn ("Total Pembelian");
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            
            while(res.next()){
                Object[] data = {
                    res.getString("nama"),
                    res.getString("total")};
                
                model.addRow(data);
                TabelRP.setModel(model);
                }
            }catch (Exception e) {
        }
    }
    private void TampilrekapM(){
        try {
            stat = con.createStatement();
            res = stat.executeQuery("SELECT * FROM rekap_menu");
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn ("Nama Menu");
            model.addColumn ("Total Pembelian");
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            
            while(res.next()){
                Object[] data = {
                    res.getString("barang"),
                    res.getString("total")};
                
                model.addRow(data);
                TabelRM.setModel(model);
                }
            }catch (Exception e) {
        }
    }
    private void bersihMember(){
        txtKodeM.setText("");
        txtNamaM.setText("");
        txtAlamatM.setText("");
        txtTelpM.setText("");
        txtKodeM.setEditable(true);
        TampiltabelbaruMember();
    }
private void TampilcariMember(){
        try {
            DefaultTableModel model = (DefaultTableModel)TabelM.getModel();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            String row [][] = new String [1][4];
            for (int i = 0; i < maxMember ; i++) {
                if (mbr[i]!=null && mbr[i].getNama().equalsIgnoreCase(txtCariM.getText())) {
                    row[0][0] = mbr[i].getId();
                    row[0][1] = mbr[i].getNama();
                    row[0][2] = mbr[i].getAlamat();
                    row[0][3] = mbr[i].getTelepon();
                    model.addRow(row[0]);
                    TabelM.setModel(model);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
}
private void TampilHeaderMember(){
    try {
        DefaultTableModel model = (DefaultTableModel)TabelM.getModel();
        model.addColumn ("ID Member");
        model.addColumn ("Nama Member");
        model.addColumn ("Alamat Member");
        model.addColumn ("No Telepon");
        TampiltabelbaruMember();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
}
private void TampiltabelbaruMember(){
    try {
        DefaultTableModel model = (DefaultTableModel)TabelM.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        model.setRowCount(0);
        String row [][] = new String [1][4];
        for (int i = 0; i < maxMember ; i++) {
            if (mbr[i]!=null) {
                row[0][0] = mbr[i].getId();
                row[0][1] = mbr[i].getNama();
                row[0][2] = mbr[i].getAlamat();
                row[0][3] = mbr[i].getTelepon();
                model.addRow(row[0]);
                TabelM.setModel(model);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
}
private void bersihBarang(){
        txtKodeB.setText("");
        txtNamaBarang.setText("");
        txtHargaBarang.setText("");
        cmbTipe.setSelectedItem("- Pilih Tipe Barang -");
        
        
        txtKodeB.setEditable(true);
        TampiltabelbaruBarang();
    }
private void TampilcariBarang(){
        try {
        DefaultTableModel model = (DefaultTableModel)TabelB.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        model.setRowCount(0);
        String row [][] = new String [1][4];
        for (int i = 0; i < dataMkn ; i++) {
            if (mkn[i]!=null && mkn[i].getNama().equalsIgnoreCase(txtCariB.getText())) {
                row[0][0] = mkn[i].getKode();
                row[0][1] = mkn[i].getNama();
                row[0][2] = mkn[i].getTipe();
                String help = String.valueOf(mkn[i].getharga());
                row[0][3] = help;
                model.addRow(row[0]);
            }
        }
        String riw [][] = new String [1][4];
        for (int i = 0; i < dataMnm; i++) {
            if (mnm[i]!=null && mnm[i].getNama().equalsIgnoreCase(txtCariB.getText())) {
                riw[0][0] = mnm[i].getKode();
                riw[0][1] = mnm[i].getNama();
                riw[0][2] = mnm[i].getTipe();
                String help = String.valueOf(mnm[i].getharga());
                riw[0][3] = help;
                model.addRow(riw[0]);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
}
private void TampilHeaderBarang(){
    try {
        DefaultTableModel model = (DefaultTableModel)TabelB.getModel();
        model.addColumn ("Kode Menu");
        model.addColumn ("Nama Menu");
        model.addColumn ("Tipe Menu");
        model.addColumn ("Harga");
        TampiltabelbaruBarang();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
}
private void TampiltabelbaruBarang(){
    try {
        DefaultTableModel model = (DefaultTableModel)TabelB.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        model.setRowCount(0);
        String row [][] = new String [1][4];
        for (int i = 0; i < dataMkn ; i++) {
            if (mkn[i]!=null) {
                row[0][0] = mkn[i].getKode();
                row[0][1] = mkn[i].getNama();
                row[0][2] = mkn[i].getTipe();
                String help = String.valueOf(mkn[i].getharga());
                row[0][3] = help;
                model.addRow(row[0]);
            }
        }
        String riw [][] = new String [1][4];
        for (int i = 0; i < dataMnm; i++) {
            if (mnm[i]!=null) {
                riw[0][0] = mnm[i].getKode();
                riw[0][1] = mnm[i].getNama();
                riw[0][2] = mnm[i].getTipe();
                String help = String.valueOf(mnm[i].getharga());
                riw[0][3] = help;
                model.addRow(riw[0]);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
}
private void bersihTransaksi(){
        txtKodeT.setText("");
        txtNamaBT.setText("");
        txtHargaT.setText("");
        txtJumlahT.setText("");
        txtTotalT.setText("");
        TampiltabelbaruTransaksi();
    } 
private void bersihSemuaTransaksi(){
        txtNoT.setText("");
        txtTglT.setCalendar(null);
        txtIdT.setText("");
        txtNamaPT.setText("");
        txtKodeT.setText("");
        txtNamaBT.setText("");
        txtHargaT.setText("");
        txtJumlahT.setText("");
        txtTotalT.setText("");
        txtKodeM.setEditable(true);
        txtNoT.setEditable(true);
        txtIdT.setEditable(true);
        txtNamaPT.setEditable(true);
        TampiltabelbaruTransaksi();
    } 
    

private void TampilHeaderTransaksi(){
    try {
        DefaultTableModel model = (DefaultTableModel)TabelT.getModel();
        model.addColumn ("Nomor Transaksi");
        model.addColumn ("Tanggal Transaksi");
        model.addColumn ("Id Pelanggan");
        model.addColumn ("Nama Pelanggan");
        model.addColumn ("Kode Barang");
        model.addColumn ("Nama Barang");
        model.addColumn ("Harga");
        model.addColumn ("Jumlah Barang");
        model.addColumn ("Total Harga");
        TampiltabelbaruTransaksi();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
}
private void TampiltabelbaruTransaksi(){
    try {
        DefaultTableModel model = (DefaultTableModel)TabelT.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        model.setRowCount(0);
        String row [][] = new String [1][9];
        for (int i = temp; i < trans.length ; i++) {
            if (trans[i]!=null) {
                row[0][0] = trans[i].getNomor();
                row[0][1] = trans[i].getTanggal();
                row[0][2] = trans[i].getIdPelanggan();
                row[0][3] = trans[i].getNamaP();
                row[0][4] = trans[i].getKode();
                row[0][5] = trans[i].getNamaB();
                row[0][6] = trans[i].getHarga();
                row[0][7] = trans[i].getJumlah();
                row[0][8] = trans[i].getTotal();
                model.addRow(row[0]);
                TabelT.setModel(model);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
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
        MenuUtama = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtKodeM = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNamaM = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTelpM = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtAlamatM = new javax.swing.JTextField();
        TambahMember = new javax.swing.JToggleButton();
        UbahMember = new javax.swing.JToggleButton();
        HapusMember = new javax.swing.JToggleButton();
        BatalMember = new javax.swing.JToggleButton();
        txtCariM = new javax.swing.JTextField();
        CariMember = new javax.swing.JToggleButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelM = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtKodeB = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNamaBarang = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtHargaBarang = new javax.swing.JTextField();
        BtambahB = new javax.swing.JToggleButton();
        BubahB = new javax.swing.JToggleButton();
        BhapusB = new javax.swing.JToggleButton();
        BbatalB = new javax.swing.JToggleButton();
        txtCariB = new javax.swing.JTextField();
        BcariB = new javax.swing.JToggleButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TabelB = new javax.swing.JTable();
        cmbTipe = new javax.swing.JComboBox<>();
        Transaksi = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtNoT = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtHargaT = new javax.swing.JTextField();
        BtambahT = new javax.swing.JToggleButton();
        BubahT = new javax.swing.JToggleButton();
        BhapusT = new javax.swing.JToggleButton();
        BbatalT = new javax.swing.JToggleButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TabelT = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        txtKodeT = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtNamaBT = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtIdT = new javax.swing.JTextField();
        txtNamaPT = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtJumlahT = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTotalT = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        Checkout = new javax.swing.JButton();
        txtTglT = new com.toedter.calendar.JDateChooser();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelRP = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TabelRM = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel9.setText("ID");

        txtKodeM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeMActionPerformed(evt);
            }
        });

        jLabel10.setText("Nama");

        txtNamaM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaMActionPerformed(evt);
            }
        });

        jLabel11.setText("Alamat");

        txtTelpM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelpMActionPerformed(evt);
            }
        });

        jLabel12.setText("No.Telp");

        txtAlamatM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlamatMActionPerformed(evt);
            }
        });

        TambahMember.setText("Tambah");
        TambahMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TambahMemberActionPerformed(evt);
            }
        });

        UbahMember.setText("Ubah");
        UbahMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UbahMemberActionPerformed(evt);
            }
        });

        HapusMember.setText("Hapus");
        HapusMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusMemberActionPerformed(evt);
            }
        });

        BatalMember.setText("Batal");
        BatalMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BatalMemberActionPerformed(evt);
            }
        });

        txtCariM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariMActionPerformed(evt);
            }
        });

        CariMember.setText("Cari");
        CariMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariMemberActionPerformed(evt);
            }
        });

        TabelM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TabelM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelMMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TabelM);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(TambahMember)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UbahMember, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(HapusMember, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BatalMember, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKodeM)
                            .addComponent(txtNamaM, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(15, 15, 15)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAlamatM)
                            .addComponent(txtTelpM, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(553, 553, 553)
                        .addComponent(txtCariM, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CariMember, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtKodeM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtAlamatM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNamaM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtTelpM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TambahMember)
                    .addComponent(UbahMember)
                    .addComponent(HapusMember)
                    .addComponent(BatalMember)
                    .addComponent(txtCariM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CariMember))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        MenuUtama.addTab("MEMBERSHIP", jPanel4);

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        jLabel13.setText("Kode");

        txtKodeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeBActionPerformed(evt);
            }
        });

        jLabel14.setText("Nama");

        txtNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaBarangActionPerformed(evt);
            }
        });

        jLabel15.setText("Harga");

        jLabel16.setText("Tipe");

        txtHargaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaBarangActionPerformed(evt);
            }
        });

        BtambahB.setText("Tambah");
        BtambahB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtambahBActionPerformed(evt);
            }
        });

        BubahB.setText("Ubah");
        BubahB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BubahBActionPerformed(evt);
            }
        });

        BhapusB.setText("Hapus");
        BhapusB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BhapusBActionPerformed(evt);
            }
        });

        BbatalB.setText("Batal");
        BbatalB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BbatalBActionPerformed(evt);
            }
        });

        txtCariB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariBActionPerformed(evt);
            }
        });

        BcariB.setText("Cari");
        BcariB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BcariBActionPerformed(evt);
            }
        });

        TabelB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TabelB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelBMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TabelB);

        cmbTipe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Tipe Barang -", "Makanan", "Minuman" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtKodeB, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(BtambahB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BubahB, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BhapusB, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BbatalB, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(224, 224, 224)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbTipe, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtCariB, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BcariB, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtKodeB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(cmbTipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtambahB)
                    .addComponent(BubahB)
                    .addComponent(BhapusB)
                    .addComponent(BbatalB)
                    .addComponent(txtCariB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BcariB))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        MenuUtama.addTab("BARANG", jPanel5);

        Transaksi.setBackground(new java.awt.Color(0, 153, 153));

        jLabel17.setText("No Transaksi");

        txtNoT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoTActionPerformed(evt);
            }
        });

        jLabel18.setText("Tanggal Transaksi");

        jLabel19.setText("Harga");

        txtHargaT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaTActionPerformed(evt);
            }
        });

        BtambahT.setText("Tambah");
        BtambahT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtambahTActionPerformed(evt);
            }
        });

        BubahT.setText("Ubah");
        BubahT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BubahTActionPerformed(evt);
            }
        });

        BhapusT.setText("Hapus");
        BhapusT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BhapusTActionPerformed(evt);
            }
        });

        BbatalT.setText("Batal");
        BbatalT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BbatalTActionPerformed(evt);
            }
        });

        TabelT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TabelT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelTMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TabelT);

        jLabel20.setText("Kode Barang");

        txtKodeT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtKodeTFocusLost(evt);
            }
        });

        jLabel21.setText("Nama Barang");

        txtNamaBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaBTActionPerformed(evt);
            }
        });

        jLabel22.setText("ID Pelanggan");

        txtIdT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdTFocusLost(evt);
            }
        });
        txtIdT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdTActionPerformed(evt);
            }
        });

        jLabel23.setText("Nama Pelanggan");

        txtJumlahT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahTActionPerformed(evt);
            }
        });
        txtJumlahT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJumlahTKeyReleased(evt);
            }
        });

        jLabel24.setText("Jumlah Barang");

        txtTotalT.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtTotalT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalTActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Geometr706 BlkCn BT", 1, 36)); // NOI18N
        jLabel25.setText("Total");

        Checkout.setText("Checkout");
        Checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TransaksiLayout = new javax.swing.GroupLayout(Transaksi);
        Transaksi.setLayout(TransaksiLayout);
        TransaksiLayout.setHorizontalGroup(
            TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiLayout.createSequentialGroup()
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(31, 31, 31)
                                .addComponent(txtKodeT, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiLayout.createSequentialGroup()
                                .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNamaBT, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                                    .addComponent(txtHargaT))))
                        .addGap(220, 220, 220)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtJumlahT, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .addComponent(txtTotalT)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TransaksiLayout.createSequentialGroup()
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNoT, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .addComponent(txtTglT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TransaksiLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(14, 14, 14)
                                .addComponent(txtNamaPT, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdT, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(TransaksiLayout.createSequentialGroup()
                        .addComponent(BtambahT)
                        .addGap(18, 18, 18)
                        .addComponent(BubahT, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BhapusT, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BbatalT, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(515, 515, 515)
                        .addComponent(Checkout)))
                .addGap(21, 21, 21))
        );
        TransaksiLayout.setVerticalGroup(
            TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TransaksiLayout.createSequentialGroup()
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtNoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(txtTglT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(TransaksiLayout.createSequentialGroup()
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamaPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))))
                .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtJumlahT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKodeT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiLayout.createSequentialGroup()
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtNamaBT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHargaT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)))
                    .addGroup(TransaksiLayout.createSequentialGroup()
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalT, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(TransaksiLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel25)))
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TransaksiLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(BbatalT)
                                    .addComponent(BhapusT)
                                    .addComponent(BubahT)
                                    .addComponent(BtambahT)))
                            .addGroup(TransaksiLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(Checkout)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        MenuUtama.addTab("TRANSAKSI", Transaksi);

        jTabbedPane5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane5MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        TabelRP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TabelRP);

        jLabel1.setFont(new java.awt.Font("Britannic Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REKAPAN  DATA PEMBELIAN PELANGGAN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("PEMBELI", jPanel2);

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));

        TabelRM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(TabelRM);

        jLabel3.setFont(new java.awt.Font("Britannic Bold", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("REKAPAN  DATA TRANSAKSI MENU");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("MENU", jPanel6);

        MenuUtama.addTab("REKAP", jTabbedPane5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(MenuUtama, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(MenuUtama, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKodeMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeMActionPerformed

    private void txtNamaMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaMActionPerformed

    private void txtTelpMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelpMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelpMActionPerformed

    private void txtAlamatMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlamatMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlamatMActionPerformed

    private void TambahMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahMemberActionPerformed
        try{
            if (txtKodeM.getText().equalsIgnoreCase("")||
                txtNamaM.getText().equalsIgnoreCase("")||
                txtAlamatM.getText().equalsIgnoreCase("")||
                txtTelpM.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Data tidak boleh kosong!", "Validasi Data",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            for (int i = 0; i < 10; i++) {
                if (mbr[i]!=null && mbr[i].getId().equalsIgnoreCase(txtKodeM.getText())) {
                    JOptionPane.showMessageDialog(null, "Data Sudah ada!", "Validasi Data",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

            // aksi simpan data
            if(TambahMember.getText()=="Tambah"){
                String i = txtKodeM.getText();
                String n = txtNamaM.getText();
                String a = txtAlamatM.getText();
                String t = txtTelpM.getText();
                mbr[dataMbr] = new cMembership(i, n, a, t);
                dataMbr++;
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
                bersihMember();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_TambahMemberActionPerformed

    private void UbahMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UbahMemberActionPerformed
        if (txtKodeM.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Data yang ingin dihapus belum dipilih!");

        }
        else{
            int jawab = JOptionPane.showConfirmDialog(this, "Apakah yakin diubah?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (jawab==0) {
                try{
                    for (int j = 0; j < dataMbr; j++) {
                        if (txtKodeM.getText().equalsIgnoreCase(mbr[j].getId())) {
                            mbr[j].setNama(txtNamaM.getText());
                            mbr[j].setAlamat(txtAlamatM.getText());
                            mbr[j].setTelepon(txtTelpM.getText());
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Data berhasil diubah");
                    bersihMember();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Data tidak jadi diubah..");
            }
        }

    }//GEN-LAST:event_UbahMemberActionPerformed

    private void HapusMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusMemberActionPerformed
        if (txtKodeM.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Data yang ingin dihapus belum dipilih!");

        }
        else{
            int jawab = JOptionPane.showConfirmDialog(this, "Apakah yakin dihapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (jawab==0) {
                try{
                    for (int i = 0; i < mbr.length; i++) {
                        if (mbr[i]!= null && mbr[i].getNama().equalsIgnoreCase(txtNamaM.getText())) {
                            mbr[i] = null;
                            for (int j = 0; j < mbr.length; j++) {
                                if (j == mbr.length-1) {
                                    mbr[i] = null;
                                }
                                else{
                                    mbr[i] = mbr[i+1];
                                }
                            }
                        }
                    }
                    bersihMember();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Data tidak jadi dihapus..");
            }
        }
    }//GEN-LAST:event_HapusMemberActionPerformed

    private void BatalMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BatalMemberActionPerformed
        bersihMember();
    }//GEN-LAST:event_BatalMemberActionPerformed

    private void txtCariMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariMActionPerformed

    private void CariMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariMemberActionPerformed
        TampilcariMember();
    }//GEN-LAST:event_CariMemberActionPerformed

    private void TabelMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMMouseClicked
        txtKodeM.setText(TabelM.getValueAt(TabelM.getSelectedRow(),0).toString());
        txtNamaM.setText(TabelM.getValueAt(TabelM.getSelectedRow(),1).toString());
        txtAlamatM.setText(TabelM.getValueAt(TabelM.getSelectedRow(),2).toString());
        txtTelpM.setText(TabelM.getValueAt(TabelM.getSelectedRow(),3).toString());

        txtKodeM.setEditable(false);
    }//GEN-LAST:event_TabelMMouseClicked

    private void txtKodeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeBActionPerformed

    private void txtNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaBarangActionPerformed

    private void txtHargaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaBarangActionPerformed

    private void BtambahBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtambahBActionPerformed
        // TODO add your handling code here:
        try{
            for (int i = 0; i < 10; i++) {
                if (mkn[i]!=null && txtKodeB.getText().equalsIgnoreCase(mkn[i].getKode())) {
                    JOptionPane.showMessageDialog(null, "Maaf Kode Barang Sudah Ada");
                    return;
                }
                if(mnm[i]!= null && txtKodeB.getText().equalsIgnoreCase(mnm[i].getKode())){
                    JOptionPane.showMessageDialog(null, "Maaf Kode Barang Sudah Ada");
                    return;
                }
            }

            if (txtKodeB.getText().equalsIgnoreCase("")||
                txtNamaBarang.getText().equalsIgnoreCase("")||
                txtHargaBarang.getText().equalsIgnoreCase("")||
                cmbTipe.getSelectedItem().equals("- Pilih Tipe Barang -")) {
                JOptionPane.showMessageDialog(null, "Data tidak boleh kosong!", "Validasi Data",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // aksi simpan data

            if(BtambahB.getText()=="Tambah"){
                String k = txtKodeB.getText();
                String n = txtNamaBarang.getText();
                String t = cmbTipe.getSelectedItem().toString();
                int h = Integer.parseInt(txtHargaBarang.getText());
                int makan = dataMkn;
                int minum = dataMnm;
                if (cmbTipe.getSelectedItem().toString().equalsIgnoreCase("Makanan")) {
                    mkn[makan] = new cMakanan (k,n,t,h);
                    dataMkn++;
                }
                else {
                    mnm[minum] = new cMinuman (k,n,t,h);
                    dataMnm++;
                }
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
                bersihBarang();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_BtambahBActionPerformed

    private void BubahBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BubahBActionPerformed
        // aksi ubah data
        int jawab = JOptionPane.showConfirmDialog(this, "Apakah yakin diubah?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (jawab==0) {
            try{
                if (cmbTipe.getSelectedItem().toString().equalsIgnoreCase("Makanan")) {
                    for (int i = 0; i < dataMkn; i++) {
                        if (txtKodeB.getText().equalsIgnoreCase(mkn[i].getKode())) {
                            mkn[i].setNama(txtNamaBarang.getText());
                            mkn[i].setTipe(cmbTipe.getSelectedItem().toString());
                            int h = Integer.parseInt(txtHargaBarang.getText());
                            mkn[i].setharga(h);
                        }
                    }
                }
                else{
                    for (int i = 0; i < dataMnm; i++) {
                        if (txtKodeB.getText().equalsIgnoreCase(mnm[i].getKode())) {
                            mnm[i].setNama(txtNamaBarang.getText());
                            mnm[i].setTipe(cmbTipe.getSelectedItem().toString());
                            int h = Integer.parseInt(txtHargaBarang.getText());
                            mnm[i].setharga(h);
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Data berhasil diubah");
                bersihBarang();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Data tidak jadi diubah..");
        }
    }//GEN-LAST:event_BubahBActionPerformed

    private void BhapusBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BhapusBActionPerformed
        if (txtHargaBarang.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Data yang ingin dihapus belum dipilih!");
        }
        else{
            int jawab = JOptionPane.showConfirmDialog(this, "Apakah yakin dihapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (jawab==0) {
                try{
                    if (cmbTipe.getSelectedItem().toString().equalsIgnoreCase("Makanan")) {
                        for (int i = 0; i < mkn.length; i++) {
                            if (mkn[i]!= null && mkn[i].getNama().equalsIgnoreCase(txtNamaBarang.getText())) {
                                mkn[i] = null;
                                for (int j = 0; j < mkn.length; j++) {
                                    if (j == mkn.length-1) {
                                        mkn[i] = null;
                                    }
                                    else{
                                        mkn[i] = mkn[i+1];
                                    }
                                }
                            }
                        }
                    }
                    else{
                        for (int i = 0; i < mnm.length; i++) {
                            if (mnm[i]!= null && mnm[i].getNama().equalsIgnoreCase(txtNamaBarang.getText())) {
                                mnm[i] = null;
                                for (int j = 0; j < mnm.length; j++) {
                                    if (j == mnm.length-1) {
                                        mnm[i] = null;
                                    }
                                    else{
                                        mnm[i] = mnm[i+1];
                                    }
                                }
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                    bersihBarang();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Data tidak jadi dihapus..");
            }
        }
    }//GEN-LAST:event_BhapusBActionPerformed

    private void BbatalBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BbatalBActionPerformed
        bersihBarang();
    }//GEN-LAST:event_BbatalBActionPerformed

    private void txtCariBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariBActionPerformed

    private void BcariBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BcariBActionPerformed
        TampilcariBarang();
    }//GEN-LAST:event_BcariBActionPerformed

    private void TabelBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelBMouseClicked
        txtKodeB.setText(TabelB.getValueAt(TabelB.getSelectedRow(),0).toString());
        txtNamaBarang.setText(TabelB.getValueAt(TabelB.getSelectedRow(),1).toString());
        cmbTipe.setSelectedItem(TabelB.getValueAt(TabelB.getSelectedRow(),2).toString());
        txtHargaBarang.setText(TabelB.getValueAt(TabelB.getSelectedRow(),3).toString());

        txtKodeB.setEditable(false);
    }//GEN-LAST:event_TabelBMouseClicked

    private void txtNoTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoTActionPerformed

    private void txtHargaTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaTActionPerformed

    private void BtambahTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtambahTActionPerformed
        if(txtTglT.getDate()!=null){
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            tanggal=format.format(txtTglT.getDate());
        }
        try{
            
            if (txtNoT.getText().equalsIgnoreCase("")||
                tanggal.equalsIgnoreCase("")||
                txtHargaT.getText().equalsIgnoreCase("")||
                txtNamaBT.getText().equalsIgnoreCase("")||
                txtNamaPT.getText().equalsIgnoreCase("")||
                txtIdT.getText().equalsIgnoreCase("")||
                txtKodeT.getText().equalsIgnoreCase("")||
                txtJumlahT.getText().equalsIgnoreCase("")||
                txtTotalT.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null, "Data tidak boleh kosong!", "Validasi Data",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // aksi simpan data
            if(BtambahT.getText()=="Tambah"){
                String no = txtNoT.getText();
                String t = tanggal;
                String id = txtIdT.getText();
                String np = txtNamaPT.getText();
                String k = txtKodeT.getText();
                String nb = txtNamaBT.getText();
                String h = txtHargaT.getText();
                String j = txtJumlahT.getText();
                String tot = txtTotalT.getText();
                trans[dataTrs] = new cTransaksi (no,t,id,np,k,nb,h,j,tot);
                dataTrs++;
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
                //                    CatatBarang();
                bersihTransaksi();
                sudahtambah = true;
                TampiltabelbaruTransaksi();
                txtNoT.setEditable(false);
                txtIdT.setEditable(false);
                txtNamaPT.setEditable(false);
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_BtambahTActionPerformed

    private void BubahTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BubahTActionPerformed
        if (txtHargaT.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Data yang ingin diubah belum dipilih!");

        }
        else{
            // aksi ubah data
            int jawab = JOptionPane.showConfirmDialog(this, "Apakah yakin diubah?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (jawab==0) {
                try{
                    for (int i = 0; i < trans.length; i++) {
                        if(trans[i]!=null && txtNamaBT.getText().equalsIgnoreCase(trans[i].getNamaB())){
                            trans[i].setKode(txtKodeT.getText());
                            trans[i].setNamaB(txtNamaBT.getText());
                            trans[i].setharga(txtHargaT.getText());
                            trans[i].setJumlah(txtJumlahT.getText());
                            trans[i].setTotal(txtTotalT.getText());
                            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
                        }
                    }
                            bersihTransaksi();
                            txtNoT.setEditable(false);
                            txtIdT.setEditable(false);
                            txtNamaPT.setEditable(false);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Data tidak jadi diubah");
            }
        }

    }//GEN-LAST:event_BubahTActionPerformed

    private void BhapusTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BhapusTActionPerformed
        if (txtHargaT.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Data yang ingin dihapus belum dipilih!");

        }
        else{
            int jawab = JOptionPane.showConfirmDialog(this, "Apakah yakin dihapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (jawab==0) {
                try{
                    for (int i = 0; i < trans.length; i++) {
                        if (trans[i]!= null && txtNamaBT.getText().equalsIgnoreCase(trans[i].getNamaB())) {
                            trans[i] = null;
                            for (int j = 0; j < trans.length; j++) {
                                if (j == trans.length-1) {
                                    trans[i] = null;
                                }
                                else{
                                    trans[i] = trans[i+1];
                                }
                            }
                        }
                    }
                    dataTrs--;
                    bersihTransaksi();
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Data tidak jadi dihapus");
            }
        }
    }//GEN-LAST:event_BhapusTActionPerformed

    private void BbatalTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BbatalTActionPerformed
        if (sudahtambah == true) {
            bersihTransaksi();
        }
        else{
            bersihSemuaTransaksi();
        }
    }//GEN-LAST:event_BbatalTActionPerformed

    private void TabelTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelTMouseClicked
        if(TabelT.getSelectedRow()>-1){
            try {
                txtNoT.setText(TabelT.getValueAt(TabelT.getSelectedRow(),0).toString());
                Date format = new SimpleDateFormat("dd-MM-yyyy").parse((String)TabelT.getValueAt(TabelT.getSelectedRow(),1));
                txtTglT.setDate(format);
            } catch (Exception e) {
            }
            txtIdT.setText(TabelT.getValueAt(TabelT.getSelectedRow(), 2).toString());
            txtNamaPT.setText(TabelT.getValueAt(TabelT.getSelectedRow(), 3).toString());
            txtKodeT.setText(TabelT.getValueAt(TabelT.getSelectedRow(), 4).toString());
            txtNamaBT.setText(TabelT.getValueAt(TabelT.getSelectedRow(), 5).toString());
            txtHargaT.setText(TabelT.getValueAt(TabelT.getSelectedRow(), 6).toString());
            txtJumlahT.setText(TabelT.getValueAt(TabelT.getSelectedRow(), 7).toString());
            txtTotalT.setText(TabelT.getValueAt(TabelT.getSelectedRow(), 8).toString());
        }
    }//GEN-LAST:event_TabelTMouseClicked

    private void txtKodeTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKodeTFocusLost
        try {
            boolean ada = false;
            for (int i = 0; i < mkn.length; i++) {

                if(mkn[i]!=null && txtKodeT.getText().equalsIgnoreCase(mkn[i].getKode())) {
                    txtNamaBT.setText(mkn[i].getNama());
                    String help = String.valueOf(mkn[i].getharga());
                    txtHargaT.setText(help);
                    ada = true;
                    break;
                }
                if(mnm[i]!=null && txtKodeT.getText().equalsIgnoreCase(mnm[i].getKode())) {
                    txtNamaBT.setText(mnm[i].getNama());
                    String help = String.valueOf(mnm[i].getharga());
                    txtHargaT.setText(help);
                    ada = true;
                    break;
                }
            }
            if(ada==false){
                    JOptionPane.showMessageDialog(null, "Kode Barang Tidak Ada");
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Memuat Data \n Data Tidak di Temukan"+e.getMessage());
        }
    }//GEN-LAST:event_txtKodeTFocusLost

    private void txtNamaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaBTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaBTActionPerformed

    private void txtIdTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdTFocusLost
        try {
            for (int i = 0; i < mbr.length; i++) {
                if(mbr[i]!=null && txtIdT.getText().equalsIgnoreCase(mbr[i].getId())) {
                    txtNamaPT.setText(mbr[i].getNama());
                    JOptionPane.showMessageDialog(null, "Karena Anda Adalah Member, Anda Berhak Mendapatkan Diskon 10% di Akhir Transaksi ");
                    i = mbr.length;
                    break;
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Memuat Data \n Data Tidak di Temukan"+e.getMessage());
        }
    }//GEN-LAST:event_txtIdTFocusLost

    private void txtIdTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTActionPerformed

    private void txtJumlahTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahTActionPerformed

    private void txtJumlahTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahTKeyReleased
        String harga = txtHargaT.getText();
        String jumlah = txtJumlahT.getText();
        int hrg = Integer.parseInt(harga);
        int jmlh = Integer.parseInt(jumlah);
        int hasil = hrg * jmlh;
        txtTotalT.setText(hasil+"");
    }//GEN-LAST:event_txtJumlahTKeyReleased

    private void txtTotalTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalTActionPerformed

    private void txtTglTPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTglTPropertyChange
        
    }//GEN-LAST:event_txtTglTPropertyChange

    private void CheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckoutActionPerformed
        int hasil = 0;
        String t = "";
        int jawab = JOptionPane.showConfirmDialog(this, "Checkout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (jawab==0) {
            for (int i = temp; i < dataTrs; i++) {
                if (trans[i]!=null) {
                    int help = Integer.parseInt(trans[i].getTotal());
                    hasil =  hasil + help;
                }
            }
            boolean cukup = false;
            
                t = t +"=========STRUK PEMBELIAN=========";
            t = t +  "\n==========WARUNG MAK TIK=========";
            t = t + "\nNomor Transaksi   : " + trans[temp].getNomor();
            t = t + "\nTanggal Transaksi : " + trans[temp].getTanggal();
            t = t + "\nId Pelanggan          : " + trans[temp].getIdPelanggan();
            t = t + "\nNama Pelanggan   : " + trans[temp].getNamaP();
            t = t +  "\n\nNo        Menu          Harga        Jumlah        Total";

            for (int i = temp; i < dataTrs; i++) {
                int angka = 1;
                if (trans[i]!=null) {
                    t = t +"\n"+(angka) + ".          " + trans[i].getNamaB() + "          " + trans[i].getHarga() + "               " + trans[i].getJumlah() + "            " + trans[i].getTotal();
                    angka++;
                }
            }
            boolean ada = false;
            for (int i = 0; i < mbr.length; i++) {
                if (mbr[i]!=null && txtIdT.getText().equalsIgnoreCase(mbr[i].getId())) {
                    hasil = hasil * 90/100;
                    t = t + "\nSelamat Anda Mendapatkan Diskon 10%";
                    t = t + "\nTotal : " + hasil;
                    ada = true;
                }
            }
            if (ada==false) {
                    t = t + "\nTotal : " + hasil;
                }
            t = t + "\n\nNominal Uang Rp : ";
            do{
            int bayar = Integer.parseInt(JOptionPane.showInputDialog(this, t)) ;
            if (bayar >= hasil) {
                int kembalian = bayar - hasil;
                JOptionPane.showMessageDialog(this, "Kembali : Rp"+ kembalian+ "\n\nTerima Kasih!");
                DefaultTableModel model = (DefaultTableModel)TabelT.getModel();
                cukup = true;
            }
            else{
                JOptionPane.showMessageDialog(this, "Maaf Uang Anda Tidak Mencukupi. Silahkan Gunakan Nominal Uang yang Lebih Besar!");
            }
            }while(cukup==false);
            try {
                if (ada = false) {
                    trans[temp].setNamaP("Non Membership");
                }
                stat = con.createStatement();
                res = stat.executeQuery("SELECT * FROM rekap_pembeli");
                while(res.next()){
                    String namaDB = res.getString("nama");
                    int totalDB = Integer.parseInt(res.getString("total"));
                    if (namaDB.equalsIgnoreCase(trans[temp].getNamaP())) {
                        hasil = hasil + totalDB;
                        String sqll = "DELETE FROM rekap_pembeli WHERE nama = '" + trans[temp].getNamaP() +"'";
                        stat.executeUpdate(sqll);
                    }
                }
                res = stat.executeQuery("SELECT * FROM rekap_menu");
                while(res.next()){
                    
                    String namaMDB = res.getString("barang");
                    int totalMDB = Integer.parseInt(res.getString("total"));
                    for (int i = temp; i < dataTrs; i++) {
                        if (trans[i]!=null && trans[i].getNamaB().equalsIgnoreCase(namaMDB)) {
                            int helpmenu = Integer.parseInt(trans[temp].getTotal());
                            helpmenu = helpmenu + totalMDB;
                            String sqll = "DELETE FROM rekap_menu WHERE barang = '" + trans[temp].getNamaB() +"'";
                            stat.executeUpdate(sqll);
                        }
                    }
                }
                    res = stat.executeQuery("SELECT * FROM rekap_pembeli");
                    String sql ="INSERT INTO rekap_pembeli VALUES('" + trans[temp].getNamaP() +
                    "','" + hasil + "')";
                    stat.execute(sql);
                    res = stat.executeQuery("SELECT * FROM rekap_menu");
                    for (int i = temp; i < dataTrs; i++) {
                    sql = "INSERT INTO rekap_menu VALUES('" + trans[i].getNamaB()+
                    "','" + trans[i].getTotal() + "')";
                    stat.execute(sql);
                }
                    
                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            temp = dataTrs;
            TampiltabelbaruTransaksi();;
            bersihSemuaTransaksi();
        }
    }//GEN-LAST:event_CheckoutActionPerformed

    private void jTabbedPane5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane5MouseClicked
        TampilrekapP();
        TampilrekapM();
    }//GEN-LAST:event_jTabbedPane5MouseClicked

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
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BatalMember;
    private javax.swing.JToggleButton BbatalB;
    private javax.swing.JToggleButton BbatalT;
    private javax.swing.JToggleButton BcariB;
    private javax.swing.JToggleButton BhapusB;
    private javax.swing.JToggleButton BhapusT;
    private javax.swing.JToggleButton BtambahB;
    private javax.swing.JToggleButton BtambahT;
    private javax.swing.JToggleButton BubahB;
    private javax.swing.JToggleButton BubahT;
    private javax.swing.JToggleButton CariMember;
    private javax.swing.JButton Checkout;
    private javax.swing.JToggleButton HapusMember;
    private javax.swing.JTabbedPane MenuUtama;
    private javax.swing.JTable TabelB;
    private javax.swing.JTable TabelM;
    private javax.swing.JTable TabelRM;
    private javax.swing.JTable TabelRP;
    private javax.swing.JTable TabelT;
    private javax.swing.JToggleButton TambahMember;
    private javax.swing.JPanel Transaksi;
    private javax.swing.JToggleButton UbahMember;
    private javax.swing.JComboBox<String> cmbTipe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTextField txtAlamatM;
    private javax.swing.JTextField txtCariB;
    private javax.swing.JTextField txtCariM;
    private javax.swing.JTextField txtHargaBarang;
    private javax.swing.JTextField txtHargaT;
    private javax.swing.JTextField txtIdT;
    private javax.swing.JTextField txtJumlahT;
    private javax.swing.JTextField txtKodeB;
    private javax.swing.JTextField txtKodeM;
    private javax.swing.JTextField txtKodeT;
    private javax.swing.JTextField txtNamaBT;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtNamaM;
    private javax.swing.JTextField txtNamaPT;
    private javax.swing.JTextField txtNoT;
    private javax.swing.JTextField txtTelpM;
    private com.toedter.calendar.JDateChooser txtTglT;
    private javax.swing.JTextField txtTotalT;
    // End of variables declaration//GEN-END:variables
}
