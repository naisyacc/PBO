package library.service;

import library.exception.BookAlreadyBorrowedException;
import library.exception.BookNotFoundException;
import library.exception.BorrowLimitExceededException;
import library.exception.MemberNotFoundException;
import library.model.Book;
import library.model.Member;

import java.util.ArrayList;
import java.util.HashMap;

public class LibraryService {
    private static final int MAKSIMAL_PINJAM = 3;

    private ArrayList<Book> daftarBuku;
    private HashMap<String, Member> daftarAnggota;
    private int totalPinjaman;

    public LibraryService() {
        this.daftarBuku = new ArrayList<>();
        this.daftarAnggota = new HashMap<>();
        this.totalPinjaman = 0;
    }

    public void tambahBuku(String judul, String penulis, int tahunTerbit, String kategori) {
        Book bukuBaru = new Book(judul, penulis, tahunTerbit, kategori);
        daftarBuku.add(bukuBaru);
        System.out.println("Buku berhasil ditambahkan.");
    }

    public void tambahAnggota(String id, String nama) {
        if (!daftarAnggota.containsKey(id)) {
            Member anggotaBaru = new Member(id, nama);
            daftarAnggota.put(id, anggotaBaru);
        }
    }

    public void tampilkanSemuaBuku() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku di perpustakaan.");
            return;
        }

        System.out.println("\n=== DAFTAR BUKU ===");
        for (int i = 0; i < daftarBuku.size(); i++) {
            daftarBuku.get(i).tampilkanInfo(i + 1);
        }
    }

    public void cariBuku(String kataKunci) {
        String keyword = kataKunci.toLowerCase().trim();
        boolean ditemukan = false;

        System.out.println("\n=== HASIL PENCARIAN ===");

        for (Book buku : daftarBuku) {
            String judulLower = buku.getJudul().toLowerCase();
            String kategoriLower = buku.getKategori().toLowerCase();

            if (judulLower.contains(keyword) || kategoriLower.contains(keyword)) {
                buku.tampilkanInfo(0);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada buku yang cocok dengan pencarian \"" + kataKunci + "\".");
        }
    }

    public void pinjamBuku(String idAnggota, String judulBuku)
            throws BookNotFoundException,
            BookAlreadyBorrowedException,
            BorrowLimitExceededException,
            MemberNotFoundException {

        assert idAnggota != null && !idAnggota.trim().isEmpty()
                : "ID anggota tidak boleh kosong.";

        assert judulBuku != null && !judulBuku.trim().isEmpty()
                : "Judul buku tidak boleh kosong.";

        Member anggota = daftarAnggota.get(idAnggota);

        if (anggota == null) {
            throw new MemberNotFoundException("Anggota dengan ID " + idAnggota + " tidak ditemukan.");
        }

        Book buku = cariBukuByJudul(judulBuku);

        if (buku == null) {
            throw new BookNotFoundException("Buku \"" + judulBuku + "\" tidak ditemukan.");
        }

        if (!buku.isTersedia()) {
            throw new BookAlreadyBorrowedException("Buku \"" + buku.getJudul() + "\" sedang dipinjam.");
        }

        if (anggota.getJumlahPinjaman() >= MAKSIMAL_PINJAM) {
            throw new BorrowLimitExceededException(
                    "Anggota " + anggota.getNama() + " sudah meminjam maksimal "
                            + MAKSIMAL_PINJAM + " buku."
            );
        }

        buku.pinjam();
        anggota.tambahPinjaman(buku.getJudul());
        totalPinjaman++;

        System.out.println("Buku \"" + buku.getJudul() + "\" berhasil dipinjam oleh "
                + anggota.getNama() + ".");
    }

    public void kembalikanBuku(String idAnggota, String judulBuku)
            throws BookNotFoundException, MemberNotFoundException {

        assert idAnggota != null && !idAnggota.trim().isEmpty()
                : "ID anggota tidak boleh kosong.";

        assert judulBuku != null && !judulBuku.trim().isEmpty()
                : "Judul buku tidak boleh kosong.";

        Member anggota = daftarAnggota.get(idAnggota);

        if (anggota == null) {
            throw new MemberNotFoundException("Anggota dengan ID " + idAnggota + " tidak ditemukan.");
        }

        Book buku = cariBukuByJudul(judulBuku);

        if (buku == null) {
            throw new BookNotFoundException("Buku \"" + judulBuku + "\" tidak ditemukan.");
        }

        if (anggota.getDaftarPinjaman().contains(buku.getJudul())) {
            buku.kembalikan();
            anggota.hapusPinjaman(buku.getJudul());
            System.out.println("Buku \"" + buku.getJudul() + "\" berhasil dikembalikan.");
        } else {
            System.out.println("Anggota tidak sedang meminjam buku tersebut.");
        }
    }

    private Book cariBukuByJudul(String judul) {
        String judulCari = judul.toLowerCase().trim();

        for (Book buku : daftarBuku) {
            if (buku.getJudul().toLowerCase().equals(judulCari)) {
                return buku;
            }
        }

        return null;
    }

    public void tampilkanLaporan() {
        System.out.println("\n=== LAPORAN PERPUSTAKAAN ===");
        System.out.println("Jumlah total buku: " + daftarBuku.size());
        System.out.println("Jumlah total pinjaman: " + totalPinjaman);

        tampilkanBukuPalingSeringDipinjam();
        tampilkanAnggotaPalingAktif();
        tampilkanKategoriPalingPopuler();
    }

    private void tampilkanBukuPalingSeringDipinjam() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada data buku.");
            return;
        }

        Book bukuTerpopuler = daftarBuku.get(0);

        for (Book buku : daftarBuku) {
            if (buku.getJumlahDipinjam() > bukuTerpopuler.getJumlahDipinjam()) {
                bukuTerpopuler = buku;
            }
        }

        System.out.println("Buku paling sering dipinjam: "
                + bukuTerpopuler.getJudul()
                + " (" + bukuTerpopuler.getJumlahDipinjam() + "x)");
    }

    private void tampilkanAnggotaPalingAktif() {
        if (daftarAnggota.isEmpty()) {
            System.out.println("Belum ada anggota terdaftar.");
            return;
        }

        Member anggotaTeraktif = null;
        int pinjamanTerbanyak = -1;

        for (Member anggota : daftarAnggota.values()) {
            int jumlah = anggota.getDaftarPinjaman().size();

            if (jumlah > pinjamanTerbanyak) {
                pinjamanTerbanyak = jumlah;
                anggotaTeraktif = anggota;
            }
        }

        if (anggotaTeraktif != null && pinjamanTerbanyak > 0) {
            System.out.println("Anggota paling aktif: "
                    + anggotaTeraktif.getNama()
                    + " dengan " + pinjamanTerbanyak + " buku sedang dipinjam.");
        } else {
            System.out.println("Belum ada anggota yang sedang meminjam buku.");
        }
    }

    private void tampilkanKategoriPalingPopuler() {
        if (daftarBuku.isEmpty()) {
            return;
        }

        HashMap<String, Integer> jumlahKategori = new HashMap<>();

        for (Book buku : daftarBuku) {
            String kategori = buku.getKategori().toLowerCase();
            jumlahKategori.put(kategori, jumlahKategori.getOrDefault(kategori, 0) + 1);
        }

        String kategoriTerpopuler = null;
        int jumlahTerbanyak = 0;

        for (String kategori : jumlahKategori.keySet()) {
            int jumlah = jumlahKategori.get(kategori);

            if (jumlah > jumlahTerbanyak) {
                jumlahTerbanyak = jumlah;
                kategoriTerpopuler = kategori;
            }
        }

        if (kategoriTerpopuler != null) {
            System.out.println("Kategori paling populer: "
                    + kategoriTerpopuler
                    + " (" + jumlahTerbanyak + " buku)");
        }
    }

    public void tampilkanJumlahBukuPerKategori() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku.");
            return;
        }

        HashMap<String, Integer> jumlahKategori = new HashMap<>();

        for (Book buku : daftarBuku) {
            String kategori = buku.getKategori().toLowerCase();
            jumlahKategori.put(kategori, jumlahKategori.getOrDefault(kategori, 0) + 1);
        }

        System.out.println("\n=== JUMLAH BUKU PER KATEGORI ===");

        for (String kategori : jumlahKategori.keySet()) {
            System.out.println(kategori + ": " + jumlahKategori.get(kategori) + " buku");
        }
    }

    public void tampilkanSemuaAnggota() {
        if (daftarAnggota.isEmpty()) {
            System.out.println("Belum ada anggota terdaftar.");
            return;
        }

        System.out.println("\n=== DAFTAR ANGGOTA ===");

        for (Member anggota : daftarAnggota.values()) {
            anggota.tampilkanInfo();
        }
    }
}