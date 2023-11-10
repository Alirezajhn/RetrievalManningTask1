

import java.util.*;

public class InvertedIndex {
    //string Posting list
    private Map<String, PostingList> table = new HashMap<>();

    public void add(Document document) {
        String[] tokens = document.getBody().split("\\s+");
        Set<String> distinctTokens = new HashSet<>();
        for (String token : tokens) {
            distinctTokens.add(token);
        }
        for (String token : distinctTokens) {
            table.putIfAbsent(token, new PostingList());
            table.get(token).add(document.getDocId());
        }
        for (PostingList list : table.values()) {
            list.sort();
        }
    }

    public PostingList get(String token) {

        return table.get(token);
    }

//    public static void main(String[] args) {
//        String [] tokens = "i am student".split("\\s+");
//        System.out.println(Arrays.toString(tokens));
//    }
}
