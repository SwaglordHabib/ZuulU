import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Dies ist die Hauptklasse der Anwendung "Die Welt von Zuul". "Die Welt von
 * Zuul" ist ein sehr einfaches, textbasiertes Adventure-Game. Ein Spieler kann
 * sich in einer Umgebung bewegen, mehr nicht. Das Spiel sollte auf jeden Fall
 * ausgebaut werden, damit es interessanter wird!
 * 
 * Zum Spielen muss eine Instanz dieser Klasse erzeugt werden und an ihr die
 * Methode "spielen" aufgerufen werden.
 * 
 * Diese Instanz erzeugt und initialisiert alle anderen Objekte der Anwendung:
 * Sie legt alle Räume und einen Parser an und startet das Spiel. Sie wertet
 * auch die Befehle aus, die der Parser liefert, und sorgt für ihre Ausführung.
 * 
 * @author Michael Kölling und David J. Barnes
 * @version 2008.03.30
 */

public class Spiel {
	private Spieler spieler;
	private Parser parser;
	private List<Raum> raumliste;
	private List<Monster> monsterliste;

	/**
	 * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
	 */
	public Spiel() {
		raeumeAnlegen();
		parser = new Parser();
	}

	/**
	 * Erzeugt ein Spielobjekt und führt an ihm die Methode spielen aus.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Spiel meinSpiel = new Spiel();
		meinSpiel.spielen();

	}

	/**
	 * Erzeuge alle Räume und verbindet ihre Ausgänge miteinander.
	 */
	private void raeumeAnlegen() {
		Raum draussen, hoersaal, cafeteria, labor, buero, teleporter;

		// die Räume erzeugen
		draussen = new Raum("vor dem Haupteingang der Universität", false);
		hoersaal = new Raum("in einem Vorlesungssaal", false);
		cafeteria = new Raum("in der Cafeteria der Uni", false);
		labor = new Raum("in einem Rechnerraum", false);
		buero = new Raum("im Verwaltungsbüro der Informatik", false);
		teleporter = new Raum("in einem Teleporter", true);

		// die Ausgänge initialisieren
		draussen.setAusgang("east", hoersaal);
		draussen.setAusgang("south", labor);
		draussen.setAusgang("west", cafeteria);
		hoersaal.setAusgang("west", draussen);
		cafeteria.setAusgang("east", draussen);
		labor.setAusgang("north", draussen);
		labor.setAusgang("east", buero);
		buero.setAusgang("west", labor);

		// Räume mit Gegenstände versehen
		draussen.gegenstandAblegen(new Gegenstand("Lichtschwert", "ein Lichtschwert(Rot) (Taschenlampe)", 9.5));
		draussen.gegenstandAblegen(new Gegenstand("Buch", "ein sehr altes Buch mit Ledereinband", 0.6));
		draussen.gegenstandAblegen(new Muffin("Muffin", "ein magischer Muffin", 0.1, 5.0));
		hoersaal.gegenstandAblegen(new Gegenstand("Schatz", "eine kleine Schatztruhe mit Münzen", 2.5));
		cafeteria.gegenstandAblegen(new Gegenstand("Pistole", "eine Pistole mit sechs Schuss", 0.5));
		labor.gegenstandAblegen(new Gegenstand("Nahrung", "ein Korb mit mehreren Fruchtsorten", 1.0));
		buero.gegenstandAblegen(new Gegenstand("Seil", "ein Seil der Länge 3m", 0.3));

		// Räumen in Liste speichern
		raumliste = new ArrayList<Raum>();
		raumliste.add(buero);
		raumliste.add(hoersaal);
		raumliste.add(draussen);
		raumliste.add(labor);
		raumliste.add(cafeteria);
		raumliste.add(teleporter);

		// Spawn Mobs in unterschiedlichen Räumen
		spawnMobs();

		// Initialisierung des Spielers
		spieler = new Spieler(100, 10);

		// das Spiel startet draussen
		spieler.setAktuellerRaum(draussen);
	}

	/**
	 * Die Hauptmethode zum Spielen. Läuft bis zum Ende des Spiels in einer
	 * Schleife.
	 */
	public void spielen() {
		willkommenstextAusgeben();

		// Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
		// und führen sie aus, bis das Spiel beendet wird.

		boolean beendet = false;
		while (!beendet) {
			Befehl befehl = parser.liefereBefehl();
			beendet = verarbeiteBefehl(befehl);
		}
		System.out.println("Danke für dieses Spiel. Auf Wiedersehen.");
	}

	/**
	 * Einen Begrüßungstext für den Spieler ausgeben.
	 */
	private void willkommenstextAusgeben() {
		System.out.println();
		System.out.println("Willkommen zu Zuul!");
		System.out.println("Zuul ist ein neues, unglaublich langweiliges Spiel.");
		System.out.println("Tippen sie 'help', wenn Sie Hilfe brauchen.");
		System.out.println();
		rauminfoAusgeben();

	}

	/**
	 * Verarbeite einen gegebenen Befehl (führe ihn aus).
	 * 
	 * @param befehl
	 *            Der zu verarbeitende Befehl.
	 * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
	 */
	private boolean verarbeiteBefehl(Befehl befehl) {
		// TODO: umbauen ohne if else -> Command-Pattern
		// TODO: Java Documentation
		boolean moechteBeenden = false;

		if (befehl.istUnbekannt()) {
			System.out.println("Ich weiß nicht, was Sie meinen...");
			return false;
		}

		String befehlswort = befehl.gibBefehlswort();
		if (befehlswort.equals("help"))
			hilfstextAusgeben();
		else if (befehlswort.equals("go"))
			goTo(befehl);
		else if (befehlswort.equals("quit")) {
			moechteBeenden = beenden(befehl);
		} else if (befehlswort.equals("look")) {
			rauminfoAusgeben();
		} else if (befehlswort.equals("take")) {
			nimmGegenstand(befehl);
			System.out.println(spieler.zeigeStatus());
			System.out.println(spieler.getAktuellerRaum().getLangeBeschreibung());
		} else if (befehlswort.equals("drop")) {
			legeGegenstandAb(befehl);
			System.out.println(spieler.zeigeStatus());
			System.out.println(spieler.getAktuellerRaum().getLangeBeschreibung());
		} else if (befehlswort.equals("eat")) {
			issMuffin(befehl);
			System.out.println(spieler.zeigeStatus());
			System.out.println(spieler.getAktuellerRaum().getLangeBeschreibung());
		}

		return moechteBeenden;
	}

	// Implementierung der Benutzerbefehle:

	/**
	 * Gib Hilfsinformationen aus. Hier geben wir eine etwas alberne und unklare
	 * Beschreibung aus, sowie eine Liste der Befehlswörter.
	 */
	private void hilfstextAusgeben() {
		System.out.println("Sie haben sich verlaufen. Sie sind allein.");
		System.out.println("Sie irren auf dem Unigelände herum.");
		System.out.println();
		System.out.println("Ihnen stehen folgende Befehle zur Verfügung:");
		System.out.println(parser.getAlleBefehle());
	}

	/**
	 * Versuche, den Raum zu wechseln. Wenn es einen Ausgang gibt, wechsele in den
	 * neuen Raum, ansonsten gib eine Fehlermeldung aus.
	 */
	private void goTo(Befehl befehl) {
		if (!befehl.hatZweitesWort()) {
			// Gibt es kein zweites Wort, wissen wir nicht, wohin...
			System.out.println("Wohin möchten Sie gehen?");
			return;
		}

		String richtung = befehl.gibZweitesWort();

		// Wir versuchen den Raum zu verlassen.
		Raum naechsterRaum = spieler.getAktuellerRaum().getAusgang(richtung);

		if (naechsterRaum == null) {
			System.out.println("Dort ist keine Tür!");
		} else {
			wechsleRaum(naechsterRaum);
		}
	}

	/**
	 * "quit" wurde eingegeben. Überprüfe den Rest des Befehls, ob das Spiel
	 * wirklich beendet werden soll.
	 * 
	 * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
	 */
	private boolean beenden(Befehl befehl) {
		if (befehl.hatZweitesWort()) {
			System.out.println("Was soll beendet werden?");
			return false;
		} else {
			return true; // Das Spiel soll beendet werden.
		}
	}

	/**
	 * Gibt alle Informationen des Raumes in der Konsole aus
	 */
	private void rauminfoAusgeben() {
		System.out.println(spieler.getAktuellerRaum().getLangeBeschreibung());
		System.out.println();
	}

	/**
	 * 
	 * @param befehl
	 */
	private void nimmGegenstand(Befehl befehl) {
		if (!befehl.hatZweitesWort()) {
			// Gibt es kein zweites Wort, wissen wir nicht, wohin...
			System.out.println("Welchen Gegenstand möchten Sie aufnehmen?");
			return;
		}

		String name = befehl.gibZweitesWort();
		// Wir versuchen den Gegenstand aufzunehmen.
		ArrayList<Gegenstand> gegenstaende = spieler.getAktuellerRaum().getAlleGegenstaende();
		for (Gegenstand g : gegenstaende) {
			if (g.getName().equals(name)) {
				if (spieler.gegenstandAufnehmen(g)) {
					spieler.getAktuellerRaum().entferneGegenstand(g);
					return;
				} else {
					System.out.println("Der Gegenstand ist zu schwer!");
					return;
				}
			}
		}
		System.out.println("Den Gegenstand gibt es hier nicht");
	}

	private void legeGegenstandAb(Befehl befehl) {
		if (!befehl.hatZweitesWort()) {
			// Gibt es kein zweites Wort, wissen wir nicht, wohin...
			System.out.println("Welchen Gegenstand möchten Sie ablegen?");
			return;
		}

		String name = befehl.gibZweitesWort();
		spieler.getAktuellerRaum().gegenstandAblegen(spieler.legeGegenstandAb(name));
	}

	private void issMuffin(Befehl befehl) {
		if (!befehl.hatZweitesWort()) {
			// Gibt es kein zweites Wort, wissen wir nicht, welcher Gegenstand gegessen
			// werden soll..
			System.out.println("Welchen Gegenstand möchten Sie essen?");
			return;
		}

		if (befehl.gibZweitesWort().equals("Muffin")) {
			// Wir versuchen den Muffin zu essen.
			ArrayList<Gegenstand> gegenstaende = spieler.getAktuellerRaum().getAlleGegenstaende();
			for (Gegenstand g : gegenstaende) {
				if (g.getName().equals("Muffin")) {
					Muffin m = (Muffin) g;
					spieler.setTragkraft(spieler.getTragkraft() + m.getErhoehungTragkraft());
					gegenstaende.remove(g);
					return;
				}
			}
		}
		System.out.println("Dieser Raum hat keinen Muffin");

	}

	/**
	 * Erstellt Monster, welche einen zufälligen Raum zugewiesen werden
	 */
	private void spawnMobs() {
		Random rnd = new Random();
		for (int i = 0; i < raumliste.size() / 2; i++) {
			rnd.setSeed(System.nanoTime());
			Monster m = new Monster(100, 5);
			raumliste.get(rnd.nextInt(raumliste.size() - 1)).addMonster(m);
		}
	}
}
