package com.tutego.insel.sealed;

import java.util.Objects;

public sealed class Feeling {
  public enum Scale {
    Not_at_all, A_little, Moderately, Quite_a_lot, Extremely
  }

  public final Scale scale;

  protected Feeling( Scale scale ) { this.scale = Objects.requireNonNull( scale ); }

  public static final class Friendly extends Feeling {
    public Friendly( Scale scale ) { super( scale ); }
  }
  public static final class Tense extends Feeling {
    public Tense( Scale scale ) { super( scale ); }
  }
  public static final class Active extends Feeling {
    public Active( Scale scale ) { super( scale ); }
  }
}

class FeelingDemo {
  public static void main( String[] args ) {
    Feeling active = new Feeling.Active( Feeling.Scale.Moderately );
    System.out.println( active.scale );
  }
}
