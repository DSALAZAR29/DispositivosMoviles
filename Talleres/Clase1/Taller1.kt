// ---------- EJERCICIO 1: Conversor de Temperatura ----------
fun ejercicio1_Temperatura() {
    val celsius = 25.0
    val fahrenheit = (celsius * 9 / 5) + 32
    println("$celsius°C equivale a $fahrenheit°F")
}

// ---------- EJERCICIO 2: Calculadora de IMC ----------
fun ejercicio2_IMC() {
    val peso = 70.0
    val altura = 1.75
    val imc = peso / (altura * altura)

    val clasificacion = when {
        imc < 18.5 -> "Bajo peso"
        imc < 25.0 -> "Peso normal"
        imc < 30.0 -> "Sobrepeso"
        else -> "Obesidad"
    }

    println("Peso: ${peso}kg, Altura: ${altura}m")
    println("IMC: %.2f".format(imc))
    println("Clasificación: $clasificacion")
}

// ---------- EJERCICIO 3: Número Par o Impar ----------
fun ejercicio3_ParImpar() {
    val numero = 42
    val resultado = if (numero % 2 == 0) "Par" else "Impar"
    println("El número $numero es: $resultado")
}

// ---------- EJERCICIO 4: Números Primos ----------
fun ejercicio4_NumeroPrimo() {
    val numero = 17
    var esPrimo = true

    if (numero <= 1) {
        esPrimo = false
    } else {
        for (i in 2 until numero) {
            if (numero % i == 0) {
                esPrimo = false
            }
        }
    }

    if (esPrimo) {
        println("El número $numero ES primo")
    } else {
        println("El número $numero NO es primo")
    }
}

// ---------- EJERCICIO 5: Suma de Números Pares ----------
fun ejercicio5_SumaPares() {
    var suma = 0

    for (i in 1..100) {
        if (i % 2 == 0) {
            suma += i
        }
    }

    println("La suma de números pares del 1 al 100 es: $suma")
}

// ---------- EJERCICIO 6: Factorial ----------
fun ejercicio6_Factorial() {
    val numero = 5
    var factorial: Long = 1

    for (i in 1..numero) {
        factorial *= i
    }

    println("El factorial de $numero es: $factorial")
}

// ---------- EJERCICIO 7: Palíndromo ----------
fun ejercicio7_Palindromo() {
    val palabra = "anilina"
    val esPalindromo = palabra == palabra.reversed()
    val resultado = if (esPalindromo) "ES un palíndromo" else "NO es un palíndromo"

    println("La palabra '$palabra' $resultado")
}

// ---------- FUNCIÓN MAIN ----------
fun main() {
    println("=".repeat(50))
    println("TALLER 1: KOTLIN FUNDAMENTALS")
    println("Nombre: Daniel Salazar Castañeda")
    println("=".repeat(50))
    println()

    println("--- Ejercicio 1: Conversor de Temperatura ---")
    ejercicio1_Temperatura()
    println()

    println("--- Ejercicio 2: Calculadora de IMC ---")
    ejercicio2_IMC()
    println()

    println("--- Ejercicio 3: Número Par o Impar ---")
    ejercicio3_ParImpar()
    println()

    println("--- Ejercicio 4: Números Primos ---")
    ejercicio4_NumeroPrimo()
    println()

    println("--- Ejercicio 5: Suma de Números Pares ---")
    ejercicio5_SumaPares()
    println()

    println("--- Ejercicio 6: Factorial ---")
    ejercicio6_Factorial()
    println()

    println("--- Ejercicio 7: Palíndromo ---")
    ejercicio7_Palindromo()
    println()

    println("=".repeat(50))
    println("FIN DEL TALLER")
    println("=".repeat(50))
}
