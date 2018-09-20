
public interface ICommands {

	void execute(Befehl befehl);

	String getDescription();

	String getCommand();

	String getSyntax();

}
