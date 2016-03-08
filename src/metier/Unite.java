/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

/**
 *
 * @author Christophe CHUY , Nicolas LAS , Ugo SERRANO,Sylvain ESCASSUT
 */
public class Unite {

    public final int PRIX_PASSAGE_NIVEAU;
    public final int AMELIORATION_DPS;
    public static final int NIVEAU_MAX = 10;

    private String nom;
    private String image;
    private long dps;
    private Joueur joueur;
    private int niveau;
    private int coutAmelioration;
    private final double donDegatAuClic = 0.25;

    public Unite(int PRIX_PASSAGE_NIVEAU, int AMELIORATION_DPS, String nom, String image, Joueur joueur) {
        this.PRIX_PASSAGE_NIVEAU = PRIX_PASSAGE_NIVEAU;
        this.AMELIORATION_DPS = AMELIORATION_DPS;
        this.nom = nom;
        this.image = image;
        this.joueur = joueur;
        this.coutAmelioration = PRIX_PASSAGE_NIVEAU;

    }
    
    public Unite(int PRIX_PASSAGE_NIVEAU, int AMELIORATION_DPS, String nom, int niveau, String image, long dps, Joueur joueur, int coutAmelioration){
        this.PRIX_PASSAGE_NIVEAU = PRIX_PASSAGE_NIVEAU;
        this.AMELIORATION_DPS = AMELIORATION_DPS;
        this.nom = nom;
        this.niveau = niveau;
        this.image = image;
        this.joueur = joueur;
        this.coutAmelioration = coutAmelioration;
        this.dps=dps;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getDps() {
        return dps;
    }

    public void setDps(long dps) {
        this.dps = dps;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getCoutAmelioration() {
        return coutAmelioration;
    }

    public void setCoutAmelioration(int coutAmelioration) {
        this.coutAmelioration = coutAmelioration;
    }

    /**
     * permet d'améliorer les statistiques de l'unité , augmente les cout
     * d'amélioration les degats par secondes et augmente les degats au clic du
     * joueur si l'unité est au niveau max
     */
    public void ameliorer() {
        if (getNiveau() < NIVEAU_MAX) {
            if (getJoueur().getPo() - getCoutAmelioration() >= 0) {
                getJoueur().setPo(getJoueur().getPo() - getCoutAmelioration());
                setCoutAmelioration(getCoutAmelioration() + PRIX_PASSAGE_NIVEAU);
                setDps(getDps() + AMELIORATION_DPS);
                getJoueur().setDps(getJoueur().getDps() + AMELIORATION_DPS);
                setNiveau(getNiveau() + 1);
                if (getNiveau() == NIVEAU_MAX) {
                    double donDegatAuClicCalcule = donDegatAuClic * dps;
                    getJoueur().setDegatAuClic(getJoueur().getDegatAuClic() + (long) donDegatAuClicCalcule);
                }
            }
        }
    }

}
