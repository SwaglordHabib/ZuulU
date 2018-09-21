
public interface ICommands {
	/**
	 * Führt den mitgelieferten Befehl aus
	 *
	 * @param befehl
	 */
	void execute(Befehl befehl);

	/**
	 * Gibt die Beschreibung des Befehls aus
	 *
	 * @return
	 */
	String getDescription();

	/**
	 * Gibt den Names des Befehls aus
	 *
	 * @return
	 */
	String getCommand();

	/**
	 * Gibt die Syntax hinter dem Befehl zurÃ¼ck
	 *
	 * @return
	 */
	String getSyntax();

}
