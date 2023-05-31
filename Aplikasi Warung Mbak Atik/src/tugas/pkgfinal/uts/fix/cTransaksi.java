package tugas.pkgfinal.uts.fix;
public class cTransaksi {
    private String namab;
    private String tanggal;
    private String nomor;
    private String kode;
    private String idp;
    private String namap;
    private String harga;
    private String jumlah;
    private String total;
    
    cTransaksi(String no, String t, String id, String np, String k, String nb, String h, String j, String tot ){
        nomor = no;
        tanggal = t;
        kode = k;
        namap = np;
        idp = id;
        namab = nb;
        harga = h;
        jumlah = j; 
        total = tot; 
    } 
    public void setNamaB(String nb){
        namab = nb; 
    } 
    public String getNamaB(){
        return namab; 
    } 
    public void setNamaP(String np){
        namap = np; 
    } 
    public String getNamaP(){
        return namap; 
    } 
    public void setIdPelanggan(String id){
        idp = id; 
    } 
    public String getIdPelanggan(){
        return idp; 
    } 
    public void setTanggal(String t){
        tanggal = t; 
    } 
    public String getTanggal(){
        return tanggal; 
    } 
    public void setNomor(String no){
        nomor = no; 
    } 
    public String getNomor(){
        return nomor; 
    } 
    public void setKode(String k){
        kode = k; 
    } 
    public String getKode(){
        return kode; 
    } 
    public void setharga(String h){
        harga = h; 
    } 
    public String getHarga(){
        return harga; 
    } 
    public void setJumlah(String j){
        jumlah = j; 
    } 
    public String getJumlah(){
        return jumlah; 
    } 
    public void setTotal(String tot){
        total = tot; 
    } 
    public String getTotal(){
        return total; 
    } 
}
