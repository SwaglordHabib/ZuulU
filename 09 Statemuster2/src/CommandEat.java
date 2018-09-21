import java.util.Scanner;

public class CommandEat implements ICommands {

	private Spieler spieler;

	private Scanner scanner;

	/**
	 * Erzeugt ein CommandEat und initialisiert den Spieler
	 *
	 * @param e
	 */
	public CommandEat(Spieler e) {
		this.spieler = e;
	}

	/**
	 *
	 *
	 * @param befehl
	 */
	@Override
	public void execute(Befehl befehl) {
		String eingabe = befehl.gibZweitesWort();
		noItemSelected(eingabe);
		eatMuffin(eingabe);
	}

	private void eatMuffin(String eingabe) {
		for (Gegenstand g : this.spieler.getGegenstaende()) {
			if (g.getName().equals(eingabe) && g.isEssbar()) {
				Muffin m = (Muffin) g;
				this.spieler.setTragkraft(this.spieler.getTragkraft() + m.getErhoehungTragkraft());
				return;
			}
		}
		System.out.println("Kaaaaaaarl.");
	}

	private void noItemSelected(String eingabe) {
		if (eingabe == ""){
			System.out.println("Welchen Gegenstand möchten Sie essen?");
			for (Gegenstand g : this.spieler.getGegenstaende()) {
				System.out.println(g.getName() + "\n");
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
