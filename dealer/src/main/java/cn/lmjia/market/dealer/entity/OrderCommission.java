package cn.lmjia.market.dealer.entity;

import cn.lmjia.market.core.entity.MainOrder;
import cn.lmjia.market.dealer.entity.pk.OrderCommissionPK;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * 一个订单产生的佣金记录
 * 必须保证一个订单就一个事情只能做一次！
 *
 * @author CJ
 */
@Entity
@Setter
@Getter
@IdClass(OrderCommissionPK.class)
public class OrderCommission {
    /**
     * 佣金来源订单
     * 如果这个订单产生退款或者什么什么的，这个佣金也应当被退回
     */
    @Id
    @ManyToOne(optional = false)
    private MainOrder source;
    /**
     * 是否退款
     */
    @Id
    private boolean refund;
    /**
     * 佣金产生时间
     */
    @Column(columnDefinition = "timestamp")
    private LocalDateTime generateTime;


}