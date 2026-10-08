package com.tutego.insel.string;
import java.util.*;
import static java.lang.System.out;

public class Hangman2 {
  @SuppressWarnings( "resource" )
  public static void main( String[] args ) {
    String[] hangmanWords = { "samoa", "tonga", "fiji", "vanuatu" };
    String hangmanWord = hangmanWords[ new Random().nextInt( hangmanWords.length ) ];

    String usedChars = "";
    String guessedWord = hangmanWord.replaceAll( ".", "_" );

    for ( int guesses = 0; ; guesses++ ) {
      if ( guesses == 10 ) {
        System.out.printf(
         "Sorry, game over after 10 attempts! Speaking of which, the word was '%s'.",
         hangmanWord );
        break;
      }

      out.printf( "Round %d. Previous guess: %s. What character do you choose?%n",
                  guesses, guessedWord );
      char guessedChar = new java.util.Scanner( System.in ).next().charAt( 0 );

      if ( usedChars.indexOf( guessedChar ) >= 0 )
        out.printf( "'%c' you have entered before!%n", guessedChar );
      else {  // Character has not been used yet
        usedChars += guessedChar;
        // hangmanWord.indexOf( c ) == -1
        if ( hangmanWord.indexOf( guessedChar ) >= 0 ) {
          guessedWord = hangmanWord.replaceAll( "[^"+usedChars+"]", "_" );
          if ( guessedWord.contains( "_" ) )
            out.printf( "Good guess, '%s' can be found in the word. " +
                        "But there's something missing!%n", guessedChar );
          else {
            out.printf( "Congratulations, you have guessed the word '%s'!",
                        hangmanWord );
            break;
          }
        }
        else
          out.printf( "Bad luck, %c does not occur in the word!%n", guessedChar );
      }
    }
  }
}