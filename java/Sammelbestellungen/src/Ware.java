/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author edward
 */
public class Ware {

    //Hier Speichern wir verschiedene variable (Bezeichung, regulaeren Preis,Rabbatprise, Menge
    //aktuelet verfuegbaren bestand
    private String bezeichnung;
    private double normalpreis;
    private double rabattpreis;
    private int mindestzahl;
    private int bestand;

    //Erstellt einen Artikel mit Preisen, Rabattgrenze und Lagerbestand 
    public Ware(String bezeichnung, double normalpreis,
            double rabattpreis, int mindestzahl, int bestand) {
        // Uebernimmt die uebergebenen Werte in die Felder dieses Artikels
        this.bezeichnung = bezeichnung;
        this.normalpreis = normalpreis;
        this.rabattpreis = rabattpreis;
        this.mindestzahl = mindestzahl;
        this.bestand = bestand;
    }

    public String getName() {
        return bezeichnung;
    }

    public double getNormalpreis() {
        return normalpreis;
    }

    public int getBestand() {
        return bestand;
    }

    public double getPreis(int menge) {
        // Die Menge muss positiv und im Lager verfuegbar sein
        if (menge >= mindestzahl) {
            return rabattpreis;
        }

        return normalpreis;
    }

    public void bestandVerringern(int menge) {
        if (menge > 0 && menge <= bestand) {
            // Entfernt die bestellte Menge aus dem verfügbaren Bestand.
            bestand = bestand - menge;
        }
    }
}
