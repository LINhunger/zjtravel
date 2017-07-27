package com.zjtravel.pojo.dto;

import com.zjtravel.web.enums.StatEnum;

/**
 * 异步返回的包装的对象
 * Created by hunger on 2017/2/15.
 */
public class RequestResult<T> {
    private  int state;
    private String stateInfo;
    private T data;

    public RequestResult(StatEnum statEnum, T data) {
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
        this.data = data;
    }

    public RequestResult(StatEnum statEnum) {
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
    }

    public RequestResult(int state,String  stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public RequestResult(int state,String  stateInfo,T data) {
        this.state = state;
        this.stateInfo = stateInfo;
        this.data = data;
    }
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RequestResult{" +
                "state=" + state +
                ", \nstateInfo='" + stateInfo + '\'' +
                ",\n data=" + data +
                '}';
    }
}
