package com.example.myapplication.ui.theme

import androidx.compose.ui.text.toUpperCase


fun main(args: Array<String>) {
   var b = mutableMapOf<String,String>("dfsdsafa" to "fasdf","sdfa"  to "safd" ,"wer"  to "dsaf")
    var a = mutableSetOf<String>("dfsa" )
    var callback  = { a: Int, b: String -> String
        "Abhishek Tripathi"
    }
   // var listsec = mutableListOf<String>("qaz", "dfd", "dsaf")
    var listFirst = mutableListOf(mutableListOf<Person>(
        Person(1, "www"),
        Person(2, "zzz"),
        Person(3, "rrr"),
        Person(1, "eee"),
        Person(4, "wwww"))
    )


}


data class Person(var id: Int, var name: String)

