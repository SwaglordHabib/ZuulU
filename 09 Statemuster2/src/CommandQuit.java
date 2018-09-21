
public class CommandQuit implements ICommands {

	private Spieler e;

	/**
	 * Erzeugt CommandQuit und inistialisiert den Spieler
	 *
	 * @param e
	 */
	public CommandQuit(Spieler e) {
		this.e = e;
	}

	/**
	 * Beendet das Spiel, wenn nur 'quit' eingegeben wird, sonst wird nochmal nachgefragt was beendet werden soll.
	 *
	 * @param befehl
	 */
	@Override
	public void execute(Befehl befehl) {
		if (befehl.hatZweitesWort()) {
			System.out.println("Was soll beendet werden?");
			Spiel.setBeendet(false);
		} else {
			Spiel.setBeendet(true); // Das Spiel soll beendet werden.
		}
	}

    /**
     * Gibt die vollst�ndige Beschreibung als String zur�ck
     */
	@Override
	public String getDescription() {
		return "Mit dem Befehl 'quit', wird das Spiel beendet.";
	}

    /**
     * Gibt den Namen des Befehles als String zur�ck
     */
	@Override
	public String getCommand() {
		return "quit";
	}

	/**
	 * Gibt die korrekte Syntax als String zur�ck
	 */
	@Override
	public String getSyntax() {
		return "Befehl 'quit': quit";
	}

}
