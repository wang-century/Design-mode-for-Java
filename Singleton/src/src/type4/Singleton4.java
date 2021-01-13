package src.type4;

/**
 * 懒汉式（线程安全，同步方法）
 */
public class Singleton4 {
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

class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    // 提供一个静态的公有方法，当使用到该方法时才去创建instance,加入同步处理的代码，解决线程安全问题
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}