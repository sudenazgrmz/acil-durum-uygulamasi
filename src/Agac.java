public class Agac {
    class Dugum {
        String bolge;
        Dugum sol, sag;

        public Dugum(String bolge) {
            this.bolge = bolge;
        }
    }

    private Dugum kok;

    public void ekle(String bolge) {
        kok = ekleRec(kok, bolge);
    }

    private Dugum ekleRec(Dugum kok, String bolge) {
        if (kok == null) return new Dugum(bolge);
        if (bolge.compareTo(kok.bolge) < 0)
            kok.sol = ekleRec(kok.sol, bolge);
        else
            kok.sag = ekleRec(kok.sag, bolge);
        return kok;
    }

    public void dolas() {
        dolasRec(kok);
    }

    private void dolasRec(Dugum kok) {
        if (kok != null) {
            dolasRec(kok.sol);
            System.out.println("Bölge: " + kok.bolge);
            dolasRec(kok.sag);
        }
    }
}
