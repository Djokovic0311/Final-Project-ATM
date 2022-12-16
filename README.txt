# CS611-Final-Project
## ATM
---------------------------------------------------------------------------
<Jiahang Li>        <Chen Zhu>          <Sanath Bhimsen>        <Mingxin Li>
<jiahang@bu.edu>    <czhuai@bu.edu>     <sanath97@bu.edu>       <mxli@bu.edu>
<U00295086>         <U39906058>          <U76109980>             <U56463762>

## Files
---------------------------------------------------------------------------
The description of all the files is in the design documentation.

## Notes
---------------------------------------------------------------------------
1. The database file (Database.sql) and the .jar file for connecting to MySql
    is under the 'lib' folder.

## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "pa1" after unzipping the files
2. Run the following instructions:
   <Example below>
   javac $(find src -name "*.java") -encoding UTF8 -classpath "XXX.jar" -d output/ 
   cd output
   java view.GUILoginWindow
