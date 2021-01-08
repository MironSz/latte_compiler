package assembly.memory.locations;

import assembly.memory.MemoryLocation;

import java.util.Objects;

public class LitPseudoLocation extends MemoryLocation {
    String lit;

    boolean stringConst = false;

    public LitPseudoLocation(String lit) {
        this.lit = lit;
    }

    public LitPseudoLocation(String lit, boolean stringConst) {
        this(lit);
        this.stringConst = stringConst;
    }


    @Override
    public String assemblyCode() {
        return lit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LitPseudoLocation that = (LitPseudoLocation) o;
        return Objects.equals(lit, that.lit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lit);
    }
}
