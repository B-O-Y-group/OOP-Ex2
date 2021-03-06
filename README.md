# OOP-Ex2

This project represents a number of algorithms that can be done on a directed graph

![img_11.png](img_11.png)


1. Graph data structure :
    hash map of two hash maps:
    the first key of hashmap --> represent the id of each node by his key,
    the value --> represent another hashmap of the key --> represent nodes' neighbors
    the value of them --> represent an array of edges by src and dest.

2. A brief overview of the algorithms that can be done on the directed graph  :
   - isConnected:
     This algorithm checks whether each vertex is connected in a graph
     with one or more edges to the other vertices in the graph
     (work with BFS algorithm).

     
  - shortestPathDist:
    This algorithm checks the shortest distance between two vertices in a graph
    and returns the total weight between those vertices.
  

  - shortestPath:
    This algorithm checks the shortest distance between two vertices in a graph and
    returns the way the vertices pass.

    
  - center: This algorithm checks which is the longest path from each vertex, and then selects the shortest path from the longest


  - tsp: This algorithm goes through all the vertices in the graph and brings the path with the lowest weight in the 
    path on all the vertices
    
    
3. How to use Gui: 

    1. First choose from the menu bar  
       
       ![img_2.png](img_2.png)
       
    2. File --> load ans save
            
        if u choose for load the fallowing will show 
        in this window u need to open a json file of Directed weight graph .
       
       ![img_3.png](img_3.png)
       
       if u choose for a save  the fallowing will  show 
       and here u need to choose for a place u want to save the result .
       
          ![img_4.png](img_4.png)
       



![img_5.png](img_5.png)


3. Algorithm --> isConnected ,  shortestPathDist, shortestPath , center , tsp
    Here u need to choose the algorithm u like to start .  
      ![img_7.png](img_7.png)

    Note that the yellow vertex in the image above is the center of the graph    




Result of 1000 Nodes and 10,000 Nodes :

![img_9.png](img_9.png)
    

![img_10.png](img_10.png)
      
 

Uml:
![img_12.png](img_12.png)


