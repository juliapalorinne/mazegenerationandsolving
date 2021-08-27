package main;

import maze.io.Io;
import maze.io.MazeIo;
import maze.ui.MazeUi;


/** Main class.
 * Run maze generation and maze solving algorithms
 */
public class Main {

    
    /** Start the program and launch user interface.
     *
     * @param args the command line arguments
     * @throws java.lang.Exception exception
     */
    public static void main(String[] args) throws Exception {
        Io io = new MazeIo();
        MazeUi ui = new MazeUi(io);
        ui.start();

    }
    
}
