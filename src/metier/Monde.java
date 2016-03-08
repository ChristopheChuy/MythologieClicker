/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import metier.etat.Etat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import metier.etat.Normal;

/**
 *
 * @author Christophe CHUY , Nicolas LAS , Ugo SERRANO,Sylvain ESCASSUT
 */
public class Monde implements Sujet {

    private final double MULTIPLICATEURPOINTDEVIEBOSS = 20.0;
    private Map<Integer, List<Monstre>> jeu;
    private int niveauActuel;
    private int stageActuel;
    private Joueur player;
    List<Observateur> listeObservateur;
    private final long ORDUMONSTRE = 5000;
    public final int NBNIVEAU = 10010;
    public final int NBSTAGE = 10;
    public final long POINTDEVIEDONNEAUMONSTRE = 500;
    public final double MULTIPLICATEURPOINTDEVIEMONSTRESUIVANTNIVEAU = 3.0;

    public Monde() {
        player = new Joueur();
        jeu = creationNiveaux();
        this.niveauActuel = 1;
        listeObservateur = new ArrayList<>();
    }
    public Monde (Joueur joueur, int niveau, int stage){
        player=joueur;
        this.niveauActuel = niveau;
        this.stageActuel = stage;
    }

    public Map<Integer, List<Monstre>> getJeu() {
        return jeu;
    }

    /**
     *
     * @return niveau actuel
     */
    public int getNiveauActuel() {
        return niveauActuel;
    }

    /**
     *
     * @return joueur
     */
    public Joueur getPlayer() {
        return player;
    }

    /**
     *
     * @return numero du stage actuel
     */
    public int getStageActuel() {
        return stageActuel;
    }

    /**
     * permet de modifié le stage actuel
     *
     * @param stageActuel
     */
    public void setStageActuel(int stageActuel) {
        this.stageActuel = stageActuel;
    }

    /**
     * permet de modifié le niveau actuel
     *
     * @param niveauActuel
     */
    public void setNiveauActuel(int niveauActuel) {
        this.niveauActuel = niveauActuel;
        notifier();
    }

    /**
     * permet de remettre a l'état d'origine tout les monstre dans le niveau
     * actuel
     */
    public void resetNiveau() {
        List<Monstre> montreList = jeu.get(niveauActuel);
        montreList.forEach(Monstre::resetHp);
    }

    /**
     * permet d'obtenir un monstre du stage cible dans le niveau actuel
     *
     * @param numeroStageCible numero stage ou nous voulons obtenir le monstre ,
     * si superieur a NBSTAGE changement de niveau fait
     * @return monstre au stagecible et au niveau actuelle
     */
    public Monstre changementMonstreActuel(int numeroStageCible) {
        Monstre monstreTheoriquementMort = jeu.get(niveauActuel).get(stageActuel);
        if (monstreTheoriquementMort.isMort()) {
            gainPoLorsqueMonstreMort(monstreTheoriquementMort);
        }
        if (numeroStageCible > NBSTAGE) {
            changerNiveau(++niveauActuel);
        } else {
            setStageActuel(numeroStageCible);
        }
        return jeu.get(niveauActuel).get(stageActuel);
    }

    /**
     * permet de demarrer le niveau cible a partir du stage 1
     *
     * @param niveauCible
     */
    public void changerNiveau(int niveauCible) {
        setNiveauActuel(niveauCible);
        setStageActuel(0);
    }

    public void gainPoLorsqueMonstreMort(Monstre monstreMort) {
        player.setPo(player.getPo() + monstreMort.getPo());
    }

    /**
     * méthode qui permet d'attacher un observateur à un sujet ainsi lorsque le
     * sujet change, l'observateur peut être informé du changement
     *
     * @param o observateur que l'on va attacher au monstre
     */
    public void attacher(Observateur o) {
        if (!listeObservateur.contains(o)) {
            listeObservateur.add(o);
        }
        notifier();
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
     * permet de creer la totalité des niveaux , des stages avec les monstres et
     * les boss
     *
     * @return Map ayant pour clef les niveaux et pour valeur une liste de
     * monstres
     */
    public Map<Integer, List<Monstre>> creationNiveaux() {
        Map<Integer, List<Monstre>> jeu = new HashMap<>();
        for (int numeroNiveau = 1; numeroNiveau <= NBNIVEAU; numeroNiveau++) {
            List<Monstre> listMonstre = new ArrayList<>();
            for (int numeroStage = 1; numeroStage <= NBSTAGE; numeroStage++) {
                Monstre monstreCreer = creationMonstreAleatoire(numeroNiveau);
                listMonstre.add(monstreCreer);
            }
            Boss bossDuNiveau = creationBossAleatoire(numeroNiveau);
            listMonstre.add((Boss) bossDuNiveau);
            jeu.put(numeroNiveau, listMonstre);
        }
        return jeu;
    }

    /**
     * permet de creer un monstre aleatoire
     *
     * @param numeroNiveau niveau dans lequel le monstre sera
     * @return monstre creer
     */
    public Monstre creationMonstreAleatoire(int numeroNiveau) {
        Random nombreAleatoire = new Random();
        int monstreAChoisir = nombreAleatoire.nextInt(2);
        Etat etat = new Normal();
        long pointDeVie = calculPointDeVie(numeroNiveau);
        switch (monstreAChoisir) {
            case 0:
                return new Monstre(pointDeVie, ORDUMONSTRE, etat, "bob", "image/8997_monster_hunter.jpg");
            default:
                return new Monstre(pointDeVie, ORDUMONSTRE, etat, "ugo", "image/IMGP0018.JPG");
        }
    }

    /**
     * permet de creer un boss aléatoirement
     *
     * @param numeroNiveau niveau dans lequel le boss sera
     * @return Boss creer
     */
    public Boss creationBossAleatoire(int numeroNiveau) {
        Random nombreAleatoire = new Random();
        Etat etat = new Normal();
        int monstreAChoisir = nombreAleatoire.nextInt(2);
        double pointDeVie = calculPointDeVie(numeroNiveau) * MULTIPLICATEURPOINTDEVIEBOSS;
        switch (monstreAChoisir) {
            case 0:
                return new Boss("bob", (long) pointDeVie, ORDUMONSTRE, etat, "image/8997_monster_hunter.jpg");
            default:
                return new Boss("ugo", (long) pointDeVie, ORDUMONSTRE, etat, "image/le-griffon-geant-de-lleida-51a8731e.jpg");
        }
    }

    /**
     * permet de calculer les points de vie du monstre en fonction de du niveau
     * passé en paramètre
     *
     * @param niveau ou le monstre sera
     * @return point de vie
     */
    public long calculPointDeVie(int niveau) {
        double pointDeVie = niveau * MULTIPLICATEURPOINTDEVIEMONSTRESUIVANTNIVEAU * POINTDEVIEDONNEAUMONSTRE;
        return (long) pointDeVie;
    }
}
