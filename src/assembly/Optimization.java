package assembly;

import quadCode.syntax.Block;

public abstract class Optimization {
    public abstract QuadCode optimize(QuadCode quadCode);
    public abstract boolean changeDetected();
}
