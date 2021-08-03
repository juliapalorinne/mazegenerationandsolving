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
        
        DepthfirstSearch search = new DepthfirstSearch(5, 5);
        search.run();
        
//        int width = 10;
//        int height = 5;
//        
//        Cell[][] cells = new Cell[height][width];
//        
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                Cell cell = new Cell(i, j);
//                cells[j][i] = cell;
//            }
//        }
//        
//        Maze maze = new Maze(cells);
//        
//        maze.printMaze();
        
        
    }
    
}
