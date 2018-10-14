package project;

public class MainTest {
    public static void main(String[] args) {
        Processor pro = new Processor();

        Thread t = new Thread(pro);
        t.start();

        try {
            while ( true ) {
                Thread.sleep(200);
                System.out.println(pro.getHours() + " - " + pro.getMinutes() + " - " + pro.getSeconds());
            }
        } catch (InterruptedException x) {
            x.printStackTrace();
        }
    }
}
