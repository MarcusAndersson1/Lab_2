
import java.util.Comparator;

public class RangeBinarySearch {

    // Returns the index of the *first* element in `a` that equals the search key,
    // according to the given comparator, or -1 if there is no matching element.
    // Precondition: `a` is sorted according to the given comparator.
    // Complexity: O(log N) comparisons where N is the length of `a`
    public static<T> int firstIndexOf(T[] a, T key, Comparator<T> comparator) {
        //Arrays.sort(a,comparator);
        int lo = 0;
        int hi = a.length-1;
        int firstValue = -1;
        while (lo <= hi){
            int mid = lo+(hi-lo)/2;

            if (comparator.compare(key, a[mid]) == 0){
                firstValue = mid;
                hi = mid-1;
            }else if (comparator.compare(a[mid], key) > 0){
                hi = mid-1;
            }else if (comparator.compare(a[mid], key) < 0){
                lo = mid+1;
            }
        }
        //System.out.println("first value " + firstValue);
            return firstValue;

        // TODO
      //  throw new UnsupportedOperationException();
    }

    // Returns the index of the *last* element in `a` that equals the search key,
    // according to the given comparator, or -1 if there are is matching element.
    // Precondition: `a` is sorted according to the given comparator.
    // Complexity: O(log N) comparisons where N is the length of `a`
    public static<T> int lastIndexOf(T[] a, T key, Comparator<T> comparator) {
        int lo = 0;
        int hi = a.length-1;
        int lastValue = -1;
        while (lo <= hi){
            int mid = (lo+hi)/2;

            if (comparator.compare(key, a[mid]) == 0){
                lastValue =  mid;
                lo = mid+1;
            }else if (comparator.compare(a[mid], key) > 0){
                hi = mid-1;
            }else if (comparator.compare(a[mid], key) < 0){
                lo = mid+1;
            }
        }
        //System.out.println("last value " + lastValue);
        return lastValue;
        // TODO
      //  throw new UnsupportedOperationException();
    }

}
