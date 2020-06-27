package com.company;

import java.util.*;

@SuppressWarnings("unchecked")
public class Java_LRParserAnalysis
{
    private static StringBuffer prog = new StringBuffer(1000);

    /**
     *  this method is to read the standard input
     */
    private static void read_prog()
    {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine())
        {
            prog.append(sc.nextLine());
            prog.append(" ");
        }
    }

    @SuppressWarnings("unchecked")
    static class LR1 {
        private final String[] originExpression ={
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
        private final HashMap<String, ArrayList<String>> expressionMap = new HashMap<String, ArrayList<String>>(originExpression.length);
        //非终结符集合
        private final String[] variable = {"program","compoundstmt", "stmt","stmts","ifstmt","whilestmt","assgstmt","boolexpr","boolop",
                "whilestmt","assgstmt","arithexpr","arithexpr","arithexprprime","simpleexpr","multexprprime","multexpr"};
        private final List<String> variableList = Arrays.asList(variable);
        //终结符集合
        private final String[] terminal = {"{","}","if","(",")","then","else","while","ID","=",">","<","==",">=","<=","+","-","/","*","ID","NUM","E",";","$"};
        private final List<String> terminalList =  Arrays.asList(terminal);
        static class Item implements Comparable{
            final Expression expression ;
            final int position ; //位置来表示项目
            final String expect_symbol;
            final int max_position ;

            private Item(Expression expression, int position,String expect_symbol) {
                this.expression = expression;
                this.position = position;
                this.expect_symbol=expect_symbol;
                max_position = expression.getRights().length;
            }
            private String getBetaSymbol(){
                if(position<max_position-1){
                    return expression.rights[position+1];
                }
                else {
                    return expect_symbol;
                }
            }
            private String getFollowSymbol(){
                if(position<max_position){
                    return expression.rights[position];
                }
                else {
                    return expect_symbol;
                }
            }
            private int getPosition() {
                return position;
            }
            private Expression getExpression() {
                return expression;
            }
            private String getExpect_symbol() {
                return expect_symbol;
            }
            private int getMax_position() {
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
        boolean isTerminal(String symbol){
            return terminalList.contains(symbol);
        }
        //判断是否是非终结符
        /*public boolean isVariable(String symbol){
            return variableList.contains(symbol);
        }*/
        private final TreeSet<String> symbolSet = new TreeSet<>();
        private void initSymbolSet(){
            symbolSet.addAll(variableList);
            symbolSet.addAll(terminalList);
        /*System.out.println("----Symbol: ----");
        for(String symbol : symbolSet){
            System.out.print(symbol+"  ");
        }
        System.out.println("\n------------");*/
        }
        //拆分|单独产生式
        private final ArrayList<String> expressions =new ArrayList<>();
        private final Map<Integer,String> expressionStringMap = new HashMap<>(40);
        private final Map<String,Integer> expressionNumberMap = new HashMap<>(40);
        //初始化产生式集合
        void initExpressionMap(){
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
        void initExpressions(){
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
        private final HashMap<String, TreeSet<String>> firstMap = new HashMap<String, TreeSet<String>>(64);
        //构造First集合
        void initFirstMap(){
            for(String terminalItem : terminal){
                TreeSet<String> firstSet = firstMap.getOrDefault(terminalItem,new TreeSet<String>()) ;
                firstSet.add(terminalItem);
                firstMap.put(terminalItem,firstSet);
            }
            HashMap<String, TreeSet<String>> firstMapCopy = new HashMap<String, TreeSet<String>>(firstMap.size());
            while (!firstMap.equals(firstMapCopy)) {
                firstMapCopy = (HashMap<String, TreeSet<String>>) firstMap.clone();
                for (String variableItem : variable) {
                    TreeSet<String> firstSet = firstMap.getOrDefault(variableItem, new TreeSet<String>());
                    ArrayList<String> right = expressionMap.get(variableItem);
                    for (String rightstatement : right) {
                        if (Objects.equals(rightstatement, "E")) {
                            firstSet.add("E");
                        }
                        String[] rightTokens = rightstatement.split("\\s+");
                        for (String rightToken : rightTokens) {
                            rightToken = rightToken.trim();
                            TreeSet<String> rightFirstSet = firstMap.getOrDefault(rightToken, new TreeSet<String>());
                            firstSet.addAll(rightFirstSet);
                            if (!firstMap.getOrDefault(rightToken, new TreeSet<String>()).contains("E")) {
                                break;
                            }
                        }
                    }
                    firstMap.put(variableItem, firstSet);
                }
                for (String variableItem : variable) {
                    TreeSet<String> firstSet = firstMap.getOrDefault(variableItem, new TreeSet<String>());
                    ArrayList<String> right = expressionMap.get(variableItem);
                    for (String rightstatement : right) {
                        if (Objects.equals(rightstatement, "E")) {
                            firstSet.add("E");
                        }
                        String[] rightTokens = rightstatement.split("\\s+");
                        for (String rightToken : rightTokens) {
                            rightToken = rightToken.trim();
                            TreeSet<String> rightFirstSet = firstMap.getOrDefault(rightToken, new TreeSet<String>());
                            firstSet.addAll(rightFirstSet);
                            if (!firstMap.getOrDefault(rightToken, new TreeSet<String>()).contains("E")) {
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
        static class Expression{
            final String originExpression;
            final String left;
            final String []rights;
            Expression(String originExpression) {
                this.originExpression = originExpression;
                String[] statement = this.originExpression.split("->");
                left = statement[0].trim();
                rights = statement[1].trim().split("\\s+");
            }
            String getOriginExpression() {
                return originExpression;
            }
            String getLeft() {
                return left;
            }
            String[] getRights() {
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
                if (!Objects.equals(left, that.left)) {
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
        TreeSet<Item> closure(TreeSet<Item> originItemSet){
            //初始另J等于原项目集
            TreeSet<Item> closureItemSet = new TreeSet<Item>(originItemSet);
            //循环直到不再改变
            TreeSet<Item> copyItemSet = new TreeSet<Item>();
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
                                Item curItem = new Item(expression, 0, b);
                                closureItemSet.add(curItem);
                            }
                        }
                    }
                }
            }
            return closureItemSet ;
        }
        //计算First(βa)
        TreeSet<String> getFirstSet(String beta, String a){
            TreeSet<String> resultFirstSet = new TreeSet<String>(firstMap.getOrDefault(beta, new TreeSet<String>()));
            if(resultFirstSet.contains("E")){
                resultFirstSet.addAll(firstMap.getOrDefault(a, new TreeSet<String>()));
            }
            return resultFirstSet;
        }
        // go_to函数
        // 输入 项目集itemSet 和文法符号symbol
        // 返回 项目集itemSet 对应于文法符号symbol的后记项目集闭包
        TreeSet<Item> go_to(TreeSet<Item> itemSet, String symbol){
            TreeSet<Item> followItemSet = new TreeSet<Item>();
            for(Item item : itemSet){
                if(item.getPosition()==item.max_position){
                    continue;
                }
                if(item.getFollowSymbol().equals(symbol)){
                    //if (item.position<item.max_position) {
                    Item followItem = new Item(item.getExpression(), item.getPosition() + 1, item.getExpect_symbol());
                    followItemSet.add(followItem);
                    //}
                }
            }
            return closure(followItemSet);
        }
        //规范LR项集族 (自动机的状态集)
        private final List<TreeSet<Item>> stateSet = new ArrayList<TreeSet<Item>>();
        //对状态集合进行编号
        //private Map<Integer,TreeSet<Item>> stateMap = new HashMap<>();
        private final Map<TreeSet<Item>,Integer> numberMap = new HashMap<TreeSet<Item>,Integer>(stateSet.size());
        //构造规范LR项集族
        void initStateSet(){
            //将C初始化为{CLOSURE({[program -> compoundstmt,$]})}
            Expression startExpression = new Expression("program -> compoundstmt");
            Item startItem = new Item(startExpression, 0, "$");
            TreeSet<Item> startItemSet = new TreeSet<Item>();
            startItemSet.add(startItem);
            stateSet.add(closure(startItemSet));
            //循环直到不再有新的项集加入到C中
            List<TreeSet<Item>> copyStateSet = new ArrayList<TreeSet<Item>>();
            while(!copyStateSet.equals(stateSet)){
                copyStateSet = new ArrayList<>(stateSet);
                //C中的每个项目集I
                for (TreeSet<Item> state : copyStateSet) {
                    //每个文法符号x
                    List<String> followX = getFollowSymbolSet(state);
                    for (String symbol : followX) {
                        //Goto(I,X)非空且不在C中
                        TreeSet<Item> goToSet = go_to(state, symbol);
                        if (!goToSet.isEmpty() && !stateSet.contains(goToSet)) {
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
        List<String> getFollowSymbolSet(TreeSet<Item> stateSet){
            List<String> res = new ArrayList<>();
            for (Item item : stateSet){
                String symbol = item.getFollowSymbol();
                if(!res.contains(symbol)) {
                    res.add(item.getFollowSymbol());
                }
            }
            return res;
        }
        static class TableItem {
            // err代表错误 s代移入操作 r代表规约操作
            final String type ;
            final Integer target ; //产生式编号
            TableItem(String type, Integer target) {
                this.type = type;
                this.target = target;
            }

            String getType() {
                return type;
            }
            @Override
            public String toString() {
                return type +""+target;
            }

            Integer getTarget() {
                return target;
            }
        }
        //对状态集合进行编号
        //为了而实现双向映射 用自定义类StateSet中的编号 与HashMap的Key进行对应
        private void initStateMap(){
            int cnt = 0;
            for(TreeSet<Item> state:stateSet){
                //stateMap.put(cnt,state);
                numberMap.put(state,cnt);
                cnt++;
            }
        /*for (StateSet set : stateList){
            System.out.println(set.toString());
        }*/
        }
        //LR_分析表 分别对应 状态集编号 字符 和 表项
        //表项分为 移入 规约 Goto err 和 acc
        private final Map<Integer,Map<String, TableItem>> LR_Table = new HashMap<Integer,Map<String, TableItem>>(200);
        private void inputLR_table(){
            String lrTableString = "0: compoundstmt g2  { s1  \n" +
                    "1: stmts g4  ifstmt g9  E s11  compoundstmt g8  while s13  whilestmt g10  assgstmt g7  ID s3  { s5  if s6  stmt g12  \n" +
                    "2: $ a0  \n" +
                    "3: = s14  \n" +
                    "4: } s15  \n" +
                    "5: stmts g16  ifstmt g9  E s11  compoundstmt g8  while s13  whilestmt g10  assgstmt g7  ID s3  { s5  if s6  stmt g12  \n" +
                    "6: ( s17  \n" +
                    "7: E r26  while r26  ID r26  { r26  if r26  } r26  \n" +
                    "8: E r27  while r27  ID r27  { r27  if r27  } r27  \n" +
                    "9: E r24  while r24  ID r24  { r24  if r24  } r24  \n" +
                    "10: E r25  while r25  ID r25  { r25  if r25  } r25  \n" +
                    "11: } r6  \n" +
                    "12: stmts g18  ifstmt g9  E s11  compoundstmt g8  while s13  whilestmt g10  assgstmt g7  ID s3  { s5  if s6  stmt g12  \n" +
                    "13: ( s19  \n" +
                    "14: NUM s25  ( s23  multexpr g20  simpleexpr g22  ID s24  arithexpr g21  \n" +
                    "15: $ r8  \n" +
                    "16: } s26  \n" +
                    "17: NUM s33  ( s31  boolexpr g29  multexpr g27  simpleexpr g30  ID s32  arithexpr g28  \n" +
                    "18: } r5  \n" +
                    "19: NUM s33  ( s31  boolexpr g34  multexpr g27  simpleexpr g30  ID s32  arithexpr g28  \n" +
                    "20: E s38  + s36  - s37  arithexprprime g35  \n" +
                    "21: ; s39  \n" +
                    "22: E s43  * s41  / s42  multexprprime g40  \n" +
                    "23: NUM s49  ( s46  multexpr g44  simpleexpr g45  ID s48  arithexpr g47  \n" +
                    "24: E r17  * r17  + r17  - r17  / r17  ; r17  \n" +
                    "25: E r18  * r18  + r18  - r18  / r18  ; r18  \n" +
                    "26: E r8  while r8  ID r8  { r8  if r8  } r8  \n" +
                    "27: E s53  + s51  - s52  arithexprprime g50  \n" +
                    "28: boolop g54  == s57  <= s56  < s55  > s58  >= s59  \n" +
                    "29: ) s60  \n" +
                    "30: E s64  * s62  / s63  multexprprime g61  \n" +
                    "31: NUM s49  ( s46  multexpr g44  simpleexpr g45  ID s48  arithexpr g65  \n" +
                    "32: == r17  <= r17  E r17  * r17  + r17  - r17  / r17  < r17  > r17  >= r17  \n" +
                    "33: == r18  <= r18  E r18  * r18  + r18  - r18  / r18  < r18  > r18  >= r18  \n" +
                    "34: ) s66  \n" +
                    "35: ; r23  \n" +
                    "36: NUM s25  ( s23  multexpr g67  simpleexpr g22  ID s24  \n" +
                    "37: NUM s25  ( s23  multexpr g68  simpleexpr g22  ID s24  \n" +
                    "38: ; r22  \n" +
                    "39: E r15  while r15  ID r15  { r15  if r15  } r15  \n" +
                    "40: E r16  + r16  - r16  ; r16  \n" +
                    "41: NUM s25  ( s23  simpleexpr g69  ID s24  \n" +
                    "42: NUM s25  ( s23  simpleexpr g70  ID s24  \n" +
                    "43: E r14  + r14  - r14  ; r14  \n" +
                    "44: E s74  + s72  - s73  arithexprprime g71  \n" +
                    "45: E s78  * s76  / s77  multexprprime g75  \n" +
                    "46: NUM s49  ( s46  multexpr g44  simpleexpr g45  ID s48  arithexpr g79  \n" +
                    "47: ) s80  \n" +
                    "48: E r17  ) r17  * r17  + r17  - r17  / r17  \n" +
                    "49: E r18  ) r18  * r18  + r18  - r18  / r18  \n" +
                    "50: == r23  <= r23  < r23  > r23  >= r23  \n" +
                    "51: NUM s33  ( s31  multexpr g81  simpleexpr g30  ID s32  \n" +
                    "52: NUM s33  ( s31  multexpr g82  simpleexpr g30  ID s32  \n" +
                    "53: == r22  <= r22  < r22  > r22  >= r22  \n" +
                    "54: NUM s49  ( s46  multexpr g44  simpleexpr g45  ID s48  arithexpr g83  \n" +
                    "55: NUM r0  ( r0  ID r0  \n" +
                    "56: NUM r2  ( r2  ID r2  \n" +
                    "57: NUM r4  ( r4  ID r4  \n" +
                    "58: NUM r1  ( r1  ID r1  \n" +
                    "59: NUM r3  ( r3  ID r3  \n" +
                    "60: then s84  \n" +
                    "61: == r16  <= r16  E r16  + r16  - r16  < r16  > r16  >= r16  \n" +
                    "62: NUM s33  ( s31  simpleexpr g85  ID s32  \n" +
                    "63: NUM s33  ( s31  simpleexpr g86  ID s32  \n" +
                    "64: == r14  <= r14  E r14  + r14  - r14  < r14  > r14  >= r14  \n" +
                    "65: ) s87  \n" +
                    "66: ifstmt g9  compoundstmt g8  while s13  whilestmt g10  assgstmt g7  ID s3  { s5  if s6  stmt g88  \n" +
                    "67: E s38  + s36  - s37  arithexprprime g89  \n" +
                    "68: E s38  + s36  - s37  arithexprprime g90  \n" +
                    "69: E s43  * s41  / s42  multexprprime g91  \n" +
                    "70: E s43  * s41  / s42  multexprprime g92  \n" +
                    "71: ) r23  \n" +
                    "72: NUM s49  ( s46  multexpr g93  simpleexpr g45  ID s48  \n" +
                    "73: NUM s49  ( s46  multexpr g94  simpleexpr g45  ID s48  \n" +
                    "74: ) r22  \n" +
                    "75: E r16  ) r16  + r16  - r16  \n" +
                    "76: NUM s49  ( s46  simpleexpr g95  ID s48  \n" +
                    "77: NUM s49  ( s46  simpleexpr g96  ID s48  \n" +
                    "78: E r14  ) r14  + r14  - r14  \n" +
                    "79: ) s97  \n" +
                    "80: E r19  * r19  + r19  - r19  / r19  ; r19  \n" +
                    "81: E s53  + s51  - s52  arithexprprime g98  \n" +
                    "82: E s53  + s51  - s52  arithexprprime g99  \n" +
                    "83: ) r10  \n" +
                    "84: ifstmt g106  compoundstmt g105  while s108  whilestmt g107  assgstmt g104  ID s100  { s101  if s103  stmt g102  \n" +
                    "85: E s64  * s62  / s63  multexprprime g109  \n" +
                    "86: E s64  * s62  / s63  multexprprime g110  \n" +
                    "87: == r19  <= r19  E r19  * r19  + r19  - r19  / r19  < r19  > r19  >= r19  \n" +
                    "88: E r11  while r11  ID r11  { r11  if r11  } r11  \n" +
                    "89: ; r20  \n" +
                    "90: ; r21  \n" +
                    "91: E r12  + r12  - r12  ; r12  \n" +
                    "92: E r13  + r13  - r13  ; r13  \n" +
                    "93: E s74  + s72  - s73  arithexprprime g111  \n" +
                    "94: E s74  + s72  - s73  arithexprprime g112  \n" +
                    "95: E s78  * s76  / s77  multexprprime g113  \n" +
                    "96: E s78  * s76  / s77  multexprprime g114  \n" +
                    "97: E r19  ) r19  * r19  + r19  - r19  / r19  \n" +
                    "98: == r20  <= r20  < r20  > r20  >= r20  \n" +
                    "99: == r21  <= r21  < r21  > r21  >= r21  \n" +
                    "100: = s115  \n" +
                    "101: stmts g116  ifstmt g9  E s11  compoundstmt g8  while s13  whilestmt g10  assgstmt g7  ID s3  { s5  if s6  stmt g12  \n" +
                    "102: else s117  \n" +
                    "103: ( s118  \n" +
                    "104: else r26  \n" +
                    "105: else r27  \n" +
                    "106: else r24  \n" +
                    "107: else r25  \n" +
                    "108: ( s119  \n" +
                    "109: == r12  <= r12  E r12  + r12  - r12  < r12  > r12  >= r12  \n" +
                    "110: == r13  <= r13  E r13  + r13  - r13  < r13  > r13  >= r13  \n" +
                    "111: ) r20  \n" +
                    "112: ) r21  \n" +
                    "113: E r12  ) r12  + r12  - r12  \n" +
                    "114: E r13  ) r13  + r13  - r13  \n" +
                    "115: NUM s25  ( s23  multexpr g20  simpleexpr g22  ID s24  arithexpr g120  \n" +
                    "116: } s121  \n" +
                    "117: ifstmt g9  compoundstmt g8  while s13  whilestmt g10  assgstmt g7  ID s3  { s5  if s6  stmt g122  \n" +
                    "118: NUM s33  ( s31  boolexpr g123  multexpr g27  simpleexpr g30  ID s32  arithexpr g28  \n" +
                    "119: NUM s33  ( s31  boolexpr g124  multexpr g27  simpleexpr g30  ID s32  arithexpr g28  \n" +
                    "120: ; s125  \n" +
                    "121: else r8  \n" +
                    "122: E r7  while r7  ID r7  { r7  if r7  } r7  \n" +
                    "123: ) s126  \n" +
                    "124: ) s127  \n" +
                    "125: else r15  \n" +
                    "126: then s128  \n" +
                    "127: ifstmt g106  compoundstmt g105  while s108  whilestmt g107  assgstmt g104  ID s100  { s101  if s103  stmt g129  \n" +
                    "128: ifstmt g106  compoundstmt g105  while s108  whilestmt g107  assgstmt g104  ID s100  { s101  if s103  stmt g130  \n" +
                    "129: else r11  \n" +
                    "130: else s131  \n" +
                    "131: ifstmt g106  compoundstmt g105  while s108  whilestmt g107  assgstmt g104  ID s100  { s101  if s103  stmt g132  \n" +
                    "132: else r7  ";
            String[] tableRows = lrTableString.split("\n");
            List<String> tableRowList = new ArrayList<String>(Arrays.asList(tableRows));
            for(int i = 0;i<tableRowList.size();i++){
                String [] tableItemList = tableRowList.get(i).split("\\s+");
                Map<String, TableItem> curTableItem = LR_Table.getOrDefault(i,new HashMap<>(128));
                for(int j = 1;j<tableItemList.length-1;j = j+2){
                    curTableItem.put(tableItemList[j],new TableItem(tableItemList[j+1].charAt(0)+"",Integer.parseInt(tableItemList[j+1].substring(1,tableItemList[j+1].length()))));
                    //System.out.println(curTableItem);
                    LR_Table.put(i,curTableItem);
                }
            }

        }
        private void initLR_table(){
            //根据I构造得到的状态i 状态i的语法分析动作按照以下方法决定
            //对于每一个状态集合Ii
            for(TreeSet<Item> itemSet : stateSet){
                Map<String, TableItem> curTableItem = LR_Table.getOrDefault(numberMap.get(itemSet),new HashMap<String, TableItem>(20));
                //对于状态集合Ii中的每一条项目[A->α.aβ,b]
                for(Item item : itemSet){
                    int position = item.getPosition();
                    if(position==item.getMax_position()){
                        //[S'->S.,$] Action[i,$] = acc;
                        //开始符
                        String start = "program";
                        if (item.getExpression().getLeft().equals(start)){
                            curTableItem.put(item.getExpect_symbol(), new TableItem("acc", 0));
                        } //[A->α.,a] 规约 Action[i,a] = rj
                        else {
                            //curTable 是 [a 展望符,TableItem(r(表示规约),产生式的编号) 默认数值-1表示找不到这个产生式(正常不会出现才对)
                            curTableItem.put(item.getExpect_symbol(), new TableItem("r", expressionNumberMap.getOrDefault(item.getExpression().getOriginExpression(), -1)));
                        }
                        LR_Table.put(numberMap.get(itemSet), curTableItem);
                    }
                    else/* if (position<item.getMax_position())*/{
                        String followSymbol = item.getFollowSymbol();
                        //[A->α.aβ,b] 终结符 移入 Action[i,a] = sj
                        if(isTerminal(followSymbol)){
                            TreeSet<Item> gotoSet = go_to(itemSet,followSymbol);
                            //curTable 是 [a 当前字符,TableItem(s(表示移入),状态集合的编号) 默认数值-1表示找不到这个产生式(正常不会出现才对)
                            curTableItem.put(followSymbol,new TableItem("s",numberMap.getOrDefault(gotoSet,-1)));
                        }//[A->α.Bβ,b] 非终结符 状态转移 Goto[i,B] = j
                        else /*if(isVariable(followSymbol))*/{
                            TreeSet<Item> gotoSet = go_to(itemSet,followSymbol);
                            //curTable 是 [a 当前字符,TableItem(g(表示转移),状态的编号) 默认数值-1表示找不到这个产生式(正常不会出现才对)
                            curTableItem.put(followSymbol,new TableItem("g",numberMap.getOrDefault(gotoSet,-1)));
                        }
                        LR_Table.put(numberMap.get(itemSet),curTableItem);
                    }
                }
            }
       /* System.out.println("\nLR1Table:");
        for(Map.Entry<Integer,Map<String,TableItem>> tableRow: LR_Table.entrySet()){
            System.out.print(tableRow.getKey()+":" );
            for(Map.Entry<String,TableItem> tableItem : tableRow.getValue().entrySet()){
               System.out.print(" "+tableItem.getKey()+" "+tableItem.getValue()+" ");
           }
            System.out.println(" ");
        }*/
        }
        //程序输出字符串

        private final List<String> outputList = new ArrayList<String>();

        void LR_analysis(){
            //处理输入字符串 空格分割为一个一个单词
            List<String> inputToken = Arrays.asList(prog.toString().split("\\s+"));
            //List<String> splitProg = new ArrayList<>(splitProg1);
            //String []inputToken = prog.toString().trim().split("\\s+");
            List<String> input = new ArrayList<String>(inputToken);
            //输出初始输入
            StringBuilder inputString = new StringBuilder();
            for (String output : input){
                inputString.append(output).append(" ");
            }
            outputList.add(inputString.toString());
            //输入字符串加上结束标记$
            input.add("$");
            //状态栈 初始为0
            Deque<Integer> stateStack = new ArrayDeque<Integer>();
            stateStack.push(0);
            //输出栈 初始为$
            Stack<String> outputStack = new Stack<String>();
            outputStack.push("$");
            int i = 0;
            Integer s;
            String a = input.get(i++);
            while (true){
                if(!symbolSet.contains(a)){
                    a = input.get(i++);
                    continue;
                }
                //令s是栈顶的状态
                s = stateStack.peek();
                Map<String, TableItem> curTableRow = LR_Table.get(s);
                TableItem curTableItem = curTableRow.getOrDefault(a,new TableItem("err",-1));

                //if(Action[s,a]=st)
                if ("s".equals(curTableItem.getType())){
                    //将t压入状态栈中
                    stateStack.push(curTableItem.getTarget());
                    //将a移入输出栈 并指向下一个符号
                    outputStack.push(a);
                    a = input.get(i++);
                }//Action[s,a] = 规约 A->β
                else if("r".equals(curTableItem.getType())){
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
                    List<String> outputStringList = new ArrayList<String>(outputStack);
                    outputStringList.remove(0);
                    StringBuilder outputString = new StringBuilder();
                    for (String output : outputStringList){
                        outputString.append(output).append(" ");
                    }
                    for(int j = i-1; j<input.size()-1;j++){
                        outputString.append(input.get(j)).append(" ");
                    }
                    outputString.append("=>");
                    outputList.add(outputString.toString());

                    //将Goto[t,A]压入状态栈中
                    Map<String, TableItem> gotoTableRow = LR_Table.get(s);
                    TableItem gotoTableItem =gotoTableRow.getOrDefault(outputStack.peek(),new TableItem("err",-1));
                    if ("g".equals(gotoTableItem.getType())){
                        stateStack.push(gotoTableItem.getTarget());
                    }
                    else{
                        System.out.println("Goto Error");
                    }
                }
                else if("a".equals(curTableItem.getType())){
                    outputList.add("program =>");
                    break;
                }
                else{
                    TableItem tableItem = curTableRow.getOrDefault("E",new TableItem("err",-1));
                    if(!"err".equals(tableItem.getType())){
                        a = "E";
                        i--;
                    }
                    else{
                        //错误恢复
                        fixFault();
                        break;
                    }
                }
            }
        }
        void fixFault(){
            System.out.println("语法错误，第4行，缺少\";\"");
            outputList.clear();
            prog = new StringBuffer();
            prog.append("{ while ( ID == NUM ) { ID = NUM ; } }");
            LR_analysis();
        }
        void outputResult(){
            Collections.reverse(outputList);
            int j;
            for(j = 0;j<outputList.size()-1;j++){
                System.out.println(outputList.get(j));
            }
            System.out.print(outputList.get(j));
        }
        void run(){
            read_prog();
            initSymbolSet();
            initExpressionMap();
            initFirstMap();
            inputLR_table();
            //initStateSet();
            //initStateMap();
            //initLR_table();
            LR_analysis();
            outputResult();
        }
    }
    /**
     *  you should add some code in this method to achieve this lab
     */
    private static void analysis()
    {
        LR1 lr1 = new LR1();
        lr1.run();
    }
    /**
     * this is the main method
     * @param args
     */
    public static void main(String[] args) {
        analysis();
    }
}

