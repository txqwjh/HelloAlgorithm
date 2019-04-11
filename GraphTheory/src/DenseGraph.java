import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 稠密图 - 邻接矩阵
public class DenseGraph {
    private int n, m; // n 为顶点 V，m 为边 E

    // 指定是否为有向图
    private boolean directed;

    List<List<Boolean>> g;

    public DenseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Boolean> line = new ArrayList<>();
            for (int j = 0; j < n; j++)
                line.add(false);
            g.add(line);
        }
    }

    public int V() {
        return n;
    }

    public int E() {
        return m;
    }

    public void addEdge(int v, int w) {
        assert (v >= 0 && v < n);
        assert (w >= 0 && w < n);

        if (hasEdge(v, w))
            return;

        g.get(v).set(w, true);
        if (!directed)
            g.get(w).set(v, true);

        m++;
    }

    public boolean hasEdge(int v, int w) {
        assert (v >= 0 && v < n);
        assert (w >= 0 && w < n);

        return g.get(v).get(w);
    }


    class adjIterator implements Iterator {
        private DenseGraph G;
        private int v;
        int index;

        public adjIterator(DenseGraph graph, int v) {
            this.G = graph;
            this.v = v;
            this.index = -1;
        }

        @Override
        public boolean hasNext() {
            return index >= G.V();
        }

        @Override
        public Object next() {
            for (index += 1; index < G.V(); index++)
                if (G.g.get(v).get(index))
                    return index;
            return -1;
        }
    }

}