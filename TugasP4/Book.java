package library.model;

public class Book {
    private String judul;
    private String penulis;
    private int tahunTerbit;
    private String kategori;
    private boolean tersedia;
    private int jumlahDipinjam;

    public Book(String judul, String penulis, int tahunTerbit, String kategori) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.kategori = kategori;
        this.tersedia = true;
        this.jumlahDipinjam = 0;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public String getKategori() {
        return kategori;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public int getJumlahDipinjam() {
        return jumlahDipinjam;
    }

    public void pinjam() {
        this.tersedia = false;
        this.jumlahDipinjam++;
    }

    public void kembalikan() {
        this.tersedia = true;
    }

    public void tampilkanInfo(int nomor) {
        String status = tersedia ? "Tersedia" : "Dipinjam";
        System.out.println(nomor + ". " + judul + " | " + penulis
                + " | " + tahunTerbit + " | " + kategori
                + " | Status: " + status
                + " | Dipinjam: " + jumlahDipinjam + "x");
    }
}