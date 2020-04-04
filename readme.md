# 常用spring项目模板，快速搭建spring项目
 * maven 常见问题汇总：  
 [maven](https://www.jianshu.com/p/91c91493ee4f)  
 [maven之resource配置](https://blog.csdn.net/u011781521/article/details/79052725)  
 [resources官方](http://maven.apache.org/pom.html#Resources)  
 [Plugins官方](http://maven.apache.org/pom.html#Plugins)  
 [Dependencies官方](http://maven.apache.org/pom.html#Dependencies)  
 [Dependency Management官方](http://maven.apache.org/pom.html#Dependency%20Management)  
 [super Pom](http://maven.apache.org/pom.html#The%20Super%20POM)
## 一、基本信息
   * packaging(**提供方**) 打包方式，默认为jar
   * type（**适用方**）打包方式，默认为jar
   * scope 依赖的范围，详细见2
   * optional 标记依赖是否可选，详细见3
   * exclusions 排除传递依赖，详细见3  
   * plugins 插件配置，具体的插件配置可进行查阅
   * resource
   * pluginManagement
   
## 二、scope(依赖范围)
   * maven编译项目主代码时需要一套classpath
   * maven在编译和执行测试的时候会使用里一套classpath
   * 在实际运行时，又会使用一套classpath
   
   依赖范围用来控制依赖以上三种classpath的关系，如：
   * compile 编译依赖范围  
     1. 默认的依赖范围  
     2. 使用此范围，对于编译、测试、运行三种classpath都有效，例如spring-core  
   * test 测试依赖范围
     1. 只对测试classpath有效，运行时无效
     2. 在编译主代码和运行项目使用时无法使用此类依赖
     3. 如JUNIT
   * provided 编译及测试依赖范围
     1. 对于编译即测试classpath有效，运行时无效
     2. 例如 servlet-api，编译和测试时需要依赖，但是在运行项目时，容器已经提供，不需要重复引入
   * runtime 运行时依赖
     1. 对于测试和运行的classpath有效
     2. 例如jdbc驱动实现，项目主代码编译主需要JDK提供的jdbc接口，只有在执行测试或者运行项目的时候才需要具体JDBC实现
   * system 系统依赖范围
     1. 依赖范围和provide完全一致
     2. 使用该范围需要使用systemPath元素显式的依赖文件路径
     3. 往往和本级绑定，可能造成构建的不可移植性
## 三、传递性依赖 （**exclusions**)   
  1. 概念： **假设A依赖B,B依赖C，那么A对于C是传递性依赖**  
  2. 依赖调解： A -> B -> C -> X(1.0)、A -> D -> X(2.0)，  
    maven有两个原则去调解依赖重复：
   * 路径最近优先
   * 第一声明优先
   * 最好的做法是使用exclusions标签排除传递性依赖   
  3. 假如项目A依赖项目B，项目B依赖于项目X和Y，  
     B对于X和Y都是可选依赖：A -> B、B -> X\Y（可选），   
     X、Y不会得以传递，称为传递性依赖。
     引入这种依赖特性的原因主要是，  
     可能项目B实现了两个特性，一个特性依赖X，一个特性依赖Y，而且这两个特性是互斥的。
     