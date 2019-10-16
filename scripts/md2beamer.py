import codecs
import os
import sys

LE = len(os.linesep) + 1

# multiple columns
columnOpen   = "::: columns"
inColumn     = False
inClassSplit = False
noColumn     = 0

# title slide
isTitle     = False
noTitleLine = 0
titleInstitute = ["institute:","- 'Bachelor Wirtschaftsinformatik, Fakultaet fuer Informatik'", "- 'Technische Hochschule Rosenheim'"]


def check(line):
    newAttribute = []
    preAttributes = []
    global columnOpen
    global inColumn
    global inClassSplit
    global noColumn
    global isTitle
    global noTitleLine
    global titleInstitute 

    line = line.replace("∧","/\\")
    line = line.replace("∨","v")
    line = line.replace("⇔","<=>")
    line = line.replace("⇒","=>")

    # Title frame
    if line.startswith("class: title-slide"):
        line = "---\n"
        isTitle = True
    else:
        if isTitle:
            noTitleLine += 1
            # print("%s : %s" % (noTitleLine ,line))
            if line.startswith("---"):
                isTitle = False 
                preAttributes.append("...")
                noTitleLine = 0
            else:
                if noTitleLine <2 or noTitleLine > 8 or noTitleLine == 3:
                    line = ""
                if noTitleLine == 2:
                    line = "title: '" + line[2:-1] + "'\n"
                if noTitleLine == 5:
                    line = "subtitle: '" + line[3:-1] + "'\n"
                if noTitleLine == 6:
                    line = "author: '" + line[4:-1] + "'\n"
                if noTitleLine == 7:
                    preAttributes = titleInstitute
                    line = "" 

    # multi column frame
    if line.startswith("class: split"):
        line = "\n"
        columnOpen = "::: columns"
        inClassSplit = True

    if line.startswith(".column["):
        inColumn = True
        noColumn += 1
        line = columnOpen + "\n\n:::: column\n"
        if inClassSplit:
            columnOpen = ""

    if line.startswith("]") and inColumn:
        line = "\n::::"
        inColumn = False
        if inClassSplit and noColumn == 2:
            line = line + "\n:::\n"
            inClassSplit = False
            noColumn = 0

    if line.startswith(".left-column["):
        inClassSplit = True
        inColumn = True
        noColumn = 1
        line = "::: columns\n\n:::: column\n"

    if line.startswith(".right-column["):
        inColumn = True
        noColumn = 2
        line = "\n\n:::: column\n"

    # images
    if line.startswith(".center["):
        line = line[len(".center["):len(line)-LE]
        preAttributes.append("\centering")

    if "![:scale" in line:
        pos = line.index("![:scale ")
        value = line[len("![:scale ") + pos: len("![:scale ") + 3 + pos]
        if not inColumn:
            newAttribute.append("width=" + value)
        start = len("![:scale ") + 4 + pos
        end = len(line)
        line =  "![]" + line[start:end]

    if line.startswith(".footenote["):
        line = "\n*" + line[len(".footenote["):len(line)-LE]+"*\n"

    if line.startswith(".footnote["):
        line = "{\\footnotesize " + line[len(".footnote["):len(line)-LE]+"}\n"

    if len(preAttributes) > 0:
        line = str.join("\n", preAttributes) + "\n" + line

    if len(newAttribute) > 0:
        line = line + "{" + str.join(",", newAttribute) + "}" + "\n"
    return line


def process_file(file):
    outlines = []
    with codecs.open(file, 'r', 'utf-8') as fp:
        line = fp.readline()
        cnt = 1
        while line:
            line = check(line)
            # print("Line {}: {}".format(cnt, line.strip()))
            outlines.append(line)
            line = fp.readline()
            cnt += 1

    with codecs.open(file + '.tmp', 'w', 'utf-8') as out:
        out.writelines(outlines)

if __name__ == "__main__":
    filename = sys.argv[1]

    process_file(filename)
