public class Entity {
	protected int hp;
	protected int maxHp;
	protected IZustand aktuellerZustand;
	protected int staerke;
	protected Raum aktuellerRaum;

	/**
	 * Erzeugt ein Entity und initilaisiert die hp, maxHp und staerke mit übergebenen Werten und der Zustand wird auf Gesund gesetzt.
	 *
	 * @param hp
	 * @param staerke
	 */
	public Entity(int hp, int staerke) {
		this.hp = hp;
		this.maxHp = hp;
		this.staerke = staerke;
		this.aktuellerZustand = Gesund.getInstance();
	}

    /**
     *
     *
     * @return
     */
	public Raum getAktuellerRaum() {
		return aktuellerRaum;
	}

    /**
     *
     *
     * @param aktuellerRaum
     */
	public void setAktuellerRaum(Raum aktuellerRaum) {
		this.aktuellerRaum = aktuellerRaum;
	}

    /**
     *
     *
     * @param e
     */
	public void doDamage(Entity e) {
		e.takeDamage((int) (this.staerke * Math.PI));
	}

	private void takeDamage(int damage) {
		this.hp -= damage;
		this.aktuellerZustand.changeZustand(this);
	}
}
