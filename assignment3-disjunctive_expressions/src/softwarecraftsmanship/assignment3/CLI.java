package softwarecraftsmanship.assignment3;

import java.util.Optional;
import java.util.Scanner;

public class CLI {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        while(true) {
            String inputExpression = keyboard.nextLine();

            try {
                DisjunctiveLexer disjunctiveLexer = new DisjunctiveLexer(inputExpression);
                System.out.println();
                CompoundFactor compoundFactor = CompoundFactor.Builder.build(disjunctiveLexer.nextValid().get(),
                        disjunctiveLexer);
                System.out.println(compoundFactor.conjunctiveRepresentation().toString());
            } catch(ParserException parserException) {
                System.out.println("Error: not a valid boolean expression!");
            }
        }
    }
}
