# Java 学习项目

使用 Java 21 练习基础语法的个人学习项目。练习源码放在 `基础概念/`，Markdown 学习笔记放在 `notes/`。

## 环境

- JDK 21（Temurin）
- 由 Sdkman 管理：`~/.sdkman/candidates/java/current`

## 目录结构

```text
基础概念/   # 按知识点存放 Java 练习源码
notes/      # Markdown 学习笔记
skills/     # 项目 Codex skills
out/        # 编译产物，不提交
```

## 学习笔记

- [函数的使用](notes/函数的使用.md)
- [包的使用](notes/包的使用.md)
- [修饰符](notes/修饰符.md)

## 运行示例

以 `基础概念/包的使用/` 为例：

```bash
javac -d out 基础概念/包的使用/Start.java 基础概念/包的使用/Dog/*.java
java -cp out 包的使用.Start
```
