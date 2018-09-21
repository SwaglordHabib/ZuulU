public class Bewegungsunfaehig implements IZustand {

	private static Bewegungsunfaehig instance = new Bewegungsunfaehig();

	private Bewegungsunfaehig() {

	}

	/**
	 * Pr�ft ob eine Instanz der Klasse vorhanden ist
	 * 
	 * @return eine vorhandene oder neu erstellte Instanz
	 */
	public static IZustand getInstance() {
		if (instance == null) {
			instance = new Bewegungsunfaehig();
		}
		return instance;
	}

	/**
	 * Sofern der Zustand 'Bewegungsunf�hig' ist, wird die Instanz von der Klasse 'Verwundet' zur�ck gegeben
	 * 
	 * @return 'instance', gibt die Instanz von 'Verwundet' zur�ck
	 */
	@Override
	public IZustand heilen() {
		return Verwundet.getInstance();
	}

	/**
	 * Gibt die Instanz von sich selbst zur�ck
	 *
	 * @return 'instance', gibt die Instanze von sich selber zur�ck
	 */
	@Override
	public IZustand leichtVerletzen() {
		return instance;

	}

	/**
	 *
	 *
	 * @return 'Tot.instance', gibt die Instanze von Tot zur�ck
	 */
	@Override
	public IZustand starkVerletzen() {
		return Tot.getInstance();
	}

	/**
	 * Name des Zustandes
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return "Bewegungsunf�hig";
	}

	/**
	 *
	 * @return 'Tot.instance', gibt die Instanze von Tot zur�ck
	 */
	@Override
	public IZustand toeten() {
		return Tot.getInstance();
	}

	/**
	 * Ermittelt den Zustand f�r eine Entity, das kann sowohl ein Spieler als auch ein Monster sein.
	 *
	 * @param entity
	 */
	@Override
	public void changeZustand(Entity entity) {
		if (entity.hp >= entity.maxHp * 0.7) {
			entity.aktuellerZustand = this.heilen();
		} else if (entity.hp >= entity.maxHp * 0.3 && entity.hp < entity.maxHp * 0.7) {
			entity.aktuellerZustand = this.leichtVerletzen();
		} else if (entity.hp >= 1 && entity.hp < entity.maxHp + 0.3) {
			entity.aktuellerZustand = this.starkVerletzen();
		} else {
			entity.aktuellerZustand = this.toeten();
		}

	}

}
