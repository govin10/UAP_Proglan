import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nama;
    private String jenis;
    private int jumlah;
    private String kode;

    public Item(String nama, String jenis, int jumlah, String kode) {
        this.nama = nama;
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.kode = kode;
    }

    // Getters and Setters
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }
    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }
}