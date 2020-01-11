import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class tokenizeexpression
{
    public static void main (String []arg) {
        String input = "123. 45 + 67*2-89*3/(12+45)";

        String input2 = "Happy Birth day to you! happy birdth day to you";

        try {
            List<String> tokens = new ArrayList<>();
            tokens = utilityexpression.tokenize(input);
            System.out.println(tokens);

            tokens = utilityexpression.tokenize(input2);
            System.out.println(tokens);
        }
        catch(Exception e)
        {
            System.out.println("exception");
        }

    }

}

class  utilityexpression{
    public static List<String> tokenize(String s) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(s));
        tokenizer.ordinaryChar('-');  // Don't parse minus as part of numbers.
        tokenizer.ordinaryChar('/');  // Don't treat slash as a comment start.
        List<String> tokenBuffer = new ArrayList<String>();

        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            switch(tokenizer.ttype) {
                case StreamTokenizer.TT_NUMBER:
                    tokenBuffer.add(String.valueOf(tokenizer.nval));
                    break;
                case StreamTokenizer.TT_WORD:
                    tokenBuffer.add(tokenizer.sval);
                    break;
                default:  // operator
                    tokenBuffer.add(String.valueOf((char) tokenizer.ttype));
            }
        }
        return tokenBuffer;
    }
}
