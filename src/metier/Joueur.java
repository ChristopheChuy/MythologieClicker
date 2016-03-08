/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import metier.sort.*;

/**
 *
 * @author Christophe CHUY , Nicolas LAS , Ugo SERRANO,Sylvain ESCASSUT
 */
public class Joueur {

    private long dps;
    private List<Unite> unites;
    private long degatAuClic = 50;
    private long po;
    private int nbClic;
    private int pourcentageCrit = 5;
    private final int MULTIPLICATEURDEGATCOUTCRITIQUE = 5;
    private List<Sort> sorts;

    public Joueur() {
        unites = new ArrayList<>();

        unites.add(new Unite(100, 20, "Achille", "image/Unite/achille.jpeg.jpg", this));

        unites.add(new Unite(500, 100, "Kratos", "image/Unite/kratos.jpeg", this));

        unites.add(new Unite(1000, 1000, "Pirithoos", "image/Unite/pirithoos.jpeg", this));

        unites.add(new Unite(2000, 2000, "Archer", "image/Unite/archer.jpeg", this));

        unites.add(new Unite(5000, 5000, "Hercule", "image/Unite/hercule.jpeg", this));

        unites.add(new Unite(15000, 15000, "Hoplite", "image/Unite/hoplite.jpeg", this));

        unites.add(new Unite(25000, 25000, "Bellerophon", "image/Unite/bellerophon.jpeg.jpg", this));

        unites.add(new Unite(50000, 50000, "Persee", "image/Unite/persee.jpeg", this));

        unites.add(new Unite(150000, 150000, "Thesee", "image/Unite/thesee.jpeg", this));

        unites.add(new Unite(150000, 5000000, "Ulysse", "image/Unite/ulysse.jpeg", this));
        sorts = new ArrayList<>();

        sorts.add(new FoudreDeZeus("Foudre De Zeus", 12, this));
        sorts.add(new TridentDePoseidon("Trident De Poseidon", 5, this));
        sorts.add(new RueeVersLor("Ruee vers l 'or", 42, this));
    }

    public Joueur(long degatAuClic, long or, int critique, int nbClic) {
        this.setDegatAuClic(degatAuClic);
        this.setPo(or);
        this.setPourcentageCrit(critique);
        this.setNbClic(nbClic);

        sorts.add(new FoudreDeZeus("Foudre De Zeus", 12, this));
        sorts.add(new TridentDePoseidon("Trident De Poseidon", 5, this));
        sorts.add(new RueeVersLor("Ruee vers l 'or", 42, this));
    }

    /**
     *
     * @return les degats par secondes fait par les unites sur le monstre
     */
    public long getDps() {
        return dps;
    }

    /**
     *
     * @param dps permet de modifier les dégats par secondes fait par les unites
     * sur le monstre
     */
    public void setDps(long dps) {
        this.dps = dps;
    }

    /**
     *
     * @return liste des unites
     */
    public List<Unite> getUnites() {
        return unites;
    }
    
    /**
     * 
     * @param unites représente la liste des unités
     */
    public void setUnites(List<Unite> unites){
        this.unites = unites;
    }
    
    /**
     *
     * @return nombre de degat effectuer lors d'un clic sur le monstre
     */
    public long getDegatAuClic() {
        return degatAuClic;
    }

    /**
     *
     * @param degatAuClic permet de modifier le nombre de dégat effectuer sur le
     * montre lors d'un clic
     */
    public void setDegatAuClic(long degatAuClic) {
        this.degatAuClic = degatAuClic;
    }

    /**
     *
     * @return nombre de pièce d'or que le joueur possède
     */
    public long getPo() {
        return po;
    }

    /**
     *
     * @param po permet de modifier le nombre de pièce que le joueur possèdera
     */
    public void setPo(long po) {
        this.po = po;
    }

    /**
     *
     * @return nombre de clic effectuer depuis le début du jeu
     */
    public int getNbClic() {
        return nbClic;
    }

    /**
     *
     * @param nbClic permet de modifer le nombre de clic du joueur
     */
    public void setNbClic(int nbClic) {
        this.nbClic = nbClic;
    }

    /**
     *
     * @return pourcentage de critique que le joueur peut effectué
     */
    public int getPourcentageCrit() {
        return pourcentageCrit;
    }

    /**
     * permet de modifier le pourcentage de critique du monstre
     *
     * @param pourcentageCrit
     */
    public void setPourcentageCrit(int pourcentageCrit) {
        this.pourcentageCrit = pourcentageCrit;
    }

    /**
     *
     * @return liste de sort
     */
    public List<Sort> getSorts() {
        return sorts;
    }

    /**
     * permet de taper le monstre gère les critiques suivant un nombre aléatoire
     *
     * @param monstreActuel monstre sur lequel on tape
     */
    public void taper(Monstre monstreActuel) {
        Random nombreAleatoire = new Random();
        nbClic++;
        if (pourcentageCrit >= nombreAleatoire.nextInt(100)) {
            monstreActuel.subirDegat(degatAuClic * MULTIPLICATEURDEGATCOUTCRITIQUE);
        } else {
            monstreActuel.subirDegat(degatAuClic);
        }
    }

}
