package com.idz.colman24class1.model

class Model private constructor() {

    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    init {
        for (i in 0..20) {
            val student = Student(
                name = "Ben Shapiro $i",
                id = i.toString(),
                avatarUrl = "",
                address = "Har Adar",
                phoneNumber = "0525381648",
                isChecked = false
            )
            students.add(student)
        }
    }
}