import java.util.Scanner;

public class CommandDrop implements ICommands {
	private Raum raum;
	private Spieler e;

	private Scanner scanner;
	private static ICommands instance = new CommandDrop();

	/**
	 * Prüfen ob eine Objekt der Klasse CommandDrop bereits vorhanden ist. Ist
	 * dem nicht so, wird ein neues Objekt erstellt.
	 * 
	 * @return die Instanz des Zustandes
	 */
	public static ICommands getInstance() {
		if (instance == null) {
			instance = new CommandDrop();
		}
		return instance;
	}

	/**
	 * Konstruktor der Klasse CommandDrop
	 */
	private CommandDrop() {

	}

	/**
	 * Initialisiert das erstellte Objekt der Klasse CommandDrop
	 * 
	 * @param e
	 *            ist der Spieler, welcher ein Objekt ablegt
	 * @param raum
	 *            ist der Raum, in welchen das Objekt abgelegt wird
	 */
	public void init(Spieler e, Raum raum) {
		this.e = e;
		this.raum = raum;
	}

	/**
	 * Wird aufgerufen, sofern festgestellt wurde, dass der Befehl 'drop'
	 * ausgeführt wird.
	 */
	@Override
	public void execute() {
		String eingabe;
		System.out.println("Welchen Gegenstand möchten Sie abglegen?");
		for (Gegenstand g : e.getGegenstaende()) {
			System.out.println(g.getName() + "\n");
		}
		eingabe = scanner.nextLine();
		try {
			System.out.println(
					"Das Item: " + this.e.legeGegenstandAb(eingabe) + " wurde auf den Boden des Raumes gelegt.");
		} catch (Exception e) {
			System.out.println("Diesen Gegenstand gibt es nur in einer anderen Matrix. Boom!");
		}
	}

	/**
	 * Gibt die lange Beschreibung als String zurück.
	 */
	@Override
	public String getDescription() {
		return "Mit dem Befehl 'drop' und der Angabe eines Items, wird das bestimmte Item auf den Boden des Raumes gelegt.";
	}

	/**
	 * Gibt den einzugebenen Befehl als String zurück.
	 */
	@Override
	public String getCommand() {
		return "drop";
	}

	/**
	 * Gib bei falsch Eingabe, oder bei dem Befehl Hilfe, die korrekte Syntax
	 * als String zurück.
	 */
	@Override
	public String getSyntax() {
		return "Befehl 'drop': drop <Itemname>";
	}

}
