/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author edward
 */
public class Bestellungverwaltung {
    // Speichert die angenommenen Bestellungen in einem Array.

    private Bestellung[] bestellungen;
    private int anzahl;
    private boolean abgeschlossen;
    private String fehlermeldung;
    // Erstellt eine neue Verwaltung fuer eine Sammelbestellung.

    public Bestellungverwaltung() {

        // Reserviert Platz für höchstens 100 Bestellungen.
        bestellungen = new Bestellung[100];
        // Zu Beginn ist keine Bestellung gespeichert.
        anzahl = 0;
        abgeschlossen = false;
    }
    // Prüft eine Bestellung und speichert sie bei Erfolg.

    public boolean bestellen(Konto konto, Ware ware, int menge) {
        // Entfernt die Fehlermeldung des vorherigen Bestellversuchs.
        fehlermeldung = "";
        // Nach dem Abschluss werden keine neuen Bestellungen angenommen.
        if (abgeschlossen) {
            fehlermeldung = "Sammelbestellung bereits abgeschlossen.";
            return false;
        }
        // Ein Konto und ein Artikel müssen vorhanden sein.
        if (konto == null || ware == null) {
            fehlermeldung = "Konto oder Ware fehlt.";
            return false;
        }
        // Es können nur positive Stückzahlen bestellt werden.

        if (menge <= 0) {
            fehlermeldung = "Die Menge muss groesser als 0 sein.";
            return false;
        }
        // Verhindert das Speichern außerhalb des Arrays.
        if (anzahl >= bestellungen.length) {
            fehlermeldung = "Kein Platz fuer weitere Bestellungen.";
            return false;
        }
         // Prüft, ob die gewünschte Menge auf Lager ist.
        if (menge > ware.getBestand()) {
            fehlermeldung = "Nicht genug Ware auf Lager. Verfuegbar: "
                    + ware.getBestand() + " Stueck.";
            return false;
        }
        // Berechnet die vorläufigen Kosten zum regulären Stückpreis
        double kosten = ware.getNormalpreis() * menge;
        // Versucht die Abbuchung und bricht bei einem Misserfolg ab
        if (!konto.abheben(kosten)) {
            fehlermeldung = "Nicht genug Guthaben.";
            return false;
        }
        // Erstellt einen Datensatz für die erfolgreich bezahlte Bestellung.
        Bestellung bestellung = new Bestellung(konto, ware, menge);
        // Speichert die Bestellung auf dem nächsten freien Platz
        bestellungen[anzahl] = bestellung;
        anzahl++;
         // Verringert den verfügbaren Lagerbestand sofort
        ware.bestandVerringern(menge);

        return true;
    }

    public String getFehlermeldung() {
        return fehlermeldung;
    }

    public int getGesamtmenge(Ware ware) {
        int summe = 0;

        for (int i = 0; i < anzahl; i++) {
            Bestellung bestellung = bestellungen[i];

            if (bestellung.getWare() == ware) {
                summe = summe + bestellung.getMenge();
            }
        }

        return summe;
    }
        // Berechnet die endgültigen Preise und erstattet die Differenzen.
    public void abschliessen() {
        // Verhindert eine erneute Abrechnung derselben Sammelbestellung.
        if (abgeschlossen) {
            System.out.println("Bereits abgeschlossen.");
            return;
        }
        // Rechnet jede gespeicherte Bestellung einzeln ab.
        for (int i = 0; i < anzahl; i++) {
            Bestellung bestellung = bestellungen[i];
        // Liest Artikel, Konto und Menge aus der Bestellung.
            Ware ware = bestellung.getWare();
            Konto konto = bestellung.getKonto();
            int menge = bestellung.getMenge();

            int gesamtmenge = getGesamtmenge(ware);

            double alterPreis = ware.getNormalpreis();
            // Ermittelt den Stückpreis anhand der gemeinsamen Bestellmenge.
            double neuerPreis = ware.getPreis(gesamtmenge);
            // Prüft einen Rabatt von 10 % ab 3 Stück in dieser Bestellung.
            if (menge >= 3) {
                double angebotspreis = alterPreis * 0.9;
                // Übernimmt den Angebotspreis nur, wenn er günstiger ist.
                if (angebotspreis < neuerPreis) {
                    neuerPreis = angebotspreis;
                }
            }
            // Prüft einen Rabatt von 25 % ab 5 Stück in dieser Bestellung.
            if (menge >= 5) {
                double angebotspreis = alterPreis * 0.75;

                if (angebotspreis < neuerPreis) {
                    neuerPreis = angebotspreis;
                }
            }
            // Berechnet die Erstattung für die gesamte Einzelbestellung.
            double rueckzahlung = (alterPreis - neuerPreis) * menge;

            konto.einzahlen(rueckzahlung);
            // Gibt Eigentümer, Menge und Artikel der Bestellung aus.
            System.out.println(
                    konto.getEigentuemer() + ": "
                    + menge + " x " + ware.getName()
            );
             // Gibt den Gesamtpreis dieser Bestellung mit 2 Nachkommastellen aus.
            System.out.printf(
                    "Endpreis: %.2f Euro%n",
                    neuerPreis * menge
            );
            // Gibt die Erstattung mit 2 Nachkommastellen aus.
            System.out.printf(
                    "Rueckzahlung: %.2f Euro%n",
                    rueckzahlung
            );

            System.out.println();
        }

        abgeschlossen = true;
    }
}
