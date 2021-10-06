# ChangeLog

### Release_1.1.1_20211006_build_A

#### 功能构建

- 为 AccountBookOperateService 增加方法。
  - AccountBookOperateService#updateAccountBook。
  - AccountBookOperateService#deleteAccountBook。

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
