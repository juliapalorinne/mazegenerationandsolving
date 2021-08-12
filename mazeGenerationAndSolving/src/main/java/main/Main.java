package main;

import maze.DepthfirstSearch;
import maze.Maze;
import maze.WallFollower;


/**
 * Main class
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DepthfirstSearch search = new DepthfirstSearch(20, 10);
        search.run();

        Maze maze = search.maze;
        maze.printMaze();
        System.out.println("");
        
        WallFollower follower = new WallFollower(maze);
        follower.run();
        
        // maze.printMaze();
    }
    
}
