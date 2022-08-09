# Robot Evolution: Genetic Testing with Robots
Project written with IntelliJ IDE, run using JavaFX Libs

Based on the C++ final I took in CISP400 with Caleb Fowler at Folsom Lake College, CA.

## Problem
You are going to reproduce an experiment first conducted at Harvard University in 1968.
This program studies the effects of evolution on a population of robots.
The robots need to maneuver around a grid collecting energy. The robots
must collect more energy than they expend to survive. Your robots will maneuver around
a 10 x 10 grid looking for batteries. A battery gives the robot give more power.
Moving a square costs 1 power. The sensors are always on and cost no power. Robots have
five power when they first emerge on the map.

Each Robot has a collection of direction sensors and a motor. Robot success depends upon
the map between these two elements. The robots start with random mapping, but over time, the mappings
evolve into successful strategies. 