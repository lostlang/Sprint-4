package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Отчество не указано",
    val age: Int = 18,
    val averageRate: Double,
    val city: String = "Город не указан",
    val specialization: String = "Специализация не указана",
    val prevEducation: String = "Предыдущее учебное заведение не указано",
)
