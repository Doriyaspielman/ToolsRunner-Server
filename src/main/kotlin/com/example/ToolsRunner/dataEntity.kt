package com.example.ToolsRunner

data class User2(val uName:String = "",val uRole: String ="",val uWorkplace:String = "")
class User(uName: String = "UNKNOWN", uRole: String = "UNKNOWN", uWorkplace: String = "UNKNOWN") {
    val name: String = uName
    val role: String = uRole
    val workplace: String = uWorkplace

    override fun toString(): String {
    //return ("name = $name, role = $role, workplace = $workplace")
        return "User [name: ${this.name}, role: ${this.role}, workplace: ${this.workplace}]"
    }
}
