/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.sort;


import metier.Joueur;
import metier.Monstre;

/**
 *
 * @author Christophe CHUY , Nicolas LAS , Ugo SERRANO,Sylvain ESCASSUT
 */
public class TridentDePoseidon extends Sort{
    public static final int DEGATDUSORT = 50;
    public TridentDePoseidon(String nom,int cd,Joueur joueur) {
            super(nom,cd,joueur);
    }
    /**
     * ferra subir des la valeur de degatDuSort au monstre 
     * @param monstreActuel 
     */
    @Override
    public void effet(Monstre monstreActuel) {
        monstreActuel.subirDegat(DEGATDUSORT);
    }   
}
