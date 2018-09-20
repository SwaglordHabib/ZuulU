
public class Gegenstand {
	private String name;
	private String beschreibung;
	private double gewicht;
	
	/**
	 * Der Konstruktor zum erstellen eines Gegenstand-Objektes
	 * 
	 * @param name
	 *            des Gegenstandes (String)
	 * @param beschreibung
	 *            des Gegenstandes (String)
	 * @param gewicht
	 *            des Gegenstandes (Double)
	 */
	public Gegenstand(String name, String beschreibung, double gewicht)
	{
		this.name = name;
		this.beschreibung = beschreibung;
		this.gewicht = gewicht;
	}

	/**
	 * Gibt den Namen des Gegenstandes als String zurück
	 * 
	 * @return Name des Gegenstandes (String)
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gibt die Beschreibung des Gegenstandes als String zurück
	 * 
	 * @return Beschreibung des Gegenstandes (String)
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * Gibt das Gewicht des Gegenstandes als Double zurück
	 * 
	 * @return Gewicht des Gegenstandes (Double)
	 */
	public double getGewicht() {
		return gewicht;
	}

	/**
	 * Liefere eine lange Beschreibung des Gegenstandes im Format
	 *    ein Schatz, 2.5 kg
	 * @return beschreibung des Gegenstandes und sein Gewicht
	 */
	public String toString() {
		return beschreibung + ", "+ gewicht + "kg";
	}
}
