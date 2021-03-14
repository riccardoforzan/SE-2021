# Kotlin bootcamp notes

Notes of the first 6 lessons of "Programmazione di Sistemi Embedded", following the Google Android Bootcamp for Kotlin 

https://developer.android.com/courses/kotlin-bootcamp/overview



## Bootcamp 2 - Basis

### Basic types

Kotlin supports two types of variables: changeable and unchangeable. 

With  `val`, you can assign a value once. If you try to assign something again, you get an error. 

With `var`, you can assign a value, then change the value later in the program.



The type you store in a variable is inferred when the compiler can figure it out from context. If you want, you can always specify the type of a variable explicitly, using the colon notation

```kotlin
var fish: Int = 12
var lakes: Double = 2.5
```

___

### String

For string manipulation in kotlin use " (double quotes), instead for single characters use ' (single quote)

```kotlin
val numberOfFish = 5
val numberOfPlants = 12

"I have $numberOfFish fish" + " and $numberOfPlants plants" 
// or equivalent usnig ${} to evaluate what is between brackets 
"I have ${numberOfFish + numberOfPlants} fish and plants"
```

A better example of the power of the string template is given at the lesson 3 on task 3:

```kotlin
val temperature = 10
val message = "The water temperature is ${ if (temperature > 50) "too warm" else "OK" }."
println(message)
```

In this code snippet the expression `if (temperature > 50)` is evaluated inside the string builder.

___

### Operators

Kotlin posssess the operator ```in``` that returns true if the value is in the given range (in this case 1..100)

```kotlin
val fish = 50
if (fish in 1..100) {
    println(fish)
}
```



Kotlin posses the operator ```when``` that operates similar to a **switch** case of other programming languages, for example these two code snippets does the same output

```kotlin
if (numberOfFish == 0) {
    println("Empty tank")
} else if (numberOfFish < 40) {
    println("Got fish!")
} else {
    println("That's a lot of fish!")
} 
```

```kotlin
when (numberOfFish) {
    0  -> println("Empty tank")
    in 1..39 -> println("Got fish!")
    else -> println("That's a lot of fish!")
}
```



Another use of `when` is given on the 3 Bootcamp:

use a `when` expression without an argument, which in Kotlin acts as a series of `if/else if` checks.

```kotlin
fun shouldChangeWater (day: String, temperature: Int = 22, dirty: Int = 20): Boolean {
    return when {
        temperature > 30 -> true
        dirty > 30 -> true
        day == "Sunday" ->  true
        else -> false
    }
}
```

___

### Non nullable

In kotlin there are **non nullable variables** (contrary to java)

The statement below is **not allowed**  

```kotlin
var rocks: Int = null
```

In order to declare for a variable the possibility of being null you have to use the operator ```?``` 

```kotlin
var marbles: Int? = null
```

The operator ```?``` can also be used to test if a var (or a val) is **null** for example, those two code snippets are equivalent:

```kotlin
var fishFoodTreats = 6
if (fishFoodTreats != null) {
    fishFoodTreats = fishFoodTreats.dec()
}
```

```kotlin
var fishFoodTreats = 6
fishFoodTreats = fishFoodTreats?.dec()
```

There is also the "Elvis operator"

```kotlin
fishFoodTreats = fishFoodTreats?.dec() ?: 0
```

in this statement, if *fishFoodTreats* is not null then decrement it and use it, otherwise use the value after ?: (Elvis operator) which is 0.

There is a way to disable the **not null assertion** of Kotlin, using the operator ```!!```

```kotlin
val len = s!!.length   // throws NullPointerException if s is null
```

___

### List

Kotlin on this side is similar to Python for the syntax

```kotlin
val school = listOf("mackerel", "trout", "halibut")
println(school)
```

The output of this snippet is ⇒ [mackerel, trout, halibut]

**List declared in this way cannot be changed**

If you want to change the list, you have to declare it as *mutable* like:

```kotlin
val myList = mutableListOf("tuna", "salmon", "shark")
myList.remove("shark")
```

The method  ```remove```, like in Java, returns true if the operation was succesful.



With a list defined with `val`, you can't change which list the variable refers to, but you can still change the contents of the list.

___

### Array

In Kotlin **arrays are immutable**. 

Once you create an array, the size is fixed. You can't add or remove elements, except by copying to a new array.

With an array defined with `val`, you can't change which array the variable refers to, but you can still change the contents of the array.

```kotlin
val school = arrayOf("shark", "salmon", "minnow")
println(java.util.Arrays.toString(school))
```

The output of this snippet is ⇒ `[shark, salmon, minnow]`



An array declared with `arrayOf` doesn't have a type associated with the elements, so you can mix types.

If you want you can create an array of a specific type like:

```kotlin
val numbers = intArrayOf(1,2,3)
```



Arrays can be combined using the operator ```+```, 

```kotlin
val numbers = intArrayOf(1,2,3)
val numbers3 = intArrayOf(4,5,6)
val foo2 = numbers3 + numbers
println(foo2[5])
```

The output in this case will be `2`



But arrays cannot be combined easliy with lists:

```kotlin
val numbers = intArrayOf(1, 2, 3)
val oceans = listOf("Atlantic", "Pacific")
val oddList = listOf(numbers, oceans, "salmon")
println(oddList)
```

The output of this snippet will be like ⇒ `[[I@89178b4, [Atlantic, Pacific], salmon]` where the first element, `numbers`, is an `Array`. When you don't use the array utility to print it, Kotlin prints the address instead of the contents of the array.



In Kotlin you can initialize array with code instead of zero. Like:

```kotlin
val array = Array (5) { it * 2 }
println(java.util.Arrays.toString(array))
```

This code snippet will output `[0, 2, 4, 6, 8]`

___

## Bootcamp 3 - Functions

### Main function

The main function, like in Java, is the function that starts the program. 

The main function can have arguments (String), but is not mandatory to declare those on the firm of the function.

```kotlin
fun printHello() {
    println ("Hello World")
}
```

works as well as

```kotlin
fun main(args: Array<String>) {
    println("Hello, ${args[0]}")
}
```

Parameters given to main works like in Java.

___

### Almost everything has a value

In Kotlin, almost everything is an *expression* and has a value—even if that value is `kotlin.Unit`

For example

```kotlin
// Will assign kotlin.Unit
val isUnit = println("This is an expression")
println(isUnit)
```

The first println will output `This is an expression` and the second one `kotlin.Unit`

___

### Creating some functions

Here below a code snippet to show how to pass values to function and how to declare the returned type of functions.

```kotlin
//This import is required!
import java.util.*

fun randomDay() : String {
    val week = arrayOf ("Monday", "Tuesday", "Wednesday", 
                        "Thursday","Friday", "Saturday", "Sunday")
    return week[Random().nextInt(week.size)]
}

fun fishFood (day : String) : String {
    val food : String				//This can be assigned only 1 time
    when (day) {
        "Monday" -> food = "flakes"
        "Wednesday" -> food = "redworms"
        "Thursday" -> food = "granules"
        "Friday" -> food = "mosquitoes"
        "Sunday" -> food = "plankton"
      	else -> food = "nothing"				//Default case
    }
    return food
}

fun feedTheFish() {
    val day = randomDay()
    val food = fishFood(day)
    println ("Today is $day and the fish eat $food")
}

fun main(args: Array<String>) {
    feedTheFish()
}
```

___

### Default values and compact functions

In the following code snippet, if the calling function does not provide a value for the *swim* funtion, the default one, declared in the firm of the function will be used.

```kotlin
fun swim(speed: String = "fast") {
   println("swimming $speed")
}
```

For example the output of this main function

```kotlin
fun main(){
	swim()   // uses default speed
	swim("slow")   // positional argument
	swim(speed="turtle-like")   // named parameter
}
```

is

```
swimming fast 
swimming slow 
swimming turtle-like
```

**If no default is specified for a parameter, the corresponding argument must always be passed.**

___

### Making compact functions

Functions can be also 1 line long, useful when evaluating an expression like:

```kotlin
fun isTooHot(temperature: Int) = temperature > 30
fun isDirty(dirty: Int) = dirty > 30
fun isSunday(day: String) = day == "Sunday"

fun shouldChangeWater (day: String, temperature: Int = 22, dirty: Int = 20): Boolean {
    return when {
        isTooHot(temperature) -> true
        isDirty(dirty) -> true
        isSunday(day) -> true
        else  -> false
    }
}
```

___

### Filters

```kotlin
fun main() {
	val decorations = listOf ("rock", "pagoda", "plastic plant", "alligator", "flowerpot")
  println( decorations.filter {it[0] == 'p'})
}
```

in the expresison `it[0]=='p'` , `it` refers to each item as the filter loops through. 

If the expression returns `true`, the item is included.



Filters can be **eager** or **lazy**. By default filters are eager.

```kotlin
fun main() {
    val decorations = listOf ("rock", "pagoda", "plastic plant", "alligator", "flowerpot")

    // eager, creates a new list
    val eager = decorations.filter { it [0] == 'p' }
    println("eager: $eager")
  
    // lazy, will wait until asked to evaluate
    val filtered = decorations.asSequence().filter { it[0] == 'p' }
    println("filtered: $filtered")
  
    val newList = filtered.toList()
    println("new list: $newList")
}
```

The output of this code snippet is 

```kotlin
eager: [pagoda, plastic plant]
filtered: kotlin.sequences.FilteringSequence@386cc1c4
new list: [pagoda, plastic plant]
```

To visualize what's going on with the `Sequence` and lazy evaluation, use the `map()` function.

The `map()` function performs a simple transformation on each element in the sequence.

___

### Lambda Functions

A *lambda* is an expression that makes a function. 

But instead of declaring a named function, you declare a function that has no name.

Lambda expression can now be passed as data. 

In other languages, lambdas are called *anonymous functions*

For lambdas, parameters (and their types, if needed) go on the left of what is called a function arrow `->`. 

The code to execute goes to the right of the function arrow. 

Once the lambda is assigned to a variable, you can call it just like a function.

```kotlin
var dirtyLevel = 20
val waterFilter = { dirty : Int -> dirty / 2}
println(waterFilter(dirtyLevel))
```

Another example specifying the return type

```kotlin
val waterFilter: (Int) -> Int = { dirty -> dirty / 2 }
```

Here's what the code says:

- Make a variable called `waterFilter`.
- `waterFilter` can be any function that takes an `Int` and returns an `Int`.
- Assign a lambda to `waterFilter`.
- The lambda returns the value of the argument `dirty` divided by 2.



The real power of lambdas is using them to create higher-order functions, where the argument to one function is another function.

```kotlin
fun updateDirty(dirty: Int, operation: (Int) -> Int): Int {
   return operation(dirty)
}

val waterFilter: (Int) -> Int = { dirty -> dirty / 2 }
println(updateDirty(30, waterFilter))
```

The body of the code calls the function that was passed as the second argument, and passes the first argument along to it.



The function you pass doesn't have to be a lambda; it can be a regular named function instead.

To specify the argument as a regular function use the `::` operator

```kotlin
fun increaseDirty( start: Int ) = start + 1

println(updateDirty(15, ::increaseDirty))
```

___

## Bootcamp 4 - Object Oriented Programming

