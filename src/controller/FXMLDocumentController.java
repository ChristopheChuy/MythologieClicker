/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import metier.Observateur;
import metier.Monde;
import metier.Monstre;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import metier.Boss;
import metier.Charger;
import metier.Sauvegarde;

import metier.Unite;
import metier.sort.Sort;
import vue.UniteCell;

/**
 *
 * @author Christophe CHUY , Nicolas LAS , Ugo SERRANO,Sylvain ESCASSUT
 */
public class FXMLDocumentController implements Initializable, Observateur {

    /**
     * chemin d'accès des differentes musiques
     */
    public String URLBruitageAuGainPO = "/son/coin_1.mp3";
    public String URLBruitageAuClic = "/son/click_4.mp3";
    public String URLMusiqueDeFond = "/son/10 - E.M.A.mp3";

    //------------------------------
    //intitule des boutons    
    //------------------------------
    public String intituleEtat = "état : ";
    public String intitulePointDeVie = "Point de vie ";
    public String intituleNiveau = "niveau ";
    public String intituleNomDuMonstre = "nom du monstre : ";
    public String intituleOr = "or :";
    public String intituleNbClic = "nb clic :";
    public String intituleDegatAuClic = "dégat au clic";
    public String intitulePourcentageCoutCritique = "chance de critique ";

    private List<Timer> listTimer = new ArrayList<>();

    public MediaPlayer bruitage;
    public MediaPlayer musicDeFond;
    public Monde monde = new Monde();
    public Monstre monstreActuelle;

    @FXML
    private Label niveau;
    @FXML
    private Label nomMonstre;
    @FXML
    private Label or;
    @FXML
    private Label nbclic;
    @FXML
    private Label degatAuClic;
    @FXML
    private Label etatMonstre;
    @FXML
    private Label pourcentageCrit;
    @FXML
    private Label timerBoss;
    @FXML
    private Label stage;
    @FXML
    private ProgressBar barreHp;
    @FXML
    private ImageView imageMonstre;
    @FXML
    private Button muteMusiqueButton;
    @FXML
    private Button quitter;
    @FXML
    private ListView<UniteCell> viewUnites;
    @FXML
    private VBox VBoxSort;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File fichier = new File("test.txt");
        if(fichier.exists()){
            Charger charger = new Charger();
            try{
                 monde = charger.charger();
            }catch(Exception e){
                System.err.println("Problème lors de la lecture du fichier : "+e.getMessage());
            } 
        }
        monstreActuelle = monde.changementMonstreActuel(monde.getStageActuel());
        monstreActuelle.attacher(this);
        misEnPlaceDesUnites();
        misEnPlaceSort();
        commencerDegatParSeconde();
        commencerMusiqueDeFond();
        update();
    }

    /**
     * cette méthode permet de commencer la musique de fond
     */
    private void commencerMusiqueDeFond() {
        try {
            musicDeFond = new MediaPlayer(prepareSon(URLMusiqueDeFond));
            musicDeFond.setCycleCount(Integer.MAX_VALUE);
            musicDeFond.play();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            quitterApplication(null);
        }
    }

    /**
     * permet de mettre en place les boutons pour les sorts
     */
    private void misEnPlaceSort() {
        List<Sort> listSort = monde.getPlayer().getSorts();
        for (Sort sort : listSort) {
            Button boutonSort = new Button(sort.getNom());
            boutonSort.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    lancementSort(sort, boutonSort);
                }
            });
            VBoxSort.getChildren().add(boutonSort);
        }
    }

    /**
     * permet la mis en place des éléments visuel liée au unité
     */
    private void misEnPlaceDesUnites() {
        List<UniteCell> list = new ArrayList<>();

        for (Unite unite : monde.getPlayer().getUnites()) {
            list.add(new UniteCell(unite));
        }
        ObservableList<UniteCell> listUniteObservable = FXCollections.observableList(list);
        viewUnites.setItems(listUniteObservable);
    }

    /**
     * commence les degats par secondes que le joueur effectuera toute les
     * secondes sur le monstre actuelle
     */
    private void commencerDegatParSeconde() {

        Timer timerDps = new Timer();
        listTimer.add(timerDps);
        timerDps.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        //1/10 de seconde pour 1/10 de degat , cela permet une meilleur fluidité dans l'affichage
                        monstreActuelle.subirDegat(monde.getPlayer().getDps() / 10);
                        changementLorsqueMonstreMort();
                    }
                });
            }
        }, 0, 100);
    }

//---------------------
//      Mis A Jour De l'interface
//---------------------
    @Override
    public void update() {
        niveau.setText(intituleNiveau + Integer.toString(monde.getNiveauActuel()));
        stage.setText(monde.getStageActuel() + "/" + monde.NBSTAGE);
        updateMonstre();
        updateStatJoueur();
    }

    /**
     * met a jour les differents éléments faisant reference au joueur
     */
    public void updateStatJoueur() {
        or.setText(intituleOr + Long.toString(monde.getPlayer().getPo()));
        nbclic.setText(intituleNbClic + Integer.toString(monde.getPlayer().getNbClic()));
        degatAuClic.setText(intituleDegatAuClic + Long.toString(monde.getPlayer().getDegatAuClic()));
        pourcentageCrit.setText(intitulePourcentageCoutCritique + Long.toString(monde.getPlayer().getPourcentageCrit()));
    }

    /**
     * met a jour les differents éléments faisant reference au monstre
     */
    public void updateMonstre() {
        etatMonstre.setText(intituleEtat + monstreActuelle.getEtat());
        double ratioPvMonstre = (float) monstreActuelle.getHp() / monstreActuelle.getNombreDePvMax();
        barreHp.setProgress(ratioPvMonstre);
        nomMonstre.setText(intituleNomDuMonstre + monstreActuelle.getNom());
        imageMonstre.setImage(new Image(monstreActuelle.getUrlImage()));
    }

    /**
     * cette méthode va preparer la musique en creant un media
     *
     * @param urlSon chemin d'accès de la musique
     * @return Media
     */
    public Media prepareSon(String urlSon) {
        URL music = getClass().getResource(urlSon);
        return new Media(music.toString());
    }

    /**
     * lorsque l'on tapera sur le monstre un son se lancera ,
     *
     * @param event
     */
    @FXML
    private void tapeMonstre(MouseEvent event) {
        try {
            bruitage = new MediaPlayer(prepareSon(URLBruitageAuClic));
            bruitage.play();
        } catch (Exception e) {
            quitterApplication(null);
        }
        monde.getPlayer().taper(monstreActuelle);
        changementLorsqueMonstreMort();
    }
//---------------------
//      SORT
//---------------------
    /**
     * méthode qui permet de lancé un sort
     * @param sort
     * @param buttonDuSort 
     */
    public void lancementSort(Sort sort, Button buttonDuSort) {
        sort.effet(monstreActuelle);
        lancementTempsDeRecuperation(sort, buttonDuSort);
    }
    /**
     * 
     * @param sort
     * @param buttonDuSort 
     */
    public void lancementTempsDeRecuperation(Sort sort, Button buttonDuSort) {
        buttonDuSort.setDisable(true);
        Timer timerSort = new Timer();
        listTimer.add(timerSort);
        long tempsLancementDuSort = System.currentTimeMillis() / 1000;
        timerSort.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    long chrono = System.currentTimeMillis() / 1000;
                    long temps = tempsLancementDuSort - chrono;
                    long tempsAvantReaparitionDuSort = sort.getCd() + temps;
                    if (tempsAvantReaparitionDuSort > 0) {
                        buttonDuSort.setText(Long.toString(tempsAvantReaparitionDuSort));
                    } else {
                        resetBoutonSort(sort.getNom(), buttonDuSort);
                        listTimer.remove(timerSort);
                        timerSort.cancel();
                    }
                });
            }
        }, 0, 1000);
    }

    /**
     * cette méthode permet de remettre a disponible un sort qui ne l'était pas
     *
     * @param nomDuBouton nom que le bouton va posseder
     * @param boutonSort bouton qui va être reset
     */
    public void resetBoutonSort(String nomDuBouton, Button boutonSort) {
        boutonSort.setText(nomDuBouton);
        boutonSort.setDisable(false);
    }

    /**
     * test si le monstre est mort (c'est à dire point de vie = 0) si c'est le
     * cas il demandera au monde de changé de monstre actuel
     */
    public void changementLorsqueMonstreMort() {
        if (monstreActuelle.isMort()) {
            try {
                bruitage = new MediaPlayer(prepareSon(URLBruitageAuGainPO));
                bruitage.play();
            } catch (Exception e) {
                System.exit(0);
            }
            monstreActuelle.detacher(this);
            monstreActuelle = monde.changementMonstreActuel(monde.getStageActuel() + 1);
            monstreActuelle.attacher(this);
            update();
            if (monstreActuelle instanceof Boss) {
                gestionTimerBoss((Boss) monstreActuelle);
            }
        }
    }

    /**
     * permet d'arreter la musique de fond
     *
     * @param event
     */
    @FXML
    private void muteMusique(ActionEvent event) {
        musicDeFond.setMute(!musicDeFond.isMute());
    }

    /**
     * gère le temps d'apparition lié au boss sachant que le boss ne peut être
     * present que durant sa DUREEPRESENCE
     *
     * @param monstreBoss
     */
    public void gestionTimerBoss(Boss monstreBoss) {
        Timer tempBoss = new Timer();
        listTimer.add(tempBoss);
        //temps en seconde du lancement du sort en seconde
        long tempsLancementDuSort = System.currentTimeMillis() / 1000;
        tempBoss.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    //temps actuelle en seconde
                    long chrono = System.currentTimeMillis() / 1000;

                    //difference entre le temps du lancement du sort et le temps 
                    long temps = tempsLancementDuSort - chrono;
                    long tempsAvantReapparitionDuSort = monstreBoss.DUREEPRESENCE + temps;
                    if (tempsAvantReapparitionDuSort > 0) {
                        timerBoss.setText(Long.toString(tempsAvantReapparitionDuSort));
                    } else {
                        changementBoss(false);
                        timerBoss.setText("");
                        listTimer.remove(tempBoss);
                        tempBoss.cancel();
                    }
                    if (monstreBoss.isMort()) {
                        changementBoss(true);
                        timerBoss.setText("");
                        listTimer.remove(tempBoss);
                        tempBoss.cancel();
                    }

                });
            }
        }, 0, 1000);
    }

    /**
     * permet de changer de de monstre sachant que l'on avait un boss
     * précédamment ,les règles sont : si nous avons réussi a tuer le boss avant
     * la duree presence alors changement de niveau sinon retour au niveau
     * actuelle actualisé
     *
     * @param monstreBoss
     */
    public void changementBoss(boolean monstreBoss) {
        monstreActuelle.detacher(this);
        int stageVise = 0;
        if (monstreBoss) {
            stageVise = monde.getStageActuel() + 1;
        } else {
            monde.resetNiveau();
        }
        monstreActuelle = monde.changementMonstreActuel(stageVise);
        monstreActuelle.attacher(this);
        update();
    }

    /**
     * permet de quitter l'application proprement en arretant notament tout les
     * timers
     *
     * @param event
     */
    @FXML
    private void quitterApplication(ActionEvent event) {
        listTimer.forEach(Timer::cancel);
        Platform.exit();
        Sauvegarde save = new Sauvegarde(monde);
        try{
            save.sauvegarder();
        }catch(Exception e){
            System.err.println("Problème lors de l'écriture du fichier : "+e.getMessage());
        }
    }
}
