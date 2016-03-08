package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author christophe
 */
public class UniteCell extends VBox {

    String intituleNiveauUnite = "Niveau :";
    String intituleCoutAmelioration = "Cout amelioration :";
    String intituleDps = "Dps :";
    String intituleBoutonAmelioration = "Améliorer";

    HBox premiereLigne = new HBox();
    HBox deuxiemeLigne = new HBox();

    Label degatParSeconde = new Label();
    Label labelNomUnite = new Label();
    Label niveauUnite = new Label();
    Label coutAmelioration = new Label();
    Button buttonAmeliorer = new Button();
    ImageView imageUnite = new ImageView();

    metier.Unite unite;

    public UniteCell(metier.Unite unite) {
        super();
        this.unite = unite;
        initialisationValeur();
        initialisationComportement();
        ajoutComposantDansConteneur();
    }
    /**
     * cette fonction permet d'ajouter des elements dans les conteneurs
     */
    public void ajoutComposantDansConteneur() {
        premiereLigne.getChildren().addAll(imageUnite, labelNomUnite, degatParSeconde, coutAmelioration);
        deuxiemeLigne.getChildren().addAll(niveauUnite, buttonAmeliorer);
        getChildren().addAll(premiereLigne, deuxiemeLigne);
    }
    /**
     * cette fonction permet d'initialiser les valeurs des différents éléments graphiques
     */
    public void initialisationValeur() {
        imageUnite.setFitHeight(100);
        imageUnite.setFitWidth(100);
        labelNomUnite.setText(unite.getNom());
        imageUnite.setImage(new Image(unite.getImage()));
        buttonAmeliorer.setText(intituleBoutonAmelioration);
        update();
    }
    /**
     * cette méthode permet d'initialiser le comportement des boutons
     */
    public void initialisationComportement() {
        buttonAmeliorer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                unite.ameliorer();
                update();
            }
        });
    }
    /**
     * cette méthode actualise les differents éléments visuels dynamique
     */
    public void update() {
        if (unite.getNiveau() != unite.NIVEAU_MAX) {
            coutAmelioration.setText(intituleCoutAmelioration + Integer.toString(unite.getCoutAmelioration()));
        }else{
            coutAmelioration.setText("");
            deuxiemeLigne.getChildren().remove(buttonAmeliorer);
            buttonAmeliorer=null;
        }
        degatParSeconde.setText(intituleDps + Long.toString(unite.getDps()));
        niveauUnite.setText(intituleNiveauUnite + Integer.toString(unite.getNiveau()));
    }
}
