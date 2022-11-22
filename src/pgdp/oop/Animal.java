package pgdp.oop;

import java.awt.*;
import java.io.File;

public abstract class Animal {

	protected int x, y;
	static String filename;
	protected File f;
	protected Image image;
	protected char type;

	protected boolean alive;

	protected static Animal[][] antarktis;

	public Animal(int x, int y) {
		this.x = x;
		this.y = y;
		this.alive = true;
	}

	public void move() {

		if (!alive) return;
		antarktis[x][y] = null;

		if (canEat(antarktis[fixPosition(x-1)][y]) && canMove(fixPosition(x-1), y, 0)) {
			x = fixPosition(x-1);
		}
		else if (canEat(antarktis[x][fixPosition(y-1)]) && canMove(x, fixPosition(y-1), 1)) {
			y = fixPosition(y-1);
		}
		else if (canEat(antarktis[fixPosition(x+1)][y]) && canMove(fixPosition(x+1), y, 2)) {
			x = fixPosition(x+1);
		}
		else if (canEat(antarktis[x][fixPosition(y+1)]) && canMove(x, fixPosition(y+1), 3)) {
			y = fixPosition(y+1);
		}
		else if (antarktis[fixPosition(x-1)][y] == null && canMove(fixPosition(x-1), y, 0)) {
			x = fixPosition(x-1);
		}
		else if (antarktis[x][fixPosition(y-1)] == null && canMove(x, fixPosition(y-1), 1)) {
			y = fixPosition(y-1);
		}
		else if (antarktis[fixPosition(x+1)][y] == null && canMove(fixPosition(x+1), y, 2)) {
			x = fixPosition(x+1);
		}
		else if (antarktis[x][fixPosition(y+1)] == null && canMove(x, fixPosition(y+1), 3)) {
			y = fixPosition(y+1);
		}


		if (antarktis[x][y] != null) {
			antarktis[x][y].kill();
		}
		antarktis[x][y] = this;

	}

	protected boolean canMove(int x, int y, int side) {
		if (side != 0 && antarktis[fixPosition(x-1)][y] != null && antarktis[fixPosition(x-1)][y].canEat(this)) {
			return false;
		}
		else if (side != 1 && antarktis[x][fixPosition(y-1)] != null && antarktis[x][fixPosition(y-1)].canEat(this)) {
			return false;
		}
		else if (side != 2 && antarktis[fixPosition(x+1)][y] != null && antarktis[fixPosition(x+1)][y].canEat(this)) {
			return false;
		}
		else if (side != 3 && antarktis[x][fixPosition(y+1)] != null && antarktis[x][fixPosition(y+1)].canEat(this)) {
			return false;
		}
		else {
			return true;
		}
	}

	protected static int fixPosition(int pos) {
		if (pos < 0) {
			return 41 + pos;
		}
		return pos % 41;
	}

	public abstract boolean canEat(Animal animal);


	protected abstract boolean eatenBy(Penguin penguin);
	protected abstract boolean eatenBy(PlayerPenguin playerPenguin);
	protected abstract boolean eatenBy(Whale whale);
	protected abstract boolean eatenBy(LeopardSeal leopardSeal);
	protected abstract boolean eatenBy(Fish fish);

	public static void setAntarktis(Animal[][] antarktis) {
		Animal.antarktis = antarktis;
	}
	// Graphics Stuff - You don't have to do anything here

	public void kill() {
		this.alive = false;
	}

	private void paintSymbol(Graphics g, Color c, int height, int width) {
		GradientPaint gradient = new GradientPaint(15, 0, c, width, 0, Color.LIGHT_GRAY);
		((Graphics2D) g).setPaint(gradient);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillOval((int) (width * 0.3), (int) (height * 0.3), (int) (width * 0.5),
				(int) (height * 0.5));
	}

	public void draw(Graphics g, int height, int width) {
		if (image == null) {
			paintSymbol(g, Color.YELLOW, height, width);
			return;
		}
		((Graphics2D) g).drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(null),
				image.getHeight(null), null);
	}

}
