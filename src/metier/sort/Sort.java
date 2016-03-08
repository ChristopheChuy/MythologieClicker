package metier.sort;

import metier.Joueur;
import metier.Monstre;

/**
 *
 * @author Christophe CHUY , Nicolas LAS , Ugo SERRANO,Sylvain ESCASSUT
 */
public abstract class Sort {
    private int cd;
    private Joueur joueur;
    private String nom;
    public Sort(String nom,int cd, Joueur joueur) {
        this.cd=cd;
        this.joueur = joueur;
        this.nom=nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public abstract void effet(Monstre monstreActuel);
}
