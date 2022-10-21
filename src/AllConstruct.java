import java.util.ArrayList;

/**
 * @author : Ahmad Al-Omari
 * @mailto : alomari.ah98@gmail.com
 * @created : 10/22/2022, Saturday
 * @project : dynamic-programming-tabulation-all-construct
 **/
public class AllConstruct {

    public static ArrayList<ArrayList<String>> allConstruct(String target, String[] wordBank) {
        ArrayList<ArrayList<ArrayList<String>>> table = new ArrayList<>();
        for (int i = 0; i <= target.length(); i++) {
            ArrayList<ArrayList<String>> lst = new ArrayList<>();
            if (i==0){
                ArrayList<String> subLst = new ArrayList<>();
                lst.add(subLst);
                table.add(lst);
                continue;
            }
            table.add(lst);
        }

        for (int i = 0; i <= target.length(); i++) {
            for (String word: wordBank) {
                if ((i+word.length() <= target.length()) && target.startsWith(word, i)){
                    ArrayList<ArrayList<String>> collection = table.get(i);
                    ArrayList<ArrayList<String>> collectionEdited = addWord(word, collection);

                    ArrayList<ArrayList<String>> collectionTarget = table.get(i + word.length());
                    ArrayList<ArrayList<String>> combine = addValues(collectionEdited, collectionTarget);
                    table.set(i+word.length(), combine);
                }
            }
        }

        return table.get(target.length());
    }

    private static ArrayList<ArrayList<String>> addValues(ArrayList<ArrayList<String>> collection, ArrayList<ArrayList<String>> collectionTarget) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (ArrayList<String> lst : collection) {
            ArrayList<String> temp = new ArrayList<>(lst);
            result.add(temp);
        }
        for (ArrayList<String> lst : collectionTarget) {
            ArrayList<String> temp = new ArrayList<>(lst);
            result.add(temp);
        }
        return result;
    }

    private static ArrayList<ArrayList<String>> addWord(String word, ArrayList<ArrayList<String>> collection) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (ArrayList<String> lst : collection) {
            ArrayList<String> temp = new ArrayList<>(lst);
            temp.add(word);
            result.add(temp);
        }
        return result;
    }

    public static void printResult(ArrayList<ArrayList<String>> array){
        for (ArrayList<String> list: array) {
            System.out.println(list.toString());
        }
        System.out.println();
    }
    public static void main(String[] args) {
        printResult(allConstruct("purple", new String[]{"purp", "p", "ur", "le", "purpl"}));
        printResult(allConstruct("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd", "ef", "c"}));
        printResult(allConstruct("skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        printResult(allConstruct("aaaaaaaaaaaaaaaaaaz", new String[]{"a", "aa", "aaa", "aaaa"}));
    }
}
