package type7;

/**
 * 静态内部类
 */
public class Singleton7 {
    public static void main(String[] args) {
        // 测试
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        // 比较内存地址
        System.out.println(instance == instance2);    // true
        // 比较hashcode
        System.out.println("instance hashCode:" + instance.hashCode());   // instance hashCode:460141958
        System.out.println("instance2 hashCode:" + instance2.hashCode()); // instance2 hashCode:460141958
    }
}


class Singleton{
    private Singleton(){}
    // 写一个静态内部类，该类中有一个静态属性Singleton
    private static class SingletonInstance{
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance(){
        return SingletonInstance.INSTANCE;
    }
}