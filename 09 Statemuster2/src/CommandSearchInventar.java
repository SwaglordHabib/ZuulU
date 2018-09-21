public class CommandSearchInventar implements ICommands{
    private Spieler spieler;

    /**
     * Erzeugt ein CommandSearchInventar und initialisiert den Spieler
     *
     * @param spieler
     */
    public CommandSearchInventar(Spieler spieler){
        this.spieler = spieler;
    }

    /**
     * Gibt alle GegenstÃ¤nde die im Inventar liegen auf der Konsole aus.
     *
     * @param befehl
     */
    @Override
    public void execute(Befehl befehl) {
        for (Gegenstand g:spieler.getGegenstaende()) {
            System.out.println(g.getName());
        }
    }
    
    /**
     * Gibt die vollständige Beschreibung als String zurück
     */
    @Override
    public String getDescription() {
        return "Dieser Befehl gibt das Spielerinventar aus";
    }

    /**
     * Gibt den Namen des Befehles als String zurück
     */
    @Override
    public String getCommand() {
        return "inventar";
    }

	/**
	 * Gibt die korrekte Syntax als String zurück
	 */
    @Override
    public String getSyntax() {
        return "Befehl 'inventar': inventar";
    }
}
