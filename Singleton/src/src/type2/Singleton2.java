package src.type2;

/**
 * 单例模式：饿汉式（静态代码块）
 */

public class Singleton2 {
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
    // 1.构造器私有化
    private Singleton() {
    }

    // 2.本类内部创建对象
    private static Singleton instance;

    static {    // 在静态代码块中创建单例对象
        instance = new Singleton();
    }

    // 3.提供一个公有的静态方法，返回实例对象
    public static Singleton getInstance() {
        return instance;
    }
}