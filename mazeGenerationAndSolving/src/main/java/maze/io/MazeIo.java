package maze.io;

import java.util.Scanner;

/** Print output and read input.
 *
 * @author julia
 */
public class MazeIo implements Io {

    private Scanner scanner = new Scanner(System.in);

    
    /** Print text.
     *
     * @param output the text to print
     */
    @Override
    public void print(String output) {
        System.out.println(output);
    }

    
    /** Read user input.
     *
     * @param input wanted input
     * @return user input
     */
    @Override
    public String read(String input) {
        System.out.println(input);
        return scanner.nextLine();
    }

}