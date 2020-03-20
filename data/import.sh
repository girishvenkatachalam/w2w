if [ -z "$1" ]
  then
    echo "Usage: ./import.sh <db-url>"
    exit 1
fi

ls *.json | sed 's/.metadata.json//' | while read col; do mongoimport --uri="$1" --mode=upsert -c $col < $col; done
