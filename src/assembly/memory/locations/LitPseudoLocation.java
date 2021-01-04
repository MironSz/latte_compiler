package assembly.memory.locations;

import assembly.memory.MemoryLocation;

public class LitPseudoLocation extends MemoryLocation {
    String lit;

    public LitPseudoLocation(String lit) {
        this.lit = lit;
    }


    @Override
    public String assemblyCode() {
        return lit;
    }
}
