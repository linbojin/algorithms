# Balanced Search Trees
Table of Contents
=================

   * [Balanced Search Trees](#balanced-search-trees)
      * [Symbol Table Review](#symbol-table-review)
      * [2-3 search trees](#2-3-search-trees)
         * [2-3 tree demo](#2-3-tree-demo)
         * [Local transformations in a 2-3 tree](#local-transformations-in-a-2-3-tree)
         * [Global properties in a 2-3 tree](#global-properties-in-a-2-3-tree)
         * [2-3 tree: performance](#2-3-tree-performance)
         * [ST implementations: summary](#st-implementations-summary)
         * [2-3 tree: implementation?](#2-3-tree-implementation)
      * [red-black BSTs](#red-black-bsts)
         * [Left-leaning red-black BSTs (Guibas-Sedgewick 1979 and Sedgewick 2007)](#left-leaning-red-black-bsts-guibas-sedgewick-1979-and-sedgewick-2007)
         * [Left-leaning red-black BSTs: 1-1 correspondence with 2-3 trees](#left-leaning-red-black-bsts-1-1-correspondence-with-2-3-trees)
         * [Search implementation for red-black BSTs](#search-implementation-for-red-black-bsts)
         * [Red-black BST representation](#red-black-bst-representation)
         * [Elementary red-black BST operations](#elementary-red-black-bst-operations)
            * [Left rotation](#left-rotation)
            * [Right rotation](#right-rotation)
            * [Color flip](#color-flip)
         * [Insertion in a LLRB tree](#insertion-in-a-llrb-tree)
            * [Warmup 1. Insert into a tree with exactly 1 node.](#warmup-1-insert-into-a-tree-with-exactly-1-node)
            * [Case 1. Insert into a 2-node at the bottom](#case-1-insert-into-a-2-node-at-the-bottom)
            * [Warmup 2. Insert into a tree with exactly 2 nodes.](#warmup-2-insert-into-a-tree-with-exactly-2-nodes)
            * [Case 2. Insert into a 3-node at the bottom.](#case-2-insert-into-a-3-node-at-the-bottom)
            * [passing red links up the tree](#passing-red-links-up-the-tree)
         * [Red-black BST construction demo](#red-black-bst-construction-demo)
         * [Java implementation](#java-implementation)
         * [visualization](#visualization)
         * [Balance in LLRB trees](#balance-in-llrb-trees)
         * [ST implementations: summary](#st-implementations-summary-1)
      * [B-trees](#b-trees)
         * [File system model](#file-system-model)
         * [B-trees (Bayer-McCreight, 1972)](#b-trees-bayer-mccreight-1972)
         * [Searching in a B-tree](#searching-in-a-b-tree)
         * [Insertion in a B-tree](#insertion-in-a-b-tree)
         * [Balance in B-tree](#balance-in-b-tree)
         * [Building a large B tree](#building-a-large-b-tree)
         * [Balanced trees in the wild](#balanced-trees-in-the-wild)
         
## Symbol Table Review
![](media/14861257683491.jpg)

## 2-3 search trees
![](media/14861301090924.jpg)<br>
![](media/14861302987986.jpg)

### 2-3 tree demo
![](media/14861301992525.jpg)

![](media/14861302229890.jpg)

### Local transformations in a 2-3 tree
![](media/14861303742127.jpg)

### Global properties in a 2-3 tree
![](media/14861304052644.jpg)

### 2-3 tree: performance
![](media/14861304490927.jpg)

### ST implementations: summary
![](media/14861304724527.jpg)

### 2-3 tree: implementation?
![](media/14861305138583.jpg)


## red-black BSTs
[RedBlackBST.java](../java/src/main/java/com/linbo/algs/searchings/RedBlackBST.java)<br>
### Left-leaning red-black BSTs (Guibas-Sedgewick 1979 and Sedgewick 2007)
![](media/14861866739881.jpg)

**An equivalent definition**<br>
![](media/14861867796395.jpg)

### Left-leaning red-black BSTs: 1-1 correspondence with 2-3 trees
![](media/14861868265223.jpg)

### Search implementation for red-black BSTs
![](media/14861868646668.jpg)

### Red-black BST representation
![](media/14861869118099.jpg)

### Elementary red-black BST operations
#### Left rotation
![](media/14861870563579.jpg)<br>
![](media/14861870660314.jpg)

#### Right rotation
![](media/14861872103887.jpg)<br>
![](media/14861872320175.jpg)

#### Color flip
![](media/14861873339591.jpg)<br>
![](media/14861873474315.jpg)

### Insertion in a LLRB tree
![](media/14861874031284.jpg)

#### Warmup 1. Insert into a tree with exactly 1 node.
![](media/14861874325609.jpg)

#### Case 1. Insert into a 2-node at the bottom
![](media/14861874699946.jpg)

#### Warmup 2. Insert into a tree with exactly 2 nodes.
![](media/14861875439874.jpg)

#### Case 2. Insert into a 3-node at the bottom.
![](media/14861876050295.jpg)

#### passing red links up the tree
![](media/14861877668508.jpg)

### Red-black BST construction demo
[33DemoRedBlackBST.mov](media/33DemoRedBlackBST.mov)

### Java implementation
![](media/14861885299561.jpg)

### visualization
![](media/14861885591068.jpg)

![](media/14861885796698.jpg)

### Balance in LLRB trees
![](media/14861885998852.jpg)

![](media/14861887078631.jpg)

### ST implementations: summary![](media/14861886164962.jpg)

 --------------------------------------------------------------
 
## B-trees
### File system model
![](media/14861904208976.jpg)

### B-trees (Bayer-McCreight, 1972)
![](media/14861904381109.jpg)

### Searching in a B-tree
![](media/14861904537897.jpg)

### Insertion in a B-tree
![](media/14861904690898.jpg)

### Balance in B-tree
![](media/14861904860642.jpg)

### Building a large B tree
![](media/14861905338647.jpg)

### Balanced trees in the wild
![](media/14861905533991.jpg)




