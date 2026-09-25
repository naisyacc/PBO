/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prak2;

/**
 *
 * @author User
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input jumlah siswa
        System.out.print("Masukkan jumlah siswa: ");
        int jumlahSiswa = scanner.nextInt();
        scanner.nextLine(); // consume newline setelah nextInt()

        // Array untuk menyimpan objek Student
        Student[] students = new Student[jumlahSiswa];

        // Loop input data siswa
        for (int i = 0; i < jumlahSiswa; i++) {
            System.out.println("\nData siswa ke-" + (i + 1) + ":");
            System.out.print("Masukkan nama: ");
            String nama = scanner.nextLine();

            System.out.print("Masukkan nilai: ");
            double nilai = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            // Buat objek Student
            students[i] = new Student(nama, nilai);

            // Cek kelulusan
            students[i].checkPassed();

            // Tampilkan data siswa langsung setelah input
            System.out.println("Siswa: " + students[i].name +
                               " - Nilai: " + students[i].score +
                               " - " + students[i].getStatus());
        }

        // Hitung rata-rata dan jumlah lulus/tidak lulus
        double totalNilai = 0;
        int lulus = 0;
        int tidakLulus = 0;

        for (Student s : students) {
            totalNilai += s.score;

            if (s.passed) {
                lulus++;
            } else {
                tidakLulus++;
            }
        }

        double rataRata = totalNilai / jumlahSiswa;

        // Output statistik kelas
        System.out.printf("\nRata-rata nilai kelas: %.2f\n", rataRata);
        System.out.println("Jumlah siswa lulus: " + lulus);
        System.out.println("Jumlah siswa tidak lulus: " + tidakLulus);

        scanner.close();
    }
}
