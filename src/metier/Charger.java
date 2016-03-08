/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import metier.Joueur;
import metier.Monde;
import metier.Unite;

/**
 *
 * @author syescassut
 */
public class Charger {

    public Monde charger() throws Exception {
        int chanceCritique=5, nbClic=0, niveauMonde=1, stage=0;
        long or=0, degatsClic=50;
        Scanner scanner = new Scanner (new BufferedReader (new FileReader (new File ("test.txt"))));
        while (scanner.hasNext()){
            switch(scanner.next()){
                case "Niveau" :
                    niveauMonde = scanner.nextInt();
                    break;
                case "Stage" :
                    stage = scanner.nextInt();
                    break;
                case "NbClic" :
                    nbClic = scanner.nextInt();
                    break;
                case "Or" :
                    or = scanner.nextLong();
                    break;
                case "DegatsClic" :
                    degatsClic = scanner.nextInt();
                    break;
                case "ChanceCritique" :
                    chanceCritique = scanner.nextInt();
                    break;
            }
        }    
        Joueur joueur = new Joueur (degatsClic, or, chanceCritique, nbClic);
        List<Unite> unites = new ArrayList<>();
        while (scanner.hasNext()){
            switch(scanner.next()){
                case "Unite" :
                    String nom = scanner.next();
                    String image = scanner.next();
                    int coutAmelioration = scanner.nextInt();
                    long dps = scanner.nextLong();
                    int niveau = scanner.nextInt();
                    int prixPassageNiveau = scanner.nextInt();
                    int ameliorationDps = scanner.nextInt();
                    unites.add(new Unite(prixPassageNiveau, ameliorationDps, nom, 
                        niveau, image, dps, joueur, coutAmelioration));
                    break;
            }
        }
        joueur.setUnites(unites);
        Monde monde = new Monde (joueur, niveauMonde, stage);
        return monde;
    }
}
