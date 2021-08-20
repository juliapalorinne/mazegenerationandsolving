package mazeSolving;

import maze.Cell;
import maze.Maze;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class BreadthfirstSearchTest {
    BreadthfirstSearch search;
    Maze maze;
    
    @Before
    public void setUp() {
        Cell[][] cells = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[j][i] = new Cell(i, j);
            }
        }
        
        maze = new Maze(cells);
        maze.getCell(0, 0).removeUpperWall();
        maze.removeWall(maze.getCell(0, 0), 2);
        maze.removeWall(maze.getCell(0, 1), 2);
        maze.removeWall(maze.getCell(0, 1), 3);
        maze.removeWall(maze.getCell(0, 2), 3);
        maze.removeWall(maze.getCell(1, 1), 0);
        maze.removeWall(maze.getCell(1, 0), 3);
        maze.removeWall(maze.getCell(2, 0), 2);
        maze.removeWall(maze.getCell(2, 1), 2);
        maze.getCell(2, 2).removeLowerWall();
        
        search = new BreadthfirstSearch(maze);
        
        search.firstCell = maze.getCell(0, 0);
    }
    
    
    @Test
    public void findFirstCellFindsCell() {
        maze.getCell(0, 0).addUpperWall();
        assertTrue(search.findFirstCell() == false);
        
        maze.getCell(2, 0).removeUpperWall();
        
        assertTrue(search.findFirstCell() == true);
    }
    
    @Test
    public void findLastCellFindsCell() {
        maze.getCell(2, 2).addLowerWall();
        assertTrue(search.findLastCell() == false);
        
        maze.getCell(1, 2).removeLowerWall();
        
        assertTrue(search.findLastCell() == true);
    }
    
    @Test
    public void findRouteReturnsFalseIfFirstCellMissing() {
        maze.getCell(0, 0).addUpperWall();
        assertTrue(search.findRoute() == false);
    }
    
    @Test
    public void findRouteReturnsFalseIfLastCellMissing() {
        maze.getCell(2, 2).addLowerWall();
        assertTrue(search.findRoute() == false);
    }
    
    @Test
    public void findRouteReturnsFalseIfNoRouteIsFound() {
        maze.getCell(1, 1).addUpperWall();
        maze.getCell(1, 0).addLowerWall();
        assertTrue(search.findRoute() == false);
    }
    
    @Test
    public void findRouteReturnsTrueIfRouteIsFound() {
        assertTrue(search.findRoute() == true);
    }
}
