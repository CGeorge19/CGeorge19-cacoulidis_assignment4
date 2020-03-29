import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class DuplicateRemover
{
    // instance variable to store unique words
    private Set<String> uniqueWords = new LinkedHashSet<>();

    // instance method remove
    // remove duplicates from dataFile
    public void remove(String dataFile)
    {
        try
        {
            // instantiate File with relative path given by dataFile
            // instantiate Scanner to read from the file
            File inputFile = new File(dataFile);
            Scanner inFile = new Scanner(inputFile);

            // as long is there is more stuff in the file add it to uniqueWords
            while (inFile.hasNext())
            {
                // force toLower and take out punctuation
                uniqueWords.add(inFile.next().toLowerCase().replaceAll("(?U)[^\\p{Alnum}]"," "));
            }

            // close Scanner
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
    public void write(String outputFile)
    {
        // try to instantiate the Formatter class pointing to outFile relative path
        try (Formatter output = new Formatter(outputFile))
        {
            // some pretty formatting gets written to the file
            output.format("The unique words in your file are: \n\n");
            output.format("[");

            // write each string value in the Set to the file
            for (String value : uniqueWords)
            {
                output.format(" %s,", value);
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
