/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.paret.projet_info_v3;

/**
 *
 * @author Utilisateur
 */
import java.util.List;

public class Plafond {
    private int idPlafond;
    private List<Coin> listeCoins;
    private List<Revetement> listeRevetements;
    
public Plafond(int idPlafond, List<Coin> listeCoins, List<Revetement> listeRevetements){
        this.idPlafond = idPlafond;
        this.listeCoins = listeCoins;
        this.listeRevetements = listeRevetements;
}
public int getIdPlafond() {
    return idPlafond;
}
public void setIdPlafond(int idPlafond) {
    this.idPlafond = idPlafond;
}
public List<Coin> getListeCoins() {
    return listeCoins;
}
public void setListeCoins(List<Coin> listeCoins) {
    this.listeCoins = listeCoins;
}
public List<Revetement> getListeRevetements() {
    return listeRevetements;
} 
public void setListeRevetements(List<Revetement> listeRevetements) {
    this.listeRevetements = listeRevetements;
}
public double surface() {
      double minX = Double.MAX_VALUE, maxX = Double.MIN_VALUE;
      double minY = Double.MAX_VALUE, maxY = Double.MIN_VALUE;
        for (Coin coin : listeCoins) {
            minX = Math.min(minX, coin.getCx());
            maxX = Math.max(maxX, coin.getCx());
            minY = Math.min(minY, coin.getCy());
            maxY = Math.max(maxY, coin.getCy());
        }
        double longueur = maxX - minX;
        double largeur = maxY - minY;
        return longueur * largeur;
}
public double montantRevetement(){
    double montantTotal = 0;
    double surfacePlafond = surface();
    for (Revetement revetement : listeRevetements){
        montantTotal += revetement.getPrixUnitaire() * surfacePlafond;
    }
    return montantTotal;
}
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Plafond").append(idPlafond).append(":");
        if (listeCoins!=null){
            for(Coin coin : listeCoins){
                sb.append(";").append(coin.getIdCoin());
            }
        }
        if (listeRevetements!=null){
            for(Revetement revetement : listeRevetements){
                sb.append(";").append(revetement.getIdRevetement());
            }
        }
        return sb.toString();
    }
    
    public void afficher()
    {
        System.out.println(" Plafond : id ="+this.getIdPlafond()+" surface ="+ surface()+ " Montant total du revetment = " + montantRevetement());
    }
}
