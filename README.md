# What to Watch [![Build Status](https://travis-ci.org/anoopelias/w2w.svg?branch=master)](https://travis-ci.org/anoopelias/w2w)

Decide what to watch using this recommendation platform.

## Installation

### Maven

 - `brew install maven`
 
### Frontend
 - `brew install yarn`
 - `brew install node`
 
In Chrome, install the following Extensions,
- `React Developer Tools`
- `Redux DevTools`

### Mongodb local
 - `brew tap mongodb/brew`
 - `brew install mongodb-community`

To start mongo services,
- `brew services start mongodb-community`

### Heroku (Optional)

 - `brew install heroku/brew/heroku`
 - `heroku login`

### Travis (Optional)

Travis commandline is needed if you want to setup the apikey in `.travis.yml`

(Do not use the Ruby that comes with mac, that one do not allow ruby gem to be in path without `sudo`)
 - `brew install ruby`
 - `echo 'export PATH="/usr/local/opt/ruby/bin:$PATH"' >> ~/.bash_profile`
 - `source ~/.bash_profile`
 - `gem install travis -v 1.8.11 --no-document`
 
This will not get `travis` in path since the ruby gem folder is not there in path.

 - `echo 'export PATH="/usr/local/lib/ruby/gems/2.7.0/bin:$PATH"' >> ~/.bash_profile`
 - `source ~/.bash_profile`
 - `travis version`

Phew!

## Verify

To verify application is running locally, try http://localhost:8080/hello

Integration URL https://whattowatch-int.herokuapp.com/

## Developer Guidelines

### Commit messages

Use the follow format for commit messages,

```
<dev1><|dev2> <commit message>
```

For example,
```
commit d82e7b517edc965fb8530835077a5a541ebb8a39 (HEAD -> master)
Author: Anoop Elias <anoopelias@gmail.com>
Date:   Mon Mar 16 13:59:40 2020 +0530

    <Anoop>|<Tapas> Added integrtion URL to readme

```
