# 獬豸

> 獬豸(xiè zhì) haetae  
中国上古十大神兽之一，能辨曲直，又有神羊之称，它是勇猛、公正的象征，是司法“正大光明” “清平公正”“光明天下”的象征  

![](https://img.shields.io/badge/haetae-building-yellow.svg)

## 1. 项目背景
### 1.1 业务背景
传统纸质材料管理学生活动信息，不仅繁琐耗时，而且可能会出现管理漏洞和僵化管理的问题。haetae 用于 管理学生校园活动、志愿活动、义工等信息，希望能通过技术手段改善现状，优化管理和学生的体验。  

### 1.2 技术栈&运行环境  

```
maven
Java 1.8
Spring boot
Mysql
```

### 1.3 开发人员
> 参与开发人员，排名不分先后  

1. [dango.yxm](https://github.com/Outside-man)
2. [Messiah.jk](https://github.com/MessiahJK)
3. [Coink.wc](https://github.com/CoinkWang)  

<br/>
<br/>

---
## 2. 项目结构

### 2.1 目录结构
```
|-- biz  （业务层）
    |-- impl        (业务逻辑实现)
|-- core  (独立的业务领域核心)
    |-- user        (用户领域核心)
    |-- activity    (活动领域核心)
|-- web   (接口交互层)
|-- util  (通用工具模块)
```


### 2.2 模块结构
module  | dependency    | desc
----    | ----          | ----
util    | \             | 通用工具模块
core    | util          | 领域核心模块
biz     | util core     | 业务层
web     | util biz core | web交互层

1. haetae-parent 管理所有子模块(除util)依赖版本.
2. util 作为基础模块，不能依赖任意其他模块，它独立管理依赖版本.
3. core 层之间模块不能互相依赖.
4. biz 层依赖所有core模块，领域交际部分在biz模块写逻辑.
5. web 层理论不会有任何业务逻辑，仅仅作为接口交互.

<br/>
<br/>

---







