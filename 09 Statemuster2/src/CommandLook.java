public class CommandLook implements ICommands {

	private Spieler e;

	public CommandLook(Spieler e) {
		this.e = e;
	}

	@Override
	public void execute(Befehl befehl) {
		System.out.println(this.e.getAktuellerRaum().getLangeBeschreibung());
	}

	@Override
	public String getDescription() {
		return "Mit dem Befehl 'look', schaut man sich um und entdeckt alle Items in dem Raum worin man sich befindet.";
	}

	@Override
	public String getCommand() {
		return "look";
	}

	@Override
	public String getSyntax() {
		return "Befehl 'look': look";
	}

}
