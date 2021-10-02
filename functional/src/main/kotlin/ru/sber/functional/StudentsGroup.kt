package ru.sber.functional

class StudentsGroup {

    var students: List<Student>

    fun filterByPredicate(pred: (Student) -> Boolean): List<Student> = students.filter { pred(it) }

    init {
        students = listOf(
            Student("Дмитрий", "Романов", "Александрочич", averageRate = 7.2),
            Student("Арсений", "Петросян", averageRate = 9.1),
            Student("Полина", "Самсонова", age = 22, averageRate = 9.9),
            Student("София", "Краснова", city = "Тула", averageRate = 6.6),
        )
    }
}
