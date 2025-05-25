public class Ekip {
    private String isim;
    private String tur;
    private int konumId;

    public Ekip(String isim, String tur, int konumId) {
        this.isim = isim;
        this.tur = tur;
        this.konumId = konumId;
    }

    public int getKonumId() {
        return konumId;
    }

    public void yazdir(String[] konumlar) {
        System.out.println("Ekip: " + isim + " | Tür: " + tur + " | Konum: " + konumlar[konumId]);
    }
}
