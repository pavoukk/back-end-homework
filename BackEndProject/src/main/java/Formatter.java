
import java.util.ArrayList;
import java.util.List;

public class Formatter {

    public String format(String str) {
        int countClBr = 0;
        int countOpBr = 0;
        StringBuilder result = new StringBuilder(str);
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '{') {
                countOpBr++;
                while (result.charAt(i + 1) == ' ') {
                    result.delete(i + 1, i + 2);

                }
                for (int j = 0; j < countOpBr; j++) {
                    result.insert(i + 1, "    ");
                }
                result.insert(i + 1, "\n");
//                if(result.charAt(i + 2 + 4*countOpBr)!= '{') {
                    while (result.charAt(i - 1) == ' ') {
                        result.delete(i - 1, i);
                        i--;
                    }
//                }
                result.insert(i, " ");
                i++;
            }
            if (result.charAt(i) == ';') {
                while (result.charAt(i + 1) == ' ') {
                    result.delete(i + 1, i + 2);
                }
                for (int j = 0; j < countOpBr; j++) {
                    result.insert(i + 1, "    ");
                }
                result.insert(i + 1, "\n");
            }

            if (result.charAt(i) == '}') {

                countClBr++;
                for (int j = 0; j < countOpBr - 1; j++) {
                    result.insert(i + 1, "    ");
                }
                result.insert(i + 1, "\n");
//                if(result.charAt(i + 2 + 4*countOpBr)!= '{') {
                    while (result.charAt(i - 1) == ' ') {
                        result.delete(i - 1, i);
                        i--;
                    }
//                }
                for (int k = 0; k < countOpBr - countClBr; k++) {
                    result.insert(i, "    ");
                }
                i += (countOpBr - 1) * 4;
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String string = "aaa { bbbb; ccc;}";
        String string1 = "public void func(){int a = 0;int b = 5;if((a + b)>=4){int a = 4;int b = 6;return a + b;}return 0;}";
        String string2 = "a  public  void func (){int a = 0; int b = 3; int c= 4; if(a== b){ b = a; return b;}";
        String string3 = "func(){ new int[12]; for(){  if(i % 2 == 0){ arr[i] = 0;}}";
        String string5 = "func(){ new int[12]; for(){  int i = 0;if(i % 2 == 0){ if(arr[i] !=0){ a = 4;}arr[i] = 0;}}";
        String string4 = "public   v void func()      {new  int[] arr = new int[12]; for(int i = 0 i < 12 i++){if  (arr[i] % 4 ==0){arr[i] = 4;}else if(arr[i]%2 == 1) { arr[i] = 2 + 1;}}}";
        String string6 = "{{{{{}}}}}";
        Formatter formatter = new Formatter();
//        System.out.println(formatter.format(string));
//        System.out.println(formatter.format(string1));
//        System.out.println(formatter.format(string2));
//        System.out.println(formatter.format(string3));
//        System.out.println(formatter.format(string4));
//        System.out.println(formatter.format(string5));
        System.out.println(formatter.format(string6));
    }
}
