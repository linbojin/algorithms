# Elementary Sorts
Table of Contents
=================

   * [Elementary Sorts](#elementary-sorts)
      * [Sorting problem](#sorting-problem)
      * [Selection sort](#selection-sort)
      * [Insertion sort](#insertion-sort)
      * [Shell sort](#shell-sort)
      * [Applications](#applications)
         * [shuffling](#shuffling)
         * [Convex hull](#convex-hull)

## Sorting problem
![](media/14844859206056.jpg)
![](media/14844859330288.jpg)
![](media/14844859443535.jpg)

**Callbacks**<br>
![](media/14844859990177.jpg)
![](media/14844860095537.jpg)

**Total order**<br>
![](media/14844860790344.jpg)

**Implementing the Comparable interface**<br>
![](media/14844861292119.jpg)

**Two useful sorting abstractions**<br>
![](media/14844861619602.jpg)
![](media/14844861823194.jpg)

------------------------------------------------------------------

## Selection sort
[Selection.java](../java/src/main/java/com/linbo/algs/sortings/Selection.java)<br>
**animation**<br>
![](media/selection-sort.gif)<br>
![](media/14844862244139.jpg)
![](media/14844862446488.jpg)

**Selection sort inner loop**<br>
![](media/14844863579685.jpg)

**Java implementation**<br>
![](media/14844863880492.jpg)

**mathematical analysis**<br>
![](media/14844864203113.jpg)

------------------------------------------------------------------------

## Insertion sort
[Insertion.java](../java/src/main/java/com/linbo/algs/sortings/Insertion.java)<br>
**animation**<br>
![random](media/insertion-sort_random.gif)
![nearly-sorted](media/insertion-sort_nearly-sorted.gif)<br>
![](media/14844871609595.jpg)
![](media/14844871752865.jpg)

**Insertion sort inner loop**<br>
![](media/14844872702309.jpg)

**Java implementation**<br>
![](media/14844885534409.jpg)

**mathematical analysis**<br>
![](media/14844885839371.jpg)

**best and worst case**<br>
![](media/14844887406613.jpg)

**partially-sorted arrays**<br>
![](media/14844887728821.jpg)

------------------------------------------------------------------------

## Shell sort
[Shell.java](../java/src/main/java/com/linbo/algs/sortings/Shell.java)<br>
**animation**<br>
![random](media/shell-sort_random.gif)
![random](media/shell-sort_nearly-sorted.gif)
<br>
![](media/14845337450961.jpg)

**h-sorting**<br>
![](media/14845337812819.jpg)

**Shellsort example: increments 7, 3, 1**<br>
![](media/14845348330630.jpg)

**Shellsort: intuition**<br>
![](media/14845350714335.jpg)

**Shellsort: which increment sequence to use?**<br>
![](media/14845352388599.jpg)

**Shellsort: Java implementation**<br>
![](media/14845355674245.jpg)

**Shellsort: visual trace**<br>
![](media/14849799441075.jpg)

**Shellsort: analysis**<br>
![](media/14849802729252.jpg)

**Why are we interested in shellsort?**<br>
![](media/14849803671303.jpg)

------------------------------------------------------------------------

## Applications
### shuffling
[KnuthShuffle.java](../java/src/main/java/com/linbo/algs/examples/KnuthShuffle.java)<br>
**How to shuffle an array**<br>
![](media/14849817416576.jpg)

**Shuffle sort**<br>
![](media/14849819655604.jpg)

**Knuth shuffle**<br>
![](media/14849820334525.jpg)<br>
![](media/14849820778089.jpg)

**War story (online poker)**<br>
![](media/14849822072619.jpg)

### Convex hull
[GrahamScan.java](../java/src/main/java/com/linbo/algs/examples/GrahamScan.java)<br>
![](media/14849846246174.jpg)

**Convex hull: mechanical algorithm**<br>
![](media/14849848277260.jpg)

**Convex hull application: motion planning**<br>
![](media/14849849643436.jpg)

**Convex hull application: farthest pair**<br>
![](media/14849849823308.jpg)

**Convex hull: geometric properties**<br>
![](media/14849857571047.jpg)

**Graham scan demo**<br>
![](media/14849858020260.jpg)<br>
![](media/14849858585613.jpg)<br>
![](media/14849858848177.jpg)<br>
![](media/14849859036194.jpg)<br>
![](media/14849859751116.jpg)<br>

**Graham scan: implementation challenges**<br>
![](media/14849859339690.jpg)

**When we have a good sorting algorithm, it gives us a good convex hull algorithm. Because the most work in convex hull is the sort.**

**Implementing ccw**<br>
![](media/14849874561497.jpg)
![](media/14849874806956.jpg)

**Immutable point data type**<br>
![](media/14849874998139.jpg)



