package library.model;

import java.util.ArrayList;

public class Member {
    private String id;
    private String nama;
    private ArrayList<String> daftarPinjaman;

    public Member(String id, String nama) {
        this.id = id;
        this.nama = nama;
        this.daftarPinjaman = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public ArrayList<String> getDaftarPinjaman() {
        return daftarPinjaman;
    }

    public int getJumlahPinjaman() {
        return daftarPinjaman.size();
    }

    public void tambahPinjaman(String judulBuku) {
        daftarPinjaman.add(judulBuku);
    }

    public void hapusPinjaman(String judulBuku) {
        daftarPinjaman.remove(judulBuku);
    }

    public void tampilkanInfo() {
        System.out.println("ID: " + id + " | Nama: " + nama
                + " | Jumlah pinjaman: " + daftarPinjaman.size());

        if (daftarPinjaman.isEmpty()) {
            System.out.println("   Belum meminjam buku.");
        } else {
            System.out.println("   Buku dipinjam:");
            for (String judul : daftarPinjaman) {
                System.out.println("   - " + judul);
            }
        }
    }
}