public class Bewegungsunfaehig implements IZustand {

	private static Bewegungsunfaehig instance = new Bewegungsunfaehig();

	private Bewegungsunfaehig() {

	}

	public static IZustand getInstance() {
		if (instance == null) {
			instance = new Bewegungsunfaehig();
		}
		return instance;
	}

	@Override
	public IZustand heilen() {
		return Verwundet.getInstance();
	}

	@Override
	public IZustand leichtVerletzen() {
		return instance;

	}

	@Override
	public IZustand starkVerletzen() {
		return Tot.getInstance();
	}

	public String toString() {
		return "Bewegungsunfähig";
	}

	@Override
	public IZustand toeten() {
		return Tot.getInstance();
	}

	@Override
	public void changeZustand(Entity e) {
		if (e.hp >= e.maxHp * 0.7) {
			e.aktuellerZustand = this.heilen();
		} else if (e.hp >= e.maxHp * 0.3 && e.hp < e.maxHp * 0.7) {
			e.aktuellerZustand = this.leichtVerletzen();
		} else if (e.hp >= 1 && e.hp < e.maxHp + 0.3) {
			e.aktuellerZustand = this.starkVerletzen();
		} else {
			e.aktuellerZustand = this.toeten();
		}

	}

}
