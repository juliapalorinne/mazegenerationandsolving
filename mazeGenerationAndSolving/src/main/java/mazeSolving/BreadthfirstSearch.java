package mazeSolving;

import java.util.ArrayDeque;
import maze.Cell;
import maze.Maze;


public class BreadthfirstSearch {
    
    public Maze maze;
    public ArrayDeque queue;
    public Cell firstCell;
    public Cell lastCell;
    public int visited;
    public int numberOfCells;
    public int[][] distance;
    public int[][] previousCell;
     
    
    /** Start a new breadth-first search with given maze.
     *
     * @param maze a new maze
     */
    public BreadthfirstSearch(Maze maze) {
        this.maze = maze;
        this.numberOfCells = maze.getHeight() * maze.getWidth();
        this.queue = new ArrayDeque();
        this.distance = new int[maze.getHeight()][maze.getWidth()];
        this.previousCell = new int[maze.getHeight()][maze.getWidth()];
        this.visited = 0;
    }
    
    
    /** Run the breadth-first search algorithm.
     * Find a route or print error message.
     */
    public void run() {
        if (findRoute() == false) {
            System.out.println("No routes");
        } else {
            getShortestPath();
        }
    }
    
    
    /** Find a route through maze.
     *
     * @return true if route found, false if not
     */
    public boolean findRoute() {
        if (!findFirstCell() || !findLastCell()) {
            return false;
        }
        
        search();
        
        if (distance[lastCell.getY()][lastCell.getX()] == 0) {
            return false;
        }
        
        return true;
    }
    
    
    public void getShortestPath() {
        int x = lastCell.getX();
        int y = lastCell.getY();
        
        while (x != firstCell.getX() || y != firstCell.getY()) {
            maze.getCell(x, y).addToRoute();
            
            if (previousCell[y][x] == 0) {
                y--;
            } else if (previousCell[y][x] == 1) {
                x--;
            } else if (previousCell[y][x] == 2) {
                y++;
            } else if (previousCell[y][x] == 3) {
                x++;
            }
        }
        
        maze.getCell(x, y).addToRoute();
    }
    
    
    /** Continue search while queue is not empty.
     *
     */
    public void search() {
        while (!queue.isEmpty()) {
            Cell next = (Cell) queue.poll();
            
            for (int i = 0; i <= 3; i++) {
                if (i == 0) {
                    moveUp(next);
                }
                
                if (i == 1) {
                    moveLeft(next);
                }
                
                if (i == 2) {
                    moveDown(next);
                }
                
                if (i == 3) {
                    moveRight(next);
                }
            }
        }
    }
    
    
    /** Check the cell upwards and move if possible.
     *
     * @param cell current cell
     */
    public void moveUp(Cell cell) {
        if (cell.getY() > 0 && cell.getUpperWall() == false) {
            if (maze.getCell(cell.getX(), cell.getY() - 1).numberOfVisits() == 0) {
                queue.add(maze.getCell(cell.getX(), cell.getY() - 1));
                distance[cell.getY() - 1][cell.getX()] = distance[cell.getY()][cell.getX()] + 1;
                previousCell[cell.getY() - 1][cell.getX()] = 2;
                maze.getCell(cell.getX(), cell.getY() - 1).visit();
                visited++;
            }
        }
    }
    
    
    /** Check the cell to left and move if possible.
     *
     * @param cell current cell
     */
    public void moveLeft(Cell cell) {
        if (cell.getX() > 0 && cell.getLeftWall() == false) {
            if (maze.getCell(cell.getX() - 1, cell.getY()).numberOfVisits() == 0) {
                queue.add(maze.getCell(cell.getX() - 1, cell.getY()));
                distance[cell.getY()][cell.getX() - 1] = distance[cell.getY()][cell.getX()] + 1;
                previousCell[cell.getY()][cell.getX() - 1] = 3;
                maze.getCell(cell.getX() - 1, cell.getY()).visit();
                visited++;
            }
        }
    }
    
    
    /** Check the cell downwards and move if possible.
     *
     * @param cell current cell
     */
    public void moveDown(Cell cell) {
        if (cell.getY() < maze.getHeight() - 1 && cell.getLowerWall() == false) {
            if (maze.getCell(cell.getX(), cell.getY() + 1).numberOfVisits() == 0) {
                queue.add(maze.getCell(cell.getX(), cell.getY() + 1));
                distance[cell.getY() + 1][cell.getX()] = distance[cell.getY()][cell.getX()] + 1;
                previousCell[cell.getY() + 1][cell.getX()] = 0;
                maze.getCell(cell.getX(), cell.getY() + 1).visit();
                visited++;
            }
        }
    }
    
    
    /** Check the cell to right and move if possible.
     *
     * @param cell current cell
     */
    public void moveRight(Cell cell) {
        if (cell.getX() < maze.getWidth() - 1 && cell.getRightWall() == false) {
            if (maze.getCell(cell.getX() + 1, cell.getY()).numberOfVisits() == 0) {
                queue.add(maze.getCell(cell.getX() + 1, cell.getY()));
                distance[cell.getY()][cell.getX() + 1] = distance[cell.getY()][cell.getX()] + 1;
                previousCell[cell.getY()][cell.getX() + 1] = 1;
                maze.getCell(cell.getX() + 1, cell.getY()).visit();
                visited++;
            }
        }
    }
    
    
    /** Find a cell from the first row with upper wall missing.
     *
     * @return true if first cell found, false if not
     */
    public boolean findFirstCell() {
        for (int i = 0; i < maze.getWidth(); i++) {
            if (maze.getCell(i, 0).getUpperWall() == false) {
                queue.push(maze.getCell(i, 0));
                firstCell = maze.getCell(i, 0);
                maze.getCell(i, 0).visit();
                distance[firstCell.getY()][firstCell.getX()] = 0;
                previousCell[firstCell.getY()][firstCell.getX()] = 1;
                return true;
            }
        }
        return false;
    }
    
    
    /** Find a cell from the last row with lower wall missing.
     *
     * @return true if last cell found, false if not
     */
    
    public boolean findLastCell() {
        for (int i = 0; i < maze.getWidth(); i++) {
            if (maze.getCell(i, maze.getHeight() - 1).getLowerWall() == false) {
                lastCell = maze.getCell(i, maze.getHeight() - 1);
                return true;
            }
        }
        return false;
    }
    
}
