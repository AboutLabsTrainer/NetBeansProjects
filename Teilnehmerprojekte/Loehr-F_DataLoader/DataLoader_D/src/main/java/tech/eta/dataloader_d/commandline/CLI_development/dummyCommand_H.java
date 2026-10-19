package tech.eta.dataloader_d.commandline.CLI_development;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public enum dummyCommand_H {
    // --- problem: forward-referrences are not allowed (otherwise it would be perfect...)
//    A(  ) {private Supplier<List<dummyCommand_H>> subcommandSupplier = ()-> List.of(A_a,A_b);},
//    A_a(()->List.of(A_b)),
//    A_b(){protected List<dummyCommand_H> definedChildren() {return List.of(A_a);}},
//
    A(  ) {private List<dummyCommand_H> mySubCommands(){return List.of(A_a,A_b);}},
    A_a(),
    A_b(){protected List<dummyCommand_H> definedChildren() {return List.of(A_a);}},

    B(),
    B_a(B){protected List<dummyCommand_H> definedChildren() {
        System.out.println("HO");return List.of(B_b);}},
    B_b(B){protected List<dummyCommand_H> definedChildren() {return List.of(B_a);}},

//    C(){protected List<dummyCommand_H> definedChildren() {return List.of(C_b);}},
//    C_a(C),
//    C_b()
    ;

    // Cashed subcommands. May be cyclic if given a subcommand-supplier.
    private final List<dummyCommand_H> subCommands = new ArrayList<>(); // this cashes / stores the subcommands
    private Supplier<List<dummyCommand_H>> subcommandSupplier = null;   // Optional: for subcommands to support cycles

    // Default Getter. Override for dynamic lists. Caches and removes the supplier. Supplier existence acts as flag.
    public List<dummyCommand_H> getSubCommands(){
        if (subcommandSupplier != null) { // still have a supplier? Cache and remove.
            subCommands.addAll(subcommandSupplier.get());
            subcommandSupplier = null;
        }
        return subCommands;
    };

    protected List<dummyCommand_H> definedChildren() {return List.of();}


    dummyCommand_H(dummyCommand_H... parents) {
        for (dummyCommand_H parent : parents) {
            // using both ways of defining children can work, but can just as easily introduce subtle bugs
            // therefore we'd rather throw an error here than risk subtle bugs
            if (parent.subcommandSupplier != null) { // to prevent subtle bugs during refactoring (in case an item is doubly added to the childrens...)
                throw new IllegalArgumentException(parent + " and " + this + " defines children both ways");
            }
            parent.subCommands.add(this);
        }
        try {
            if (getClass().getDeclaredMethod("definedChildren").getDeclaringClass() != dummyCommand_H.class) {
                this.subcommandSupplier = this::definedChildren;
            }
        } catch (NoSuchMethodException ignored) {}
    }
    dummyCommand_H(Supplier<List<dummyCommand_H>> children, dummyCommand_H... parents) {
        this(parents);
        this.subcommandSupplier = children; // set a supplyer. if given it will double as isNotCachedYet marker...
    }

    public static void main(String[] args) {
        for (dummyCommand_H command : dummyCommand_H.values()) {
            System.out.println(command + " => " + command.getSubCommands());
            System.out.println(command + " => " + command.getSubCommands());
        }
    }
}
