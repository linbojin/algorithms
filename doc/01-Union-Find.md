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
## Quick-Find [eager approach]
![](media/14838602886285.jpg)

**Java implementation** [source code](../java/src/main/java/com/linbo/algs/datatypes/QuickFindUF.java)
![](media/14838604580021.jpg)

**Quick-find is too slow**<br>
![](media/14838606491534.jpg)

**Quadratic algorithms do not scale !!!**
![](media/14838607543772.jpg)

## Quick-Union [Lazy approach]
![](media/14838650996257.jpg)

**Java implementation** [source code](../java/src/main/java/com/linbo/algs/datatypes/QuickUnionUF.java)
![](media/14838653277236.jpg)

**Quick-union is also too slow**
![](media/14838671401867.jpg)

