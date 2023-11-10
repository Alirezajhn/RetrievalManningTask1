import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SimpleSearch {
    public static void main(String[] args) throws IOException {
        List<String> texts = Arrays.asList("alice", "frankenstein", "pride", "romeo", "yellow");
        DocumentStore store = new DocumentStore();
        InvertedIndex index = new InvertedIndex();

        for (String name : texts) {
            InputStream stream = SimpleSearch.class.getResource(name + ".txt").openStream();
            String body = new String(stream.readAllBytes());
            Document document = new Document(name, body);
            store.add(document);
            index.add(document);
        }

        while (true) {
            System.out.println("Enter Your sign(and/or/not): ");
            Scanner reader3 = new Scanner(System.in);
            String sign = reader3.nextLine();
            if (sign.equals("quit")) {
                return;
            }

            try {
                if (sign.equals("and")) {
                    System.out.println("Enter Your Query1: ");
                    Scanner reader1 = new Scanner(System.in);
                    String line1 = reader1.nextLine().toLowerCase();

                    System.out.println("Enter Your Query2: ");
                    Scanner reader2 = new Scanner(System.in);
                    String line2 = reader2.nextLine().toLowerCase();

                    if (line1.equals("quit") || line2.equals("quit"))
                        return;

                    PostingList list1 = index.get(line1);
                    PostingList list2 = index.get(line2);
                    PostingList list1AndList2 = list1.and(list2);

                    for (Integer docId : list1AndList2.getDocIds()) {
                        System.out.print(store.get(docId) + "\t");
                    }
                }
                else if (sign.equals("or")) {
                    System.out.println("Enter Your Query1: ");
                    Scanner reader1 = new Scanner(System.in);
                    String line1 = reader1.nextLine().toLowerCase();

                    System.out.println("Enter Your Query2: ");
                    Scanner reader2 = new Scanner(System.in);
                    String line2 = reader2.nextLine().toLowerCase();

                    if (line1.equals("quit") || line2.equals("quit"))
                        return;

                    PostingList list1 = index.get(line1);
                    PostingList list2 = index.get(line2);
                    PostingList list1OrList2 = list1.or(list2);

                    for (Integer docId : list1OrList2.getDocIds()) {
                        System.out.print(store.get(docId) + "\t");
                    }
                }
                else if (sign.equals("not")) {
                    System.out.println("Enter Your Query1: ");
                    Scanner reader1 = new Scanner(System.in);
                    String line1 = reader1.nextLine().toLowerCase();

                    if (line1.equals("quit"))
                        return;
                    PostingList list1 = index.get(line1);
                    PostingList list3 = new PostingList(1, 2, 3, 4, 5);
                    PostingList list1NotList2 = list1.not(list3);

                    for (Integer docId : list1NotList2.getDocIds()) {
                        System.out.print(store.get(docId) + "\t");
                    }
                }else {
                    System.out.println("the sign is incorrect...");
                }
                System.out.println();

            } catch (Exception e) {
                System.out.println("NotFound");
            }


        }
    }
}
