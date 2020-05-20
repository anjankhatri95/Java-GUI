public class AmountException extends Exception{
  public AmountException(){
   super("Error: Amount is required !!!"); 
  }
  public AmountException(String message){
    super(message); 
  }
}