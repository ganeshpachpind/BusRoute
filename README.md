# Bus Route 

[![Build Status](https://travis-ci.org/ganeshpachpind/BusRoute.svg?branch=master)](https://travis-ci.org/ganeshpachpind/BusRoute)


# Assumption :

1. Provided file will be valid file , no validation added as a part of solution 
2. A station id may occur in multiple bus routes, but can never occur twice within the same bus route.
3. Service will get the path to file as the first command line argument, example in my solution case : ```./gradlw clean bootrun -Droutes-file-path="data/example"```
 
  
# Solution:

A cache data structure Map <Integer,List< Integer >> where Key is station Id and value is list of route in which station exist

- Construction of such map will be little expensive operation, but as per problem statement its once a week activity
- Response to query will be fast , in terms of time complexity   
    O(1) - search first station   
    O(1) - search second station  
    O(mLogm + nLogn) - Calculating disjoint of route 
      
# Test Report 

![alt text](https://raw.githubusercontent.com/ganeshpachpind/BusRoute/master/data/test-report.png) 
