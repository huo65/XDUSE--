package Bank;
public class AccountImpl
    extends Account
{
    // 属性定义
//    protected float balance;?
    // 构造方法，按指定余额创建新的帐户
    public AccountImpl(float bal){
        balance = bal;
    }
    // 往帐户中存款
    public void deposit(float amount){
        balance += amount;
    }
    // 从帐户中取款，不足余额则返回false
    public boolean withdraw(float amount){
        if (balance < amount)  return false;
else {
            balance -= amount;
            return true;
}
}
// 查询帐户余额
public float getBalance(){
        return balance;
}
}    
