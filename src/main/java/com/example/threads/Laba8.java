package com.example.threads;

import reactor.core.publisher.Mono;

//Реактивный поток
public class Laba8 {
    public static void main(String[] args){
        int number = 66;
        /*
        Создаем реактивный поток Mono (который может испускать только один элемент).
        Метод map преобразует объекты, лежащие в потоке. Преобразование задается переданной
        в качестве аргумента функцией
         */
        Mono <Integer> result_number = toMono(number).map(Laba8::square).map(Laba8::minusSumOfDigits);
        //Подписываемся на испускаемые потоком объекты и выводим их в консоль
        result_number.subscribe(System.out::println);
    }

    //Функции проихводящие различные манипуляции с числом
    public static int square(int number){
        return number * number;
    }

    public static int minusSumOfDigits(int number){
        int sum = 0;
        int number_buff = number;
        while (number_buff != 0){
            sum += number_buff % 10;
            number_buff /= 10;
        }
        return number - sum;
    }

    //Функция отвечающая за создание реактивного потока
    public static Mono<Integer> toMono(int number){
        return Mono.just(number);
    }
}
