
//这里有个从后往前复制的思想，但是只能在c++里行得通，因为java和c++对数组处理机制是不同的，
// c++以“\0” 来识别末尾， 也就是挣个存储空间可能比字符串本身所占得大,
// 也就是说复制的字符串 和源字符串1可以再同一块数组空间进行操作,但java 就不一样了。
public class spaceReplace {
    public String replaceSpace(StringBuffer str) {
        if(str == null) throw  new NullPointerException();
        if(str.length()== 0) return "";
        String s = str.toString();
        int space_amout = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == ' ')
                space_amout++;

        char[] new_str = new char[s.length() + space_amout * 2];
        int j = 0;
        for(int i = 0; i<s.length();i++){
            if(s.charAt(i)==' ') {
                new_str[j] = '%';
                j++;
                new_str[j] = '2';
                j++;
                new_str[j] = '0';
                j++;
            }
            else {new_str[j] = s.charAt(i);
                    j++;}
        }

        return new String(new_str);
    }

    public static void main(String[] args){
        spaceReplace sp = new spaceReplace();

        System.out.println(sp.replaceSpace(new StringBuffer(args[0])));
    }
}
