/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.io.BufferedWriter;
import java.io.File;
import metier.Monde;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import metier.Unite;
/**
 *
 * @author syescassut
 */

public class Sauvegarde {
    
    private Monde monde;
    
    public Sauvegarde (Monde monde){
        setMonde(monde);
    }
    
    public Monde getMonde() {
        return monde;
    }

    public void setMonde(Monde monde) {
        this.monde = monde;
    }
    
    public void sauvegarder() throws Exception{
        File fichier = new File ("test.txt");
        PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (fichier)));
        pw.println ("Niveau "+monde.getNiveauActuel());
        pw.println ("Stage "+monde.getStageActuel());
        pw.println ("NbClic "+monde.getPlayer().getNbClic());
        pw.println ("Or "+monde.getPlayer().getPo());
        pw.println ("DegatsClic "+monde.getPlayer().getDegatAuClic());
        pw.println ("ChanceCritique "+monde.getPlayer().getPourcentageCrit());
        List<Unite> listUnite = monde.getPlayer().getUnites();
        for (Unite u : listUnite){
            pw.println("Unite");
            pw.println(u.getNom());
            pw.println(u.getImage());
            pw.println(u.getCoutAmelioration());
            pw.println(u.getDps());
            pw.println(u.getNiveau());
            pw.println(u.PRIX_PASSAGE_NIVEAU);
            pw.println(u.AMELIORATION_DPS);
        }
        pw.close();
        
    }
}
