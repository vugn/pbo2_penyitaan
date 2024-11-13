package GUI.Institusi;

public class Institusi {
    private final int id;
    private final String namaInstitusi;
    private final String alamat;

    public Institusi(int id, String namaInstitusi, String alamat) {
        this.id = id;
        this.namaInstitusi = namaInstitusi;
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    public String getNamaInstitusi() {
        return namaInstitusi;
    }

    public String getAlamat() {
        return alamat;
    }
}