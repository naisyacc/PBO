package lab.kasir;

/**
 * Tahap 1: class logika untuk menghitung pesanan.
 */
public class Pesanan {

    private static final double BATAS_DISKON = 50000;
    private static final double PERSEN_DISKON = 0.10;

    private double subtotal;

    // Menambahkan harga x jumlah ke subtotal
    public void tambahItem(double harga, int jumlah) {
        subtotal += harga * jumlah;
    }

    public double getSubtotal() {
        return subtotal;
    }

    // Subtotal > 50.000 mendapat diskon 10 persen
    public double hitungTotal() {
        if (subtotal > BATAS_DISKON) {
            return subtotal - (subtotal * PERSEN_DISKON);
        }
        return subtotal;
    }

    public void reset() {
        subtotal = 0;
    }
}
