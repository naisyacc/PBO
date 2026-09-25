/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsession;

/**
 *
 * @author User
 */
import java.util.ArrayList;

// ================= POIN 1: CLASS & OBJECT =================
public class Buku {

    // Atribut class
    private String judul;
    private String penulis;
    private int tahunTerbit;
    private boolean tersedia;

    // ================= POIN 3: KONSTANTA =================
    public static final double DENDA_PER_HARI = 2000.0;

    // ================= POIN 2: CONSTRUCTOR =================
    public Buku(String judul, String penulis, int tahunTerbit, boolean tersedia) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.tersedia = tersedia;
    }

    // ================= POIN 1: METHOD 1 =================
    public String getInfo() {
        String status = tersedia ? "Tersedia" : "Dipinjam";

        // Method String: toUpperCase()
        return "Judul: " + judul.toUpperCase()
                + " | Penulis: " + penulis
                + " | Tahun: " + tahunTerbit
                + " | Status: " + status;
    }

    // ================= POIN 1: METHOD 2 =================
    public double hitungDenda(int hariTerlambat) {
        return hariTerlambat * DENDA_PER_HARI;
    }

    // Getter
    public String getJudul() {
        return judul;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }
}
