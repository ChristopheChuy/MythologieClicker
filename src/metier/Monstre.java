/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import metier.etat.Etat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Christophe CHUY , Nicolas LAS , Ugo SERRANO,Sylvain ESCASSUT
 */
public class Monstre implements Sujet {

    private final long nombreDePvMax;
    private String urlImage;
    private String nom;
    private long hp;
    private long po;
    private List<Observateur> listeObservateur;
    private Etat etat;

    /**
     *
     * @param hp nombre de point de vie du monstre
     * @param po le nombre de pièce d'or que nous fait gagner le monstre en le
     * tuant
     * @param etat l'etat actuelle du monstre
     * @param nom nom du monstre
     * @param urlImage image liée au monstre
     */
    public Monstre(long hp, long po, Etat etat, String nom, String urlImage) {
        this.hp = hp;
        this.po = po;
        listeObservateur = new ArrayList<>();
        this.nombreDePvMax = hp;
        this.etat = etat;
        this.nom = nom;
        this.urlImage = urlImage;
    }

    /**
     *
     * @return l'url de l'image lié au monstre
     */
    public String getUrlImage() {
        return urlImage;
    }

    /**
     *
     * @return le nombre de point de vie actuelle du monstre
     */
    public long getHp() {
        return hp;
    }

    /**
     *
     * @return le nom du monstre
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param hp si hp est inferieur ou égal à 0, les points de vie du monstre
     * seront égal à 0, appel ensuite la méthode notifier
     */
    public void setHp(long hp) {
        if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = hp;
        }
        etat.changerEtat(this);
        notifier();
    }

    /**
     *
     * @return le nombre de pièce d'or que nous fait gagner le monstre en le
     * tuant
     */
    public long getPo() {
        return po;
    }

    /**
     *
     * @param po permet la modification du montant d'or gagner en tuant le
     * monstre
     */
    public void setPo(long po) {
        this.po = po;
    }

    /**
     *
     * @param etat modifie l'état du monstre
     */
    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    /**
     *
     * @return le nombre de point de vie lors de la creation du monstre
     */
    public long getNombreDePvMax() {
        return nombreDePvMax;
    }

    /**
     *
     * @return la liste d'observateur qui sont attaché au monstre
     */
    public List<Observateur> getListeObservateur() {
        return listeObservateur;
    }

    /**
     *
     * @return l'etat actuelle du monstre
     */
    public Etat getEtat() {
        return etat;
    }

    /**
     *
     * @param urlImage permet de modifier l'image actuelle liée au monstre
     */
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    /**
     *
     * @param nom permet de modifer le nom du montre
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * méthode qui permet d'attacher un observateur à un sujet ainsi lorsque le
     * sujet change, l'observateur peut être informé du changement
     *
     * @param o observateur que l'on va attacher au monstre
     */
    @Override
    public void attacher(Observateur o) {
        if (!listeObservateur.contains(o)) {
            listeObservateur.add(o);
        }
    }

    /**
     * méthode qui permet de dettacher un observateur à un sujet ainsi lorsque
     * le sujet change, l'observateur ne sera plus informé du changement
     *
     * @param o observateur que l'on va dettacher au monstre
     */
    @Override
    public void detacher(Observateur o) {
        if (listeObservateur.contains(o)) {
            listeObservateur.remove(o);
        }
    }

    /**
     * méthode qui permet de prévenir du changement fait au monstre a tout les
     * observateurs
     *
     */
    @Override
    public void notifier() {
        listeObservateur.forEach(Observateur::update);
    }

    /**
     * cette méthode fera subir des dégat au monstre qui varieront suivant
     * l'état actuelle du monstre
     *
     * @param nbDegatASubirTherorique est le nombre de dégat que le monstre
     * devrais recevoir théoriquement
     *
     */
    public void subirDegat(long nbDegatASubirTherorique) {
        double nbDegatASubirReel = nbDegatASubirTherorique * etat.getMultiplicateurDegat();
        setHp(getHp() - (long) nbDegatASubirReel);
    }
    /**
     * permet de remettre à l'état d'origine le monstre
     */
    public void resetHp(){
        setHp(nombreDePvMax);
    }
    /**
     * permet de savoir si le monstre est mort
     * @return vrai si le monstre est mort faux sinon
     */
    public boolean isMort(){
        return getHp()==0;
    }
}
