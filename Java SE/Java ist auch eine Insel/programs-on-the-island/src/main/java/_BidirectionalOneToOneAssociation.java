import org.jetbrains.annotations.NotNull;

import javax.swing.text.AsyncBoxView;
import java.util.Objects;

class Person {
  private Pet pet;

  public void setPet( Pet pet ) {
    Objects.requireNonNull( pet, "Pet cannot be null" );

    if ( this.pet == pet )
      return;

    if ( this.pet != null )
      this.pet.removeOwner();

    this.pet = pet;
    pet.setOwner( this );
  }

  public Pet getPet() {
    return pet;
  }

  public Pet removePet() {
    Pet removedPet = this.pet;
    if ( this.pet != null ) {
      this.pet = null;
      removedPet.removeOwner();
    }
    return removedPet;
  }
}

class Pet {
  private Person owner;

  public void setOwner( Person owner ) {
    Objects.requireNonNull( owner, "Owner cannot be null" );

    if ( this.owner == owner )
      return;

    if ( this.owner != null )
      this.owner.removePet();

    this.owner = owner;
    owner.setPet( this );
  }

  public Person getOwner() {
    return owner;
  }

  public Person removeOwner() {
    Person removedOwner = this.owner;
    if ( this.owner != null ) {
      this.owner = null;
      removedOwner.removePet();
    }
    return removedOwner;
  }
}

public class _BidirectionalOneToOneAssociation {
  public static void main( String[] args ) {
    Person homer = new Person();
    Pet santa = new Pet();
    homer.setPet( santa );
    santa.setOwner( homer );
    System.out.println( homer.getPet() == santa );
    System.out.println( santa.getOwner() == homer );

    Person marge = new Person();
    marge.setPet( santa );
    System.out.println( homer.getPet() == null );
    System.out.println( marge.getPet() == santa );
    System.out.println( santa.getOwner() == marge );

    marge.removePet();
    System.out.println( marge.getPet() == null );

    santa.setOwner( homer );
    System.out.println( homer.getPet() == santa );
    System.out.println( marge.getPet() == null );
    System.out.println( santa.getOwner() == homer );
  }
}

