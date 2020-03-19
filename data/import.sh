mongoimport --uri="mongodb+srv://batman:twbootcamp@mongocluster-kuvl0.azure.mongodb.net/MongoCluster" --collection="language" --file=language.json --mode=upsert
mongoimport --uri="mongodb+srv://batman:twbootcamp@mongocluster-kuvl0.azure.mongodb.net/MongoCluster" --collection="genre" --file=genre.json --mode=upsert
mongoimport --uri="mongodb+srv://batman:twbootcamp@mongocluster-kuvl0.azure.mongodb.net/MongoCluster" --collection="productionCompany" --file=productionCompany.json --mode=upsert
mongoimport --uri="mongodb+srv://batman:twbootcamp@mongocluster-kuvl0.azure.mongodb.net/MongoCluster" --collection="movie" --file=movie.json --mode=upsert
