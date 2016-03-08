/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;
/**
 *
 * @author Christophe CHUY , Nicolas LAS , Ugo SERRANO,Sylvain ESCASSUT
 */
public interface Sujet {
    public void attacher(Observateur o);
    public void detacher(Observateur o);
    public void notifier();
}
