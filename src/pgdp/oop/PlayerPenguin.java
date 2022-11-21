package pgdp.oop;

public class PlayerPenguin extends Penguin {

	public PlayerPenguin(int x, int y) {
		super(x, y);
	}

	public boolean canEat(Animal animal) {
		return animal != null && animal.eatenBy(this);
	}

	public boolean move(int newX, int newY) {

		newX = fixPosition(newX);
		newY = fixPosition(newY);

		antarktis[x][y] = null;
		if (antarktis[newX][newY] == null || canEat(antarktis[newX][newY])) {
			x = newX;
			y = newY;
		}

		if (antarktis[newX][newY] != null) {
			if (!(antarktis[newX][newY] instanceof Fish)) {
				return true;
			}
			antarktis[x][y].kill();
		}
		antarktis[x][y] = this;
		return false;

	}

}
