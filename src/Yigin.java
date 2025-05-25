public class Yigin {
    private Cagri[] dizi;
    private int tepe;

    public Yigin(int kapasite) {
        dizi = new Cagri[kapasite];
        tepe = -1;
    }

    public boolean bosMu() {
        return tepe == -1;
    }

    public boolean doluMu() {
        return tepe == dizi.length - 1;
    }

    public void ekle(Cagri cagri) {
        if (doluMu()) {
            System.out.println("Yığın dolu!");
            return;
        }
        dizi[++tepe] = cagri;
    }

    public Cagri cikar() {
        if (bosMu()) {
            System.out.println("Yığın boş!");
            return null;
        }
        return dizi[tepe--];
    }
}
