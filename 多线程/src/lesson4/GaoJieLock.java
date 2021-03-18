package lesson4;

import java.util.Arrays;

public class GaoJieLock {
    public static String  fun(String str) {

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < str.length();i++) {
            if((!Character.isLetter(str.charAt(i)) && str.charAt(i) != ' ')) {
                sb.append(" ");
            } else {
                sb.append(str.charAt(i));
            }
        } //替换
        str = sb.toString();
        String[] strings = str.split(" "); //分割
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = strings.length - 1;i>= 0;i--) {
            if (strings[i].equals("")) {
                while (strings[i].equals("") && strings[i - 1].equals("")){
                    i--;
                }
                i--;
                stringBuffer.append(strings[i] + " ");
            } else {
                stringBuffer.append(strings[i] + " ");
            }
        }
        str = stringBuffer.toString();
        return str;
    }

//        char[] chars = str.toCharArray();
//        StringBuffer sb = new StringBuffer();
//        for(int i = str.length() - 1; i >= 0;i++) {
//            sb.append(chars[i] + " ");
//            if(i == 0) {
//                sb.append(chars[i]);
//            }
//        }
//        return sb;

    public static String fun1(String str){

        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length;i++) {
            if (chars[i] == '_') {
                i++;
                chars[i] = (char) (chars[i] - 32);
            }
            sb.append(chars[i]);
        }
        str = sb.toString();

        return str;
    }
    public static void fun(String str1,String str2) {
        if(str1.length() <= str2.length()) {
            System.out.println("NO");
        }



    }
    public static int MoreThanHalfNum_Solution(int [] array) {
        Arrays.sort(array);
        int max = 0;
        int index = 0;
        for(int i = 0; i < array.length - 1;i++) {
            int count = 1;
            while((i + 1 < array.length) && array[i] == array[i + 1]) {
                index = i;
                count++;
                i++;
            }
            if(count > max) {
                max = count;
            }
        }
        if (max <= array.length /2) {
            return 0;
        }
        return array[index];
    }
    public static void main(String[] args) {
        int[] arr = {4,2,1,4,2,4};
        int a = MoreThanHalfNum_Solution(arr);
        System.out.println(a);

            //String str = "wyZksmG XY ReXA Pedt mabjlFdGmJUseHz GzciYP nDWh";


//        }
        }
    }



