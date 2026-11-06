/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsession2;

/**
 *
 * @author User
 */
public class Kelinci extends Hewan implements Vaksinasi {
    private String warnaBulu;
    private static final double BIAYA_PER_HARI = 35000;

    public Kelinci(String nama, int umur, double berat, String warnaBulu) {
        super(nama, umur, berat);
        this.warnaBulu = warnaBulu;
    }

    public String getWarnaBulu() { return warnaBulu; }
    public void setWarnaBulu(String warnaBulu) { this.warnaBulu = warnaBulu; }

    @Override
    public String suaraKhas() {
        return "(mendengus pelan dan menghentakkan kaki)";
    }

    @Override
    public double hitungBiayaPerawatan(int hari) {
        double total = BIAYA_PER_HARI * hari;
        if (hari >= 5) {
            total = total * 0.9; // diskon 10% untuk rawat 5 hari atau lebih
        }
        return total;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[KELINCI]");
        super.tampilkanInfo();
        System.out.println("Warna bulu: " + warnaBulu);
    }

    @Override
    public void suntikVaksin() {
        setSudahDivaksin(true);
        System.out.println(getNama() + " (kelinci) diberi vaksin RHD.");
    }

    @Override
    public String getJadwalVaksin() {
        return "Ulangi setiap 12 bulan";
    }
}
