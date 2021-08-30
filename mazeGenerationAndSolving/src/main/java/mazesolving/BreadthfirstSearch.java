package mazesolving;

import java.util.ArrayDeque;
import maze.Cell;
import maze.Maze;

/** Class for solving a maze with breadth-first search.
 *
 * @author julia
 */
public class BreadthfirstSearch extends MazeSolvingAlgorithm {
    
    public ArrayDeque<Cell> queue;
    
    
    /** Start a new breadth-first search with given maze.
     *
     * @param maze a new maze
     */
    public BreadthfirstSearch(Maze maze) {
        this.maze = maze;
        this.queue = new ArrayDeque();
    }
    
    
    /** Run the breadth-first search algorithm.
     * Find a route or print error message.
     */
    public void run() {
        if (findRoute() == false) {
            System.out.println("No routes");
        } else {
            getShortestPath();
            // for (int i = 0; i < maze.getHeight() - 1; i++) {
            //     System.out.println(Arrays.toString(distance[i]));
            // }
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
        queue.push(firstCell);
        firstCell.visit();
                
        search();
        
        if (distance[lastCell.getY()][lastCell.getX()] == 0) {
            return false;
        }
        
        return true;
    }
    
    
    /** Get shortest path.
     *
     */
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
            Cell next = queue.poll();
            
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
            } else if (previousCell[cell.getY()][cell.getX()] != 0) {
                loops = true;
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
            } else if (previousCell[cell.getY()][cell.getX()] != 1) {
                loops = true;            
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
            } else if (previousCell[cell.getY()][cell.getX()] != 2) {
                loops = true;
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
            } else if (previousCell[cell.getY()][cell.getX()] != 3) {
                loops = true;
            }
        }
    }
    
    
}
