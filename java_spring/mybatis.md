# 快速搭建mybatis

idea插件：MyBatisCodeHelperPro

# 概念
POJO（Plain Old Java Object）
- 只有属性（字段）
- 提供 getter/setter
- 无任何框架依赖
- 不继承任何类、不实现任何接口

JavaBean 比 POJO 更严格
- 类是 public
- 有无参构造函数
- 所有属性 private
- 提供 getter/setter
- （可选）实现 Serializable 序列化接口

ORM 框架 ≈ 用 JavaBean/POJO 操作数据库的工具


# 多表关联查询 (一对一)

```xml
<mapper namespace="com.southwind.mapper.RentMapper">

    <resultMap id="rentMap" type="com.southwind.entity.Rent">
        <id column="rentid" property="rentid"></id>
        <result column="price" property="price"></result>
        <result column="begindate" property="begindate"></result>
        <result column="returndate" property="returndate"></result>
        <result column="carnumber" property="carnumber"></result>
        <result column="createtime" property="createtime"></result>
        <association property="customer" javaType="com.southwind.entity.Customer">
            <id column="identity" property="identity"></id>
            <result column="identity" property="identity"></result>
            <result column="opername" property="custname"></result>
            <result column="address" property="address"></result>
            <result column="phone" property="phone"></result>
            <result column="career" property="career"></result>
        </association>
    </resultMap>

    <select id="getById" resultMap="rentMap">
        select * from bus_rent,bus_customer where bus_rent.identity = bus_customer.identity and rentid = #{rentid}
    </select>

</mapper>
```

# 延迟加载 级联查询 （一对多）
延迟加载、惰性加载、**按需加载**，提升程序运行效率的方式

持久层（访问数据库）原则：Java 程序和数据库交互频率越低越好

MyBatis 通过延迟加载来减少 Java 和数据库交互次数

学生类
```java
public class Student {
    private Integer id;
    private String name;
    private Class clazz;
}
```
学生mapper，班级属性利用cid通过另外一条sql去查
```xml
<resultMap id="studentMap" type="com.southwind.entity.Student">
    <id column="id" property="id"></id>
    <result column="name" property="name"></result>
    <association
        property="clazz"
        javaType="com.southwind.entity.Class"
        column="cid"
        select="com.southwind.mapper.ClassMapper.getById"></association>
</resultMap>

<select id="getById" parameterType="java.lang.Integer" resultMap="studentMap">
    select * from student where id = #{id}
</select>
```

班级类
```java
public class Class {
    private Integer id;
    private String name;
    private List<Student> students;
}
```
班级mapper
```xml
<select id="getById" parameterType="java.lang.Integer" resultType="com.southwind.entity.Class">
    select * from class where id = #{id}
</select>
```
 
查询 Student ，会将对应的 Class 一并查出

按需加载：```<setting name="lazyLoadingEnabled" value="true"/>```

查询 Student 的时候，如果没有调用 Class 的相关字段，则只执行一条 SQL 只查 Student。(```student.getName()```)

如果需要访问 Class 的相关字段，此时再执行第二条 SQL。(```student.getClazz().getName()```)

根据具体的需求，动态选择 SQL 语句的条数

# 缓存 

减少 Java 程序和数据库的交互次数，从而提升程序的运行效率。

当查询出数据后，将数据保存到缓存中，当下一次需要使用同样的数据时，直接从缓存中取出数据，不需要再查询数据库

一级缓存默认开启，无法关闭，SqlSession 级别的缓存，同一个 SqlSession 的情况下，一级缓存有效，如果使用两个 SqlSession，则一级缓存失效，一级缓存不需要进行任何配置，直接使用即可。

二级缓存是比一级缓存作用域更大的缓存机制，它是 Mapper 级别的，只要是同一个 Mapper，无论使用多少个 SqlSession，二级缓存都是存在的。

二级缓存默认是关闭的，需要手动开启。

缓存验证：打开sql日志，看执行次数。

## 开启二级缓存
1、config.xml 中配置
```xml
<settings>
    <setting name="cacheEnabled" value="true"/>
</settings>
```
2、Mapper.xml 中配置：```<cache></cache>```

3、实体类实现序列化接口

# 动态sql

MyBatis 需要开发者手动写 SQL

MyBatis 提供了动态 SQL 的功能，可以根据具体业务需求动态拼接 SQL 语句

场景：查询条件为对象，对象属性为空，则不参与查询
```java
public interface SupplierMapper {
    public Supplier get(Supplier user);
}
```

choose  wehen 如果有一个满足，后面的就不再判断了。