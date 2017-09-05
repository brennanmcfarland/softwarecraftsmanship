import java.util.*;

public class Assignment1
{

  //given two lists, return the longest common prefix, empty if either is null
  static <T>
  List<T> longestPrefix(List<T> a, List<T> b, Comparator<? super T> cmp)
  {
    //test for null lists
    if(a == null)
      return b == null ? new ArrayList<T>() : b.subList(0,0);
    if(b == null)
      return a == null ? new ArrayList<T>() : a.subList(0,0);

    //iterate until the elements are different, then return that portion
    int commonPrefixLength = 0;
    Iterator<T> a_elems = a.iterator();
    Iterator<T> b_elems = b.iterator();
    while(a_elems.hasNext() && b_elems.hasNext())
    {
      if(cmp.compare(a_elems.next(), b_elems.next()) == 0)
        commonPrefixLength++;
      else
        break;
    }
    return a.subList(0,commonPrefixLength);
  }

  //run and test different cases for longestPrefix method
  public static void main(String []args)
  {
    System.out.println("Test returned " + testLongestPrefix(
      Arrays.asList(1,2,3,4),
      Arrays.asList(1,2,4,3),
      Arrays.asList(1,2),
      Comparator.<Integer>naturalOrder()
    ));
    System.out.println("Test returned " + testLongestPrefix(
      Arrays.asList(3.5,6.4,0.0,3.9),
      Arrays.asList(3.5,6.4,0.0,3.9),
      Arrays.asList(3.5,6.4,0.0,3.9),
      Comparator.<Double>naturalOrder()
      ));
    System.out.println("Test returned " + testLongestPrefix(
      Arrays.asList("blip", "bleep"),
      Arrays.asList("bloop, blop"),
      Arrays.asList(),
      Comparator.<String>naturalOrder()
      ));
    System.out.println("Test returned " + testLongestPrefix(
      null,
      Arrays.asList(2,5,3,2),
      Arrays.asList(),
      Comparator.<Integer>naturalOrder()
      ));
  }

  //run a test case on the longestPrefix method
  private static <T> boolean testLongestPrefix(
    List<T> a, List<T> b, List<T> correctOutput,Comparator<T> cmp)
  {
    List<T> output = longestPrefix(a, b, cmp);
    System.out.println(output.toString());
    return (output.equals(correctOutput));
  }
}
