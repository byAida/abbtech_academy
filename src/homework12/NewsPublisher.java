package homework12;

import java.util.ArrayList;
import java.util.List;

public class NewsPublisher {

    private List<Subscriber> list = new ArrayList<>();
    private String news;

    public synchronized void add(Subscriber s){
        list.add(s);
    }

    public synchronized void publish(String n){
        news = n;
        System.out.println("Yeni xəbər yayımlandı " + n);
        notifyAll();
    }

    public synchronized String getNews() throws InterruptedException {
        while (news == null){
            wait();
        }
        String x = news;
        news = null;
        return x;
    }
}

