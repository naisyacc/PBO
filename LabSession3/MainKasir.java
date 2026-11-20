package lab.kasir;

import javax.swing.SwingUtilities;

public class MainKasir {

    public static void main(String[] args) {
        ujiTahap1();
        ujiTahap2();

        // Tahap 3 dan 4: tampilkan GUI di Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new KasirFrame());
    }

    // ---------- Tahap 1: uji class Pesanan ----------
    private static void ujiTahap1() {
        System.out.println("=== TAHAP 1: Uji Pesanan ===");
        Pesanan pesanan = new Pesanan();

        pesanan.tambahItem(15000, 2); // Nasi Goreng
        pesanan.tambahItem(5000, 3);  // Es Teh
        System.out.println("Subtotal : " + pesanan.getSubtotal());   // 45000.0
        System.out.println("Total    : " + pesanan.hitungTotal());   // 45000.0 (tanpa diskon)

        pesanan.tambahItem(25000, 1); // Ayam Bakar
        System.out.println("Subtotal : " + pesanan.getSubtotal());   // 70000.0
        System.out.println("Total    : " + pesanan.hitungTotal());   // 63000.0 (diskon 10%)

        pesanan.reset();
        System.out.println("Setelah reset, subtotal: " + pesanan.getSubtotal());
        System.out.println();
    }

    // ---------- Tahap 2: dua kasir sebagai thread ----------
    private static void ujiTahap2() {
        System.out.println("=== TAHAP 2: Dua Kasir ===");
        Kasir dina = new Kasir("Dina");
        Kasir eko = new Kasir("Eko");

        dina.start();
        eko.start();

        try {
            // join(): main menunggu kedua kasir selesai
            dina.join();
            eko.join();
        } catch (InterruptedException e) {
            System.out.println("Main terganggu saat menunggu kasir.");
        }

        System.out.println("Semua pelanggan selesai dilayani");
        System.out.println();
    }
}
