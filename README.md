# Design-mode-for-Java

# 单例设计模式

​	所谓类的单例设计模式，就是采取一定的方法保证在整个的软件系统中，对某个类只能存在一个对象实例，并且该类只提供一个取得其对象实例的方法(静态方法)。

## 单例模式的八种方式

### 饿汉式（静态常量）

#### 优缺点：

- 优点:这种写法比较简单，就是在类装载的时候就完成实例化。避免了线程同步问题。
- 缺点:在类装载的时候就完成实例化，没有达到Lazy Loading的效果。如果从始至终从未使用过这个实例,则会造成内存的浪费
- 这种方式基于classloder机制避免了多线程的同步问题，不过，instance在类装载时就实例化，在单例模式中大多数都是调用getInstance方法，但是导致类装载的原因有很多种，因此不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化instance就没有达到lazy loading的效果
- 结论:这种单例模式可用，可能造成内存浪费

#### 步骤：

- [ ] 构造器私有化
- [ ] 类的内部创建对象
- [ ] 向外暴露一个静态的公共方法
- [ ] 代码实现

```java
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
```

### 饿汉式（静态代码块）

优缺点与步骤与上一个饿汉式（静态常量相同）

#### 代码实现

```java
package type2;

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
```

### 懒汉式（线程不安全）

#### 优缺点

1. 起到了Lazy Loading的效果，但是只能在单线程下使用。
2. 如果在多线程下，一个线程进入了if (singleton m= null)判断语句块，还未来得及往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程环境下不可使用这种方式
3. 结论:在实际开发中，不要使用这种方式

#### 代码实现

```java
package type3;

/**
 * 懒汉式（线程不安全）
 */

public class Singleton3 {
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

    // 提供一个静态的公有方法，当使用到该方法时才去创建instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

```

### 懒汉式（线程安全，同步方法）

#### 优缺点

1. 解决了线程不安全问题
2. 效率太低了，每个线程在想获得类的实例时候，执行getInstance()方法都要进行同步。而其实这个方法只执行一次实例化代码就够了，后面的想获得该类实例,直接return就行了。方法进行同步效率太低
3. 结论:在实际开发中，不推荐使用这种方式

#### 代码实现

```java
package type4;

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
```



### 懒汉式（线程安全，同步代码块）

#### 优缺点

1. 这种方式，本意是想对第四种实现方式的改进，因为前面同步方法效率太低，改为同步产生实例化的的代码块
2. 但是这种同步并不能起到线程同步的作用。跟第3种实现方式遇到的情形一致，假如一个线程进入了if (singleton == null)判断语句块，还未来得及往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例
3. 结论:在实际开发中，不能使用这种方式

#### 代码实现

<a href="https://sm.ms/image/yRaxGnCAZ7uPoJe" target="_blank"><img src="https://i.loli.net/2021/01/13/yRaxGnCAZ7uPoJe.png" alt="QQ截图20210113100716.png"></a>



### 双重检查

#### 优缺点

1. Double-Check概念是多线程开发中常使用到的，如代码中所示，我们进行了两次if (singleton == null)检查，这样就可以保证线程安全了。
2. 这样，实例化代码只用执行一次，后面再次访问时，判断if (singleton == null),直接return实例化对象，也避免的反复进行方法同步.
3. 线程安全;延迟加载;效率较高
4. 结论:在实际开发中，推荐使用这种单例设计模式

#### 代码实现

```java
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
```



### 静态内部类

#### 优缺点

1. 这种方式采用了类装载的机制来保证初始化实例时只有一个线程。
2. 静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，才会装载singletonInstance类，从而完成Singleton的实例化。
3. 类的静态属性只会在第一次加载类的时候初始化，所以在这里，VM帮助我们保证了线程的安全性，在类进行初始化时，别的线程是无法进入的。
4. 优点:避免了线程不安全，利用静态内部类特点实现延迟加载，效率高
5. 结论:推荐使用

#### 代码实现

```java
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
```



### 枚举

#### 优缺点

1. 这借助JDK1.5中添加的枚举来实现单例模式。不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象。
2. 这种方式是Effective Java作者Josh Bloch提倡的方式
3. 结论:推荐使用

#### 代码实现

```java
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

```

