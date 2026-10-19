package tech.eta.dataloader_d.commandline.CLI_development;

import java.util.ArrayList;
import java.util.List;

public enum dummyCommand_G {
    A(){protected List<dummyCommand_G> definedChildren() {return List.of(A_a,A_b);}},
    A_a(){protected List<dummyCommand_G> definedChildren() {return List.of(A_b);}},
    A_b(){protected List<dummyCommand_G> definedChildren() {return List.of(A_a);}},

    B(),
    B_a(B){protected List<dummyCommand_G> definedChildren() {return List.of(B_b);}},
    B_b(B){protected List<dummyCommand_G> definedChildren() {return List.of(B_a);}},

    C(){protected List<dummyCommand_G> definedChildren() {return List.of(C_b);}},
    C_a(C),
    C_b()
    ;

    private List<dummyCommand_G> definedChildren() {return List.of();}
    private final List<dummyCommand_G> subCommands = new ArrayList<>();
    private volatile boolean cachedSubCommands=false;
    public List<dummyCommand_G> getSubCommands(){
        if (!this.cachedSubCommands) {
            subCommands.addAll(definedChildren());
            cachedSubCommands = true;
        }
        return subCommands;
    };


    dummyCommand_G(dummyCommand_G... parents) {
        for (dummyCommand_G parent : parents) {
            if (parent.hasChildrenOverride()) {
                throw new IllegalArgumentException(parent + " and " + this + " defines children both ways");
            }
            parent.subCommands.add(this);
        }
    }

    private boolean hasChildrenOverride() {
        try {
            return getClass().getDeclaredMethod("definedChildren").getDeclaringClass() != dummyCommand_G.class;
//            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
