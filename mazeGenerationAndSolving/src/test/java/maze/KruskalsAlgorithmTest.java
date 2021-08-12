package maze;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class KruskalsAlgorithmTest {
    KruskalsAlgorithm search;
    
        
    @Before
    public void setUp() {
        int width = 50;
        int height = 50;
        
        this.search = new KruskalsAlgorithm(width, height);
    }
    
    
}
