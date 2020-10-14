# SPBU-Homework-3
Here you can find conditions and solutions to problems for the third semester

## Navigation menu
* [Semester №1](https://github.com/GirZ0n/SPBU-Homework-1) 
* [Semester №2](https://github.com/GirZ0n/SPBU-Homework-2) 
* Semester №3 :arrow_left: **You are here**
* Semester №4

## Homework

### Homework №1
1. Смоделировать работу локальной сети: <br/>
a) в сети есть несколько компьютеров, связанных друг с другом (каким образом - можно задавать, например, матрицей смежности); <br/>
b) на каждом компе стоит ОС (Windows, Linux, etc...); <br/>
c) в сети гуляют вирусы, так что для каждой машины есть ненулевая вероятность заразиться (вероятность зависит от типа ОС), заражаются компьютеры, непосредственно соединённые с заражёнными; <br/>
d) заражения (и проверки, заразился компьютер или нет) происходят дискретно - по ходам. <br/>
Требуется периодически выводить состояние сети. Нужны тесты, проверяющие правильность процесса заражения. [[Solution]](https://github.com/GirZ0n/SPBU-Homework-3/tree/master/src/main/kotlin/homework/homework1/task1)

### Homework №2
1. Нарисовать диаграмму классов для игры "реверси", с несколькими видами "ботов", которые могли бы играть вместо человека. Диаграмма должна быть довольно подробным проектом системы. Реализовывать этот проект не нужно. [[Solution]](https://github.com/GirZ0n/SPBU-Homework-3/blob/master/src/main/resources/kotlin/homework/homework2/task1/task1.svg)

2. Нарисовать диаграмму состояний для класса `MicrowaveOven`, реализующего ПО СВЧ-печи. [[Solution]](https://github.com/GirZ0n/SPBU-Homework-3/blob/master/src/main/resources/kotlin/homework/homework2/task2/task2.svg)

### Homework №3
1. Есть автостоянка с ограниченным количеством мест, на стоянку могут заезжать машины и со стоянки могут уезжать машины. Надо реализовать класс, который бы сообщал машине при въезде, есть ли свободные места, и регистрировал уезжающие машины. На парковку есть несколько въездов, на каждом из которых планируется поставить такой автомат и сделать так, чтобы автоматы синхронизировались друг с другом. Реализация должна быть lock-free. [[Solution]](https://github.com/GirZ0n/SPBU-Homework-3/tree/master/src/main/kotlin/homework/homework1/task3)

### Homework №4
1. Реализовать Android-приложение "Калькулятор". Пользователь должен вводить выражение, которое состоит из вещественных чисел, операций "+-*/" и скобочек. По нажатию на равно должно показываться значение выражения. [[Solution]](https://github.com/GirZ0n/SPBU-Homework-3-AndroidCalculator)
