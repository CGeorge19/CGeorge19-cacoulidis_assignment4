import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class DuplicateCounter
{
    // instance variable to store word count
    private Map<String, Integer> wordCounter = new LinkedHashMap<>();

    // instance method count
    // counts the occurance of each word in the file
    public void count(String dataFile)
    {
        String buffer;
        try
        {
            // instantiate File with relative path given by dataFile
            // instantiate Scanner to read from the file
            File inputFile = new File(dataFile);
            Scanner inFile = new Scanner(inputFile);

            while(inFile.hasNext())
            {
                buffer = inFile.next();
                Integer freq = wordCounter.get(buffer);
                wordCounter.put(buffer, (freq == null) ? 1 : freq + 1);
            }
            inFile.close();
        }

        // deals w/ exceptions, prints stack trace and message to user, and exits program
        catch (IOException e)
        {
            System.err.println("\n\n\t\tOh no! Looks like you've got a problem..." +
                               "\n\t\tHere's some info to help!\t\t\n\n");
            e.printStackTrace();
            System.exit(1);
        }
    }

    // instance method write
    // write results to a file
    public void write(String outFile)
    {
        // try to instantiate the Formatter class pointing to outFile relative path
        try (Formatter output = new Formatter(outFile))
        {
            // some pretty formatting gets written to the file
            output.format("There are %d distinct words in your file.\n\n", wordCounter.size());
            output.format("[");

            // write each string and its frequency to the file
            for (Map.Entry<String, Integer> pair : wordCounter.entrySet())
            {
                output.format(" %s : %d  ", pair.getKey(), pair.getValue());
            }

            // some more nice formatting
            output.format("]");
            // immediately write things w/ flush
            output.flush();
        }

        // deals w/ exceptions, prints stack trace and message to user, and exits program
        catch (SecurityException | FileNotFoundException | FormatterClosedException e)
        {
            System.err.println("\n\n\t\tOh no! Looks like you've got a problem..." +
                               "\n\t\tHere's some info to help!\t\t\n\n");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
