#!/bin/bash

python -m http.server &
PID=$!

for FILE in ./assets/*
do
    if [ -d $FILE ]
    then
        cd $FILE

        NAME="$(cut -d'/' -f3<<<"$FILE")"
        echo $NAME

        if [ -e ./slides.md ] && [ -e ./$NAME.pdf ]
        then
            if [ ./slides.md -nt ./$NAME.pdf ]
            then
                echo "Generate PDF for $NAME..."
                decktape remark http://localhost:8000/assets/$NAME/slides.html $NAME.pdf
                echo ".. done!"
            fi
            echo "--"
        fi
        if [ -e ./slides.md ] && [ $1="--f" ]
        then
            echo "Generate PDF for $NAME..."
            decktape remark http://localhost:8000/assets/$NAME/slides.html $NAME.pdf
            echo ".. done!"
        fi
        cd ../..
    fi
done

kill $PID
