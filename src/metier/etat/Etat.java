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
public interface Etat {
    public double getMultiplicateurDegat();  
    public void changerEtat(Monstre monstre);
   
}
