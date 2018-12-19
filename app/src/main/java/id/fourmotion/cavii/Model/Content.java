package id.fourmotion.cavii.Model;

public class Content {

    private String judul;
    private String jenis;
    private String bahan;
    private String harga;

    public Content(String judul, String jenis, String bahan, String harga) {
        this.judul = judul;
        this.jenis = jenis;
        this.bahan = bahan;
        this.harga = harga;
    }

    public String getJudul() {
        return judul;
    }

    public String getJenis() {
        return jenis;
    }

    public String getBahan() {
        return bahan;
    }

    public String getHarga() {
        return harga;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
