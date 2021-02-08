
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

class AutocompleteCLI {
    public static void main(String[] args) {

       /*
        Simple test for revers weight order
        Term a = new Term("spam", 0);
        Term b = new Term("ham", 1);
        System.out.println(Term.byReverseWeightOrder.compare(a,b));
        System.exit(0);
        */

        // If not enough program arguments are given, display the usage.

        if (args.length < 2) {
            System.err.println("Usage: you have to provide two program arguments:");
            System.err.println("  (1) the path to a dictionary file");
            System.err.println("  (2) the maximum number of matches that will be displayed");
            System.exit(1);
        }
        Term[] dictionary = null;
        int max_matches = 0;
        String[] prefixes = null;

        // Load dictionary file specified in first program argument.
        String dictfile = args[0];
        try {
            dictionary = Files.lines(Paths.get(dictfile))
                    .map(line -> line.trim().split("\\s+"))
                    .map(pair -> new Term(pair[1], Long.valueOf(pair[0])))
                    .toArray(size -> new Term[size]);
        } catch (Exception e) {
            System.err.println("I failed to read the dictionary file.");
            e.printStackTrace();
            System.exit(1);
        }

        // Parse maximum number of matches specified in second program argument.
        try {
            max_matches = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.err.println("I failed to parse the maximum number of matches to display.");
            e.printStackTrace();
            System.exit(1);
        }

        // Print some help.
        System.out.println("Loaded dictionary " + dictfile + " containing " + dictionary.length + " words");
        System.out.println("Maximum number of matches to display: " + max_matches);

        Autocomplete autocomplete = new Autocomplete(dictionary);

        // The main REPL (read-eval-print loop)
        System.out.println("Enter search prefixes line-by-line.");
        System.out.println();
        Scanner input = new Scanner(System.in);
        while (true) {
            // Read prefix from input line, exit if there is no more input.
            if (!input.hasNextLine())
                break;
            String prefix = input.nextLine();

            // Print the number of matches, find all matches, and print the top-most ones.
            int nrMatches = autocomplete.numberOfMatches(prefix);
            System.out.println("Number of matches for prefix " + prefix + ": " + nrMatches);
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(max_matches, results.length); i++)
                System.out.println(results[i]);
            System.out.println();
        }
    }
}
