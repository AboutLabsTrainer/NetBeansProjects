package tech.eta.dataloader_d.commandline.CLI_development;

import lombok.Getter;

import java.util.*;

public enum dummyCommand_K {
    A(){@Override public List<dummyCommand_K> getSubCommands() {return List.of(A_a, A_b);}},
    A_a(List.of("A_b"), A),
    A_b(){public List<String> getSubCommandNames() {return List.of("A_a");}},

//    B(),
//    B_a(B){protected List<dummyCommand_K> mySubs() {return List.of(B_b);}},
//    B_b(B){protected List<dummyCommand_K> mySubs() {return List.of(B_a);}},
//
//    C(){protected List<dummyCommand_K> mySubs() {return List.of(C_b);}},
    C(List.of("C_b")),
//    C_a(C),
    C_b()
    ;
    private static void throwAmbiguousRelation(dummyCommand_K parent) {
        throwAmbiguousRelation(parent, parent.subCommands, "getSubCommands()");
    }
    private static void throwAmbiguousRelation(Object parent, List<?> children, String getterName) {
        throw new IllegalArgumentException("""
            Bidirectional definition of relationship: Declared by both parent and children.
            To avoid ambiguity, maintain a single definition pattern per parent:
              - Child-driven (recommended) via varargs: CHILD (..., PARENTs)
              - Parent-driven (if cyclic)  via strings: PARENT(..., childNames, ...)
            For dynamic/runtime lists, override '%s'.

            Context:
              Parent:   %s
              Children: %s
            """.formatted(getterName, parent, children));
    }

    static {
        // Validation and static linking of command graph
        for (dummyCommand_K command : dummyCommand_K.values()) {
            // Mixing both definition styles is bad practice and prone to subtle bugs.
            // Restricting to one approach per parent command also keeps the implementation straightforward.
            if (!command.subCommands.isEmpty() && !command.subCommandNames.isEmpty()) throwAmbiguousRelation(command);
            // Todo: Should we use the getters here? If overridden they're not safe against loops...
            for (String name : command.subCommandNames) command.subCommands.add(dummyCommand_K.valueOf(name));
            for (dummyCommand_K child : command.subCommands) command.subCommandNames.add(child.name());

            // just for fun, let's log if we use dynamic lists...
            if (command.declaresOrOverridesMethod("getSubCommands")) {
                System.out.println("getSubCommands() overridden in " + command);
            };
            if (command.declaresOrOverridesMethod("getSubCommandNames")) {
                System.out.println("getSubCommandNames() overridden in " + command);
            };
        }
    }

    // Where the subcommands are stored. Override for dynamic list. Be careful when overriding both getters at once.
    @Getter // Override for dynamic list.
    private final List<String> subCommandNames = new ArrayList<>();
    @Getter // Override for dynamic list. If overridden you must take care of your non-dynamic values yourself.
    private final List<dummyCommand_K> subCommands = new ArrayList<>();

    dummyCommand_K(String[] children, dummyCommand_K... parents){this(List.of(children), parents);}
    dummyCommand_K(List<String> children, dummyCommand_K... parents){
        this(parents);
        this.subCommandNames.addAll(children);
    }
    dummyCommand_K(dummyCommand_K... parents) {
        // NOTE: We could already check in this loop, if we double-define, but we need the static setup block anyway...
        for (dummyCommand_K parent : parents) parent.subCommands.add(this);
    }

    private boolean declaresOrOverridesMethod(String methodName) {
        try {
            return getClass().getDeclaredMethod(methodName).getDeclaringClass() != dummyCommand_K.class;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        for (dummyCommand_K command : dummyCommand_K.values()) {
            System.out.println(command + " => " + command.getSubCommands());
            System.out.println(command + " => " + command.getSubCommands());
        }
    }
}

/*
    """
    Relation defined both ways: Parent defines children AND children define parent!
    This can introduce subtle bugs. Only use one way of defining per parent command:
      - If you favor simplicity, let children define their parent(s) (vararg):  CHILD(..., PARENTs)
      - For cyclic relations, let parents define their children (String[]): PARENT(..., ChildNames, ...)
    For dynamic (uncached) lists, override public getSubCommands (may access cached relations too).
    Parent: %s  Children: %s
    """

    """
    Bidirectional definition of relationship: Declared by both, parent and children.
    To avoid ambiguity and subtle bugs, maintain one definition pattern per parent:
      - Child-driven (Recommended): Children define their parent(s) via varargs -> CHILD(..., PARENTs)
      - Parent-driven (For cycles): Parents define child names via String[] -> PARENT(..., ChildNames, ...)

    For dynamic or runtime-resolved lists, override getter ("%s").

    Context:
      Parent:   %s
      Children: %s
    """

 */
