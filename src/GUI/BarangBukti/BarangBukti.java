package GUI.BarangBukti;

public class BarangBukti {
    private final int id;
    private final String asalPemohon;
    private final String tersangka;
    private final String tindakPidana;
    private final String dokumen;
    private final String tahap;

    public BarangBukti(int id, String asalPemohon, String tersangka, String tindakPidana, String dokumen, String tahap) {
        this.id = id;
        this.asalPemohon = asalPemohon;
        this.tersangka = tersangka;
        this.tindakPidana = tindakPidana;
        this.dokumen = dokumen;
        this.tahap = tahap;
    }

    public int getId() {
        return id;
    }

    public String getAsalPemohon() {
        return asalPemohon;
    }

    public String getTersangka() {
        return tersangka;
    }

    public String getTindakPidana() {
        return tindakPidana;
    }

    public String getDokumen() {
        return dokumen;
    }

    public String getTahap() {
        return tahap;
    }
}