
public class CommandHelp implements ICommands {

	private Spieler spieler;

	/**
	 * Erzeugt CommandHelp und initliaisiert den Spieler
	 *
	 * @param spieler
	 */
	public CommandHelp(Spieler spieler) {
		this.spieler = spieler;
	}

    /**
     * F�hrt den mitgelieferten Befehl aus
     * @param befehl
     */
	@Override
	public void execute(Befehl befehl) {
		System.out.println("Sie haben sich verlaufen. Sie sind allein.");
		System.out.println("Sie irren auf dem Unigel�nde herum.");
		System.out.println();
		System.out.println("Ihnen stehen folgende Befehle zur Verf�gung:");
		for (String b : Befehlswoerter.getAlleBefehle()) {
			System.out.println(b);
		}
	}

    /**
     * Gibt die vollst�ndige Beschreibung als String zur�ck
     */
	@Override
	public String getDescription() {
		return "Mit dem Befehl 'help', wird dir eine Liste aller Befehle ausgegeben.";
	}

    /**
     * Gibt den Namen des Befehles als String zur�ck
     */
	@Override
	public String getCommand() {
		return "help";
	}

	/**
	 * Gibt die korrekte Syntax als String zur�ck
	 */
	@Override
	public String getSyntax() {
		return "Befehl 'help': help";
	}
}
