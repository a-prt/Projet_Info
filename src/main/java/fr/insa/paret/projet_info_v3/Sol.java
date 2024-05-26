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

public class Sol {
    private int idSol;
    private List<Coin> listeCoins;
    private List<Revetement> listeRevetements;
    
    public Sol(int idSol, List<Coin> listeCoins, List<Revetement> listeRevetements) {
        this.idSol = idSol;
        this.listeCoins = listeCoins;
        this.listeRevetements = listeRevetements;
    }
    
    public int getIdSol() {
    return idSol;
}
public void setIdSol(int idSol) {
    this.idSol = idSol;
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
        double minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        double minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
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
    public double montantRevetement() {
        double montantTotal = 0;
        double surfaceSol = surface();
        for (Revetement revetement : listeRevetements){
        montantTotal += revetement.getPrixUnitaire() * surfaceSol;
    }
        return montantTotal;
    }
    public void afficher() {
        System.out.println("ID du sol : " + idSol);
        System.out.println("Coordonnées des coins :" + listeCoins);
        System.out.println("Revêtement sur ce sol : " + listeRevetements);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sol").append(idSol).append(":");
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
    
}

