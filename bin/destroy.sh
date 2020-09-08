#!/usr/bin/bash
# destroy all the files that pdflatex tool generates when it compiles:
# - *.aux
# - *.log
# - *.out
# - *.toc
# + *.pdf [this one isn't destroyed]
find ./ -type  f \( \
    -name \*.aux -o \
    -name \*.log -o \
    -name \*.out -o \
    -name \*.toc    \
    \) -exec echo destroyed: {} \; -exec rm {} \;

