package type1;

/**
 * 单例模式：饿汉式（静态变量）
 */

public class Singleton1 {
    public static void main(String[] args) {
        // 测试
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        // 比较内存地址
        System.out.println(instance==instance2);    // true
        // 比较hashcode
        System.out.println("instance hashCode:"+instance.hashCode());   // instance hashCode:460141958
        System.out.println("instance2 hashCode:"+instance2.hashCode()); // instance2 hashCode:460141958
    }
}

class Singleton{
    // 1.构造器私有化
    private Singleton(){

    }
    // 2.本类内部创建对象
    private final static Singleton instance = new Singleton();
    // 3.提供一个公有的静态方法，返回实例对象
    public static Singleton getInstance(){
        return instance;
    }
}