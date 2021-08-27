package mazegeneration;

import maze.Cell;
import maze.Maze;
import mazesolving.BreadthfirstSearch;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class DepthfirstSearchTest {
    DepthfirstSearch search;
    
        
    @Before
    public void setUp() {
        int width = 50;
        int height = 50;
        
        this.search = new DepthfirstSearch(width, height);
        search.x = 0;
        search.y = 0;
    }
    
    
    @Test
    public void getRandomNumberGivesANumberInTheCorrectRange() {
        for (int i = 0; i < 20; i++) {
            int random = search.getRandomNumber();
            assertTrue(random < 4 && random >= 0);
        }
    }
    
    
    @Test
    public void moveUpWorksCorrectly() {
        assertFalse(search.moveUp());
        assertTrue(search.y == 0);
        search.y = 5;
        assertTrue(search.moveUp());
        assertTrue(search.y == 4);
        search.maze.getCell(0, 3).visit();
        assertFalse(search.moveUp());
        assertTrue(search.y == 4);
    }
    
    @Test
    public void moveLeftWorksCorrectly() {
        assertFalse(search.moveLeft());
        assertTrue(search.x == 0);
        search.x = 5;
        assertTrue(search.moveLeft());
        assertTrue(search.x == 4);
        search.maze.getCell(0, 3).visit();
        assertFalse(search.moveUp());
        assertTrue(search.x == 4);
    }
    
    @Test
    public void moveDownWorksCorrectly() {
        assertTrue(search.moveDown());
        assertTrue(search.y == 1);
        search.maze.getCell(0, 2).visit();
        assertFalse(search.moveDown());
        assertTrue(search.y == 1);
        search.y = 49;
        assertFalse(search.moveDown());
        assertTrue(search.y == 49);
    }
    
    @Test
    public void moveRightWorksCorrectly() {
        assertTrue(search.moveRight());
        assertTrue(search.x == 1);
        search.maze.getCell(2, 0).visit();
        assertFalse(search.moveRight());
        assertTrue(search.x == 1);
        search.x = 49;
        assertFalse(search.moveRight()); 
        assertTrue(search.x == 49);
    }
    
    @Test
    public void chooseDirectionStaysWithinTheMaze() {
        for (int i = 0; i < 5; i++) {
            search.chooseDirection();
            assertTrue(search.x >= 0 && search.x < 50);
            assertTrue(search.y >= 0 && search.y < 50);
        }
        
        search.x = 40;
        search.y = 40;
        for (int i = 0; i < 7; i++) {
            search.chooseDirection();
            assertTrue(search.x >= 0 && search.x < 50);
            assertTrue(search.y >= 0 && search.y < 50);
        }
    }
    
    
    @Test
    public void chooseDirectionChoosesFreeCell() {
        for (int i = 0; i < 5; i++) {
            search.chooseDirection();
            Maze maze = search.maze;
            Cell c = maze.getCell(search.x, search.y);
            int visited = c.numberOfVisits();
            assertTrue(visited == 0);
            c.visit();
        }
        
        search.x = 40;
        search.y = 40;
        for (int i = 0; i < 7; i++) {
            search.chooseDirection();
            Maze maze = search.maze;
            Cell c = maze.getCell(search.x, search.y);
            int visited = c.numberOfVisits();
            assertTrue(visited == 0);
            c.visit();
        }
    }
    
    @Test
    public void checkIfAllVisitedReturnsCorrectAnswer() {
        assertFalse(search.checkIfAllVisited());
        
        search.maze.getCell(1, 0).visit();
        assertFalse(search.checkIfAllVisited());
        
        search.maze.getCell(0, 1).visit();
        assertTrue(search.checkIfAllVisited());
        
        search.x = 2;
        search.y = 2;
        
        assertFalse(search.checkIfAllVisited());
        
        search.maze.getCell(1, 2).visit();
        assertFalse(search.checkIfAllVisited());
        
        search.maze.getCell(2, 1).visit();
        assertFalse(search.checkIfAllVisited());
        
        search.maze.getCell(3, 2).visit();
        assertFalse(search.checkIfAllVisited());
        
        search.maze.getCell(2, 3).visit();
        assertTrue(search.checkIfAllVisited());  
        
        search.x = 49;
        search.y = 49;
        
        assertFalse(search.checkIfAllVisited());
        
        search.maze.getCell(49, 48).visit();
        assertFalse(search.checkIfAllVisited());
        
        search.maze.getCell(48, 49).visit();
        assertTrue(search.checkIfAllVisited());
    }
    
    @Test
    public void searchVisitsAllCells() {
        search.generateRoutes();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                assertFalse(search.maze.getCell(i, j).numberOfVisits() == 0);
            }
        }
    }
    
    @Test
    public void searchGeneratesMazeWithoutLoops() {
        search.run();
        Maze maze = search.maze;
        
        BreadthfirstSearch findLoops = new BreadthfirstSearch(maze);
        findLoops.run();
        assertFalse(findLoops.loops);
    }
    
}
