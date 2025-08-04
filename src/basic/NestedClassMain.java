package basic;

public class NestedClassMain {
    public static void main (String[] args) {
        NestedClass.MyNestedClass myNestedClass = new NestedClass.MyNestedClass();

        myNestedClass.display();

        int sum = 0;
        for (int i = 51; i <= 100; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
