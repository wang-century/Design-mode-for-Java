package type6;

/**
 * 双重检查
 */

public class Singleton6 {
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
    private static volatile Singleton instance;

    private Singleton(){}
    // 提供一个静态的公有方法，加入双重检查代码,解决线程安全问题，同时解决懒加载问题
    public static Singleton getInstance(){
        if (instance==null){
            synchronized (Singleton.class){
                if (instance==null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
