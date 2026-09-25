/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prak2;

/**
 *
 * @author User
 */
public class Student {
    // Variabel primitif & reference
    String name;    // reference type
    double score;   // primitive type
    boolean passed; // primitive type

    // Constructor
    public Student(String name, double score) {
        this.name = name;
        this.score = score;
        // passed akan diisi oleh method checkPassed()
    }

    // Method untuk mengecek kelulusan
    public void checkPassed() {
        if (this.score >= 70) {
            this.passed = true;
        } else {
            this.passed = false;
        }
    }

    // Method untuk mendapatkan status dalam bentuk String
    public String getStatus() {
        return this.passed ? "Lulus" : "Tidak Lulus";
    }
}
