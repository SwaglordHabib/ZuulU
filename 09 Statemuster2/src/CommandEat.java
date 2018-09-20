import java.util.Scanner;

public class CommandEat implements ICommands {

	private Spieler e;

	private Scanner scanner;
	private static ICommands instance = new CommandEat();

	/**
	 * Prüfen ob eine Objekt der Klasse CommandEat bereits vorhanden ist. Ist
	 * dem nicht so, wird ein neues Objekt erstellt.
	 * 
	 * @return die Instanz des Zustandes
	 */
	public static ICommands getInstance() {
		if (instance == null) {
			instance = new CommandEat();
		}
		return instance;
	}

	/**
	 * Konstruktor der Klasse CommandEat
	 */
	private CommandEat() {

	}

	/**
	 * Initialisiert das erstellte Objekt der Klasse CommandDrop
	 * 
	 * @param e
	 *            ist der Spieler, welcher ein Objekt isst
	 */
	public void init(Spieler e) {
		this.e = e;
	}

	/**
	 * Wird aufgerufen, sofern festgestellt wurde, dass der Befehl 'eat'
	 * ausgeführt wird.
	 */
	@Override
	public void execute() {
		String eingabe;
		System.out.println("Welchen Gegenstand möchten Sie essen?");
		for (Gegenstand g : this.e.getGegenstaende()) {
			System.out.println(g.getName() + "\n");
		}
		eingabe = scanner.nextLine();
		for (Gegenstand g : this.e.getGegenstaende()) {
			if (g.getName().equals(eingabe)) {
				// TODO: Gegesnstände überarbeiten
			} else {
				System.out.println("Karl, dieses Item kann man nicht essen.");
			}
		}

	}

	/**
	 * Gibt die lange Beschreibung als String zurück.
	 */
	@Override
	public String getDescription() {
		return "Mit dem Befehl 'eat' und der Angabe eines Items, wird das bestimmte Item gegesssen.";
	}

	/**
	 * Gibt den einzugebenen Befehl als String zurück.
	 */
	@Override
	public String getCommand() {
		return "eat";
	}

	/**
	 * Gib bei falsch Eingabe, oder bei dem Befehl Hilfe, die korrekte Syntax
	 * als String zurück.
	 */
	@Override
	public String getSyntax() {
		return "Befehl 'eat': eat <Itemname>";
	}

}
