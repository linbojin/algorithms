Build Project

```
cd algorithms/java/
mvn clean package
```

Run examples

```
# with out inputs
java -cp target/algs-1.0-SNAPSHOT.jar com.linbo.algs.examples.convexhull.GrahamScan

# with inputs
java -cp target/algs-1.0-SNAPSHOT.jar com.linbo.algs.examples.collinear.FastCollinearPoints input6.txt
```

Knowledge Graph

|       | LinkedList | Array  | 
|-------|------------|--------|
| Stack | [LinkedStack](src/main/java/com/linbo/algs/datatypes/LinkedStack.java) | [ResizingArrayStack](src/main/java/com/linbo/algs/datatypes/ResizingArrayStack.java) |
| Queue | [LinkedQueue](src/main/java/com/linbo/algs/datatypes/LinkedQueue.java) | [ResizingArrayQueue](src/main/java/com/linbo/algs/datatypes/ResizingArrayQueue.java) |

| Sortings | worst | average | best | remarks | 
|----------|:-----:|:-------:|:----:|---------|
| [Insertion](src/main/java/com/linbo/algs/sortings/Insertion.java) | n<sup>2</sup>/2 | n<sup>2</sup>/4 | N | **stable**, for small N or partially ordered |
| [Shell](src/main/java/com/linbo/algs/sortings/Shell.java) | ? | ? | N | subquatratic |
| [Quick](src/main/java/com/linbo/algs/sortings/Quick.java) | n<sup>2</sup>/2 | 2NInN | NlogN | **fastest in practices** |
| [Quick3way](src/main/java/com/linbo/algs/sortings/Quick3way.java) | n<sup>2</sup>/2 | 2NInN | N | improves quicksort in presence of **duplicated keys** |
| [Merge](src/main/java/com/linbo/algs/sortings/Merge.java) | NlogN | NlogN | NlogN | **NlogN guarantee, stable** |
| [Heap](src/main/java/com/linbo/algs/sortings/Heap.java) | 2NlogN | 2NlogN | NlogN | NlogN guarantee, in-place, **Priority Queue** |

Project Structure

```
src.main.java.com.linbo.algs 
├── App.java
├── datatypes
│   ├── LinkedBag.java
│   ├── LinkedQueue.java
│   ├── LinkedStack.java
│   ├── MaxPQ.java
│   ├── MinPQ.java
│   ├── QuickFindUF.java
│   ├── QuickUnionUF.java
│   ├── ResizingArrayBag.java
│   ├── ResizingArrayQueue.java
│   ├── ResizingArrayStack.java
│   ├── UF.java
│   └── WeightedQuickUnionUF.java
├── examples
│   ├── KnuthShuffle.java
│   ├── collinear
│   │   ├── BruteCollinearPoints.java
│   │   ├── FastCollinearPoints.java
│   │   ├── LineSegment.java
│   │   └── Point.java
│   ├── convexhull
│   │   ├── GrahamScan.java
│   │   └── Point2D.java
│   ├── kdtree
│   │   ├── KdTree.java
│   │   ├── KdTreeVisualizer.java
│   │   ├── NearestNeighborVisualizer.java
│   │   ├── PointSET.java
│   │   └── RangeSearchVisualizer.java
│   ├── percolation
│   │   ├── Percolation.java
│   │   └── PercolationStats.java
│   ├── puzzle
│   │   ├── Board.java
│   │   └── Solver.java
│   └── queues
│       ├── Deque.java
│       ├── Permutation.java
│       └── RandomizedQueue.java
├── leetcode
│   ├── TwoSum.java
│   ├── TwoSumDataStructureDesign.java
│   └── TwoSumInOrder.java
├── mapreduce
│   └── sort.java
├── searchings
│   ├── BST.java
│   ├── BinarySearch.java
│   ├── BinarySearchST.java
│   ├── QuickSelect.java
│   └── RedBlackBST.java
├── sortings
│   ├── Heap.java
│   ├── Insertion.java
│   ├── Merge.java
│   ├── MergeBU.java
│   ├── Quick.java
│   ├── Quick3way.java
│   ├── Selection.java
│   └── Shell.java
└── util
    ├── In.java
    ├── StdDraw.java
    ├── StdOut.java
    ├── StdRandom.java
    ├── StdStats.java
    ├── Stopwatch.java
    └── StopwatchCPU.java
```
