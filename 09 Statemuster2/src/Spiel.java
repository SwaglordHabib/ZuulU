import java.util.ArrayList;
import java.util.HashMap;
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

	private static List<Raum> raumliste;
	private List<Monster> monsterliste;
	HashMap<String, ICommands> commandList = new HashMap<String, ICommands>();

	private static boolean beendet = false;

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
	 * Erzeuge alle Räume und verbinde ihre Ausgänge miteinander.
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
		draussen.gegenstandAblegen(new Gegenstand("Lichtschwert", "ein Lichtschwert(Rot) (Taschenlampe)", 9.5, false));
		draussen.gegenstandAblegen(new Gegenstand("Buch", "ein sehr altes Buch mit Ledereinband", 0.6, false));
		draussen.gegenstandAblegen(new Muffin("Muffin", "ein magischer Muffin", 0.1, 5.0));
		hoersaal.gegenstandAblegen(new Gegenstand("Schatz", "eine kleine Schatztruhe mit Münzen", 2.5, false));
		cafeteria.gegenstandAblegen(new Gegenstand("Pistole", "eine Pistole mit sechs Schuss", 0.5, false));
		labor.gegenstandAblegen(new Gegenstand("Nahrung", "ein Korb mit mehreren Fruchtsorten", 1.0, false));
		buero.gegenstandAblegen(new Gegenstand("Seil", "ein Seil der Länge 3m", 0.3, false));

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
		initCommands();
		// Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
		// und führen sie aus, bis das Spiel beendet wird.

		while (!this.beendet) {
			Befehl befehl = parser.liefereBefehl();
			verarbeiteBefehl(befehl);
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
     * Initialiesiert alle Befehle     *
     */
	private void initCommands() {
		commandList.put("go", new CommandGo(spieler));
		commandList.put("take", new CommandTake(spieler));
		commandList.put("drop", new CommandDrop(spieler));
		commandList.put("help", new CommandHelp(spieler));
		commandList.put("look", new CommandLook(spieler));
		commandList.put("quit", new CommandQuit(spieler));
		commandList.put("eat", new CommandEat(spieler));
		commandList.put("inventar", new CommandSearchInventar(spieler));
	}

	/**
	 * Verarbeite einen gegebenen Befehl (führe ihn aus).
	 * 
	 * @param befehl
	 *            Der zu verarbeitende Befehl.
	 */
	// TODO: Java Documentation
	private void verarbeiteBefehl(Befehl befehl) {

		if (commandList.containsKey(befehl.gibBefehlswort())) {
			commandList.get(befehl.gibBefehlswort()).execute(befehl);
		}
	}

	// Implementierung der Benutzerbefehle:

	private void rauminfoAusgeben() {
		System.out.println(spieler.getAktuellerRaum().getLangeBeschreibung());
		System.out.println();
	}


    /**
     * Erstellt eine "zufällige" Zahl, bis zu der Zahl die im Parameter übergeben wurde
     * @param max
     * @return
     */
	public static int getRandom(int max) {
		Random rnd = new Random();
		return rnd.nextInt(max);
	}

    /**
     * Erstellt die Monster und und speichert diese zufällig in einem Raum
     */
	private void spawnMobs() {
		Random rnd = new Random();
		for (int i = 0; i < raumliste.size() / 2; i++) {
			rnd.setSeed(System.currentTimeMillis());
			Monster m = new Monster(100, 5);
			raumliste.get(Spiel.getRandom(2)).addMonster(m);
		}
	}


	public static void setBeendet(boolean beendet) {
		Spiel.beendet = beendet;
	}

    public static List<Raum> getRaumliste() {
		return raumliste;
	}
}
