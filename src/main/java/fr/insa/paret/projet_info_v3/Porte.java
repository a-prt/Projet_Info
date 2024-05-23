/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.paret.projet_info_v3;

/**
 *
 * @author Utilisateur
 */
public class Porte extends Ouverture {
    
    Porte(int id, double x, double y)
    {
        this.setidOuverture(id);
        this.setdimX(x);
        this.setdimY(y);
    }
   //Méthode pour obtenir le type de l'ouverture (ici, porte)
   public String getTypeOuverture() {
       return "Porte";
   }
   //Méthode pour afficher les informations sur la porte
   @Override
   public double surface()
   {
       return (this.getdimX()*this.getdimY());
   }
}
