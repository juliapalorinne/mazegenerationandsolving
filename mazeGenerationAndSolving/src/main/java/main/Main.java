package main;

import maze.DepthfirstSearch;


/**
 * Main class
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DepthfirstSearch search = new DepthfirstSearch(20, 20);
        search.run();

        
        
    }
    
}
