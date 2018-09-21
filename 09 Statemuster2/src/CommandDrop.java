import java.util.Scanner;

public class CommandDrop implements ICommands {
	private Spieler e;

    /**
     * Erzeugt ein CommandDrop und initialisiert den Spieler.
     *
     * @param e
     */
	public CommandDrop(Spieler e) {
		this.e = e;
	}

	/**
	 * Führt den mitgelieferten Befehl aus
	 *
	 * @param befehl
	 */
	@Override
	public void execute(Befehl befehl) {
		String eingabe = befehl.gibZweitesWort();
        noItemSelected(eingabe);
        try {

			System.out.println("Das Item: " + this.e.legeGegenstandAb(eingabe).getName()
					+ " wurde auf den Boden des Raumes gelegt.");
		} catch (Exception e) {
			System.out.println("Diesen Gegenstand gibt es nur in einer anderen Matrix. Boom!");
		}
	}

	/**
	 * Prüft ob ein Item ausgewählt wurde
	 * @param eingabe, ist der Itemname
	 */
    private void noItemSelected(String eingabe) {
        if (eingabe == "") {
            System.out.println("Welchen Gegenstand mï¿½chten Sie abglegen?");
            for (Gegenstand g : e.getGegenstaende()) {
                System.out.println(g.getName() + "\n");
            }
        }
    }

    /**
     * Gibt die vollständige Beschreibung als String zurück
     */
    @Override
	public String getDescription() {
		return "Mit dem Befehl 'drop' und der Angabe eines Items, wird das bestimmte Item auf den Boden des Raumes gelegt.";
	}

    /**
     * Gibt den Namen des Befehles als String zurück
     */
	@Override
	public String getCommand() {
		return "drop";
	}

	/**
	 * Gibt die korrekte Syntax als String zurück
	 */
	@Override
	public String getSyntax() {
		return "Befehl 'drop': drop <Itemname>";
	}

}
