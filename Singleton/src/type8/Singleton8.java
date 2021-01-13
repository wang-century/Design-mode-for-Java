package type8;

/**
 * 单例模式（枚举）
 */

public class Singleton8 {
    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        // 比较内存地址
        System.out.println(instance==instance2);
        // 比较hashcode
        System.out.println("instance hashCode:" + instance.hashCode());   // instance hashCode:460141958
        System.out.println("instance2 hashCode:" + instance2.hashCode()); // instance2 hashCode:460141958
        instance.sayOk();
    }
}

enum Singleton{
    INSTANCE;   // 属性
    public void sayOk(){
        System.out.println("OK");
    }
}
