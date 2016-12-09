package tiquartet.CommonModule.util;

public enum CreditChange {
	订单执行时自动增加信用值, 客户较晚撤销订单时扣除信用值, 订单超时异常时自动扣除信用值, 延迟入住时恢复扣除的信用值, 撤销异常订单时恢复一半信用值,撤销异常订单时恢复全部信用值, 用户线下充值信用值;
}
