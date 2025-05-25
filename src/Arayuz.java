import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Arayuz extends JFrame {
    private JTextArea ekran;
    private JButton btnCagriEkle, btnCagriSil, btnKuyrukGoster, btnEkipEkle, btnCikis;

    private String[] konumlar = {"Kadıköy", "Üsküdar", "Beşiktaş", "Şişli", "Fatih"};
    private Graf konumGrafigi;
    private OncelikliKuyruk kuyruk;
    private ArrayList<Ekip> ekipler;

    public Arayuz() {
        setTitle("Acil Durum Yönetimi");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ekran = new JTextArea();
        ekran.setEditable(false);
        ekran.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(ekran);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        btnCagriEkle = new JButton("Yeni Çağrı Ekle");
        btnCagriSil = new JButton("Çağrıyı Sil");
        btnKuyrukGoster = new JButton("Kuyruğu Göster");
        btnEkipEkle = new JButton("Yeni Ekip Ekle");
        btnCikis = new JButton("Çıkış");

        // Renkler
        btnCagriEkle.setBackground(Color.GREEN);
        btnCagriSil.setBackground(Color.ORANGE);
        btnKuyrukGoster.setBackground(Color.YELLOW);
        btnEkipEkle.setBackground(Color.CYAN);
        btnCikis.setBackground(Color.RED);

        JPanel butonPanel = new JPanel();
        butonPanel.add(btnCagriEkle);
        butonPanel.add(btnCagriSil);
        butonPanel.add(btnKuyrukGoster);
        butonPanel.add(btnEkipEkle);
        butonPanel.add(btnCikis);

        add(scroll, BorderLayout.CENTER);
        add(butonPanel, BorderLayout.SOUTH);

        // Veri yapıları
        kuyruk = new OncelikliKuyruk(30);
        ekipler = new ArrayList<>();
        ekipler.add(new Ekip("112 Ambulans", "Ambulans", 0));
        ekipler.add(new Ekip("İtfaiye Ekibi", "İtfaiye", 2));
        ekipler.add(new Ekip("Polis Devriyesi", "Polis", 4));

        konumGrafigi = new Graf(konumlar.length, konumlar);
        konumGrafigi.kenarEkle(0, 1);
        konumGrafigi.kenarEkle(1, 2);
        konumGrafigi.kenarEkle(2, 3);
        konumGrafigi.kenarEkle(3, 4);

        // Buton olayları
        btnCagriEkle.addActionListener(e -> cagriEkle());
        btnCagriSil.addActionListener(e -> cagriSil());
        btnKuyrukGoster.addActionListener(e -> kuyrukGoster());
        btnEkipEkle.addActionListener(e -> ekipEkle());
        btnCikis.addActionListener(e -> System.exit(0));
    }

    private void cagriEkle() {
        try {
            String isim = JOptionPane.showInputDialog(this, "İsim:");
            String tur = JOptionPane.showInputDialog(this, "Acil Durum Türü:");
            int oncelik = Integer.parseInt(JOptionPane.showInputDialog(this, "Öncelik (1=acil, 5=normal):"));
            int konumId = Integer.parseInt(JOptionPane.showInputDialog(this, "Konum ID (0-4):"));

            Cagri cagri = new Cagri(isim, konumlar[konumId], tur, oncelik);
            kuyruk.ekle(cagri);

            // En yakın ekibi bul
            Ekip enYakin = ekipler.get(0);
            int minMesafe = konumGrafigi.enKisaMesafe(enYakin.getKonumId(), konumId);
            for (Ekip ekip : ekipler) {
                int mesafe = konumGrafigi.enKisaMesafe(ekip.getKonumId(), konumId);
                if (mesafe < minMesafe) {
                    minMesafe = mesafe;
                    enYakin = ekip;
                }
            }

            ekran.append("Çağrı eklendi → " + isim + " | Tür: " + tur + " | Konum: " + konumlar[konumId] + " | Öncelik: " + oncelik + "\n");
            ekran.append("→ Yönlendirilen ekip: ");
            enYakin.yazdir(konumlar);
            ekran.append("\n\n");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Geçersiz giriş!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cagriSil() {
        Cagri silinen = kuyruk.cikar();
        if (silinen != null) {
            ekran.append("Silinen çağrı → ");
            silinen.yazdir();
            ekran.append("\n\n");
        } else {
            ekran.append("Kuyruk boş! Silinecek çağrı yok.\n\n");
        }
    }

    private void kuyrukGoster() {
        ekran.append("=== Mevcut Çağrılar ===\n");
        kuyruk.yazdir(ekran);
        ekran.append("\n");
    }

    private void ekipEkle() {
        try {
            String isim = JOptionPane.showInputDialog(this, "Ekip Adı:");
            String tur = JOptionPane.showInputDialog(this, "Ekip Türü:");
            int konumId = Integer.parseInt(JOptionPane.showInputDialog(this, "Konum ID (0-4):"));

            ekipler.add(new Ekip(isim, tur, konumId));
            ekran.append("Yeni ekip eklendi: " + isim + " - " + tur + " - " + konumlar[konumId] + "\n\n");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hatalı giriş!", "Uyarı", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Arayuz().setVisible(true));
    }
}
