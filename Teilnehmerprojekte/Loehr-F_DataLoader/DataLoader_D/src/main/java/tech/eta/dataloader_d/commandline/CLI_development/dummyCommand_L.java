package tech.eta.dataloader_d.commandline.CLI_development;

import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

// in theory it should be enough to provide children via an varargs of string.
// We shall execute the transformation during the static block.
// We would love to accept a method producing the enum entries directly, but that will be difficult.
// Thus we take it all via strings, but do a hard fail on typos.
// be carefull when overriding either getter. You need to check the othe one is still valid.
public enum dummyCommand_L {
    A(){@Override public List<dummyCommand_L> getChildren() {return List.of(A_a, A_b);}},
    A_a("A", "A_b"),
    A_b(){public List<String> getChildNames() {return List.of("A_a");}},

    B(dummyCommand_L::childnamesByNamespace),
    B_a,
    B_b,
    B_c,
    C(()->List.of("C_b")),
//    C_a(C),
    C_b(self->List.of("C")),
    ;

    static {
        // Validation and static linking of command graph
        for (dummyCommand_L command : dummyCommand_L.values()) {
            // 1. Fetch the child names to store statically. This should accept a method referrence.
            command.childNames.addAll(command.__getChildNames.apply(command));
            // 2. Fetch the child items to store statically.
            for (String childName : command.childNames) command.children.add(dummyCommand_L.valueOf(childName));
            // 3. Unset the child names getter static function.
            command.__getChildNames = null; // this isn't actually necessary...
        }
        // note
        // I *could* store the map of names to constants and raise one error for all missing children together
        // but really that's mostly what valueOf does anyway... there'll hardly ever be more than one mistake at the same time
        // ... enumClass.enumConstantDirectory().get(name) // not availavle from outside
    }

    // Where the subcommands are stored. Override for dynamic list. Be careful when overriding both getters at once.
    @Getter // Override for dynamic list.
    private final List<String> childNames = new ArrayList<>();
    @Getter // Override for dynamic list. If overridden you must take care of your non-dynamic values yourself.
    private final List<dummyCommand_L> children = new ArrayList<>();

    private Function<dummyCommand_L, List<String>> __getChildNames=(self)->List.of();
    // this is a namespacing default...
    public static List<String> childnamesByNamespace(dummyCommand_L parent){
        final String base = parent.name();
        final int baseLen = base.length();
        final int skipLen = baseLen + 1;
        return Arrays.stream(dummyCommand_L.values()).map(Enum::name).filter( // filters for direct children only
                kid -> kid.length() > skipLen
                        && kid.charAt(baseLen) == '_'
                        && kid.regionMatches(0, base, 0, baseLen)
                        && kid.indexOf('_', skipLen) == -1
        ).toList();
    }
//    public static List<String> childnamesByNamespace(dummyCommand_L parent){
//        return childrenByNamespace(parent).stream().map(Enum::name).toList();
//    }
    public static List<dummyCommand_L> childrenByNamespace(dummyCommand_L parent){
        return Arrays.stream(dummyCommand_L.values()).filter(child -> isNamespaceChild(parent, child)).toList();
    }
    public static boolean isNamespaceChild(dummyCommand_L parent, dummyCommand_L child){
        String childName = child.name();
        String parentName = parent.name()+"_";
        return childName.startsWith(parentName) && !childName.substring(parentName.length()).contains("_");
    }

    dummyCommand_L(String... children) {this(self -> List.of(children));}
    dummyCommand_L(Supplier<List<String>> getChildNames) {this(self -> getChildNames.get());}
    dummyCommand_L(Function<dummyCommand_L, List<String>> getChildNames) {this.__getChildNames = getChildNames;}

    private boolean declaresOrOverridesMethod(String methodName) {
        try {
            return getClass().getDeclaredMethod(methodName).getDeclaringClass() != dummyCommand_L.class;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        for (dummyCommand_L command : dummyCommand_L.values()) {
            System.out.println(command + " => " + command.getChildren());
            System.out.println(command + " => " + command.getChildren());
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
