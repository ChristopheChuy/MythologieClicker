/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import metier.etat.Etat;

/**
 *
 * @author Christophe CHUY , Nicolas LAS , Ugo SERRANO,Sylvain ESCASSUT
 */

public class Boss extends Monstre {
    /**
     * la duree de presence correspond a la duree pendant laquel le monstre doit être present dans la vue 
     */
    public final int DUREEPRESENCE = 30;

    /**
     * appel le constructeur de Monstre
    */
    public Boss(String nom, long Hp, long po, Etat etat, String urlImage) {
        super(Hp, po, etat, nom, urlImage);

    }

    
}
