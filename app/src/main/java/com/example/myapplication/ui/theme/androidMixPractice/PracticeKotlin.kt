package com.example.myapplication.ui.theme.androidMixPractice


class Abc{

    var list = arrayListOf<Persons>(Persons("abc", 1), Persons("dsfa", 2))
    fun printData()
    {
        var str = ""
        var a = with(str){
            3
        }



        list.associate {
            Pair( "my age ${it.age}", it)
        }
    }


}


data class Persons(var name: String, var age: Int)





fun abc()
{
    /*var session = Abc()
    session.name = ""*/
}