/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsession2;

/**
 *
 * @author User
 */
public class Kucing extends Hewan implements Vaksinasi {
    private boolean buluPanjang;
    private static final double BIAYA_PER_HARI = 50000;
    private static final double BIAYA_GROOMING = 30000;

    public Kucing(String nama, int umur, double berat, boolean buluPanjang) {
        super(nama, umur, berat);
        this.buluPanjang = buluPanjang;
    }

    public boolean isBuluPanjang() { return buluPanjang; }
    public void setBuluPanjang(boolean buluPanjang) { this.buluPanjang = buluPanjang; }

    @Override
    public String suaraKhas() {
        return "Meong~";
    }

    @Override
    public double hitungBiayaPerawatan(int hari) {
        double total = BIAYA_PER_HARI * hari;
        if (buluPanjang) {
            total += BIAYA_GROOMING; // grooming bulu panjang
        }
        return total;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[KUCING]");
        super.tampilkanInfo();
        System.out.println("Bulu panjang: " + (buluPanjang ? "Ya" : "Tidak"));
    }

    @Override
    public void suntikVaksin() {
        setSudahDivaksin(true);
        System.out.println(getNama() + " (kucing) diberi vaksin tricat (F3).");
    }

    @Override
    public String getJadwalVaksin() {
        return "Ulangi setiap 12 bulan";
    }
}
