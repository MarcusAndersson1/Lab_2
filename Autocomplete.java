
import java.util.Arrays;

public class Autocomplete {
    private Term[] dictionary;

    // Initializes the dictionary from the given array of terms.
    public Autocomplete(Term[] dictionary) {
        this.dictionary = dictionary;
        sortDictionary();
    }

    // Sorts the dictionary in *case-insensitive* lexicographic order.
    // Complexity: O(N log N) where N is the number of dictionary terms
    private void sortDictionary() {
        Arrays.sort(dictionary, Term.byLexicographicOrder);

        //for (Term term:dictionary){System.out.println(term);}
        // TODO
       // throw new UnsupportedOperationException();
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    // Precondition: the internal dictionary is in lexicographic order.
    // Complexity: O(log N + M log M) where M is the number of matching terms
    public Term[] allMatches(String prefix) {
        Term [] allMatchesArray = new Term [numberOfMatches(prefix)];

        int MatchingValues = 0;
        Term term = new Term(prefix,  -1);
        if(allMatchesArray.length>0) {
            for (int i = RangeBinarySearch.firstIndexOf(dictionary, term, Term.byPrefixOrder(prefix.length()));
                 // 1 added for one iteration when there is only on valid match firstIndex = lastIndex
                 i < RangeBinarySearch.lastIndexOf(dictionary, term, Term.byPrefixOrder(prefix.length())) + 1 ; i++) {
                allMatchesArray[MatchingValues] = dictionary[i];
                MatchingValues++;

            }
        }
            //removed reversed
        Arrays.sort(allMatchesArray, Term.byReverseWeightOrder);
        //for (Term termA : allMatchesArray){System.out.println(termA);}
        return allMatchesArray;
        // TODO
        // throw new UnsupportedOperationException();
    }

    // Returns the number of terms that start with the given prefix.
    // Precondition: the internal dictionary is in lexicographic order.
    // Complexity: O(log N) where N is the number of dictionary terms
    public int numberOfMatches(String prefix) {
        Term term = new Term(prefix, -1);
        int first = RangeBinarySearch.firstIndexOf(dictionary, term, Term.byPrefixOrder(prefix.length()));

        // added + one to get the last element
       int last = RangeBinarySearch.lastIndexOf(dictionary, term, Term.byPrefixOrder(prefix.length())) + 1;
       if(first != -1 && last != 0){ // only return when there is a match
           return last-first;

       }else{
           // return 0 when there is no match
           return 0;
       }
        // TODO
       // throw new UnsupportedOperationException();
    }

}
