package metier.sort;

import metier.Joueur;
import metier.Monstre;

/**
 *
 * @author Christophe CHUY , Nicolas LAS , Ugo SERRANO,Sylvain ESCASSUT
 */
public class RueeVersLor extends Sort {

    public RueeVersLor(String nom, int cd, Joueur joueur) {
        super(nom, cd, joueur);
    }

    /**
     * cette fonction va multiplié par 2 le nombre de po que le monstre possède
     *
     * @param monstreActuel monstre sur lequelle nous allons effectué la
     * multiplication
     */
    @Override
    public void effet(Monstre monstreActuel) {
        monstreActuel.setPo(monstreActuel.getPo() * 2);
    }
}
