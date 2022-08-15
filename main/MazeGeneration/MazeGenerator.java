package team06.main.MazeGeneration;

import team06.main.PathCalculation.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class MazeGenerator {

	/**
	 * Declare variables
	 */
	private final List<MazeCell> gridMaze;

	private MazeCell currentCell;
	private Random r = new Random();
	
	//Return the generated maze
	public List<MazeCell> GetMaze() { return gridMaze; }

	/**
	 * Constructor for MazeGenerator. Directly creates a maze dependent on the given mazeSize
	 * @param mazeSize the number of rows and columns the maze should have.
	 */
	public MazeGenerator(int mazeSize) {
		this.gridMaze = new ArrayList<>();
		for (int x = 0; x < mazeSize; x++) {
			for (int y = 0; y < mazeSize; y++) {
				var newCell = new MazeCell(x, y);
				if(x == mazeSize-1 && y == mazeSize-1) {
					newCell.setStart(true);		
				}
				gridMaze.add(newCell);
			}
		}

		currentCell = gridMaze.get(r.nextInt(gridMaze.size() - 1));

		while(!gridMaze.parallelStream().allMatch(c -> c.isVisited())) {
			Carve();
		}

		new Dijkstra(gridMaze);
		CarveSecondPath(mazeSize);

		new Dijkstra(gridMaze, true);

		CarvePathToTarget();
		RemoveBordersOfLastCell();
	}
	
	/**
	 * Removes the border to the left of the cell which is next to the later target.
	 */
	private void CarvePathToTarget() {
		var targetNeighbourCell = GetCellAtCoordinate(gridMaze, 0, 0);
		var currentWalls = targetNeighbourCell.getWalls();
		targetNeighbourCell.setWalls(new boolean[]{currentWalls[0], currentWalls[1], currentWalls[2], false});
	}

	/**
	 * Make sure the target cell is reachable from two sides
	 */
	private void RemoveBordersOfLastCell() {
		var mazeCell = gridMaze.get(gridMaze.size()-1);
		var lastWalls = mazeCell.getWalls();
		mazeCell.setWalls(new boolean[]{lastWalls[0], false, false, lastWalls[3]});
	}

	/**
	 * Actual algorithm to carve through the default cells and create a proper maze
	 */
	private void Carve() {
		currentCell.setVisited(true);
		List<MazeCell> neighs = currentCell.getAllNeighbours(gridMaze);
		MazeCell next = neighs.get(r.nextInt(neighs.size()));
		if (!next.isVisited()) {
			currentCell.removeWalls(next);
		}
		currentCell = next;
	}

	/**
	 * Carve a second path to guarantee multiple paths to target cell
	 */
	private void CarveSecondPath(int mazeSize) {
		var currentCell = gridMaze.get(gridMaze.size()-1);
		var previousCell = currentCell;
		while (true){
			var newCell = TakeTwoSteps(currentCell, previousCell, mazeSize);
			if(newCell == null)
				break;
			else if(!newCell.isPath()) {
				var walls = newCell.getWalls();
				for (int j = 0; j < 4; j++) {
					if (walls[j]) {
						var nextCell = newCell.getNeighbourByWallNumber(gridMaze, j);
						var originalNextWalls = nextCell.getWalls();
						var nextWalls = Arrays.copyOf(originalNextWalls, originalNextWalls.length);
						if (j < 2)
							nextWalls[j + 2] = false;
						else
							nextWalls[j - 2] = false;

						if (CheckIfCarvingIsIllegal(nextCell, nextWalls))
							continue;

						walls[j] = false;
						newCell.setWalls(walls);
						nextCell.setWalls(nextWalls);
						return;
					}
				}
				newCell = TakeNextPathCell(currentCell, previousCell);
			}
			previousCell = currentCell;
			currentCell = newCell;
		}
	}

	private boolean CheckIfCarvingIsIllegal(MazeCell carvedCell, boolean[] newWalls){
		var topNeighbour = carvedCell.getTopNeighbour(gridMaze);
		var bottomNeighbour = carvedCell.getBottomNeighbour(gridMaze);

		if(!newWalls[0] && !newWalls[3]){
			//Check top left quadrant
			var topLeftNeighbour = topNeighbour.getLeftNeighbour(gridMaze);
			var neighbourWalls = topLeftNeighbour.getWalls();
			if(!neighbourWalls[1] && !neighbourWalls[2])
				return true;
		}
		if(!newWalls[0] && !newWalls[1]){
			//Check top right quadrant
			var topRightNeighbour = topNeighbour.getRightNeighbour(gridMaze);
			var neighbourWalls = topRightNeighbour.getWalls();
			if(!neighbourWalls[3] && !neighbourWalls[2])
				return true;
		}
		if(!newWalls[2] && !newWalls[1]){
			//Check bottom right quadrant
			var bottomRightNeighbour = bottomNeighbour.getRightNeighbour(gridMaze);
			var neighbourWalls = bottomRightNeighbour.getWalls();
			if(!neighbourWalls[3] && !neighbourWalls[0])
				return true;
		}
		if(!newWalls[2] && !newWalls[3]){
			//Check bottom left quadrant
			var bottomLeftNeighbour = bottomNeighbour.getLeftNeighbour(gridMaze);
			var neighbourWalls = bottomLeftNeighbour.getWalls();
			if(!neighbourWalls[1] && !neighbourWalls[0])
				return true;
		}
		return false;
	}

	private MazeCell TakeNextPathCell(MazeCell currentCell, MazeCell previousCell){
		var neighbours = currentCell.getValidMoveNeighbours(gridMaze);
		for (var cell: neighbours) {
			if(cell.isPath() && cell != currentCell && cell != previousCell){
				return cell;
			}
		}
		return null;
	}

	private MazeCell TakeTwoSteps(MazeCell currentCell, MazeCell previousCell, int mazeSize){
		var neighbours = currentCell.getValidMoveNeighbours(gridMaze);
		MazeCell newCell = null;
		for (var cell: neighbours) {
			if(!cell.isPath() && cell != currentCell){
				var neighboursSecondStep = cell.getValidMoveNeighbours(gridMaze);
				for (var secondStepCell: neighboursSecondStep) {
					if(secondStepCell != cell && !secondStepCell.isPath() && !CellIsOnEdge(secondStepCell, mazeSize)){
						return secondStepCell;
					}
				}
			}
			else if(cell != currentCell && cell != previousCell){
				newCell = cell;
			}
		}
		return newCell;
	}
	private boolean CellIsOnEdge(MazeCell cell, int mazeSize) {
		return cell.getX() == mazeSize-1 || cell.getX() == 0 || cell.getY() == 0 || cell.getY() == mazeSize-1 ;
	}

	/**
	 * Merges four mazes into one for easier frontend handling.
	 * @return the merged maze in a List<>
	 */
	public static List<MazeCell> MergeFourMazesAndCreateTargets(List<MazeCell> topLeftMaze, List<MazeCell> topRightMaze,
																List<MazeCell> bottomLeftMaze, List<MazeCell> bottomRightMaze, int mazeSize){
		List<MazeCell> mergedMaze = new ArrayList<>();
		var mergedMazeSize = mazeSize * 2;

		CreateTargetZone(mergedMazeSize, 0 ,mergedMaze);

		MergeTopMazes(topLeftMaze, topRightMaze, mazeSize, mergedMaze);
		MergeBottomMazes(bottomLeftMaze, bottomRightMaze, mazeSize, mergedMaze);

		CreateTargetZone(mergedMazeSize, mergedMazeSize + 1, mergedMaze);

		return mergedMaze;
	}

	private static void MergeBottomMazes(List<MazeCell> bottomLeftMaze, List<MazeCell> bottomRightMaze, int mazeSize, List<MazeCell> mergedMaze) {
		for(var cell : bottomLeftMaze){
			var initalX = cell.getX();
			var initalY = cell.getY();
			cell.setY(initalY + mazeSize);
			cell.setX(initalX + 1);
			mergedMaze.add(cell);
			if(initalY == mazeSize -1)
			{
				for (int i = 0; i < mazeSize; i++){
					var mergeCell = GetCellAtCoordinate(bottomRightMaze, initalX, i);
					if(mergeCell.getX() != initalX)
						break;
					mergeCell.setX(mergeCell.getX() + mazeSize + 1);
					mergeCell.setY(mergeCell.getY() + mazeSize);
					mergedMaze.add(mergeCell);
				}
			}
		}
	}
	
	private static void MergeTopMazes(List<MazeCell> topLeftMaze, List<MazeCell> topRightMaze, int mazeSize, List<MazeCell> mergedMaze) {
		for(var cell : topLeftMaze){
			var currentRow = cell.getX();
			cell.setX(cell.getX() + 1);
			mergedMaze.add(cell);
			if(cell.getY() == mazeSize -1)
			{
				for (int i = 0; i < mazeSize; i++){
					var mergeCell = GetCellAtCoordinate(topRightMaze, currentRow, i);
					if(mergeCell.getX() != currentRow)
						break;
					mergeCell.setX(mergeCell.getX() + mazeSize +1);
					mergedMaze.add(mergeCell);
				}
			}
		}
	}

	private static void CreateTargetZone(int mergedMazeSize, int xCoordinate, List<MazeCell> mergedMaze) {
		for(int i = 0; i < mergedMazeSize; i++){
			var isTarget = i == 0 || i == mergedMazeSize -1;
			var cell = MazeCell.CreateEdgeCell(xCoordinate, i, isTarget);
			mergedMaze.add(cell);
		}
	}

	/**
	 * Transforms a given maze by either mirroring it vertically or horizontally
	 * @param originalMaze the maze that should be mirrored
	 * @param vertical boolean to decide if the maze should be mirrored vertically (otherwise horizontally)
	 * @return the mirrored maze
	 */
	public List<MazeCell> CreateTransformation(List<MazeCell> originalMaze, boolean vertical){
		List<MazeCell> mazeCopy = new ArrayList<>();
		var lastMazeCell = originalMaze.get(originalMaze.size() - 1);

		for (int x = 0; x <= lastMazeCell.getX(); x++) {
			for (int y = 0; y <= lastMazeCell.getY(); y++) {
				MazeCell mirroredCell;
				if(vertical)
					mirroredCell = CopyMazeCellVertically(originalMaze, lastMazeCell.getX() - x, x, y);
				else
					mirroredCell = CopyMazeCellHorizontally(originalMaze, lastMazeCell.getY() - y, x, y);
				mazeCopy.add(mirroredCell);
			}
		}
		return mazeCopy;
	}

	private MazeCell CopyMazeCellVertically(List<MazeCell> originalMaze, int x, int currentX, int y) {
		var originalCell = GetCellAtCoordinate(originalMaze, x, y);
		var mirroredCell = new MazeCell(currentX, y, originalCell);
		var originalWalls = originalCell.getWalls();
		mirroredCell.setWalls(new boolean[]{originalWalls[0], originalWalls[3], originalWalls[2], originalWalls[1]});
		return mirroredCell;
	}

	private MazeCell CopyMazeCellHorizontally(List<MazeCell> originalMaze, int y, int x, int currentY) {
		var originalCell = GetCellAtCoordinate(originalMaze, x, y);
		var mirroredCell = new MazeCell(x, currentY, originalCell);
		var originalWalls = originalCell.getWalls();
		mirroredCell.setWalls(new boolean[]{originalWalls[2], originalWalls[1], originalWalls[0], originalWalls[3]});
		return mirroredCell;
	}

	public static MazeCell GetCellAtCoordinate(List<MazeCell> maze, int x, int y){
		for (var cell: maze) {
				if(cell.getX() == x && cell.getY() == y)
					return cell;
		}
		return null;
	}
}