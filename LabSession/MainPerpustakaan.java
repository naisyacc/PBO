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
import java.util.Scanner;

public class MainPerpustakaan {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // POIN 8: Collection
        ArrayList<Buku> daftarBuku = new ArrayList<>();

        daftarBuku.add(new Buku("Java Dasar", "Andi", 2020, true));
        daftarBuku.add(new Buku("Struktur Data", "Budi", 2021, true));
        daftarBuku.add(new Buku("Basis Data", "Citra", 2022, false));

        System.out.println("=== DAFTAR BUKU PERPUSTAKAAN ===");

        // POIN 5: Looping
        for (int i = 0; i < daftarBuku.size(); i++) {
            System.out.println((i + 1) + ". " + daftarBuku.get(i).getInfo());
        }

        System.out.print("\nPilih nomor buku yang dipinjam: ");

        try {
            int pilihanBuku = Integer.parseInt(input.nextLine());

            // Memeriksa apakah nomor buku valid
            if (pilihanBuku < 1 || pilihanBuku > daftarBuku.size()) {
                System.out.println("Nomor buku tidak tersedia.");
                input.close();
                return;
            }

            // Mengambil buku sesuai pilihan pengguna
            Buku bukuDipinjam = daftarBuku.get(pilihanBuku - 1);

            System.out.print("Masukkan jumlah hari keterlambatan: ");
            int hariTerlambat = Integer.parseInt(input.nextLine());

            // POIN 4: Kondisional
            if (hariTerlambat > 0) {
                double denda = bukuDipinjam.hitungDenda(hariTerlambat);

                System.out.println("\nBuku yang dipinjam: "
                        + bukuDipinjam.getJudul());
                System.out.println("Total denda: Rp" + denda);

            } else if (hariTerlambat == 0) {
                System.out.println("\nBuku yang dipinjam: "
                        + bukuDipinjam.getJudul());
                System.out.println("Tidak ada denda karena tidak terlambat.");

            } else {
                System.out.println("Jumlah hari tidak boleh negatif.");
            }

            // POIN 7: Character & String
            String judul = bukuDipinjam.getJudul();
            char hurufPertama = judul.charAt(0);

            System.out.println("\nJudul buku           : " + judul);
            System.out.println("Panjang judul        : " + judul.length());
            System.out.println("Huruf pertama judul  : " + hurufPertama);
            System.out.println("Tiga huruf pertama   : "
                    + judul.substring(0, 3));

        } catch (NumberFormatException e) {
            // POIN 6: Exception Handling
            System.out.println("Input tidak valid! Harus berupa angka.");
        }

        input.close();
    }
}