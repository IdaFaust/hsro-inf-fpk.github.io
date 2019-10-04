#!/bin/bash

echo "-> Create tmp file"
python ../../scripts/md2beamer.py $1

echo "-> tmp created!"

echo "->Create pdf"
pandoc $1.tmp -o $2 -t beamer -V fontsize=9pt --pdf-engine=xelatex -V theme:metropolis -V colortheme=crane

rm $1.tmp
echo "->tmp deleted!"

