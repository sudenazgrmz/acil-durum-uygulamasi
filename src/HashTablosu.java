public class HashTablosu {
    class Eleman {
        String anahtar;
        Cagri cagri;
        Eleman sonraki;

        public Eleman(String anahtar, Cagri cagri) {
            this.anahtar = anahtar;
            this.cagri = cagri;
        }
    }

    private Eleman[] tablo;
    private int boyut;

    public HashTablosu(int boyut) {
        this.boyut = boyut;
        tablo = new Eleman[boyut];
    }

    private int hash(String anahtar) {
        return Math.abs(anahtar.hashCode()) % boyut;
    }

    public void ekle(String anahtar, Cagri cagri) {
        int index = hash(anahtar);
        Eleman yeni = new Eleman(anahtar, cagri);
        yeni.sonraki = tablo[index];
        tablo[index] = yeni;
    }

    public void listele() {
        for (int i = 0; i < boyut; i++) {
            Eleman eleman = tablo[i];
            while (eleman != null) {
                System.out.print("Anahtar: " + eleman.anahtar + " - ");
                eleman.cagri.yazdir();
                eleman = eleman.sonraki;
            }
        }
    }
}
