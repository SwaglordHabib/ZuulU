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
	 * F�hrt den mitgelieferten Befehl aus
	 *
	 * @param befehl
	 */
	@Override
	public void execute(Befehl befehl) {
		String eingabe = befehl.gibZweitesWort();
		noItemSelected(eingabe);
		eatMuffin(eingabe);
	}

	/**
	 * Erh�ht, je nachdem ob der Muffin gegessen wurde, die Tragkraft des Spielers
	 * @param eingabe, ist der Name des Items
	 */
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

	/**
	 * Pr�ft ob ein Item ausgew�hlt wurde
	 * @param eingabe, ist der Itemname
	 */
	private void noItemSelected(String eingabe) {
		if (eingabe == ""){
			System.out.println("Welchen Gegenstand m�chten Sie essen?");
			for (Gegenstand g : this.spieler.getGegenstaende()) {
				System.out.println(g.getName() + "\n");
			}
		}
	}

    /**
     * Gibt die vollst�ndige Beschreibung als String zur�ck
     */
	@Override
	public String getDescription() {
		return "Mit dem Befehl 'eat' und der Angabe eines Items, wird das bestimmte Item gegesssen.";
	}

    /**
     * Gibt den Namen des Befehles als String zur�ck
     */
	@Override
	public String getCommand() {
		return "eat";
	}

	/**
	 * Gibt die korrekte Syntax als String zur�ck
	 */
	@Override
	public String getSyntax() {
		return "Befehl 'eat': eat <Itemname>";
	}

}
