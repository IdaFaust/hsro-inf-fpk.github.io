import codecs
import os
import sys

LE = len(os.linesep) + 1 

def check(line):
    newAttribute = []
    preAttributes = []

    if line.startswith(".center["):
        line = line[len(".center["):len(line)-LE]
        preAttributes.append("\centering")

    if "![:scale" in line:
        pos = line.index("![:scale ")
        value = line[len("![:scale ") + pos: len("![:scale ") + 3 + pos]
        newAttribute.append("width=" + value)
        start = len("![:scale ") + 4 + pos
        end = len(line)
        line =  "![]" + line[start:end]

    if line.startswith(".footenote["):
        line = "\n*" + line[len(".footenote["):len(line)-LE]+"*\n"
        # newAttribute.append("center")

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
