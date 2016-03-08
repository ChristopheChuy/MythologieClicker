/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.etat;

import metier.Monstre;

/**
 *
 * @author Christophe CHUY, Nicolas LAS, Ugo SERRANO, Sylvain ESCASSUT
 */
public class Normal implements Etat {

    public static final double MULTIPLICATEURDEGAT = 1.0;

    @Override
    public double getMultiplicateurDegat() {
        return MULTIPLICATEURDEGAT;
    }

    @Override
    public String toString() {
        return "Normal";
    }

    /**
     * permet de changer d'état suivant le pourcentage de point de vie du
     * monstre
     *
     * @param monstre
     */
    @Override
    public void changerEtat(Monstre monstre) {
        double pourcentageVie = (monstre.getHp() / (float) monstre.getNombreDePvMax()) * 100.0;
        if (pourcentageVie <= 50) {
            monstre.setEtat(new Rage());
        }
    }

}
