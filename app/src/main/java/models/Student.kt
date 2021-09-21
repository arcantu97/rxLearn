package models

data class Student(val name: String, val age: Int, val grade: Int, val isActive: Boolean){
    companion object Students {
        val studenList = arrayOf(
            Student("Sophia", 24, 4, true),
            Student("Kristen", 20, 2, false),
            Student("Sam", 23, 3, true)
        )
    }
}
