/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.paret.projet_info_v3;

import java.util.List;

public class Mur {
    private int idMur;
    private Coin coinDebut;
    private Coin coinFin;
    private int nbrePortes;
    private int nbreFenetres;
    List<Revetement> listeRevetements;
    
    public Mur(int idMur, Coin coindebut, Coin coinFin, int porte, int fenetre,List<Revetement> listeRevetements ) {
        this.idMur= idMur;
        this.coinDebut = coindebut;
        this.coinFin = coinFin; 
        this.nbreFenetres=fenetre;
        this.nbrePortes=porte;
        this.listeRevetements=listeRevetements;
    }
    public int getIdMur() {
        return idMur;
    }
    public Coin getCoinDebut() {
        return coinDebut;
    }
    public Coin getCoinFin() {
        return coinFin;
    }
    public int getNbrePortes() {
        return nbrePortes;
    }
    public int getNbreFenetres(){
    return nbreFenetres;
    }
    public void setNbrePortes(int nbrePortes) {
        this.nbrePortes = nbrePortes;
    }
    public void setNbreFenetres(int nbreFenetres) {
        this.nbreFenetres= nbreFenetres;
    }
    public void setidMur (int nom){
        this.idMur=nom;
    }
    public void setcoindeb (Coin coin){
        this.coinDebut=coin;
    }
    public void setcoinfin (Coin coin){
        this.coinFin=coin;
    }
    public List<Revetement> getListeRevetements() {
        return listeRevetements;
    }
    public void setListeRevetements(List<Revetement> listeRevetements) {
        this.listeRevetements = listeRevetements;
    }
    public double surface() {
        double longueur = Math.sqrt(Math.pow(coinFin.getCx() - coinDebut.getCx(), 2)+ Math.pow(coinFin.getCy()- coinDebut.getCy(), 2));
        double hauteur = 2.5; //quelle hauteur pour le mur?
        double surfaceComplete = longueur*hauteur;
        double surfacePorte = nbrePortes*(0.90*2.10);
        double surfaceFenetre = nbreFenetres*(1.20*1.20);
        return surfaceComplete-surfacePorte-surfaceFenetre;
    }
        
    public double montantRevetement(){
        double montantTotal = 0;
        for(Revetement revetement : listeRevetements){
            montantTotal += revetement.getPrixUnitaire() * surface();
        }
        return montantTotal;
        }
    public void afficher(){
        System.out.println("Informations sur le mur:");
        System.out.println("ID du mur: "+ idMur);
        System.out.println("Coordonnées du coin de début: (" + coinDebut.getCx() + ", " + coinDebut.getCy() + ")");
        System.out.println("Coordonnées du coin de fin:(" + coinFin.getCx() + ", " + coinFin.getCy() + ")");
        System.out.println("Nombre de portes: " + nbrePortes);
        System.out.println("Nombre de fenêtres: " + nbreFenetres);
        System.out.println("Liste des revêtements: ");
        if (listeRevetements != null) {
            for (Revetement revetement : listeRevetements) {
                System.out.println("-" + revetement.getIdRevetement());
            }
        }
    }
    @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mur: ").append(idMur).append("; ");
        sb.append(coinDebut.getIdCoin()).append(";").append(coinFin.getIdCoin()).append(";");
        sb.append(nbrePortes).append(";").append(nbreFenetres).append(";");
        if (listeRevetements != null){
            for (Revetement revetement: listeRevetements){
                sb.append(revetement.getIdRevetement()).append(" ");
            }
        }
        return sb.toString();
    }
    
}