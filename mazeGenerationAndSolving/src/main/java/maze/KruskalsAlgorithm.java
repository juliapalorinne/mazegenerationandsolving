package maze;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;


/** Randomized Kruskal's algorithm for creating a maze.
 *
 * @author julia
 */
public final class KruskalsAlgorithm {  
    
    public Maze maze;
    public ArrayList<Wall> walls;
    public PriorityQueue<Wall> queue;
    public int cells;
    public int parts;
    Cell[] parents;

    
    /** Create a new maze with given dimensions.
     *
     * @param width of the maze
     * @param height of the maze
     */
    public KruskalsAlgorithm(int width, int height) {
        this.cells = width * height;
        this.parts = width * height;
        this.walls = new ArrayList<>();
        this.queue = new PriorityQueue<>();
        this.parents = new Cell[cells];
        
        generateNewMaze(width, height);
    }
    
    
    /** Run the Kruskal's algorithm to find a simple maze.
     * Then print the maze.
     */
    public void runSimpleMaze() {
        ArrayList<Wall> tree = findSimpleMaze();
        removeWallsFromMaze(tree);
    }

    
    /** Run the Kruskal's algorithm to find a simple maze.
     * Then print the maze.
     */
    public void runLoopedMaze() {
        ArrayList<Wall> tree = findLoopedMaze();
        removeWallsFromMaze(tree);
    }

    
    public void removeWallsFromMaze(ArrayList<Wall> tree) {
        for (Wall wall : tree) {
            wall.openTheWall();
            if (wall.getCell1().getX() == wall.getCell2().getX()) {
                maze.removeWall(wall.getCell1(), 2);
            }
            if (wall.getCell1().getY() == wall.getCell2().getY()) {
                maze.removeWall(wall.getCell1(), 3);
            }
            
        }
        
        maze.getCell(0, 0).removeUpperWall();
        maze.getCell(maze.getWidth() - 1, maze.getHeight() - 1).removeLowerWall();
    }
    
    
    /** Generate a new maze with given dimensions.
     *
     * @param width number of cells
     * @param height number of cells
     */
    public void generateNewMaze(int width, int height) {
        int number = 0;
        
        Cell[][] cellArray = new Cell[height][width];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j);
                cell.addNumber(number);
                cellArray[j][i] = cell;
                parents[number] = cell;
                number++;
            }
        }
        this.maze = new Maze(cellArray);
        createWalls(width, height);
    }
    
    
    /** Create walls between cells.
     *
     * @param width of the maze
     * @param height of the maze
     */
    public void createWalls(int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j != width - 1) {
                    makeSet(maze.getCell(j, i), maze.getCell(j + 1, i));
                }
                if (i != height - 1) {
                    makeSet(maze.getCell(j, i), maze.getCell(j, i + 1));
                }
            }
        }
    }
    
    
    public int getRandomWeight() {
        Random r = new Random();
        int number = r.nextInt(cells * 100);
        return number;
    }
    
    
    /** Create a set of two cells with random weight.
     *
     * @param cell1 first cell
     * @param cell2 second cell
     */
    public void makeSet(Cell cell1, Cell cell2) {
        int weight = getRandomWeight();
        Wall wall = new Wall(cell1, cell2, weight);
        walls.add(wall);
        queue.add(wall);
    }
    
    
    public ArrayList<Wall> findSimpleMaze() {
        ArrayList<Wall> tree = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            Wall wall = queue.poll();
            Cell cell1 = wall.getCell1();
            Cell cell2 = wall.getCell2();
            
            Cell parent1 = findRoot(cell1);
            Cell parent2 = findRoot(cell2);
            
            if (parent1.getNumber() > parent2.getNumber()) {
                parents[parent2.getNumber()] = parent1;
                tree.add(wall);
            } else if (parent1.getNumber() < parent2.getNumber()) {
                parents[parent1.getNumber()] = parent2;
                tree.add(wall);
            } else {
                parents[parent2.getNumber()] = parent1;
            }
        }
        
        return tree;        
    }
    
    
    public ArrayList<Wall> findLoopedMaze() {
        ArrayList<Wall> tree = new ArrayList<>();
        
        while (!queue.isEmpty() && parts > 1) {
            Wall wall = queue.poll();
            Cell cell1 = wall.getCell1();
            Cell cell2 = wall.getCell2();
            
            Cell parent1 = findRoot(cell1);
            Cell parent2 = findRoot(cell2);
            
            
            if (parent1.getNumber() > parent2.getNumber()) {
                parents[parent2.getNumber()] = parent1;
                parts--;
            } else if (parent1.getNumber() < parent2.getNumber()) {
                parents[parent1.getNumber()] = parent2;
                parts--;
            } else {
                parents[parent2.getNumber()] = parent1;
            }
            tree.add(wall);
        }
        
        return tree;        
    }
    
    
    public Cell findRoot(Cell cell) {
        Cell parent = parents[cell.getNumber()];
        
        if (parent.getNumber() == cell.getNumber()) {
            return parent;
        }
        return findRoot(parent);
    }

}
