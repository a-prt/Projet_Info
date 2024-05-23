/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.paret.projet_info_v3;

/**
 *
 * @author Utilisateur
 */
public class Coin {
    
    // Déclaration des attributs de la classe Coin 

    private int idCoin;
    private double cx;
    private double cy;

    // Déclaration du constructeur de la classe Coin

    public Coin(int id, double x, double y)
    {
      this.idCoin=id;
      this.cx=x;
      this.cy=y;
    }
    
    // Setters pour modifier les valeurs des attributs
    
    public void setidCoin(int name)
    {
        this.idCoin=name;
    }
    public void setcx(double x)
    {
        this.cx=x;
    }
    public void setcy(double y)
    {
        this.cy=y;
    }
    
    // Méthodes getter pour récupérer les valeurs des attributs
    //si jamais l'implémentation interne de la classe Coin change à l'avenir (par exemple, si le nom de l'attribut idCoin est modifié en identifiantCoin), le reste du code qui utilise getIdCoin() ne sera pas affecté, car il continue à appeler la méthode getter appropriée
    
    public int getIdCoin() {
        return idCoin;
    }
    public double getCx() {
        return cx;
    }

     public double getCy() {
        return cy;
    }
    
//Déclaration de méthodes de la classe Coin

    public void afficher()
    {
        System.out.println(" Coin : id ="+this.getIdCoin()+" abscisse = "+this.getCx()+ " Ordonnée ="+this.getCy());
    }
    
    // Méthode pour fournir sous forme de chaîne de caractères un objet Coin cx;cy
    // pourquoi si compliqué
 @Override  
 public String toString() { 
        StringBuilder sb = new StringBuilder(); //permet de construire des chaînes de caractères de manière efficace
        sb.append("Coin");
        sb.append(";").append(getIdCoin());
        sb.append(";").append(getCx());
        sb.append(";").append(getCy());
        return sb.toString();
    }
    
    // Méthode qui définit la distance entre deux coins
    // je ne vois pas comment ca marche!!
    public double distanceCoin(Coin p) {
        double dx = this.cx - p.cx;
        double dy = this.cy - p.cy;
        return Math.sqrt(dx*dx+dy*dy);

    }
            
}
