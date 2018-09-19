import java.util.Scanner;

public class CommandEat implements ICommands {

	private Spieler e;

	private Scanner scanner;
	private static ICommands instance = new CommandEat();

	public static ICommands getInstance() {
		if (instance == null) {
			instance = new CommandEat();
		}
		return instance;
	}

	private CommandEat() {

	}

	public void init(Spieler e) {
		this.e = e;
	}

	@Override
	public void execute() {
		String eingabe;
		System.out.println("Welchen Gegenstand möchten Sie essen?");
		for (Gegenstand g : this.e.getGegenstaende()) {
			System.out.println(g.getName() + "\n");
		}
		eingabe = scanner.nextLine();
		for (Gegenstand g : this.e.getGegenstaende()) {
			if (g.getName().equals(eingabe)) {
				// TODO: Gegesnstände überarbeiten
			} else {
				System.out.println("Karl, dieses Item kann man nicht essen.");
			}
		}

	}

	@Override
	public String getDescription() {
		return "Mit dem Befehl 'eat' und der Angabe eines Items, wird das bestimmte Item gegesssen.";
	}

	@Override
	public String getCommand() {
		return "eat";
	}

	@Override
	public String getSyntax() {
		return "Befehl 'eat': eat <Itemname>";
	}

}
