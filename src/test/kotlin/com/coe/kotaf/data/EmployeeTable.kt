package com.coe.kotaf.data

import com.coe.kotaf.utils.dbName
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Trim

object EmployeeTable : Table("$dbName.hs_hr_employee") {
    val employeeId = integer("emp_number")
    val employeeFirstName = text("emp_firstname")
    val employeeLastName = text("emp_lastname")
    val employeeMiddleName = text("emp_middle_name")
    val employeeFullName = Trim(Expression.build {
        this.concat(
            separator = " ",
            expr = listOf(
                Expression.build { employeeFirstName },
                Expression.build { employeeMiddleName },
                Expression.build { employeeLastName })
        )
    })
}
