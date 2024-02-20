package com.example.myapplication.ui.theme.kotlinCollection

import com.example.myapplication.ui.theme.androidMixPractice.Book
import com.example.myapplication.ui.theme.androidMixPractice.Person


fun main(args: Array<String>) {

    var dataList = mutableListOf(Person(1, "Abhishek"),
        Person(2, "Vikash"),
        null,
        Person(3, "Shivam"),
        Person(4, "Himanshu"),
        Person(5, "Rizwan"),
        Person(2, "Shobith"),
        Person(3, "Agam"),
        null,
        Book(1, "Let Us C"),
        )


    var dataListNew = mutableListOf<Any>()


    var dataSet = mutableSetOf(
        Person(1, "Abhishek"),
        Person(2, "Vikash"),
        Person(3, "Shivam"),
        Person(4, "Himanshu"),
        Person(5, "Rizwan"),
        Person(5, "Rizwan"),)

    var dataMap = mutableMapOf<String, Person>("A" to Person(1, "Abhishek"),
        "B" to  Person(2, "Vikash"),
        "C" to    Person(3, "Shivam"),
        "D" to   Person(4, "Himanshu"),
        "E" to   Person(5, "Rizwan"),
        "F" to    Person(5, "Rizwan"))

    var dataMapDummy  = mutableMapOf<String, Person>()

    var mapdata  = dataList.groupingBy { when(it) {
        is Person ->{
            it.id
        }
        is Book ->
        {
            it.bookName
        }

        else -> {}
    } }
    dataList.associate {
        Pair( "my age ${(it as? Person)?.id}", it)
    }

      println(mapdata.eachCount())
        associateExample()
}


fun associateExample(){
    var dataList = mutableListOf(Person(1, "Abhishek"),
        Person(2, "Vikash"),
        Person(3, "Shivam"),
        Person(4, "Himanshu"),
        Person(5, "Rizwan"),
        Person(2, "Shobith"),
        Person(3, "Agam"),
    )

    var dataListNew = mutableListOf<Any>()


    println(dataList.associateBy {"${it.name} id ${it.id}"})
}

