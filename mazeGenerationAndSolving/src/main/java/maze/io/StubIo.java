package maze.io;

import java.util.ArrayList;
import java.util.List;


/** Prints output and receives input from tests.
 *
 * @author julia
 */
public class StubIo implements Io {

    private List<String> lines;
    private int iterator;
    private ArrayList<String> prints;

    
    public StubIo(List<String> values) {
        this.lines = values;
        prints = new ArrayList<>();
    }
    
    
    /** Save printed text.
     *
     * @param output the text to print
     */
    @Override
    public void print(String output) {
        prints.add(output);
    }

    
    /** Get printed text.
     *
     * @return the prints
     */
    public ArrayList<String> getPrints() {
        return prints;
    }
    

    /** Read user input.
     *
     * @param input wanted input
     * @return user input
     */
    @Override
    public String read(String input) {
        if (iterator < lines.size()) {
            return lines.get(iterator++);
        }
        return "";
    }

}
