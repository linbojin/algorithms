# Analytics of algorithms
Table of Contents
=================

   * [Analytics of algorithms](#analytics-of-algorithms)
      * [Introduction](#introduction)
      * [Observations](#observations)
      * [Mathematical Models](#mathematical-models)
      * [order-of-growth classifications](#order-of-growth-classifications)
         * [Binary search demo](#binary-search-demo)
      * [Theory of algorithms](#theory-of-algorithms)
      * [Memory](#memory)
      * [Turning the crank: summary](#turning-the-crank-summary)
      * [Amortized Cost Analysis](#amortized-cost-analysis)

## Introduction
**Reasons to analyze algorithms**
![](media/14842875277142.jpg)

**Some algorithmic successes**
![](media/14842875773952.jpg)
![](media/14842876063440.jpg)

**Scientific method applied to analysis of algorithms**
![](media/14842876820176.jpg)

----------------------------------------------------------------

## Observations
![](media/14842885896230.jpg)

**3-SUM: brute-force algorithm**
![](media/14842886126125.jpg)

**Measuring the running time**<br>
![](media/14842886265579.jpg)

**Empirical analysis**<br>
![](media/14842886865380.jpg)

**Data analysis**<br>
![](media/14842887137954.jpg)
![](media/14842887314195.jpg)

**Prediction and validation**<br>
![](media/14842888053900.jpg)

**Doubling hypothesis**<br>
![](media/14842888489532.jpg)
![](media/14842889581360.jpg)

**Experimental algorithmics**
![](media/14842890720834.jpg)

----------------------------------------------------------------

## Mathematical Models
**Mathematical models for running time**
![](media/14842892000648.jpg)

**2-Sum**<br>
![](media/14842899369789.jpg)


**Cost model**<br>
![](media/14842896131894.jpg)

**Tilde notation**<br>
![](media/14842900355208.jpg)
![](media/14842900726256.jpg)

**Example**<br>
![](media/14842901477729.jpg)
![](media/14842901794390.jpg)

**Estimating a discrete sum**
![](media/14842902010554.jpg)

**Mathematical models for running time**
![](media/14842903081598.jpg)

## order-of-growth classifications
**Common order-of-growth classifications**
![](media/14842903918954.jpg)
![](media/14842905769682.jpg)

**Practical implications of order-of-growth**
![](media/14842906879724.jpg)

### Binary search demo
![](media/14842908000219.jpg)

**Java implementation**<br>
![](media/14842908839249.jpg)

**Mathematical Analysis**<br>
![](media/14842909264147.jpg)

**An N2 log N algorithm for 3-SUM**
![](media/14842910361406.jpg)

**Comparing programs**<br>
![](media/14842913669843.jpg)

## Theory of algorithms
**Types of analyses**<br>
![](media/14842915391352.jpg)
![](media/14842915643542.jpg)

**Theory of algorithms**<br>
![](media/14842916247841.jpg)

**Commonly-used notations in the theory of algorithms**
![](media/14842916997793.jpg)

**Theory of algorithms: example 1(?)**
![](media/14842918004590.jpg)

**Theory of algorithms: example 2(?)**
![](media/14842919384638.jpg)

**Algorithm design approach(?)**
![](media/14842923400797.jpg)

**Commonly-used notations(?)**
![](media/14842924834495.jpg)

## Memory
**Basics**<br>
![](media/14842925535290.jpg)

**Typical memory usage for primitive types and arrays**
![](media/14842925763888.jpg)

**Typical memory usage for objects in Java**
![](media/14842926376163.jpg)
![](media/14842926842293.jpg)

**Typical memory usage summary**
![](media/14842927587550.jpg)

**Example**<br>
![](media/14842928362329.jpg)

## Turning the crank: summary
![](media/14842930182975.jpg)

## Amortized Cost Analysis
Amortized time can be summarized like this:

With some data structures, when an operation is invoked, sometimes it can take a long time (worst case).<br>
However, if the data structure is designed a certain way, the same operation can take a short time during other invocations (best case).<br>
On average, the time it takes to perform the operation is much less than the worst case, and only slightly worse than the best case.<br>
Before you try to understand amortized cost, think of a straightforward way of implementing a dynamic data structure that uses an array of values. Imagine that you want to store all your friends' names in an array, and you will be adding them one by one.<br>

Initially, you have an empty array.<br>
When you add the first friend's name, there is no available space in your empty array. You have to make a new array with 1 available space.<br>
You add your first friend's name to the array. Now, you have a full array with one name.<br>
When you add the second friend's name, there is no available space in your array. You have to make a new array with 1 available space.<br>
In this case, you also have to copy all the existing names into the new array.
You add your second friend's name to the array. Now, you have a full array with two names.<br>
...<br>
When you add the one-hundredth friends name, there is no available space in your array. You have to make a new array with 1 available space.<br>
In this case, you also have to copy all the existing 99 names into the new array.
You add your one-hundredth friend's name to the array. Now, you have a full array with one hundred names.<br>
Two points are important to notice:

Every time you add a new name, you have to make a new array. This step takes constant time. It's not a huge problem. The new array's size is oldArray.length + 1.<br>
Every time you add a new name, you have to copy all the existing names into the new array. This takes O(n) time.<br>
Both problems can be solved with a clever design.<br>

The main idea of the improved design is this:

When you make a new array, you will certainly need to copy all the existing elements into the new array. This cannot be avoided.<br>
However, you don't need to make a new array every time.<br>
When you make a new array, don't simply add one (oldArray.length + 1) to the new array. Make a new array that is twice the size of the oldArray (2 * oldArray.length).<br>
Until the array fills up again, you won't need to make a new array or copy the existing elements into a new array.<br>
The same idea can be applied to shrinking the array size.

![](media/14843676001195.jpg)


Video: https://www.youtube.com/watch?v=uso8zq5ZtzQ


