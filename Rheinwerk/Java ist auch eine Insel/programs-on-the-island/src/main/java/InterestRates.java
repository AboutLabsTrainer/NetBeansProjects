public class InterestRates {

  public static void main( String[] args ) {
    double capital      = 20000 /* Euro */;
    double interestRate = 3.6   /* percent */;
    
    double totalInterestRate = capital * interestRate / 100;  // Year 1
    
    System.out.print( "Interest: " );
    System.out.println( totalInterestRate );  // 720.0
  }
}