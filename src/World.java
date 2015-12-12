public class World {

	int size;
	boolean[][] world;
	Ant ant;

	World(int size) {
		this.size = size;
		world = new boolean[size][size];
		world[size / 2][size / 2] = true;
		ant = new Ant(size/2, size/2, this);
	}

	public int getSize() {
		return this.size;
	}

	public void toggleWorld(int row, int column){
		world[row][column] = !world[row][column];
	}
	
	public boolean isCellBlack(int row, int column) {
		return world[row][column];
	}

	public Ant getAnt() {
		// TODO Auto-generated method stub
		return ant;
	}
	
	int checkWorldSize(int value) {
		if (value < 0) {
			return this.getSize() - 1;
		} else if (value > this.getSize() - 1) {
			return value = 0;
		}
		return value;
	}
}
