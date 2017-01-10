# Union-Find
## Problem: Dynamic connectivity
![](media/14838598936355.jpg)

## Model the problem.
**Modeling the connections**
![](media/14838599758991.jpg)

**Implementing the operations**
![](media/14838600244954.jpg)

**Union-find data type (API)**
![](media/14838600473514.jpg)

----------------------------------------------------------------

## Solutions 
## Quick-Find [eager approach]  => O(n ^ 2)
![](media/14838602886285.jpg)

**Java implementation** [source code](../java/src/main/java/com/linbo/algs/datatypes/QuickFindUF.java)
![](media/14838604580021.jpg)

**Quick-find is too slow**<br>
![](media/14838606491534.jpg)

**Quadratic algorithms do not scale !!!**
![](media/14838607543772.jpg)

## Quick-Union [Lazy approach] => O(N)
![](media/14838650996257.jpg)

**Java implementation** [source code](../java/src/main/java/com/linbo/algs/datatypes/QuickUnionUF.java)
![](media/14838653277236.jpg)

**Quick-union is also too slow**
![](media/14838671401867.jpg)

----------------------------------------------------------------

## Improvements
### weighting quick-union => O(lg(n))
Always put smaller tree lower
![](media/14838677996908.jpg)

**Quick-union and weighted quick-union example**
![](media/14838680485004.jpg)

**Java implementation** [source code](../java/src/main/java/com/linbo/algs/datatypes/QuickUnionUF.java)
![](media/14838680922712.jpg)

**Weighted quick-union analysis**
![](media/14838696953334.jpg)
![](media/14838697146110.jpg)
![](media/14838697836573.jpg)

### path compression => O(lg(n))
![](media/14838720393272.jpg)
![](media/14838720689227.jpg)
![](media/14838720823989.jpg)

**Java implementation** [source code](../java/src/main/java/com/linbo/algs/datatypes/UF.java)
![](media/14838721151299.jpg)

----------------------------------------------------------------

## Summary
![](media/14838862736507.jpg)

----------------------------------------------------------------

## Use case
### Percolation
Assignment: http://coursera.cs.princeton.edu/algs4/assignments/percolation.html  <br>
Solution: [Percolation.java](../java/src/main/java/com/linbo/algs/examples/Percolation.java) and [PercolationStats.java](../java/src/main/java/com/linbo/algs/examples/PercolationStats.java) <br>
![](media/14840067575596.jpg)

**Likelihood of percolation**<br>
![](media/14840068253244.jpg)
![](media/14840068401453.jpg)

**Monte Carlo simulation**<br>
![](media/14840068656604.jpg)

**Dynamic connectivity solution to estimate percolation threshold**
![](media/14840069248061.jpg)
![](media/14840069360891.jpg)
![](media/14840069572019.jpg)
![](media/14840069898141.jpg)



