package quadCode.syntax.jumps;

import latte.Absyn.EApp;
import quadCode.syntax.Block;

import java.util.List;

public class CallJump extends BlockJump {
    EApp call;
    @Override
    public List<Block> allNextBlocks() {
        return null;
    }
}
