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

public class Couloir {
    private int idCouloir;
    private Coin coinDebut;
    private Coin coinFin;
    private List<Revetement> listeRevetements;

    public Couloir(int idCouloir, Coin coinDebut, Coin coinFin, List<Revetement> listeRevetements) {
        this.idCouloir = idCouloir;
        this.coinDebut = coinDebut;
        this.coinFin = coinFin;
        this.listeRevetements = listeRevetements;
    }

    public int getIdCouloir() {
        return idCouloir;
    }

    public void setIdCouloir(int idCouloir) {
        this.idCouloir = idCouloir;
    }

    public Coin getCoinDebut() {
        return coinDebut;
    }

    public void setCoinDebut(Coin coinDebut) {
        this.coinDebut = coinDebut;
    }

    public Coin getCoinFin() {
        return coinFin;
    }

    public void setCoinFin(Coin coinFin) {
        this.coinFin = coinFin;
    }

    public List<Revetement> getListeRevetements() {
        return listeRevetements;
    }

    public void setListeRevetements(List<Revetement> listeRevetements) {
        this.listeRevetements = listeRevetements;
    }

    public double surface() {
        double longueur = Math.abs(coinFin.getCx() - coinDebut.getCx());
        double largeur = Math.abs(coinFin.getCy() - coinDebut.getCy());
        return longueur * largeur;
    }

    public double montantRevetement() {
        double surfaceCouloir = surface();
        double montantTotal = 0;
        for (Revetement revetement : listeRevetements) {
            montantTotal += revetement.getPrixUnitaire() * surfaceCouloir;
        }
        return montantTotal;
    }
}