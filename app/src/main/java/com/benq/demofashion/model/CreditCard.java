package com.benq.demofashion.model;

import com.benq.demofashion.Utils.Utils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CreditCard {
    @Id
    public Long id;
    public Long userId;
    public String userName;//持有者名字
    public String cardNum;//卡号
    public String whichBank;//哪个银行的
    public int cardType;//卡等级，分类 0 ~ 5

    @Override
    public String toString() {
        return Utils.logObject(this);
    }

    @Generated(hash = 637432226)
    public CreditCard(Long id, Long userId, String userName, String cardNum,
                      String whichBank, int cardType) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.cardNum = cardNum;
        this.whichBank = whichBank;
        this.cardType = cardType;
    }

    @Generated(hash = 1860989810)
    public CreditCard() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardNum() {
        return this.cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getWhichBank() {
        return this.whichBank;
    }

    public void setWhichBank(String whichBank) {
        this.whichBank = whichBank;
    }

    public int getCardType() {
        return this.cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }
}
