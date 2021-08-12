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
    
    Cell[] order;
    Cell[] parent;

    
    /** Create a new maze with given dimensions.
     *
     * @param width of the maze
     * @param height of the maze
     */
    public KruskalsAlgorithm(int width, int height) {
        generateNewMaze(width, height);
        
        this.walls = new ArrayList<>();
        this.queue = new PriorityQueue<>();
        this.parent = new Cell[cells+1];
        this.order = new Cell[cells+1];
    }
    
    
    /** Run the Kruskal's algorithm.
     * Then print the maze.
     */
    public void run() {
        
        
        maze.getCell(0, 0).removeUpperWall();
        maze.getCell(maze.getWidth() - 1, maze.getHeight() - 1).removeLowerWall();
        // maze.printMaze();
    }

    
    /** Generate a new maze with given dimensions.
     *
     * @param width number of cells
     * @param height number of cells
     */
    public void generateNewMaze(int width, int height) {
        this.cells = width * height;  
        int number = 0;
        
        Cell[][] cellArray = new Cell[height][width];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j);
                cell.addNumber(number);
                cellArray[j][i] = cell;
                number++;
            }
        }
        this.maze = new Maze(cellArray);
    }
    
    
    public int getRandomWeight() {
        Random r = new Random();
        int number = r.nextInt(cells * 100);
        return number;
    }
    
    
    public void combine(Cell cell1, Cell cell2, int weight) {
        Wall wall = new Wall(cell1, cell2, weight);
        walls.add(wall);
        queue.add(wall);
    }
    
    public ArrayList<Wall> count() {
        ArrayList<Wall> tree = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            Wall w = queue.poll();
            Cell cell1 = w.getCell1();
            Cell cell2 = w.getCell2();
            
            Cell parent1 = findRoot(cell1);
            Cell parent2 = findRoot(cell2);
            
            if (parent1.getNumber() == parent2.getNumber()) {
                continue;
            }
            
        }
        
        return tree;        
    }
    
    
    public Cell findRoot(Cell cell) {
        Cell p = new Cell(0, 0);
        return p;
    }

}
