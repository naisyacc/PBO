package library.main;

import library.exception.BookAlreadyBorrowedException;
import library.exception.BookNotFoundException;
import library.exception.BorrowLimitExceededException;
import library.exception.MemberNotFoundException;
import library.service.LibraryService;

import java.util.Scanner;

public class MainApp {
    private static final Scanner input = new Scanner(System.in);
    private static final LibraryService perpustakaan = new LibraryService();

    public static void main(String[] args) {
        dataAwal();

        boolean berjalan = true;

        while (berjalan) {
            tampilkanMenu();
            System.out.print("Pilih menu: ");
            String pilihan = input.nextLine().trim();

            switch (pilihan) {
                case "1":
                    tambahBuku();
                    break;
                case "2":
                    perpustakaan.tampilkanSemuaBuku();
                    break;
                case "3":
                    cariBuku();
                    break;
                case "4":
                    pinjamBuku();
                    break;
                case "5":
                    kembalikanBuku();
                    break;
                case "6":
                    perpustakaan.tampilkanLaporan();
                    perpustakaan.tampilkanJumlahBukuPerKategori();
                    break;
                case "7":
                    perpustakaan.tampilkanSemuaAnggota();
                    break;
                case "8":
                    berjalan = false;
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        input.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n===== SISTEM PERPUSTAKAAN MINI =====");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Daftar Buku");
        System.out.println("3. Cari Buku");
        System.out.println("4. Pinjam Buku");
        System.out.println("5. Kembalikan Buku");
        System.out.println("6. Laporan Perpustakaan");
        System.out.println("7. Daftar Anggota");
        System.out.println("8. Keluar");
    }

    private static void dataAwal() {
        perpustakaan.tambahAnggota("M001", "Andi");
        perpustakaan.tambahAnggota("M002", "Budi");

        perpustakaan.tambahBuku("Belajar Java", "Budi Santoso", 2021, "Pemrograman");
        perpustakaan.tambahBuku("Struktur Data", "Rina Melati", 2020, "Informatika");
        perpustakaan.tambahBuku("Java OOP", "Ahmad Fauzi", 2022, "Pemrograman");
        perpustakaan.tambahBuku("Laskar Pelangi", "Andrea Hirata", 2005, "Novel");
    }

    private static void tambahBuku() {
        System.out.println("\n=== TAMBAH BUKU ===");
        System.out.print("Judul: ");
        String judul = input.nextLine();

        System.out.print("Penulis: ");
        String penulis = input.nextLine();

        int tahunTerbit = 0;
        boolean tahunValid = false;

        while (!tahunValid) {
            System.out.print("Tahun terbit: ");

            try {
                tahunTerbit = Integer.parseInt(input.nextLine().trim());
                tahunValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Tahun harus berupa angka.");
            }
        }

        System.out.print("Kategori: ");
        String kategori = input.nextLine();

        perpustakaan.tambahBuku(judul, penulis, tahunTerbit, kategori);
    }

    private static void cariBuku() {
        System.out.println("\n=== CARI BUKU ===");
        System.out.print("Masukkan judul atau kategori: ");
        String kataKunci = input.nextLine();

        perpustakaan.cariBuku(kataKunci);
    }

    private static void pinjamBuku() {
        System.out.println("\n=== PINJAM BUKU ===");
        System.out.print("ID anggota: ");
        String idAnggota = input.nextLine();

        System.out.print("Judul buku: ");
        String judulBuku = input.nextLine();

        try {
            perpustakaan.pinjamBuku(idAnggota, judulBuku);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (BookAlreadyBorrowedException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (BorrowLimitExceededException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (MemberNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void kembalikanBuku() {
        System.out.println("\n=== KEMBALIKAN BUKU ===");
        System.out.print("ID anggota: ");
        String idAnggota = input.nextLine();

        System.out.print("Judul buku: ");
        String judulBuku = input.nextLine();

        try {
            perpustakaan.kembalikanBuku(idAnggota, judulBuku);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (MemberNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}