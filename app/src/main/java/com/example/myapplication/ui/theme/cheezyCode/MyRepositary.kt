package com.example.myapplication.ui.theme.cheezyCode

import javax.inject.Inject

interface MyRepositary
{
    fun showData(a: String)
}
class MyLocalRepo @Inject constructor(val loggerService: LoggerService) : MyRepositary{
    val  Tag = "MyRepositary"
    override fun showData(a: String) {
        loggerService.log("MyLocal " +a)
    }
}
class FireBaseRepo @Inject constructor(val loggerService: LoggerService) : MyRepositary{
    val  Tag = "MyRepositary"
    override fun showData(a: String) {
        loggerService.log("My Firebase$a")
    }

}
