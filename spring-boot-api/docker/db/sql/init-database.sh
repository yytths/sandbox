#!/bin/sh

#run the setup script to create the DB and the schema in the DB
mysql -u myuser -p1234 myschema < "/docker-entrypoint-initdb.d/01.create.tables.sql"