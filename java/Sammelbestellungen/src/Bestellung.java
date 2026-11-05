/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author edward
 */
public class Bestellung {
    // Verweist auf das Konto, über das die Bestellung bezahlt wird.

    private Konto konto;
    // Verweist auf den bestellten Artikel.
    private Ware ware;
    // Speichert die bestellte Stückzahl.
    private int menge;

    public Bestellung(Konto konto, Ware ware, int menge) {
        this.konto = konto;
        this.ware = ware;
        this.menge = menge;
    }

    public Konto getKonto() {
        return konto;
    }

    public Ware getWare() {
        return ware;
    }

    public int getMenge() {
        return menge;
    }
}
