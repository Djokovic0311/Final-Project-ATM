The program is divided into 6 layers: The GUI layer displaying the required information and guiding the user to input the actions. The Controller layer receieve the input information from GUI layer, forward the data to corresponding functions in Service layer, and return the result to the GUI for displaying. The Service layer is where every action's logic is really executed. Whenever the action requires data from database, the service layer will forward the request to the Data Access Object (DAO) layer, only in this layer the program can access to the database.

Using the action "log in" as an example. When user input the name, password and select the type of user, the GUI will forward these input to the LoginController.signIn() function in the Controller layer. This function will further forward the information to the LoginService.signIn() function in the service layer, where the log in action is really execute. When executing the action, we need to compare the username, password, and user type with our database to see whether there is a match, therefore, the 
LoginService.signIn() will call the userDao.selectUserById() function, where the program access the database serching for matching user. Each layer is returning whether log in or not to its upper layer, and if receieving response of success, the GUI will forward to the home page of user, otherwise the information doesn't match warning will be displayed.

Apart from the 5 layers above, the program also contains a model layer storing the defination of each object in the ATM system. The instances of these madels are created based on the data retrieved from the database in the DAO layer and return to the Service layer. The Service layer functions retrieve the required information from these instances and process these information based on the corresponding action's logic.

The detail description of each layers is as follows:

The GUI Layer in the Package "View":
  1. GUIBuyOrSellStock: The interface for user having activated security account to buy or sell stocks.
  2. GUICustomerAccountWindow: The interface for users to view, open and close their accounts. ?? need double check
  3. GUICustomerCloseAccount: ?????
  4. GUICustomerHomePage: The homepage of customer, where customer can choose to managing user information, account information or taking actions related to money.
  5. GUICustomerMoneyWindow: 
