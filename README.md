w2w

# Installation

## Maven

 - `brew install maven`
 
## Frontend
 - `brew install yarn`
 - `brew install node`
 
In Chrome, install the following Extensions,
- `React Developer Tools`
- `Redux DevTools`

## Mongodb local
 - `brew tap mongodb/brew`
 - `brew install mongodb-community`

To start mongo services,
- `brew services start mongodb-community`

## Heroku (Optional)

 - `brew install heroku/brew/heroku`
 - `heroku login`

## Travis (Optional)

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

To verify application is running try http://localhost:8080/hello
