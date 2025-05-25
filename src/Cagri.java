public class Cagri {
    private String isim;
    private String konum;
    private String tur;
    private int oncelik;

    public Cagri(String isim, String konum, String tur, int oncelik) {
        this.isim = isim;
        this.konum = konum;
        this.tur = tur;
        this.oncelik = oncelik;
    }


    public String getIsim() {
        return isim;
    }

    public String getKonum() {
        return konum;
    }

    public String getTur() {
        return tur;
    }

    public int getOncelik() {
        return oncelik;
    }

    public void yazdir() {
        System.out.println(isim + " | Tür: " + tur + " | Öncelik: " + oncelik + " | Konum: " + konum);
    }
}
