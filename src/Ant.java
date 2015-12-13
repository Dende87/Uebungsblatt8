public class Ant {

	int row;
	int column;
	World world;
	int direction = 0;

	Ant(int row, int column, World world) {

		this.row = row;
		this.column = column;
		this.world = world;

	}

	public void nextStep() {

		world.toggleWorld(row, column);
		ChangeDirection();

		switch (direction) {
		//Norden
		case 0:
			row = world.checkWorldSize(row - 1);
			//row = world.checkWorldSize(row - (int)(Math.random()*(5)+1));
			break;
		//Osten
		case 1:
			column = world.checkWorldSize(column + 1);
			//column = world.checkWorldSize(column + (int)(Math.random()*(5)+1));
			break;
		//Sueden
		case 2:
			row = world.checkWorldSize(row + 1);
			//row = world.checkWorldSize(row + (int)(Math.random()*(5)+1));
			break;
		//Westen
		case 3:
			column = world.checkWorldSize(column - 1);
			//column = world.checkWorldSize(column - (int)(Math.random()*(5)+1));
			break;
		default:
			System.out.println("Direction not implemented!");
		}
		System.out.println("row: " + row + " " + "column: " + column);
	}

	private void ChangeDirection() {

		if (world.isCellBlack(row, column)) {
			// schwarz 90° nach links drehen
			direction = ((direction - 1 < 0) ? 3 : direction - 1);
		} else {
			// weiß 90° nach rechts drehen
			direction = ((direction + 1 > 3) ? 0 : direction + 1);
		}
		
		System.out.println("direction: " + direction);
	}

}
