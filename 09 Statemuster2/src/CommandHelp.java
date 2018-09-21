
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
     *
     * @param befehl
     */
	@Override
	public void execute(Befehl befehl) {
		System.out.println("Sie haben sich verlaufen. Sie sind allein.");
		System.out.println("Sie irren auf dem Unigelände herum.");
		System.out.println();
		System.out.println("Ihnen stehen folgende Befehle zur Verfügung:");
		for (String b : Befehlswoerter.getAlleBefehle()) {
			System.out.println(b);
		}
	}

	@Override
	public String getDescription() {
		return "Mit dem Befehl 'help', wird dir eine Liste aller Befehle ausgegeben.";
	}

	@Override
	public String getCommand() {
		return "help";
	}

	@Override
	public String getSyntax() {
		return "Befehl 'help': help";
	}
}
