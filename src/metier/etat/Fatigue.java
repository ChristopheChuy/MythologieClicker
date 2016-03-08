/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.etat;

import metier.Monstre;

/**
 *
 * @author Christophe CHUY , Nicolas LAS , Ugo SERRANO,Sylvain ESCASSUT
 */
public class Fatigue implements Etat {

    private static final double MULTIPLICATEURDEGAT = 1.2;

    @Override
    public double getMultiplicateurDegat() {
        return MULTIPLICATEURDEGAT;
    }

    @Override
    public String toString() {
        return "Fatigue";
    }

    /**
     * permet de changer d'état suivant le pourcentage de point de vie du
     * monstre
     *
     * @param monstre 
     */
    @Override
    public void changerEtat(Monstre monstre) {
    }

}
