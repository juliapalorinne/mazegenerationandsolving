package mazegeneration;

import java.util.ArrayList;
import maze.Cell;
import maze.Maze;
import maze.Wall;
import mazesolving.BreadthfirstSearch;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class KruskalsAlgorithmTest {
    KruskalsAlgorithm search;
    
        
    @Before
    public void setUp() {
        int width = 50;
        int height = 50;
        
        this.search = new KruskalsAlgorithm(width, height);
    }
    
    
    @Test
    public void generateMazeWorks() {
        assertTrue(this.search.maze.getWidth() == 50);
        assertTrue(this.search.maze.getHeight() == 50);
    }
    
    @Test
    public void getRandomWeightGivesCorrectNumbers() {
        for (int i = 0; i < 50; i++) {
            int weight = search.getRandomWeight();
            assertTrue(weight >= 0 && weight < 50 * 50 * 100);
        }
    }
    
    @Test
    public void createWallsCreatesAllWalls() {
        assertTrue(search.walls.size() == 49 * 50 + 50 * 49);
        Wall wall = search.walls.get(4);
        assertTrue(wall.getCell1().getX() == 2);
        assertTrue(wall.getCell1().getY() == 0);
        assertTrue(wall.getCell2().getX() == 3);
        assertTrue(wall.getCell2().getY() == 0);
        
        wall = search.walls.get(100);
        assertTrue(wall.getCell1().getX() == 0);
        assertTrue(wall.getCell1().getY() == 1);
        assertTrue(wall.getCell2().getX() == 0);
        assertTrue(wall.getCell2().getY() == 2);
    }
    
    @Test
    public void findRootWorksWhenCellIsItsOwnParent() {
        Cell cell = search.maze.getCell(12, 4);
        Cell parent = search.findRoot(cell);
        assertTrue(parent.getX() == 12);
        assertTrue(parent.getY() == 4);
    }
    
    @Test
    public void orderingWorks() {
        Wall wall = search.queue.poll();
        int weight = wall.getWeight();
        
        for (int i = 0; i < 1000; i++) {
            wall = search.queue.poll();
            assertTrue(weight <= wall.getWeight());
            weight = wall.getWeight();
        }
    }
    
    @Test
    public void generateSimpleMazeGeneratesMazeWithoutLoops() {
        search.runSimpleMaze();
        Maze maze = search.maze;
        
        BreadthfirstSearch findLoops = new BreadthfirstSearch(maze);
        findLoops.run();
        assertFalse(findLoops.loops);
    }
    
    @Test
    public void generateLoopedMazeGeneratesMazeWithLoops() {
        search.runLoopedMaze();
        Maze maze = search.maze;
        
        BreadthfirstSearch findLoops = new BreadthfirstSearch(maze);
        findLoops.run();
        assertTrue(findLoops.loops);
    }
    
    @Test
    public void correctWallsAreRemoved() {
        ArrayList<Wall> walls = search.findSimpleMaze();
        search.removeWallsFromMaze(walls);
        
        Maze maze = search.maze;
        
        for (Wall wall : walls) {
            int x1 = wall.getCell1().getX();
            int y1 = wall.getCell1().getY();
            int x2 = wall.getCell2().getX();
            int y2 = wall.getCell2().getY();
            
            if (x1 < x2) {
                assertFalse(maze.getCell(x1, y1).getRightWall());
            }
            if (y1 < y2) {
                assertFalse(maze.getCell(x1, y1).getLowerWall());
            }
        }
    }
    
    
}
