# ChangeLog

### Release_1.4.1_20221204_build_A

#### 功能构建

- (无)

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
