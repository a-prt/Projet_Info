/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.paret.projet_info_v3;

/**
 *
 * @author Utilisateur
 */
public abstract class Ouverture {
    
       // Déclaration des attributs de la classe Ouverture

        private int idOuverture;
        private double dimX;
        private double dimY;
    // Setters

    public void setidOuverture(int id)
    {
        this.idOuverture=id;
    }
    public void setdimX(double x)
    {
        this.dimX=x;
    }
    public void setdimY(double y)
    {
        this.dimY=y;
    }
    // getters

    public int getidOuverture()
    {
        return this.idOuverture;
    }
    public double getdimX() 
    {
        return this.dimX;
    }
    public double getdimY() 
    {
        return this.dimY;
    }
    abstract double surface();
    void afficher()
    {
        System.out.println("identifiant : "+idOuverture+" largeur : "+ dimX+" longueur : "+dimY);
    }
    @Override
    public String toString()
    {
        return "Ouverture (idOuverture= " + idOuverture + ", dimX= " + dimX + ", dimY= " + dimY + ')';
    }
}
