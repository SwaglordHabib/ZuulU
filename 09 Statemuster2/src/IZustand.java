public interface IZustand {
	IZustand heilen();

	IZustand leichtVerletzen();

	IZustand starkVerletzen();

	IZustand toeten();

	void changeZustand(Entity e);
}
