/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsession2;

/**
 *
 * @author User
 */
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== SISTEM KLINIK HEWAN =====\n");

        // Runtime polymorphism: objek subclass disimpan di referensi superclass
        ArrayList<Hewan> daftar = new ArrayList<>();
        daftar.add(new Kucing("Mochi", 2, 4.2, true));
        daftar.add(new Anjing("Bruno", 4, 30.5, "Labrador"));
        daftar.add(new Kelinci("Snowy", 1, 1.8, "Putih"));
        daftar.add(new Anjing("Coco", 3, 8.0, "Pomeranian"));

        // 1. Tampilkan data pasien (method overriding)
        System.out.println("--- DAFTAR PASIEN ---");
        for (Hewan h : daftar) {
            h.tampilkanInfo();
            System.out.println();
        }

        // 2. Suara khas (runtime polymorphism)
        System.out.println("--- SUARA PASIEN ---");
        for (Hewan h : daftar) {
            System.out.println(h.getNama() + " : " + h.suaraKhas());
        }
        System.out.println();

        // 3. Pemeriksaan (method overloading)
        System.out.println("--- PEMERIKSAAN ---");
        daftar.get(0).periksa("Bersin-bersin");
        daftar.get(1).periksa("Lesu dan tidak mau makan", 40.1);
        System.out.println();

        // 4. Rawat inap dan biaya (abstract method diimplementasikan tiap subclass)
        System.out.println("--- RAWAT INAP DAN BIAYA (5 HARI) ---");
        daftar.get(1).rawatInap();
        daftar.get(1).rawatInap(); // gagal karena sudah dirawat
        for (Hewan h : daftar) {
            System.out.println(h.getNama() + " : Rp "
                    + String.format("%,.0f", h.hitungBiayaPerawatan(5)));
        }
        daftar.get(1).pulangkan();
        System.out.println();

        // 5. Ubah data lewat setter
        System.out.println("--- UBAH DATA LEWAT SETTER ---");
        Hewan kelinci = daftar.get(2);
        System.out.println("Berat lama: " + kelinci.getBerat() + " kg");
        kelinci.setBerat(2.1);
        kelinci.setBerat(-1); // ditolak oleh validasi
        System.out.println("Berat baru: " + kelinci.getBerat() + " kg");
        System.out.println();

        // 6. Interface Vaksinasi
        System.out.println("--- VAKSINASI (INTERFACE) ---");
        for (Hewan h : daftar) {
            if (h instanceof Vaksinasi) {
                Vaksinasi v = (Vaksinasi) h;
                v.suntikVaksin();
                System.out.println("  Jadwal: " + v.getJadwalVaksin());
            }
        }
        System.out.println();

        // 7. Cek status vaksin setelah penyuntikan
        System.out.println("--- STATUS VAKSIN SETELAH PENYUNTIKAN ---");
        for (Hewan h : daftar) {
            System.out.println(h.getNama() + " : " + (h.isSudahDivaksin() ? "Sudah" : "Belum"));
        }
    }
}
