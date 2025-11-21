package homework12;

public class Main {
    public static void main(String[] args) {

        NewsPublisher pub = new NewsPublisher();

        Subscriber s1 = new Subscriber(pub, "Abunəçi-1");
        Subscriber s2 = new Subscriber(pub, "Abunəçi-2");
        Subscriber s3 = new Subscriber(pub, "Abunəçi-3");

        pub.add(s1);
        pub.add(s2);
        pub.add(s3);

        s1.start();
        s2.start();
        s3.start();

        int i = 1;
        while (true){
            try {
                Thread.sleep(2000);
                pub.publish("Xəbər" + i);
                i++;
            } catch (Exception e){
            }
        }
    }
}
