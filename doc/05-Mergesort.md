# Mergesort
Table of Contents
=================

   * [Mergesort](#mergesort)
      * [Two classic sorting algorithms](#two-classic-sorting-algorithms)
      * [Mergesort](#mergesort-1)
         * [Trace and Animation](#trace-and-animation)
         * [Empirical analysis](#empirical-analysis)
         * [Mergesort: number of compares and array accesses](#mergesort-number-of-compares-and-array-accesses)
         * [Mergesort analysis: memory](#mergesort-analysis-memory)
         * [Practical improvements](#practical-improvements)
         * [visualization](#visualization)
      * [Bottom-up mergesort](#bottom-up-mergesort)
      * [Sorting complexity](#sorting-complexity)
         * [Compare-based lower bound for sorting](#compare-based-lower-bound-for-sorting)
         * [Complexity results in context](#complexity-results-in-context)
      * [comparators](#comparators)
         * [Comparator interface](#comparator-interface)
         * [Comparator interface: implementing](#comparator-interface-implementing)
         * [Polar order](#polar-order)
      * [stability](#stability)
         * [Stability: insertion sort](#stability-insertion-sort)
         * [Stability: selection sort](#stability-selection-sort)
         * [Stability: shellsort](#stability-shellsort)
         * [Stability: mergesort](#stability-mergesort)
        
## Two classic sorting algorithms
![](media/14849910002972.jpg)

## Mergesort
[Merge.java](../java/src/main/java/com/linbo/algs/sortings/Merge.java)<br>
![](media/14849910371420.jpg)

**Abstract in-place merge**<br>
![](media/14849910717982.jpg)

**Merging: Java implementation**
![](media/14849910963673.jpg)

**Assertions**<br>
![](media/14849911323857.jpg)

**Mergesort: Java implementation**<br>
![](media/14849911863278.jpg)

### Trace and Animation
![](media/14849911572250.jpg)

![random](media/merge-sort_random.gif)
![nearly-sorted](media/merge-sort_nearly-sorted.gif)<br>

### Empirical analysis
![](media/14849913219037.jpg)

### Mergesort: number of compares and array accesses
![](media/14850781475793.jpg)

**Divide-and-conquer recurrence**<br>
![](media/14850782241288.jpg)
![](media/14850783255836.jpg)<br>
![](media/14850783550062.jpg)<br>
![](media/14850783669122.jpg)
### Mergesort analysis: memory![](media/14850785620225.jpg)

### Practical improvements
![](media/14850786281865.jpg)<br>
![](media/14850786471126.jpg)<br>
![](media/14850787857933.jpg)

### visualization
![](media/14850789084991.jpg)

-------------------------------------------------

## Bottom-up mergesort
[MergeBU.java](../java/src/main/java/com/linbo/algs/sortings/MergeBU.java)<br>
![](media/14850791044082.jpg)
![](media/14850791171463.jpg)

-------------------------------------------------

## Sorting complexity
![](media/14850877677714.jpg)

**Decision tree (for 3 distinct items a, b, and c)**<br>
![](media/14850878126803.jpg)

### Compare-based lower bound for sorting
![](media/14850878659507.jpg)<br>
![](media/14850879257391.jpg)

**Complexity of sorting**<br>
![](media/14850880826753.jpg)

### Complexity results in context
![](media/14850881697747.jpg)
![](media/14850882009653.jpg)

------------------------------------------

## comparators
![](media/14850882704041.jpg)

### Comparator interface
![](media/14850883337561.jpg)

**Comparator interface: system sort**<br>
![](media/14850883636008.jpg)

**Comparator interface: using with our sorting libraries**<br>
![](media/14850884402720.jpg)

### Comparator interface: implementing
![](media/14850885019583.jpg)<br>
![](media/14850885368548.jpg)

### Polar order
[Point2D.java](../java/src/main/java/com/linbo/algs/examples/Point2D.java)<br>
![](media/14850885749219.jpg)
![](media/14850886126537.jpg)

**Comparator interface: polar order**<br>
![](media/14850886998861.jpg)

-------------------------------------------------------------------

## stability
![](media/14850889186043.jpg)<br>
![](media/14850889370242.jpg)

### Stability: insertion sort
![](media/14850889806760.jpg)

### Stability: selection sort
![](media/14850890120375.jpg)

### Stability: shellsort
![](media/14850890323187.jpg)

### Stability: mergesort
![](media/14850890960545.jpg)<br>
![](media/14850891192667.jpg)


