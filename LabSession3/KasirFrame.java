package lab.kasir;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.*;

public class KasirFrame extends JFrame {

    private JTextField txtMenu, txtHarga, txtJumlah;
    private JTextArea areaStruk;
    private JLabel lblTotal;
    private JLabel lblStatus;
    private Pesanan pesanan = new Pesanan();

    // Format rupiah: 63000 -> 63.000
    private final DecimalFormat formatRupiah;

    public KasirFrame() {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols(Locale.US);
        simbol.setGroupingSeparator('.');
        formatRupiah = new DecimalFormat("#,##0", simbol);

        setTitle("Kasir Kantin UNS");
        setSize(400, 450);
        setLayout(null);

        // ---------- Komponen (Tahap 3) ----------
        JLabel lblJudul = new JLabel("Kasir Kantin UNS");
        lblJudul.setBounds(20, 10, 200, 25);
        add(lblJudul);

        JLabel lblMenu = new JLabel("Nama Menu :");
        lblMenu.setBounds(20, 45, 90, 25);
        add(lblMenu);
        txtMenu = new JTextField();
        txtMenu.setBounds(120, 45, 240, 25);
        add(txtMenu);

        JLabel lblHarga = new JLabel("Harga :");
        lblHarga.setBounds(20, 80, 90, 25);
        add(lblHarga);
        txtHarga = new JTextField();
        txtHarga.setBounds(120, 80, 240, 25);
        add(txtHarga);

        JLabel lblJumlah = new JLabel("Jumlah :");
        lblJumlah.setBounds(20, 115, 90, 25);
        add(lblJumlah);
        txtJumlah = new JTextField();
        txtJumlah.setBounds(120, 115, 240, 25);
        add(txtJumlah);

        JButton btnTambah = new JButton("Tambah");
        btnTambah.setBounds(40, 150, 140, 28);
        add(btnTambah);

        JButton btnHitung = new JButton("Hitung Total");
        btnHitung.setBounds(200, 150, 140, 28);
        add(btnHitung);

        areaStruk = new JTextArea();
        areaStruk.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaStruk);
        scroll.setBounds(20, 190, 340, 115);
        add(scroll);

        lblTotal = new JLabel("Total Bayar : Rp 0");
        lblTotal.setBounds(20, 312, 340, 25);
        add(lblTotal);

        // ---------- Komponen Tahap 4 ----------
        JButton btnCetak = new JButton("Cetak Struk");
        btnCetak.setBounds(20, 345, 130, 28);
        add(btnCetak);

        lblStatus = new JLabel(" ");
        lblStatus.setBounds(20, 378, 340, 25);
        add(lblStatus);

        // ---------- Tombol Tambah ----------
        btnTambah.addActionListener(e -> tambahItem());

        // ---------- Tombol Hitung Total ----------
        btnHitung.addActionListener(e ->
                lblTotal.setText("Total Bayar : Rp " + formatRupiah.format(pesanan.hitungTotal())));

        // ---------- Tombol Cetak Struk (Tahap 4) ----------
        btnCetak.addActionListener(e -> {
            btnCetak.setEnabled(false);
            lblStatus.setText("Mencetak struk...");

            new Thread(() -> {
                try {
                    Thread.sleep(2000); // penantian di thread baru, GUI tidak macet
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                // Perubahan komponen Swing dikembalikan ke Event Dispatch Thread
                SwingUtilities.invokeLater(() -> {
                    lblStatus.setText("Struk selesai");
                    btnCetak.setEnabled(true);
                });
            }).start();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void tambahItem() {
        String menu = txtMenu.getText().trim();
        String hargaStr = txtHarga.getText().trim();
        String jumlahStr = txtJumlah.getText().trim();

        // Validasi: field tidak boleh kosong
        if (menu.isEmpty() || hargaStr.isEmpty() || jumlahStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }

        // Validasi: harga dan jumlah harus angka
        try {
            double harga = Double.parseDouble(hargaStr);
            int jumlah = Integer.parseInt(jumlahStr);

            if (harga < 0 || jumlah <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Harga tidak boleh negatif dan jumlah harus lebih dari 0!");
                return;
            }

            pesanan.tambahItem(harga, jumlah);
            areaStruk.append(menu + " x" + jumlah + " @" + formatRupiah.format(harga)
                    + " = " + formatRupiah.format(harga * jumlah) + "\n");

            txtMenu.setText("");
            txtHarga.setText("");
            txtJumlah.setText("");
            txtMenu.requestFocus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga dan jumlah harus berupa angka!");
        }
    }
}
