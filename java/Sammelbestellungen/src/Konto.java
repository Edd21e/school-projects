/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author edward
 */
public class Konto {
    //Speichert den Namen des Kontoinhabers.
   private String eigentuemer;
   //Speicher das aktuelle verfügbare Guthaben
    private double kontostand;
    //Erstellt ein Konto fuer den angegebenen Eigetuemer
    public Konto(String eigentuemer) {
        this.eigentuemer = eigentuemer;
        //Ein neues Konto begint mit 0
        this.kontostand = 0;
    }
    //Zahlt einen positiven Betrag auf dieses konto ein
    public void einzahlen(double betrag) {
        //Nur Betraege groesser als 0 werden berueckichtigt, dann Erhoeht den Kontostand 
        //um den Einzahlunsbetrag
        if (betrag > 0) {
            kontostand += betrag;
        }
    }
    //Versucht, Geld abzuheben, und meldet den Erfolg
    public boolean abheben(double betrag) {
        //Verhindert ungueltige Bertaege und eine Ueberziehung des Kontos,
        //dann Ziehlt den gueltigen Betrag vom Kontostand ab und Meldet,
        //dass die Abbuchung erfolgreich war
        if (betrag <= 0 || betrag > kontostand) {
            return false;
        }

        kontostand -= betrag;
        return true;
    }

    public double getKontostand() {
        return kontostand;
    }

    public String getEigentuemer() {
        return eigentuemer;
    }
    //Ueberweist einen Betrag von diesem Konto auf ein anderes Konto
    // if verzweigung Verhindert Ueberweisungen ohne Zielkonto oder auf dasselbe Konto
    public boolean ueberweisen(Konto zielKonto, double betrag) {
        if (zielKonto == null || zielKonto == this) {
            return false;
        }
        //Fuert die Einzahlung nur nach erfolgreicher Abbuchung aus
        if (abheben(betrag)) {
            //Schreibt den betrag dem Zielkonto gut
            zielKonto.einzahlen(betrag);
            return true;
        }

        return false;
    }
}
