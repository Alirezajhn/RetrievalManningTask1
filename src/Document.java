import java.util.concurrent.atomic.AtomicInteger;

public class Document {
    private static final AtomicInteger lastId=new AtomicInteger(0);
    //درواقع داخل کلاس داکیومنت یک متغیر استاتیک تعریف میکنم اخرین داکیومت ایدی در ان نگه میدارم هرموقع شی ساختم یک داک ایدی تخصیص دادم و این خودش جنریت میکند
    private final int docId;
    private final String name;
    private final String body;

    public Document(String name, String body) {
        this.docId = lastId.incrementAndGet();
        this.name = name;
        this.body = body;
    }

    public int getDocId() {
        return docId;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return
                "docId=" + docId +
                ", name='" + name + '\''
                ;
    }
    //    public static void main(String[] args) {
//        Document doc1=new Document("one","dkhfd");
//        Document doc2=new Document("2","dered");
//        System.out.println(doc2.getDocId());
//        System.out.println(doc1.getDocId());
//    }
}



