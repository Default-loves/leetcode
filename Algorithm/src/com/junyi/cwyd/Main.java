package com.junyi.cwyd;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.*;

/**
 * @time: 2021/7/15 14:18
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */

public class Main {

    private Logger logger = LoggerFactory.getLogger(Main.class);


    private Coordinate start;
    private Coordinate end;

    public WayLine find2(List<WayLine> list) {
        // 节点信息
        Set<Coordinate> coordinateSet = new HashSet<>();
        // 边信息，Key：节点，Value：与其直接相连的节点
        HashMap<Coordinate, List<Coordinate>> edge = new HashMap<>();
        list.forEach(t -> {
            coordinateSet.add(t.getStart());
            coordinateSet.add(t.getEnd());
            edge.computeIfAbsent(t.getStart(), k -> new ArrayList<>()).add(t.getEnd());
            edge.computeIfAbsent(t.getEnd(), k -> new ArrayList<>()).add(t.getStart());
        });

        // 起点到其他点的距离
        Map<Coordinate, Double> distances  = new HashMap<>();
        for (Coordinate coordinate : coordinateSet) {
            distances.put(coordinate, Double.MAX_VALUE);
        }
        // 判断节点是否已经访问
        HashMap<Coordinate, Boolean> visited  = new HashMap<>();
        for (Coordinate coordinate : coordinateSet) {
            visited.put(coordinate, false);
        }
        distances.put(this.start, 0d);
        this.start.path.add(this.start);
        while (true) {
            // 寻找距离最近的节点
            Coordinate nearestNode = null;
            double minDistance = Double.MAX_VALUE;
            for (Map.Entry<Coordinate, Double> entry : distances.entrySet()) {
                if (minDistance > entry.getValue() && !visited.get(entry.getKey())) {
                    minDistance = entry.getValue();
                    nearestNode = entry.getKey();
                }
            }
            // 全部节点都已遍历完毕
            if (nearestNode == null) {
                break;
            }
            for (Coordinate t : edge.get(nearestNode)) {
                double tmp = distances.get(nearestNode) + t.distance(nearestNode);
                if (distances.get(t) > tmp) {
                    distances.put(t, tmp);
                    t.path.addAll(nearestNode.path);
                    t.path.add(t);
                }
            }
            visited.put(nearestNode, true);
        }
        List<Coordinate> path = this.end.path;
        WayLine pre = new WayLine(this.start, path.get(1));
        WayLine head = pre;
        for (int i = 2; i < path.size(); i++) {
            WayLine last = new WayLine(pre.getEnd(), path.get(i));
            pre.setParent(last);
            pre = last;
        }
        return head;
    }

    @Test
    public void test() {
        Coordinate c1 = new Coordinate(0, 100);
        Coordinate c2 = new Coordinate(2, 100);
        Coordinate c3 = new Coordinate(100, 100);
        Coordinate c4 = new Coordinate(2, 50);
        Coordinate c5 = new Coordinate(50, 50);
        this.start = c1;
        this.end = c5;
        ArrayList<WayLine> list = new ArrayList<>();
        list.add(new WayLine(c1, c2));
        list.add(new WayLine(c2, c3));
        list.add(new WayLine(c2, c4));
        list.add(new WayLine(c4, c5));
        list.add(new WayLine(c3, c5));
        this.find2(list);

    }
}
