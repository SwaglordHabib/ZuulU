
public class Muffin extends Gegenstand {

	private double erhoehungTragkraft;

	/**
	 * Erzeugt einen Muffin und initialisiert Name, Beschreibung, Gewicht und eine Tragkrafterh�hung mit �bergebenen Werten
	 *
	 * @param name
	 * @param beschreibung
	 * @param gewicht
	 * @param et
	 */
	public Muffin(String name, String beschreibung, double gewicht, double et) {
		super(name, beschreibung, gewicht, true);
		this.erhoehungTragkraft = et;
	}

	public double getErhoehungTragkraft() {
		return erhoehungTragkraft;
	}

    /**
     * Gibt eine Beschreibung des Muffins zur�ck
     *
     * @return
     */
	@Override
	public String toString() {
		return (super.toString() + ", Erh�hung der Tragkraft um " + erhoehungTragkraft);

	}

}
