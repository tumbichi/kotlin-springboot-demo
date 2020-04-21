package com.pity.restapi

import com.pity.restapi.dao.PersonaRepository
import com.pity.restapi.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
class RestapiApplication:CommandLineRunner{

	@Autowired
	val personaRepository: PersonaRepository? = null

	override fun run(vararg args: String?) {
		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
		val persona1 = Persona(40222000, "German",
					"Viglietti", LocalDate.parse("07-10-1997", formatter))
		personaRepository!!.save(persona1)
	}
}x

fun main(args: Array<String>) {
	runApplication<RestapiApplication>(*args)
}
