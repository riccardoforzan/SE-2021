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

### Creating a class

```kotlin
class Aquarium {
    var width: Int = 20
    var height: Int = 40
    var length: Int = 100
  
    fun printSize() {
        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm ")
    }
}

fun main() {
	var my_acquarium = Aquarium()
    my_acquarium.printSize()
    
    my_acquarium.width = 1000
    my_acquarium.printSize()
    
}
```

If you declared these properties with `val` instead of `var`, the properties would be immutable. 

**You could only set them once, and all the instances of `Aquarium` would have the same dimensions.**

___

### Declaring a constructor

This code snippet does the same output of the one above, but it uses the constructor of the class Aquarium, declared on the firm of the class.

Declaration of the fields of the class (like in Java) can be skipped.

In the constructor you can define standard value to give to parameters, these value are used if a value for the filed is not given by the calling function.

```kotlin
class Aquarium(var length: Int = 100, var width: Int = 20, var height: Int = 40) {
  	
  	init {
        println("aquarium initializing")
    }
  
    fun printSize() {
        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm ")
    }
}

fun main() {
	var my_acquarium1 = Aquarium()
    my_acquarium1.printSize()
    
	var my_acquarium2 = Aquarium(width=1000)
    my_acquarium2.printSize()
    
}
```

The `init` function is a function called when an instance of the class is being created.

Parameters of the primary constructor can be used in the initializer blocks. Any properties used in initializer blocks must be declared previously.



In addition to a primary constructor, which can have one or more `init` blocks, a Kotlin class can also have one or more secondary constructors to allow constructor overloading, that is, constructors with different arguments.

Kotlin coding style says each class should have only one constructor for testing purposes, if you have multiple constructors one or more paths will go untested.

Every secondary constructor must call the primary constructor first, either directly using `this()`, or indirectly by calling another secondary constructor.

If you need multiple constructos consider a **factory function**.

```kotlin
class Aquarium(var length: Int = 100, var width: Int = 20, var height: Int = 40) {
  
 		// Adding a second constructor
    constructor(numberOfFish: Int) : this() {
        // 2,000 cm^3 per fish + extra room so water doesn't spill
        val tank = numberOfFish * 2000 * 1.1
        height = (tank / (length * width)).toInt()
    }
    
    fun printSize() {
        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm ")
    }
}

fun main() {
	var my_acquarium1 = Aquarium()
    my_acquarium1.printSize()
    
	var my_acquarium2 = Aquarium(width=1000)
    my_acquarium2.printSize()
    
    
    val my_acquarium3 = Aquarium(numberOfFish = 29)
    my_acquarium3.printSize()
    println("Volume: ${my_acquarium3.width * my_acquarium3.length * my_acquarium3.height / 1000} l")
    
}
```

___

### Add a new property getter and setter

Kotlin automatically defines getters and setters when you define properties, but sometimes the value for a property needs to be adjusted or calculated.

```kotlin
class Aquarium(var length: Int = 100, var width: Int = 20, var height: Int = 40) {
    
    var volume: Int
    	get() = width * height * length / 1000  // 1000 cm^3 = 1 l
 			set(value) {
        height = (value * 1000) / (width * length)
    	}
  
    constructor(numberOfFish: Int) : this() {
        // 2,000 cm^3 per fish + extra room so water doesn't spill
        val tank = numberOfFish * 2000 * 1.1
        height = (tank / (length * width)).toInt()
    }
    
    fun printSize() {
    		println("width: $width cm , length: $length cm , height: $height cm ")
        println("Volume: $volume l")
    }
}

fun main() {
	var my_acquarium1 = Aquarium()
  my_acquarium1.printSize()
    
	var my_acquarium2 = Aquarium(width=1000)
  my_acquarium2.printSize()
    
  val my_acquarium3 = Aquarium(numberOfFish = 29)
  my_acquarium3.printSize()
  
  val my_acquarium4 = Aquarium()
  my_acquarium4.printSize()
  my_acquarium4.volume = 50
  my_acquarium4.printSize()
}
```

**Remember:** A `val` property can't have a setter!

___

### Visibility modifiers

By default in Kotlin, like in Java, all members of the class are public.

There are 4 visibility modifiers in Kotlin:

- `public` means visible outside the class. Everything is public by default, including variables and methods of the class.
- `internal` means it will only be visible within that module. A [module](https://kotlinlang.org/docs/reference/visibility-modifiers.html#modules) is a set of Kotlin files compiled together, for example, a library or application.
- `private` means it will only be visible in that class (or source file if you are working with functions).
- `protected` is the same as `private`, but it will also be visible to any subclasses.

You can declare getter or setter of a propeerty private, for example if you want that other modules can read it's value but not change it, you can make the setter private, like:

```kotlin
var volume: Int
	get() = width * height * length / 1000
	private set(value) {
		height = (value * 1000) / (width * length)
	}
```

___

### Subclasses and inheritance

In Kotlin, by default, classes cannot be subclassed, properties and member variables cannot be overridden by subclasses (though they can be accessed).

You must mark a class as `open` to allow it to be subclassed. The `open` keyword is required, to prevent accidentally leaking implementation details as part of the class's interface.

```kotlin
import java.lang.Math.PI

//Master class
open class Aquarium (open var length: Int = 100, open var width: Int = 20,
                     open var height: Int = 40) {
  
    open var volume: Int
  			get() = width * height * length / 1000
        set(value) {
            height = (value * 1000) / (width * length)
        }
  	
  	open val shape = "rectangle"
  
  	open var water: Double = 0.0
        get() = volume * 0.9
  	
  	fun printSize() {
    	println(shape)
    	println("width: $width cm , length: $length cm , height: $height cm ")
    	// 1 l = 1000 cm^3
    	println("Volume: $volume l Water: $water l (${water/volume*100.0}% full)")
    }
}

//Derivative class
class TowerTank (override var height: Int, var diameter: Int): 
								Aquarium(height = height, width = diameter, length = diameter) {
    
  override var volume: Int
  // ellipse area = π * r1 * r2
  get() = (width/2 * length/2 * height / 1000 * PI).toInt()
  set(value) {
      height = ((value * 1000 / PI) / (width/2 * length/2)).toInt()
  }
  
  override var water = volume * 0.8
  
  override val shape = "cylinder"
  
}
                  
fun main(){
    val my_aquarium = Aquarium(length = 25, width = 25, height = 40)
    my_aquarium.printSize()
    val my_tower = TowerTank(diameter = 25, height = 40)
    my_tower.printSize()
}
```

___

### Abstract classes and interfaces

Sometimes you want to define common behavior or properties to be shared among some related classes.

Kotlin offers two ways to do that, interfaces and abstract classes.

- Neither an abstract class nor an interface can be instantiated on its own, which means you cannot create objects of those types directly.
- Abstract classes have constructors.
- Interfaces can't have any constructor logic or store any state.

In this sense it's 100% like Java.

#### Abstract class example

```kotlin
abstract class AquariumFish {
    abstract val color: String
}

class Shark: AquariumFish() {
    override val color = "gray"
}

class Plecostomus: AquariumFish() {
    override val color = "gold"
}

fun makeFish() {
    val shark = Shark()
    val pleco = Plecostomus()

    println("Shark: ${shark.color}")
    println("Plecostomus: ${pleco.color}")
}

fun main () {
    makeFish()
}
```

![A diagram showing the abstract class, AquariumFish, and two subclasses, Shark and Plecostumus.](https://developer.android.com/codelabs/kotlin-bootcamp-classes/img/209b37c55b04ddb7.png)

#### Interface example

```kotlin
interface FishAction  {
    fun eat()
}

class Shark: FishAction {
    override fun eat() {
        println("hunt and eat fish")
    }
}

class Plecostomus: FishAction {
    override fun eat() {
        println("eat algae")
    }
}

fun makeFish() {
    val shark = Shark()
    val pleco = Plecostomus()
    shark.eat()
    pleco.eat()
}

fun main () {
    makeFish()
}
```

![e4b747e0303bcdaa.png](https://developer.android.com/codelabs/kotlin-bootcamp-classes/img/e4b747e0303bcdaa.png)

#### When to use abstract classes versus interfaces

Abstract classes and interfaces can help you keep your design cleaner, more organized, and easier to maintain. As noted above, abstract classes can have constructors, and interfaces cannot, but otherwise they are very similar. 

When you use interfaces to compose a class, the class's functionality is extended by way of the class instances that it contains. Composition tends to make code easier to reuse and reason about than inheritance from an abstract class. Also, you can use multiple interfaces in a class, but you can only subclass from one abstract class.

In general:

* Use an interface if you have a lot of methods and one or two default implementations
* Use an abstract class any time you can't complete a class

___

### Interface delegation

*Interface delegation* is an advanced technique where the methods of an interface are implemented by a helper (or delegate) object.

This technique can be useful when you use an interface in a series of unrelated classes: you add the needed interface functionality to a separate helper class, and each of the classes uses an instance of the helper class to implement the functionality.

Kotlin lets you declare a class where you can **only create one instance of it** by using the keyword `object` instead of `class`

```kotlin
object GoldColor : FishColor {
   override val color = "gold"
}
```

<a href=https://developer.android.com/codelabs/kotlin-bootcamp-classes/#7>Documentation</a>

___

### Data class

A data class is similar to a `struct` in some other languages—it exists mainly to hold some data—but a data class object is still an object. Kotlin data class objects have some extra benefits, such as utilities for printing and copying. This behaviour is like Java.

**Notes:**

* In Kotlin, using `==` on data class objects is the same as using `equals()` (structural equality). If you need to check whether two variables refer to the same object (referential equality), use the `===` operator.
* Data class objects are objects. Assigning a data class object to another variable copies the reference to that object, not the contents. To copy the contents to a new object, use the `copy()` method.
* The `copy()`, `equals()`, and other data class utilities only reference properties defined in the primary constructor.

Another function that Kotlin offers (java does not) is the **deconstructing function**.

As example, if my_object is an object which has 3 fields (or properties), you can deconstruct the object and inspect its public fields.

```kotlin
val (property1, property2, property3) = my_object
```

If you don't need one or more of the properties, you can skip them by using `_` instead of a variable name, as shown in the code below.

```kotlin
val (property1, _, property3) = my_object
```

<a href=https://developer.android.com/codelabs/kotlin-bootcamp-classes/#8>Documentation</a>

___

### Enums

Kotlin also supports enums, which allow you to enumerate something and refer to it by name, much like in other languages.

You can also get the ordinal value of an enum using the `ordinal` property, and its name using the `name` property.

```kotlin
enum class Color(val rgb: Int) {
   RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);
}

fun main(){
    println("Position: ${Color.RED.ordinal},Color: ${Color.RED.name}, RGB:${Color.RED.rgb}")
    println("Position: ${Color.GREEN.ordinal},Color: ${Color.GREEN.name}, RGB:${Color.GREEN.rgb}")
    println("Position: ${Color.BLUE.ordinal},Color: ${Color.BLUE.name}, RGB:${Color.BLUE.rgb}")
}
```

### Sealed classes

A *sealed class* is a class that can be subclassed, but only inside the file in which it's declared. If you try to subclass the class in a different file, you get an error.

The `Seal` class can't be subclassed in another file. If you want to add more `Seal` types, you have to add them in the same file. This makes sealed classes a safe way to represent a fixed number of types. For example, sealed classes are great for [returning success or error from a network API](https://articles.caster.io/android/handling-optional-errors-using-kotlin-sealed-classes/).

```kotlin
sealed class Seal
class SeaLion : Seal()
class Walrus : Seal()

fun matchSeal(seal: Seal): String {
   return when(seal) {
       is Walrus -> "walrus"
       is SeaLion -> "sea lion"
   }
}
```

___

