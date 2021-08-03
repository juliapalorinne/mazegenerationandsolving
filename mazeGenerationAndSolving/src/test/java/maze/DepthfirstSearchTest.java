package maze;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
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
    public void moveDownWorksCorrectly() {
        assertTrue(search.moveDown() == true);        
        search.maze.getCell(0, 2).visit();
        assertTrue(search.moveDown() == false);        
    }
    
    @Test
    public void moveUpWorksCorrectly() {
        assertTrue(search.moveUp() == false);
        search.y = 5;
        assertTrue(search.moveUp() == true);
        search.maze.getCell(0, 3).visit();
        assertTrue(search.moveUp() == false);        
    }
    
    
    @Test
    public void chooseDirectionStaysWithinTheMaze() {
       for (int i = 0; i < 5; i++) {
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
    }
    
    @Test
    public void checkIfAllVisitedReturnsCorrectAnswer() {
        assertTrue(search.checkIfAllVisited() == false);
        
        search.maze.getCell(1, 0).visit();
        assertTrue(search.checkIfAllVisited() == false);
        
        search.maze.getCell(0, 1).visit();
        assertTrue(search.checkIfAllVisited() == true);
        
        search.x = 2;
        search.y = 2;
        
        assertTrue(search.checkIfAllVisited() == false);
        
        search.maze.getCell(1, 2).visit();
        assertTrue(search.checkIfAllVisited() == false);
        
        search.maze.getCell(2, 1).visit();
        assertTrue(search.checkIfAllVisited() == false);
        
        search.maze.getCell(3, 2).visit();
        assertTrue(search.checkIfAllVisited() == false);
        
        search.maze.getCell(2, 3).visit();
        assertTrue(search.checkIfAllVisited() == true);  
        
        search.x = 49;
        search.y = 49;
        
        assertTrue(search.checkIfAllVisited() == false);
        
        search.maze.getCell(49, 48).visit();
        assertTrue(search.checkIfAllVisited() == false);
        
        search.maze.getCell(48, 49).visit();
        assertTrue(search.checkIfAllVisited() == true);
    }
    
    
    
}
