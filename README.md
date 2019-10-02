# RxJava Codelabs: Basic + Advanced

[![Build Status](https://travis-ci.org/jraska/RxJava-Codelab.svg?branch=master)](https://travis-ci.org/jraska/RxJava-Codelab)

## Introduction
Reactive programming is mentioned everywhere these days. It solves many common issues of imperative programming
in an elegant way. Dozens of libraries are springing up, giving developers access to powerful tools that make their everyday development life easier. However, it can take time to learn how to wield these tools effectively as they are often difficult to understand and if used improperly they can sometimes do more harm than good.

These codelabs will first go through RxJava basics, in order to demonstrate the main concepts and common use-cases of the ReactiveX framework, as well as reactive programming in general. It should make RxJava and reactive programming much clearer to you and give you lots of ideas of when and where you might want to use it. Once you have seen what it can do, you won't want to go back to how you were programming before!

The advanced codelab will take you more into deep of reactive world, we will go into detail and we will discover more of its beauty, because its endless.


## Prerequisites
 0. Java 8 SDK
 0. IntelliJ (any edition) or any other IDE you like


## Project setup
 0. Clone this repo (GitHub Desktop, SourceTree or `git clone https://github.com/jraska/RxJava-Codelab.git`)
 0. Open it in IntelliJ or other IDE
 0. Try to run `SetupTest` from your IDE
 0. Call for help if it doesn't work


## RxJava Basics Codelab covers ([slides](https://docs.google.com/presentation/d/1W7AZm5t1PRIxttFtxROPV4wowUn6gh7lNsp_y0aNvgs/edit?usp=sharing))
 - What is a stream? Observable contract
 - How to create Observables
 - Transformations
 - Composing
 - Error handling
 - Threading - Schedulers


## RxJava Advanced Codelab covers ([slides](https://docs.google.com/presentation/d/1jxQA4uN61aZAmvnsw10-wTVD_f1rcRP2zIhzPNC7NYE/edit?usp=sharing))
 - Observable feat. Single, Maybe, Completable
 - Hot/Cold Observables
 - Testing
 - Use cases - cache/refresh, polling, caching, ...
 - Backpressure - Observable vs. Flowable
 - Other reactive libraries interoperability

## How to use the Code Lab
- There are tasks for different areas formed as unit tests and having a format `Task{Order}{AreaOfFocus}` (e.g. `Task2Transformations`).
- Each of these tasks has a method with a `TODO` explaining what to do and giving hint which operator might be useful. Very often there are multiple solutions to given problem.
- Each Task has a solution with format `SolutionTask{Order}{AreaOfFocus}` (e.g. `SolutionTask2Transformations`).
- Recommended order is doing the tasks by order of their numbers, however jumping based on area of interest is encouraged as well.

## Links
 - ReactiveX http://reactivex.io/intro.html I consider understanding the intro part the most essential thing to understand Rx.
 - RxJava https://github.com/ReactiveX/RxJava
 - All other relevant resources are linked on [ReactiveX website](http://reactivex.io/tutorials.html).
