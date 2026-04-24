# Maven 项目与 IDEA 基础知识

## 一、Maven 是什么

Maven 是 Java 项目的**构建和依赖管理工具**，主要解决两个问题：

### 1. 依赖管理

不需要手动下载 jar 包，只需在 `pom.xml` 中声明需要什么库，Maven 会自动从仓库下载，并处理依赖之间的依赖关系（传递依赖）。

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
</dependency>
```

Maven 会自动下载 `javax.servlet-api-4.0.1.jar`。

### 2. 构建管理

提供标准化的项目构建生命周期：

| 命令 | 作用 |
|------|------|
| `mvn compile` | 编译 `.java` 为 `.class` |
| `mvn test` | 编译 + 运行测试用例 |
| `mvn package` | 编译 + 测试 + 打成 war/jar 包 |
| `mvn install` | 打包 + 安装到本地仓库 |

---

## 二、Maven 构建流程详解

### 1. mvn compile — 编译

把 `.java` 文件编译成 `.class` 文件：

```
src/main/java/Hello.java  →  target/classes/Hello.class
```

### 2. mvn test — 测试

流程：
1. 编译主代码（`src/main/java`）
2. 编译测试代码（`src/test/java`）
3. 运行所有测试

### 3. mvn package — 打包

最终产物：`target/java_web-1.0-SNAPSHOT.war`

war 文件是一个压缩包，结构类似：

```
java_web-1.0-SNAPSHOT.war
├── WEB-INF/
│   ├── classes/        ← 你的 .class 文件
│   ├── lib/            ← 所有依赖的 jar 包
│   └── web.xml         ← Web配置
├── index.jsp
└── ...
```

### 4. mvn install — 安装到本地仓库

将 war/jar 放到本地 Maven 仓库，默认路径：`~/.m2/repository/`

安装后，其他 Maven 项目可以依赖你这个项目。

### 生成 war 之后要做什么

war 包不能直接运行，需要部署到 **Web 容器**（如 Tomcat）中：

```
生成 war 包
    ↓
部署到 Tomcat 的 webapps 目录
    ↓
启动 Tomcat
    ↓
浏览器访问 http://localhost:8080/java_web-1.0-SNAPSHOT/
```

### 常用命令组合

| 命令 | 作用 |
|------|------|
| `mvn clean` | 清理 `target` 目录 |
| `mvn clean compile` | 清理后重新编译 |
| `mvn clean package` | 清理后重新打包（最常用） |
| `mvn clean install` | 清理后安装到本地仓库 |

---

## 三、pom.xml 配置文件

`pom.xml` 是 Maven 项目的核心配置文件（Project Object Model）。

### 项目坐标

```xml
<modelVersion>4.0.0</modelVersion>
<groupId>com.example</groupId>       <!-- 组织名，通常是公司域名的反写 -->
<artifactId>java_web</artifactId>    <!-- 项目名 -->
<version>1.0-SNAPSHOT</version>      <!-- 版本号，SNAPSHOT 表示开发中 -->
<name>java_web</name>                <!-- 项目的显示名称 -->
<packaging>war</packaging>           <!-- 打包方式：war（Web项目）或 jar（普通项目） -->
```

五个元素合起来是项目的**唯一坐标**。

### 全局变量（properties）

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.target>1.8</maven.compiler.target>
    <maven.compiler.source>1.8</maven.compiler.source>
    <junit.version>5.10.2</junit.version>
</properties>
```

定义全局变量，后面的依赖中可以用 `${junit.version}` 引用。

### 依赖管理（dependencies）

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>  <!-- 运行时由容器（Tomcat）提供 -->
</dependency>
```

`<scope>` 的含义：

| scope | 含义 |
|-------|------|
| `compile` | 默认值，编译和运行都需要 |
| `provided` | 编译时需要，运行时由容器提供 |
| `test` | 只在测试时需要 |
| `runtime` | 编译时不需要，运行时需要 |

### 构建插件（plugins）

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <version>3.3.2</version>
        </plugin>
    </plugins>
</build>
```

`maven-war-plugin` 负责将项目打包成 `.war` 文件。

### pom.xml 总结

做三件事：
1. **定义项目坐标**（groupId + artifactId + version）
2. **声明依赖**（Maven 自动下载和管理）
3. **配置构建插件**（打包、编译规则）

---

## 四、Maven 标准目录结构

### 约定优于配置

Maven 采用**约定优于配置**（Convention over Configuration）的设计思想，内部写死了一套目录规则：

| 目录 | Maven 认为它是什么 |
|------|-------------------|
| `src/main/java` | **主源码**，需要编译 |
| `src/main/resources` | 静态资源（配置文件等），复制即可，不编译 |
| `src/test/java` | **测试源码**，需要编译 |
| `src/test/resources` | 测试资源 |

执行 `mvn compile` 时，Maven **只会**去找 `src/main/java` 下面的 `.java` 文件。放在其他目录（如 `src/Test`），Maven **完全不会理会**。

### 自定义源码目录

需要在 `pom.xml` 中显式指定：

```xml
<build>
    <sourceDirectory>src</sourceDirectory>  <!-- 不推荐 -->
</build>
```

但不推荐，会破坏标准约定。

---

## 五、Java 包名与目录结构

### 包名对应目录

Java 中用 `.` 分隔包层级：

```java
package com.example.java_web;
```

对应到文件系统：

```
com/
└── example/
    └── java_web/
        └── Hello.class
```

### 编译后的路径

| 代码中的 package | 编译后的路径 |
|-----------------|-------------|
| 无（默认包） | `target/classes/Hello.class` |
| `package com.example;` | `target/classes/com/example/Hello.class` |
| `package com.example.java_web;` | `target/classes/com/example/java_web/Hello.class` |

**不是 Maven 根据 `.` 拆分目录，是 Java 编译器根据 `package` 语句决定存放位置。**

---

## 六、.iml 文件（IDEA 项目配置）

### 是什么

`.iml` 是 **IntelliJ IDEA 专用的项目配置文件**，告诉 IDE 怎么识别和编辑这个项目。

### 类比其他工具

| 工具 | 配置文件 | 作用 |
|------|---------|------|
| Maven | `pom.xml` | 告诉 Maven 项目结构和依赖 |
| IDEA | `.iml` | 告诉 IDEA 怎么识别和编辑项目 |
| Eclipse | `.classpath` + `.project` | 告诉 Eclipse 源码和依赖 |

### 做了什么

1. 声明这是一个什么类型的项目（Java/Android/Python 等）
2. 标记哪些目录是源码目录
3. 标记哪些目录是测试源码目录
4. 定义依赖顺序（JDK、源码、jar 包等）
5. 配置编译输出路径

### 实际效果对比

**没有正确配置 `.iml` 时：**
- `.java` 文件图标是普通文件
- 没有代码补全
- 没有运行按钮
- `import` 报红

**配置好 `.iml` 后：**
- 源码目录显示为蓝色图标
- 测试源码显示为绿色图标
- 有代码补全提示
- 右键有 Run 选项
- `import` 正常解析

### 生成方式

通常不需要手动创建，以下方式会自动生成：

| 方式 | 触发条件 |
|------|---------|
| Maven 项目导入 | IDEA 读取 `pom.xml` 自动生成 |
| Gradle 项目导入 | IDEA 读取 `build.gradle` 自动生成 |
| 新建项目 | IDEA 创建项目时自动生成 |

### 配置示例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<module type="JAVA_MODULE" version="4">
  <component name="NewModuleRootManager" inherit-compiler-output="true">
    <exclude-output />
    <content url="file://$MODULE_DIR$">
      <sourceFolder url="file://$MODULE_DIR$/src/test" isTestSource="true" />
    </content>
    <orderEntry type="inheritedJdk" />
    <orderEntry type="sourceFolder" forTests="false" />
  </component>
</module>
```

| 属性/元素 | 含义 |
|-----------|------|
| `type="JAVA_MODULE"` | Java 类型的模块 |
| `inherit-compiler-output="true"` | 使用默认编译输出目录 |
| `sourceFolder` | 标记源码目录 |
| `isTestSource="true"` | 测试源码（绿色），`false` 为主源码（蓝色） |
| `$MODULE_DIR$` | `.iml` 文件所在目录 |
| `orderEntry` | 依赖顺序：JDK → 源码目录 |

### 总结一句话

**`.iml` 是 IDEA 的"说明书"，告诉编辑器项目的源码在哪、依赖在哪、该怎么运行。不用于构建，只用于 IDE 层面的代码提示和运行支持。**

---

## 七、IDEA 目录标记操作

### Mark Directory as

右键目录 → **Mark Directory as**：

| 标记类型 | 颜色 | 含义 |
|---------|------|------|
| Sources Root | 蓝色 | 主业务代码，编译到 `target/classes` |
| Test Sources Root | 绿色 | 测试代码，编译到 `target/test-classes` |
| Resources Root | 棕色 | 资源文件，直接复制不编译 |
| Excluded | 红色 | 排除，IDE 不扫描 |

这个操作会更新 `.iml` 文件，不影响 Maven 构建。

### 临时测试文件的写法

在 `src/test` 下创建包含 `main` 方法的类，右键 → **Run** 即可执行：

```java
// src/test/HelloTest.java
public class HelloTest {
    public static void main(String[] args) {
        System.out.println("临时测试代码");
    }
}
```

注意：`mvn package` 打包时，`src/test` 下的代码**不会**被包含在 war 包中。
