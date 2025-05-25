public class BagliListe {
    class Dugum {
        String isim;
        Dugum sonraki;

        public Dugum(String isim) {
            this.isim = isim;
            this.sonraki = null;
        }
    }

    private Dugum bas;

    public void ekle(String isim) {
        Dugum yeni = new Dugum(isim);
        if (bas == null) {
            bas = yeni;
        } else {
            Dugum temp = bas;
            while (temp.sonraki != null) {
                temp = temp.sonraki;
            }
            temp.sonraki = yeni;
        }
    }

    public void yazdir() {
        Dugum temp = bas;
        while (temp != null) {
            System.out.println("Ekip: " + temp.isim);
            temp = temp.sonraki;
        }
    }
}
