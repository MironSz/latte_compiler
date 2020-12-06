//----------------------------------------------------
// The following code was generated by CUP v0.10k
// Sun Jul 25 13:36:02 EDT 1999
//----------------------------------------------------

package java_cup.simple_calc;

/**
 * CUP v0.10k generated parser.
 *
 * @version Sun Jul 25 13:36:02 EDT 1999
 */
public class parser extends java_cup.runtime.lr_parser {

    /**
     * Default constructor.
     */
    public parser() {
        super();
    }

    /**
     * Constructor which sets the default scanner.
     */
    public parser(java_cup.runtime.Scanner s) {
        super(s);
    }

    /**
     * Production table.
     */
    protected static final short[][] _production_table =
            unpackFromStrings(new String[]{
                    "\000\015\000\002\003\004\000\002\002\004\000\002\003" +
                            "\003\000\002\006\002\000\002\004\005\000\002\005\005" +
                            "\000\002\005\005\000\002\005\005\000\002\005\005\000" +
                            "\002\005\005\000\002\005\003\000\002\005\004\000\002" +
                            "\005\005"});

    /**
     * Access to production table.
     */
    public short[][] production_table() {
        return _production_table;
    }

    /**
     * Parse-action table.
     */
    protected static final short[][] _action_table =
            unpackFromStrings(new String[]{
                    "\000\030\000\010\006\004\013\011\015\005\001\002\000" +
                            "\010\006\004\013\011\015\005\001\002\000\020\004\ufff7" +
                            "\005\ufff7\006\ufff7\007\ufff7\010\ufff7\011\ufff7\014\ufff7\001" +
                            "\002\000\012\002\uffff\006\uffff\013\uffff\015\uffff\001\002" +
                            "\000\016\004\ufffe\005\016\006\014\007\020\010\017\011" +
                            "\013\001\002\000\012\002\027\006\004\013\011\015\005" +
                            "\001\002\000\010\006\004\013\011\015\005\001\002\000" +
                            "\016\005\016\006\014\007\020\010\017\011\013\014\015" +
                            "\001\002\000\010\006\004\013\011\015\005\001\002\000" +
                            "\010\006\004\013\011\015\005\001\002\000\020\004\ufff5" +
                            "\005\ufff5\006\ufff5\007\ufff5\010\ufff5\011\ufff5\014\ufff5\001" +
                            "\002\000\010\006\004\013\011\015\005\001\002\000\010" +
                            "\006\004\013\011\015\005\001\002\000\010\006\004\013" +
                            "\011\015\005\001\002\000\020\004\ufffa\005\ufffa\006\ufffa" +
                            "\007\ufffa\010\ufffa\011\ufffa\014\ufffa\001\002\000\020\004" +
                            "\ufff9\005\ufff9\006\ufff9\007\ufff9\010\ufff9\011\ufff9\014\ufff9" +
                            "\001\002\000\020\004\ufffc\005\ufffc\006\ufffc\007\020\010" +
                            "\017\011\013\014\ufffc\001\002\000\020\004\ufffb\005\ufffb" +
                            "\006\ufffb\007\020\010\017\011\013\014\ufffb\001\002\000" +
                            "\020\004\ufff8\005\ufff8\006\ufff8\007\ufff8\010\ufff8\011\ufff8" +
                            "\014\ufff8\001\002\000\012\002\001\006\001\013\001\015" +
                            "\001\001\002\000\004\002\000\001\002\000\004\004\031" +
                            "\001\002\000\012\002\ufffd\006\ufffd\013\ufffd\015\ufffd\001" +
                            "\002\000\020\004\ufff6\005\ufff6\006\ufff6\007\ufff6\010\ufff6" +
                            "\011\ufff6\014\ufff6\001\002"});

    /**
     * Access to parse-action table.
     */
    public short[][] action_table() {
        return _action_table;
    }

    /**
     * <code>reduce_goto</code> table.
     */
    protected static final short[][] _reduce_table =
            unpackFromStrings(new String[]{
                    "\000\030\000\010\003\007\004\005\005\006\001\001\000" +
                            "\004\005\031\001\001\000\002\001\001\000\002\001\001" +
                            "\000\004\006\027\001\001\000\006\004\025\005\006\001" +
                            "\001\000\004\005\011\001\001\000\002\001\001\000\004" +
                            "\005\024\001\001\000\004\005\023\001\001\000\002\001" +
                            "\001\000\004\005\022\001\001\000\004\005\021\001\001" +
                            "\000\004\005\020\001\001\000\002\001\001\000\002\001" +
                            "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
                            "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
                            "\002\001\001\000\002\001\001"});

    /**
     * Access to <code>reduce_goto</code> table.
     */
    public short[][] reduce_table() {
        return _reduce_table;
    }

    /**
     * Instance of action encapsulation class.
     */
    protected CUP$parser$actions action_obj;

    /**
     * Action encapsulation object initializer.
     */
    protected void init_actions() {
        action_obj = new CUP$parser$actions(this);
    }

    /**
     * Invoke a user supplied parse action.
     */
    public java_cup.runtime.Symbol do_action(
            int act_num,
            java_cup.runtime.lr_parser parser,
            java.util.Stack stack,
            int top)
            throws java.lang.Exception {
        /* call code in generated class */
        return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
    }

    /**
     * Indicates start state.
     */
    public int start_state() {
        return 0;
    }

    /**
     * Indicates start production.
     */
    public int start_production() {
        return 1;
    }

    /**
     * <code>EOF</code> Symbol index.
     */
    public int EOF_sym() {
        return 0;
    }

    /**
     * <code>error</code> Symbol index.
     */
    public int error_sym() {
        return 1;
    }

}

/**
 * Cup generated class to encapsulate user supplied action code.
 */
class CUP$parser$actions {
    private final parser parser;

    /**
     * Constructor
     */
    CUP$parser$actions(parser parser) {
        this.parser = parser;
    }

    /**
     * Method with the actual generated action code.
     */
    public final java_cup.runtime.Symbol CUP$parser$do_action(
            int CUP$parser$act_num,
            java_cup.runtime.lr_parser CUP$parser$parser,
            java.util.Stack CUP$parser$stack,
            int CUP$parser$top)
            throws java.lang.Exception {
        /* Symbol object for return from actions */
        java_cup.runtime.Symbol CUP$parser$result;

        /* select the action based on the action number */
        switch (CUP$parser$act_num) {
            /*. . . . . . . . . . . . . . . . . . . .*/
            case 12: // expr ::= LPAREN expr RPAREN
            {
                Integer RESULT = null;
                int eleft = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 1)).left;
                int eright = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 1)).right;
                Integer e = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 1)).value;
                RESULT = e;
                CUP$parser$result = new java_cup.runtime.Symbol(3/*expr*/, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).left, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, RESULT);
            }
            return CUP$parser$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 11: // expr ::= MINUS expr
            {
                Integer RESULT = null;
                int eleft = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).left;
                int eright = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right;
                Integer e = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).value;
                RESULT = new Integer(0 - e.intValue());
                CUP$parser$result = new java_cup.runtime.Symbol(3/*expr*/, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 1)).left, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, RESULT);
            }
            return CUP$parser$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 10: // expr ::= NUMBER
            {
                Integer RESULT = null;
                int nleft = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).left;
                int nright = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right;
                Integer n = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).value;
                RESULT = n;
                CUP$parser$result = new java_cup.runtime.Symbol(3/*expr*/, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).left, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, RESULT);
            }
            return CUP$parser$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 9: // expr ::= expr MOD expr
            {
                Integer RESULT = null;
                int e1left = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).left;
                int e1right = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).right;
                Integer e1 = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).value;
                int e2left = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).left;
                int e2right = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right;
                Integer e2 = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).value;
                RESULT = new Integer(e1.intValue() % e2.intValue());
                CUP$parser$result = new java_cup.runtime.Symbol(3/*expr*/, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).left, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, RESULT);
            }
            return CUP$parser$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 8: // expr ::= expr DIVIDE expr
            {
                Integer RESULT = null;
                int e1left = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).left;
                int e1right = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).right;
                Integer e1 = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).value;
                int e2left = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).left;
                int e2right = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right;
                Integer e2 = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).value;
                RESULT = new Integer(e1.intValue() / e2.intValue());
                CUP$parser$result = new java_cup.runtime.Symbol(3/*expr*/, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).left, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, RESULT);
            }
            return CUP$parser$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 7: // expr ::= expr TIMES expr
            {
                Integer RESULT = null;
                int e1left = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).left;
                int e1right = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).right;
                Integer e1 = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).value;
                int e2left = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).left;
                int e2right = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right;
                Integer e2 = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).value;
                RESULT = new Integer(e1.intValue() * e2.intValue());
                CUP$parser$result = new java_cup.runtime.Symbol(3/*expr*/, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).left, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, RESULT);
            }
            return CUP$parser$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 6: // expr ::= expr MINUS expr
            {
                Integer RESULT = null;
                int e1left = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).left;
                int e1right = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).right;
                Integer e1 = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).value;
                int e2left = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).left;
                int e2right = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right;
                Integer e2 = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).value;
                RESULT = new Integer(e1.intValue() - e2.intValue());
                CUP$parser$result = new java_cup.runtime.Symbol(3/*expr*/, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).left, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, RESULT);
            }
            return CUP$parser$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 5: // expr ::= expr PLUS expr
            {
                Integer RESULT = null;
                int e1left = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).left;
                int e1right = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).right;
                Integer e1 = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).value;
                int e2left = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).left;
                int e2right = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right;
                Integer e2 = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).value;
                RESULT = new Integer(e1.intValue() + e2.intValue());
                CUP$parser$result = new java_cup.runtime.Symbol(3/*expr*/, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).left, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, RESULT);
            }
            return CUP$parser$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 4: // expr_part ::= expr NT$0 SEMI
            {
                Object RESULT = null;
                // propagate RESULT from NT$0
                if (((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 1)).value != null)
                    RESULT = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 1)).value;
                int eleft = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).left;
                int eright = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).right;
                Integer e = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).value;

                CUP$parser$result = new java_cup.runtime.Symbol(2/*expr_part*/, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 2)).left, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, RESULT);
            }
            return CUP$parser$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 3: // NT$0 ::=
            {
                Object RESULT = null;
                int eleft = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).left;
                int eright = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right;
                Integer e = (Integer) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).value;
                System.out.println("= " + e);
                CUP$parser$result = new java_cup.runtime.Symbol(4/*NT$0*/, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, RESULT);
            }
            return CUP$parser$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 2: // expr_list ::= expr_part
            {
                Object RESULT = null;

                CUP$parser$result = new java_cup.runtime.Symbol(1/*expr_list*/, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).left, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, RESULT);
            }
            return CUP$parser$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 1: // $START ::= expr_list EOF
            {
                Object RESULT = null;
                int start_valleft = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 1)).left;
                int start_valright = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 1)).right;
                Object start_val = ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 1)).value;
                RESULT = start_val;
                CUP$parser$result = new java_cup.runtime.Symbol(0/*$START*/, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 1)).left, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, RESULT);
            }
            /* ACCEPT */
            CUP$parser$parser.done_parsing();
            return CUP$parser$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 0: // expr_list ::= expr_list expr_part
            {
                Object RESULT = null;

                CUP$parser$result = new java_cup.runtime.Symbol(1/*expr_list*/, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 1)).left, ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top - 0)).right, RESULT);
            }
            return CUP$parser$result;

            /* . . . . . .*/
            default:
                throw new Exception(
                        "Invalid action number found in internal parse table");

        }
    }
}

