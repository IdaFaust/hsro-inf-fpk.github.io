#!/bin/bash

python -m http.server &
PID=$!

echo $1
echo $2

for FILE in ./assets/*
do
    if [ -d $FILE ]
    then
        cd $FILE

        NAME="$(cut -d'/' -f3<<<"$FILE")"
        echo $NAME
        NR="$(cut -d'-' -f1<<<"$NAME")"
        echo $NR
        if [ $1 = "--d" ]; 
        then
            echo "--d"
            if [ $2 = $NR ]; 
            then
                echo "Generate PDF for $NAME..."
                decktape remark http://localhost:8000/assets/$NAME/slides.html $NAME.pdf
                echo ".. done!"
            fi
        elif [ $1 = "--a" ];
        then
            echo "--a"
            if [ ./slides.md -nt ./$NAME.pdf ];
            then
                echo "Generate PDF for $NAME..."
                decktape remark http://localhost:8000/assets/$NAME/slides.html $NAME.pdf
                echo ".. done!"
            fi
            echo "--"
        elif [ $1 = "--f" ];
        then
            echo "--f"
            if [ -e ./slides.md ];
            then
                echo "Generate PDF for $NAME..."
                decktape remark http://localhost:8000/assets/$NAME/slides.html $NAME.pdf
                echo ".. done!"
            fi
        fi
        cd ../..
    fi
done

kill $PID
