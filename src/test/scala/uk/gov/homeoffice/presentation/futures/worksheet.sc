def happyBirthday(name: String) = (1 to 4) map { line =>
 s"Happy Birthday ${if (line == 3) s"dear $name" else "to You"}"
} foreach println