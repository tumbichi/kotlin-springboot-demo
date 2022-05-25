package com.pity.restapi.model

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "persona")
data class Persona(val dni:Long = 0,
                   val nombre:String = "",
                   val apellido:String = "",
                   val fechaNacimiento:LocalDate? = null,
                   val alert:Boolean = false) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long = 0;


}