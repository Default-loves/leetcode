package com.junyi.cwyd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 线路
 *
 * @author TYB
 * @date 2019/12/26
 */
public class WayLine implements Cloneable, Serializable {

    /**
     * 开始z坐标
     */
    private Coordinate start;

    /**
     * 结束坐标
     */
    private Coordinate end;

    private ArrayList<Coordinate> reachableCoordinate;

    private WayLine parent;


    public WayLine(Coordinate start, Coordinate end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("线路的开始坐标和结束坐标不能为 null");
        }
        this.start = start;
        this.end = end;
    }

    public Coordinate isReachable(Coordinate coordinate) {
        this.getReachableCoordinate();
        if (indexOf(coordinate) > -1){
            return coordinate;
        }
        //如果没有重合的点就去最近的点
        Coordinate nearestPoint = nearestPoint(coordinate);
        if (nearestPoint != null && coordinate.near(nearestPoint)){
            return nearestPoint;
        }
        return null;
    }

    public int indexOf(Coordinate coordinate){
        return getReachableCoordinate().indexOf(coordinate);
    }

    public int startOrEnd(Coordinate coordinate){
        if (this.start.equals(coordinate)){
            return 1;
        }
        if (this.end.equals(coordinate)){
            return 2;
        }
        return 0;
    }

    /**
     * 得到线路长度（总长）
     *
     * @return int
     */
    public int getLength() {
        int length = getReachableCoordinate().size();
        WayLine p = parent;
        while (p != null) {
            length += p.getReachableCoordinate().size();
            p = p.parent;
        }
        return length;
    }

    /**
     * 得到线路上所有的坐标
     *
     * @return {@link ArrayList< Coordinate >}
     */
    public ArrayList<Coordinate> getReachableCoordinate() {
        if (this.reachableCoordinate == null) {
            this.reachableCoordinate = getWayPoints();
        }
        return reachableCoordinate;
    }

    public Coordinate getStart() {
        return start;
    }

    public WayLine getParent() {
        return parent;
    }


    public void setParent(WayLine parent) {
        this.parent = parent;
    }

    public Coordinate getEnd() {
        return end;
    }

    public void setStart(Coordinate start) {
        this.start = start;
    }

    public void setEnd(Coordinate end) {
        this.end = end;
    }

    /**
     * 获取路径上的所有坐标
     *
     * @return 路径上坐标集合
     */
    public ArrayList<Coordinate> getWayPoints() {

        int x_length = end.x - start.x;
        int y_length = end.y - start.y;

        int all_step = Math.max(Math.abs(x_length), Math.abs(y_length));

        float x_step = (float) x_length / (float) all_step;
        float y_step = (float) y_length / (float) all_step;

        ArrayList<Coordinate> coordinates = new ArrayList<>(all_step + 1);
        for (int i = 0; i <= all_step; i++) {
            int x = BigDecimal.valueOf(start.x + i * x_step).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
            int y = BigDecimal.valueOf(start.y + i * y_step).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
            //基准点
            Coordinate point = new Coordinate(x, y);
            coordinates.add(point);
        }
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WayLine wayLine = (WayLine) o;
        return start.equals(wayLine.start) &&
                end.equals(wayLine.end) &&
                Objects.equals(parent, wayLine.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, parent);
    }

    @Override
    public String toString() {
        return "{" + start + "," + end + ">>" + parent + "}";
    }

    @Override
    public WayLine clone() {
        WayLine clone = new WayLine(start, end);
        clone.parent = parent;
        clone.reachableCoordinate = reachableCoordinate;
        return clone;
    }

    /**
     * 最近的点
     *
     * @param point 点
     * @return {@link Coordinate}
     */
    public Coordinate nearestPoint(Coordinate point) {
        return getFoot(start, end, point);
    }

    /**
     * p3与 p2-p1组成的线段的 垂足
     *
     * @param p1 p1
     * @param p2 p2
     * @param p3 p3
     * @return {@link Coordinate}
     */
    private Coordinate getFoot(Coordinate p1, Coordinate p2, Coordinate p3){
        float dx=p1.x-p2.x;
        float dy=p1.y-p2.y;

        float u=(p3.x-p1.x)*dx+(p3.y-p1.y)*dy;
        u/=dx*dx+dy*dy;

        int x=BigDecimal.valueOf(p1.x+u*dx).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        int y=BigDecimal.valueOf(p1.y+u*dy).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        Coordinate foot=new Coordinate(x, y);

        double d1 = p1.distance(foot);
        double d2 = p2.distance(foot);
        if (!getReachableCoordinate().contains(foot)) {
            if (d1 > d2) return p2;
            else return p1;
        }

        return foot;
    }
}
