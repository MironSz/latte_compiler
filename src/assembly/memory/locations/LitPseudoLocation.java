package assembly.memory.locations;

import assembly.memory.MemoryLocation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LitPseudoLocation extends MemoryLocation {
    private static Set<String> stringSet = new HashSet<>();
    String lit;


    public LitPseudoLocation(String lit) {
        this.lit = lit;
    }

    public LitPseudoLocation(String lit, boolean stringConst) {
        this(lit);
        if (stringConst)
            stringSet.add(lit);
    }


    public boolean isConstString() {
        return stringSet.contains(lit);
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
