# AGENTS.md

这是用户的 Java 学习项目。修改代码、生成笔记或新增内容前，先阅读本文件并遵守以下约定。

## 项目约定

- 使用 Java 21（Temurin），由 Sdkman 管理：`~/.sdkman/candidates/java/current`
- 笔记和对话使用中文
- 包名使用英文小写，例如 `basic.functions`，不要使用中文包名
- 编译产物放在 `out/`，不要提交
- 不要提交 `.idea/`、`*.class`、`.DS_Store`

## 目录结构

- `基础概念/`：按知识点存放 Java 练习源码
- `notes/`：由 skill 生成的 Markdown 学习笔记
- `skills/`：本项目的 Codex skills
  - `learning-path-notes`：根据学习路径生成 Markdown 笔记

## 常用命令

```bash
javac -d out 基础概念/函数类的使用/Run.java
java -cp out 函数类的使用.Test
```

## Skill 使用

需要生成学习笔记时，使用 `skills/learning-path-notes`：先确认当前学习路径，再按 `assets/note-template.md` 生成笔记到 `notes/`。
