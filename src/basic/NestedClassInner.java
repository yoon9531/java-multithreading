package basic;

import static util.MyLogger.log;

public class NestedClassInner {
    class Inner{
        void display () {
            log("Inner Class");
        }
    }
}
