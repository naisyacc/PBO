package model;

/**
 * Kelas model yang merepresentasikan seorang mahasiswa.
 */
public class Mahasiswa {

    // Konstanta standar kelulusan
    public static final double FINAL_PASSING_GRADE = 70.0;

    // Atribut: String adalah reference type, double adalah primitive type
    private final String nama;
    private final String nim;
    private final double nilai;

    /**
     * Constructor: merapikan nama/NIM, memastikan NIM tidak kosong (assertion),
     * dan memvalidasi rentang nilai (custom exception).
     *
     * @throws NilaiInvalidException jika nilai < 0 atau nilai > 100
     */
    public Mahasiswa(String nama, String nim, double nilai) throws NilaiInvalidException {
        this.nama = nama.trim().toUpperCase();
        this.nim = nim.trim();

        // Assertion: hanya aktif jika program dijalankan dengan flag -ea
        assert !this.nim.isEmpty() : "NIM tidak boleh kosong!";

        if (nilai < 0 || nilai > 100) {
            throw new NilaiInvalidException(nilai);
        }
        this.nilai = nilai;
    }

    /**
     * Validasi NIM: tidak boleh kosong dan hanya boleh berisi karakter angka.
     */
    public static boolean isNimValid(String nim) {
        String bersih = nim.trim();
        if (bersih.isEmpty()) {
            return false;
        }
        for (char c : bersih.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Memetakan nilai angka ke grade huruf.
     */
    public String getGrade() {
        if (nilai >= 85) {
            return "A";
        } else if (nilai >= 75) {
            return "B";
        } else if (nilai >= FINAL_PASSING_GRADE) {
            return "C";
        } else {
            return "D";
        }
    }

    public boolean isLulus() {
        return nilai >= FINAL_PASSING_GRADE;
    }

    public String getStatus() {
        return isLulus() ? "Lulus" : "Tidak Lulus";
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public double getNilai() {
        return nilai;
    }
}
