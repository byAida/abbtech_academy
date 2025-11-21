package homework12;

public class Subscriber extends Thread {

    private NewsPublisher pub;
    private String name;

    public Subscriber(NewsPublisher p, String name){
        this.pub = p;
        this.name = name;
    }

    public void run(){
        while (true){
            try {
                String n = pub.getNews();
                System.out.println(name + " xəbər aldı: " + n);
            } catch (Exception e) {
            }
        }
    }
}

