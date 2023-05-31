package tugas.pkgfinal.uts.fix;
public class cMasterBarang {
    private String nama; 
    private int harga; 
    private String tipe;
    private String kode;
    private int total = 0; 
    
    cMasterBarang(String k, String n, String t,int h){
        kode = k;
        nama = n;
        tipe = t;
        harga = h; 
    } 
    public void setharga(int h){
        harga = h; 
    } 
    public int getharga(){
        return harga; 
    }
    public void setNama(String n){
        nama = n; 
    }
    public String getNama(){
        return nama;
    }
    public void setTipe(String t){
        tipe = t; 
    } 
    public String getTipe(){
        return tipe; 
    }
    public void setKode(String k){
        kode = k; 
    } 
    public String getKode(){
        return kode; 
    }
}
