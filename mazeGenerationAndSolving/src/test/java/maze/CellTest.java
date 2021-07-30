package maze;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class CellTest {
    Cell cell1;
    Cell cell2;
    Cell cell3;
    Cell cell4;
    
        
    @Before
    public void setUp() {
        this.cell1 = new Cell(0, 0);
        this.cell2 = new Cell(0, 0);
        this.cell3 = new Cell(0, 1);
        this.cell4 = new Cell(1, 1);
    }
    
    
    @Test
    public void getCoordinatesGivesCorrectCoordinates() {
        int [] c = cell3.getCoordinates();
        
        assertTrue(c[0] == 0 && c[1] == 1);
    }
    
    
    @Test
    public void removeUpperWallRemovesCorrectWall() {
        cell1.removeUpperWall();
        assertTrue(cell1.getUpperWall() == false);
    }
    
    @Test
    public void removeLeftWallRemovesCorrectWall() {
        cell1.removeLeftWall();
        assertTrue(cell1.getLeftWall() == false);
    }
    
    @Test
    public void removeLowerWallRemovesCorrectWall() {
        cell1.removeLowerWall();
        assertTrue(cell1.getLowerWall() == false);
    }
    
    @Test
    public void removeRightWallRemovesCorrectWall() {
        cell1.removeRightWall();
        assertTrue(cell1.getRightWall() == false);
    }
    
    
}
