# ChangeLog

### Release_1.2.5_20211031_build_A

#### 功能构建

- (无)

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
  - com.dwarfeng.familyhelper.finance.stack.exception.UserNotPermittedException。

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
