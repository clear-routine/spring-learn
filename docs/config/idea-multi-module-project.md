# IDEA 如何创建多模块项目
## 创建父工程
父工程主要作为容器，用来聚合所有子模块，本身不编写业务代码。创建步骤如下：

1. 打开 `IDEA`，点击 **New Project**。
2. 在左侧选择 **Maven**。
3. **Name**：输入项目名称，例如 `spring-learning-demos`。
4. **Location**：选择存放代码的硬盘位置。
5. **JDK**：选择常用的 `JDK` 版本（建议使用 `17`）。
6. **目录**：选择内部目录（即 Maven 模板目录）。选择后，下方的 **Archetype** 可选择对应模板。一般可不依赖模板，除非使用公司自定义模板。
7. **Archetype**：选择 `org.apache.maven.archetypes:maven-archetype-quickstart`，这是最简洁的模板，便于快速删除默认代码。
8. **组 ID**：例如 `com.公司名.项目名`。
9. **工件 ID**：与项目名一致。
10. **版本**：保留默认 `1.0-SNAPSHOT`，表示项目处于迭代阶段，一般无需修改。
11. 点击 **Create** 完成项目创建。

**重要**：创建父工程后，根目录下会有 `src` 文件夹。因父工程仅作管理用途，不编写代码，请直接删除该 `src` 文件夹，以保持项目结构清晰。

## 修改父工程的 pom.xml
打开根目录下的 `pom.xml`，在 `<version>` 标签下方添加打包方式配置，声明此为聚合工程：

```xml
<packaging>pom</packaging>
```

该配置表示此项目不生成 `.jar` 或 `.war`，仅用于管理子模块。

## 创建子模块
可开始创建子模块，例如 `demo-ioc`、`demo-aop`、`demo-web` 等。步骤如下：

1. 在项目左侧导航栏，右键点击父工程的名称。
2. 其余步骤与创建父工程类似，需**选择父模块**，子模块名称可自定义。
3. 点击 **Create** 完成创建。

创建完成后，IDEA 会自动更新父工程的 `pom.xml`，根目录下会新增 `<modules>` 标签，例如：

```xml
<modules>
    <module>your-demo</module>
</modules>
```

父工程即可统一管理所有子模块。