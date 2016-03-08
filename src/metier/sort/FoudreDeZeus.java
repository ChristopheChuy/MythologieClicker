package metier.sort;

import metier.Joueur;
import metier.Monstre;

/**
 *
 * @author Christophe CHUY , Nicolas LAS , Ugo SERRANO,Sylvain ESCASSUT
 */
public class FoudreDeZeus extends Sort {

    public static final int DEGATDUSORT = 100;

    public FoudreDeZeus(String nom, int cd, Joueur joueur) {
        super(nom, cd, joueur);
    }

    /**
     * ferra subir des la valeur de degatDuSort au monstre
     *
     * @param monstreActuel
     */
    @Override
    public void effet(Monstre monstreActuel) {
        monstreActuel.subirDegat(DEGATDUSORT);
    }
}
