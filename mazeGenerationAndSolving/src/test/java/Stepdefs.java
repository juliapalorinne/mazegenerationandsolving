import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import main.*;
import maze.*;
import mazeGeneration.*;
import mazeSolving.*;

public class Stepdefs {
    DepthfirstSearch depthfirst;
    KruskalsAlgorithm simpleKruskal;
    KruskalsAlgorithm loopedKruskal;
    
    // StubIo io;
        
    @Before
    public void setup(){
        depthfirst = new DepthfirstSearch(50, 50);
        simpleKruskal = new KruskalsAlgorithm(50, 50);
        loopedKruskal = new KruskalsAlgorithm(50, 50);
        
    }
    
    @Given("command printMaze is given")
    public void commandPrintMazeIsGiven() {
        
    }
   
    @When("maze is printed")
    public void mazeIsPrinted() {
        
    }
      
    @Then("system will respond with {string}")
    public void mazeIsPrintedCorrectly(String expectedOutput) {
        //assertTrue(io.getPrints().contains(expectedOutput));
    }
    
    @Then("system's response contains {string}")
    public void responseContains(String expectedOutput) {
        // boolean contains = false;
        // for (String s : io.getPrints()) {
        //     if (s.contains(expectedOutput)) {
        //         contains = true;
        //     }
        // }
        // assertTrue(contains);
    }
}