import java.util.Scanner;

public class CommandGo implements ICommands {

	private Spieler spieler;
	private Raum raum;
	private Raum alterRaum;
	private Scanner scanner;

    /**
     * Erzeugt CommandGo und initialisiert den Spieler und den Raum aus dem der Spieler kam
     *
     * @param spieler
     */
	public CommandGo(Spieler spieler) {
		this.spieler = spieler;
		this.alterRaum = spieler.aktuellerRaum;
	}

    /**
     * Führt den mitgelieferten Befehl aus
     * @param befehl
     */
	@Override
	public void execute(Befehl befehl) {
		try {
			this.raum = spieler.getAktuellerRaum().getAusgang(befehl.gibZweitesWort());
			teleporter(befehl);
			if (raum.getMonsterliste().size() > 0) {
				fight(befehl);
			}
			spieler.setAktuellerRaum(raum);
			System.out.println(raumInfoausgeben(raum));
		} catch (Exception e) {
			System.out.println("Dieser Raum ist nicht vorhanden!\n");
		}
	}

	/**
	 * Teleportiert den Spieler, sofern der angegebene Raum eine Teleporter ist
	 * @param befehl
	 */
	private void teleporter(Befehl befehl) {
		if (raum.isTeleporter()) {
			spieler.setAktuellerRaum(Spiel.getRaumliste().get(Spiel.getRandom(Spiel.getRaumliste().size() - 1)));
			execute(befehl);
		}
	}

	/**
	 * Startet den Kampf, sofern Monster vorhanden sind
	 * @param befehl
	 */
	private void fight(Befehl befehl) {
		StringBuilder sb = new StringBuilder();
		String eingabe;
		scanner = new Scanner(System.in);
		boolean attacke = false;
		sb.append("Es sind " + raum.getMonsterliste().size() + "Monster in diesem Raum!\n");
		sb.append("Innerhalb eines Kampfes, kannst du 'attacke' oder 'fluechten' verwenden.\n");
		sb.append("Wenn du fluechtest, kannst du den Raum nicht betreten!\n");
		System.out.println(sb);

		sb.delete(0, sb.length());

		do {
			eingabe = scanner.nextLine().toLowerCase();

			if (eingabe == "attacke" || eingabe == "a") {

				for (int i = 0; i < raum.getMonsterliste().size(); i++)
					System.out.println("Monster " + i + " Leben: " + raum.getMonsterliste().get(i).hp);

				attacke = true;

				while (attacke) {
					System.out.println("Welches Monster mï¿½chtest du angreifen: ");
					eingabe = scanner.nextLine();
					try {
						spieler.doDamage(raum.getMonsterliste().get(Integer.parseInt(eingabe)));
						if (raum.getMonsterliste().get(Integer.parseInt(eingabe)).aktuellerZustand == Tot
								.getInstance()) {
							raum.getMonsterliste().remove(Integer.parseInt(eingabe));
							System.out.println("Das Monster wurde besiegt!!! Gurr!");
						}

						for (Monster m : raum.getMonsterliste()) {
							m.doDamage(spieler);

							if (spieler.aktuellerZustand == Tot.getInstance()) {
								System.out.println("Du wurdest besiegt!");
								System.exit(0);
							}
						}
						attacke = false;
					} catch (Exception e) {
						System.out.println("Dieses Monster gibt es nicht.");
					}
				}
			} else if (eingabe == "fluechten" || eingabe == "f") {
				this.raum = this.alterRaum;
				execute(befehl);
				System.out.println("Du bist geflohen, skrr!");
				break;
			} else {
				System.out.println("Die Eingabe war falsch!");
			}
		} while (raum.getMonsterliste().size() > 0);
		scanner.close();
	}

    /**
     * Gibt die vollständige Beschreibung als String zurück
     */
	@Override
	public String getDescription() {
		return "Mit dem Befehl 'go' und der Angabe einer Richtung, wird der Raum gewechselt.";
	}

	/**
	 * Gibt die korrekte Syntax als String zurück
	 */
	@Override
	public String getSyntax() {
		return "Befehl 'go': go <Richtung>";
	}

	/**
	 * Gibt die Beschreibung des angegebenen Raumes als String zurück
	 * @param r, ist der Raum, welcher ausgelesen werden soll
	 * @return
	 */
	private String raumInfoausgeben(Raum r) {
		return r.getLangeBeschreibung();
	}

    /**
     * Gibt den Namen des Befehles als String zurück
     */
	@Override
	public String getCommand() {
		return "go";
	}

}
