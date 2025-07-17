# DarkMaven
这个存储库仅能帮助 [DarkMeowTeam](https://github.com/DarkMeowTeam) 内部仓库更便捷的上传 artifact 到私有仓库

除非你能修改源代码为自己所用, 否则几乎没有帮助

如何使用

1.在 `settings.gradle.kts` 中 `pluginManagement` 的 `repositories` 块添加

````kts
pluginManagement {
    repositories {
        // 其它仓库
        // 例如: gradlePluginPortal()
        maven("https://nekocurit.asia/repository/release/")
    }
}
````

2.在 `build.gradle.kts` 中添 `plugins` 块中添加

```kts
plugins {
    // 其它插件
    id("net.darkmeow.dark-maven") version "1.0"
}
```

3.完成导入工作后, 最后可以在 `build.gradle.kts` 添加 `mavenDarkMeow()` 即可完成
```kts
    publishing {
        repositories {
            // 其它仓库
            mavenDarkMeow()
        }
    }
```

4.除了一次性工作外, 您还需要额外配置环境变量才能启用 分别是 `MAVEN_USERNAME` 和 `MAVEN_PASSWORD`

* 对于 [DarkMeowTeam](https://github.com/DarkMeowTeam) 内部成员, 更佳建议通过 Github Action 发布依赖