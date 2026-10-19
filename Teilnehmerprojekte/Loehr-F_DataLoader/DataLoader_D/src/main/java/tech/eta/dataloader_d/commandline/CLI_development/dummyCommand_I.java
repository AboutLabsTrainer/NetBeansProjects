package tech.eta.dataloader_d.commandline.CLI_development;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Approach using a hashmap for caching, storing only at first use...
public enum dummyCommand_I {
    A(){protected List<dummyCommand_I> mySubs() {return List.of(A_a,A_b);}},
    A_a(){protected List<dummyCommand_I> mySubs() {return List.of(A_b);}},
    A_b(){protected List<dummyCommand_I> mySubs() {return List.of(A_a);}},

    B(),
    B_a(B){protected List<dummyCommand_I> mySubs() {return List.of(B_b);}},
    B_b(B){protected List<dummyCommand_I> mySubs() {return List.of(B_a);}},

    C(){protected List<dummyCommand_I> mySubs() {return List.of(C_b);}},
//    C_a(C),
    C_b()
    ;

    // Where the subcommands are stored. overriding mySubs allows for cyclic graphs.
    protected List<dummyCommand_I> mySubs() {return List.of();}
    private final List<dummyCommand_I> subCommands = new ArrayList<>();
    // the actual cache.
    private static final Map<dummyCommand_I, List<dummyCommand_I>> subCommandsMap = new ConcurrentHashMap<>();
    // default getter. Override for dynamic lists. If overridden you must take care of your non-dynamic values yourself.
    public List<dummyCommand_I> getSubCommands(){
        return subCommandsMap.computeIfAbsent(
            this,
            k -> {subCommands.addAll(mySubs()); return Collections.unmodifiableList(subCommands);}
        );
    };


    dummyCommand_I(dummyCommand_I... parents) {
        for (dummyCommand_I parent : parents) {
            if (parent.declaresOrOverridesMethod("mySubs")) {
                throw new IllegalArgumentException(parent + " and " + this + " defines children both ways.\n" +
                        "Please choose setting the parents if you favor simplicity, \n" +
                        "overriding protected mySubs method for circular dependencies, \n" +
                        "and overriding pubic getSubCommands for dynamic uncashed lists.");
            }
            parent.subCommands.add(this);
        }
    }

    private boolean declaresOrOverridesMethod(String methodName) {
        try {
            return getClass().getDeclaredMethod(methodName).getDeclaringClass() != dummyCommand_I.class;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        for (dummyCommand_I command : dummyCommand_I.values()) {
            System.out.println(command + " => " + command.getSubCommands());
            System.out.println(command + " => " + command.getSubCommands());
        }
    }
}
