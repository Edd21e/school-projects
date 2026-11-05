/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author edward
 */
public class Main {
       // Einstiegspunkt des Programms.
    public static void main(String[] args) {
        // Erstellt die Konten der Teilnehmer mit einem Anfangsguthaben von 0.
        Konto edward = new Konto("Edward");
        Konto andrii = new Konto("Andrii");
        Konto mark = new Konto("Mark");
        Konto mostik = new Konto("Rosty$l@Ve");
        Konto escobar = new Konto("Pablo");
        // Zahlt das Startguthaben auf die jeweiligen Konten ein.
        edward.einzahlen(250);
        andrii.einzahlen(99.99);
        mark.einzahlen(15.42);
        mostik.einzahlen(140);
        escobar.einzahlen(67.67);
        // Erstellt die Artikel mit Normalpreis, Rabattpreis, Mindestmenge und Bestand.
        Ware maus = new Ware("Maus", 20, 15, 5, 100);
        Ware tastatur = new Ware("Tastatur", 30, 25, 5, 100);
        Ware kopfhoerer = new Ware("Kopfhoerer", 15, 9, 10, 100);
        // Erstellt die gemeinsame Verwaltung für alle folgenden Bestellungen.
        Bestellungverwaltung verwaltung = new Bestellungverwaltung();
        // Versucht jede Bestellung anzunehmen und gibt das Ergebnis aus.
        bestellenMitMeldung(verwaltung, mostik, maus, 1);
        bestellenMitMeldung(verwaltung, mostik, tastatur, 1);
        bestellenMitMeldung(verwaltung, andrii, kopfhoerer, 6);
        bestellenMitMeldung(verwaltung, andrii, maus, 1);
        bestellenMitMeldung(verwaltung, edward, maus, 3);
        bestellenMitMeldung(verwaltung, escobar, maus, 1);
        bestellenMitMeldung(verwaltung, edward, tastatur, 3);
        bestellenMitMeldung(verwaltung, edward, kopfhoerer, 3);

        System.out.println();
        // Gibt die Gesamtmenge jedes Artikels aus den angenommenen Bestellungen aus
        System.out.println("Gesamtmengen:");
        System.out.println("Maeuse: " + verwaltung.getGesamtmenge(maus));
        System.out.println("Tastaturen: " + verwaltung.getGesamtmenge(tastatur));
        System.out.println("Kopfhoerer: " + verwaltung.getGesamtmenge(kopfhoerer));

        System.out.println();
        System.out.println("Abrechnung:");
        // Berechnet die endgültigen Preise und zahlt die Erstattungen aus.
        verwaltung.abschliessen();

        System.out.println();
        System.out.println("Lagerbestand:");
        // Liest den verbleibenden Bestand aus den jeweiligen Ware-Objekten.
        System.out.println("Maus: " + maus.getBestand() + " Stueck");
        System.out.println("Tastatur: " + tastatur.getBestand() + " Stueck");
        System.out.println("Kopfhoerer: " + kopfhoerer.getBestand() + " Stueck");

        System.out.println();
        System.out.println("Guthaben nach der Abrechnung:");
        System.out.println("Edward: " + edward.getKontostand() + " Euro");
        System.out.println("Pablo: " + escobar.getKontostand() + " Euro");
        System.out.println("Rosty$l@Ve: " + mostik.getKontostand() + " Euro");
        System.out.println("Andrii: " + andrii.getKontostand() + " Euro");
        System.out.println("Mark: " + mark.getKontostand() + " Euro");
    }
        // Verbindet die Bestellverarbeitung mit einer passenden Konsolenausgabe.
    public static void bestellenMitMeldung(
            Bestellungverwaltung verwaltung,
            Konto konto, Ware ware, int menge) {
        // Führt den Bestellversuch aus und speichert dessen Ergebnis.
        boolean erfolgreich = verwaltung.bestellen(konto, ware, menge);
        // Verhindert Methodenaufrufe auf fehlenden Konto- oder Ware-Objekten.
        if (konto == null || ware == null) {
            System.out.println(verwaltung.getFehlermeldung());
            return;
        }
        // Wählt die Ausgabe anhand des Ergebnisses des Bestellversuchs.
        if (erfolgreich) {
            System.out.println(
                    konto.getEigentuemer() + ": "
                    + menge + " x " + ware.getName()
                    + " - Bestellung angenommen."
            );
        } else {
            System.out.println(
                    konto.getEigentuemer() + ": "
                    + menge + " x " + ware.getName()
                    + " - " + verwaltung.getFehlermeldung()
            );
        }
    }

}
