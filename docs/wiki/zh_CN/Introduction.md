# familyhelper-finance

Familyhelper Finance 是作者个人项目家庭助手的资金管理后台微服务，实现了记账、资金报告等功能。

## 特性

1. Subgrade 架构支持。
2. 支持多种资金管理功能，如记账、资金报告等。
3. 提供 QoS 运维平台，能够在前端页面、GUI 尚未开发完成的环境下使用本服务的功能，并进行运维操作。
4. 关键数据支持数据标记，为运维调试、数据迁移、数据追溯提供一定的辅助作用。

## 文档

该项目的文档位于 [docs](../../../docs) 目录下，包括：

### wiki

wiki 为项目的开发人员为本项目编写的详细文档，包含不同语言的版本，主要入口为：

1. [简介](./Introduction.md) - 镜像的 `README.md`，与本文件内容基本相同。
2. [目录](./Contents.md) - 文档目录。

## 安装说明

1. 下载源码

   使用git进行源码下载。

   ```shell
   git clone git@github.com:DwArFeng/familyhelper-finance.git
   ```

   对于中国用户，可以使用gitee进行高速下载。

   ```shell
   git clone git@gitee.com:dwarfeng/familyhelper-finance.git
   ```

2. 项目打包

   进入项目根目录，执行maven命令

   ```shell
   mvn clean package
   ```

3. 解压

   找到打包后的目标文件

   ```
   familyhelper-finance-node/target/familyhelper-finance-node-[version]-release
   ```

   将其解压至windows系统或者linux系统

4. 配置

   1. 修改conf文件夹下的配置文件，着重修改各连接的url与密码。

5. enjoy it

## 分布式说明

该项目使用 `dubbo` 作为RPC框架，本身支持分布式，您可以在实际使用时，部署该项目任何模块任意数量，以进行分布式运算。
