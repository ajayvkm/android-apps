package com.example.assignment1

import java.io.Serializable

class UserInfo() : Serializable {

    var name : String = ""
    var age: String = ""
    var origin: Int = 0
    var os: Int = 0
    override fun toString(): String {
        return "UserInfo(name='$name', age='$age', origin=$origin, os=$os)"
    }
    /*override fun toString(): String {
        return this.name + " | " + this.age + "|" + origin.toString() + "|" + os.toString();
    }*/
}
