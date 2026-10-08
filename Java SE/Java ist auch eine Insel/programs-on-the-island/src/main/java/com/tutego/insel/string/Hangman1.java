package com.tutego.insel.string;

public class Hangman1 {

  @SuppressWarnings( "resource" )
  public static void main( String[] args ) {
    String hangmanWord = "alligatoralley";
    String usedChars = "";

    String guessedWord = "";
    for ( int i = 0; i < hangmanWord.length(); i++ )
      guessedWord += "_";

    for ( int guesses = 0; ; guesses++ ) {
      if ( guesses == 10 ) {
        System.out.printf(
         "Sorry, game over after 10 attempts! By the way, the word was '%s'.",
         hangmanWord );
        break;
      }

      System.out.printf(
          "Round %d. Previous guess: '%s'. Which character do you choose?%n",
          guesses, guessedWord );
      char guessedChar = new java.util.Scanner( System.in ).next().charAt( 0 );

      if ( usedChars.indexOf( guessedChar ) >= 0 )
        System.out.printf( "'%c' you have entered before!%n", guessedChar );
      else { // Character has not been used yet
        usedChars += guessedChar;
        // hangmanWord.indexOf( c ) == -1
        if ( hangmanWord.indexOf( guessedChar ) >= 0 ) {
          guessedWord = "";
          for ( int i = 0; i < hangmanWord.length(); i++ )
            guessedWord += usedChars.indexOf( hangmanWord.charAt( i ) ) >= 0 ?
                             hangmanWord.charAt( i ) : "_";

          if ( guessedWord.contains( "_" ) )
            System.out.printf( "Good guess, '%s' can be found in the word. " +
                               "But there's something missing!%n", guessedChar );
          else {
            System.out.printf( "Congratulations, you have guessed the word '%s'!",
                               hangmanWord );
            break;
          }
        }
        else
          System.out.printf( "Bad luck, %c does not occur in the word!%n",
                             guessedChar );
      }
    }
  }
}