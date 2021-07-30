package mazeGenerationAndSolving;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CellTest {
    Cell cell1;
    Cell cell2;
    Cell cell3;
    Cell cell4;
    
        
    @Before
    public void setUp() {
        this.cell1 = new Cell(0, 0);
        this.cell2 = new Cell(0, 0);
        this.cell3 = new Cell(1, 0);
        this.cell4 = new Cell(0, 1);
    }
    
    
    @Test
    public void equalWhenSameCoordinates() {
        assertTrue(cell1.equals(cell2));
    }
    
    @Test
    public void nonEqualWhenDifferentXcoordinate() {
        assertFalse(cell1.equals(cell3));
    } 
    
    
 }
