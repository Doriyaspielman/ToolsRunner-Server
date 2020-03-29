package com.example.ToolsRunner

import com.google.gson.Gson
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.core.Ordered
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.io.File
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.servlet.Filter
//import kotlinx.serialization.*
//import kotlinx.serialization.json.JSON


@RestController
class HelloWorldController {

    @GetMapping("/execute")
    fun greet(): String{
        //val info = executeScript("python theHarvester.py -d tufin.com -b linkedin")
        val info = executeScript("python theHarvester.py -d tufin.com -b linkedin")
        val object_data = convertResultsTextToJSON(info)

        return object_data
    }

    @Bean
    fun simpleCorsFilter(): FilterRegistrationBean<*> {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        // *** URL below needs to match the Vue client URL and port ***
        config.allowedOrigins = Collections.singletonList("http://localhost:8080")
        config.allowedMethods = Collections.singletonList("*")
        config.allowedHeaders = Collections.singletonList("*")
        source.registerCorsConfiguration("/**", config)
        val bean = FilterRegistrationBean<Filter>(CorsFilter(source))
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE)
        return bean
    }

    fun executeScript (command:String) : String{
        val path = File("C:/Users/Doriya Spielman/Desktop/ToolsRunner/src/main/kotlin/com/example/ToolsRunner/theHarvester")
        val content = command.runCommand(path)
        return content

    }

    fun String.runCommand(
            workingDir: File = File("."),
            timeoutAmount: Long = 60,
            timeoutUnit: TimeUnit = TimeUnit.SECONDS
    ): String = try {
        val proc = ProcessBuilder(split("\\s".toRegex()))
                .directory(workingDir)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start().apply { waitFor(timeoutAmount, timeoutUnit) }
                .inputStream


        proc.bufferedReader().use { it.readText()}
    } catch (e: IOException) {
        e.printStackTrace()
        "null"
    }


    fun convertResultsTextToJSON(raw_data: String): String{
        val allData : MutableList<String> = mutableListOf()
        val users: MutableList<User> = mutableListOf()
        val reg =Regex("[a-zA-Z +]+ - [a-zA-Z +]+ - [a-zA-Z +]+")
        val ans : Sequence<MatchResult> = reg.findAll(raw_data, 0)
        var u = User()
        ans.forEach()
        {
            matchResult -> allData.add(matchResult.value)
        }
        allData.forEach(){
            val a = it.split(" - ")
             u = User(a[0],a[1],a[2])
            users.add(u)
        }
        val gson = Gson()

        return gson.toJson(users)
    }



}