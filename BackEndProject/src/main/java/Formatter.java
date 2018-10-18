
import java.util.ArrayList;
import java.util.List;

public class Formatter {
    private final char OPENING_BRACKET = '{';
    private final char CLOSING_BRACKET = '}';
    private final String SPACE = "    ";
    private final String NEW_LINE = "\n";
    public String format(String str) {
        StringBuilder result = new StringBuilder(str);
        int amountOfOpBrackets = 0;
        int amountOfClBrackets = 0;
        int countBr = 0;
        for (int j = 0; j < result.length(); j++) {
            if(result.charAt(j) == '{'){
                countBr++;
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '{'){
                amountOfOpBrackets++;
            } else if(str.charAt(i) == '}'){
                amountOfClBrackets++;
            }
        }
        removeChar(result.toString(), ' ', '{');
        removeChar(result.toString(), ' ', '}');
        removeChar(result.toString(), ' ', ';');
        int lastClosingBracket = result.length();
        int firstOpeningBracket = 0;
        int count = 0;
        for (int i = firstOpeningBracket; i < lastClosingBracket; i++) {

            if (result.charAt(i) == '{') {
                result.replace(i, lastClosingBracket, makeSpace(result.toString(), ";", i, lastClosingBracket, "    ").toString());
                String subStrCl = result.toString();
                firstOpeningBracket = i + 1;
                for (int j = 0; j < count; j++) {
                    if(amountOfClBrackets == amountOfOpBrackets) {
                        subStrCl = subStrCl.substring(0, result.lastIndexOf("}"));
                    } else if(amountOfClBrackets > amountOfOpBrackets){
                        subStrCl = subStrCl.substring(0, result.lastIndexOf("}"));
                        amountOfClBrackets--;
                    } else if(amountOfClBrackets < amountOfOpBrackets){
                        amountOfOpBrackets--;
                    }
                }
                    lastClosingBracket = subStrCl.lastIndexOf("}") - 1;
                    if(amountOfClBrackets == amountOfOpBrackets) { // doesn't remove space before closing bracket the way it must do. also check situation when if else if else if
                        result.delete(lastClosingBracket + 1 - 4, lastClosingBracket);
                        lastClosingBracket -= 4;
                    }
                    result.replace(firstOpeningBracket - 1, lastClosingBracket + 1, makeSpace(result.toString(), "{", firstOpeningBracket - 1, lastClosingBracket + 1, "    ").toString());

                lastClosingBracket+=countBr*4;
                countBr--;
                count++;
            }
        }
        result = makeSpace(result.toString(), "{", 0, result.length(), "\n");
        result = makeSpace(result.toString(), ";", 0, result.length(), "\n");
        result = makeSpace(result.toString(), "}", 0, result.length(), "\n");
        return result.toString();
    }
    private StringBuilder makeSpace(String str, String off, int from, int to, String sequence){
        StringBuilder res = new StringBuilder();
        makeSpace(str.substring(from, to),res, off, sequence);
        return res;
    }
    public void makeSpace(String str, StringBuilder stringBuilder, String off, String sequence) {
        if (str.length() == 0 || !str.contains(off)) { //check } || str.substring(str.indexOf(sequence), )
            stringBuilder.append(str);
            return;
        }
        stringBuilder.append(str.substring(0, str.indexOf(off) + 1));
        stringBuilder.append(sequence);
        makeSpace(str.substring(str.indexOf(off) + 1), stringBuilder, off, sequence);
    }

    public String removeChar(String str, char ch, char mark){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length() - 1; i++) {
            if(str.charAt(i) == mark){
                stringBuilder.append(str.charAt(i));
                i++;
                while(i < str.length() && str.charAt(i) == ch){
                    i++;
                }
            }
            if(i < str.length()) {
                stringBuilder.append(str.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        String string = "aaa { bbbb; ccc;}";
        String string1 = "public void func(){int a = 0;int b = 5;if((a + b)>=4){int a = 4;int b = 6;return a + b;}return 0;}";
        String string2 = "a  public  void func (){int a = 0; int b = 3; int c= 4; if(a== b){ b = a; return b;}";
        String string3 = "func(){ new int[12]; for(){  if(i % 2 == 0){ arr[i] = 0;}}";
        String string5 = "func(){ new int[12]; for(){  if(i % 2 == 0){ if(arr[i] !=0){ a = 4;}arr[i] = 0;}}";
        String string4 = "public   v void func(){new  int[] arr = new int[12]; for(int i = 0; i < 12; i++){if  (arr[i] % 4 ==0){arr[i] = 4;}else if(arr[i]%2 == 1) { arr[i] = 2 + 1;}}";
        Formatter formatter = new Formatter();
        System.out.println(formatter.format(string));
        System.out.println(formatter.format(string1));
        System.out.println(formatter.format(string2));
        System.out.println(formatter.format(string3));
        System.out.println(formatter.format(string4));
        System.out.println(formatter.format(string5));
    }
}
