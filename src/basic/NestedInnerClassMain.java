package basic;

public class NestedInnerClassMain {
    public static void main (String[] args) {
        NestedClassInner outer = new NestedClassInner();
        NestedClassInner.Inner inner = outer.new Inner();
        inner.display();
    }
}
