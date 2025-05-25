public class OncelikliKuyruk {
    private Cagri[] dizi;
    private int boyut;

    public OncelikliKuyruk(int kapasite) {
        dizi = new Cagri[kapasite];
        boyut = 0;
    }

    public void ekle(Cagri yeni) {
        if (boyut == dizi.length) {
            System.out.println("Kuyruk dolu!");
            return;
        }

        int i = boyut - 1;
        while (i >= 0 && dizi[i].getOncelik() > yeni.getOncelik()) {
            dizi[i + 1] = dizi[i];
            i--;
        }
        dizi[i + 1] = yeni;
        boyut++;
    }

    public Cagri cikar() {
        if (boyut == 0) {
            System.out.println("Kuyruk boş!");
            return null;
        }
        return dizi[--boyut];
    }

    // Konsola yazdırmak için
    public void yazdir() {
        for (int i = 0; i < boyut; i++) {
            dizi[i].yazdir();
        }
    }

    // GUI için: JTextArea'ya yazdır
    public void yazdir(javax.swing.JTextArea hedef) {
        if (boyut == 0) {
            hedef.append("Kuyruk boş.\n");
            return;
        }
        for (int i = 0; i < boyut; i++) {
            hedef.append((i + 1) + ". ");
            hedef.append(dizi[i].getIsim() + " | Tür: " + dizi[i].getTur() + " | Öncelik: " + dizi[i].getOncelik() + " | Konum: " + dizi[i].getKonum() + "\n");
        }
    }

    public boolean bosMu() {
        return boyut == 0;
    }
}
