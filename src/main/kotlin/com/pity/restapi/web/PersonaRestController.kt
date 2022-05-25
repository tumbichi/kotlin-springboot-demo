package com.pity.restapi.web

import com.pity.restapi.business.IPersonaBusiness
import com.pity.restapi.exception.BusinessException
import com.pity.restapi.exception.NotFoundException
import com.pity.restapi.model.Persona
import com.pity.restapi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PERSONAS)
class PersonaRestController {
    @Autowired
    val personaBusiness: IPersonaBusiness? = null

    @CrossOrigin(origins = ["http://localhost:3000", "http://18.223.21.235:3000"])
    @GetMapping("")
    fun list():ResponseEntity<List<Persona>>  {
        return try {
            ResponseEntity(personaBusiness!!.list(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @CrossOrigin(origins = ["http://localhost:3000", "http://18.223.21.235:3000"])
    @GetMapping("/{id}")
    fun load(@PathVariable("id") idPersona:Long): ResponseEntity<Persona>{
        return try {
            ResponseEntity(personaBusiness!!.load(idPersona), HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @CrossOrigin(origins = ["http://localhost:3000", "http://18.223.21.235:3000"])
    @PostMapping("")
    fun insert(@RequestBody persona: Persona): ResponseEntity<Any>{
        return try {
            personaBusiness!!.save(persona)
            val resonseHeader = HttpHeaders()
            resonseHeader.set("location", Constants.URL_BASE_PERSONAS + "/" + persona.id)
            ResponseEntity(resonseHeader, HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @CrossOrigin(origins = ["http://localhost:3000", "http://18.223.21.235:3000"])
    @PutMapping("")
    fun update(@RequestBody persona: Persona): ResponseEntity<Any>{
        return try{
            personaBusiness!!.save(persona)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @CrossOrigin(origins = ["http://localhost:3000", "http://18.223.21.235:3000"])
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idPersona: Long): ResponseEntity<Any>{
        return try {
            personaBusiness!!.remove(idPersona)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}