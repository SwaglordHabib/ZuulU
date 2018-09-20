import java.util.Scanner;

public class CommandTake implements ICommands {

	private Spieler e;

	private Scanner scanner;

	public CommandTake(Spieler e) {
		this.e = e;
	}

	@Override
	public void execute(Befehl befehl) {
		String eingabe = befehl.gibZweitesWort();
		System.out.println("Welchen Gegenstand m�chten Sie aufnehmen?");
		for (Gegenstand g : this.e.aktuellerRaum.getAlleGegenstaende()) {
			System.out.println(g.getName() + "\n");
		}
		if (eingabe == "") {
			eingabe = scanner.nextLine();
		}
		try {
			for (Gegenstand g : this.e.aktuellerRaum.getAlleGegenstaende()) {
				if (g.getName().equals(eingabe)) {
					if (this.e.getTragkraft() + g.getGewicht() <= this.e.getTragkraft()) {
						this.e.getGegenstaende().add(g);
						this.e.getAktuellerRaum().getAlleGegenstaende().remove(g);
						System.out.println("Das Item:" + g.getName()
								+ " hast du vom Boden aufgenommen und in dein Inventar gelegt.");
					} else {
						System.out.println(
								"Du kannst dieses Item nicht aufnehmen, da deine Tragkraft nicht ausreicht. \n Lege ein Item ab oder lass es liegen.");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Diesen Gegenstand gibt es nur in einer anderen Matrix. Boom!");
		}
		scanner.close();
	}

	@Override
	public String getDescription() {
		return "Mit dem Befehl 'take' und der Angabe eines Items, wird dieses Item in das Spieler inventar aufgenommen.";
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
