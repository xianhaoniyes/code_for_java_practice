import java.util.HashMap;

public class FirstNotRepeatingChar {
    public int FirstNotRepeatingChar(String str) {



        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i<256;i++)
            map.put(i,0);

        for(int i = 0; i<str.length();i++){
            int si = (int)str.charAt(i);
            map.put(si, map.get(si)+1);
        }


        for(int i = 0; i<str.length();i++) {
            int si = (int) str.charAt(i);
            if (map.get(si) == 1)
                return i;
        }
        return -1;

    }
}
