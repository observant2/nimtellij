import strutils, sequtils

let keywordFile = open("keywords.txt")

var keywords: seq[string]

for line in keywordFile.lines:
  keywords.add(line.split(' '))

keywordFile.close()

let outFile = open("output.txt", fmWrite)

outFile.writeLine("// keywords")
outFile.writeLine("")

for keyword in keywords:
  outFile.writeLine("@JvmField val ",
                   keyword.toUpper,
                   " = NimElementType(\"",
                   keyword.toLower,
                   "\")"
                  )

outFile.writeLine("")
outFile.writeLine("")

outFile.writeLine("@JvmField val KEYWORDS = TokenSet.create(")
outFile.writeLine(keywords.map(toUpper).join(", "))
outFile.writeLine(")")

outFile.writeLine("")
outFile.writeLine("")
outFile.writeLine("")

outFile.writeLine("// for .flex file:")

for keyword in keywords:
  outFile.writeLine("\"", keyword.toLower, "\"",
                    "\t",
                    "{ return ",
                    keyword.toUpper,
                    "; }"
                   )