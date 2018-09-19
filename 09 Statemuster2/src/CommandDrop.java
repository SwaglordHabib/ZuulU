import java.util.Scanner;

public class CommandDrop implements ICommands {
	private Raum raum;
	private Spieler e;

	private Scanner scanner;
	private static ICommands instance = new CommandDrop();

	public static ICommands getInstance() {
		if (instance == null) {
			instance = new CommandDrop();
		}
		return instance;
	}

	private CommandDrop() {

	}

	public void init(Spieler e, Raum raum) {
		this.e = e;
		this.raum = raum;
	}

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

	@Override
	public String getDescription() {
		return "Mit dem Befehl 'drop' und der Angabe eines Items, wird das bestimmte Item auf den Boden des Raumes gelegt.";
	}

	@Override
	public String getCommand() {
		return "take";
	}

	@Override
	public String getSyntax() {
		return "Befehl 'take': take <Itemname>";
	}

}
