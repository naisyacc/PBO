package model;

/**
 * Custom exception yang dilempar ketika nilai kurang dari 0 atau lebih dari 100.
 * Turunan dari Exception (checked exception), sehingga wajib ditangani
 * dengan try-catch atau dideklarasikan dengan throws.
 */
public class NilaiInvalidException extends Exception {

    private final double nilai;

    public NilaiInvalidException(double nilai) {
        super("Nilai tidak valid: " + nilai + " (harus di antara 0 dan 100)");
        this.nilai = nilai;
    }

    public double getNilai() {
        return nilai;
    }
}
