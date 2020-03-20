if [ -z "$1" ]
  then
    echo "Usage: ./import.sh <db-url>"
    exit 1
fi

for filename in *.json; do
  mongoimport --uri="$1" --file="$filename" --mode=upsert
done
