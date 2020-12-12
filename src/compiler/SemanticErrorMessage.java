package compiler;

public class SemanticErrorMessage {
    public static String buildMessage(int line, int collumn, String message) {
        return "Error at line : " + Integer.toString(line) +  "\n     " + message;
    }

}
