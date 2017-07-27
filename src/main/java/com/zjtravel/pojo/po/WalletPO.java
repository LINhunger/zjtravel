package com.zjtravel.pojo.po;

/**
 * Created by hunger on 2017/3/3.
 */
public class WalletPO {
    private Long userId;
    private double money;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletPO walletPO = (WalletPO) o;
        return userId != null ? userId.equals(walletPO.userId) : walletPO.userId == null;
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "WalletPO{" +
                "userId=" + userId +
                ", money=" + money +
                '}';
    }
}
