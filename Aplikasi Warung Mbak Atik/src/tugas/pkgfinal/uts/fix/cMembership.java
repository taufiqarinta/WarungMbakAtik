package tugas.pkgfinal.uts.fix;
public class cMembership {
    private String id;
    private String nama; 
    private String alamat;
    private String telepon;
    cMembership(String i, String n, String a, String t){
        id = i;
        nama = n; 
        alamat = a;
        telepon = t;
    } 
    public void setNama(String n){
        nama = n; 
    } 
    public String getNama(){
        return nama; 
    } 
    public void setId(String i){
        id = i; 
    } 
    public String getId(){
        return id; 
    } 
    public void setAlamat(String a){
        alamat = a; 
    } 
    public String getAlamat(){
        return alamat; 
    } 
    public void setTelepon(String t){
       telepon = t; 
    } 
    public String getTelepon(){
        return telepon; 
    } 
}
