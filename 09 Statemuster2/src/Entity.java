public class Entity {
	protected int hp;
	protected int maxHp;
	protected IZustand aktuellerZustand;
	protected int staerke;
	protected Raum aktuellerRaum;

	public Entity(int hp, int staerke) {
		this.hp = hp;
		this.maxHp = hp;
		this.staerke = staerke;
		this.aktuellerZustand = Gesund.getInstance();
	}

	public int getHp() {
		return this.hp;
	}

	public Raum getAktuellerRaum() {
		return aktuellerRaum;
	}

	public void setAktuellerRaum(Raum aktuellerRaum) {
		this.aktuellerRaum = aktuellerRaum;
	}

	public void doDamage(Entity e) {
		e.takeDamage((int) (this.staerke * Math.PI));
	}

	private void takeDamage(int damage) {
		this.hp -= damage;
		this.aktuellerZustand.changeZustand(this);
	}

	private void heal() {
		int healstraeke = (int) (this.staerke * Math.PI / 2.5);
		if (this.hp + healstraeke >= this.maxHp) {
			this.hp = this.maxHp;
		} else {
			this.hp += healstraeke;
		}
	}

	public void heilen() {
		heal();
		this.aktuellerZustand.changeZustand(this);
	}

	public void leichtVerletzen() {
		this.aktuellerZustand.changeZustand(this);
	}

	public void starkVerletzen() {
		this.aktuellerZustand.changeZustand(this);
	}
}
