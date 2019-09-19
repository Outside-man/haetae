# 獬豸

> 獬豸(xiè zhì) haetae  
中国上古十大神兽之一，能辨曲直，又有神羊之称，它是勇猛、公正的象征，是司法“正大光明” “清平公正”“光明天下”的象征  

![](https://img.shields.io/badge/haetae-building-yellow.svg)

## 1. 项目背景
### 1.1 业务背景
传统纸质材料管理学生活动信息，不仅繁琐耗时，而且可能会出现管理漏洞和僵化管理的问题。haetae 用于 管理学生校园活动、志愿活动、义工活动、社会实践、党建活动等信息，希望能通过技术手段改善现状，优化管理和学生的体验。
同时能方便管理物资，通过二维码扫码的形式借用，归还/报损。借用的时候也能清楚看到物资相关信息，实现透明公开，管理员管理也十分方便。

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
    |-- user        (用户领域核心)
    |-- activity    (活动领域核心)
    |-- asset       (物资领域核心)
    |-- finance     (财务领域核心)
    |-- organization(组织领域核心)
    |-- certificate (证书领域核心)
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


### 2.2 业务码使用情况
业务码是在生成唯一32位id时，4位表示业务的标识码

#### user 模块
id      | desc              | id     | desc
----    | ----              | ----   | ----
0001    | user              | 0002   | role
0003    | user role relation| 0004   | perm
0005    | role perm relation| 0006   | user perm relation
0007    | user info         | 0008   | major

#### activity 模块
id      | desc              | id     | desc
----    | ----              | ----   | ----
1001    | activity          | 1002   | activity record
1003    | organization      | 1004   | position record
1006    | activity entry    | 1007   | activity entry record
1008    | activity blacklist

#### asset 模块
id      | desc                   | id     | desc
----    | ----                   | ----   | ----
2001    | asset                  | 2002   | asset loan record
2003    | asset back record

#### finance 模块
id      | desc            | id     | desc
----    | ----            | ----   | ----
3001    | finance message | 3002   | finance total

#### organization 模块
id      | desc                 | id     | desc
----    | ----                 | ----   | ----
0011    | organization         | 0012   | organization member relation
0013    | organization relation

<br/>
<br/>

---








