package com.junyi.cwyd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 坐标
 *
 * @author TYB
 * @date 2019/12/26
 */
public class Coordinate implements Serializable {
    /**
     * x，横坐标
     */
    public int x;
    /**
     * y，纵坐标
     */
    public int y;

    public List<Coordinate> path = new ArrayList<>();

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public boolean near(Coordinate node) {
        double distance = distance(node);
        return distance <= 40;
    }

    /**
     * 构建坐标对象（前端地图使用的是（y,x）模式，这里做一下转换）
     *
     * @param coordinate 字符坐标，xy用<code>,</code>分割
     * @return {@link Coordinate}
     */
    public static Coordinate of(String coordinate) {
        if (coordinate == null) {
            return null;
        }
        String[] strings = coordinate.split(",");
        if (strings.length < 2) {
            return null;
        }
        int yy = Integer.parseInt(strings[0].trim());
        int xx = Integer.parseInt(strings[1].trim());
        return new Coordinate(xx, yy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + ']';
    }

    /**
     * 与node的距离
     *
     * @param node 节点
     * @return double
     */
    public double distance(Coordinate node){
        return Math.sqrt(Math.abs((this.x - node.x) * (this.x - node.x) + (this.y - node.y) * (this.y - node.y)));
    }

    /**
     * 围绕 o点顺时针旋转后的坐标
     * @param o
     * @param angle
     * @return {@link Coordinate} 基于o点的相对坐标
     */
    public Coordinate rotate(Coordinate o, int angle) {
        //角度转成弧度
        //需要顺时针旋转，但是算法是逆时针的，所以角度加负号
        double rotAngle = Math.toRadians(-(360-angle));

        double x = o.x + (this.x - o.x) * Math.cos(rotAngle) - (this.y - o.y) * Math.sin(rotAngle);
        double y = o.y + (this.x - o.x) * Math.sin(rotAngle) + (this.y - o.y) * Math.cos(rotAngle);
        int ix = BigDecimal.valueOf(x).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        int iy = BigDecimal.valueOf(y).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return new Coordinate(ix - o.x, iy - o.y);
    }

    public Coordinate offset(int x, int y){
        return new Coordinate(x +this.x, y +this.y);
    }
}
