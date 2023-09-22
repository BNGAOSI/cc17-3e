fun main() {
    val border = "==%%"
    val timesToRepeat = 5
    val name = "Bien"
    val age = 20
    val year = 365
    val dayage = age * year
    
    printBorder(border, timesToRepeat)
    print("Happy Birthday! ${name}\n")
    printBorder(border, timesToRepeat)
    print("I am ${age }!\n")
    print("${age } is the best age to learn Kotlin!\n")
    print("I am ${dayage} days old\n")
    print("${name} is already ${dayage} days old\n")
    
    //let's print a cake!
    println("   ,,,,,   ")
    println("   |||||   ")
    println(" =========")
    println("@@@@@@@@@@@")
    println("{~@~@~@~@~}")
    println("@@@@@@@@@@@")
    println("")
    
    
    }
	fun printBorder(border:String,timesToRepeat:Int){
        
        repeat(timesToRepeat){
            print(border)
        }
        println()
}
