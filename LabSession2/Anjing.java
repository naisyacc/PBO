/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsession2;

/**
 *
 * @author User
 */
public class Anjing extends Hewan implements Vaksinasi {
    private String ras;
    private static final double BIAYA_PER_HARI = 70000;
    private static final double TAMBAHAN_ANJING_BESAR = 20000;

    public Anjing(String nama, int umur, double berat, String ras) {
        super(nama, umur, berat);
        this.ras = ras;
    }

    public String getRas() { return ras; }
    public void setRas(String ras) { this.ras = ras; }

    @Override
    public String suaraKhas() {
        return "Guk guk!";
    }

    @Override
    public double hitungBiayaPerawatan(int hari) {
        double biayaPerHari = BIAYA_PER_HARI;
        if (getBerat() > 25) {
            biayaPerHari += TAMBAHAN_ANJING_BESAR; // kandang dan pakan lebih besar
        }
        return biayaPerHari * hari;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[ANJING]");
        super.tampilkanInfo();
        System.out.println("Ras      : " + ras);
    }

    @Override
    public void suntikVaksin() {
        setSudahDivaksin(true);
        System.out.println(getNama() + " (anjing) diberi vaksin rabies dan DHPP.");
    }

    @Override
    public String getJadwalVaksin() {
        return "Ulangi setiap 12 bulan";
    }
}
