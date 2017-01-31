# Quicksort
Table of Contents
=================

   * [Quicksort](#quicksort)
      * [Quicksort](#quicksort-1)
         * [Java code for partitioning](#java-code-for-partitioning)
         * [Quicksort trace and Animation](#quicksort-trace-and-animation)
         * [Implementation details](#implementation-details)
         * [empirical analysis](#empirical-analysis)
         * [best-case and worst-case analysis](#best-case-and-worst-case-analysis)
         * [Average-case analysis](#average-case-analysis)
         * [Quicksort properties](#quicksort-properties)
      * [Quicksort: practical improvements](#quicksort-practical-improvements)
      * [Selection](#selection)
         * [Quick-select](#quick-select)
      * [Duplicate keys](#duplicate-keys)
         * [3-way partitioning](#3-way-partitioning)
         * [Dijkstra 3-way partitioning demo](#dijkstra-3-way-partitioning-demo)
         * [Dijkstra's 3-way partitioning trace and visual trace](#dijkstras-3-way-partitioning-trace-and-visual-trace)
         * [3-way quicksort: Java implementation](#3-way-quicksort-java-implementation)
         * [Duplicate keys: lower bound](#duplicate-keys-lower-bound)
      * [System sorts](#system-sorts)
         * [Sorting applications](#sorting-applications)
         * [Java system sorts](#java-system-sorts)
         * [Engineering a system sort](#engineering-a-system-sort)
         * [Tukey's ninther](#tukeys-ninther)
         * [Achilles heel in Bentley-McIlroy implementation (Java system sort)](#achilles-heel-in-bentley-mcilroy-implementation-java-system-sort)
         * [System sort: Which algorithm to use?](#system-sort-which-algorithm-to-use)
         * [Sorting summary](#sorting-summary)
         
![](media/14858486968970.jpg)

## Quicksort
[Quick.java](../java/src/main/java/com/linbo/algs/examples/Quick.java)<br>
![](media/14858488006458.jpg)<br>
![](media/14858488114593.jpg)<br>
![](media/14858488209815.jpg)<br>

### Java code for partitioning![](media/14858490392229.jpg)<br>
![](media/14858490594994.jpg)<br>

### Quicksort trace and Animation
![](media/quick-sort_random.gif)
![](media/quick-sort_nearly-sorted.gif)<br>
![](media/14858491675250.jpg)

### Implementation details
![](media/14858494882511.jpg)

### empirical analysis
![](media/14858495123896.jpg)

### best-case and worst-case analysis
![](media/14858496413799.jpg)<br>
![](media/14858496520129.jpg)

### Average-case analysis
![](media/14858497109854.jpg)<br>
![](media/14858497213536.jpg)

### Quicksort properties
![](media/14858501722521.jpg)

## Quicksort: practical improvements
[QuickX.java](https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/QuickX.java)<br>
![](media/14858502210451.jpg)<br>
![](media/14858502885022.jpg)

**sort with median-of-3 and cutoff to insertion sort: visualization**<br>
![](media/14858503422165.jpg)

## Selection
![](media/14858508191488.jpg)

### Quick-select
![](media/14858508904461.jpg)<br>
![](media/14858509589622.jpg)

**Theoretical context for selection**<br>
![](media/14858510641248.jpg)

## Duplicate keys
![](media/14858511547141.jpg)<br>
![](media/14858511672669.jpg)

**the problem**<br>
![](media/14858512269427.jpg)

### 3-way partitioning
[Quick3way.java](../java/src/main/java/com/linbo/algs/examples/Quick3way.java)<br>
![](media/14858513073911.jpg)

### Dijkstra 3-way partitioning demo
![](media/14858513803516.jpg)<br>
![](media/14858514535433.jpg)

### Dijkstra's 3-way partitioning trace and visual trace
![](media/14858522649651.jpg)<br>
![](media/14858523186183.jpg)

### 3-way quicksort: Java implementation
![](media/14858523355974.jpg)

### Duplicate keys: lower bound
![](media/14858523905363.jpg)

## System sorts
### Sorting applications
![](media/14858534685801.jpg)

### Java system sorts
![](media/14858534940481.jpg)

### Engineering a system sort
![](media/14858537122311.jpg)

### Tukey's ninther
![](media/14858537428608.jpg)

### Achilles heel in Bentley-McIlroy implementation (Java system sort)
![](media/14858538139767.jpg)

### System sort: Which algorithm to use?
![](media/14858538613278.jpg)

![](media/14858538989579.jpg)

### Sorting summary
![](media/14858539444533.jpg)


