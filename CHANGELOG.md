# ChangeLog

### Release_1.6.0_20250605_build_A

#### 功能构建

- Wiki 编写。
  - 构建 wiki 目录结构。
  - docs/wiki/en_US/Contents.md。
  - docs/wiki/en_US/Introduction.md。
  - docs/wiki/zh_CN/Contents.md。
  - docs/wiki/zh_CN/Introduction.md。

- 更新 README.md。

- FTP 路径机制优化。
  - 新增 FtpPathResolver 代替 FtpConstants，使得 FTP 路径由静态定义变为动态解析。
  - 新增 `ftp/path.properties`，使得服务的 FTP 根路径支持动态配置。
  - 新的 FTP 路径机制使得不同组服务共享同一 FTP 服务器时，存储文件的路径隔离成为可能。

- 优化实体映射器机制。

- 为部分工具类中方法的入口参数增加 `@Nonnull` 注解。
  - com.dwarfeng.familyhelper.finance.impl.service.telqos.CommandUtil。

- 优化部分单元测试代码，以规避潜在的 bug。
  - com.dwarfeng.familyhelper.finance.impl.service.AccountBookMaintainServiceImplTest。
  - com.dwarfeng.familyhelper.finance.impl.service.BankCardBalanceHistoryMaintainServiceImplTest。
  - com.dwarfeng.familyhelper.finance.impl.service.BankCardMaintainServiceImplTest。
  - com.dwarfeng.familyhelper.finance.impl.service.BankCardTypeIndicatorMaintainServiceImplTest。
  - com.dwarfeng.familyhelper.finance.impl.service.BillFileInfoMaintainServiceImplTest。
  - com.dwarfeng.familyhelper.finance.impl.service.FundChangeMaintainServiceImplTest。
  - com.dwarfeng.familyhelper.finance.impl.service.FundChangeTypeIndicatorMaintainServiceImplTest。
  - com.dwarfeng.familyhelper.finance.impl.service.PoabMaintainServiceImplTest。
  - com.dwarfeng.familyhelper.finance.impl.service.RemindDriverInfoMaintainServiceImplTest。
  - com.dwarfeng.familyhelper.finance.impl.service.RemindDriverSupportMaintainServiceImplTest。
  - com.dwarfeng.familyhelper.finance.impl.service.TotalBalanceHistoryMaintainServiceImplTest。
  - com.dwarfeng.familyhelper.finance.impl.service.UserMaintainServiceImplTest。

- 导入运维指令。
  - com.dwarfeng.datamark.service.telqos.*。

- 增加 Hibernate 实体数据标记字段，并应用相关实体侦听器。
  - com.dwarfeng.acckeeper.impl.bean.entity.HibernateAccount。
  - com.dwarfeng.acckeeper.impl.bean.entity.HibernateProtectorInfo。

- 增加依赖。
  - 增加依赖 `dwarfeng-datamark` 以应用其新功能，版本为 `1.0.2.a`。

- SPI 目录结构优化。
  - 将驱动机制的 SPI 接口与抽象类提相关代码文件提升至 `sdk` 模块中。
  - 将执行机制的 SPI 接口与抽象类提相关代码文件提升至 `sdk` 模块中。
  - 将推送机制的 SPI 接口与抽象类提相关代码文件提升至 `sdk` 模块中。
  - 将重置机制的 SPI 抽象类提相关代码文件提升至 `sdk` 模块中。

- 依赖升级。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.3.3.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `jetty` 依赖版本为 `9.4.57.v20241219` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.119.Final` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.6.4.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.5.10.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.13.a` 以规避漏洞。

#### Bug修复

- 修正 `impl` 模块中错误的 dubbo 应用名称。

- 修复部分 FastJson 实体中缺失的字段。
  - com.dwarfeng.familyhelper.finance.sdk.bean.entity.JSFixedFastJsonAccountBook。

#### 功能移除

- 删除 `node` 模块中多余的配置文件。
  - `ftp/test-avatar.png`。

---

### Release_1.5.0_20241208_build_B

#### 功能构建

- (无)

#### Bug修复

- 修复预设查询中的错误。
  - FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK_WITH_CONDITION_DISPLAY。

#### 功能移除

- (无)

---

### Release_1.5.0_20241208_build_A

#### 功能构建

- 优化配置文件。
  - 优化 `application-context-database.xml`，使得更多属性可以在配置文件中配置。

- dwarfeng-ftp 优化。
  - 优化 FtpConstants 中的常量类型，使其更加符合 dwarfeng-ftp 的接口标准。

- 增加 `PusherAdapter`。
  - 建议任何插件的推送器实现都继承自该适配器。
  - 适配器对所有的事件推送方法都进行了空实现。
  - 解决了增加了新的事件时，旧的推送器实现必须实现新的方法的问题。
  - 从此以后，推送器增加新的事件，将被视作兼容性更新。

- dubbo 优化。
  - 优化 `dubbo/connection.properties` 中配置的键名。

- 启动器优化。
  - 将入口方法中完整独立的功能封装在子方法中，使入口方法代码结构更加清晰。

- 增加预设查询。
  - FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK_WITH_CONDITION_DISPLAY。

- 部分 dubbo 消费者服务注册配置添加 `check="false"` 属性。
  - snowflakeGenerateService。

- Dubbo 微服务增加分组配置。

- 为 dubbo 增加超时时间的配置选项。

- 优化重置功能。
  - 为重置处理器的重置方法添加同步锁。

- 新增实体字段。
  - FundChange.recordedDate。

- 升级 spring-telqos 并应用其新功能。
  - 使用包扫描的方式注册指令。
  - 优化 `telqos/connection.properties` 中配置的键名。

- 优化 `node` 模块部分服务启停脚本的注释。
  - binres/familyhelper-start.bat。
  - binres/familyhelper-start.sh。

- 优化项目启停脚本设置程序的根目录的方式。

- 优化启停脚本的目录结构。

- 日志功能优化。
  - 优化默认日志配置，默认配置仅向控制台输出 `INFO` 级别的日志。
  - 优化日志配置结构，提供 `conf/logging/settings.xml` 配置文件及其不同平台的参考配置文件，以供用户自定义日志配置。
  - 优化日志配置结构，提供 `confext/logging-settings.xml` 配置文件，以供外部功能自定义日志配置。
  - 优化启动脚本，使服务支持新的日志配置结构。
  - 优化 `assembly.xml`，使项目打包时输出新的日志配置结构。
  - 优化 `confext/README.md`，添加新的日志配置结构的相关说明。

- 启停脚本优化。
  - 优化 Windows 系统的启动脚本。
  - 优化 Linux 系统的启停脚本。

- 依赖升级。
  - 升级 `subgrade` 依赖版本为 `1.5.7.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `snowflake` 依赖版本为 `1.5.3.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.2.2.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `spring` 依赖版本为 `5.3.39` 以规避漏洞。
  - 升级 `protobuf` 依赖版本为 `3.25.5` 以规避漏洞。
  - 升级 `mysql` 依赖版本为 `8.2.0` 以规避漏洞。
  - 升级 `snakeyaml` 依赖版本为 `2.0` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.22` 以规避漏洞。
  - 升级 `jetty` 依赖版本为 `9.4.55.v20240627` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.115.Final` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.9.3` 以规避漏洞。
  - 升级 `guava` 依赖版本为 `32.0.1-jre` 以规避漏洞。
  - 升级 `slf4j` 依赖版本为 `1.7.36` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.14.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.11.b` 以规避漏洞。

#### Bug修复

- 修复项目中部分依赖的异常未能正确映射的 bug。
  - 修复 `dwarfeng-ftp` 依赖的异常未能正确映射的 bug。

- 修复项目中部分依赖的异常代码偏移未能正确设置的 bug。
  - 修复 `subgrade` 依赖的异常代码偏移未能正确设置的 bug。
  - 修复 `snowflake` 依赖的异常代码偏移未能正确设置的 bug。
  - 修复 `dwarfeng-ftp` 依赖的异常代码偏移未能正确设置的 bug。

- 删除 `assembly.xml` 中多余的配置。

- 重置功能 bug 修复。
  - 在提醒驱动未启动的状态下重置提醒驱动，会导致提醒驱动意外启动。

- 修复部分功能启动延时为 0 时行为不正确的 bug。
  - 提醒驱动服务启动。

- 补全 `assembly.xml` 中缺失的配置。

#### 功能移除

- (无)

---

### Release_1.4.6_20230215_build_A

#### 功能构建

- 优化 `MapStruct` 实体映射 `Mapper` 接口的路径。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.4.5_20230101_build_A

#### 功能构建

- (无)

#### Bug修复

- 修正 `HibernateRemindDriverSupport` 中的字段名称错误。

#### 功能移除

- (无)

---

### Release_1.4.4_20221221_build_A

#### 功能构建

- 优化可选加载项的文件结构。
  - opt/opt-pusher.xml。
  - opt/opt-redriver.xml。
  - opt/opt-resetter.xml。

#### Bug修复

- 修正 `CronResetter` 中日志输出的文案错误。

#### 功能移除

- (无)

---

### Release_1.4.3_20221220_build_A

#### 功能构建

- (无)

#### Bug修复

- 修正 `ResetterHandlerImpl` 中 `LOGGER` 构造中的错误。

#### 功能移除

- (无)

---

### Release_1.4.2_20221219_build_A

#### 功能构建

- 增加系统事件。
  - 提醒驱动重置事件。

- 增加重置机制，实现提醒驱动器的定时重置。

- 增加提醒驱动的优雅停机逻辑。

- 优化 RemindDriver.Context 中抛出异常的类型。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.4.1_20221218_build_A

#### 功能构建

- 增加运维指令。
  - com.dwarfeng.familyhelper.finance.impl.service.telqos.RemindDriveCommand。
  - com.dwarfeng.familyhelper.finance.impl.service.telqos.RemindDriveLocalCacheCommand。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.4.0_20221203_build_A

#### 功能构建

- 提醒逻辑实现。
  - 提醒驱动机制实现。
  - 分布式锁实现。

- `OperateHandlerValidator` 重命名为 `HandlerValidator`。

- 使用 `MapStruct` 重构 `BeanTransformer`。

- 增加依赖。
  - 增加依赖 `protobuf` 以规避漏洞，版本为 `3.19.6`。
  - 增加依赖 `guava` 以规避漏洞，版本为 `31.1-jre`。
  - 增加依赖 `gson` 以规避漏洞，版本为 `2.8.9`。
  - 增加依赖 `snakeyaml` 以规避漏洞，版本为 `1.33`。
  - 增加依赖 `jackson` 以规避漏洞，版本为 `2.14.0`。
  - 增加依赖 `javax.servlet-api` 以规避漏洞，版本为 `4.0.1`。
  - 增加依赖 `jboss-logging` 以规避漏洞，版本为 `3.4.3.Final`。

- 插件升级。
  - 升级 `maven-deploy-plugin` 插件版本为 `2.8.2`。

- 添加实体。
  - com.dwarfeng.familyhelper.finance.stack.bean.entity.BillFileInfo。
  - com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo。
  - com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverSupport。

- 依赖升级。
  - 升级 `mysql` 依赖版本为 `8.0.31` 以规避漏洞。
  - 升级 `jedis` 依赖版本为 `3.8.0` 以规避漏洞。
  - 升级 `spring-data-redis` 依赖版本为 `2.7.5` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.18` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.5.7` 以规避漏洞。
  - 升级 `curator` 依赖版本为 `4.3.0` 以规避漏洞。
  - 升级 `hibernate-validator` 依赖版本为 `6.2.5.Final` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `beta-0.3.2.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.10.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.3.0.a` 以规避漏洞。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.1.2.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.10.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.5.a` 以规避漏洞。

- 优化操作服务验证环节的代码结构。

- 完成实体的操作服务。
  - com.dwarfeng.familyhelper.finance.stack.service.BillFileOperateService。

- 优化部分 DTO 实体的名称。
  - 此处更新与旧版本不兼容。

- 优化部分异常的名称。
  - 此处更新与旧版本不兼容。

#### Bug修复

- (无)

#### 功能移除

- 删除不需要的依赖。
  - 删除 `commons-lang3` 依赖。
  - 删除 `commons-io` 依赖。
  - 删除 `joda-time` 依赖。
  - 删除 `httpcomponents` 依赖。
  - 删除 `el` 依赖。
  - 删除 `zkclient` 依赖。
  - 删除 `commons-net` 依赖。
  - 删除 `dozer` 依赖。

---

### Release_1.3.1_20211225_build_A

#### 功能构建

- 将部分常量移入 Constants 类中。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.3.0_20211219_build_A

#### 功能构建

- 升级 `log4j2` 依赖版本为 `2.15.0` 以规避 `CVE-2021-44228` 漏洞。

- 升级 `log4j2` 依赖版本为 `2.17.0` 以规避 `CVE-2021-45105` 漏洞。

- 优化部分 OperateService 的运行逻辑。
  - com.dwarfeng.familyhelper.finance.stack.service.AccountBookOperateService。
  - com.dwarfeng.familyhelper.finance.stack.service.BankCardOperateService。
  - com.dwarfeng.familyhelper.finance.stack.service.BalanceOperateService。
  - com.dwarfeng.familyhelper.finance.stack.service.FundChangeOperateService。

#### Bug修复

- 清理单元测试代码中部分无用的字段。

#### 功能移除

- (无)

---

### Release_1.2.7_20211204_build_A

#### 功能构建

- 优化资金变更实体的更新逻辑。
  - 更新资金变更时，发生时间字段由之前的更新为当前时间改变为不变。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.2.6_20211106_build_A

#### 功能构建

- (无)

#### Bug修复

- 修正调用 ServiceExceptionCodes.setExceptionCodeOffset 后，报警代码不更新的 bug。

- 修正 CrudOperation 实现中的 bug。

#### 功能移除

- (无)

---

### Release_1.2.5_20211102_build_A

#### 功能构建

- 完善实体的验证注解。
  - com.dwarfeng.familyhelper.finance.sdk.bean.entity.WebInputBankCardTypeIndicator。
  - com.dwarfeng.familyhelper.finance.sdk.bean.entity.WebInputFundChangeTypeIndicator。

- 将程序自身的异常代码偏移加入配置项。

- 新增服务方法。
  - AccountBookOperateService.resetGuestPermission。

#### Bug修复

- 修正实体的 CrudOperation 类中的错误代码。
  - com.dwarfeng.familyhelper.finance.impl.service.operation.AccountBookCrudOperation。
  - com.dwarfeng.familyhelper.finance.impl.service.operation.BankCardCrudOperation。
  - com.dwarfeng.familyhelper.finance.impl.service.operation.UserCrudOperation。

- 去除用户实体维护服务以及数据访问层的多余实现。

#### 功能移除

- (无)

---

### Release_1.2.4_20211027_build_A

#### 功能构建

- (无)

#### Bug修复

- 更正部分实体对应的表名错误。
  - com.dwarfeng.familyhelper.finance.impl.bean.entity.HibernateBankCardTypeIndicator。
  - com.dwarfeng.familyhelper.finance.impl.bean.entity.HibernateFundChangeTypeIndicator。

- 修正数据库连接默认配置不规范的 schema 名称。

#### 功能移除

- (无)

---

### Release_1.2.3_20211017_build_A

#### 功能构建

- 增加维护服务的预设查询。
  - FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK_TYPE_EQUALS。
  - FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK_TYPE_EQUALS_DESC。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.2.2_20211017_build_A

#### 功能构建

- 优化银行卡余额历史的记录逻辑。

#### Bug修复

- 修复银行卡余额提交时对回滚的银行卡金额处理存在问题的 bug。

#### 功能移除

- (无)

---

### Release_1.2.1_20211017_build_A

#### 功能构建

- 增加维护服务的预设查询。
  - TotalBalanceHistoryMaintainService.CHILD_FOR_ACCOUNT_BOOK_DESC。
  - BankCardBalanceHistoryMaintainService.CHILD_FOR_BANK_CARD_DESC。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.2.0_20211014_build_A

#### 功能构建

- 根据业务要求变更实体。
  - com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange。

- 优化单元测试代码，使得实体增删改查潜在的错误被漏过的可能性进一步降低。

- 增加操作服务。
  - com.dwarfeng.familyhelper.finance.stack.service.FundChangeOperateService。

#### Bug修复

- 修复部分服务错误的继承了处理器接口的 bug。
  - com.dwarfeng.familyhelper.finance.stack.service.AccountBookOperateService。
  - com.dwarfeng.familyhelper.finance.stack.service.BankCardOperateService。

#### 功能移除

- (无)

---

### Release_1.1.3_20211012_build_A

#### 功能构建

- (无)

#### Bug修复

- 修正 `BalanceOperateHandlerImpl.recordCommit` 方法执行时行为不正常的 bug。

#### 功能移除

- (无)

---

### Release_1.1.2_20211008_build_A

#### 功能构建

- (无)

#### Bug修复

- 补全 HibernateBankCard 缺失的字段。
  - HibernateBankCard.cardType。

#### 功能移除

- 删除异常包中个异常类不正确的构造器函数。
  - com.dwarfeng.familyhelper.finance.stack.exception.AccountBookNotExistsException。
  - com.dwarfeng.familyhelper.finance.stack.exception.BankCardNotExistsException。
  - com.dwarfeng.familyhelper.finance.stack.exception.IllegalBankCardStateException。
  - com.dwarfeng.familyhelper.finance.stack.exception.UserNotExistsException。
  - com.dwarfeng.familyhelper.finance.stack.exception.UserNotPermittedForAccountBookException。

---

### Release_1.1.1_20211006_build_A

#### 功能构建

- 为 AccountBookOperateService 增加方法。
  - AccountBookOperateService#updateAccountBook。
  - AccountBookOperateService#removeAccountBook。

- 为 BankCardOperateService 增加方法。
  - BankCardOperateService#updateBankCard。

- 为 PoabMaintainService 增加预设查询。
  - PoabMaintainService.CHILD_FOR_ACCOUNT_BOOK_PERMISSION_LEVEL_EQUALS。
  - PoabMaintainService.CHILD_FOR_USER_PERMISSION_LEVEL_EQUALS。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.0_20211002_build_A

#### 功能构建

- 升级 `subgrade` 依赖至 `1.2.2.a`
- 根据新的预想业务重新设计实体。
  - com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook。
  - com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard。
  - com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardTypeIndicator。
  - com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange。
  - com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChangeTypeIndicator。
- 根据业务设计新的实体。
  - com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab。
  - com.dwarfeng.familyhelper.finance.stack.bean.entity.User。
  - com.dwarfeng.familyhelper.finance.stack.bean.entity.TotalBalanceHistory。
  - com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardBalanceHistory。
- 创建操作服务。
  - com.dwarfeng.familyhelper.finance.stack.service.BalanceOperateService。
  - com.dwarfeng.familyhelper.finance.stack.service.AccountBookOperateService。
  - com.dwarfeng.familyhelper.finance.stack.service.BankCardOperateService。
- 异常与异常代码映射实现。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.0.0_20210822_build_A

#### 功能构建

- 建立必要实体，维护服务通过测试。
- 程序节点搭建完毕，实现正常启动。

#### Bug修复

- (无)

#### 功能移除

- (无)
