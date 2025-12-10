package com.example.year2025;

import com.example.template.Day;

import java.util.*;

public class Day08 extends Day {

    List<Point> points = new ArrayList<>();
    List<Circuit> circuits = new ArrayList<>();
    Map<Point, Circuit> pointToCircuit = new HashMap<>();

    public Day08() {
        super("08", "2025");
        for (int i = 0; i < getInput().size(); i++) {
            String[] parts = getInput().get(i).split(",");
            points.add(new Point(i, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }
    }

    @Override
    public long part1() {
        reset();
        int processed = 0;
        int amount = getInput().size() > 50 ? 1000 : 10; // I dont like my solution, but it works
        for (Edge edge : getSortedEdges()) {
            if (processed >= amount) break;
            processed++;

            Point p = edge.a();
            Point q = edge.b();

            Circuit c1 = pointToCircuit.get(p);
            Circuit c2 = pointToCircuit.get(q);

            if (c1 == c2) continue;

            merge(c1, c2);
        }
        List<Integer> sizes = circuits.stream().map(c -> c.members.size()).sorted(Comparator.reverseOrder()).toList();
        return (long) sizes.get(0) * sizes.get(1) * sizes.get(2);
    }


    @Override
    public long part2() {
        reset();
        Edge lastMerge = null;
        for (Edge e : getSortedEdges()) {
            if (circuits.size() == 1) break;  // woop woop only one left, we done

            Point p= e.a();
            Point q = e.b();

            Circuit c1 = pointToCircuit.get(p);
            Circuit c2 = pointToCircuit.get(q);

            if (c1 == c2) continue;

            lastMerge = e;
            merge(c1, c2);
        }
        return (long) lastMerge.a().x() * lastMerge.b().x();
    }

    private void reset() {
        circuits.clear();
        pointToCircuit.clear();
        for (Point p : points) {
            Circuit c = new Circuit(circuits.size());
            c.members.add(p);
            circuits.add(c);
            pointToCircuit.put(p, c);
        }
    }

    public List<Edge> getSortedEdges() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point q = points.get(j);
                long dist = distanceSquared(p, q);
                edges.add(new Edge(p, q, dist));
            }
        }
        edges.sort(Comparator.comparingLong(Edge::distSq));
        return edges;
    }

    private void merge(Circuit c1, Circuit c2) {
        if (c1.members.size() < c2.members.size()) {
            Circuit tmp = c1;
            c1 = c2;
            c2 = tmp;
        }
        for (Point member : c2.members) {
            c1.members.add(member);
            pointToCircuit.put(member, c1);
        }
        circuits.remove(c2);
    }

    // distance squared calculation - examples like https://www.geeksforgeeks.org/maths/euclidean-distance/
    long distanceSquared(Point a, Point b) {
        long dx = b.x() - a.x();
        long dy = b.y() - a.y();
        long dz = b.z() - a.z();
        return dx * dx + dy * dy + dz * dz;
    }

    record Edge(Point a, Point b, long distSq) {}

    record Point(int id, int x, int y, int z) {}

    static class Circuit {
        int id;
        List<Point> members = new ArrayList<>();

        Circuit(int id) {
            this.id = id;
        }
    }
}
