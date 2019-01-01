package id.fourmotion.cavii.Model;

public class Content {

    private String _id;
    private String judul;
    private String jenis;
    private String bahan;
    private String harga;
    private String imgPath;
    private String desc;
    private String phone;
    private String location;
    private String alamat;

    public Content(String _id, String judul, String jenis, String bahan, String harga, String imgPath, String desc, String phone, String location, String alamat) {
        this._id = _id;
        this.judul = judul;
        this.jenis = jenis;
        this.bahan = bahan;
        this.harga = harga;
        this.imgPath = imgPath;
        this.desc = desc;
        this.phone = phone;
        this.location = location;
        this.alamat = alamat;
    }


    // Getter
    public String get_id() {
        return _id;
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

    public String getImgPath() {
        return imgPath;
    }

    public String getDesc() {
        return desc;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getAlamat() {
        return alamat;
    }

    // Setter
    public void set_id(String _id) {
        this._id = _id;
    }


    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
/*
    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    */


}
