
public class CommandQuit implements ICommands {

	private Spieler e;

	public CommandQuit(Spieler e) {
		this.e = e;
	}

	@Override
	public void execute(Befehl befehl) {
		if (befehl.hatZweitesWort()) {
			System.out.println("Was soll beendet werden?");
			Spiel.setBeendet(false);
		} else {
			Spiel.setBeendet(true); // Das Spiel soll beendet werden.
		}
	}

	@Override
	public String getDescription() {
		return "Mit dem Befehl 'quit', wird das Spiel beendet.";
	}

	@Override
	public String getCommand() {
		return "quit";
	}

	@Override
	public String getSyntax() {
		return "Befehl 'quit': quit";
	}

}
