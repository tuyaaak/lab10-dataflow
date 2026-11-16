# Lab 10 - Data Flow Testing

**Оюутны нэр:** Туяацэцэг
**Оюутны код:** B210910804

## Ашигласан орчин

**Java:**
openjdk version "25.0.5.0.2" 2026-10-22 LTS
OpenJDK Runtime Environment Microsoft-15804669 (build 25.0.5.0.2+1-LTS)
OpenJDK 64-Bit Server VM Microsoft-15804669 (build 25.0.5.0.2+1-LTS, mixed mode, sharing)

**Maven:**
Apache Maven 3.10.0 (c43a36b8d67be7e0805a411bc0898af1a51f5472)
Maven home: /usr/local/sdkman/candidates/maven/current
Java version: 25.0.5.0.2, vendor: Microsoft, runtime: /usr/local/sdkman/candidates/java/25.0.5.0+2-ms
Default locale: en, platform encoding: UTF-8, time zone: Etc/UTC
OS name: "linux", version: "6.8.0-1070-azure", arch: "amd64", family: "unix"

## A. gcd() функцийн DU шинжилгээ

### 1. Def/Use цэгүүд

gcd() функцийн x, y, tmp хувьсагчдын def болон
use цэгүүдийг эх кодын мөрийн дугаараар тодорхойлов.

| Мөр | Үйлдэл | Definition | Use |
|---|---|---|---|
| 13 | gcd(int x, int y) | x, y | - |
| 14 | int tmp | - | - |
| 15 | while (y != 0) | - | y (p-use) |
| 16 | tmp = x % y | tmp | x, y (c-use) |
| 17 | x = y | x | y (c-use) |
| 18 | y = tmp | y | tmp (c-use) |
| 20 | return x | - | x (c-use) |

15-р мөрийн нөхцөлийн T/F салааг шалгасан.
G1 дээр нөхцөл эхнээсээ false.
G2 дээр давталтын явцад true, дараа нь false болсон.

### 2. DU хосууд

Нийт 11 DU хос тодорхойлсон:

- x: 4 хос
- y: 6 хос
- tmp: 1 хос

| Хувьсагч | Def мөр | Use мөр | Төрөл | Def-clear зам | Тестийн ID | Хамрагдсан |
|---|---|---|---|---|---|---|
| x | 13 | 16 | c-use | 13→15→16 | G2, G3 | Тийм |
| x | 13 | 20 | c-use | 13→15→20 | G1 | Тийм |
| x | 17 | 16 | c-use | 17→18→15→16 | G2 | Тийм |
| x | 17 | 20 | c-use | 17→18→15→20 | G2, G3 | Тийм |
| y | 13 | 15 | p-use | 13→15 | G1, G2, G3 | Тийм |
| y | 13 | 16 | c-use | 13→15→16 | G2, G3 | Тийм |
| y | 13 | 17 | c-use | 13→15→16→17 | G2, G3 | Тийм |
| y | 18 | 15 | p-use | 18→15 | G2, G3 | Тийм |
| y | 18 | 16 | c-use | 18→15→16 | G2 | Тийм |
| y | 18 | 17 | c-use | 18→15→16→17 | G2 | Тийм |
| tmp | 16 | 18 | c-use | 16→17→18 | G2, G3 | Тийм |

### 3. JUnit тест

3 тест бичиж ажиллуулав.

| Тест | Оролт | Expected |
|---|---|---|
| testGcdWithoutLoop | (7, 0) | 7 |
| testGcdWithTwoIterations | (12, 8) | 4 |
| testGcdWithZeroFirstInput | (0, 5) | 5 |

gcd(7, 0) нь давталтад орохгүй тул x-ийн
13→20 хосыг хамарсан.

gcd(12, 8) нь 2 давталт хийсэн.
Ингэснээр давталтын дараагийн эргэлтэд
ашиглагдах DU хосууд хамрагдсан.

### 4. Үр дүн

mvn test:
- Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS

Үр дүн: gcd() функцийн тодорхойлсон DU хосууд
бүгд сонгосон тестүүдээр хамрагдсан.