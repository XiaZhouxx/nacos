package com.xz.nacos.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.cloud.gateway.route.RouteDefinition;

public class RouteInfo {
    @TableId(type = IdType.AUTO)
    private int id;

    private String routeId;

    /**
     * route定义 json
     * @see RouteDefinition
     */
    private String routeDefinition;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteDefinition() {
        return routeDefinition;
    }

    public void setRouteDefinition(String routeDefinition) {
        this.routeDefinition = routeDefinition;
    }
}
