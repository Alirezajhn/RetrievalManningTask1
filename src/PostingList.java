import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PostingList {
    private List<Integer> docIds = new ArrayList<>();

    public PostingList() {
    }

    public PostingList(int... ids) {
        for (int id : ids) {
            docIds.add(id);
        }

    }

    public List<Integer> getDocIds() {
        return docIds;
    }

    public void add(int id) {

        docIds.add(id);
    }

    public void remove(int id) {
        docIds.remove(id);
    }

    public void sort() {

        Collections.sort(docIds);
    }

    public int size() {

        return docIds.size();
    }

    @Override
    public String toString() {

        return Arrays.toString(docIds.toArray(new Integer[0]));
    }

    public PostingList and(PostingList other) {
        PostingList result = new PostingList();
        int i = 0, j = 0;
        while (i < size() && j < other.size()) {
            int a = docIds.get(i);
            int b = other.docIds.get(j);
            if (a == b) {
                result.add(a);
                i++;
                j++;
            } else if (a < b) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

    public PostingList or(PostingList other) {
        PostingList result = new PostingList();
        int i = 0, j = 0;
        while (i < size() && j < other.size()) {
            int a = docIds.get(i);
            int b = other.docIds.get(j);
            if (a == b) {
                result.add(a);
                i++;
                j++;
            } else if (a < b) {
                result.add(a);
                i++;
                if (i == size()) {
                    int k = j;
                    while (k < other.size()) {
                        int b2 = other.docIds.get(k);
                        result.add(b2);
                        k++;
                    }
                }
            } else {
                result.add(b);
                j++;
                if (j == other.size()) {
                    int l = i;
                    while (l < size()) {
                        int a2 = docIds.get(l);
                        result.add(a2);
                        l++;
                    }
                }

            }

        }
        return result;
    }

    public PostingList not(PostingList other) {
        PostingList result = new PostingList();
        int i = 0,j=0;
        while (i < size() && j < other.size()) {
            int b = other.docIds.get(j);
            boolean flag = docIds.contains(b);
            if (!flag) {
                result.add(b);
            }
            j++;
            i++;
        }
        return result;
    }

    //test
//
//    public static void main(String[] args) {
//        //مرتب شده باید باشد
//        PostingList l1 = new PostingList(2, 3, 4, 5);
//        System.out.println(l1.not());
//
//    }
//    public static void main(String[] args) {
//        //مرتب شده باید باشد
//        PostingList l2 = new PostingList(3,4,5);
//        PostingList l2 = new PostingList(1,2,3,4,5);
//
//        System.out.println(l1);
//        System.out.println(l2);
//        System.out.println(l1.not());
//    }


}


