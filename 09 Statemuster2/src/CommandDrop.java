import java.util.Scanner;

public class CommandDrop implements ICommands {
	private Spieler e;

	private Scanner scanner;

	public CommandDrop(Spieler e) {
		this.e = e;
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

			System.out.println("Das Item: " + this.e.legeGegenstandAb(eingabe).getName()
					+ " wurde auf den Boden des Raumes gelegt.");
		} catch (Exception e) {
			System.out.println("Diesen Gegenstand gibt es nur in einer anderen Matrix. Boom!");
		}
		scanner.close();
	}

	@Override
	public String getDescription() {
		return "Mit dem Befehl 'drop' und der Angabe eines Items, wird das bestimmte Item auf den Boden des Raumes gelegt.";
	}

	@Override
	public String getCommand() {
		return "drop";
	}

	@Override
	public String getSyntax() {
		return "Befehl 'drop': drop <Itemname>";
	}

}
