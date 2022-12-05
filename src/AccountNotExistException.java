/*
   Exception to call when try to visit/operate on an account
   that does not exist.
      @author: Jiahang Li
    @version: 1.0
 */
public class AccountNotExistException extends RuntimeException{

    public AccountNotExistException(){super("Account does not Exist!");}

}