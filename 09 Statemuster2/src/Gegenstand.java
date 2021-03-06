
public class Gegenstand {
	private String name;
	private String beschreibung;
	private double gewicht;
	private boolean essbar;

	/**
	 * Erzeugt einen Gegenstand und initialisiert den Name, Beschreibung, das Gewicht und ob man es essen kann mit übergebenen werten.
	 *
	 * @param name
	 * @param beschreibung
	 * @param gewicht
	 * @param essbar
	 */
	public Gegenstand(String name, String beschreibung, double gewicht, boolean essbar) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.gewicht = gewicht;
		this.essbar = essbar;
	}

	/**
	 * Gibt den Namen des Gegenstandes als String zur�ck
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Pr�ft ob der Gegenstand essbar ist
	 * @return 'true', wenn Gegenstand essbar
	 */
	public boolean isEssbar() {
		return essbar;
	}

	/**
	 * Gibt das Gewicht des Gegenstandes als Double zur�ck
	 * @return gewicht
	 */
	public double getGewicht() {
		return gewicht;
	}

	/**
	 * Liefere eine lange Beschreibung des Gegenstandes im Format ein Schatz, 2.5 kg
	 * 
	 * @return beschreibung des Gegenstandes und sein Gewicht
	 */
	public String toString() {
		return beschreibung + ", " + gewicht + "kg";
	}

}
