/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsession2;

/**
 *
 * @author User
 */
public abstract class Hewan {
    // Encapsulation: atribut private
    private String nama;
    private int umur;          // dalam tahun
    private double berat;      // dalam kg
    private boolean sudahDivaksin;
    private boolean dirawatInap;

    public Hewan(String nama, int umur, double berat) {
        this.nama = nama;
        this.sudahDivaksin = false;
        this.dirawatInap = false;
        setUmur(umur);
        setBerat(berat);
    }

    // Getter
    public String getNama() { return nama; }
    public int getUmur() { return umur; }
    public double getBerat() { return berat; }
    public boolean isSudahDivaksin() { return sudahDivaksin; }
    public boolean isDirawatInap() { return dirawatInap; }

    // Setter dengan validasi
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setUmur(int umur) {
        if (umur >= 0) {
            this.umur = umur;
        } else {
            System.out.println("Umur tidak boleh negatif!");
        }
    }

    public void setBerat(double berat) {
        if (berat > 0) {
            this.berat = berat;
        } else {
            System.out.println("Berat harus lebih dari 0!");
        }
    }

    public void setSudahDivaksin(boolean sudahDivaksin) {
        this.sudahDivaksin = sudahDivaksin;
    }

    // Perubahan status rawat inap hanya lewat method
    public void rawatInap() {
        if (!dirawatInap) {
            dirawatInap = true;
            System.out.println(nama + " masuk rawat inap.");
        } else {
            System.out.println(nama + " sudah dalam rawat inap.");
        }
    }

    public void pulangkan() {
        if (dirawatInap) {
            dirawatInap = false;
            System.out.println(nama + " sudah boleh pulang.");
        } else {
            System.out.println(nama + " tidak sedang dirawat inap.");
        }
    }

    // Abstract methods
    public abstract String suaraKhas();
    public abstract double hitungBiayaPerawatan(int hari);

    // Overloading: nama sama, parameter berbeda
    public void periksa(String keluhan) {
        System.out.println("Pemeriksaan " + nama + " dengan keluhan: " + keluhan);
    }

    public void periksa(String keluhan, double suhuTubuh) {
        System.out.println("Pemeriksaan " + nama + " dengan keluhan: " + keluhan
                + " (suhu tubuh " + suhuTubuh + " C)");
        if (suhuTubuh > 39.5) {
            System.out.println("  Catatan: suhu tinggi, disarankan rawat inap.");
        } else {
            System.out.println("  Catatan: suhu normal.");
        }
    }

    public void tampilkanInfo() {
        System.out.println("Nama     : " + nama);
        System.out.println("Umur     : " + umur + " tahun");
        System.out.println("Berat    : " + berat + " kg");
        System.out.println("Vaksin   : " + (sudahDivaksin ? "Sudah" : "Belum"));
        System.out.println("Rawat inap: " + (dirawatInap ? "Ya" : "Tidak"));
    }
}
