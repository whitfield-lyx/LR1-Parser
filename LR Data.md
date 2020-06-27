## 产生式

program -> compoundstmt
 stmt ->  ifstmt  |  whilestmt  |  assgstmt  |  compoundstmt
 compoundstmt ->  { stmts }
 stmts ->  stmt stmts   |   E
 ifstmt ->  if ( boolexpr ) then stmt else stmt
 whilestmt ->  while ( boolexpr ) stmt
 assgstmt ->  ID = arithexpr ;
 boolexpr  ->  arithexpr boolop arithexpr
 boolop ->   <  |  >  |  <=  |  >=  | ==
 arithexpr  ->  multexpr arithexprprime
 arithexprprime ->  + multexpr arithexprprime  |  - multexpr arithexprprime  |   E
 multexpr ->  simpleexpr  multexprprime
 multexprprime ->  * simpleexpr multexprprime  |  / simpleexpr multexprprime  |   E
 simpleexpr ->  ID  |  NUM  |  ( arithexpr )

拆分为单独的表达式
0:boolop -> <
1:boolop -> >
2:boolop -> <=
3:boolop -> >=
4:boolop -> ==
5:stmts -> stmt stmts
6:stmts -> E
7:ifstmt -> if ( boolexpr ) then stmt else stmt
8:compoundstmt -> { stmts }
9:program -> compoundstmt
10:boolexpr -> arithexpr boolop arithexpr
11:whilestmt -> while ( boolexpr ) stmt
12:multexprprime -> * simpleexpr multexprprime
13:multexprprime -> / simpleexpr multexprprime
14:multexprprime -> E
15:assgstmt -> ID = arithexpr ;
16:multexpr -> simpleexpr  multexprprime
17:simpleexpr -> ID
18:simpleexpr -> NUM
19:simpleexpr -> ( arithexpr )
20:arithexprprime -> + multexpr arithexprprime
21:arithexprprime -> - multexpr arithexprprime
22:arithexprprime -> E
23:arithexpr -> multexpr arithexprprime
24:stmt -> ifstmt
25:stmt -> whilestmt
26:stmt -> assgstmt
27:stmt -> compoundstmt

## ----Symbol----

(  )  *  +  -  /  ;  <  <=  =  ==  >  >=  E  ID  NUM  arithexpr  arithexprprime  assgstmt  boolexpr  boolop  compoundstmt  else  if  ifstmt  multexpr  multexprprime  program  simpleexpr  stmt  stmts  then  while  whilestmt  {  }  

FirstSet:
boolop = [<, <=, ==, >, >=]
<= = [<=]
E = [E]
program = [{]
while = [while]
boolexpr = [(, ID, NUM]
multexprprime = [*, /, E]
assgstmt = [ID]
multexpr = [(, ID, NUM]
simpleexpr = [(, ID, NUM]
else = [else]
ID = [ID]
if = [if]
== = [==]
stmts = [E, ID, if, while, {]
ifstmt = [if]
NUM = [NUM]
( = [(]
) = [)]
compoundstmt = [{]
\* = [*]
then = [then]
\+ = [+]
\- = [-]
/ = [/]
whilestmt = [while]
arithexprprime = [+, -, E]
{ = [{]
; = [;]
< = [<]
arithexpr = [(, ID, NUM]
} = [}]
= = [=]
\> = [>]
stmt = [ID, if, while, {]
= = [>=]
$ = [$]



## 状态集合

-----0-----

compoundstmt -> { stmts }' , 0 , $	
program -> compoundstmt' , 0 , $	

-----1-----

program -> compoundstmt' , 1 , $	

-----2-----

assgstmt -> ID = arithexpr ;' , 0 , E	
assgstmt -> ID = arithexpr ;' , 0 , ID	
assgstmt -> ID = arithexpr ;' , 0 , if	
assgstmt -> ID = arithexpr ;' , 0 , while	
assgstmt -> ID = arithexpr ;' , 0 , {	
assgstmt -> ID = arithexpr ;' , 0 , }	
compoundstmt -> { stmts }' , 1 , $	
compoundstmt -> { stmts }' , 0 , E	
compoundstmt -> { stmts }' , 0 , ID	
compoundstmt -> { stmts }' , 0 , if	
compoundstmt -> { stmts }' , 0 , while	
compoundstmt -> { stmts }' , 0 , {	
compoundstmt -> { stmts }' , 0 , }	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , }	
stmt -> assgstmt' , 0 , E	
stmt -> assgstmt' , 0 , ID	
stmt -> assgstmt' , 0 , if	
stmt -> assgstmt' , 0 , while	
stmt -> assgstmt' , 0 , {	
stmt -> assgstmt' , 0 , }	
stmt -> compoundstmt' , 0 , E	
stmt -> compoundstmt' , 0 , ID	
stmt -> compoundstmt' , 0 , if	
stmt -> compoundstmt' , 0 , while	
stmt -> compoundstmt' , 0 , {	
stmt -> compoundstmt' , 0 , }	
stmt -> ifstmt' , 0 , E	
stmt -> ifstmt' , 0 , ID	
stmt -> ifstmt' , 0 , if	
stmt -> ifstmt' , 0 , while	
stmt -> ifstmt' , 0 , {	
stmt -> ifstmt' , 0 , }	
stmt -> whilestmt' , 0 , E	
stmt -> whilestmt' , 0 , ID	
stmt -> whilestmt' , 0 , if	
stmt -> whilestmt' , 0 , while	
stmt -> whilestmt' , 0 , {	
stmt -> whilestmt' , 0 , }	
stmts -> E' , 0 , }	
stmts -> stmt stmts' , 0 , }	
whilestmt -> while ( boolexpr ) stmt' , 0 , E	
whilestmt -> while ( boolexpr ) stmt' , 0 , ID	
whilestmt -> while ( boolexpr ) stmt' , 0 , if	
whilestmt -> while ( boolexpr ) stmt' , 0 , while	
whilestmt -> while ( boolexpr ) stmt' , 0 , {	
whilestmt -> while ( boolexpr ) stmt' , 0 , }	

-----3-----
stmts -> E' , 1 , }	

-----4-----
assgstmt -> ID = arithexpr ;' , 1 , E	
assgstmt -> ID = arithexpr ;' , 1 , ID	
assgstmt -> ID = arithexpr ;' , 1 , if	
assgstmt -> ID = arithexpr ;' , 1 , while	
assgstmt -> ID = arithexpr ;' , 1 , {	
assgstmt -> ID = arithexpr ;' , 1 , }	

-----5-----
stmt -> assgstmt' , 1 , E	
stmt -> assgstmt' , 1 , ID	
stmt -> assgstmt' , 1 , if	
stmt -> assgstmt' , 1 , while	
stmt -> assgstmt' , 1 , {	
stmt -> assgstmt' , 1 , }	

-----6-----
stmt -> compoundstmt' , 1 , E	
stmt -> compoundstmt' , 1 , ID	
stmt -> compoundstmt' , 1 , if	
stmt -> compoundstmt' , 1 , while	
stmt -> compoundstmt' , 1 , {	
stmt -> compoundstmt' , 1 , }	

-----7-----
ifstmt -> if ( boolexpr ) then stmt else stmt' , 1 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 1 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 1 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 1 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 1 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 1 , }	

-----8-----
stmt -> ifstmt' , 1 , E	
stmt -> ifstmt' , 1 , ID	
stmt -> ifstmt' , 1 , if	
stmt -> ifstmt' , 1 , while	
stmt -> ifstmt' , 1 , {	
stmt -> ifstmt' , 1 , }	

-----9-----
assgstmt -> ID = arithexpr ;' , 0 , E	
assgstmt -> ID = arithexpr ;' , 0 , ID	
assgstmt -> ID = arithexpr ;' , 0 , if	
assgstmt -> ID = arithexpr ;' , 0 , while	
assgstmt -> ID = arithexpr ;' , 0 , {	
assgstmt -> ID = arithexpr ;' , 0 , }	
compoundstmt -> { stmts }' , 0 , E	
compoundstmt -> { stmts }' , 0 , ID	
compoundstmt -> { stmts }' , 0 , if	
compoundstmt -> { stmts }' , 0 , while	
compoundstmt -> { stmts }' , 0 , {	
compoundstmt -> { stmts }' , 0 , }	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , }	
stmt -> assgstmt' , 0 , E	
stmt -> assgstmt' , 0 , ID	
stmt -> assgstmt' , 0 , if	
stmt -> assgstmt' , 0 , while	
stmt -> assgstmt' , 0 , {	
stmt -> assgstmt' , 0 , }	
stmt -> compoundstmt' , 0 , E	
stmt -> compoundstmt' , 0 , ID	
stmt -> compoundstmt' , 0 , if	
stmt -> compoundstmt' , 0 , while	
stmt -> compoundstmt' , 0 , {	
stmt -> compoundstmt' , 0 , }	
stmt -> ifstmt' , 0 , E	
stmt -> ifstmt' , 0 , ID	
stmt -> ifstmt' , 0 , if	
stmt -> ifstmt' , 0 , while	
stmt -> ifstmt' , 0 , {	
stmt -> ifstmt' , 0 , }	
stmt -> whilestmt' , 0 , E	
stmt -> whilestmt' , 0 , ID	
stmt -> whilestmt' , 0 , if	
stmt -> whilestmt' , 0 , while	
stmt -> whilestmt' , 0 , {	
stmt -> whilestmt' , 0 , }	
stmts -> E' , 0 , }	
stmts -> stmt stmts' , 0 , }	
stmts -> stmt stmts' , 1 , }	
whilestmt -> while ( boolexpr ) stmt' , 0 , E	
whilestmt -> while ( boolexpr ) stmt' , 0 , ID	
whilestmt -> while ( boolexpr ) stmt' , 0 , if	
whilestmt -> while ( boolexpr ) stmt' , 0 , while	
whilestmt -> while ( boolexpr ) stmt' , 0 , {	
whilestmt -> while ( boolexpr ) stmt' , 0 , }	

-----10-----
compoundstmt -> { stmts }' , 2 , $	

-----11-----
whilestmt -> while ( boolexpr ) stmt' , 1 , E	
whilestmt -> while ( boolexpr ) stmt' , 1 , ID	
whilestmt -> while ( boolexpr ) stmt' , 1 , if	
whilestmt -> while ( boolexpr ) stmt' , 1 , while	
whilestmt -> while ( boolexpr ) stmt' , 1 , {	
whilestmt -> while ( boolexpr ) stmt' , 1 , }	

-----12-----
stmt -> whilestmt' , 1 , E	
stmt -> whilestmt' , 1 , ID	
stmt -> whilestmt' , 1 , if	
stmt -> whilestmt' , 1 , while	
stmt -> whilestmt' , 1 , {	
stmt -> whilestmt' , 1 , }	

-----13-----
assgstmt -> ID = arithexpr ;' , 0 , E	
assgstmt -> ID = arithexpr ;' , 0 , ID	
assgstmt -> ID = arithexpr ;' , 0 , if	
assgstmt -> ID = arithexpr ;' , 0 , while	
assgstmt -> ID = arithexpr ;' , 0 , {	
assgstmt -> ID = arithexpr ;' , 0 , }	
compoundstmt -> { stmts }' , 0 , E	
compoundstmt -> { stmts }' , 1 , E	
compoundstmt -> { stmts }' , 0 , ID	
compoundstmt -> { stmts }' , 1 , ID	
compoundstmt -> { stmts }' , 0 , if	
compoundstmt -> { stmts }' , 1 , if	
compoundstmt -> { stmts }' , 0 , while	
compoundstmt -> { stmts }' , 1 , while	
compoundstmt -> { stmts }' , 0 , {	
compoundstmt -> { stmts }' , 1 , {	
compoundstmt -> { stmts }' , 0 , }	
compoundstmt -> { stmts }' , 1 , }	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , }	
stmt -> assgstmt' , 0 , E	
stmt -> assgstmt' , 0 , ID	
stmt -> assgstmt' , 0 , if	
stmt -> assgstmt' , 0 , while	
stmt -> assgstmt' , 0 , {	
stmt -> assgstmt' , 0 , }	
stmt -> compoundstmt' , 0 , E	
stmt -> compoundstmt' , 0 , ID	
stmt -> compoundstmt' , 0 , if	
stmt -> compoundstmt' , 0 , while	
stmt -> compoundstmt' , 0 , {	
stmt -> compoundstmt' , 0 , }	
stmt -> ifstmt' , 0 , E	
stmt -> ifstmt' , 0 , ID	
stmt -> ifstmt' , 0 , if	
stmt -> ifstmt' , 0 , while	
stmt -> ifstmt' , 0 , {	
stmt -> ifstmt' , 0 , }	
stmt -> whilestmt' , 0 , E	
stmt -> whilestmt' , 0 , ID	
stmt -> whilestmt' , 0 , if	
stmt -> whilestmt' , 0 , while	
stmt -> whilestmt' , 0 , {	
stmt -> whilestmt' , 0 , }	
stmts -> E' , 0 , }	
stmts -> stmt stmts' , 0 , }	
whilestmt -> while ( boolexpr ) stmt' , 0 , E	
whilestmt -> while ( boolexpr ) stmt' , 0 , ID	
whilestmt -> while ( boolexpr ) stmt' , 0 , if	
whilestmt -> while ( boolexpr ) stmt' , 0 , while	
whilestmt -> while ( boolexpr ) stmt' , 0 , {	
whilestmt -> while ( boolexpr ) stmt' , 0 , }	

-----14-----
arithexpr -> multexpr arithexprprime' , 0 , ;	
assgstmt -> ID = arithexpr ;' , 2 , E	
assgstmt -> ID = arithexpr ;' , 2 , ID	
assgstmt -> ID = arithexpr ;' , 2 , if	
assgstmt -> ID = arithexpr ;' , 2 , while	
assgstmt -> ID = arithexpr ;' , 2 , {	
assgstmt -> ID = arithexpr ;' , 2 , }	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , ;	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , ;	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , ;	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , ;	
simpleexpr -> NUM' , 0 , E	

-----15-----
arithexpr -> multexpr arithexprprime' , 0 , <	
arithexpr -> multexpr arithexprprime' , 0 , <=	
arithexpr -> multexpr arithexprprime' , 0 , ==	
arithexpr -> multexpr arithexprprime' , 0 , >	
arithexpr -> multexpr arithexprprime' , 0 , >=	
boolexpr -> arithexpr boolop arithexpr' , 0 , )	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 2 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 2 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 2 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 2 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 2 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 2 , }	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , <	
multexpr -> simpleexpr  multexprprime' , 0 , <=	
multexpr -> simpleexpr  multexprprime' , 0 , ==	
multexpr -> simpleexpr  multexprprime' , 0 , >	
multexpr -> simpleexpr  multexprprime' , 0 , >=	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , <	
simpleexpr -> ( arithexpr )' , 0 , <=	
simpleexpr -> ( arithexpr )' , 0 , ==	
simpleexpr -> ( arithexpr )' , 0 , >	
simpleexpr -> ( arithexpr )' , 0 , >=	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , <	
simpleexpr -> ID' , 0 , <=	
simpleexpr -> ID' , 0 , ==	
simpleexpr -> ID' , 0 , >	
simpleexpr -> ID' , 0 , >=	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , <	
simpleexpr -> NUM' , 0 , <=	
simpleexpr -> NUM' , 0 , ==	
simpleexpr -> NUM' , 0 , >	
simpleexpr -> NUM' , 0 , >=	
simpleexpr -> NUM' , 0 , E	

-----16-----
stmts -> stmt stmts' , 2 , }	

-----17-----
compoundstmt -> { stmts }' , 3 , $	

-----18-----
arithexpr -> multexpr arithexprprime' , 0 , <	
arithexpr -> multexpr arithexprprime' , 0 , <=	
arithexpr -> multexpr arithexprprime' , 0 , ==	
arithexpr -> multexpr arithexprprime' , 0 , >	
arithexpr -> multexpr arithexprprime' , 0 , >=	
boolexpr -> arithexpr boolop arithexpr' , 0 , )	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , <	
multexpr -> simpleexpr  multexprprime' , 0 , <=	
multexpr -> simpleexpr  multexprprime' , 0 , ==	
multexpr -> simpleexpr  multexprprime' , 0 , >	
multexpr -> simpleexpr  multexprprime' , 0 , >=	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , <	
simpleexpr -> ( arithexpr )' , 0 , <=	
simpleexpr -> ( arithexpr )' , 0 , ==	
simpleexpr -> ( arithexpr )' , 0 , >	
simpleexpr -> ( arithexpr )' , 0 , >=	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , <	
simpleexpr -> ID' , 0 , <=	
simpleexpr -> ID' , 0 , ==	
simpleexpr -> ID' , 0 , >	
simpleexpr -> ID' , 0 , >=	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , <	
simpleexpr -> NUM' , 0 , <=	
simpleexpr -> NUM' , 0 , ==	
simpleexpr -> NUM' , 0 , >	
simpleexpr -> NUM' , 0 , >=	
simpleexpr -> NUM' , 0 , E	
whilestmt -> while ( boolexpr ) stmt' , 2 , E	
whilestmt -> while ( boolexpr ) stmt' , 2 , ID	
whilestmt -> while ( boolexpr ) stmt' , 2 , if	
whilestmt -> while ( boolexpr ) stmt' , 2 , while	
whilestmt -> while ( boolexpr ) stmt' , 2 , {	
whilestmt -> while ( boolexpr ) stmt' , 2 , }	

-----19-----
compoundstmt -> { stmts }' , 2 , E	
compoundstmt -> { stmts }' , 2 , ID	
compoundstmt -> { stmts }' , 2 , if	
compoundstmt -> { stmts }' , 2 , while	
compoundstmt -> { stmts }' , 2 , {	
compoundstmt -> { stmts }' , 2 , }	

-----20-----
arithexpr -> multexpr arithexprprime' , 0 , )	
multexpr -> simpleexpr  multexprprime' , 0 , )	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , )	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 1 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 1 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 1 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 1 , /	
simpleexpr -> ( arithexpr )' , 1 , ;	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ( arithexpr )' , 1 , E	
simpleexpr -> ID' , 0 , )	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , )	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , E	

-----21-----
simpleexpr -> ID' , 1 , *	
simpleexpr -> ID' , 1 , +	
simpleexpr -> ID' , 1 , -	
simpleexpr -> ID' , 1 , /	
simpleexpr -> ID' , 1 , ;	
simpleexpr -> ID' , 1 , E	

-----22-----
simpleexpr -> NUM' , 1 , *	
simpleexpr -> NUM' , 1 , +	
simpleexpr -> NUM' , 1 , -	
simpleexpr -> NUM' , 1 , /	
simpleexpr -> NUM' , 1 , ;	
simpleexpr -> NUM' , 1 , E	

-----23-----
assgstmt -> ID = arithexpr ;' , 3 , E	
assgstmt -> ID = arithexpr ;' , 3 , ID	
assgstmt -> ID = arithexpr ;' , 3 , if	
assgstmt -> ID = arithexpr ;' , 3 , while	
assgstmt -> ID = arithexpr ;' , 3 , {	
assgstmt -> ID = arithexpr ;' , 3 , }	

-----24-----
arithexpr -> multexpr arithexprprime' , 1 , ;	
arithexprprime -> + multexpr arithexprprime' , 0 , ;	
arithexprprime -> - multexpr arithexprprime' , 0 , ;	
arithexprprime -> E' , 0 , ;	

### -----25-----

multexpr -> simpleexpr  multexprprime' , 1 , +	
multexpr -> simpleexpr  multexprprime' , 1 , -	
multexpr -> simpleexpr  multexprprime' , 1 , ;	
multexpr -> simpleexpr  multexprprime' , 1 , E	
multexprprime -> * simpleexpr multexprprime' , 0 , +	
multexprprime -> * simpleexpr multexprprime' , 0 , -	
multexprprime -> * simpleexpr multexprprime' , 0 , ;	
multexprprime -> * simpleexpr multexprprime' , 0 , E	
multexprprime -> / simpleexpr multexprprime' , 0 , +	
multexprprime -> / simpleexpr multexprprime' , 0 , -	
multexprprime -> / simpleexpr multexprprime' , 0 , ;	
multexprprime -> / simpleexpr multexprprime' , 0 , E	
multexprprime -> E' , 0 , +	
multexprprime -> E' , 0 , -	
multexprprime -> E' , 0 , ;	
multexprprime -> E' , 0 , E	

-----26-----
arithexpr -> multexpr arithexprprime' , 0 , )	
multexpr -> simpleexpr  multexprprime' , 0 , )	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , )	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 1 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 1 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 1 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 1 , /	
simpleexpr -> ( arithexpr )' , 1 , <	
simpleexpr -> ( arithexpr )' , 1 , <=	
simpleexpr -> ( arithexpr )' , 1 , ==	
simpleexpr -> ( arithexpr )' , 1 , >	
simpleexpr -> ( arithexpr )' , 1 , >=	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ( arithexpr )' , 1 , E	
simpleexpr -> ID' , 0 , )	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , )	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , E	

-----27-----
simpleexpr -> ID' , 1 , *	
simpleexpr -> ID' , 1 , +	
simpleexpr -> ID' , 1 , -	
simpleexpr -> ID' , 1 , /	
simpleexpr -> ID' , 1 , <	
simpleexpr -> ID' , 1 , <=	
simpleexpr -> ID' , 1 , ==	
simpleexpr -> ID' , 1 , >	
simpleexpr -> ID' , 1 , >=	
simpleexpr -> ID' , 1 , E	

-----28-----
simpleexpr -> NUM' , 1 , *	
simpleexpr -> NUM' , 1 , +	
simpleexpr -> NUM' , 1 , -	
simpleexpr -> NUM' , 1 , /	
simpleexpr -> NUM' , 1 , <	
simpleexpr -> NUM' , 1 , <=	
simpleexpr -> NUM' , 1 , ==	
simpleexpr -> NUM' , 1 , >	
simpleexpr -> NUM' , 1 , >=	
simpleexpr -> NUM' , 1 , E	

-----29-----
boolexpr -> arithexpr boolop arithexpr' , 1 , )	
boolop -> <' , 0 , (	
boolop -> <' , 0 , ID	
boolop -> <' , 0 , NUM	
boolop -> <=' , 0 , (	
boolop -> <=' , 0 , ID	
boolop -> <=' , 0 , NUM	
boolop -> ==' , 0 , (	
boolop -> ==' , 0 , ID	
boolop -> ==' , 0 , NUM	
boolop -> >' , 0 , (	
boolop -> >' , 0 , ID	
boolop -> >' , 0 , NUM	
boolop -> >=' , 0 , (	
boolop -> >=' , 0 , ID	
boolop -> >=' , 0 , NUM	

-----30-----
ifstmt -> if ( boolexpr ) then stmt else stmt' , 3 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 3 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 3 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 3 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 3 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 3 , }	

-----31-----
arithexpr -> multexpr arithexprprime' , 1 , <	
arithexpr -> multexpr arithexprprime' , 1 , <=	
arithexpr -> multexpr arithexprprime' , 1 , ==	
arithexpr -> multexpr arithexprprime' , 1 , >	
arithexpr -> multexpr arithexprprime' , 1 , >=	
arithexprprime -> + multexpr arithexprprime' , 0 , <	
arithexprprime -> + multexpr arithexprprime' , 0 , <=	
arithexprprime -> + multexpr arithexprprime' , 0 , ==	
arithexprprime -> + multexpr arithexprprime' , 0 , >	
arithexprprime -> + multexpr arithexprprime' , 0 , >=	
arithexprprime -> - multexpr arithexprprime' , 0 , <	
arithexprprime -> - multexpr arithexprprime' , 0 , <=	
arithexprprime -> - multexpr arithexprprime' , 0 , ==	
arithexprprime -> - multexpr arithexprprime' , 0 , >	
arithexprprime -> - multexpr arithexprprime' , 0 , >=	
arithexprprime -> E' , 0 , <	
arithexprprime -> E' , 0 , <=	
arithexprprime -> E' , 0 , ==	
arithexprprime -> E' , 0 , >	
arithexprprime -> E' , 0 , >=	

-----32-----
multexpr -> simpleexpr  multexprprime' , 1 , +	
multexpr -> simpleexpr  multexprprime' , 1 , -	
multexpr -> simpleexpr  multexprprime' , 1 , <	
multexpr -> simpleexpr  multexprprime' , 1 , <=	
multexpr -> simpleexpr  multexprprime' , 1 , ==	
multexpr -> simpleexpr  multexprprime' , 1 , >	
multexpr -> simpleexpr  multexprprime' , 1 , >=	
multexpr -> simpleexpr  multexprprime' , 1 , E	
multexprprime -> * simpleexpr multexprprime' , 0 , +	
multexprprime -> * simpleexpr multexprprime' , 0 , -	
multexprprime -> * simpleexpr multexprprime' , 0 , <	
multexprprime -> * simpleexpr multexprprime' , 0 , <=	
multexprprime -> * simpleexpr multexprprime' , 0 , ==	
multexprprime -> * simpleexpr multexprprime' , 0 , >	
multexprprime -> * simpleexpr multexprprime' , 0 , >=	
multexprprime -> * simpleexpr multexprprime' , 0 , E	
multexprprime -> / simpleexpr multexprprime' , 0 , +	
multexprprime -> / simpleexpr multexprprime' , 0 , -	
multexprprime -> / simpleexpr multexprprime' , 0 , <	
multexprprime -> / simpleexpr multexprprime' , 0 , <=	
multexprprime -> / simpleexpr multexprprime' , 0 , ==	
multexprprime -> / simpleexpr multexprprime' , 0 , >	
multexprprime -> / simpleexpr multexprprime' , 0 , >=	
multexprprime -> / simpleexpr multexprprime' , 0 , E	
multexprprime -> E' , 0 , +	
multexprprime -> E' , 0 , -	
multexprprime -> E' , 0 , <	
multexprprime -> E' , 0 , <=	
multexprprime -> E' , 0 , ==	
multexprprime -> E' , 0 , >	
multexprprime -> E' , 0 , >=	
multexprprime -> E' , 0 , E	

-----33-----
whilestmt -> while ( boolexpr ) stmt' , 3 , E	
whilestmt -> while ( boolexpr ) stmt' , 3 , ID	
whilestmt -> while ( boolexpr ) stmt' , 3 , if	
whilestmt -> while ( boolexpr ) stmt' , 3 , while	
whilestmt -> while ( boolexpr ) stmt' , 3 , {	
whilestmt -> while ( boolexpr ) stmt' , 3 , }	

-----34-----
compoundstmt -> { stmts }' , 3 , E	
compoundstmt -> { stmts }' , 3 , ID	
compoundstmt -> { stmts }' , 3 , if	
compoundstmt -> { stmts }' , 3 , while	
compoundstmt -> { stmts }' , 3 , {	
compoundstmt -> { stmts }' , 3 , }	

-----35-----
arithexpr -> multexpr arithexprprime' , 0 , )	
multexpr -> simpleexpr  multexprprime' , 0 , )	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , )	
simpleexpr -> ( arithexpr )' , 1 , )	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 1 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 1 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 1 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 1 , /	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ( arithexpr )' , 1 , E	
simpleexpr -> ID' , 0 , )	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , )	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , E	

-----36-----
simpleexpr -> ID' , 1 , )	
simpleexpr -> ID' , 1 , *	
simpleexpr -> ID' , 1 , +	
simpleexpr -> ID' , 1 , -	
simpleexpr -> ID' , 1 , /	
simpleexpr -> ID' , 1 , E	

-----37-----
simpleexpr -> NUM' , 1 , )	
simpleexpr -> NUM' , 1 , *	
simpleexpr -> NUM' , 1 , +	
simpleexpr -> NUM' , 1 , -	
simpleexpr -> NUM' , 1 , /	
simpleexpr -> NUM' , 1 , E	

-----38-----
simpleexpr -> ( arithexpr )' , 2 , *	
simpleexpr -> ( arithexpr )' , 2 , +	
simpleexpr -> ( arithexpr )' , 2 , -	
simpleexpr -> ( arithexpr )' , 2 , /	
simpleexpr -> ( arithexpr )' , 2 , ;	
simpleexpr -> ( arithexpr )' , 2 , E	

-----39-----
arithexpr -> multexpr arithexprprime' , 1 , )	
arithexprprime -> + multexpr arithexprprime' , 0 , )	
arithexprprime -> - multexpr arithexprprime' , 0 , )	
arithexprprime -> E' , 0 , )	

-----40-----
multexpr -> simpleexpr  multexprprime' , 1 , )	
multexpr -> simpleexpr  multexprprime' , 1 , +	
multexpr -> simpleexpr  multexprprime' , 1 , -	
multexpr -> simpleexpr  multexprprime' , 1 , E	
multexprprime -> * simpleexpr multexprprime' , 0 , )	
multexprprime -> * simpleexpr multexprprime' , 0 , +	
multexprprime -> * simpleexpr multexprprime' , 0 , -	
multexprprime -> * simpleexpr multexprprime' , 0 , E	
multexprprime -> / simpleexpr multexprprime' , 0 , )	
multexprprime -> / simpleexpr multexprprime' , 0 , +	
multexprprime -> / simpleexpr multexprprime' , 0 , -	
multexprprime -> / simpleexpr multexprprime' , 0 , E	
multexprprime -> E' , 0 , )	
multexprprime -> E' , 0 , +	
multexprprime -> E' , 0 , -	
multexprprime -> E' , 0 , E	

-----41-----
assgstmt -> ID = arithexpr ;' , 4 , E	
assgstmt -> ID = arithexpr ;' , 4 , ID	
assgstmt -> ID = arithexpr ;' , 4 , if	
assgstmt -> ID = arithexpr ;' , 4 , while	
assgstmt -> ID = arithexpr ;' , 4 , {	
assgstmt -> ID = arithexpr ;' , 4 , }	

-----42-----
arithexprprime -> + multexpr arithexprprime' , 1 , ;	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , ;	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , ;	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , ;	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , ;	
simpleexpr -> NUM' , 0 , E	

-----43-----
arithexprprime -> - multexpr arithexprprime' , 1 , ;	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , ;	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , ;	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , ;	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , ;	
simpleexpr -> NUM' , 0 , E	

-----44-----
arithexprprime -> E' , 1 , ;	

-----45-----
arithexpr -> multexpr arithexprprime' , 2 , ;	

-----46-----
multexprprime -> * simpleexpr multexprprime' , 1 , +	
multexprprime -> * simpleexpr multexprprime' , 1 , -	
multexprprime -> * simpleexpr multexprprime' , 1 , ;	
multexprprime -> * simpleexpr multexprprime' , 1 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , ;	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , ;	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , ;	
simpleexpr -> NUM' , 0 , E	

-----47-----
multexprprime -> / simpleexpr multexprprime' , 1 , +	
multexprprime -> / simpleexpr multexprprime' , 1 , -	
multexprprime -> / simpleexpr multexprprime' , 1 , ;	
multexprprime -> / simpleexpr multexprprime' , 1 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , ;	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , ;	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , ;	
simpleexpr -> NUM' , 0 , E	

-----48-----
multexprprime -> E' , 1 , +	
multexprprime -> E' , 1 , -	
multexprprime -> E' , 1 , ;	
multexprprime -> E' , 1 , E	

-----49-----
multexpr -> simpleexpr  multexprprime' , 2 , +	
multexpr -> simpleexpr  multexprprime' , 2 , -	
multexpr -> simpleexpr  multexprprime' , 2 , ;	
multexpr -> simpleexpr  multexprprime' , 2 , E	

-----50-----
simpleexpr -> ( arithexpr )' , 2 , *	
simpleexpr -> ( arithexpr )' , 2 , +	
simpleexpr -> ( arithexpr )' , 2 , -	
simpleexpr -> ( arithexpr )' , 2 , /	
simpleexpr -> ( arithexpr )' , 2 , <	
simpleexpr -> ( arithexpr )' , 2 , <=	
simpleexpr -> ( arithexpr )' , 2 , ==	
simpleexpr -> ( arithexpr )' , 2 , >	
simpleexpr -> ( arithexpr )' , 2 , >=	
simpleexpr -> ( arithexpr )' , 2 , E	

-----51-----
boolop -> <' , 1 , (	
boolop -> <' , 1 , ID	
boolop -> <' , 1 , NUM	

-----52-----
boolop -> <=' , 1 , (	
boolop -> <=' , 1 , ID	
boolop -> <=' , 1 , NUM	

-----53-----
boolop -> ==' , 1 , (	
boolop -> ==' , 1 , ID	
boolop -> ==' , 1 , NUM	

-----54-----
boolop -> >' , 1 , (	
boolop -> >' , 1 , ID	
boolop -> >' , 1 , NUM	

-----55-----
boolop -> >=' , 1 , (	
boolop -> >=' , 1 , ID	
boolop -> >=' , 1 , NUM	

-----56-----
arithexpr -> multexpr arithexprprime' , 0 , )	
boolexpr -> arithexpr boolop arithexpr' , 2 , )	
multexpr -> simpleexpr  multexprprime' , 0 , )	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , )	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , )	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , )	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , E	

-----57-----
ifstmt -> if ( boolexpr ) then stmt else stmt' , 4 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 4 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 4 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 4 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 4 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 4 , }	

-----58-----
arithexprprime -> + multexpr arithexprprime' , 1 , <	
arithexprprime -> + multexpr arithexprprime' , 1 , <=	
arithexprprime -> + multexpr arithexprprime' , 1 , ==	
arithexprprime -> + multexpr arithexprprime' , 1 , >	
arithexprprime -> + multexpr arithexprprime' , 1 , >=	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , <	
multexpr -> simpleexpr  multexprprime' , 0 , <=	
multexpr -> simpleexpr  multexprprime' , 0 , ==	
multexpr -> simpleexpr  multexprprime' , 0 , >	
multexpr -> simpleexpr  multexprprime' , 0 , >=	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , <	
simpleexpr -> ( arithexpr )' , 0 , <=	
simpleexpr -> ( arithexpr )' , 0 , ==	
simpleexpr -> ( arithexpr )' , 0 , >	
simpleexpr -> ( arithexpr )' , 0 , >=	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , <	
simpleexpr -> ID' , 0 , <=	
simpleexpr -> ID' , 0 , ==	
simpleexpr -> ID' , 0 , >	
simpleexpr -> ID' , 0 , >=	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , <	
simpleexpr -> NUM' , 0 , <=	
simpleexpr -> NUM' , 0 , ==	
simpleexpr -> NUM' , 0 , >	
simpleexpr -> NUM' , 0 , >=	
simpleexpr -> NUM' , 0 , E	

-----59-----
arithexprprime -> - multexpr arithexprprime' , 1 , <	
arithexprprime -> - multexpr arithexprprime' , 1 , <=	
arithexprprime -> - multexpr arithexprprime' , 1 , ==	
arithexprprime -> - multexpr arithexprprime' , 1 , >	
arithexprprime -> - multexpr arithexprprime' , 1 , >=	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , <	
multexpr -> simpleexpr  multexprprime' , 0 , <=	
multexpr -> simpleexpr  multexprprime' , 0 , ==	
multexpr -> simpleexpr  multexprprime' , 0 , >	
multexpr -> simpleexpr  multexprprime' , 0 , >=	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , <	
simpleexpr -> ( arithexpr )' , 0 , <=	
simpleexpr -> ( arithexpr )' , 0 , ==	
simpleexpr -> ( arithexpr )' , 0 , >	
simpleexpr -> ( arithexpr )' , 0 , >=	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , <	
simpleexpr -> ID' , 0 , <=	
simpleexpr -> ID' , 0 , ==	
simpleexpr -> ID' , 0 , >	
simpleexpr -> ID' , 0 , >=	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , <	
simpleexpr -> NUM' , 0 , <=	
simpleexpr -> NUM' , 0 , ==	
simpleexpr -> NUM' , 0 , >	
simpleexpr -> NUM' , 0 , >=	
simpleexpr -> NUM' , 0 , E	

-----60-----
arithexprprime -> E' , 1 , <	
arithexprprime -> E' , 1 , <=	
arithexprprime -> E' , 1 , ==	
arithexprprime -> E' , 1 , >	
arithexprprime -> E' , 1 , >=	

-----61-----
arithexpr -> multexpr arithexprprime' , 2 , <	
arithexpr -> multexpr arithexprprime' , 2 , <=	
arithexpr -> multexpr arithexprprime' , 2 , ==	
arithexpr -> multexpr arithexprprime' , 2 , >	
arithexpr -> multexpr arithexprprime' , 2 , >=	

-----62-----
multexprprime -> * simpleexpr multexprprime' , 1 , +	
multexprprime -> * simpleexpr multexprprime' , 1 , -	
multexprprime -> * simpleexpr multexprprime' , 1 , <	
multexprprime -> * simpleexpr multexprprime' , 1 , <=	
multexprprime -> * simpleexpr multexprprime' , 1 , ==	
multexprprime -> * simpleexpr multexprprime' , 1 , >	
multexprprime -> * simpleexpr multexprprime' , 1 , >=	
multexprprime -> * simpleexpr multexprprime' , 1 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , <	
simpleexpr -> ( arithexpr )' , 0 , <=	
simpleexpr -> ( arithexpr )' , 0 , ==	
simpleexpr -> ( arithexpr )' , 0 , >	
simpleexpr -> ( arithexpr )' , 0 , >=	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , <	
simpleexpr -> ID' , 0 , <=	
simpleexpr -> ID' , 0 , ==	
simpleexpr -> ID' , 0 , >	
simpleexpr -> ID' , 0 , >=	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , <	
simpleexpr -> NUM' , 0 , <=	
simpleexpr -> NUM' , 0 , ==	
simpleexpr -> NUM' , 0 , >	
simpleexpr -> NUM' , 0 , >=	
simpleexpr -> NUM' , 0 , E	

-----63-----
multexprprime -> / simpleexpr multexprprime' , 1 , +	
multexprprime -> / simpleexpr multexprprime' , 1 , -	
multexprprime -> / simpleexpr multexprprime' , 1 , <	
multexprprime -> / simpleexpr multexprprime' , 1 , <=	
multexprprime -> / simpleexpr multexprprime' , 1 , ==	
multexprprime -> / simpleexpr multexprprime' , 1 , >	
multexprprime -> / simpleexpr multexprprime' , 1 , >=	
multexprprime -> / simpleexpr multexprprime' , 1 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , <	
simpleexpr -> ( arithexpr )' , 0 , <=	
simpleexpr -> ( arithexpr )' , 0 , ==	
simpleexpr -> ( arithexpr )' , 0 , >	
simpleexpr -> ( arithexpr )' , 0 , >=	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , <	
simpleexpr -> ID' , 0 , <=	
simpleexpr -> ID' , 0 , ==	
simpleexpr -> ID' , 0 , >	
simpleexpr -> ID' , 0 , >=	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , <	
simpleexpr -> NUM' , 0 , <=	
simpleexpr -> NUM' , 0 , ==	
simpleexpr -> NUM' , 0 , >	
simpleexpr -> NUM' , 0 , >=	
simpleexpr -> NUM' , 0 , E	

-----64-----
multexprprime -> E' , 1 , +	
multexprprime -> E' , 1 , -	
multexprprime -> E' , 1 , <	
multexprprime -> E' , 1 , <=	
multexprprime -> E' , 1 , ==	
multexprprime -> E' , 1 , >	
multexprprime -> E' , 1 , >=	
multexprprime -> E' , 1 , E	

-----65-----
multexpr -> simpleexpr  multexprprime' , 2 , +	
multexpr -> simpleexpr  multexprprime' , 2 , -	
multexpr -> simpleexpr  multexprprime' , 2 , <	
multexpr -> simpleexpr  multexprprime' , 2 , <=	
multexpr -> simpleexpr  multexprprime' , 2 , ==	
multexpr -> simpleexpr  multexprprime' , 2 , >	
multexpr -> simpleexpr  multexprprime' , 2 , >=	
multexpr -> simpleexpr  multexprprime' , 2 , E	

-----66-----
assgstmt -> ID = arithexpr ;' , 0 , E	
assgstmt -> ID = arithexpr ;' , 0 , ID	
assgstmt -> ID = arithexpr ;' , 0 , if	
assgstmt -> ID = arithexpr ;' , 0 , while	
assgstmt -> ID = arithexpr ;' , 0 , {	
assgstmt -> ID = arithexpr ;' , 0 , }	
compoundstmt -> { stmts }' , 0 , E	
compoundstmt -> { stmts }' , 0 , ID	
compoundstmt -> { stmts }' , 0 , if	
compoundstmt -> { stmts }' , 0 , while	
compoundstmt -> { stmts }' , 0 , {	
compoundstmt -> { stmts }' , 0 , }	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , }	
stmt -> assgstmt' , 0 , E	
stmt -> assgstmt' , 0 , ID	
stmt -> assgstmt' , 0 , if	
stmt -> assgstmt' , 0 , while	
stmt -> assgstmt' , 0 , {	
stmt -> assgstmt' , 0 , }	
stmt -> compoundstmt' , 0 , E	
stmt -> compoundstmt' , 0 , ID	
stmt -> compoundstmt' , 0 , if	
stmt -> compoundstmt' , 0 , while	
stmt -> compoundstmt' , 0 , {	
stmt -> compoundstmt' , 0 , }	
stmt -> ifstmt' , 0 , E	
stmt -> ifstmt' , 0 , ID	
stmt -> ifstmt' , 0 , if	
stmt -> ifstmt' , 0 , while	
stmt -> ifstmt' , 0 , {	
stmt -> ifstmt' , 0 , }	
stmt -> whilestmt' , 0 , E	
stmt -> whilestmt' , 0 , ID	
stmt -> whilestmt' , 0 , if	
stmt -> whilestmt' , 0 , while	
stmt -> whilestmt' , 0 , {	
stmt -> whilestmt' , 0 , }	
whilestmt -> while ( boolexpr ) stmt' , 0 , E	
whilestmt -> while ( boolexpr ) stmt' , 4 , E	
whilestmt -> while ( boolexpr ) stmt' , 0 , ID	
whilestmt -> while ( boolexpr ) stmt' , 4 , ID	
whilestmt -> while ( boolexpr ) stmt' , 0 , if	
whilestmt -> while ( boolexpr ) stmt' , 4 , if	
whilestmt -> while ( boolexpr ) stmt' , 0 , while	
whilestmt -> while ( boolexpr ) stmt' , 4 , while	
whilestmt -> while ( boolexpr ) stmt' , 0 , {	
whilestmt -> while ( boolexpr ) stmt' , 4 , {	
whilestmt -> while ( boolexpr ) stmt' , 0 , }	
whilestmt -> while ( boolexpr ) stmt' , 4 , }	

-----67-----
simpleexpr -> ( arithexpr )' , 2 , )	
simpleexpr -> ( arithexpr )' , 2 , *	
simpleexpr -> ( arithexpr )' , 2 , +	
simpleexpr -> ( arithexpr )' , 2 , -	
simpleexpr -> ( arithexpr )' , 2 , /	
simpleexpr -> ( arithexpr )' , 2 , E	

-----68-----
simpleexpr -> ( arithexpr )' , 3 , *	
simpleexpr -> ( arithexpr )' , 3 , +	
simpleexpr -> ( arithexpr )' , 3 , -	
simpleexpr -> ( arithexpr )' , 3 , /	
simpleexpr -> ( arithexpr )' , 3 , ;	
simpleexpr -> ( arithexpr )' , 3 , E	

-----69-----
arithexprprime -> + multexpr arithexprprime' , 1 , )	
multexpr -> simpleexpr  multexprprime' , 0 , )	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , )	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , )	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , )	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , E	

-----70-----
arithexprprime -> - multexpr arithexprprime' , 1 , )	
multexpr -> simpleexpr  multexprprime' , 0 , )	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , )	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , )	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , )	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , E	

-----71-----
arithexprprime -> E' , 1 , )	

-----72-----
arithexpr -> multexpr arithexprprime' , 2 , )	

-----73-----
multexprprime -> * simpleexpr multexprprime' , 1 , )	
multexprprime -> * simpleexpr multexprprime' , 1 , +	
multexprprime -> * simpleexpr multexprprime' , 1 , -	
multexprprime -> * simpleexpr multexprprime' , 1 , E	
simpleexpr -> ( arithexpr )' , 0 , )	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , )	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , )	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , E	

-----74-----
multexprprime -> / simpleexpr multexprprime' , 1 , )	
multexprprime -> / simpleexpr multexprprime' , 1 , +	
multexprprime -> / simpleexpr multexprprime' , 1 , -	
multexprprime -> / simpleexpr multexprprime' , 1 , E	
simpleexpr -> ( arithexpr )' , 0 , )	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , )	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , )	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , E	

-----75-----
multexprprime -> E' , 1 , )	
multexprprime -> E' , 1 , +	
multexprprime -> E' , 1 , -	
multexprprime -> E' , 1 , E	

-----76-----
multexpr -> simpleexpr  multexprprime' , 2 , )	
multexpr -> simpleexpr  multexprprime' , 2 , +	
multexpr -> simpleexpr  multexprprime' , 2 , -	
multexpr -> simpleexpr  multexprprime' , 2 , E	

-----77-----
arithexprprime -> + multexpr arithexprprime' , 0 , ;	
arithexprprime -> + multexpr arithexprprime' , 2 , ;	
arithexprprime -> - multexpr arithexprprime' , 0 , ;	
arithexprprime -> E' , 0 , ;	

-----78-----
arithexprprime -> + multexpr arithexprprime' , 0 , ;	
arithexprprime -> - multexpr arithexprprime' , 0 , ;	
arithexprprime -> - multexpr arithexprprime' , 2 , ;	
arithexprprime -> E' , 0 , ;	

-----79-----
multexprprime -> * simpleexpr multexprprime' , 0 , +	
multexprprime -> * simpleexpr multexprprime' , 2 , +	
multexprprime -> * simpleexpr multexprprime' , 0 , -	
multexprprime -> * simpleexpr multexprprime' , 2 , -	
multexprprime -> * simpleexpr multexprprime' , 0 , ;	
multexprprime -> * simpleexpr multexprprime' , 2 , ;	
multexprprime -> * simpleexpr multexprprime' , 0 , E	
multexprprime -> * simpleexpr multexprprime' , 2 , E	
multexprprime -> / simpleexpr multexprprime' , 0 , +	
multexprprime -> / simpleexpr multexprprime' , 0 , -	
multexprprime -> / simpleexpr multexprprime' , 0 , ;	
multexprprime -> / simpleexpr multexprprime' , 0 , E	
multexprprime -> E' , 0 , +	
multexprprime -> E' , 0 , -	
multexprprime -> E' , 0 , ;	
multexprprime -> E' , 0 , E	

-----80-----
multexprprime -> * simpleexpr multexprprime' , 0 , +	
multexprprime -> * simpleexpr multexprprime' , 0 , -	
multexprprime -> * simpleexpr multexprprime' , 0 , ;	
multexprprime -> * simpleexpr multexprprime' , 0 , E	
multexprprime -> / simpleexpr multexprprime' , 0 , +	
multexprprime -> / simpleexpr multexprprime' , 2 , +	
multexprprime -> / simpleexpr multexprprime' , 0 , -	
multexprprime -> / simpleexpr multexprprime' , 2 , -	
multexprprime -> / simpleexpr multexprprime' , 0 , ;	
multexprprime -> / simpleexpr multexprprime' , 2 , ;	
multexprprime -> / simpleexpr multexprprime' , 0 , E	
multexprprime -> / simpleexpr multexprprime' , 2 , E	
multexprprime -> E' , 0 , +	
multexprprime -> E' , 0 , -	
multexprprime -> E' , 0 , ;	
multexprprime -> E' , 0 , E	

-----81-----
simpleexpr -> ( arithexpr )' , 3 , *	
simpleexpr -> ( arithexpr )' , 3 , +	
simpleexpr -> ( arithexpr )' , 3 , -	
simpleexpr -> ( arithexpr )' , 3 , /	
simpleexpr -> ( arithexpr )' , 3 , <	
simpleexpr -> ( arithexpr )' , 3 , <=	
simpleexpr -> ( arithexpr )' , 3 , ==	
simpleexpr -> ( arithexpr )' , 3 , >	
simpleexpr -> ( arithexpr )' , 3 , >=	
simpleexpr -> ( arithexpr )' , 3 , E	

-----82-----
boolexpr -> arithexpr boolop arithexpr' , 3 , )	

-----83-----
assgstmt -> ID = arithexpr ;' , 0 , else	
compoundstmt -> { stmts }' , 0 , else	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 5 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 5 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , else	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 5 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 5 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 5 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 5 , }	
stmt -> assgstmt' , 0 , else	
stmt -> compoundstmt' , 0 , else	
stmt -> ifstmt' , 0 , else	
stmt -> whilestmt' , 0 , else	
whilestmt -> while ( boolexpr ) stmt' , 0 , else	

-----84-----
arithexprprime -> + multexpr arithexprprime' , 0 , <	
arithexprprime -> + multexpr arithexprprime' , 2 , <	
arithexprprime -> + multexpr arithexprprime' , 0 , <=	
arithexprprime -> + multexpr arithexprprime' , 2 , <=	
arithexprprime -> + multexpr arithexprprime' , 0 , ==	
arithexprprime -> + multexpr arithexprprime' , 2 , ==	
arithexprprime -> + multexpr arithexprprime' , 0 , >	
arithexprprime -> + multexpr arithexprprime' , 2 , >	
arithexprprime -> + multexpr arithexprprime' , 0 , >=	
arithexprprime -> + multexpr arithexprprime' , 2 , >=	
arithexprprime -> - multexpr arithexprprime' , 0 , <	
arithexprprime -> - multexpr arithexprprime' , 0 , <=	
arithexprprime -> - multexpr arithexprprime' , 0 , ==	
arithexprprime -> - multexpr arithexprprime' , 0 , >	
arithexprprime -> - multexpr arithexprprime' , 0 , >=	
arithexprprime -> E' , 0 , <	
arithexprprime -> E' , 0 , <=	
arithexprprime -> E' , 0 , ==	
arithexprprime -> E' , 0 , >	
arithexprprime -> E' , 0 , >=	

-----85-----
arithexprprime -> + multexpr arithexprprime' , 0 , <	
arithexprprime -> + multexpr arithexprprime' , 0 , <=	
arithexprprime -> + multexpr arithexprprime' , 0 , ==	
arithexprprime -> + multexpr arithexprprime' , 0 , >	
arithexprprime -> + multexpr arithexprprime' , 0 , >=	
arithexprprime -> - multexpr arithexprprime' , 0 , <	
arithexprprime -> - multexpr arithexprprime' , 2 , <	
arithexprprime -> - multexpr arithexprprime' , 0 , <=	
arithexprprime -> - multexpr arithexprprime' , 2 , <=	
arithexprprime -> - multexpr arithexprprime' , 0 , ==	
arithexprprime -> - multexpr arithexprprime' , 2 , ==	
arithexprprime -> - multexpr arithexprprime' , 0 , >	
arithexprprime -> - multexpr arithexprprime' , 2 , >	
arithexprprime -> - multexpr arithexprprime' , 0 , >=	
arithexprprime -> - multexpr arithexprprime' , 2 , >=	
arithexprprime -> E' , 0 , <	
arithexprprime -> E' , 0 , <=	
arithexprprime -> E' , 0 , ==	
arithexprprime -> E' , 0 , >	
arithexprprime -> E' , 0 , >=	

-----86-----
multexprprime -> * simpleexpr multexprprime' , 0 , +	
multexprprime -> * simpleexpr multexprprime' , 2 , +	
multexprprime -> * simpleexpr multexprprime' , 0 , -	
multexprprime -> * simpleexpr multexprprime' , 2 , -	
multexprprime -> * simpleexpr multexprprime' , 0 , <	
multexprprime -> * simpleexpr multexprprime' , 2 , <	
multexprprime -> * simpleexpr multexprprime' , 0 , <=	
multexprprime -> * simpleexpr multexprprime' , 2 , <=	
multexprprime -> * simpleexpr multexprprime' , 0 , ==	
multexprprime -> * simpleexpr multexprprime' , 2 , ==	
multexprprime -> * simpleexpr multexprprime' , 0 , >	
multexprprime -> * simpleexpr multexprprime' , 2 , >	
multexprprime -> * simpleexpr multexprprime' , 0 , >=	
multexprprime -> * simpleexpr multexprprime' , 2 , >=	
multexprprime -> * simpleexpr multexprprime' , 0 , E	
multexprprime -> * simpleexpr multexprprime' , 2 , E	
multexprprime -> / simpleexpr multexprprime' , 0 , +	
multexprprime -> / simpleexpr multexprprime' , 0 , -	
multexprprime -> / simpleexpr multexprprime' , 0 , <	
multexprprime -> / simpleexpr multexprprime' , 0 , <=	
multexprprime -> / simpleexpr multexprprime' , 0 , ==	
multexprprime -> / simpleexpr multexprprime' , 0 , >	
multexprprime -> / simpleexpr multexprprime' , 0 , >=	
multexprprime -> / simpleexpr multexprprime' , 0 , E	
multexprprime -> E' , 0 , +	
multexprprime -> E' , 0 , -	
multexprprime -> E' , 0 , <	
multexprprime -> E' , 0 , <=	
multexprprime -> E' , 0 , ==	
multexprprime -> E' , 0 , >	
multexprprime -> E' , 0 , >=	
multexprprime -> E' , 0 , E	

-----87-----
multexprprime -> * simpleexpr multexprprime' , 0 , +	
multexprprime -> * simpleexpr multexprprime' , 0 , -	
multexprprime -> * simpleexpr multexprprime' , 0 , <	
multexprprime -> * simpleexpr multexprprime' , 0 , <=	
multexprprime -> * simpleexpr multexprprime' , 0 , ==	
multexprprime -> * simpleexpr multexprprime' , 0 , >	
multexprprime -> * simpleexpr multexprprime' , 0 , >=	
multexprprime -> * simpleexpr multexprprime' , 0 , E	
multexprprime -> / simpleexpr multexprprime' , 0 , +	
multexprprime -> / simpleexpr multexprprime' , 2 , +	
multexprprime -> / simpleexpr multexprprime' , 0 , -	
multexprprime -> / simpleexpr multexprprime' , 2 , -	
multexprprime -> / simpleexpr multexprprime' , 0 , <	
multexprprime -> / simpleexpr multexprprime' , 2 , <	
multexprprime -> / simpleexpr multexprprime' , 0 , <=	
multexprprime -> / simpleexpr multexprprime' , 2 , <=	
multexprprime -> / simpleexpr multexprprime' , 0 , ==	
multexprprime -> / simpleexpr multexprprime' , 2 , ==	
multexprprime -> / simpleexpr multexprprime' , 0 , >	
multexprprime -> / simpleexpr multexprprime' , 2 , >	
multexprprime -> / simpleexpr multexprprime' , 0 , >=	
multexprprime -> / simpleexpr multexprprime' , 2 , >=	
multexprprime -> / simpleexpr multexprprime' , 0 , E	
multexprprime -> / simpleexpr multexprprime' , 2 , E	
multexprprime -> E' , 0 , +	
multexprprime -> E' , 0 , -	
multexprprime -> E' , 0 , <	
multexprprime -> E' , 0 , <=	
multexprprime -> E' , 0 , ==	
multexprprime -> E' , 0 , >	
multexprprime -> E' , 0 , >=	
multexprprime -> E' , 0 , E	

-----88-----
whilestmt -> while ( boolexpr ) stmt' , 5 , E	
whilestmt -> while ( boolexpr ) stmt' , 5 , ID	
whilestmt -> while ( boolexpr ) stmt' , 5 , if	
whilestmt -> while ( boolexpr ) stmt' , 5 , while	
whilestmt -> while ( boolexpr ) stmt' , 5 , {	
whilestmt -> while ( boolexpr ) stmt' , 5 , }	

-----89-----
simpleexpr -> ( arithexpr )' , 3 , )	
simpleexpr -> ( arithexpr )' , 3 , *	
simpleexpr -> ( arithexpr )' , 3 , +	
simpleexpr -> ( arithexpr )' , 3 , -	
simpleexpr -> ( arithexpr )' , 3 , /	
simpleexpr -> ( arithexpr )' , 3 , E	

-----90-----
arithexprprime -> + multexpr arithexprprime' , 0 , )	
arithexprprime -> + multexpr arithexprprime' , 2 , )	
arithexprprime -> - multexpr arithexprprime' , 0 , )	
arithexprprime -> E' , 0 , )	

-----91-----
arithexprprime -> + multexpr arithexprprime' , 0 , )	
arithexprprime -> - multexpr arithexprprime' , 0 , )	
arithexprprime -> - multexpr arithexprprime' , 2 , )	
arithexprprime -> E' , 0 , )	

-----92-----
multexprprime -> * simpleexpr multexprprime' , 0 , )	
multexprprime -> * simpleexpr multexprprime' , 2 , )	
multexprprime -> * simpleexpr multexprprime' , 0 , +	
multexprprime -> * simpleexpr multexprprime' , 2 , +	
multexprprime -> * simpleexpr multexprprime' , 0 , -	
multexprprime -> * simpleexpr multexprprime' , 2 , -	
multexprprime -> * simpleexpr multexprprime' , 0 , E	
multexprprime -> * simpleexpr multexprprime' , 2 , E	
multexprprime -> / simpleexpr multexprprime' , 0 , )	
multexprprime -> / simpleexpr multexprprime' , 0 , +	
multexprprime -> / simpleexpr multexprprime' , 0 , -	
multexprprime -> / simpleexpr multexprprime' , 0 , E	
multexprprime -> E' , 0 , )	
multexprprime -> E' , 0 , +	
multexprprime -> E' , 0 , -	
multexprprime -> E' , 0 , E	

-----93-----
multexprprime -> * simpleexpr multexprprime' , 0 , )	
multexprprime -> * simpleexpr multexprprime' , 0 , +	
multexprprime -> * simpleexpr multexprprime' , 0 , -	
multexprprime -> * simpleexpr multexprprime' , 0 , E	
multexprprime -> / simpleexpr multexprprime' , 0 , )	
multexprprime -> / simpleexpr multexprprime' , 2 , )	
multexprprime -> / simpleexpr multexprprime' , 0 , +	
multexprprime -> / simpleexpr multexprprime' , 2 , +	
multexprprime -> / simpleexpr multexprprime' , 0 , -	
multexprprime -> / simpleexpr multexprprime' , 2 , -	
multexprprime -> / simpleexpr multexprprime' , 0 , E	
multexprprime -> / simpleexpr multexprprime' , 2 , E	
multexprprime -> E' , 0 , )	
multexprprime -> E' , 0 , +	
multexprprime -> E' , 0 , -	
multexprprime -> E' , 0 , E	

-----94-----
arithexprprime -> + multexpr arithexprprime' , 3 , ;	

-----95-----
arithexprprime -> - multexpr arithexprprime' , 3 , ;	

-----96-----
multexprprime -> * simpleexpr multexprprime' , 3 , +	
multexprprime -> * simpleexpr multexprprime' , 3 , -	
multexprprime -> * simpleexpr multexprprime' , 3 , ;	
multexprprime -> * simpleexpr multexprprime' , 3 , E	

-----97-----
multexprprime -> / simpleexpr multexprprime' , 3 , +	
multexprprime -> / simpleexpr multexprprime' , 3 , -	
multexprprime -> / simpleexpr multexprprime' , 3 , ;	
multexprprime -> / simpleexpr multexprprime' , 3 , E	

-----98-----
assgstmt -> ID = arithexpr ;' , 1 , else	

-----99-----
stmt -> assgstmt' , 1 , else	

-----100-----
stmt -> compoundstmt' , 1 , else	

-----101-----
ifstmt -> if ( boolexpr ) then stmt else stmt' , 1 , else	

-----102-----
stmt -> ifstmt' , 1 , else	

-----103-----
ifstmt -> if ( boolexpr ) then stmt else stmt' , 6 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 6 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 6 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 6 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 6 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 6 , }	

-----104-----
whilestmt -> while ( boolexpr ) stmt' , 1 , else	

-----105-----
stmt -> whilestmt' , 1 , else	

-----106-----
assgstmt -> ID = arithexpr ;' , 0 , E	
assgstmt -> ID = arithexpr ;' , 0 , ID	
assgstmt -> ID = arithexpr ;' , 0 , if	
assgstmt -> ID = arithexpr ;' , 0 , while	
assgstmt -> ID = arithexpr ;' , 0 , {	
assgstmt -> ID = arithexpr ;' , 0 , }	
compoundstmt -> { stmts }' , 0 , E	
compoundstmt -> { stmts }' , 0 , ID	
compoundstmt -> { stmts }' , 1 , else	
compoundstmt -> { stmts }' , 0 , if	
compoundstmt -> { stmts }' , 0 , while	
compoundstmt -> { stmts }' , 0 , {	
compoundstmt -> { stmts }' , 0 , }	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , }	
stmt -> assgstmt' , 0 , E	
stmt -> assgstmt' , 0 , ID	
stmt -> assgstmt' , 0 , if	
stmt -> assgstmt' , 0 , while	
stmt -> assgstmt' , 0 , {	
stmt -> assgstmt' , 0 , }	
stmt -> compoundstmt' , 0 , E	
stmt -> compoundstmt' , 0 , ID	
stmt -> compoundstmt' , 0 , if	
stmt -> compoundstmt' , 0 , while	
stmt -> compoundstmt' , 0 , {	
stmt -> compoundstmt' , 0 , }	
stmt -> ifstmt' , 0 , E	
stmt -> ifstmt' , 0 , ID	
stmt -> ifstmt' , 0 , if	
stmt -> ifstmt' , 0 , while	
stmt -> ifstmt' , 0 , {	
stmt -> ifstmt' , 0 , }	
stmt -> whilestmt' , 0 , E	
stmt -> whilestmt' , 0 , ID	
stmt -> whilestmt' , 0 , if	
stmt -> whilestmt' , 0 , while	
stmt -> whilestmt' , 0 , {	
stmt -> whilestmt' , 0 , }	
stmts -> E' , 0 , }	
stmts -> stmt stmts' , 0 , }	
whilestmt -> while ( boolexpr ) stmt' , 0 , E	
whilestmt -> while ( boolexpr ) stmt' , 0 , ID	
whilestmt -> while ( boolexpr ) stmt' , 0 , if	
whilestmt -> while ( boolexpr ) stmt' , 0 , while	
whilestmt -> while ( boolexpr ) stmt' , 0 , {	
whilestmt -> while ( boolexpr ) stmt' , 0 , }	

-----107-----
arithexprprime -> + multexpr arithexprprime' , 3 , <	
arithexprprime -> + multexpr arithexprprime' , 3 , <=	
arithexprprime -> + multexpr arithexprprime' , 3 , ==	
arithexprprime -> + multexpr arithexprprime' , 3 , >	
arithexprprime -> + multexpr arithexprprime' , 3 , >=	

-----108-----
arithexprprime -> - multexpr arithexprprime' , 3 , <	
arithexprprime -> - multexpr arithexprprime' , 3 , <=	
arithexprprime -> - multexpr arithexprprime' , 3 , ==	
arithexprprime -> - multexpr arithexprprime' , 3 , >	
arithexprprime -> - multexpr arithexprprime' , 3 , >=	

-----109-----
multexprprime -> * simpleexpr multexprprime' , 3 , +	
multexprprime -> * simpleexpr multexprprime' , 3 , -	
multexprprime -> * simpleexpr multexprprime' , 3 , <	
multexprprime -> * simpleexpr multexprprime' , 3 , <=	
multexprprime -> * simpleexpr multexprprime' , 3 , ==	
multexprprime -> * simpleexpr multexprprime' , 3 , >	
multexprprime -> * simpleexpr multexprprime' , 3 , >=	
multexprprime -> * simpleexpr multexprprime' , 3 , E	

-----110-----
multexprprime -> / simpleexpr multexprprime' , 3 , +	
multexprprime -> / simpleexpr multexprprime' , 3 , -	
multexprprime -> / simpleexpr multexprprime' , 3 , <	
multexprprime -> / simpleexpr multexprprime' , 3 , <=	
multexprprime -> / simpleexpr multexprprime' , 3 , ==	
multexprprime -> / simpleexpr multexprprime' , 3 , >	
multexprprime -> / simpleexpr multexprprime' , 3 , >=	
multexprprime -> / simpleexpr multexprprime' , 3 , E	

-----111-----
arithexprprime -> + multexpr arithexprprime' , 3 , )	

-----112-----
arithexprprime -> - multexpr arithexprprime' , 3 , )	

-----113-----
multexprprime -> * simpleexpr multexprprime' , 3 , )	
multexprprime -> * simpleexpr multexprprime' , 3 , +	
multexprprime -> * simpleexpr multexprprime' , 3 , -	
multexprprime -> * simpleexpr multexprprime' , 3 , E	

-----114-----
multexprprime -> / simpleexpr multexprprime' , 3 , )	
multexprprime -> / simpleexpr multexprprime' , 3 , +	
multexprprime -> / simpleexpr multexprprime' , 3 , -	
multexprprime -> / simpleexpr multexprprime' , 3 , E	

-----115-----
arithexpr -> multexpr arithexprprime' , 0 , ;	
assgstmt -> ID = arithexpr ;' , 2 , else	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , ;	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , ;	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , ;	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , ;	
simpleexpr -> NUM' , 0 , E	

-----116-----
arithexpr -> multexpr arithexprprime' , 0 , <	
arithexpr -> multexpr arithexprprime' , 0 , <=	
arithexpr -> multexpr arithexprprime' , 0 , ==	
arithexpr -> multexpr arithexprprime' , 0 , >	
arithexpr -> multexpr arithexprprime' , 0 , >=	
boolexpr -> arithexpr boolop arithexpr' , 0 , )	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 2 , else	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , <	
multexpr -> simpleexpr  multexprprime' , 0 , <=	
multexpr -> simpleexpr  multexprprime' , 0 , ==	
multexpr -> simpleexpr  multexprprime' , 0 , >	
multexpr -> simpleexpr  multexprprime' , 0 , >=	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , <	
simpleexpr -> ( arithexpr )' , 0 , <=	
simpleexpr -> ( arithexpr )' , 0 , ==	
simpleexpr -> ( arithexpr )' , 0 , >	
simpleexpr -> ( arithexpr )' , 0 , >=	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , <	
simpleexpr -> ID' , 0 , <=	
simpleexpr -> ID' , 0 , ==	
simpleexpr -> ID' , 0 , >	
simpleexpr -> ID' , 0 , >=	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , <	
simpleexpr -> NUM' , 0 , <=	
simpleexpr -> NUM' , 0 , ==	
simpleexpr -> NUM' , 0 , >	
simpleexpr -> NUM' , 0 , >=	
simpleexpr -> NUM' , 0 , E	

-----117-----
assgstmt -> ID = arithexpr ;' , 0 , E	
assgstmt -> ID = arithexpr ;' , 0 , ID	
assgstmt -> ID = arithexpr ;' , 0 , if	
assgstmt -> ID = arithexpr ;' , 0 , while	
assgstmt -> ID = arithexpr ;' , 0 , {	
assgstmt -> ID = arithexpr ;' , 0 , }	
compoundstmt -> { stmts }' , 0 , E	
compoundstmt -> { stmts }' , 0 , ID	
compoundstmt -> { stmts }' , 0 , if	
compoundstmt -> { stmts }' , 0 , while	
compoundstmt -> { stmts }' , 0 , {	
compoundstmt -> { stmts }' , 0 , }	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 7 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 7 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 7 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 7 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 7 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , }	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 7 , }	
stmt -> assgstmt' , 0 , E	
stmt -> assgstmt' , 0 , ID	
stmt -> assgstmt' , 0 , if	
stmt -> assgstmt' , 0 , while	
stmt -> assgstmt' , 0 , {	
stmt -> assgstmt' , 0 , }	
stmt -> compoundstmt' , 0 , E	
stmt -> compoundstmt' , 0 , ID	
stmt -> compoundstmt' , 0 , if	
stmt -> compoundstmt' , 0 , while	
stmt -> compoundstmt' , 0 , {	
stmt -> compoundstmt' , 0 , }	
stmt -> ifstmt' , 0 , E	
stmt -> ifstmt' , 0 , ID	
stmt -> ifstmt' , 0 , if	
stmt -> ifstmt' , 0 , while	
stmt -> ifstmt' , 0 , {	
stmt -> ifstmt' , 0 , }	
stmt -> whilestmt' , 0 , E	
stmt -> whilestmt' , 0 , ID	
stmt -> whilestmt' , 0 , if	
stmt -> whilestmt' , 0 , while	
stmt -> whilestmt' , 0 , {	
stmt -> whilestmt' , 0 , }	
whilestmt -> while ( boolexpr ) stmt' , 0 , E	
whilestmt -> while ( boolexpr ) stmt' , 0 , ID	
whilestmt -> while ( boolexpr ) stmt' , 0 , if	
whilestmt -> while ( boolexpr ) stmt' , 0 , while	
whilestmt -> while ( boolexpr ) stmt' , 0 , {	
whilestmt -> while ( boolexpr ) stmt' , 0 , }	

-----118-----
arithexpr -> multexpr arithexprprime' , 0 , <	
arithexpr -> multexpr arithexprprime' , 0 , <=	
arithexpr -> multexpr arithexprprime' , 0 , ==	
arithexpr -> multexpr arithexprprime' , 0 , >	
arithexpr -> multexpr arithexprprime' , 0 , >=	
boolexpr -> arithexpr boolop arithexpr' , 0 , )	
multexpr -> simpleexpr  multexprprime' , 0 , +	
multexpr -> simpleexpr  multexprprime' , 0 , -	
multexpr -> simpleexpr  multexprprime' , 0 , <	
multexpr -> simpleexpr  multexprprime' , 0 , <=	
multexpr -> simpleexpr  multexprprime' , 0 , ==	
multexpr -> simpleexpr  multexprprime' , 0 , >	
multexpr -> simpleexpr  multexprprime' , 0 , >=	
multexpr -> simpleexpr  multexprprime' , 0 , E	
simpleexpr -> ( arithexpr )' , 0 , *	
simpleexpr -> ( arithexpr )' , 0 , +	
simpleexpr -> ( arithexpr )' , 0 , -	
simpleexpr -> ( arithexpr )' , 0 , /	
simpleexpr -> ( arithexpr )' , 0 , <	
simpleexpr -> ( arithexpr )' , 0 , <=	
simpleexpr -> ( arithexpr )' , 0 , ==	
simpleexpr -> ( arithexpr )' , 0 , >	
simpleexpr -> ( arithexpr )' , 0 , >=	
simpleexpr -> ( arithexpr )' , 0 , E	
simpleexpr -> ID' , 0 , *	
simpleexpr -> ID' , 0 , +	
simpleexpr -> ID' , 0 , -	
simpleexpr -> ID' , 0 , /	
simpleexpr -> ID' , 0 , <	
simpleexpr -> ID' , 0 , <=	
simpleexpr -> ID' , 0 , ==	
simpleexpr -> ID' , 0 , >	
simpleexpr -> ID' , 0 , >=	
simpleexpr -> ID' , 0 , E	
simpleexpr -> NUM' , 0 , *	
simpleexpr -> NUM' , 0 , +	
simpleexpr -> NUM' , 0 , -	
simpleexpr -> NUM' , 0 , /	
simpleexpr -> NUM' , 0 , <	
simpleexpr -> NUM' , 0 , <=	
simpleexpr -> NUM' , 0 , ==	
simpleexpr -> NUM' , 0 , >	
simpleexpr -> NUM' , 0 , >=	
simpleexpr -> NUM' , 0 , E	
whilestmt -> while ( boolexpr ) stmt' , 2 , else	

-----119-----
compoundstmt -> { stmts }' , 2 , else	

-----120-----
assgstmt -> ID = arithexpr ;' , 3 , else	

-----121-----
ifstmt -> if ( boolexpr ) then stmt else stmt' , 3 , else	

-----122-----
ifstmt -> if ( boolexpr ) then stmt else stmt' , 8 , E	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 8 , ID	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 8 , if	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 8 , while	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 8 , {	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 8 , }	

-----123-----
whilestmt -> while ( boolexpr ) stmt' , 3 , else	

-----124-----
compoundstmt -> { stmts }' , 3 , else	

-----125-----
assgstmt -> ID = arithexpr ;' , 4 , else	

-----126-----
ifstmt -> if ( boolexpr ) then stmt else stmt' , 4 , else	

-----127-----
assgstmt -> ID = arithexpr ;' , 0 , else	
compoundstmt -> { stmts }' , 0 , else	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , else	
stmt -> assgstmt' , 0 , else	
stmt -> compoundstmt' , 0 , else	
stmt -> ifstmt' , 0 , else	
stmt -> whilestmt' , 0 , else	
whilestmt -> while ( boolexpr ) stmt' , 0 , else	
whilestmt -> while ( boolexpr ) stmt' , 4 , else	

-----128-----
assgstmt -> ID = arithexpr ;' , 0 , else	
compoundstmt -> { stmts }' , 0 , else	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , else	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 5 , else	
stmt -> assgstmt' , 0 , else	
stmt -> compoundstmt' , 0 , else	
stmt -> ifstmt' , 0 , else	
stmt -> whilestmt' , 0 , else	
whilestmt -> while ( boolexpr ) stmt' , 0 , else	

-----129-----
whilestmt -> while ( boolexpr ) stmt' , 5 , else	

-----130-----
ifstmt -> if ( boolexpr ) then stmt else stmt' , 6 , else	

-----131-----
assgstmt -> ID = arithexpr ;' , 0 , else	
compoundstmt -> { stmts }' , 0 , else	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 0 , else	
ifstmt -> if ( boolexpr ) then stmt else stmt' , 7 , else	
stmt -> assgstmt' , 0 , else	
stmt -> compoundstmt' , 0 , else	
stmt -> ifstmt' , 0 , else	
stmt -> whilestmt' , 0 , else	
whilestmt -> while ( boolexpr ) stmt' , 0 , else	

-----132-----
ifstmt -> if ( boolexpr ) then stmt else stmt' , 8 , else	

## LR1Table:

0 : 	compoundstmt g1		{ s2	 
1 : 	$ acc0	 
2 : 	stmts g10		whilestmt g12		assgstmt g5		ifstmt g8		E s3		compoundstmt g6		ID s4		{ s13		while s11		if s7		stmt g9	 
3 : 	} r6	 
4 : 	= s14	 
5 : 	E r26		ID r26		{ r26		while r26		if r26		} r26	 
6 : 	E r27		ID r27		{ r27		while r27		if r27		} r27	 
7 : 	( s15	 
8 : 	E r24		ID r24		{ r24		while r24		if r24		} r24	 
9 : 	whilestmt g12		stmts g16		assgstmt g5		ifstmt g8		E s3		compoundstmt g6		ID s4		{ s13		while s11		if s7		stmt g9	 
10 : 	} s17	 
11 : 	( s18	 
12 : 	E r25		ID r25		{ r25		while r25		if r25		} r25	 
13 : 	stmts g19		whilestmt g12		assgstmt g5		ifstmt g8		E s3		compoundstmt g6		ID s4		{ s13		while s11		if s7		stmt g9	 
14 : 	multexpr g24		simpleexpr g25		NUM s22		( s20		ID s21		arithexpr g23	 
15 : 	multexpr g31		simpleexpr g32		NUM s28		( s26		ID s27		arithexpr g29		boolexpr g30	 
16 : 	} r5	 
17 : 	$ r8	 
18 : 	multexpr g31		simpleexpr g32		NUM s28		( s26		ID s27		arithexpr g29		boolexpr g33	 
19 : 	} s34	 
20 : 	multexpr g39		simpleexpr g40		NUM s37		( s35		ID s36		arithexpr g38	 
21 : 	E r17		* r17		+ r17		; r17		- r17		/ r17	 
22 : 	E r18		* r18		+ r18		; r18		- r18		/ r18	 
23 : 	; s41	 
24 : 	E s44		arithexprprime g45		+ s42		- s43	 
25 : 	multexprprime g49		E s48		* s46		/ s47	 
26 : 	multexpr g39		simpleexpr g40		NUM s37		( s35		ID s36		arithexpr g50	 
27 : 	== r17		<= r17		E r17		* r17		+ r17		< r17		- r17		> r17		/ r17		>= r17	 
28 : 	== r18		<= r18		E r18		* r18		+ r18		< r18		- r18		> r18		/ r18		>= r18	 
29 : 	boolop g56		== s53		<= s52		< s51		> s54		>= s55	 
30 : 	) s57	 
31 : 	E s60		arithexprprime g61		+ s58		- s59	 
32 : 	multexprprime g65		E s64		* s62		/ s63	 
33 : 	) s66	 
34 : 	E r8		ID r8		{ r8		while r8		if r8		} r8	 
35 : 	multexpr g39		simpleexpr g40		NUM s37		( s35		ID s36		arithexpr g67	 
36 : 	E r17		) r17		* r17		+ r17		- r17		/ r17	 
37 : 	E r18		) r18		* r18		+ r18		- r18		/ r18	 
38 : 	) s68	 
39 : 	E s71		arithexprprime g72		+ s69		- s70	 
40 : 	multexprprime g76		E s75		* s73		/ s74	 
41 : 	E r15		ID r15		{ r15		while r15		if r15		} r15	 
42 : 	multexpr g77		simpleexpr g25		NUM s22		( s20		ID s21	 
43 : 	multexpr g78		simpleexpr g25		NUM s22		( s20		ID s21	 
44 : 	; r22	 
45 : 	; r23	 
46 : 	simpleexpr g79		NUM s22		( s20		ID s21	 
47 : 	simpleexpr g80		NUM s22		( s20		ID s21	 
48 : 	E r14		+ r14		; r14		- r14	 
49 : 	E r16		+ r16		; r16		- r16	 
50 : 	) s81	 
51 : 	NUM r0		( r0		ID r0	 
52 : 	NUM r2		( r2		ID r2	 
53 : 	NUM r4		( r4		ID r4	 
54 : 	NUM r1		( r1		ID r1	 
55 : 	NUM r3		( r3		ID r3	 
56 : 	multexpr g39		simpleexpr g40		NUM s37		( s35		ID s36		arithexpr g82	 
57 : 	then s83	 
58 : 	multexpr g84		simpleexpr g32		NUM s28		( s26		ID s27	 
59 : 	multexpr g85		simpleexpr g32		NUM s28		( s26		ID s27	 
60 : 	== r22		<= r22		< r22		> r22		>= r22	 
61 : 	== r23		<= r23		< r23		> r23		>= r23	 
62 : 	simpleexpr g86		NUM s28		( s26		ID s27	 
63 : 	simpleexpr g87		NUM s28		( s26		ID s27	 
64 : 	== r14		<= r14		E r14		+ r14		< r14		- r14		> r14		>= r14	 
65 : 	== r16		<= r16		E r16		+ r16		< r16		- r16		> r16		>= r16	 
66 : 	whilestmt g12		assgstmt g5		ifstmt g8		compoundstmt g6		ID s4		{ s13		while s11		if s7		stmt g88	 
67 : 	) s89	 
68 : 	E r19		* r19		+ r19		; r19		- r19		/ r19	 
69 : 	multexpr g90		simpleexpr g40		NUM s37		( s35		ID s36	 
70 : 	multexpr g91		simpleexpr g40		NUM s37		( s35		ID s36	 
71 : 	) r22	 
72 : 	) r23	 
73 : 	simpleexpr g92		NUM s37		( s35		ID s36	 
74 : 	simpleexpr g93		NUM s37		( s35		ID s36	 
75 : 	E r14		) r14		+ r14		- r14	 
76 : 	E r16		) r16		+ r16		- r16	 
77 : 	E s44		arithexprprime g94		+ s42		- s43	 
78 : 	E s44		arithexprprime g95		+ s42		- s43	 
79 : 	multexprprime g96		E s48		* s46		/ s47	 
80 : 	multexprprime g97		E s48		* s46		/ s47	 
81 : 	== r19		<= r19		E r19		* r19		+ r19		< r19		- r19		> r19		/ r19		>= r19	 
82 : 	) r10	 
83 : 	whilestmt g105		assgstmt g99		ifstmt g102		compoundstmt g100		ID s98		{ s106		while s104		if s101		stmt g103	 
84 : 	E s60		arithexprprime g107		+ s58		- s59	 
85 : 	E s60		arithexprprime g108		+ s58		- s59	 
86 : 	multexprprime g109		E s64		* s62		/ s63	 
87 : 	multexprprime g110		E s64		* s62		/ s63	 
88 : 	E r11		ID r11		{ r11		while r11		if r11		} r11	 
89 : 	E r19		) r19		* r19		+ r19		- r19		/ r19	 
90 : 	E s71		arithexprprime g111		+ s69		- s70	 
91 : 	E s71		arithexprprime g112		+ s69		- s70	 
92 : 	multexprprime g113		E s75		* s73		/ s74	 
93 : 	multexprprime g114		E s75		* s73		/ s74	 
94 : 	; r20	 
95 : 	; r21	 
96 : 	E r12		+ r12		; r12		- r12	 
97 : 	E r13		+ r13		; r13		- r13	 
98 : 	= s115	 
99 : 	else r26	 
100 : 	else r27	 
101 : 	( s116	 
102 : 	else r24	 
103 : 	else s117	 
104 : 	( s118	 
105 : 	else r25	 
106 : 	stmts g119		whilestmt g12		assgstmt g5		ifstmt g8		E s3		compoundstmt g6		ID s4		{ s13		while s11		if s7		stmt g9	 
107 : 	== r20		<= r20		< r20		> r20		>= r20	 
108 : 	== r21		<= r21		< r21		> r21		>= r21	 
109 : 	== r12		<= r12		E r12		+ r12		< r12		- r12		> r12		>= r12	 
110 : 	== r13		<= r13		E r13		+ r13		< r13		- r13		> r13		>= r13	 
111 : 	) r20	 
112 : 	) r21	 
113 : 	E r12		) r12		+ r12		- r12	 
114 : 	E r13		) r13		+ r13		- r13	 
115 : 	multexpr g24		simpleexpr g25		NUM s22		( s20		ID s21		arithexpr g120	 
116 : 	multexpr g31		simpleexpr g32		NUM s28		( s26		ID s27		arithexpr g29		boolexpr g121	 
117 : 	whilestmt g12		assgstmt g5		ifstmt g8		compoundstmt g6		ID s4		{ s13		while s11		if s7		stmt g122	 
118 : 	multexpr g31		simpleexpr g32		NUM s28		( s26		ID s27		arithexpr g29		boolexpr g123	 
119 : 	} s124	 
120 : 	; s125	 
121 : 	) s126	 
122 : 	E r7		ID r7		{ r7		while r7		if r7		} r7	 
123 : 	) s127	 
124 : 	else r8	 
125 : 	else r15	 
126 : 	then s128	 
127 : 	whilestmt g105		assgstmt g99		ifstmt g102		compoundstmt g100		ID s98		{ s106		while s104		if s101		stmt g129	 
128 : 	whilestmt g105		assgstmt g99		ifstmt g102		compoundstmt g100		ID s98		{ s106		while s104		if s101		stmt g130	 
129 : 	else r11	 
130 : 	else s131	 
131 : 	whilestmt g105		assgstmt g99		ifstmt g102		compoundstmt g100		ID s98		{ s106		while s104		if s101		stmt g132	 
132 : 	else r7



## 测试输入	

{ ID = NUM ; }
{ while ( ID == NUM ) { ID = NUM } }
{ ID = ID + NUM ; }

