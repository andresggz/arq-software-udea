#!/usr/bin/bash

# destroy eliminate all the files that pdflatex and bibtex 
# tools generate. All the documents inside docs will be ignored.
find ./ -type d -name docs -prune -o \
    -type f \( \
    -name \*.aux  -o \
    -name \*.log  -o \
    -name \*.out  -o \
    -name \*.toc  -o \
    -name \*.bbl  -o \
    -name \*.bcf  -o \
    -name \*.blg  -o \
    -name \*.pdf  -o \
    -name \*.dvi  -o \
    -name \*x.bib -o \
    -name \*.\*n.xml \
    \) -exec echo destroyed: {} \; -exec rm {} \;
