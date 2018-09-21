
public class CommandHelp implements ICommands {

	private Spieler e;

	public CommandHelp(Spieler e) {
		this.e = e;
	}

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
