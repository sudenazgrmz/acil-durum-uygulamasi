public class Graf {
    private int[][] matris;
    private boolean[] ziyaret;
    private String[] konumIsimleri;
    int boyut;

    public Graf(int boyut, String[] isimler) {
        this.boyut = boyut;
        matris = new int[boyut][boyut];
        ziyaret = new boolean[boyut];
        konumIsimleri = isimler;
    }

    public void kenarEkle(int kaynak, int hedef) {
        matris[kaynak][hedef] = 1;
        matris[hedef][kaynak] = 1;
    }

    public void bfs(int baslangic) {
        boolean[] ziyaretEdildi = new boolean[boyut];
        int[] kuyruk = new int[boyut];
        int on = 0, arka = 0;

        ziyaretEdildi[baslangic] = true;
        kuyruk[arka++] = baslangic;

        while (on < arka) {
            int mevcut = kuyruk[on++];
            System.out.println("Ziyaret edilen: " + konumIsimleri[mevcut]);

            for (int i = 0; i < boyut; i++) {
                if (matris[mevcut][i] == 1 && !ziyaretEdildi[i]) {
                    ziyaretEdildi[i] = true;
                    kuyruk[arka++] = i;
                }
            }
        }
    }

    public int enKisaMesafe(int kaynak, int hedef) {
        boolean[] ziyaretEdildi = new boolean[boyut];
        int[] mesafe = new int[boyut];
        int[] kuyruk = new int[boyut];
        int on = 0, arka = 0;

        ziyaretEdildi[kaynak] = true;
        kuyruk[arka++] = kaynak;
        mesafe[kaynak] = 0;

        while (on < arka) {
            int mevcut = kuyruk[on++];
            for (int i = 0; i < boyut; i++) {
                if (matris[mevcut][i] == 1 && !ziyaretEdildi[i]) {
                    ziyaretEdildi[i] = true;
                    mesafe[i] = mesafe[mevcut] + 1;
                    kuyruk[arka++] = i;

                    if (i == hedef) return mesafe[i];
                }
            }
        }

        return Integer.MAX_VALUE; // Ulaşılamazsa
    }
}
