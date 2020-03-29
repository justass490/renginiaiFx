package renginiaiFx.Model;

public class Renginiai {
    private int id;
    private String pavadinimas;
    private String vardas;
    private String zmoniuSkaicius;
    private String adresas;
    private String priedai;

    public Renginiai() {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.vardas = vardas;
        this.zmoniuSkaicius = zmoniuSkaicius;
        this.adresas = adresas;
        this.priedai = priedai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getZmoniuSkaicius() {
        return zmoniuSkaicius;
    }

    public void setZmoniuSkaicius(String zmoniuSkaicius) {
        this.zmoniuSkaicius = zmoniuSkaicius;
    }

    public String getAdresas() {
        return adresas;
    }

    public void setAdresas(String adresas) {
        this.adresas = adresas;
    }

    public String getPriedai() {
        return priedai;
    }

    public void setPriedai(String priedai) {
        this.priedai = priedai;
    }
}
