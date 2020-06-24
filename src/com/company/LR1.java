package com.company;

import java.util.*;

public class LR1 {
    private String[] originExpression ={
            "program -> compoundstmt",
            "stmt ->  ifstmt  |  whilestmt  |  assgstmt  |  compoundstmt",
            "compoundstmt ->  { stmts }",
            "stmts ->  stmt stmts   |   E",
            "ifstmt ->  if ( boolexpr ) then stmt else stmt",
            "whilestmt ->  while ( boolexpr ) stmt",
            "assgstmt ->  ID = arithexpr ;",
            "boolexpr  ->  arithexpr boolop arithexpr",
            "boolop ->   <  |  >  |  <=  |  >=  | ==",
            "arithexpr  ->  multexpr arithexprprime",
            "arithexprprime ->  + multexpr arithexprprime  |  - multexpr arithexprprime  |   E",
            "multexpr ->  simpleexpr  multexprprime",
            "multexprprime ->  * simpleexpr multexprprime  |  / simpleexpr multexprprime  |   E",
            "simpleexpr ->  ID  |  NUM  |  ( arithexpr )"
    };
    private HashMap<String, ArrayList<String>> expressionMap = new HashMap<>();
    //开始符
    private String start= "program";
    //非终结符集合
    private String[] variable = {"program","compoundstmt", "stmt","stmts","ifstmt","whilestmt","assgstmt","boolexpr","boolop",
            "whilestmt","assgstmt","arithexpr","arithexpr","arithexprprime","simpleexpr","multexprprime","multexpr"};
    private List<String> variableList = Arrays.asList(variable);;
    //终结符集合
    private String[] terminal = {"{","}","if","(",")","then","else","while","ID","=",">","<","==",">=","<=","+","-","/","*","ID","NUM","E",";","$"};
    private List<String> terminalList =  Arrays.asList(terminal);
    class Item implements Comparable{
        Expression expression = new Expression("program -> compoundstmt");
        int position = 0; //位置来表示项目
        String expect_symbol;
        int max_position = 0;

        public Item(Expression expression, int position,String expect_symbol) {
            this.expression = expression;
            this.position = position;
            this.expect_symbol=expect_symbol;
            max_position = expression.getRights().length;
        }
        public String getBetaSymbol(){
            if(position<max_position-1){
                return expression.rights[position+1];
            }
            else {
                return expect_symbol;
            }
        }
        public String getFollowSymbol(){
            return expression.rights[position];
        }
        public int getPosition() {
            return position;
        }
        public Expression getExpression() {
            return expression;
        }
        public String getExpect_symbol() {
            return expect_symbol;
        }

        public int getMax_position() {
            return max_position;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Item item = (Item) o;

            if (position != item.position) {
                return false;
            }
            if (max_position != item.max_position) {
                return false;
            }
            if (!expression.equals(item.expression)) {
                return false;
            }
            return expect_symbol.equals(item.expect_symbol);
        }

        @Override
        public int hashCode() {
            int result = expression.hashCode();
            result = 31 * result + position;
            result = 31 * result + expect_symbol.hashCode();
            result = 31 * result + max_position;
            return result;
        }

        @Override
        public int compareTo(Object o) {
            if(! (o instanceof Item))//先判断obj是Item类的实例
            {
                throw new RuntimeException("不是Item对象");//抛出运行时异常
            }
            Item item2 = (Item)o;//强转s
            int res = this.getExpression().getOriginExpression().compareTo(item2.getExpression().getOriginExpression());
            if(res == 0){
                res = this.getExpect_symbol().compareTo(item2.getExpect_symbol());
                if (res == 0){
                    res = this.getPosition()-item2.getPosition();
                }
            }
            return res;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "expression=" + expression +
                    ", position=" + position +
                    ", expect_symbol='" + expect_symbol + '\'' +
                    ", max_position=" + max_position +
                    '}';
        }
    }
    //判断是否是终结符
    public boolean isTerminal(String symbol){
        return terminalList.contains(symbol);
    }
    //判断是否是非终结符
    public boolean isVariable(String symbol){
        return variableList.contains(symbol);
    }
    private TreeSet<String> symbolSet = new TreeSet<>();
    private void initSymbolSet(){
        symbolSet.addAll(variableList);
        symbolSet.addAll(terminalList);
        /*System.out.println("----Symbol: ----");
        for(String symbol : symbolSet){
            System.out.print(symbol+"  ");
        }
        System.out.println("\n------------");*/
    }
    private Map<Integer,String> expressionStringMap = new HashMap<>();
    private Map<String,Integer> expressionNumberMap = new HashMap<>();
    //拆分|单独产生式
    private ArrayList<String> expressions =new ArrayList<>();
    //初始化产生式集合
    public void initExpressionMap(){
        for (String gsItem : originExpression) {
            String[] variableItem = gsItem.split("->");
            String variable = variableItem[0].trim();
            String[] rightString = variableItem[1].split("\\|");
            ArrayList<String> right = new ArrayList<>();
            for (String rightItem : rightString) {
                right.add(rightItem.trim());
            }
            expressionMap.put(variable,right);
        }
        initExpressions();
    }
    //分割或产生式
    public void initExpressions(){
        for(Map.Entry<String,ArrayList<String>> entry : expressionMap.entrySet()){
            String left = entry.getKey();
            ArrayList<String> right = entry.getValue();
            for(String rightstatement : right){
                String expression = left +" -> " + rightstatement;
                expressions.add(expression);
            }
        }
        int cnt = 0;
        for(String expression:expressions){
            expressionStringMap.put(cnt,expression);
            expressionNumberMap.put(expression,cnt);
            cnt++;
        }
       /* int cnt = 0;
        for (String expression : expressions){
            System.out.println(cnt +":"+expression);
            cnt++;
        }*/
    }
    //First集合
    private HashMap<String, TreeSet<String>> firstMap = new HashMap<>();
    //构造First集合
    public void initFirstMap(){
        for(String terminalItem : terminal){
            TreeSet<String> firstSet = firstMap.getOrDefault(terminalItem,new TreeSet<>()) ;
            firstSet.add(terminalItem);
            firstMap.put(terminalItem,firstSet);
        }
        HashMap<String, TreeSet<String>> firstMapCopy = new HashMap<>();

            while (!firstMap.equals(firstMapCopy)) {
                firstMapCopy = (HashMap<String, TreeSet<String>>) firstMap.clone();
                for (String variableItem : variable) {
                    TreeSet<String> firstSet = firstMap.getOrDefault(variableItem, new TreeSet<>());
                    ArrayList<String> right = expressionMap.get(variableItem);
                    for (String rightstatement : right) {
                        if (rightstatement == "E") {
                            firstSet.add("E");
                        }
                        String[] rightTokens = rightstatement.split("\\s+");
                        for (String rightToken : rightTokens) {
                            rightToken = rightToken.trim();
                            TreeSet<String> rightFirstSet = firstMap.getOrDefault(rightToken, new TreeSet<>());
                            firstSet.addAll(rightFirstSet);
                            if (!firstMap.getOrDefault(rightToken, new TreeSet<>()).contains("E")) {
                                break;
                            }
                        }
                    }
                    firstMap.put(variableItem, firstSet);
                }
                for (String variableItem : variable) {
                    TreeSet<String> firstSet = firstMap.getOrDefault(variableItem, new TreeSet<>());
                    ArrayList<String> right = expressionMap.get(variableItem);
                    for (String rightstatement : right) {
                        if (rightstatement == "E") {
                            firstSet.add("E");
                        }
                        String[] rightTokens = rightstatement.split("\\s+");
                        for (String rightToken : rightTokens) {
                            rightToken = rightToken.trim();
                            TreeSet<String> rightFirstSet = firstMap.getOrDefault(rightToken, new TreeSet<>());
                            firstSet.addAll(rightFirstSet);
                            if (!firstMap.getOrDefault(rightToken, new TreeSet<>()).contains("E")) {
                                break;
                            }
                        }
                    }
                    firstMap.put(variableItem, firstSet);
                }
            }
           /* System.out.println("FirstSet:");
            for(Map.Entry<String, TreeSet<String>> entry : firstMap.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }*/
        }
    //产生式的结构
    class Expression{
        String originExpression = "program -> compoundstmt";
        String left;
        String []rights;
        public Expression(String originExpression) {
            this.originExpression = originExpression;
            String[] statement = this.originExpression.split("->");
            left = statement[0].trim();
            rights = statement[1].trim().split("\\s+");
        }
        public String getOriginExpression() {
            return originExpression;
        }
        public String getLeft() {
            return left;
        }
        public String[] getRights() {
            return rights;
        }

        @Override
        public String toString() {
            return  originExpression + '\'';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Expression that = (Expression) o;

            if (!originExpression.equals(that.originExpression)) {
                return false;
            }
            if (left != null ? !left.equals(that.left) : that.left != null) {
                return false;
            }
            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            return Arrays.equals(rights, that.rights);
        }

        @Override
        public int hashCode() {
            int result = originExpression.hashCode();
            result = 31 * result + (left != null ? left.hashCode() : 0);
            result = 31 * result + Arrays.hashCode(rights);
            return result;
        }
    }
    //项目的结构
    public TreeSet<Item> closure(TreeSet<Item> originItemSet){
        //初始另J等于原项目集
        TreeSet<Item> closureItemSet = new TreeSet<>();
        closureItemSet.addAll(originItemSet);
        //循环直到不再改变
        TreeSet<Item> copyItemSet = new TreeSet<>();
        while (!closureItemSet.equals(copyItemSet)) {
            copyItemSet = (TreeSet<Item>) closureItemSet.clone();
            //对J中的每一个项A->α.Bβ,a
            for (Item item : copyItemSet) {
                if(item.getPosition()==item.max_position) {
                    continue;
                }
                String B = item.getFollowSymbol();
                String expect_symbol = item.getExpect_symbol();
                //对G中的每个产生式B->γ
                for (String originExpression : expressions) {
                    Expression expression = new Expression(originExpression);
                    //如果项B->.γ,b不在J中 则加入J中
                    if (expression.getLeft().equals(B)) {
                        //对First(βa)中的每个符号b
                        for(String b : getFirstSet(item.getBetaSymbol(),expect_symbol)){
                            Item curItem = new Item(expression, 0,b);
                            closureItemSet.add(curItem);
                        }
                    }
                }
            }
        }
        return closureItemSet ;
    }
    //计算First(βa)
    public TreeSet<String> getFirstSet(String beta, String a){
        TreeSet<String> resultFirstSet = new TreeSet<>();
        resultFirstSet.addAll(firstMap.getOrDefault(beta,new TreeSet<String>()));
        if(resultFirstSet.contains("E")){
            resultFirstSet.addAll(firstMap.getOrDefault(a,new TreeSet<String>()));
        }
        return resultFirstSet;
    }
    // go_to函数
    // 输入 项目集itemSet 和文法符号symbol
    // 返回 项目集itemSet 对应于文法符号symbol的后记项目集闭包
    public TreeSet<Item> go_to(TreeSet<Item> itemSet , String symbol){
        TreeSet<Item> followItemSet = new TreeSet<>();
        for(Item item : itemSet){
            if(item.getPosition()==item.max_position){
                continue;
            }
            if(item.getFollowSymbol().equals(symbol)){
                if (item.position<item.max_position) {
                    Item followItem = new Item(item.getExpression(), item.getPosition() + 1, item.getExpect_symbol());
                    followItemSet.add(followItem);
                }
            }
        }
        return closure(followItemSet);
    }
    //规范LR项集族 (自动机的状态集)
    private List<TreeSet<Item>> stateSet = new ArrayList<>();
    //对状态集合进行编号
    private Map<Integer,TreeSet<Item>> stateMap = new HashMap<>();
    private Map<TreeSet<Item>,Integer> numberMap = new HashMap<>();
    //构造规范LR项集族
    public void initStateSet(){
        //将C初始化为{CLOSURE({[program -> compoundstmt,$]})}
        Expression startExpression = new Expression("program -> compoundstmt");
        Item startItem = new Item(startExpression,0,"$");

        TreeSet<Item> startItemSet = new TreeSet<>();

        startItemSet.add(startItem);
        stateSet.add(closure(startItemSet));

        //循环直到不再有新的项集加入到C中
        List<TreeSet<Item>> copyStateSet = new ArrayList<>();
        while(!copyStateSet.equals(stateSet)){
            copyStateSet = new ArrayList<>(stateSet);
            //C中的每个项目集I
            for(TreeSet<Item> state : copyStateSet){
                //每个文法符号x
                for(String symbol : symbolSet){
                    //Goto(I,X)非空且不在C中
                    TreeSet<Item> goToSet = go_to(state,symbol);
                    if(!goToSet.isEmpty()&&!stateSet.contains(goToSet)){
                        //将Goto(I,X)加入C中
                        stateSet.add(goToSet);
                    }
                }
            }
       }
        /*int cnt = 0;
        for(TreeSet<Item> state:stateSet){
            System.out.println("-----"+ (cnt++) +"-----");
            for(Item item : state){
                System.out.println(item.getExpression()+" , "+item.getPosition()+" , "+item.getExpect_symbol() +"\t");
            }
            System.out.println("");
        }*/
    }
    class TableItem {
        // err代表错误 s代移入操作 r代表规约操作
        String type = "err";
        Integer target = 0; //产生式编号

        public TableItem(String type, Integer target) {
            this.type = type;
            this.target = target;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return type +""+target;
        }

        public Integer getTarget() {
            return target;
        }
    }
    //对状态集合进行编号
    //为了而实现双向映射 用自定义类StateSet中的编号 与HashMap的Key进行对应
    private void initStateMap(){
        int cnt = 0;
        for(TreeSet<Item> state:stateSet){
            stateMap.put(cnt,state);
            numberMap.put(state,cnt);
            cnt++;
        }
        /*for (StateSet set : stateList){
            System.out.println(set.toString());
        }*/
    }
    //LR_分析表 分别对应 状态集编号 字符 和 表项
    //表项分为 移入 规约 Goto err 和 acc
    private Map<Integer,Map<String, TableItem>> LR_Table = new HashMap<>();
    private void initLR_table(){
        //根据I构造得到的状态i 状态i的语法分析动作按照以下方法决定
        //对于每一个状态集合Ii
        for(TreeSet<Item> itemSet : stateSet){
            //对于状态集合Ii中的每一条项目[A->α.aβ,b]
            for(Item item : itemSet){
                int position = item.getPosition();
                if(position==item.getMax_position()){
                    //[S'->S.,$] Action[i,$] = acc;
                    if (item.getExpression().getLeft().equals(start)){
                        Map<String, TableItem> curTableItem = LR_Table.getOrDefault(numberMap.get(itemSet),new HashMap<>());
                        curTableItem.put(item.getExpect_symbol(), new TableItem("acc", 0));
                        LR_Table.put(numberMap.get(itemSet), curTableItem);
                    } //[A->α.,a] 规约 Action[i,a] = rj
                    else {
                        Map<String, TableItem> curTableItem = LR_Table.getOrDefault(numberMap.get(itemSet),new HashMap<>());
                        //curTable 是 [a 展望符,TableItem(r(表示规约),产生式的编号) 默认数值-1表示找不到这个产生式(正常不会出现才对)
                        curTableItem.put(item.getExpect_symbol(), new TableItem("r", expressionNumberMap.getOrDefault(item.getExpression().getOriginExpression(), -1)));
                        LR_Table.put(numberMap.get(itemSet), curTableItem);
                    }
                }
                else if (position<item.getMax_position()){
                    String followSymbol = item.getFollowSymbol();
                    //[A->α.aβ,b] 终结符 移入 Action[i,a] = sj
                    if(isTerminal(followSymbol)){
                        Map<String, TableItem> curTableItem = LR_Table.getOrDefault(numberMap.get(itemSet),new HashMap<>());
                        TreeSet<Item> gotoSet = go_to(itemSet,followSymbol);
                        //curTable 是 [a 当前字符,TableItem(s(表示移入),状态集合的编号) 默认数值-1表示找不到这个产生式(正常不会出现才对)
                        curTableItem.put(followSymbol,new TableItem("s",numberMap.getOrDefault(gotoSet,-1)));
                        LR_Table.put(numberMap.get(itemSet),curTableItem);
                    }//[A->α.Bβ,b] 非终结符 状态转移 Goto[i,B] = j
                    else if(isVariable(followSymbol)){
                        Map<String, TableItem> curTableItem = LR_Table.getOrDefault(numberMap.get(itemSet),new HashMap<>());
                        TreeSet<Item> gotoSet = go_to(itemSet,followSymbol);
                        //curTable 是 [a 当前字符,TableItem(g(表示转移),状态的编号) 默认数值-1表示找不到这个产生式(正常不会出现才对)
                        curTableItem.put(followSymbol,new TableItem("g",numberMap.getOrDefault(gotoSet,-1)));
                        LR_Table.put(numberMap.get(itemSet),curTableItem);
                    }
                }
            }
        }
        /*System.out.println("\nLR1Table:");
        for(Map.Entry<Integer,Map<String,TableItem>> tableRow: LR_Table.entrySet()){
            System.out.print(tableRow.getKey()+" : " );
            for(Map.Entry<String,TableItem> tableItem : tableRow.getValue().entrySet()){
               System.out.print("\t"+tableItem.getKey()+" "+tableItem.getValue()+"\t");
           }
            System.out.println(" ");
        }*/
    }
    //程序输入字符串
    private  StringBuffer prog = new StringBuffer();
    private  List<String> outputList = new ArrayList<>();
    public void read_prog() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            prog.append(sc.nextLine());
            prog.append(' ');
            prog.append('\n');
        }
    }
    public void LR_analysis(){
        //处理输入字符串 空格分割为一个一个单词
        ArrayList<String> input = new ArrayList<>();
        String []inputToken = prog.toString().split("\\s+");
        input.addAll(Arrays.asList(inputToken));
        //输出初始输入
        String inputString = new String();
        for (String output : input){
            inputString += output+" ";
        }
        inputString += "=>";
        outputList.add(inputString);
        //输入字符串加上结束标记$
        input.add("$");
        //状态栈 初始为0
        Stack<Integer> stateStack = new Stack<>();
        stateStack.push(0);
        //输出栈 初始为$
        Stack<String> outputStack = new Stack<>();
        outputStack.push("$");
        int i = 0;
        Integer s;
        String a = input.get(i++);


        while (true){
            //令s是栈顶的状态
            s = stateStack.peek();
            Map<String, TableItem> curTableRow = LR_Table.get(s);
            TableItem curTableItem = curTableRow.getOrDefault(a,new TableItem("err",-1));
            //if(Action[s,a]=st)
            if (curTableItem.getType().equals("s")){
                //将t压入状态栈中
                stateStack.push(curTableItem.getTarget());
                //将a移入输出栈 并指向下一个符号
                outputStack.push(a);
                a = input.get(i++);
            }//Action[s,a] = 规约 A->β
            else if(curTableItem.getType().equals("r")){
                //从栈中弹出|β|个符号
                String expression = expressionStringMap.get(curTableItem.getTarget());
                Expression curExpression = new Expression(expression);
                int popNumber = curExpression.getRights().length;
                for(int j = 0;j<popNumber;j++){
                    outputStack.pop();
                    stateStack.pop();
                }
                s = stateStack.peek();
                //将规约的左侧A压入输出栈中
                    outputStack.push(curExpression.left);

                    //输出
                    List<String> outputStringList =  new ArrayList<String>(outputStack);
                    outputStringList.remove(0);
                    String outputString = new String();
                    for (String output : outputStringList){
                        outputString += output+" ";
                    }
                    for(int j = i-1; j<input.size()-1;j++){
                        outputString += input.get(j)+" ";
                    }
                    outputString +="=>";
                    outputList.add(outputString);

                //将Goto[t,A]压入状态栈中
                    Map<String, TableItem> gotoTableRow = LR_Table.get(s);
                    TableItem gotoTableItem =gotoTableRow.getOrDefault(outputStack.peek(),new TableItem("err",-1));
                    if (gotoTableItem.getType().equals("g")){
                        stateStack.push(gotoTableItem.getTarget());
                    }
                    else{
                        System.out.println("Goto Error");
                    }
            }
            else if(curTableItem.getType().equals("acc")){
                outputList.add("program =>");
                break;
            }
            else{
                a = "E";
                i--;
            }
        }
    }

    public void outputResult(){

        Collections.reverse(outputList);
        for(String res : outputList){
            System.out.println(res);
        }
    }
    public void run(){
        initSymbolSet();
        initExpressionMap();
        initFirstMap();
        initStateSet();
        initStateMap();
        initLR_table();
        read_prog();
        LR_analysis();
        outputResult();
    }
}
